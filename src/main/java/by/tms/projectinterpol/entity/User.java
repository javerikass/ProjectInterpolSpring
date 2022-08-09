package by.tms.projectinterpol.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity(name = "users")
@Table(schema = "interpol_storage")
public class User extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @OneToMany(mappedBy = "users")
    @ToString.Exclude
    private List<Requests> requests = new ArrayList<>();

}
