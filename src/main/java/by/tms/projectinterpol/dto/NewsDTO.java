package by.tms.projectinterpol.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class NewsDTO implements Serializable {

    private Long id;
    private String headline;
    private String text;
    private LocalDate publicationDate;
    private List<TagDTO> tags;

}
