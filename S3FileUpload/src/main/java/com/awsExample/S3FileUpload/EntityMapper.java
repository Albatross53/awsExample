package com.awsExample.S3FileUpload;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMapper {
    Entity entityPostToEntity(EntityDto.EntityPost entityPost);

    EntityDto.EntityResponse entityToEntityResponse(Entity entity);
}
