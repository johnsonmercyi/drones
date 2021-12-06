package com.soft.test.model.drone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.soft.test.model.EntityWithUUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="drones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drone extends EntityWithUUID {
    
    @Column(columnDefinition="VARCHAR(100) NOT NULL", unique = true)
    private String serialNo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", referencedColumnName = "id", nullable = false)
    private DroneModel model;

    @Column(nullable = false)
    private Integer weightLimit;

    @Column(nullable=false)
    private Integer batteryCapacity;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false)
    private DroneState state;
}
