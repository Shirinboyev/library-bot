package uz.pdp.bot.handlers;

import com.pengrad.telegrambot.model.Update;

public class HandleUpdate {

    public static void handle(Update update) {
        if (update.message() != null && update.message().text() != null) {
            MesHandler.update(update);
        }
        else if (update.callbackQuery() != null)
            CallbackHandle.update(update);
    }
}
