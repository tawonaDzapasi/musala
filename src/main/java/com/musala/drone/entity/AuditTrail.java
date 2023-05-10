package com.musala.drone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="audit")
public class AuditTrail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,name = "location")
    private String locationName;
    @ManyToOne
    @JsonBackReference
    private Drone drone;
    @ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "audit_medication",
            joinColumns = @JoinColumn(name = "audit_trail_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private List<Medication> medications;

}
