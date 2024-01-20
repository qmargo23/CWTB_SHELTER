package pro.sky.CWTBshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SetMyCommands;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.sky.CWTBshelter.dto.mapper.TelegramUserDTOMapper;
import pro.sky.CWTBshelter.handler.Handler;
import pro.sky.CWTBshelter.handler.StartHandler;
import pro.sky.CWTBshelter.handler.WaitCommandHandler;
import pro.sky.CWTBshelter.model.BotState;
import pro.sky.CWTBshelter.service.ReportTelegramService;
import pro.sky.CWTBshelter.service.imp.TelegramUserServiceImpl;
import pro.sky.CWTBshelter.util.ButtonReactionService;
import pro.sky.CWTBshelter.util.imp.HandlerServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotUpdatesListener implements UpdatesListener {
    private final TelegramBot bot;
    private final TelegramUserServiceImpl telegramUserService;
    private final Map<BotState, Handler> handlerMap = new HashMap<>();
    private final TelegramUserDTOMapper telegramUserDTOMapper;
    private final Logger logger = LoggerFactory.getLogger(BotUpdatesListener.class);
    private final HandlerServiceImpl updateHandler;
    private final ButtonReactionService buttonReactionService;
    private final ReportTelegramService reportService;

    public BotUpdatesListener(
            TelegramBot bot,
            TelegramUserServiceImpl telegramUserService,
            StartHandler startHandler,
            WaitCommandHandler waitCommandHandler,
            TelegramUserDTOMapper telegramUserDTOMapper,
            HandlerServiceImpl updateHandler,
            ButtonReactionService buttonReactionService,
            ReportTelegramService reportService) {
        this.bot = bot;
        this.telegramUserService = telegramUserService;
        this.telegramUserDTOMapper = telegramUserDTOMapper;
        this.updateHandler = updateHandler;
        this.buttonReactionService = buttonReactionService;
        this.reportService = reportService;

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
        try {
            updates.forEach(update -> {
                logger.info("Handles update: {}", update);
                if (update.callbackQuery() != null) {

                    buttonReactionService.buttonReaction(update.callbackQuery());

                } else if (update.message().text() != null) {

                    updateHandler.messageHandler(update);

                } else if (update.message().photo() != null || update.message().caption() != null) {

                    reportService.postReport(update.message().chat().id(), update);
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
