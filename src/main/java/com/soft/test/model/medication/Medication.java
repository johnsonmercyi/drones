package com.soft.test.model.medication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.soft.test.model.EntityWithUUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="medications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medication extends EntityWithUUID {
    
    @Column(nullable = false)
    private String name;

    @Column(nullable=false)
    private Integer weight;

    @Column(nullable = false)
    private String code;

    @Column(columnDefinition = "VARCHAR(1000)") //stores image link
    private String image;
}
