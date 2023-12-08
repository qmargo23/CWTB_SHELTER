package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "telegram_user")
public class TelegramUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "bot_state")
    private BotState botState;

    public TelegramUser() {
    }

    public TelegramUser(Long id, Long chatId, BotState botState) {
        this.id = id;
        this.chatId = chatId;
        this.botState = botState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public BotState getBotState() {
        return botState;
    }

    public void setBotState(BotState botState) {
        this.botState = botState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelegramUser that = (TelegramUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TelegramUser{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", botState=" + botState +
                '}';
    }
}
