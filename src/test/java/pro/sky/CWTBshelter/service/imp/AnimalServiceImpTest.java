package pro.sky.CWTBshelter.service.imp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.CWTBshelter.dto.AnimalDTO;
import pro.sky.CWTBshelter.dto.mapper.AnimalDTOMapper;
import pro.sky.CWTBshelter.exception.AnimalNotFoundException;
import pro.sky.CWTBshelter.model.Animal;
import pro.sky.CWTBshelter.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImpTest {
    @Mock
    private AnimalRepository animalRepository;

    @Spy
    private AnimalDTOMapper animalDTOMapper;

    @InjectMocks
    private AnimalServiceImp animalService;

    Animal animal = new Animal(1L, "cat", "британец", true, true);
    AnimalDTO.Request.Create request = new AnimalDTO.Request.Create("cat", "британец", true, true);

    public static final List<Animal> ANIMAL_LIST = List.of(
            new Animal(2L, "cat", "британец2", true, true),
            new Animal(3L, "cat", "британец3", true, true),
            new Animal(4L, "cat", "британец4", true, true)
    );


    @Test
    @DisplayName("Создание животного")
    void shouldReturnAnimalWhenCreateUserCalled() {
        when(animalService.createAnimal(request)).thenReturn(animal);

        assertEquals(animalService.createAnimal(request), animal);

    }


    @Test
    @DisplayName("Редактирование животного")
    void shouldReturnAnimalWhenEditAnimalCalled() {

        when(animalRepository.findById(anyLong())).thenReturn(Optional.of(animal));
        when(animalRepository.save(any())).thenReturn(animal);

        Animal animal1 = animalService.editAnimal(1L, request);

        Assertions.assertThat(animal1).isEqualTo(animal);
    }

    @Test
    @DisplayName("Удаление животного по его id")
    void shouldReturnTrueWhenDeleteAnimalByIdCalled() {

        when(animalRepository.existsById(anyLong())).thenReturn(true);

        assertTrue(animalService.deleteAnimalById(anyLong()));
    }

    @Test
    @DisplayName("Ошибка при удалении животного")
    void shouldReturnExceptionWhenDeleteAnimalByIdCalled() {
        when(animalRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(AnimalNotFoundException.class, () -> animalService.deleteAnimalById(anyLong()));
    }

    @Test
    @DisplayName("Ошибка при редактировании животного")
    void shouldReturnExceptionWhenEditAnimalCalled() {
        when(animalRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(AnimalNotFoundException.class, () -> animalService.editAnimal(1L, request));
    }

    @Test
    @DisplayName("Ошибка при поиске животного по id")
    void shouldReturnExceptionWhenFindByIdCalled() {
        when(animalRepository.findById(anyLong())).thenThrow(AnimalNotFoundException.class);

        assertThrows(AnimalNotFoundException.class, () -> animalService.findAnimalById(anyLong()));
    }

    @Test
    @DisplayName("Получить всех животных")
    void shouldReturnCollectionOfAnimalWhenFindAllUserCalled() {
        when(animalRepository.findAll())
                .thenReturn(ANIMAL_LIST);

        assertIterableEquals(ANIMAL_LIST, animalService.getAllAnimals());
    }
}