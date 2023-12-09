package pro.sky.CWTBshelter.handler;

import com.pengrad.telegrambot.model.Update;
import pro.sky.CWTBshelter.model.BotState;

public interface Handler {
    /**
     * Does some preparation after changing botState
     *
     * @param update update from Telegram
     */
    void makeTransition(Update update);

    /**
     * Handle update from Telegram and return next bot state
     *
     * @param update update from Telegram
     * @return next bot state
     */
    BotState handle(Update update);
}
