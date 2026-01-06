package com.joaojunio_dev.taskHub.file.exporter.impl;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.file.exporter.contract.TaskExporter;
import org.springframework.core.io.Resource;

import java.util.List;

public class PdfExporterImpl implements TaskExporter {
    @Override
    public Resource exportTasks(List<TaskDTO> tasks) throws Exception {
        return null;
    }

    @Override
    public Resource exportTask(TaskDTO task) throws Exception {
        return null;
    }
}
