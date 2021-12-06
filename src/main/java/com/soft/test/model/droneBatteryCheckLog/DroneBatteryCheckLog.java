package com.soft.test.model.droneBatteryCheckLog;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.soft.test.model.EntityWithUUID;
import com.soft.test.model.drone.Drone;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="battery_check_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneBatteryCheckLog extends EntityWithUUID {
    
    @ManyToOne
    @JoinColumn(name="drone_id", nullable=false)
    private Drone drone;

    @Column(nullable=false)
    private Integer batteryCapacity;

    @CreationTimestamp
    private LocalDateTime checkedTime;
}
