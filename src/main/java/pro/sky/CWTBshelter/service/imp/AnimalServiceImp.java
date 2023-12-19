package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.exception.AnimalNotFoundException;
import pro.sky.CWTBshelter.model.Animal;
import pro.sky.CWTBshelter.repository.AnimalRepository;
import pro.sky.CWTBshelter.service.AnimalService;

import java.util.List;

@Service
public class AnimalServiceImp implements AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalServiceImp(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }
    //CREATE
    @Override
    public Animal createAnimal(Animal animal) {
        animalRepository.save(animal);
        return animal;
    }
    //READ
    @Override
    public Animal findAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);
    }
    //UPDATE
    @Override
    public Animal editAnimal(Animal animal) {
        if (animalRepository.findById(animal.getId()).isPresent()) {
            animalRepository.save(animal);
            return animal;
        } else {
            throw new AnimalNotFoundException();
        }
    }

    @Override
    public boolean deleteAnimalById(Long id) {
        if (animalRepository.findById(id).isPresent()) {
            animalRepository.deleteById(id);
            return true;
        } else {
            throw new AnimalNotFoundException();
        }
    }

    //вывод всех питомцев приюта
    //возможно в дальнейшем нужна будет сортирока по typeAnimal
    //
    @Override
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

}
