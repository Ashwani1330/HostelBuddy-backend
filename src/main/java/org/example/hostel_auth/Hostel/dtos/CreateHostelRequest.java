package org.example.hostel_auth.Hostel.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Data
@Setter(AccessLevel.NONE)
public class CreateHostelRequest {
    @NonNull
    private String regNumber;
    @NonNull
    private String hostelType;
    @NonNull
    private String blockName;
    @NonNull
    private int roomNumber;
}
