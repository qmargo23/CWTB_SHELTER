package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.exceptions.ShelterUserTelegramNotFoundException;
import pro.sky.CWTBshelter.model.ShelterUserTelegram;
import pro.sky.CWTBshelter.repository.ShelterUserTelegramRepository;
import pro.sky.CWTBshelter.service.ShelterUserTelegramService;

import java.util.List;

@Service
public class ShelterUserTelegramServiceImp implements ShelterUserTelegramService {
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
}

