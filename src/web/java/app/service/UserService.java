package app.service;

import app.model.User;
import java.util.List;

public interface UserService {

    User get(String nickName);

    void add(User people);

    void update(User people);

    void remove(String nickName);

    List<User> getAll();
}
