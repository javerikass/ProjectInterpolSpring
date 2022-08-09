package by.tms.projectinterpol.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
public class News extends BaseEntity<Long> {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_seq_generator")
//    @SequenceGenerator(
//            name = "news_id_seq_generator",
//            schema = "interpol_storage",
//            sequenceName = "news_id_seq",
//            allocationSize = 1
//    )
//    private Long id;

    @Column(name = "news_text", length = 10000,nullable = false, unique = true)
    private String text;
    @Column(nullable = false,unique = true)
    private String headline;
    @Column(name = "publication_date",nullable = false)
    private LocalDate publicationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "news_tags",
            schema = "interpol_storage",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private List<Tag> tags = new ArrayList<>();

}
