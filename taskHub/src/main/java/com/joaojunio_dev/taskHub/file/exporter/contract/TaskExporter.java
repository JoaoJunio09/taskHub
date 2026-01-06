package com.joaojunio_dev.taskHub.file.exporter.contract;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.springframework.core.io.Resource;

import java.util.List;

public interface TaskExporter {

    Resource exportTasks(List<TaskHistory> tasks) throws Exception;
    Resource exportTask(TaskHistory task) throws Exception;
}
