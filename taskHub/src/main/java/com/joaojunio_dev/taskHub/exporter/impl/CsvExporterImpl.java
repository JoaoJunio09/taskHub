package com.joaojunio_dev.taskHub.exporter.impl;

import com.joaojunio_dev.taskHub.data.dto.report.PersonTaskHistoryReportDTO;
import com.joaojunio_dev.taskHub.data.dto.report.TaskHistoryReportDTO;
import com.joaojunio_dev.taskHub.exporter.contract.TaskHistoryExporter;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CsvExporterImpl implements TaskHistoryExporter {
    @Override
    public Resource exportTasks(List<TaskHistory> tasks) throws Exception {
        return exportingDataOfTasks(tasks);
    }

    @Override
    public Resource exportTasksByPersonId(List<TaskHistoryReportDTO> tasks, PersonTaskHistoryReportDTO person, String routeGetProfileImage) throws Exception {
        return exportingDataOfTasksById(tasks);
    }

    private ByteArrayResource exportingDataOfTasks(List<TaskHistory> tasks) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        CSVFormat csvFormat = CSVFormat.Builder.create()
            .setHeader("ID", "Title", "Description","Occurred At", "Action", "Person")
            .setSkipHeaderRecord(true)
            .build();

        try (CSVPrinter printer = new CSVPrinter(writer, csvFormat)) {
            for (TaskHistory task : tasks) {
                printer.printRecord(
                    "ID", task.getId(),
                    "Title", task.getTask().getTitle(),
                    "Description", task.getTask().getDescription(),
                    "Occurred At", task.getOccurredAt(),
                    "Action", task.getType(),
                    "Person", task.getPerson().getFirstName() + " " + task.getPerson().getLastName()
                );
            }

            printer.flush();
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }

    private ByteArrayResource exportingDataOfTasksById(List<TaskHistoryReportDTO> tasks) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        CSVFormat csvFormat = CSVFormat.Builder.create()
            .setHeader("ID", "Title", "Description","Occurred At", "Action")
            .setSkipHeaderRecord(true)
            .build();

        try (CSVPrinter printer = new CSVPrinter(writer, csvFormat)) {
            for (TaskHistoryReportDTO task : tasks) {
                printer.printRecord(
                    "ID", task.getId(),
                    "Title", task.getTitle(),
                    "Description", task.getDescription(),
                    "Occurred At", task.getOccurredAt(),
                    "Action", task.getAction()
                );
            }

            printer.flush();
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }
}
