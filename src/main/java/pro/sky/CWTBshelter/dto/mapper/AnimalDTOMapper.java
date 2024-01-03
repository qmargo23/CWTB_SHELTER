package pro.sky.CWTBshelter.dto.mapper;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.CWTBshelter.dto.AnimalDTO;
import pro.sky.CWTBshelter.model.Animal;

@NoArgsConstructor
@Component
public class AnimalDTOMapper {
    public AnimalDTO.Response.Detail toDetailDTO(Animal animal) {
        if (animal == null) {
            return null;
        }
        return new AnimalDTO.Response.Detail(
                animal.getId(),
                animal.getTypeAnimal(),
                animal.getBreed(),
                animal.getInShelter(),
                animal.getHealth()
        );
    }

    public AnimalDTO.Response.Item toItemDTO(Animal animal) {
        if (animal == null) {
            return null;
        }
        return new AnimalDTO.Response.Item(
                animal.getId(),
                animal.getTypeAnimal(),
                animal.getBreed()
        );
    }

    public Animal toAnimal(AnimalDTO.Request.Create dto) {
        if (dto == null) {
            return null;
        }
        return new Animal(
                null,
                dto.getTypeAnimal(),
                dto.getBreed(),
                dto.getInShelter(),
                dto.getHealth()
        );
    }
}
