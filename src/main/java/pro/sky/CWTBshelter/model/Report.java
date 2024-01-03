package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report")
public class Report {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "photo")
    private String photo;
    @Column(name = "local_date")
    private LocalDate localDate;
    @Column(name = "report_text_under_photo")
    private String reportTextUnderPhoto;
}
