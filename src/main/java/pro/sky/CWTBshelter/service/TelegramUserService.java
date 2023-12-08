package pro.sky.CWTBshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.model.BotState;
import pro.sky.CWTBshelter.model.TelegramUser;
import pro.sky.CWTBshelter.repository.TelegramUserRepository;

@Service
public class TelegramUserService {
    private final TelegramUserRepository repository;

    public TelegramUserService(TelegramUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns the telegram user by id (the id is not the one in the telegram) if it exists otherwise {@code null}
     *
     * @param id {@link TelegramUser} id
     * @return the found {@link TelegramUser} or {@code null}
     */
    public TelegramUser findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Returns the telegram user by chat id if it exists otherwise {@code null}
     *
     * @param chatId telegram chat id
     * @return the found {@link TelegramUser} or {@code null}
     */
    public TelegramUser findByChatId(Long chatId) {
        return repository.findByChatId(chatId).orElse(null);
    }

    /**
     * Adds a telegram user to the database
     *
     * @param telegramUser the telegram user to add
     * @return the added {@link TelegramUser}
     */
    public TelegramUser add(TelegramUser telegramUser) {
        telegramUser.setId(null);
        return repository.save(telegramUser);
    }

    /**
     * Adds a telegram user to the database by chat id and bot state
     *
     * @param chatId   the telegram chat id
     * @param botState the bot state
     * @return the added {@link TelegramUser}
     */
    public TelegramUser add(Long chatId, BotState botState) {
        return repository.save(new TelegramUser(null, chatId, botState));
    }

    /**
     * Changes telegram user information
     *
     * @param telegramUser changed telegram user information
     * @return changed {@link TelegramUser}
     */
    public TelegramUser update(TelegramUser telegramUser) {
        return repository.save(telegramUser);
    }

    /**
     * Deletes the transferred telegram user from the database
     *
     * @param telegramUser telegram user to delete
     */
    public void remove(TelegramUser telegramUser) {
        repository.delete(telegramUser);
    }
}
