package com.vehisoc.scheduler;

import com.vehisoc.entity.Visitors;
import com.vehisoc.repository.VisitorRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class VisitorBackupScheduler {

    @Autowired
    private VisitorRepository visitorRepository;


    @Scheduled(cron = "0 59 23 * * *")
    public void backupDailyVisitors() {
        System.out.println("Running daily visitor backup at: " + LocalDateTime.now());

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);


        List<Visitors> visitors = visitorRepository.findAll().stream()
                .filter(v -> v.getTimeIn() != null &&
                        !v.getTimeIn().isBefore(startOfDay) &&
                        !v.getTimeIn().isAfter(endOfDay))
                .toList();

        if (visitors.isEmpty()) {
            System.out.println("No visitors found for today.");
            return;
        }


        String folderPath = "C:/visitors_log";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // File name format
        String fileName = "Visitors_history_log_" +
                today.format(DateTimeFormatter.ofPattern("ddMMyyyy")) + ".xlsx";
        File file = new File(folder, fileName);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Visitors Log");

            // Header row
            Row header = sheet.createRow(0);
            String[] columns = {"ID", "Visitor Name", "Vehicle Name", "Vehicle Reg No", "Purpose",
                    "Time In", "Time Out", "Visit Duration", "Phone Number",
                    "Visitor Type", "Resident Name", "Flat No"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(columns[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Fill data
            int rowNum = 1;
            for (Visitors v : visitors) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(v.getId());
                row.createCell(1).setCellValue(v.getVisitorName());
                row.createCell(2).setCellValue(v.getVehicleName());
                row.createCell(3).setCellValue(v.getVehicleRegNo());
                row.createCell(4).setCellValue(v.getVisitPurpose());
                row.createCell(5).setCellValue(v.getTimeIn() != null ? v.getTimeIn().toString() : "");
                row.createCell(6).setCellValue(v.getTimeOut() != null ? v.getTimeOut().toString() : "");
                row.createCell(7).setCellValue(v.getVisitDuration() != null ? v.getVisitDuration() : "");
                row.createCell(8).setCellValue(v.getPhoneNumber());
                row.createCell(9).setCellValue(v.getVisitorType() != null ? v.getVisitorType().name() : "");
                if (v.getResident() != null) {
                    row.createCell(10).setCellValue(v.getResident().getFName() + " " + v.getResident().getLName());
                    row.createCell(11).setCellValue(v.getResident().getFlatNo());
                } else {
                    row.createCell(10).setCellValue("");
                    row.createCell(11).setCellValue("");
                }
            }


            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }


            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }

            System.out.println("Backup created successfully at: " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
