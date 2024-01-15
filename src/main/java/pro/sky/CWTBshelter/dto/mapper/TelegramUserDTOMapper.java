package pro.sky.CWTBshelter.dto.mapper;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.CWTBshelter.dto.TelegramUserDTO;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.model.TelegramUser;

@NoArgsConstructor
@Component
public class TelegramUserDTOMapper {
    public TelegramUserDTO.Response.Detail toDetailDTO(TelegramUser telegramUser) {
        if (telegramUser == null) {
            return null;
        }
        ShelterUser shelterUser = telegramUser.getShelterUser();
        return new TelegramUserDTO.Response.Detail(
                telegramUser.getId(),
                telegramUser.getChatId(),
                telegramUser.getBotState(),
                (shelterUser != null) ? shelterUser.getId() : null
        );
    }

    public TelegramUserDTO.Request.Create toCreateDTO(TelegramUser telegramUser) {
        if (telegramUser == null) {
            return null;
        }
        ShelterUser shelterUser = telegramUser.getShelterUser();
        return new TelegramUserDTO.Request.Create(
                telegramUser.getChatId(),
                telegramUser.getBotState(),
                (shelterUser != null) ? shelterUser.getId() : null
        );
    }

    public TelegramUser toTelegramUser(TelegramUserDTO.Request.Create request) {
        if (request == null) {
            return null;
        }
        return new TelegramUser(
                null,
                request.getChatId(),
                request.getBotState(),
                null
        );
    }
}
