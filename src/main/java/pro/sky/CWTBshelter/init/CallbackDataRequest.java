package pro.sky.CWTBshelter.init;

public enum CallbackDataRequest {
    CAT("Приют для котов", "CAT"),
    DOG("Приют для собак", "DOG"),

    HELP("Вызвать волонтера", "HELP"),

    //ADOPT_MENU_____________________________________________________
    ADOPT_MENU("Как забрать питомца.","ADOPT_MENU"),

    //REPORT_MENU________________________________________________________________
    REPORT_MENU("Правила ведения отчетов","REPORT_MENU"),

    //GET_SHELTER_MENU_____________________________________________________
    GET_SHELTER_MENU("(I) Информация о приюте. ","GET_INFO_SHELTER"),
    ABOUT_SHELTER("Рассказать о приюте. ","ABOUT_SHELTER"),
    WORKING_HOURS("Выдать расписание работы приюта и адрес.","WORKING_HOURS"),
    LOCATION_MAP("Показать схему проезда (в разработке !!!)","LOCATION_MAP"),
    SECURITY_CONTACT("Оформить пропуск на машину.","SECURITY_CONTACT"),
    SAFETY_RECOMMENDATIONS("Рекомендации пребывания на территории приюта. ","SAFETY_RECOMMENDATIONS"),

    ;
    private final String text;
    private final String callbackData;
    CallbackDataRequest(String text, String callbackData) {
        this.text = text;
        this.callbackData = callbackData;
    }

    public String getText() {
        return text;
    }

    public String getCallbackData() {
        return callbackData;
    }
    //getConstantByRequest ищет в enum  и возвращает значения запроса по значению data
    public static CallbackDataRequest getConstantByRequest(String data) {
        for (CallbackDataRequest value : CallbackDataRequest.values()) {
            if (value.getCallbackData().equals(data)) {
                return value;
            }
        }
        throw new IllegalArgumentException("такого значения нет!");
    }

}
