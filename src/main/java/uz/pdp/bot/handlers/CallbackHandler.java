package uz.pdp.bot.handlers;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.bot.states.BaseState;

import java.util.Objects;


public class CallbackHandler extends BaseHandler {

    @Override
    public void handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        User from = callbackQuery.from();
        super.curUser = getUserOrCreate(from);
        super.update = update;
        String baseStateString = curUser.getBaseState();
        BaseState baseState = BaseState.valueOf(baseStateString);
        if (Objects.equals(baseState,BaseState.MAIN_STATE)){
            mainSate();
        }else if (Objects.equals(baseState,BaseState.ROMANTIK_STATE)){
            romantik();
        }else if (Objects.equals(baseState,BaseState.BADIY_STATE)){
            badiy();
        } else if (Objects.equals(baseState,BaseState.FANTASTIK_STATE)){
            fantastik();
        }

    }

    private void fantastik() {

    }

    private void badiy() {

    }

    private void romantik() {

    }

    private void mainSate() {
        curUser.setState(BaseState.MAIN_STATE.name());
        userService.save(curUser);
        SendMessage sendMessage = messageMaker.mainMenu(curUser);
        bot.execute(sendMessage);
    }


}
