package com.abidevel.oauth.authorization.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse <T> {
    private boolean isSuccessful;
    private String message;
    private T data;
    private LocalDateTime responseTime;
    private String path;
}
