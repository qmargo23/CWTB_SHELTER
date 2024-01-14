package pro.sky.CWTBshelter.util.imp;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.repository.ShelterInfoRepository;
import pro.sky.CWTBshelter.util.KeyboardUtil;
import pro.sky.CWTBshelter.util.MenuService;

import static pro.sky.CWTBshelter.init.CallbackDataRequest.*;

/**
 * отвечает за меню и расстановку кнопок под сообщением
 *
 * @see pro.sky.CWTBshelter.util.KeyboardUtil
 */
@Service
public class MenuServiceImp implements MenuService {
    private final String startText = "Для начала работы надо выбрать приют.";//текст выбора приюта
    private final String adoptText = "Усыновление питомца возможно только через волонтера.\n" +
            "\n...на данном этапе Вы можете посмотреть какие питомцы ищут новый дом:";
    private final String reportText = "Сдача отчета на данном этапе возможно только через волонтера.";// TODO: 09.01.2024  


    //кнопки быстрого доступа к меню - они дублируют команды в главном меню, но добавлены (кодим ради кода)))))
    private final String Button1 = "Выбрать приют";
    private final String Button2 = "Забрать питомца";
    private final String Button3 = "Сдать отчет";
    private final String Button4 = "Помощь";

    private final TelegramBot telegramBot;
    private final KeyboardUtil keyboardUtil;
    private final ShelterInfoRepository shelterInfoRepository;

    public MenuServiceImp(TelegramBot telegramBot,
                          KeyboardUtil keyboardUtil,
                          ShelterInfoRepository shelterInfoRepository) {
        this.telegramBot = telegramBot;
        this.keyboardUtil = keyboardUtil;
        this.shelterInfoRepository = shelterInfoRepository;
    }

    private final String catMenuText = "Добро пожаловать в наш кошачий приют." +
            "\n" +//можно добавить описание...
            "\n На данном этапе вы можете выбрать:" +//можно добавить описание
            "\n";//можно добавить описание...
    private final String dogMenuText = "Добро пожаловать в наш собачий приют." +
            "\n" +//можно добавить описание...
            "\n На данном этапе вы можете выбрать:" +//можно добавить описание
            "\n";//можно добавить описание...

    private final String getCatAdoptText = "... <<<getCatAdoptText>>>...\n " +
            "\n дополнить нужной ИНФ";

    private final String getDogAdoptText = "...<<<getDogAdoptText>>>...\n " +
            "\n дополнить нужной ИНФ";

    private final String shelterMenuText = "...на данном этапе можно получить следующую информацию о приюте:";//можно добавить описание...

    @Override
    public SendMessage getStartMenuShelter(Long chatId) {
        //прикрепляем кнопки выбора приюта на данном этапе CAT и DOG
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(CAT, DOG);
        SendMessage sendMessage = new SendMessage(chatId, startText)
                .replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    @Override
    public SendMessage getCatMenu(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                GET_SHELTER_MENU,
                ADOPT_MENU,
                REPORT_MENU,
                HELP
        );
//нужен рефакторинг кода..проверка  get()
        String shelter = shelterInfoRepository.findById(2L).get().getAboutShelter();
        SendMessage sendMessage = new SendMessage(chatId, shelter + "\n" + "\n" + catMenuText)
                .replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    @Override
    public SendMessage getDogMenu(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                GET_SHELTER_MENU,
                ADOPT_MENU,
                REPORT_MENU,
                HELP
        );
        String shelter = shelterInfoRepository.findById(1L).get().getAboutShelter();
        SendMessage sendMessage = new SendMessage(chatId, shelter + "\n" + "\n" + dogMenuText)
                .replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    @Override
    public SendMessage getAdoptMenuShelter(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                CAT_ADOPT_LIST,
                DOG_ADOPT_LIST,
                HELP);
        SendMessage sendMessage = new SendMessage(chatId, adoptText)
                .replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    @Override
    public SendMessage getReportMenuShelter(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(HELP);
        SendMessage sendMessage = new SendMessage(chatId, reportText)
                .replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    @Override
    public SendMessage getCatAdoptMenu(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                FIRST_MEET_RECOMMENDATION,
                DOCUMENTS,
                TRANSPORTATION_ADVICE,
                HOUSE_RULES_FOR_SMALL_ANIMAL,
                HOUSE_RULES_FOR_ADULT_ANIMAL,
                RULES_FOR_ANIMAL_WITH_DISABILITY,
                REFUSE_REASONS,
                HELP
        );
        String shelter = shelterInfoRepository.findById(2L).get().getAboutShelter();
        SendMessage sendMessage = new SendMessage(chatId, shelter + "\n" + "\n" + getCatAdoptText)
                .replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    @Override
    public SendMessage getDogAdoptMenu(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                FIRST_MEET_RECOMMENDATION,
                DOCUMENTS,
                TRANSPORTATION_ADVICE,
                HOUSE_RULES_FOR_SMALL_ANIMAL,
                HOUSE_RULES_FOR_ADULT_ANIMAL,
                RULES_FOR_ANIMAL_WITH_DISABILITY,
                DOG_HANDLER_ADVISE,
                DOG_HANDLER,
                REFUSE_REASONS,
                HELP
        );
        String shelter = shelterInfoRepository.findById(1L).get().getAboutShelter();
        SendMessage sendMessage = new SendMessage(chatId, shelter + "\n" + "\n" + getDogAdoptText)
                .replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    @Override
    public SendMessage getShelterInfoMenu(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                ABOUT_SHELTER,
                WORKING_HOURS,
                LOCATION_MAP,
                SECURITY_CONTACT,
                SAFETY_RECOMMENDATIONS,
                HELP
        );
        //Бот приветствует пользователя.
        // ? по заданию проекта, но нужно ли?
        SendMessage sendMessage = new SendMessage(chatId, shelterMenuText)
                .replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }


    public void setButtonKeyboard(Update update, String greetingFirstMessageText) {
// создаем меню-клавиатуру, для быстрого доступа к данным о приюте
        Keyboard keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton[]{new KeyboardButton(Button4)},
                new KeyboardButton[]{
                        new KeyboardButton(Button1),
                        new KeyboardButton(Button2),
                        new KeyboardButton(Button3)
                })
                .resizeKeyboard(true)
                .selective(true);
// посылаем приветсвенное сообщение + меню-кнопки внизу
        SendMessage request = new SendMessage(update.message().chat().id(), greetingFirstMessageText)
                .replyMarkup(keyboard);
        telegramBot.execute(request);
    }
}