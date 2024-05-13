package uz.pdp.backend.service.userService;

import com.pengrad.telegrambot.model.User;
import uz.pdp.backend.dataBase.DataBase;
import uz.pdp.backend.model.book.Book;
import uz.pdp.backend.model.user.MyUser;

public class UserServiceImp implements UserService{
    private final String FILE_URL = "src/main/resources/users.txt";
    private final String FILE_NAME = "users.txt";
    private static DataBase<MyUser> dataBase = new DataBase<>("src/main/resources/users.txt");
    @Override
    public void create(User user) {
        dataBase.SAVE(user,FILE_URL);
    }

    public static void main(String[] args) {
        MyUser myUser = new MyUser("daf","dcd","cdcd,","131","vrwv");
    }
}
