package com.joaojunio_dev.taskHub.exporter.impl;

import com.joaojunio_dev.taskHub.exporter.contract.TaskHistoryExporter;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PdfExporterImpl implements TaskHistoryExporter {

    @Override
    public Resource exportTasks(List<TaskHistory> tasks) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/templates/tasks.jrxml");
        if (inputStream == null) throw new RuntimeException("Template file not found: /templates/tasks.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tasks);
        Map<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }

    @Override
    public Resource exportTasksByPersonId(List<TaskHistory> tasks) throws Exception {
        return null;
    }
}
