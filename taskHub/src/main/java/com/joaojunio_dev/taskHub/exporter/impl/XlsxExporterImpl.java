package com.joaojunio_dev.taskHub.exporter.impl;

import com.joaojunio_dev.taskHub.data.dto.report.PersonTaskHistoryReportDTO;
import com.joaojunio_dev.taskHub.data.dto.report.TaskHistoryReportDTO;
import com.joaojunio_dev.taskHub.exporter.contract.TaskHistoryExporter;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class XlsxExporterImpl implements TaskHistoryExporter {
    @Override
    public Resource exportTasks(List<TaskHistory> tasks) throws Exception {
        return exportingDataOfTasks(tasks);
    }

    @Override
    public Resource exportTasksByPersonId(List<TaskHistoryReportDTO> tasks, PersonTaskHistoryReportDTO person) throws Exception {
        return exportingDataOfTasksById(tasks);
    }

    private ByteArrayResource exportingDataOfTasks(List<TaskHistory> tasks) throws Exception {
        try (Workbook workBook = new XSSFWorkbook()) {
            Sheet sheet = workBook.createSheet("Task's History Exported in XLSX");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Title", "Description", "Occurred At", "Action", "Person"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workBook));
            }

            int rowIndex = 1;
            for (TaskHistory task : tasks) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(task.getId());
                row.createCell(1).setCellValue(task.getTask().getTitle());
                row.createCell(2).setCellValue(task.getTask().getDescription());
                row.createCell(3).setCellValue(task.getOccurredAt());
                row.createCell(4).setCellValue(String.valueOf(task.getType()));
                row.createCell(5).setCellValue(task.getPerson().getFirstName() + " " + task.getPerson().getLastName());
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workBook.write(outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }

    private ByteArrayResource exportingDataOfTasksById(List<TaskHistoryReportDTO> tasks) throws Exception {
        try (Workbook workBook = new XSSFWorkbook()) {
            Sheet sheet = workBook.createSheet("Task's History Exported in XLSX");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Title", "Description", "Occurred At", "Action"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workBook));
            }

            int rowIndex = 1;
            for (TaskHistoryReportDTO task : tasks) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(task.getId());
                row.createCell(1).setCellValue(task.getTitle());
                row.createCell(2).setCellValue(task.getDescription());
                row.createCell(3).setCellValue(task.getOccurredAt());
                row.createCell(4).setCellValue(String.valueOf(task.getAction()));
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workBook.write(outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }


    private CellStyle createHeaderCellStyle(Workbook workBook) {
        CellStyle style = workBook.createCellStyle();
        Font font = workBook.createFont();;
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}
