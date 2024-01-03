package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.dto.ShelterUserDTO;
import pro.sky.CWTBshelter.dto.mapper.ShelterUserDTOMapper;
import pro.sky.CWTBshelter.exception.BadTelegramUserIdException;
import pro.sky.CWTBshelter.exception.ShelterUserNotFoundException;
import pro.sky.CWTBshelter.exception.TelegramUserNotFound;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.model.TelegramUser;
import pro.sky.CWTBshelter.repository.ShelterUserRepository;
import pro.sky.CWTBshelter.repository.TelegramUserRepository;
import pro.sky.CWTBshelter.service.ShelterUserService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ShelterUserServiceImpl implements ShelterUserService {
    private final ShelterUserRepository shelterUserRepository;
    private final TelegramUserRepository telegramUserRepository;
    private final ShelterUserDTOMapper shelterUserDTOMapper;

    public ShelterUserServiceImpl(
            ShelterUserRepository shelterUserRepository,
            TelegramUserRepository telegramUserRepository,
            ShelterUserDTOMapper shelterUserDTOMapper
    ) {
        this.shelterUserRepository = shelterUserRepository;
        this.telegramUserRepository = telegramUserRepository;
        this.shelterUserDTOMapper = shelterUserDTOMapper;
    }

    @Override
    public ShelterUser findShelterUserById(Long id) {
        return shelterUserRepository.findById(id).orElseThrow(ShelterUserNotFoundException::new);
    }

    @Override
    public List<ShelterUser> getAllShelterUsers() {
        return shelterUserRepository.findAll();
    }

    @Override
    public ShelterUser createShelterUser(ShelterUserDTO.Request.Create request) {
        ShelterUser shelterUser = shelterUserDTOMapper.toShelterUser(request);
        if (request.getTelegramUser() != null) {
            setShelterUserTelegramUserById(shelterUser, request.getTelegramUser());
        }
        return shelterUserRepository.save(shelterUser);
    }

    @Override
    public ShelterUser updateShelterUser(Long id, ShelterUserDTO.Request.Create request) {
        ShelterUser shelterUser = shelterUserRepository.findById(id).orElseThrow(ShelterUserNotFoundException::new);
        shelterUser.setName(request.getName());
        shelterUser.setSurname(request.getSurname());
        shelterUser.setPhoneNumber(request.getPhoneNumber());
        shelterUser.setAdoptDate(request.getAdoptDate());
        shelterUser.setType(shelterUser.getType());
        if (request.getTelegramUser() != null) {
            setShelterUserTelegramUserById(shelterUser, request.getTelegramUser());
        } else {
            shelterUser.setTelegramUser(null);
        }
        return shelterUserRepository.save(shelterUser);
    }

    @Override
    public void removeById(Long id) {
        ShelterUser shelterUser = shelterUserRepository.findById(id).orElseThrow(ShelterUserNotFoundException::new);
        shelterUser.setTelegramUser(null);
        shelterUserRepository.save(shelterUser);
        shelterUserRepository.deleteById(id);
    }

    private void setShelterUserTelegramUserById(ShelterUser shelterUser, Long id) {
        TelegramUser telegramUser = telegramUserRepository.findById(id).orElseThrow(TelegramUserNotFound::new);
        if (telegramUser.getShelterUser() != null && !telegramUser.getShelterUser().equals(shelterUser)) {
            throw new BadTelegramUserIdException();
        }
        shelterUser.setTelegramUser(telegramUser);
    }

    @Override
    public boolean setPhoneNumber(Long id, String phoneNumber) {
        String regex = "^((\\+7|7|8)+([0-9]){10})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            ShelterUser shelterUser = shelterUserRepository.findById(id).orElse(null);
            if (shelterUser != null) {
                shelterUser.setPhoneNumber(phoneNumber);
                shelterUserRepository.save(shelterUser);
                return true;
            }
        }
        return false;
    }
}
