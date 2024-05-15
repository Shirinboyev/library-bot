package uz.pdp.bot.handlers;

import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.backend.model.user.MyUser;
import uz.pdp.bot.states.BaseState;
import uz.pdp.bot.states.child.MainState;

import java.util.Objects;

import static uz.pdp.bot.myBot.MyBot.bot;


///*
//public class MesHandler  extends BaseHandler {
//    @Override
//    public void handle(Update update){
//        Message message = update.message();
//        User from = message.from();
//        super.update = update;
//        super.curUser = getUserOrCreate(from);
//        String text = message.text();
//
//        if (text.equals("/start")){
//            SendMessage sendMessage = new SendMessage(from.id(),"Assalomu Alekum" + curUser.getFirstname() + " " + curUser.getLastname() + " botga Xush kelibsiz");
//            bot.execute(sendMessage);
//
//  if(Objects.isNull( curUser.getPhoneNumber()) ||  curUser.getPhoneNumber().isEmpty()){
//                enterPhoneNumber();
//            }else {
//                mainMenu();
//            }
//
//        }
//        else{
//            String baseStateString = curUser.getBaseState();
//        BaseState baseState = BaseState.valueOf(baseStateString);
//        if (Objects.equals(baseState,BaseState.MAIN_STATE)){
//            mainState();
//        }
//        else if(Objects.equals(baseState,BaseState.ROMANTIK_STATE)){
//            romantikState();
//        }
//        else if(Objects.equals(baseState,BaseState.BADIY_STATE)){
//            badiyState();
//        }
//        else if(Objects.equals(baseState,BaseState.FANTASTIK_STATE)){
//            fantastikState();
//        }
//
//        }
//    }
//
//    private void fantastikState() {
//
//    }
//
//    private void badiyState() {
//
//    }
//
//    private void romantikState() {
//
//    }
//
//    private void mainState() {
//        String stateStr = curUser.getState();
//        MainState state =  MainState.valueOf(stateStr);
//        switch (state) {
//            case REGISTER -> {
//            Message message = update.message();
//            Contact contact = message.contact();
//            if (contact != null) {
//                String phoneNumber = contact.phoneNumber();
//                curUser.setPhoneNumber(phoneNumber);
//                userService.save(curUser);
//                mainMenu();
//            }
//            else {
//                incorrectData("Phone Number");
//            }
//            }
//
//        }
//
//    }
//
//    private void mainMenu() {
//        SendMessage sendMessage = messageMaker.mainMenu(curUser);
//        bot.execute(sendMessage);
//        curUser.setState(BaseState.MAIN_STATE.name());
//        userService.save(curUser);
//    }
//
//    private void enterPhoneNumber() {
//        SendMessage sendMessage = messageMaker.enterPhoneNumber(curUser);
//        bot.execute(sendMessage);
//    }
//    private void incorrectData(String data  ) {
//        bot.execute(new SendMessage(curUser.getId(),"You entered incorrect " + data));
//
//    }
//
//
//
//}
//
//*/

public class MesHandler  extends BaseHandler {
    @Override
    public void handle(Update update){
        Message message = update.message();
        User from = message.from();
        super.update = update;
        super.curUser = getUserOrCreate(from);
        String text = message.text();

        if (text.equals("/start")){
            SendMessage sendMessage = new SendMessage(from.id(),"Assalomu Alekum" + curUser.getFirstname() + " " + curUser.getLastname() + " botga Xush kelibsiz");
            bot.execute(sendMessage);

            // Telefon raqamini tekshirib, agar yo'qsa telefon raqamini kiritishni so'raymiz
            if (Objects.isNull(curUser.getPhoneNumber()) || curUser.getPhoneNumber().isEmpty()){
                enterPhoneNumber();
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
            else if(Objects.equals(baseState, BaseState.ROMANTIK_STATE)){
                romantikState();
            }
            else if(Objects.equals(baseState, BaseState.BADIY_STATE)){
                badiyState();
            }
            else if(Objects.equals(baseState, BaseState.FANTASTIK_STATE)){
                fantastikState();
            }
        }
    }

    private void fantastikState() {
        // Bu yerga fantastik holatini bajarish funksiyasini yozing
    }

    private void badiyState() {
        // Bu yerga badiy holatini bajarish funksiyasini yozing
    }

    private void romantikState() {
        // Bu yerga romantik holatini bajarish funksiyasini yozing
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
