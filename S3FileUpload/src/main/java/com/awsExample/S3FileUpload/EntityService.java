package com.awsExample.S3FileUpload;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class EntityService {
    private final EntityRepository entityRepository;
    private final Storage storage;
//    @Value("${spring.cloud.bucket}") // application.yml에 써둔 bucket 이름
    private String bucketName = "color_pick";

    public EntityService(EntityRepository entityRepository, Storage storage) {
        this.entityRepository = entityRepository;
        this.storage = storage;
    }

//    private final S3Uploader s3Uploader;

//    public EntityService(EntityRepository entityRepository, S3Uploader s3Uploader) {
//        this.entityRepository = entityRepository;
//        this.s3Uploader = s3Uploader;
//    }
//
//    public Entity createEntity(Entity entity, MultipartFile image) throws IOException {
//        if(!image.isEmpty()) {
//            String storedFileName = s3Uploader.upload(image,"entity");
//            entity.setImageName(storedFileName);
//        }
//
//        return entityRepository.save(entity);
//    }

    public Entity createEntity(Entity entity, MultipartFile image) throws IOException {
        if(!image.isEmpty()) {
            String storedFileName = image.getName();
            entity.setImageName(storedFileName);
        }

        // !!!!!!!!!!!이미지 업로드 관련 부분!!!!!!!!!!!!!!!
        String uuid = UUID.randomUUID().toString(); // Google Cloud Storage에 저장될 파일 이름
        String ext = image.getContentType(); // 파일의 형식 ex) JPG

        // Cloud에 이미지 업로드
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, uuid)
                        .setContentType(ext)
                        .build(),
                image.getInputStream()
        );

        return entityRepository.save(entity);
    }
}
