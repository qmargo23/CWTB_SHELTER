package pro.sky.CWTBshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BotUpdatesListener implements UpdatesListener {
    private final TelegramBot bot;

    public BotUpdatesListener(TelegramBot bot) {
        this.bot = bot;
    }

    @PostConstruct
    void init() {
        bot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            // echo bot
            bot.execute(new SendMessage(update.message().chat().id(), update.message().text()));
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
