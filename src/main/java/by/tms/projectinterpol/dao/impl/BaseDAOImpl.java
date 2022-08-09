package by.tms.projectinterpol.dao.impl;

import by.tms.projectinterpol.dao.BaseDAO;
import by.tms.projectinterpol.entity.BaseEntity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Getter
@Repository
@Slf4j
public abstract class BaseDAOImpl<PK extends Serializable, E extends BaseEntity<PK>> implements BaseDAO<PK, E> {

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<E> clazz;

    protected BaseDAOImpl() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        clazz = (Class<E>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
    }

    @Override
    public Optional<E> findById(PK id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public PK save(E entity) {
        Session session = sessionFactory.getCurrentSession();
        PK id = (PK) session.save(entity);
        log.debug("{} is saved with {} id", entity.toString(), id);
        return id;
    }

    @Override
    public void update(E entity) {
        Session session = sessionFactory.getCurrentSession();
        Object merge = session.merge(entity);
        session.flush();
        log.info("{} is updated", merge.toString());
    }

    @Override
    public void delete(E entity) {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        session.delete(entity);
        session.flush();
        log.info("{} is deleted", entity.toString());
    }

    @Override
    public List<E> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(clazz);
        Root<E> root = criteria.from(clazz);
        return session.createQuery(criteria.select(root)).getResultList();
    }
}
