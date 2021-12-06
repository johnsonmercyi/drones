package com.soft.test.model.drone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.soft.test.model.EntityWithUUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drone_states")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneState extends EntityWithUUID {
    @Column(nullable = false)
    private String name;
}
