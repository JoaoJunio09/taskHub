package com.joaojunio_dev.taskHub.file.exporter.factory;

import com.joaojunio_dev.taskHub.file.exporter.contract.TaskHistoryExporter;
import com.joaojunio_dev.taskHub.file.exporter.impl.CsvExporterImpl;
import com.joaojunio_dev.taskHub.file.exporter.impl.PdfExporterImpl;
import com.joaojunio_dev.taskHub.file.exporter.impl.XlsxExporterImpl;
import com.joaojunio_dev.taskHub.mediatype.MediaType;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class FileExporterFactory {

    @Autowired
    private ApplicationContext context;

    public TaskHistoryExporter getExporter(String acceptHeader) throws Exception {
        if (acceptHeader.equalsIgnoreCase(MediaType.APPLICATION_XLSX_VALUE)) {
            return context.getBean(XlsxExporterImpl.class);
        }
        else if (acceptHeader.equalsIgnoreCase(MediaType.APPLICATION_CSV_VALUE)) {
            return context.getBean(CsvExporterImpl.class);
        }
        else if (acceptHeader.equalsIgnoreCase(MediaType.APPLICATION_PDF_VALUE)) {
            return context.getBean(PdfExporterImpl.class);
        }
        else {
            throw new BadRequestException("Invalid File Format!");
        }
    }
}
