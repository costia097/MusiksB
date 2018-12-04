package core.repositories;

import core.entities.User;
import io.vavr.control.Option;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

}
