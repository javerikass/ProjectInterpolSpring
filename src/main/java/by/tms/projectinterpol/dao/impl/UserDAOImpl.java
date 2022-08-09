package by.tms.projectinterpol.dao.impl;

import by.tms.projectinterpol.dao.UserDAO;
import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.entity.User_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UserDAOImpl extends BaseDAOImpl<Long, User> implements UserDAO {

    @Override
    public Optional<User> findUserByUsername(String username) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root).where(cb.equal(root.get(User_.username), username));
        return session.createQuery(criteria).getResultStream().findFirst();
    }
}
