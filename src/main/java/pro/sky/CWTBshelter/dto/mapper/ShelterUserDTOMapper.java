package pro.sky.CWTBshelter.dto.mapper;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.CWTBshelter.dto.ShelterUserDTO;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.model.TelegramUser;

@NoArgsConstructor
@Component
public class ShelterUserDTOMapper {
    public ShelterUserDTO.Response.Detail toDetailDTO(ShelterUser shelterUser) {
        if (shelterUser == null) {
            return null;
        }
        TelegramUser telegramUser = shelterUser.getTelegramUser();
        return new ShelterUserDTO.Response.Detail(
                shelterUser.getId(),
                shelterUser.getName(),
                shelterUser.getSurname(),
                shelterUser.getPhoneNumber(),
                shelterUser.getAdoptDate(),
                (telegramUser != null) ? telegramUser.getId() : null,
                shelterUser.getType()
        );
    }

    public ShelterUserDTO.Response.Item toItemDTO(ShelterUser shelterUser) {
        if (shelterUser == null) {
            return null;
        }
        return new ShelterUserDTO.Response.Item(
                shelterUser.getId(),
                shelterUser.getName(),
                shelterUser.getSurname(),
                shelterUser.getPhoneNumber()
        );
    }

    public ShelterUser toShelterUser(ShelterUserDTO.Request.Create dto) {
        if (dto == null) {
            return null;
        }
        return new ShelterUser(
                null,
                dto.getName(),
                dto.getSurname(),
                dto.getPhoneNumber(),
                dto.getAdoptDate(),
                null,
                dto.getType()
        );
    }
}
