package com.joaojunio_dev.taskHub.file.importer.contract;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.model.Task;

import java.io.InputStream;
import java.util.List;

public interface TaskImporter {

    List<TaskDTO> importerTasks(InputStream inputStream) throws Exception;
}
