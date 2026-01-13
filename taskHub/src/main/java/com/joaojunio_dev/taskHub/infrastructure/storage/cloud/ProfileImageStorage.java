package com.joaojunio_dev.taskHub.infrastructure.storage.cloud;

import com.joaojunio_dev.taskHub.data.dto.storage.StoredFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageStorage {

    StoredFileResponse uploadProfileImage(MultipartFile image);
    Resource getProfileImage(String fileId);
}
