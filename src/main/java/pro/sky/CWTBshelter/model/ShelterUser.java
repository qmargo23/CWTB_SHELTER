package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "shelter_user")
public class ShelterUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "adopt_date")
    private LocalDate adoptDate;

    @OneToOne(mappedBy = "shelterUser", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    })
    private TelegramUser telegramUser;

    @OneToOne(mappedBy = "shelterUser", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    })
    private Animal animal;

    @Column(name = "type")
    private ShelterUserType type;

    public ShelterUser() {
    }

    public ShelterUser(Long id, String name, String surname, String phoneNumber, LocalDate adoptDate, TelegramUser telegramUser, Animal animal, ShelterUserType type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.adoptDate = adoptDate;
        this.telegramUser = telegramUser;
        this.animal = animal;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getAdoptDate() {
        return adoptDate;
    }

    public void setAdoptDate(LocalDate adoptDate) {
        this.adoptDate = adoptDate;
    }

    public TelegramUser getTelegramUser() {
        return telegramUser;
    }

    public void setTelegramUser(TelegramUser telegramUser) {
        TelegramUser oldTelegramUser = this.telegramUser;
        if (oldTelegramUser == telegramUser) {
            return;
        }
        this.telegramUser = telegramUser;
        if (oldTelegramUser != null) {
            oldTelegramUser.setShelterUser(null);
        }
        if (telegramUser != null) {
            telegramUser.setShelterUser(this);
        }
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        Animal oldAnimal = this.animal;
        if (oldAnimal == animal) {
            return;
        }
        this.animal = animal;
        if (oldAnimal != null) {
            oldAnimal.setShelterUser(null);
        }
        if (animal != null) {
            animal.setShelterUser(this);
        }
    }

    public ShelterUserType getType() {
        return type;
    }

    public void setType(ShelterUserType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterUser that = (ShelterUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ShelterUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
