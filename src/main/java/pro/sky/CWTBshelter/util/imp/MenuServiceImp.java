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
//                GET_SHELTER_MENU,
//                ADOPT_MENU,
//                REPORT_MENU,
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
//                GET_SHELTER_MENU,
//                ADOPT_MENU,
//                REPORT_MENU,
                HELP
        );
        String shelter = shelterInfoRepository.findById(1L).get().getAboutShelter();
        SendMessage sendMessage = new SendMessage(chatId, shelter + "\n" + "\n" + dogMenuText)
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