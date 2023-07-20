package com.awsExample.S3FileUpload;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/fileUpload")
public class EntityController {
    private final EntityMapper mapper;
    private final EntityService entityService;

    public EntityController(EntityMapper mapper, EntityService entityService) {
        this.mapper = mapper;
        this.entityService = entityService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity createEntity(@RequestPart EntityDto.EntityPost entityPost,
                                       @RequestPart MultipartFile image) throws IOException {
        Entity entity = entityService.createEntity(mapper.entityPostToEntity(entityPost), image);

        return new ResponseEntity<>(mapper.entityToEntityResponse(entity), HttpStatus.OK);
    }
}
