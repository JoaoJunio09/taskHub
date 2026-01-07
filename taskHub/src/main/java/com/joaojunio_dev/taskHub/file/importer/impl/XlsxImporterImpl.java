package com.joaojunio_dev.taskHub.file.importer.impl;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.file.importer.contract.TaskImporter;

import java.io.InputStream;
import java.util.List;

public class XlsxImporterImpl implements TaskImporter {
    @Override
    public List<TaskDTO> importerTasks(InputStream inputStream) throws Exception {
        return List.of();
    }
}
