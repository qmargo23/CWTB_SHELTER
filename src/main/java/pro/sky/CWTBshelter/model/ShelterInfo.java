package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * класс ShelterInfo - сущность описывающая питомник.
 * Содержит пустой конструктор, геттеры, сеттеры,
 * переопределены equals, hashCode и toString
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
 * transportationAdvice         - советы по транспортировке животных
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
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shelter_info")
public class ShelterInfo {
    @EqualsAndHashCode.Include
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
}
