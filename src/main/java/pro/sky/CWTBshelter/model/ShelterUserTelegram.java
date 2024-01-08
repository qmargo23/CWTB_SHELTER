package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "shelter_user_telegram")
public class ShelterUserTelegram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private BotState botState;
    private String userName;
    private String userSurname;
    private String userPhoneNumber;
    private LocalDate adoptDate;
    private ShelterUserType shelterUserType;
    @OneToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}