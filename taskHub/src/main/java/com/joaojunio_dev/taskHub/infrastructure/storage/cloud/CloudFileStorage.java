package com.joaojunio_dev.taskHub.infrastructure.storage.cloud;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.contentSources.B2ByteArrayContentSource;
import com.backblaze.b2.client.contentSources.B2ContentTypes;
import com.backblaze.b2.client.structures.B2Bucket;
import com.backblaze.b2.client.structures.B2FileVersion;
import com.backblaze.b2.client.structures.B2UploadFileRequest;
import com.joaojunio_dev.taskHub.config.B2Properties;
import com.joaojunio_dev.taskHub.exceptions.B2Exception;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CloudFileStorage {

    private final B2StorageClient client;
    private final B2Properties props;

    public CloudFileStorage(B2StorageClient client, B2Properties props) {
        this.client = client;
        this.props = props;
    }

    private B2Bucket getBucket() throws Exception {
        var bucket = client.getBucketOrNullByName(props.getBucketName());
        if (bucket == null) throw new ObjectIsNullException("The Bucket is not exists or null!");
        return bucket;
    }

    public B2FileVersion uploadProfileImage(MultipartFile image) {
        try {
            final B2Bucket bucket = getBucket();
            final String bucketId = bucket.getBucketId();
            final B2ByteArrayContentSource source = (B2ByteArrayContentSource) B2ByteArrayContentSource.build(image.getBytes());

            final B2UploadFileRequest request = B2UploadFileRequest
                .builder(bucketId, image.getOriginalFilename(), B2ContentTypes.B2_AUTO, source)
                .build();

            return client.uploadSmallFile(request);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new B2Exception("Error uploading profile picture");
        }
    }
}
