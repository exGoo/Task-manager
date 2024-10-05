package app.dao;

import app.model.User;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private EntityManagerFactory emf;

    @Override
    @Transactional
    public User get(String nickName) {
        return emf.createEntityManager().find(User.class, nickName);
    }

    @Override
    public void persist(User people) {
        emf.createEntityManager().persist(people);
    }

    @Override
    @Transactional
    public void update(User people) {
        emf.createEntityManager().merge(people);
    }

    @Override
    @Transactional
    public void remove(User people) {
        emf.createEntityManager().remove(people);
    }

    @Override
    @Transactional()
    public List<User> getAll() {
        TypedQuery<User> query = emf.createEntityManager().createQuery("from User", User.class);
        List<User> users = query.getResultList();
        return users;
    }
}
