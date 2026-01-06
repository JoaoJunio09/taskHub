package com.joaojunio_dev.taskHub.file.exporter.factory;

import com.joaojunio_dev.taskHub.file.exporter.contract.TaskExporter;
import com.joaojunio_dev.taskHub.mediatype.MediaType;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class FileExporterFactory {

    @Autowired
    private ApplicationContext context;

    public TaskExporter getExporter(String acceptHeader) throws Exception {
        if (acceptHeader.equalsIgnoreCase(MediaType.APPLICATION_XLSX_VALUE)) {

        }
        else if (acceptHeader.equalsIgnoreCase(MediaType.APPLICATION_CSV_VALUE)) {

        }
        else if (acceptHeader.equalsIgnoreCase(MediaType.APPLICATION_PDF_VALUE)) {

        }
        else {
            throw new BadRequestException("Invalid File Format!");
        }
    }
}
