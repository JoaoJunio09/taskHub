package com.joaojunio_dev.taskHub.infrastructure.storage.cloud;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.contentHandlers.B2ContentSink;
import com.backblaze.b2.client.contentSources.B2ByteArrayContentSource;
import com.backblaze.b2.client.contentSources.B2ContentTypes;
import com.backblaze.b2.client.structures.B2Bucket;
import com.backblaze.b2.client.structures.B2FileVersion;
import com.backblaze.b2.client.structures.B2UploadFileRequest;
import com.joaojunio_dev.taskHub.config.B2Properties;
import com.joaojunio_dev.taskHub.data.dto.storage.StoredFileResponse;
import com.joaojunio_dev.taskHub.exceptions.FileStorageException;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class B2ProfileImageStorage implements ProfileImageStorage {

    private final static Logger logger = LoggerFactory.getLogger(B2ProfileImageStorage.class.getName());

    private final B2StorageClient client;
    private final B2Properties props;

    public B2ProfileImageStorage(B2StorageClient client, B2Properties props) {
        this.client = client;
        this.props = props;
    }

    public StoredFileResponse uploadProfileImage(MultipartFile image) {
        try {
            final B2Bucket bucket = getBucket();
            final String bucketId = bucket.getBucketId();
            final B2ByteArrayContentSource source = (B2ByteArrayContentSource) B2ByteArrayContentSource.build(image.getBytes());

            final B2UploadFileRequest request = B2UploadFileRequest
                .builder(bucketId, image.getOriginalFilename(), B2ContentTypes.B2_AUTO, source)
                .build();

            var fileVersion = client.uploadSmallFile(request);

            return new StoredFileResponse(
                fileVersion.getFileName(),
                fileVersion.getContentType(),
                fileVersion.getContentLength(),
                fileVersion.getFileId()
            );
        }
        catch (Exception e) {
            throw new FileStorageException("Error uploading profile picture");
        }
    }

    public Resource getProfileImage(String fileId) {
        try {
            B2FileVersion fileVersion = client.getFileInfo(fileId);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            B2ContentSink sink = (headers, inputStream) -> inputStream.transferTo(outputStream);
            client.downloadById(fileId, sink);

            byte[] data = outputStream.toByteArray();

            return new ByteArrayResource(data);
        }
        catch (Exception e) {
            throw new FileStorageException("Error get/downloading profile picture");
        }
    }

    public InputStream getProfileImageInputStream(String fileId) throws IOException {
        var resource = getProfileImage(fileId);
        return resource.getInputStream();
    }

    private B2Bucket getBucket() throws Exception {
        var bucket = client.getBucketOrNullByName(props.getBucketName());
        if (bucket == null) throw new ObjectIsNullException("The Bucket is not exists or null!");
        return bucket;
    }

    public String getFileName(String fileId) {
        try {
            B2FileVersion fileVersion = client.getFileInfo(fileId);
            return fileVersion.getFileName();
        }
        catch (Exception e) {
            logger.error("Could not get fileName for fileId {}", fileId, e);
            throw new RuntimeException("Could not get fileName for fileId {}");
        }
    }
}
