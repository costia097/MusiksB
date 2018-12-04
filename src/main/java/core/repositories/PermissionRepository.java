package core.repositories;

import core.entities.Permission;
import io.vavr.control.Option;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class PermissionRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Option<Permission> findByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM Permission WHERE name = :name ");
        query.setParameter("name", name);
        return Option.of((Permission) query.uniqueResult());
    }

    public Option<List<Permission>> findByNames(List<String> names) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM Permission WHERE name in (:names)");
        query.setParameter("names", names);
        return Option.of((List<Permission>) query.list());
    }
}
