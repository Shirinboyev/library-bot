package uz.pdp.backend.service.userService;

import com.pengrad.telegrambot.model.User;
import uz.pdp.backend.model.book.Book;
import uz.pdp.backend.model.user.MyUser;

import java.util.List;
import java.util.Objects;

public interface UserService {
    void create(User user);
void search(User user);

    void save(MyUser myUser);

    MyUser get(Long id);
}
