package pro.sky.CWTBshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.GetFileResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pro.sky.CWTBshelter.dto.TelegramUserDTO;
import pro.sky.CWTBshelter.dto.mapper.TelegramUserDTOMapper;
import pro.sky.CWTBshelter.handler.Handler;
import pro.sky.CWTBshelter.handler.StartHandler;
import pro.sky.CWTBshelter.handler.WaitCommandHandler;
import pro.sky.CWTBshelter.model.BotState;
import pro.sky.CWTBshelter.model.TelegramUser;
import pro.sky.CWTBshelter.service.imp.TelegramUserServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class BotUpdatesListener implements UpdatesListener {
    private final TelegramBot bot;
    private final TelegramUserServiceImpl telegramUserService;
    private final Map<BotState, Handler> handlerMap = new HashMap<>();
    private final BotState defaultBotState = BotState.START;
    private final TelegramUserDTOMapper telegramUserDTOMapper;
    private final Logger logger = LoggerFactory.getLogger(BotUpdatesListener.class);

    public BotUpdatesListener(
            TelegramBot bot,
            TelegramUserServiceImpl telegramUserService,
            StartHandler startHandler,
            WaitCommandHandler waitCommandHandler,
            TelegramUserDTOMapper telegramUserDTOMapper
    ) {
        this.bot = bot;
        this.telegramUserService = telegramUserService;
        this.telegramUserDTOMapper = telegramUserDTOMapper;
        handlerMap.put(BotState.START, startHandler);
        handlerMap.put(BotState.WAIT_COMMAND, waitCommandHandler);
    }

    @PostConstruct
    void init() {
        bot.setUpdatesListener(this);
        bot.execute(new SetMyCommands(
                new BotCommand("/start", " выбор приюта"),
                new BotCommand("/adopt", " забрать питомца"),
                new BotCommand("/report", " сдать отчет"),
                new BotCommand("/help", "вызов волонтера")));
    }

    @Override
    public int process(List<Update> updates) {

//        for (Update update : updates) {
//            TelegramUser telegramUser = telegramUserService.getTelegramUserByChatId(update.message().chat().id());
//            if (telegramUser == null) {
//                telegramUser = telegramUserService.createTelegramUser(
//                        new TelegramUserDTO.Request.Create(update.message().chat().id(), defaultBotState, null)
//                ); // FIXME: заменить null на нового юзера
//            }
//            BotState botState = telegramUser.getBotState();
//            BotState nextBotState = handlerMap.get(botState).handle(update); // Обрабатываем обновление
//            if (!Objects.equals(nextBotState, botState)) { // Если состояние изменилось
//                handlerMap.get(nextBotState).makeTransition(update); // Делаем переход на следующее состояние
//                telegramUser.setBotState(nextBotState);
//                telegramUserService.updateTelegramUser(telegramUser.getId(), telegramUserDTOMapper.toCreateDTO(telegramUser));
//            }
//        }

        //____EL________EL________EL____
        try {
            updates.forEach(update -> {
                logger.info("Handles update: {}", update);
                if (update.callbackQuery() != null) {
//вызвать методы класса  для обработки обратных запросов - при нажатии КНОПКИ
                } else if (update.message().text() != null) {
// вызвать методы класса обрабатывающие  update.message().text() - когда введен ТЕКСТ
                } else if (update.message().photo() != null || update.message().caption() != null) {
// вызвать методы класса обрабатывающие ФОТО, ВИДЕО иди ДОКУМЕНТЫ
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        //____EL________EL________EL____

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
