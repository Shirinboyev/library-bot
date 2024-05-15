package uz.pdp.bot.handlers;

import com.pengrad.telegrambot.model.Update;


public class HandleUpdate {

    private BaseHandler messageHandler ;
    private BaseHandler callBackQueryHandler ;


    public HandleUpdate() {
        this.messageHandler = new MesHandler();
        this.callBackQueryHandler = new CallbackHandler();
    }

    public void manage(Update update) {
        if (update.message()!=null) {
            messageHandler.handle(update);
        }else if (update.callbackQuery()!=null){
            callBackQueryHandler.handle(update);
        }
    }
}
