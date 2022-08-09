package by.tms.projectinterpol.dao.impl;

import by.tms.projectinterpol.dao.NewsDAO;
import by.tms.projectinterpol.entity.News;
import by.tms.projectinterpol.entity.News_;
import by.tms.projectinterpol.entity.Tag;
import by.tms.projectinterpol.entity.Tag_;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Repository
@Slf4j
public class NewsDAOImpl extends BaseDAOImpl<Long, News> implements NewsDAO {

    @Override
    public List<News> findNewsByTag(String tag) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<News> criteria = cb.createQuery(News.class);
        Root<News> root = criteria.from(News.class);
        ListJoin<News, Tag> tagListJoin = root.join(News_.tags);
        criteria.select(root).where(cb.equal(tagListJoin.get(Tag_.TAG), tag));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public List<News> findNewsByPublicationDate(LocalDate localDate) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<News> criteria = cb.createQuery(News.class);
        Root<News> root = criteria.from(News.class);
        criteria.select(root).where(cb.equal(root.get(News_.publicationDate), localDate));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public long findNewsAmount() {
        Session session = getSessionFactory().getCurrentSession();
        Object singleResult = session.createQuery("select count(*) from News").getSingleResult();
        return (long) singleResult;
    }

    @Override
    public List<News> findAllNewsWithOffset(String offset) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<News> criteria = cb.createQuery(News.class);
        Root<News> root = criteria.from(News.class);
        criteria.select(root);
        return session.createQuery(criteria).setFirstResult(Integer.parseInt(offset)).setMaxResults(5).getResultList();
    }
}
