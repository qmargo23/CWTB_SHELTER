package pro.sky.CWTBshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.model.BotState;
import pro.sky.CWTBshelter.model.TelegramUser;
import pro.sky.CWTBshelter.repository.TelegramUserRepository;

import java.util.Optional;

@Service
public class TelegramUserService {
    private final TelegramUserRepository repository;

    public TelegramUserService(TelegramUserRepository repository) {
        this.repository = repository;
    }

    public TelegramUser findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public TelegramUser findByChatId(Long chatId) {
        return repository.findByChatId(chatId).orElse(null);
    }

    public TelegramUser add(TelegramUser telegramUser) {
        telegramUser.setId(null);
        return repository.save(telegramUser);
    }

    public TelegramUser add(Long chatId, BotState botState) {
        return repository.save(new TelegramUser(null, chatId, botState));
    }

    public TelegramUser update(TelegramUser telegramUser) {
        return repository.save(telegramUser);
    }

    public void remove(TelegramUser telegramUser) {
        repository.delete(telegramUser);
    }
}
