package com.joaojunio_dev.taskHub.importer.impl;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.importer.contract.TaskImporter;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class XlsxImporterImpl implements TaskImporter {
    @Override
    public List<TaskDTO> importerTasks(InputStream inputStream) throws Exception {
        return List.of();
    }
}
