package com.soft.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drone_models")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneModel extends EntityWithUUID {
    @Column(nullable = false, unique = true)
    private String name;
}
