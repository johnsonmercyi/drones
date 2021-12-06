package com.soft.test.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public class EntityWithUUID {
    @Id
    @Column(
        name = "id",
        insertable = false, 
        nullable = false, 
        columnDefinition = "UUID default uuid_generate_v4()"
    )
    private UUID id;

    public EntityWithUUID () {
        this.id = UUID.randomUUID();
    }
}
