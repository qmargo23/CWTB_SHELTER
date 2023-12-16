package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * класс Animal - сущность описывающая питомца.
 * Содержит пустой конструктор, геттеры, сеттеры,
 * переопределны equals, hashCode и toString
 * содержит следующие поля:
 * id           - идентификатор питомца;
 * typeAnimal   - тип животного (кошка или собака)
 * breed        - порода (если нет, то указывается без породы)
 * inShelter    - принимает значение true если животное находится в приюте, иначе false
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

    public Animal() {
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
        this.typeAnimal = typeAnimal;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Boolean getInShelter() {
        return inShelter;
    }

    public void setInShelter(Boolean inShelter) {
        this.inShelter = inShelter;
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