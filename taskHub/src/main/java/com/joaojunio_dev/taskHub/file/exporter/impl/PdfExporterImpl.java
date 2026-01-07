package com.joaojunio_dev.taskHub.file.exporter.impl;

import com.joaojunio_dev.taskHub.file.exporter.contract.TaskHistoryExporter;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PdfExporterImpl implements TaskHistoryExporter {
    @Override
    public Resource exportTasks(List<TaskHistory> tasks) throws Exception {
        return null;
    }

    @Override
    public Resource exportTask(TaskHistory task) throws Exception {
        return null;
    }
}
