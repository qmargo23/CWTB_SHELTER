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
@Table(name = "report_telegram")
public class ReportTelegram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "photo")
    private String photo;
    @Column(name = "local_date")
    private LocalDate localDate;
    @Column(name = "report_text_under_photo")
    private String reportTextUnderPhoto;
    @OneToOne
    @JoinColumn(name = "shelter_users_telegram_id")
    private ShelterUserTelegram sheltersUser;
}
