package uz.pdp.backend.service.userService;

import com.pengrad.telegrambot.model.User;
import uz.pdp.backend.model.book.Book;

public interface UserService {
    void create(User user);
void search(User user);
}
