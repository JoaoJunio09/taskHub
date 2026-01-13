package com.joaojunio_dev.taskHub.exporter.contract;

import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.springframework.core.io.Resource;

import java.util.List;

public interface TaskHistoryExporter {

    Resource exportTasks(List<TaskHistory> tasks) throws Exception;
    Resource exportTasksByPersonId(List<TaskHistory> tasks) throws Exception;
}
