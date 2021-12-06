package com.soft.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drone_state")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneState extends EntityWithUUID {
    @Column(nullable = false)
    private String name;
}
