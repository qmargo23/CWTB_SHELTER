package pro.sky.CWTBshelter.init;

public enum CallbackDataRequest {
    CAT("Приют для котов", "CAT"),
    DOG("Приют для собак", "DOG"),

    HELP("Вызвать волонтера", "HELP"),;
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
