package com.dancoghlan.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class Todo {
    private int id;
    private String userName;
    private String description;
    private Date targetDate;
    private boolean isDone;

}