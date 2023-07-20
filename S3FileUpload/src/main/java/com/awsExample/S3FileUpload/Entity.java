package com.awsExample.S3FileUpload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
public class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entityId;

    private String entityName;

    @Column(length = 100)
    private String imageName;
}
