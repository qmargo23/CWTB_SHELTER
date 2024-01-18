package pro.sky.CWTBshelter.model;

/**
 * Возможный "тип" пользователя приюта
 */
public enum ShelterUserType {
    /**
     * Просто смотрит
     */
    JUST_LOOKING,
    /**
     * Находится на испытательном сроке
     */
    PROBATION,
    /**
     * Испытательный срок продлен на 14 дней
     */
    PROBATION_EXTEND,

    /**
     * Успешно прошел испытательный срок
     */
    SUCCESSFUL_COMPLETION,
    /**
     * Испытательный срок не пройден
     */
    FAILED,
}
