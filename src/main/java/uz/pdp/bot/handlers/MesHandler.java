package uz.pdp.bot.handlers;

import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.backend.model.book.Book;
import uz.pdp.backend.model.user.MyUser;
import uz.pdp.bot.states.BaseState;
import uz.pdp.bot.states.child.MainState;


import java.util.List;
import java.util.Objects;

import static uz.pdp.bot.myBot.MyBot.bot;

public class MesHandler  extends BaseHandler {
    @Override
    public void handle(Update update){
        Message message = update.message();
        User from = message.from();
        super.update = update;
        super.curUser = getUserOrCreate(from);
        String text = message.text();

        if (text.equals("/start")){
            SendMessage sendMessage = new SendMessage(from.id(),"Assalomu Alaykum " + curUser.getFirstname() + " " + curUser.getLastname() + "\nBotga xush kelibsiz");
            bot.execute(sendMessage);
            if (Objects.isNull(curUser.getPhoneNumber()) || curUser.getPhoneNumber().isEmpty()){
                enterPhoneNumber();
                curUser.setState(MainState.REGISTER.name());
                userService.save(curUser);
            } else {
                mainMenu();
            }
        }

        else {
            String baseStateString = curUser.getBaseState();
            BaseState baseState = BaseState.valueOf(baseStateString);
            if (Objects.equals(baseState, BaseState.MAIN_STATE)){
                mainState();
            }
            else if(Objects.equals(baseState, BaseState.ADD_BOOK_STATE)){
                addBookState();
            }
            else if(Objects.equals(baseState, BaseState.SEARCH_BOOK_STATE)){
                searchBookState();
            }
        }
    }

    private void searchBookState() {

    }

    private void addBookState() {
    }

/*
    private void searchBookState() {
        Message message = update.message();
        String text = message.text();
        if (text != null && !text.isEmpty()) {
            List<Book> searchResults = performSearch(text);
            sendSearchResults(searchResults);
        } else {
            bot.execute(new SendMessage(curUser.getId(), "You didn't enter any text for searching."));
        }
    }
    private List<Book> performSearch(String searchText) {
        return bookService.searchBooks(searchText);
    }

    private void sendSearchResults(List<Book> searchResults) {
        if (searchResults.isEmpty()) {
            bot.execute(new SendMessage(curUser.getId(), "No books found matching your search."));
        } else {
            StringBuilder resultMessage = new StringBuilder("Search results:\n");
            for (Book book : searchResults) {
                resultMessage.append("- ").append(book.getName()).append("\n");
            }
            bot.execute(new SendMessage(curUser.getId(), resultMessage.toString()));
        }
    }
*/


    private void fantastikState() {
    }

    private void badiyState() {
    }

    private void romantikState() {
    }


    private void mainState() {
        String stateStr = curUser.getState();
        MainState state =  MainState.valueOf(stateStr);
        switch (state) {
            case REGISTER -> {
                Message message = update.message();
                Contact contact = message.contact();
                if (contact != null) {
                    String phoneNumber = contact.phoneNumber();
                    curUser.setPhoneNumber(phoneNumber);
                    userService.save(curUser);
                    mainMenu();
                }
                else {
                    incorrectData("Phone Number");
                }
            }
        }
    }

    private void mainMenu() {
        SendMessage sendMessage = messageMaker.mainMenu(curUser);
        bot.execute(sendMessage);
        curUser.setState(BaseState.MAIN_STATE.name());
        userService.save(curUser);
    }

    private void enterPhoneNumber() {
        SendMessage sendMessage = messageMaker.enterPhoneNumber(curUser);
        bot.execute(sendMessage);
    }

    private void incorrectData(String data  ) {
        bot.execute(new SendMessage(curUser.getId(),"You entered incorrect " + data));
    }
}
