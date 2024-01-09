package pro.sky.CWTBshelter.util.imp;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.init.CallbackDataRequest;
import pro.sky.CWTBshelter.model.ShelterInfo;
import pro.sky.CWTBshelter.repository.ShelterInfoRepository;
import pro.sky.CWTBshelter.util.ButtonReactionService;
import pro.sky.CWTBshelter.util.MenuService;
import pro.sky.CWTBshelter.util.MessageSender;

import java.util.Optional;

@Service
public class ButtonReactionServiceImpl implements ButtonReactionService {
    //изначально выбран приют для собак
    private boolean isCat = false;

    private final TelegramBot telegramBot;

    private final MessageSender messageSender;
    private final MenuService menuService;
    private final ShelterInfoRepository shelterInfoRepository;

    public ButtonReactionServiceImpl(TelegramBot telegramBot,
                                     MessageSender messageSender,
                                     MenuService menuService,
                                     ShelterInfoRepository shelterInfoRepository) {
        this.telegramBot = telegramBot;
        this.messageSender = messageSender;
        this.menuService = menuService;
        this.shelterInfoRepository = shelterInfoRepository;
    }

    @Override
    public SendMessage buttonReaction(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.message().chat().id();
        String data = callbackQuery.data();

        //dataRequest - данные пришедшие при нажатии кнопок
        CallbackDataRequest dataRequest = CallbackDataRequest.getConstantByRequest(data);
        //shelterInfoOptional - работа с нужной строкой (приютами) в БД
        Optional<ShelterInfo> shelterInfoOptional;

        if (isCat) {
            shelterInfoOptional = shelterInfoRepository.findById(2L);//cats
        } else shelterInfoOptional = shelterInfoRepository.findById(1L);//dogs

        //проверка значений для dataRequest
        switch (dataRequest) {
            case CAT:
                isCat = true;
                return menuService.getCatMenu(chatId);//создадим сообщение для приюта cat
            case DOG:
                isCat = false;
                return menuService.getDogMenu(chatId);//создадим сообщение для приюта dog
            case HELP:
                return messageSender.sendMessage(chatId, "Воспользуйтесь командой /help");
            case ADOPT_MENU:
                if (isCat) {
                    return menuService.getCatAdoptMenu(chatId);
                }
                return menuService.getDogAdoptMenu(chatId);

            default:
                return messageSender.sendMessage(chatId, "КОД ЭТОЙ КНОПКИ ЕЩЕ НЕ НАПИСАН");
        }
    }
}
