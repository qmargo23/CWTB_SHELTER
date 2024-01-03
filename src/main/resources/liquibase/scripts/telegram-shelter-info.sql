-- liquibase formatted sql

-- changeset mir:1
CREATE TABLE shelter_info
(
    id BIGSERIAL PRIMARY KEY,
    about_shelter varchar,
    address_schedule varchar,
    contact_for_car_pass varchar,
    safety_on_territory varchar,
    first_meet_recommendation varchar,
    documents varchar,
    transportation_advice varchar,
    house_rules_for_small_animal varchar,
    house_rules_for_adult_animal varchar,
    rules_for_animal_with_disability varchar,
    cynologist_advice varchar,
    cynologists varchar,
    refuse_reasons varchar
)
-- changeset qMargo:2
INSERT INTO shelter_info (about_shelter, address_schedule, contact_for_car_pass, safety_on_territory,first_meet_recommendation,
documents, transportation_advice, house_rules_for_small_animal,house_rules_for_adult_animal,rules_for_animal_with_disability,
cynologist_advice,cynologists,refuse_reasons)
VALUES ('Приют для кошек "МУР-МУР"',
'Адрес приюта и часы работы',
'Телефон для получения пропуска 8(222) 222-222',
'Правила нахождения внутри приюта и общения с животными:____',
'Рекомендации для первого знакомства с  животным:___Р1',
'Список документов',
'Необходимо иметь переноску',
'Необходимо иметь 3-х дневный запас еды возможно молока для малыша',
'Рекомендации для кошки:___Р2',
'Рекомендации для кошки:___Р3',
null,
null,
'Список правил "усыновления" кошек')

