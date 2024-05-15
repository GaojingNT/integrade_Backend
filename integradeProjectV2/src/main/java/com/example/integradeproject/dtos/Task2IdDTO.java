package com.example.integradeproject.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class Task2IdDTO {
        private String title;
        private String description;
        private String assignees;
        private String stautus ;
        private Timestamp createdOn;
        private Timestamp updatedOn;
}
