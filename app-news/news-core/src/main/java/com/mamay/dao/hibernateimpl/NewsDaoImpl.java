package com.mamay.dao.hibernateimpl;

import com.mamay.dao.NewsDao;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.DaoException;
import com.mamay.util.QueryHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<NewsEntity> loadAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<NewsEntity>) session.createCriteria(NewsEntity.class).list();
    }

    @Override
    public NewsEntity loadById(Long id) throws DaoException {
        Session session = this.sessionFactory.getCurrentSession();
        return (NewsEntity) session.get(NewsEntity.class, id);
    }

    @Override
    public NewsPageItem<NewsEntity> loadByFilter(NewsSearchCriteria searchCriteria,
                                                 Integer pageNumber, int newsPerPage) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Long authorId = searchCriteria.getAuthorId();
        boolean isAuthorNull = authorId == null
                || Long.compare(authorId, 0) == 0;
        String strQuery = QueryHelper.loadOrderedList(searchCriteria);
        Query query = session.createSQLQuery(strQuery).addScalar("news_id",
                LongType.INSTANCE);
        int idx = 0;
        if (!isAuthorNull) {
            query.setParameter(idx++, authorId);
        }
        int firstIdx = (pageNumber - 1) * newsPerPage;
        query.setFirstResult(firstIdx);
        query.setMaxResults(newsPerPage);
        List<Long> idList = (List<Long>) query.list();
        List<NewsEntity> newsList = new ArrayList<NewsEntity>();
        if (idList == null || idList.isEmpty()) {
            return new NewsPageItem<>(newsList, pageNumber, newsPerPage);
        }
        for (Long obj : idList) {
            NewsEntity newsEntity = (NewsEntity) session.get(NewsEntity.class, obj);
            newsList.add(newsEntity);
        }
        return new NewsPageItem<>(newsList, pageNumber, newsPerPage);
    }

    @Override
    public Long create(NewsEntity entity) {
        Session session = this.sessionFactory.getCurrentSession();
        entity.setModificationDate(LocalDateTime.now());
        return (Long) session.save(entity);
    }

    @Override
    public void update(NewsEntity entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public Long loadNextId(NewsSearchCriteria searchCriteria, Long id) throws DaoException {
        Long authorId = searchCriteria.getAuthorId();
        boolean isAuthorNull = authorId == null || authorId == 0;

        Session session = this.sessionFactory.getCurrentSession();
        String strQuery = QueryHelper.loadOffsetId(searchCriteria, 1);
        Query query = session.createSQLQuery(strQuery).addScalar("news_id", LongType.INSTANCE);
        int idx = 0;
        if (!isAuthorNull) {
            query.setParameter(idx++, authorId);
        }
        query.setParameter(idx++, id);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long loadPreviousId(NewsSearchCriteria searchCriteria, Long id) throws DaoException {
        Long authorId = searchCriteria.getAuthorId();
        boolean isAuthorNull = authorId == null || authorId == 0;

        Session session = this.sessionFactory.getCurrentSession();
        String strQuery = QueryHelper.loadOffsetId(searchCriteria, -1);
        Query query = session.createSQLQuery(strQuery).addScalar("news_id",
                LongType.INSTANCE);
        int idx = 0;
        if (!isAuthorNull) {
            query.setParameter(idx++, authorId);
        }
        query.setParameter(idx++, id);
        return (Long) query.uniqueResult();
    }

    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        NewsEntity entity = (NewsEntity) session.get(NewsEntity.class, id);
        if (entity != null) {
            session.delete(entity);
        }
    }

    @Override
    public void deleteList(List<Long> newsIdList) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("News.deleteList");
        query.setParameterList("idList", newsIdList);
        query.executeUpdate();
    }
}
