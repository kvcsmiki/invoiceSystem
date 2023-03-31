package com.task.invoice.core.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Roles")
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;

    public Role(String name) {
        this.name = name;
    }
}
