package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "telegram_user")
public class TelegramUser {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "bot_state")
    private BotState botState;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "shelter_user_id")
    private ShelterUser shelterUser;

    public TelegramUser(Long id, Long chatId, BotState botState, ShelterUser shelterUser) {
        this.id = id;
        this.chatId = chatId;
        this.botState = botState;
        setShelterUser(shelterUser);
    }

    public void setShelterUser(ShelterUser shelterUser) {
        ShelterUser oldShelterUser = this.shelterUser;
        if (oldShelterUser == shelterUser) {
            return;
        }
        this.shelterUser = shelterUser;
        if (oldShelterUser != null) {
            oldShelterUser.setTelegramUser(null);
        }
        if (shelterUser != null) {
            shelterUser.setTelegramUser(this);
        }
    }
}
