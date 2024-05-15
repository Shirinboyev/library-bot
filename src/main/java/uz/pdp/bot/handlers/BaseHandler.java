package uz.pdp.bot.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.DeleteMessage;
import uz.pdp.backend.baen.BeanController;
import uz.pdp.bot.maker.MessageMaker;
import uz.pdp.backend.model.user.MyUser;
import uz.pdp.backend.service.bookService.BadiyService;
import uz.pdp.backend.service.bookService.BookService;
import uz.pdp.backend.service.bookService.FantastikService;
import uz.pdp.backend.service.bookService.RomantikService;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.bot.states.child.MainState;

import static uz.pdp.bot.myBot.MyBot.BOT_TOKEN;


public abstract class BaseHandler  {
    protected TelegramBot bot;
    protected MyUser curUser;
    protected Update update;
    protected UserService userService;
    protected RomantikService romantikService;
    protected BadiyService badiyService;
    protected FantastikService fantastikService;
    protected BookService bookService;
    protected static MessageMaker messageMaker;

    public BaseHandler() {
        this.bot = new TelegramBot(BOT_TOKEN);
        this.userService = BeanController.userServiceByThreadLocal.get();
        this.bookService = BeanController.bookServiceByThreadLocal.get();
        this.romantikService = BeanController.romantikServiceByThreadLocal.get();
        this.badiyService = BeanController.badiyServiceByThreadLocal.get();
        this.fantastikService = BeanController.fantastikServiceByThreadLocal.get();
        this.messageMaker = BeanController.messageMakerByThreadLocal.get();
    }

    public abstract void handle(Update update);


    protected MyUser getUserOrCreate(User from){
        MyUser myUser = userService.get(from.id());
        if (myUser==null){
            myUser = MyUser.builder()
                    .id(from.id())
                    .username(from.username())
                    .baseState(MainState.MAIN_MENU.name())
                    .state(MainState.REGISTER.name())
                    .firstname(from.firstName())
                    .lastname(from.lastName())
                    .build();
            userService.save(myUser);
        }
        return myUser;
    }

    protected void deleteMessage(int messageId){
        DeleteMessage deleteMessage = new DeleteMessage(curUser.getId(), messageId);
        bot.execute(deleteMessage);
    }
}
