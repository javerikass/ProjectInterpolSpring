package by.tms.projectinterpol.entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(schema = "interpol_storage")
public class Requests extends BaseEntity<Long> {


    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private Integer age;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(length = 1000)
    private String details;
    private Integer reward;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private boolean approved;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User users;

}
