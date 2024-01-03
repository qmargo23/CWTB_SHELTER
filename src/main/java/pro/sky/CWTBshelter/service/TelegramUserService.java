package pro.sky.CWTBshelter.service;

import pro.sky.CWTBshelter.dto.TelegramUserDTO;
import pro.sky.CWTBshelter.model.TelegramUser;

import java.util.List;

public interface TelegramUserService {
    TelegramUser getTelegramUserById(Long id);

    TelegramUser getTelegramUserByChatId(Long id);

    List<TelegramUser> getAllTelegramUsers();

    TelegramUser createTelegramUser(TelegramUserDTO.Request.Create request);

    TelegramUser updateTelegramUser(Long id, TelegramUserDTO.Request.Create request);

    void deleteTelegramUserById(Long id);
}
