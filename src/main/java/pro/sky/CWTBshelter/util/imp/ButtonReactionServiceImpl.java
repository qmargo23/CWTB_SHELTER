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
                return menuService.getCatMenu(chatId);//создадим menu-сообщение для приюта cat
            case DOG:
                isCat = false;
                return menuService.getDogMenu(chatId);//создадим menu-сообщение для приюта dog
            case HELP:
                return messageSender.sendMessage(chatId, "Воспользуйтесь командой /help");

                //________________ADOPT_MENU________________
            case ADOPT_MENU:
                if (isCat) {
                    return menuService.getCatAdoptMenu(chatId);
                }
                return menuService.getDogAdoptMenu(chatId);
            case FIRST_MEET_RECOMMENDATION:
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getFirstMeetRecommendation());
                }
            case DOCUMENTS:
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getDocuments());
                }
            case TRANSPORTATION_ADVICE:
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getTransportationAdvice());
                }
            case HOUSE_RULES_FOR_SMALL_ANIMAL:
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getHouseRulesForSmallAnimal());
                }
            case HOUSE_RULES_FOR_ADULT_ANIMAL:
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getHouseRulesForAdultAnimal());
                }
            case RULES_FOR_ANIMAL_WITH_DISABILITY:
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getRulesForAnimalWithDisability());
                }
            case DOG_HANDLER_ADVISE:
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getHouseRulesForAdultAnimal());
                }
            case DOG_HANDLER:
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getCynologists());
                }
            case REFUSE_REASONS:
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getRefuseReasons());
                }

                //________________GET_SHELTER_MENU________________
            case GET_SHELTER_MENU://это общее меню для приюта кошек и собак!
// можно сюда "воткнуть" приветсвие пользователя
//messageSender.sendMessage(chatId, "HELLO ");
                return menuService.getShelterInfoMenu(chatId);
            //________________getShelterInfoMenu________________
            case ABOUT_SHELTER://Рассказать о приюте.
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getAboutShelter());
                }
            case WORKING_HOURS://Выдать расписание работы приюта и адрес.
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getAddressSchedule());
                }
            case LOCATION_MAP://"Показать схему проезда
                return messageSender.sendMessage(chatId, "временно не доступно");
            case SECURITY_CONTACT://Оформить пропуск на машину.
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getContactForCarPass());
                }
            case SAFETY_RECOMMENDATIONS://Рекомендации пребывания на территории приюта.
                if (shelterInfoOptional.isPresent()) {
                    return messageSender.sendMessage(chatId, shelterInfoOptional.get().getSafetyOnTerritory());
                }

            default:
                return messageSender.sendMessage(chatId, "КОД ЭТОЙ КНОПКИ ЕЩЕ НЕ НАПИСАН");
        }
    }
}
