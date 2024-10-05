package app.dao;

import app.model.User;

import java.util.List;

public interface UserDao {

    public User get(String nickName);

    public void persist(User people);

    public void update(User people);

    public void remove(User people);

    public List<User> getAll();
}
