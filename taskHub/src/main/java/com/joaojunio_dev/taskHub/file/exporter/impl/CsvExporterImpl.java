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
        return exportingDataOfTasks(tasks);
    }

    @Override
    public Resource exportTasksByPersonId(List<TaskHistory> tasks) throws Exception {
        return exportingDataOfTasks(tasks);
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
}
