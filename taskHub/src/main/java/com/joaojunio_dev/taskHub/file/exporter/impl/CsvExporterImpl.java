package com.joaojunio_dev.taskHub.file.exporter.impl;

import com.joaojunio_dev.taskHub.file.exporter.contract.TaskHistoryExporter;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.csv.*;
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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        CSVFormat csvFormat = CSVFormat.Builder.create()
            .setHeader("ID", "Title", "Occurred At", "Action", "First name")
            .setSkipHeaderRecord(false)
            .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
            for (TaskHistory task : tasks) {
                csvPrinter.printRecord(
                    task.getId(),
                    task.getTask().getTitle(),
                    task.getOccuredAt(),
                    task.getType(),
                    task.getPerson().getFirstName()
                );
            }
            csvPrinter.flush();
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }

    @Override
    public Resource exportTask(TaskHistory task) throws Exception {
        return null;
    }
}
