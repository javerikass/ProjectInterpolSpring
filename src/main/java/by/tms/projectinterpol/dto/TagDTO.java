package by.tms.projectinterpol.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class TagDTO implements Serializable {

    private Long id;
    private String tag;
    @ToString.Exclude
    private List<NewsDTO> news;
}
