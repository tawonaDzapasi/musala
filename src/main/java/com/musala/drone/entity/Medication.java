package com.musala.drone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @Pattern(regexp = "[a-zA-Z0-9\\-_]+")
    private String name;
    @Column(nullable = false)
    private Integer weight;
    @Column(nullable = false)
    @Pattern(regexp = "[A-Z0-9_]+")
    public String code;
    @Column(unique = false, nullable = true)
    private byte[] image;
    @ManyToMany(mappedBy = "medications")
    @JsonIgnore
    List<AuditTrail> auditTrails;
}