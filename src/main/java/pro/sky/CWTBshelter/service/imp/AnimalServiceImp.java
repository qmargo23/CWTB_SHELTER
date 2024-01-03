package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.dto.AnimalDTO;
import pro.sky.CWTBshelter.dto.mapper.AnimalDTOMapper;
import pro.sky.CWTBshelter.exception.AnimalNotFoundException;
import pro.sky.CWTBshelter.model.Animal;
import pro.sky.CWTBshelter.repository.AnimalRepository;
import pro.sky.CWTBshelter.service.AnimalService;

import java.util.List;

@Service
public class AnimalServiceImp implements AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalDTOMapper animalDTOMapper;

    public AnimalServiceImp(AnimalRepository animalRepository, AnimalDTOMapper animalDTOMapper) {
        this.animalRepository = animalRepository;
        this.animalDTOMapper = animalDTOMapper;
    }

    //CREATE
    @Override
    public Animal createAnimal(AnimalDTO.Request.Create request) {
        Animal animal = animalDTOMapper.toAnimal(request);
        return animalRepository.save(animal);
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
        return animalRepository.save(animal);
    }

    //DELETE
    @Override
    public boolean deleteAnimalById(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new AnimalNotFoundException();
        }
        animalRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }
}
