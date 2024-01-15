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
@Table(name = "shelter_user")
public class ShelterUser {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "adopt_date")
    private LocalDate adoptDate;

    @ToString.Exclude
    @OneToOne(mappedBy = "shelterUser", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    })
    private TelegramUser telegramUser;

    @Column(name = "type")
    private ShelterUserType type;

    public ShelterUser(
            Long id,
            String name,
            String surname,
            String phoneNumber,
            LocalDate adoptDate,
            TelegramUser telegramUser,
            ShelterUserType type
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.adoptDate = adoptDate;
        setTelegramUser(telegramUser);
        this.type = type;
    }

    public void setTelegramUser(TelegramUser telegramUser) {
        TelegramUser oldTelegramUser = this.telegramUser;
        if (oldTelegramUser == telegramUser) {
            return;
        }
        this.telegramUser = telegramUser;
        if (oldTelegramUser != null) {
            oldTelegramUser.setShelterUser(null);
        }
        if (telegramUser != null) {
            telegramUser.setShelterUser(this);
        }
    }
}
