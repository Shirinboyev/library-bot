package uz.pdp.bot.handlers;

import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import uz.pdp.backend.model.book.Book;
import uz.pdp.backend.model.user.MyUser;
import uz.pdp.bot.states.BaseState;
import uz.pdp.bot.states.child.MainState;


import java.io.File;
import java.util.List;
import java.util.Objects;

import static uz.pdp.bot.myBot.MyBot.bot;

public class MesHandler extends BaseHandler {
    @Override
    public void handle(Update update) {
        Message message = update.message();
        if (message == null) {
            return;
        }
        User from = message.from();
        super.update = update;
        super.curUser = getUserOrCreate(from);
        String text = message.text();

        if (text != null && text.equals("/start")) {
            SendMessage sendMessage = new SendMessage(from.id(), "Assalomu Alaykum " + curUser.getFirstname() + "\nBotga xush kelibsiz");
            bot.execute(sendMessage);
            if (Objects.isNull(curUser.getPhoneNumber()) || curUser.getPhoneNumber().isEmpty()) {
                enterPhoneNumber();
                curUser.setState(MainState.REGISTER.name());
                userService.save(curUser);
            }
            else mainMenu();
        }
        else if (Objects.equals(text,"Search Book")) {
            SendMessage sendMessage = new SendMessage(from.id(),"Janrlardan brini tanlang");
            sendMessage.replyMarkup(messageMaker.selectBookGenre());

            bot.execute(sendMessage);
        }
        else if (Objects.equals(text,"Fantastic Book")) {
            String fantasticBook = """
                    1. /Harry_Poter_Falsafiy_tosh
                    2. /Harry_Poter_Maxfiy_xujra
                    3. /Harry_Poter_Azkaban_maxbusi
                    4. /Harry_Poter_Otashli_jom
                    5. /Harry_Poter_Kaknus_ordeni
                    6. /Harry_Poter_Chalazot_shaxzoda
                    
                    7. /Gulliverning_Sayohatlari
                    
                    """;
            bot.execute(new SendMessage( from.id(),fantasticBook));

        } else if (Objects.equals(text,"Badiy Book")) {
            String badiyBook = """
                    1. /Abdulla_Qahhor_Hikoyalari
                    2. /Anor_Oq_korfaz
                    3. /Boy_Ota_kambagal_ota
                    4. /Daydi_qizning_daftari
                    5. /Gafur_Gulom_shum_bola
                    6. /Otkish_Hoshimov_Dunyoning_Ishlari
                                       
                    """;
            bot.execute(new SendMessage( from.id(),badiyBook));

        } else {
            String baseStateString = curUser.getBaseState();
            BaseState baseState = BaseState.valueOf(baseStateString);
            if (Objects.equals(baseState, BaseState.MAIN_MENU)) {
                mainState();
            } else if (Objects.equals(baseState, BaseState.ADD_BOOK_STATE)) {
                addBookState();
            } else if (Objects.equals(baseState, BaseState.SEARCH_BOOK_STATE)) {
                searchBookState();
            }
        }switch (text){
            case "/Harry_Poter_Falsafiy_tosh" -> sendDocument(new File("src/main/resources/fantastikBooks/1.Garri Potter va Falsafiy tosh.pdf"));
            case "/Harry_Poter_Maxfiy_xujra" -> sendDocument(new File("src/main/resources/fantastikBooks/2.Garri Potter va Maxfiy xujra.pdf"));
            case "/Harry_Poter_Azkaban_maxbusi" -> sendDocument(new File("src/main/resources/fantastikBooks/3.Garri Potter va Azkaban maxbusi.pdf"));
            case "/Harry_Poter_Otashli_jom" -> sendDocument(new File("src/main/resources/fantastikBooks/4. Garri Potter va Otashli jom.pdf"));
            case "/Harry_Poter_Kaknus_ordeni" -> sendDocument(new File("src/main/resources/fantastikBooks/5. Garri Potter va Kaknus ordeni.pdf"));
            case "/Harry_Poter_Chalazot_shaxzoda" -> sendDocument(new File("src/main/resources/fantastikBooks/6. Garri Potter va Chalazot shaxzoda.pdf"));
            case "/Gulliverning_Sayohatlari" -> sendDocument(new File("src/main/resources/fantastikBooks/Gulliverning_sayohatlari.pdf"));

            case "/Abdulla_Qahhor_Hikoyalari" -> sendDocument(new File("src/main/resources/badiyBooks/Abdulla Qohhor hikoyalari.pdf"));
            case "/Anor_Oq_korfaz" -> sendDocument(new File("src/main/resources/badiyBooks/Anor. Oq ko'rfaz (qissa).pdf"));
            case "/Boy_Ota_kambagal_ota" -> sendDocument(new File("src/main/resources/badiyBooks/Boy ota, kambag'al ota.pdf"));
            case "/Daydi_qizning_daftari" -> sendDocument(new File("src/main/resources/badiyBooks/daydi_qizning_daftari.pdf"));
            case "/Gafur_Gulom_shum_bola" -> sendDocument(new File("src/main/resources/badiyBooks/G‘ofur G‘ulom shum bola.pdf"));
            case "/Otkish_Hoshimov_Dunyoning_Ishlari" -> sendDocument(new File("src/main/resources/badiyBooks/O'tkir H - Dunyoning ishlari.pdf"));
        }
    }
    public void sendDocument(File file){
        Long chatId = update.message().chat().id();
        SendDocument sendDocument = new SendDocument(chatId,file);
        bot.execute(sendDocument);
    }

    private void searchBookState() {
        Message message = update.message();
        if (message == null) {
            // Agar habar bo'sh bo'lsa, qaytib ketamiz
            return;
        }
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

    private void addBookState() {
    }

    private void fantastikState() {
    }

    private void badiyState() {
    }

    private void romantikState() {
    }

    private void mainState() {
        String stateStr = curUser.getState();
        MainState state = MainState.valueOf(stateStr);
        switch (state) {
            case REGISTER -> {
                Message message = update.message();
                if (message == null) {
                    // Agar habar bo'sh bo'lsa, qaytib ketamiz
                    return;
                }
                Contact contact = message.contact();
                if (contact != null) {
                    String phoneNumber = contact.phoneNumber();
                    curUser.setPhoneNumber(phoneNumber);
                    userService.save(curUser);
                    mainMenu();
                } else {
                    incorrectData("Phone Number");
                }
            }
        }
    }

    private void mainMenu() {
        SendMessage sendMessage = messageMaker.mainMenu(curUser);
        bot.execute(sendMessage);
        curUser.setState(BaseState.MAIN_MENU.name());
        userService.save(curUser);
    }

    private void enterPhoneNumber() {
        SendMessage sendMessage = messageMaker.enterPhoneNumber(curUser);
        bot.execute(sendMessage);
    }

    private void incorrectData(String data) {
        bot.execute(new SendMessage(curUser.getId(), "You entered incorrect " + data));
    }
}

