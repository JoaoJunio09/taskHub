package com.joaojunio_dev.taskHub.file.exporter.impl;

import com.joaojunio_dev.taskHub.file.exporter.contract.TaskExporter;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class XlsxExporterImpl implements TaskExporter {
    @Override
    public Resource exportTasks(List<TaskHistory> tasks) throws Exception {
        try (Workbook workBook = new XSSFWorkbook()) {
            Sheet sheet = workBook.createSheet("Task's Historys");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Title", "Occurred At", "Action", "First Name"};
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
                row.createCell(2).setCellValue(task.getOccuredAt());
                row.createCell(3).setCellValue(String.valueOf(task.getType()));
                row.createCell(4).setCellValue(task.getPerson().getFirstName());
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workBook.write(outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }

    @Override
    public Resource exportTask(TaskHistory task) throws Exception {
        return null;
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
