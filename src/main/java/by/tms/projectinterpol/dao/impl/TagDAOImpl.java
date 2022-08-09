package by.tms.projectinterpol.dao.impl;

import by.tms.projectinterpol.dao.TagDAO;
import by.tms.projectinterpol.entity.Tag;
import by.tms.projectinterpol.entity.Tag_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class TagDAOImpl extends BaseDAOImpl<Long, Tag> implements TagDAO {

    @Override
    public Optional<Tag> findTagByName(String tagName) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = cb.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);
        criteria.select(root).where(cb.equal(root.get(Tag_.tag), tagName));
        return session.createQuery(criteria).getResultList().stream().findFirst();
    }
}
