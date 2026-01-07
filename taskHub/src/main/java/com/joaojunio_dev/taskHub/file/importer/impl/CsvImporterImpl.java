package com.joaojunio_dev.taskHub.file.importer.impl;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.file.importer.contract.TaskImporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CsvImporterImpl implements TaskImporter {
    @Override
    public List<TaskDTO> importerTasks(InputStream inputStream) throws Exception {
        CSVFormat format = CSVFormat.Builder.create()
            .setHeader()
            .setSkipHeaderRecord(true)
            .setIgnoreEmptyLines(true)
            .setTrim(true)
            .build();
        Iterable<CSVRecord> records = format.parse(new InputStreamReader(inputStream));
        return parseRecordsToTaskDTOs(records);
    }

    private List<TaskDTO> parseRecordsToTaskDTOs(Iterable<CSVRecord> records) {
        List<TaskDTO> tasks = new ArrayList<>();
        for (CSVRecord record : records) {
            TaskDTO task = new TaskDTO();
            task.setTitle(record.get("title"));
            task.setDescription(record.get("description"));
            task.setDate(LocalDateTime.parse(record.get("occurred at")));
            task.setCompleted(getCompletedRecord(record.get("completed")));
            task.setPersonId(Long.parseLong(record.get("person_id")));
            tasks.add(task);
        }
        return tasks;
    }

    private Boolean getCompletedRecord(String completed) {
        return completed.equalsIgnoreCase("true") ? true : false;
    }
}
