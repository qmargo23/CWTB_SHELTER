package pro.sky.CWTBshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pro.sky.CWTBshelter.handler.Handler;
import pro.sky.CWTBshelter.handler.StartHandler;
import pro.sky.CWTBshelter.handler.WaitCommandHandler;
import pro.sky.CWTBshelter.model.BotState;
import pro.sky.CWTBshelter.model.TelegramUser;
import pro.sky.CWTBshelter.service.TelegramUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotUpdatesListener implements UpdatesListener {
    private final TelegramBot bot;
    private final TelegramUserService telegramUserService;
    private final Map<BotState, Handler> handlerMap = new HashMap<>();
    private final BotState defaultBotState = BotState.START;

    public BotUpdatesListener(
            TelegramBot bot,
            TelegramUserService telegramUserService,
            StartHandler startHandler,
            WaitCommandHandler waitCommandHandler
    ) {
        this.bot = bot;
        this.telegramUserService = telegramUserService;
        handlerMap.put(BotState.START, startHandler);
        handlerMap.put(BotState.WAIT_COMMAND, waitCommandHandler);
    }

    @PostConstruct
    void init() {
        bot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            TelegramUser telegramUser = telegramUserService.findByChatId(update.message().chat().id());
            BotState botState = telegramUser != null ? telegramUser.getBotState() : defaultBotState;
            Handler handler = handlerMap.get(botState);
            handler.handle(update);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
