package uz.pdp.bot.handlers;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.backend.model.user.MyUser;
import static uz.pdp.bot.myBot.MyBot.bot;

public class MesHandler  {
    public static void update(Update update){
        Message message = update.message();
        Long chatID = message.chat().id();
        MyUser myUser = new MyUser();
        User from = message.from();
        String text = message.text();

        if (text.equals("/start")){
            SendMessage snm = new SendMessage(chatID,"Assalomu Alaykum !");
            bot.execute(snm);
        }
    }
}