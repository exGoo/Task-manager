package app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final EntityManagerFactory emf;

    @Autowired
    public UserDaoImp(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void add(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public User get(String nickname) {
        return emf.createEntityManager().find(User.class, nickname);
    }

    @Override
    public void update(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public void remove(String nickname) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, nickname);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();
    }

    @Override
    public List<User> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("from User", User.class);
        return query.getResultList();
    }
}