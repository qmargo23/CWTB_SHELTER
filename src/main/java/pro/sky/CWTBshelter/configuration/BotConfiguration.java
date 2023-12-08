package pro.sky.CWTBshelter.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {
    @Value("${bot.token}")
    private String botToken;
    @Bean
    public TelegramBot bot() {
        return new TelegramBot(botToken);
    }
}
