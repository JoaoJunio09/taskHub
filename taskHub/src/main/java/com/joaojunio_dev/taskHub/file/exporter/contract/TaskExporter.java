package com.joaojunio_dev.taskHub.file.exporter.contract;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import org.springframework.core.io.Resource;

import java.util.List;

public interface TaskExporter {

    Resource exportTasks(List<TaskDTO> tasks) throws Exception;
    Resource exportTask(TaskDTO task) throws Exception;
}
