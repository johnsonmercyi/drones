package com.soft.test.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@MappedSuperclass
@Data
public class EntityWithUUID {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", insertable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    private UUID id;

    public EntityWithUUID() {
        this.id = UUID.randomUUID();
    }
}
