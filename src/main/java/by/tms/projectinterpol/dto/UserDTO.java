package by.tms.projectinterpol.dto;

import by.tms.projectinterpol.entity.Role;
import lombok.*;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@SessionScope
public class UserDTO implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Role role;
    private List<RequestsDTO> requests;
}
