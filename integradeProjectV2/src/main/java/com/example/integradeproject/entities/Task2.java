package com.example.integradeproject.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Data
@Table(name = "tasks")


public class Task2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId")
    private Integer id;
    private String title ;
    private String description ;
    private String assignees ;

    @ManyToOne
    @JoinColumn(name ="statusId" , referencedColumnName = "statusId")
    private Status  status ;

    @Column(name = "createdOn", updatable = false, insertable = false)
    private Date createdOn;
    @Column(name = "updatedOn", updatable = false, insertable = false)
    private Date updatedOn;

    public Task2() {

        this.status = new Status( 1, "NO STATUS" , null);
    }
}

