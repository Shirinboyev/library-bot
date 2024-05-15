package uz.pdp.bot.myBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.bot.handlers.HandleUpdate;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyBot {
    public static String BOT_TOKEN = "7179007441:AAEtUjFIQcNBPK9-dGdQvwXJcvZpClhQX08";
    public static TelegramBot bot = new TelegramBot(BOT_TOKEN);
    static  ThreadLocal<HandleUpdate> updateHandlerThreadLocal = ThreadLocal.withInitial(HandleUpdate::new);

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                CompletableFuture.runAsync( () -> {
                            try {
                                updateHandlerThreadLocal.get().manage(update);
                            }catch (Exception e){
                                e.printStackTrace();
                            }}
                        ,pool);
            }
                    return UpdatesListener.CONFIRMED_UPDATES_ALL;
                }
        );
    }

}
