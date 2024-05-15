package uz.pdp.bot.maker;

import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.backend.model.user.MyUser;
import uz.pdp.bot.states.BaseState;

import java.util.Objects;


public class MessageMaker {

    public SendMessage mainMenu(MyUser curUser) {
        SendMessage sendMessage = new SendMessage(curUser.getId(), "Choose Menu");
        KeyboardButton[][] buttons = {
                {new KeyboardButton("Add Book"), new KeyboardButton("Search Book")}
        };
        if (Objects.equals(curUser.getBaseState(), BaseState.SEARCH_BOOK_STATE.name())) {
            buttons = new KeyboardButton[][]{
                    {new KeyboardButton("Fantastik Book"), new KeyboardButton("Badiy Book"), new KeyboardButton("Romantik Book")}
            };
        }
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(buttons).oneTimeKeyboard(true).resizeKeyboard(true);
        sendMessage.replyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage enterPhoneNumber(MyUser curUser) {
        SendMessage sendMessage = new SendMessage(curUser.getId(), "Share Contact");
        KeyboardButton[][] buttons = {
                {new KeyboardButton("Share Contact").requestContact(true)}
        };
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(buttons).oneTimeKeyboard(true).resizeKeyboard(true);
        sendMessage.replyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
    public Keyboard selectBookGenre(){
        KeyboardButton[][] buttons = {
                {new KeyboardButton("Fantastic Book"),
                        new KeyboardButton("Badiy Book"),
                        new KeyboardButton("Romantic Book")}
        };
        return new ReplyKeyboardMarkup(buttons).oneTimeKeyboard(true).resizeKeyboard(true);
    }

}

