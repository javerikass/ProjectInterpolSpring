package by.tms.projectinterpol.dto;

import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Status;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class RequestsDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String nationality;
    private String details;
    private int reward;
    private Status status;
    private boolean approved;
    private UserDTO user;
}
