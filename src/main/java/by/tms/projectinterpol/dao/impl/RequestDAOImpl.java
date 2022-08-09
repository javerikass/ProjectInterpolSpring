package by.tms.projectinterpol.dao.impl;

import by.tms.projectinterpol.dao.RequestDAO;
import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Requests;
import by.tms.projectinterpol.entity.Requests_;
import by.tms.projectinterpol.entity.Status;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RequestDAOImpl extends BaseDAOImpl<Long, Requests> implements RequestDAO {

    @Override
    public List<Requests> findRequestByName(String firstName, String lastName) {

        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Requests> criteria = cb.createQuery(Requests.class);
        Root<Requests> root = criteria.from(Requests.class);
        criteria.select(root).where
                (cb.and(cb.equal(root.get(Requests_.firstName), firstName),
                        cb.equal(root.get(Requests_.lastName), lastName)));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public List<Requests> findRequestByAge(Integer age) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Requests> criteria = cb.createQuery(Requests.class);
        Root<Requests> root = criteria.from(Requests.class);
        criteria.select(root).where(cb.equal(root.get(Requests_.age), age));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public List<Requests> findRequestByNationality(String nationality) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Requests> criteria = cb.createQuery(Requests.class);
        Root<Requests> root = criteria.from(Requests.class);
        criteria.select(root).where(cb.equal(root.get(Requests_.nationality), nationality));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public List<Requests> findRequestsByGender(Gender gender) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Requests> criteria = cb.createQuery(Requests.class);
        Root<Requests> root = criteria.from(Requests.class);
        criteria.select(root).where(cb.equal(root.get(Requests_.gender), gender));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public long findAmountRequestByStatusAndApproval(Status status, boolean approved) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("select count(*) from Requests r where r.status=:status and r.approved=:approved");
        query.setParameter("status", status);
        query.setParameter("approved", approved);
        return (long) query.getSingleResult();
    }

    @Override
    public List<Requests> findRequestsByStatusAndApprovalWithLimitAndOffset(Status status, boolean approved, String limit, String offset) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Requests> criteria = cb.createQuery(Requests.class);
        Root<Requests> root = criteria.from(Requests.class);
        criteria.select(root).where(cb.and(
                cb.equal(root.get(Requests_.status), status),
                cb.equal(root.get(Requests_.approved), approved)));
        return session.createQuery(criteria).setFirstResult(Integer.parseInt(offset)).setMaxResults(Integer.parseInt(limit)).getResultList();
    }

    @Override
    public List<Requests> findRequestsByApproval(boolean approved) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Requests> criteria = cb.createQuery(Requests.class);
        Root<Requests> root = criteria.from(Requests.class);
        criteria.select(root).where(cb.equal(root.get(Requests_.approved), approved));
        return session.createQuery(criteria).getResultList();
    }
}
