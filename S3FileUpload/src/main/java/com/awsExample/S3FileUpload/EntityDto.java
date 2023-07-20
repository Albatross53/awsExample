package com.awsExample.S3FileUpload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EntityDto {
    @Getter
    @NoArgsConstructor
    public static class EntityPost{
        private String entityName;
    }

    @Builder
    public static class EntityResponse{
        private long entityId;
        private String entityName;
        private String imageName;
    }


}
