package core.repositories;

import core.entities.Role;
import io.vavr.control.Option;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class RoleRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Option<Role> findByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM Role WHERE name = :name");
        query.setParameter("name", name);
        return Option.of((Role) query.uniqueResult());
    }

    public Option<List<Role>> findByNames(List<String> names) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM Role WHERE name in (:names)");
        query.setParameter("names", names);
        return Option.of((List<Role>) query.list());
    }
}
