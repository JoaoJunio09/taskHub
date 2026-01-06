package com.joaojunio_dev.taskHub.file.exporter.impl;

import com.joaojunio_dev.taskHub.file.exporter.contract.TaskExporter;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.csv.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.OutputStreamWriter;
import java.util.List;

public class CsvExporterImpl implements TaskExporter {
    @Override
    public Resource exportTasks(List<TaskHistory> tasks) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

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
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }

    @Override
    public Resource exportTask(TaskHistory task) throws Exception {
        return null;
    }
}
