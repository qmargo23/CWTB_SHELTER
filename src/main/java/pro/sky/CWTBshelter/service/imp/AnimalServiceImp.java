package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.dto.AnimalDTO;
import pro.sky.CWTBshelter.dto.mapper.AnimalDTOMapper;
import pro.sky.CWTBshelter.exception.AnimalNotFoundException;
import pro.sky.CWTBshelter.exception.BadShelterUserIdException;
import pro.sky.CWTBshelter.exception.ShelterUserNotFoundException;
import pro.sky.CWTBshelter.model.Animal;
import pro.sky.CWTBshelter.model.ShelterUser;
import pro.sky.CWTBshelter.repository.AnimalRepository;
import pro.sky.CWTBshelter.repository.ShelterUserRepository;
import pro.sky.CWTBshelter.service.AnimalService;

import java.util.List;

@Service
public class AnimalServiceImp implements AnimalService {
    private final AnimalRepository animalRepository;
    private final ShelterUserRepository shelterUserRepository;
    private final AnimalDTOMapper animalDTOMapper;

    public AnimalServiceImp(AnimalRepository animalRepository, ShelterUserRepository shelterUserRepository, AnimalDTOMapper animalDTOMapper) {
        this.animalRepository = animalRepository;
        this.shelterUserRepository = shelterUserRepository;
        this.animalDTOMapper = animalDTOMapper;
    }

    //CREATE
    @Override
    public Animal createAnimal(AnimalDTO.Request.Create request) {
        Animal animal = animalDTOMapper.toAnimal(request);
        animal = animalRepository.save(animal);
        if (request.getShelterUser() != null) {
            ShelterUser shelterUser = setAnimalShelterUserById(animal, request.getShelterUser());
            shelterUserRepository.save(shelterUser);
        }
        return animal;
    }

    //READ
    @Override
    public Animal findAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);
    }

    //UPDATE
    @Override
    public Animal editAnimal(Long id, AnimalDTO.Request.Create request) {
        Animal animal = animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);
        animal.setTypeAnimal(request.getTypeAnimal());
        animal.setBreed(request.getBreed());
        animal.setHealth(request.getHealth());
        animal.setInShelter(request.getInShelter());
        animalRepository.save(animal);
        if (request.getShelterUser() != null) {
            ShelterUser shelterUser = setAnimalShelterUserById(animal, request.getShelterUser());
            shelterUserRepository.save(shelterUser);
        } else {
            animal.setShelterUser(null);
        }
        return animal;
    }
    //DELETE
    @Override
    public boolean deleteAnimalById(Long id) {
        Animal animal = animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);
        animal.setShelterUser(null);
        animalRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    private ShelterUser setAnimalShelterUserById(Animal animal, Long id) {
        ShelterUser shelterUser = shelterUserRepository.findById(id).orElseThrow(ShelterUserNotFoundException::new);
        if (shelterUser.getAnimal() != null && !shelterUser.getAnimal().equals(animal)) {
            throw new BadShelterUserIdException();
        }
        animal.setShelterUser(shelterUser);
        return shelterUser;
    }
}
