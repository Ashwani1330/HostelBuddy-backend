package org.example.hostel_auth.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorsResponse {
    private String message;
}