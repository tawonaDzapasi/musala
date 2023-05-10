package com.musala.drone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.musala.drone.enums.Model;
import com.musala.drone.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drone")
public class Drone {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @Length(max = 100)
    private String serialNumber;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Model model;
    @Column(nullable = false)
    @Max(500)
    private Integer weightLimit;
    @Column(nullable = false)
    private Integer batteryCapacity;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private State state=State.IDLE;
    @OneToMany(mappedBy = "drone")
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<AuditTrail> auditTrails;


}
