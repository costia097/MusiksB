package core.repositories;

import core.entities.User;
import io.vavr.control.Option;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@SuppressWarnings("unchecked")
public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Option<User> findById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM User WHERE id = :id");
        query.setParameter("id", id);
        return Option.of((User) query.uniqueResult());
    }

    public Option<User> findByLogin(String login) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM User WHERE login = :login");
        query.setParameter("login", login);
        return Option.of((User) query.uniqueResult());
    }

    public Option<User> findByEmail(String email) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM User WHERE login = :email");
        query.setParameter("email", email);
        return Option.of((User) query.uniqueResult());
    }

    public Option<User> findByUuid(UUID uuid) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM User WHERE uuid = :uuid");
        query.setParameter("uuid", uuid);
        return Option.of((User) query.uniqueResult());
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

}
