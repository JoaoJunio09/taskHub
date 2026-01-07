package com.joaojunio_dev.taskHub.file.importer.factory;

import com.joaojunio_dev.taskHub.file.importer.contract.TaskImporter;
import com.joaojunio_dev.taskHub.file.importer.impl.CsvImporterImpl;
import com.joaojunio_dev.taskHub.file.importer.impl.XlsxImporterImpl;
import com.joaojunio_dev.taskHub.mediatype.MediaTypes;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileImporterFactory {

    @Autowired
    private ApplicationContext context;

    public TaskImporter getImporter(String acceptHeader) throws Exception {
        if (acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_CSV_VALUE)) {
            return context.getBean(CsvImporterImpl.class);
        }
        else if (acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_XLSX_VALUE)) {
            return context.getBean(XlsxImporterImpl.class);
        }
        else {
            throw new BadRequestException("Invalid File Format!");
        }
    }
}
