package com.joaojunio_dev.taskHub.exporter.impl;

import com.joaojunio_dev.taskHub.data.dto.report.PersonTaskHistoryReportDTO;
import com.joaojunio_dev.taskHub.data.dto.report.TaskHistoryReportDTO;
import com.joaojunio_dev.taskHub.exporter.contract.TaskHistoryExporter;
import com.joaojunio_dev.taskHub.infrastructure.qrcode.QRCodeService;
import com.joaojunio_dev.taskHub.infrastructure.storage.cloud.B2ProfileImageStorage;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PdfExporterImpl implements TaskHistoryExporter {

    @Autowired
    private B2ProfileImageStorage profileImageStorageGateway;

    @Autowired
    private QRCodeService qrCodeService;

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
    public Resource exportTasksByPersonId(List<TaskHistoryReportDTO> tasks, PersonTaskHistoryReportDTO person, String routeGetProfileImage) throws Exception {
        try {
            InputStream mainTemplateStream = getClass().getResourceAsStream("/templates/taskHistoryByPerson.jrxml");
            if (mainTemplateStream == null) throw new RuntimeException("Template file not found: /templates/taskHistoryByPerson.jrxml");

            InputStream subReportStream = getClass().getResourceAsStream("/templates/tasksHistory.jrxml");
            if (subReportStream == null) throw new RuntimeException("Template file not found: /templates/tasksHistory.jrxml");

            JasperReport mainReport = JasperCompileManager.compileReport(mainTemplateStream);
            JasperReport subReport = JasperCompileManager.compileReport(subReportStream);

            InputStream qrCodeStream = qrCodeService.generateQRCode(routeGetProfileImage, 200, 200);

            JRBeanCollectionDataSource subDataSource = new JRBeanCollectionDataSource(tasks);

            InputStream profileImage = profileImageStorageGateway.getProfileImageInputStream(person.getProfileImageFileId());

            String path = getClass().getResource("/templates/tasksHistory.jasper").getPath();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("PERSON_ID", person.getId());
            parameters.put("SUB_REPORT_DATA_SOURCE", subDataSource);
            parameters.put("SUB_REPORT_DIR", path);
            parameters.put("QR_CODEIMAGE", qrCodeStream);
            parameters.put("PROFILE_IMAGE", profileImage);

            JRBeanCollectionDataSource mainDataSource = new JRBeanCollectionDataSource(Collections.singletonList(person));

            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport, parameters, mainDataSource);

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                return new ByteArrayResource(outputStream.toByteArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
