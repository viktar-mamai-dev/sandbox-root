package com.mamay.dao;

import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.util.QueryHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsDaoImpl extends BaseDaoImpl implements NewsDao {
    @Override
    public List<NewsEntity> loadAll() {
        Session session = getSession();
        return (List<NewsEntity>) session.createCriteria(NewsEntity.class).list();
    }

    @Override
    public NewsEntity loadById(Long id) {
        Session session = getSession();
        return session.get(NewsEntity.class, id);
    }

    @Override
    public NewsPageItem<NewsEntity> loadByFilter(NewsSearchCriteria searchCriteria,
                                                 Integer pageNumber, int newsPerPage) {
        Session session = getSession();
        Long authorId = searchCriteria.getAuthorId();
        boolean isAuthorNull = authorId == null || authorId == 0;
        String strQuery = QueryHelper.loadOrderedList(searchCriteria);
        NativeQuery<Long> query = session.createSQLQuery(strQuery).addScalar("news_id", LongType.INSTANCE);
        int idx = 0;
        if (!isAuthorNull) {
            query.setParameter(idx++, authorId);
        }
        int firstIdx = (pageNumber - 1) * newsPerPage;
        query.setFirstResult(firstIdx);
        query.setMaxResults(newsPerPage);
        List<Long> idList = query.list();
        List<NewsEntity> newsList = new ArrayList<NewsEntity>();
        if (idList == null || idList.isEmpty()) {
            return new NewsPageItem<>(newsList, pageNumber, newsPerPage);
        }
        for (Long obj : idList) {
            NewsEntity newsEntity = session.get(NewsEntity.class, obj);
            newsList.add(newsEntity);
        }
        return new NewsPageItem<>(newsList, pageNumber, newsPerPage);
    }

    @Override
    public Long create(NewsEntity entity) {
        Session session = getSession();
        entity.setModificationDate(LocalDateTime.now());
        return (Long) session.save(entity);
    }

    @Override
    public void update(NewsEntity entity) {
        Session session = getSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public Long loadNextId(NewsSearchCriteria searchCriteria, Long id) {
        Long authorId = searchCriteria.getAuthorId();
        boolean isAuthorNull = authorId == null || authorId == 0;

        Session session = getSession();
        String strQuery = QueryHelper.loadOffsetId(searchCriteria, 1);
        NativeQuery<Long> query = session.createSQLQuery(strQuery).addScalar("news_id", LongType.INSTANCE);
        int idx = 0;
        if (!isAuthorNull) {
            query.setParameter(idx++, authorId);
        }
        query.setParameter(idx++, id);
        return query.uniqueResult();
    }

    @Override
    public Long loadPreviousId(NewsSearchCriteria searchCriteria, Long id) {
        Long authorId = searchCriteria.getAuthorId();
        boolean isAuthorNull = authorId == null || authorId == 0;

        Session session = getSession();
        String strQuery = QueryHelper.loadOffsetId(searchCriteria, -1);
        NativeQuery<Long> query = session.createSQLQuery(strQuery).addScalar("news_id", LongType.INSTANCE);
        int idx = 0;
        if (!isAuthorNull) {
            query.setParameter(idx++, authorId);
        }
        query.setParameter(idx++, id);
        return (Long) query.uniqueResult();
    }

    @Override
    public void delete(Long id) {
        Session session = getSession();
        NewsEntity entity = session.get(NewsEntity.class, id);
        if (entity != null) {
            session.delete(entity);
        }
    }

    @Override
    public void deleteList(List<Long> newsIdList) {
        Session session = getSession();
        Query query = session.getNamedQuery("News.deleteList");
        query.setParameterList("idList", newsIdList);
        query.executeUpdate();
    }
}
