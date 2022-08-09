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
@Entity
@Table(schema = "interpol_storage")
public class Tag extends BaseEntity<Long> {

    @Column(nullable = false,unique = true)
    private String tag;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<News> news = new ArrayList<>();
}
