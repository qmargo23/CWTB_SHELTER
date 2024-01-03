package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.dto.TelegramUserDTO;
import pro.sky.CWTBshelter.dto.mapper.TelegramUserDTOMapper;
import pro.sky.CWTBshelter.exception.BadShelterUserIdException;
import pro.sky.CWTBshelter.exception.ShelterUserNotFoundException;
import pro.sky.CWTBshelter.exception.TelegramUserNotFound;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.model.TelegramUser;
import pro.sky.CWTBshelter.repository.ShelterUserRepository;
import pro.sky.CWTBshelter.repository.TelegramUserRepository;
import pro.sky.CWTBshelter.service.TelegramUserService;

import java.util.List;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;
    private final ShelterUserRepository shelterUserRepository;
    private final TelegramUserDTOMapper telegramUserDTOMapper;

    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository, ShelterUserRepository shelterUserRepository, TelegramUserDTOMapper telegramUserDTOMapper) {
        this.telegramUserRepository = telegramUserRepository;
        this.shelterUserRepository = shelterUserRepository;
        this.telegramUserDTOMapper = telegramUserDTOMapper;
    }

    @Override
    public TelegramUser getTelegramUserById(Long id) {
        return telegramUserRepository.findById(id).orElseThrow(TelegramUserNotFound::new);
    }

    @Override
    public TelegramUser getTelegramUserByChatId(Long id) {
        return telegramUserRepository.findByChatId(id).orElseThrow(TelegramUserNotFound::new);
    }

    @Override
    public List<TelegramUser> getAllTelegramUsers() {
        return telegramUserRepository.findAll();
    }

    @Override
    public TelegramUser createTelegramUser(TelegramUserDTO.Request.Create request) {
        TelegramUser telegramUser = telegramUserDTOMapper.toTelegramUser(request);
        telegramUser = telegramUserRepository.save(telegramUser);
        if (request.getShelterUser() != null) {
            ShelterUser shelterUser = setTelegramUserShelterUserById(telegramUser, request.getShelterUser());
            shelterUserRepository.save(shelterUser);
        }
        return telegramUser;
    }

    @Override
    public TelegramUser updateTelegramUser(Long id, TelegramUserDTO.Request.Create request) {
        TelegramUser telegramUser = telegramUserRepository.findById(id).orElseThrow(TelegramUserNotFound::new);
        telegramUser.setBotState(request.getBotState());
        telegramUser.setChatId(request.getChatId());
        telegramUserRepository.save(telegramUser);
        if (request.getShelterUser() != null) {
            ShelterUser shelterUser = setTelegramUserShelterUserById(telegramUser, request.getShelterUser());
            shelterUserRepository.save(shelterUser);
        } else {
            telegramUser.setShelterUser(null);
        }
        return telegramUser;
    }

    @Override
    public void deleteTelegramUserById(Long id) {
        TelegramUser telegramUser = telegramUserRepository.findById(id).orElseThrow(TelegramUserNotFound::new);
        telegramUser.setShelterUser(null);
        telegramUserRepository.deleteById(id);
    }

    private ShelterUser setTelegramUserShelterUserById(TelegramUser telegramUser, Long id) {
        ShelterUser shelterUser = shelterUserRepository.findById(id).orElseThrow(ShelterUserNotFoundException::new);
        if (shelterUser.getTelegramUser() != null && !shelterUser.getTelegramUser().equals(telegramUser)) {
            throw new BadShelterUserIdException();
        }
        telegramUser.setShelterUser(shelterUser);
        return shelterUser;
    }
}
