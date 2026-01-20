package com.joaojunio_dev.taskHub.exporter.contract;

import com.joaojunio_dev.taskHub.data.dto.report.PersonTaskHistoryReportDTO;
import com.joaojunio_dev.taskHub.data.dto.report.TaskHistoryReportDTO;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.springframework.core.io.Resource;

import java.util.List;

public interface TaskHistoryExporter {

    Resource exportTasks(List<TaskHistory> tasks) throws Exception;
    Resource exportTasksByPersonId(
        List<TaskHistoryReportDTO> tasks,
        PersonTaskHistoryReportDTO person,
        String routeGetProfileImage) throws Exception;
}
