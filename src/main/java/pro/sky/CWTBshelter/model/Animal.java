package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Сущность описывающая питомца.
 * <i>содержит следующие поля:</i><br>
 * <br>
 * - id             (идентификатор питомца);<br>
 * - typeAnimal     (тип животного, кошка или собака)<br>
 * - breed          (порода если нет, то указывается без породы)<br>
 * - inShelter      (принимает значение {@code true} если животное находится в приюте, иначе {@code false})<br>
 * - health         (принимает значение {@code true} если животное здорово, иначе {@code false})<br>
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "animal")
public class Animal {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeAnimal;
    private String breed;
    private Boolean inShelter;
    private Boolean health;

    public Animal(Long id, String typeAnimal, String breed, Boolean inShelter, Boolean health) {
        this.id = id;
        setTypeAnimal(typeAnimal);
        setBreed(breed);
        this.inShelter = inShelter;
        this.health = health;
    }

    public void setTypeAnimal(String typeAnimal) {
        //значения в нижнем регистре
        this.typeAnimal = typeAnimal.toLowerCase();
    }

    public void setBreed(String breed) {
        //значения в нижнем регистре
        this.breed = breed.toLowerCase();
    }
}