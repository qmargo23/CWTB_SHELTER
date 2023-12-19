package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.exception.ShelterUserNotFoundException;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.repository.ShelterUserRepository;
import pro.sky.CWTBshelter.service.ShelterUserService;

import java.util.List;

@Service
public class ShelterUserServiceImpl implements ShelterUserService {
    private final ShelterUserRepository repository;

    public ShelterUserServiceImpl(ShelterUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShelterUser findById(Long id) {
        return repository.findById(id).orElseThrow(ShelterUserNotFoundException::new);
    }

    @Override
    public List<ShelterUser> findAll() {
        return repository.findAll();
    }

    @Override
    public ShelterUser add(ShelterUser shelterUser) {
        shelterUser.setId(null);
        return repository.save(shelterUser);
    }

    @Override
    public ShelterUser update(ShelterUser shelterUser) {
        if (repository.existsById(shelterUser.getId())) {
            return repository.save(shelterUser);
        }
        throw new ShelterUserNotFoundException();
    }

    @Override
    public void removeById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new ShelterUserNotFoundException();
    }
}
