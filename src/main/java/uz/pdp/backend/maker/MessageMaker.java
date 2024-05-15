package uz.pdp.backend.maker;

import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.backend.model.user.MyUser;
import uz.pdp.bot.states.BaseState;


public class MessageMaker {
    public SendMessage mainMenu(MyUser curUser){
        SendMessage sendMessage = new SendMessage(curUser.getId(), "Main Menu");
        InlineKeyboardButton[][] buttons = {
                {
                        new InlineKeyboardButton("Add Book").callbackData("ARCHIVE"),
                        new InlineKeyboardButton("Search Book").callbackData("CALCULATE"),
                }
        };
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(buttons);
        sendMessage.replyMarkup(markup);
        return sendMessage;
    }

    public SendMessage enterPhoneNumber(MyUser curUser){
        SendMessage sendMessage = new SendMessage(curUser.getId(), "Enter Phone Number");
        KeyboardButton[][] buttons ={
                { new KeyboardButton("Share Contact").requestContact(true) }
        };
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(buttons).oneTimeKeyboard(true).resizeKeyboard(true);
        sendMessage.replyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    // Yangi metod
    public SendMessage chooseMenu(MyUser curUser) {
        SendMessage sendMessage = new SendMessage(curUser.getId(), "Choose Menu");
        KeyboardButton[][] buttons ={
                { new KeyboardButton("Add Book"), new KeyboardButton("Search Book") }
        };
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(buttons).oneTimeKeyboard(true).resizeKeyboard(true);
        sendMessage.replyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}
