package uz.pdp.backend.service.userService;

import com.pengrad.telegrambot.model.User;
import uz.pdp.backend.model.book.Book;
import uz.pdp.backend.model.user.MyUser;
import uz.pdp.backend.repository.FileWriterAndLoader;
import uz.pdp.backend.statics.PathConstants;

import java.util.List;
import java.util.Objects;

public class UserServiceImp  implements UserService , PathConstants{

    FileWriterAndLoader<MyUser> writerAndLoader;

    public UserServiceImp() {
        this.writerAndLoader = new FileWriterAndLoader<>(USERS_PATH);
    }

    public void create(User user) {
        ;
    }

    @Override
    public void search(User user) {

    }

    @Override
    public void save(MyUser myUser) {
        List<MyUser> users = writerAndLoader.load(MyUser.class);
        for (int i = 0; i < users.size(); i++) {
            MyUser cur = users.get(i);
            if (Objects.equals(cur.getId(),myUser.getId())){
                users.set(i,myUser);
                writerAndLoader.write(users);
                return;
            }
        }
        users.add(myUser);
        writerAndLoader.write(users);
        return;
    }

    @Override
    public MyUser get(Long id) {
        List<MyUser> users = writerAndLoader.load(MyUser.class);
        for (int i = 0; i < users.size(); i++) {
            MyUser cur = users.get(i);
            if (Objects.equals(cur.getId(),id)){
                return cur;
            }
        }
        return null;
    }

    public static void main(String[] args) {
    }
}
