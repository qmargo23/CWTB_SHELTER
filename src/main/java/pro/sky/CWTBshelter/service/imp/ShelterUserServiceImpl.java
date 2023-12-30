package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.dto.ShelterUserDTO;
import pro.sky.CWTBshelter.dto.mapper.ShelterUserDTOMapper;
import pro.sky.CWTBshelter.exception.*;
import pro.sky.CWTBshelter.model.Animal;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.model.TelegramUser;
import pro.sky.CWTBshelter.repository.AnimalRepository;
import pro.sky.CWTBshelter.repository.ShelterUserRepository;
import pro.sky.CWTBshelter.repository.TelegramUserRepository;
import pro.sky.CWTBshelter.service.ShelterUserService;

import java.util.List;

@Service
public class ShelterUserServiceImpl implements ShelterUserService {
    private final ShelterUserRepository shelterUserRepository;
    private final TelegramUserRepository telegramUserRepository;
    private final AnimalRepository animalRepository;
    private final ShelterUserDTOMapper shelterUserDTOMapper;

    public ShelterUserServiceImpl(
            ShelterUserRepository shelterUserRepository,
            TelegramUserRepository telegramUserRepository,
            AnimalRepository animalRepository,
            ShelterUserDTOMapper shelterUserDTOMapper
    ) {
        this.shelterUserRepository = shelterUserRepository;
        this.telegramUserRepository = telegramUserRepository;
        this.animalRepository = animalRepository;
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
        if (request.getAnimal() != null) {
            setShelterUserAnimalById(shelterUser, request.getAnimal());
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
        if (request.getAnimal() != null) {
            setShelterUserAnimalById(shelterUser, request.getAnimal());
        } else {
            shelterUser.setAnimal(null);
        }
        return shelterUserRepository.save(shelterUser);
    }

    @Override
    public void removeById(Long id) {
        ShelterUser shelterUser = shelterUserRepository.findById(id).orElseThrow(ShelterUserNotFoundException::new);
        shelterUser.setAnimal(null);
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

    private void setShelterUserAnimalById(ShelterUser shelterUser, Long id) {
        Animal animal = animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);
        if (animal.getShelterUser() != null && !animal.getShelterUser().equals(shelterUser)) {
            throw new BadAnimalIdException();
        }
        shelterUser.setAnimal(animal);
    }
}
