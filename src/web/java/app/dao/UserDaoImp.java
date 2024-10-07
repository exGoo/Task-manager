package app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final EntityManagerFactory emf;

    @Autowired
    public UserDaoImp(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    @Transactional
    public User get(String nickName) {
        return emf.createEntityManager().find(User.class, nickName);
    }

    @Override
    @Transactional
    public void persist(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    @Transactional
    public void update(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    @Transactional
    public void remove(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.contains(user) ? user : em.merge(user));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    @Transactional
    public List<User> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("from User", User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}