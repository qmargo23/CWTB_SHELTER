package pro.sky.CWTBshelter.handler;

import com.pengrad.telegrambot.model.Update;

public interface Handler {
    void handle(Update update);
}
