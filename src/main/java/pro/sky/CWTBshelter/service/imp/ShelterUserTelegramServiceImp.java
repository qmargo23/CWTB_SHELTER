package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.exceptions.ShelterUserTelegramNotFoundException;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;
import pro.sky.CWTBshelter.repository.ShelterUserTelegramRepository;
import pro.sky.CWTBshelter.service.ShelterUserTelegramService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ShelterUserTelegramServiceImp implements ShelterUserTelegramService {
    //regex шаблон для номера телефона
    String regex = "\\+7-9\\d{2}-\\d{3}-\\d{2}-\\d{2}";

    private final ShelterUserTelegramRepository repository;

    public ShelterUserTelegramServiceImp(ShelterUserTelegramRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShelterUserTelegram findById(Long id) {
        return repository.findById(id).orElseThrow(ShelterUserTelegramNotFoundException::new);
    }

    @Override
    public List<ShelterUserTelegram> findAll() {
        return repository.findAll();
    }

    @Override
    public ShelterUserTelegram add(ShelterUserTelegram shelterUserTelegram) {
        return repository.save(shelterUserTelegram);
    }

    @Override
    public ShelterUserTelegram update(ShelterUserTelegram shelterUserTelegram) {
        if (repository.existsById(shelterUserTelegram.getId())) {
            return repository.save(shelterUserTelegram);
        }
        throw new ShelterUserTelegramNotFoundException();
    }

    @Override
    public boolean removeById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new ShelterUserTelegramNotFoundException();
    }

    @Override
    public boolean setPhoneNumber(Long id, String phoneNumber) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            ShelterUserTelegram shelterUser = repository.findById(id).orElse(null);
            if (shelterUser != null) {
                shelterUser.setUserPhoneNumber(phoneNumber);
                repository.save(shelterUser);
                return true;
            }
        }
        return false;
    }
}

