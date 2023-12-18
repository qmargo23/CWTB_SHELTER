package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * класс ShelterInfo - сущность описывающая питомник.
 * Содержит пустой конструктор, геттеры, сеттеры,
 * переопределны equals, hashCode и toString
 * содержит следующие поля:
 * <br>
 * id                           - идентификатор питомника
 * <br>
 * aboutShelter                 - информация о питомнике
 * <br>
 * addressSchedule              - адрес и расписание
 * <br>
 * contactForCarPass            - контакт для получения автомобильного пропуска
 * <br>
 * safetyOnTerritory            - безопасность на территории
 * <br>
 * firstMeetRecommendation      - рекомендация к первому знакомству с питомцем
 * <br>
 * documents                    - документы питомника
 * <br>
 * transportationAdvice         - советы по транспорту
 * <br>
 * houseRulesForSmallAnimal     - правила содержания для детенышей
 * <br>
 * houseRulesForAdultAnimal     - правила содержания для взрослых особей
 * <br>
 * rulesForAnimalWithDisability - правила содержания для питомцев с ограниченными возможностями
 * <br>
 * cynologistAdvice             - советы кинолога
 * <br>
 * cynologists                  - кинологи
 * <br>
 * refuseReasons                - причины по которым может быть отказано в получении питомца
 */
@Entity
@Table(name = "shelter_info")
public class ShelterInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aboutShelter;
    private String addressSchedule;
    private String contactForCarPass;
    private String safetyOnTerritory;
    private String firstMeetRecommendation;
    private String documents;
    private String transportationAdvice;
    private String houseRulesForSmallAnimal;
    @Column(name = "house_rules_for_adult_animal")
    private String houseRulesForAdultAnimal;
    private String rulesForAnimalWithDisability;
    private String cynologistAdvice;
    private String cynologists;
    private String refuseReasons;

    public ShelterInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAboutShelter() {
        return aboutShelter;
    }

    public void setAboutShelter(String aboutShelter) {
        this.aboutShelter = aboutShelter;
    }

    public String getAddressSchedule() {
        return addressSchedule;
    }

    public void setAddressSchedule(String addressSchedule) {
        this.addressSchedule = addressSchedule;
    }

    public String getContactForCarPass() {
        return contactForCarPass;
    }

    public void setContactForCarPass(String contactForCarPass) {
        this.contactForCarPass = contactForCarPass;
    }

    public String getSafetyOnTerritory() {
        return safetyOnTerritory;
    }

    public void setSafetyOnTerritory(String safetyOnTerritory) {
        this.safetyOnTerritory = safetyOnTerritory;
    }

    public String getFirstMeetRecommendation() {
        return firstMeetRecommendation;
    }

    public void setFirstMeetRecommendation(String firstMeetRecommendation) {
        this.firstMeetRecommendation = firstMeetRecommendation;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getTransportationAdvice() {
        return transportationAdvice;
    }

    public void setTransportationAdvice(String transportationAdvice) {
        this.transportationAdvice = transportationAdvice;
    }

    public String getHouseRulesForSmallAnimal() {
        return houseRulesForSmallAnimal;
    }

    public void setHouseRulesForSmallAnimal(String houseRulesForSmallAnimal) {
        this.houseRulesForSmallAnimal = houseRulesForSmallAnimal;
    }

    public String getHouseRulesForAdultAnimal() {
        return houseRulesForAdultAnimal;
    }

    public void setHouseRulesForAdultAnimal(String houseRulesForAdultAnimal) {
        this.houseRulesForAdultAnimal = houseRulesForAdultAnimal;
    }

    public String getRulesForAnimalWithDisability() {
        return rulesForAnimalWithDisability;
    }

    public void setRulesForAnimalWithDisability(String rulesForAnimalWithDisability) {
        this.rulesForAnimalWithDisability = rulesForAnimalWithDisability;
    }

    public String getCynologistAdvice() {
        return cynologistAdvice;
    }

    public void setCynologistAdvice(String cynologistAdvice) {
        this.cynologistAdvice = cynologistAdvice;
    }

    public String getCynologists() {
        return cynologists;
    }

    public void setCynologists(String cynologists) {
        this.cynologists = cynologists;
    }

    public String getRefuseReasons() {
        return refuseReasons;
    }

    public void setRefuseReasons(String refuseReasons) {
        this.refuseReasons = refuseReasons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterInfo that = (ShelterInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(aboutShelter, that.aboutShelter) && Objects.equals(addressSchedule, that.addressSchedule) && Objects.equals(contactForCarPass, that.contactForCarPass) && Objects.equals(safetyOnTerritory, that.safetyOnTerritory) && Objects.equals(firstMeetRecommendation, that.firstMeetRecommendation) && Objects.equals(documents, that.documents) && Objects.equals(transportationAdvice, that.transportationAdvice) && Objects.equals(houseRulesForSmallAnimal, that.houseRulesForSmallAnimal) && Objects.equals(houseRulesForAdultAnimal, that.houseRulesForAdultAnimal) && Objects.equals(rulesForAnimalWithDisability, that.rulesForAnimalWithDisability) && Objects.equals(cynologistAdvice, that.cynologistAdvice) && Objects.equals(cynologists, that.cynologists) && Objects.equals(refuseReasons, that.refuseReasons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aboutShelter, addressSchedule, contactForCarPass, safetyOnTerritory, firstMeetRecommendation, documents, transportationAdvice, houseRulesForSmallAnimal, houseRulesForAdultAnimal, rulesForAnimalWithDisability, cynologistAdvice, cynologists, refuseReasons);
    }

    @Override
    public String   toString() {
        return "ShelterInfo{" +
                "id=" + id +
                ", aboutShelter='" + aboutShelter + '\'' +
                ", addressSchedule='" + addressSchedule + '\'' +
                ", contactForCarPass='" + contactForCarPass + '\'' +
                ", safetyOnTerritory='" + safetyOnTerritory + '\'' +
                ", firstMeetRecommendation='" + firstMeetRecommendation + '\'' +
                ", documents='" + documents + '\'' +
                ", transportationAdvice='" + transportationAdvice + '\'' +
                ", houseRulesForSmallAnimal='" + houseRulesForSmallAnimal + '\'' +
                ", houseRulesForAdultAnimal='" + houseRulesForAdultAnimal + '\'' +
                ", rulesForAnimalWithDisability='" + rulesForAnimalWithDisability + '\'' +
                ", cynologistAdvice='" + cynologistAdvice + '\'' +
                ", cynologists='" + cynologists + '\'' +
                ", refuseReasons='" + refuseReasons + '\'' +
                '}';
    }
}
