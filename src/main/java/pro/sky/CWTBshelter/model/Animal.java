package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;

import java.util.Objects;

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
@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeAnimal;
    private String breed;
    private Boolean inShelter;
    private Boolean health;


    public Animal() {
    }


        this.id = id;
        this.typeAnimal = typeAnimal;
        this.breed = breed;
        this.inShelter = inShelter;
        this.health = health;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeAnimal() {
        return typeAnimal;
    }

    public void setTypeAnimal(String typeAnimal) {
        //значения в нижнем регистре
        this.typeAnimal = typeAnimal.toLowerCase();
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        //значения в нижнем регистре
        this.breed = breed.toLowerCase();
    }

    public Boolean getInShelter() {
        return inShelter;
    }

    public void setInShelter(Boolean inShelter) {
        this.inShelter = inShelter;
    }

    public Boolean getHealth() {
        return health;
    }

    public void setHealth(Boolean health) {
        this.health = health;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return id == animal.id && Objects.equals(typeAnimal, animal.typeAnimal) && Objects.equals(breed, animal.breed) && Objects.equals(inShelter, animal.inShelter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeAnimal, breed, inShelter);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", typeAnimal='" + typeAnimal + '\'' +
                ", breed='" + breed + '\'' +
                ", inShelter=" + inShelter +
                '}';
    }
}