package com.project.To_Do_ListApp.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TODO")
public class ToDo {
    @Id @GeneratedValue
    private Integer id;

    @Column(name="TITLE")
    private String title;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="DUEDATE")
    private Date dueDate;

    @Column(name="COMPLETED")
    private Boolean completed;
}