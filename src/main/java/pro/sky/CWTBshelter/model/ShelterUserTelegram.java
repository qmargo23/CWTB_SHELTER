package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 *класс ShelterUserTelegram - сущность описывающая пользователя телеграм-бота.
 * Содержит геттеры, сеттеры, переопределены equals,
 * hashCode и toString
 * содержит следующие поля:
 * <br>
 * id - идентификатор пользователя телеграм-бота
 * <br>
 * chatId - идентификатор чата
 * <br>
 * botState - состояние телеграмм бота
 * <br>
 * userName - имя пользователя
 * <br>
 * userSurname - фамилия пользователя
 * <br>
 * userPhoneNumber - номер телефона пользователя
 * <br>
 * adoptDate - дата принятия
 * <br>
 * shelterUserType - тип пользователя бота
 * animal - животное(имеет зависимость один к одному с {@link Animal})
 */
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
