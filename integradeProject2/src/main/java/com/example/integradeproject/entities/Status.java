package com.example.integradeproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId ;
    private String statusName;
    private String statusDescription;
    public void setName(String name) {
        this.statusName = name == null ? null : name.trim();
    }

    public void setDescription(String description) {
        this.statusDescription = description == null ? null : description.trim();
    }


}
