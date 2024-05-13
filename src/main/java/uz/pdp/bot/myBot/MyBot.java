package uz.pdp.bot.myBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.bot.handlers.HandleUpdate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyBot {
    public static TelegramBot bot = new TelegramBot("7179007441:AAEtUjFIQcNBPK9-dGdQvwXJcvZpClhQX08");
    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        bot.setUpdatesListener(updates -> {
                    for (Update update : updates) {
                        CompletableFuture.runAsync(() -> {
                            HandleUpdate.handle(update);
                        }, executorService);
                    }
                    return UpdatesListener.CONFIRMED_UPDATES_ALL;
                }
        );
    }

}
