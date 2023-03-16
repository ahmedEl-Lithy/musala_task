package com.musala.task.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorMessageDto {
    private int statusCode;
    private Date timestamp;
    private List<String> message;
    private String description;
}