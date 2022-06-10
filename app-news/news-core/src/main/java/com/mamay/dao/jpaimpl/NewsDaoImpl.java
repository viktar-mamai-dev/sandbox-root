package com.mamay.dao.jpaimpl;

import com.mamay.dao.NewsDao;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.AuthorEntity;
import com.mamay.entity.NewsEntity;
import com.mamay.entity.TagEntity;
import com.mamay.exception.DaoException;
import com.mamay.util.QueryHelper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsEntity> loadAll() throws DaoException {
        Query query = entityManager.createQuery("from NewsEntity n");
        List<NewsEntity> newsList = query.getResultList();
        return newsList;
    }

    @Override
    public NewsEntity loadById(Long id) throws DaoException {
        NewsEntity newsEntity = entityManager.find(NewsEntity.class, id);
        return newsEntity;
    }

    @Override
    public NewsPageItem<NewsEntity> loadByFilter(NewsSearchCriteria searchCriteria,
                                                 Integer pageNumber, int newsPerPage) throws DaoException {
        Long authorId = searchCriteria.getAuthorId();
        boolean isAuthorNull = authorId == null || authorId == 0;
        String strQuery = QueryHelper.loadOrderedList(searchCriteria);
        Query query = entityManager.createNativeQuery(strQuery);
        int idx = 1;
        if (!isAuthorNull) {
            query.setParameter(idx++, authorId);
        }
        int firstIdx = (pageNumber - 1) * newsPerPage;
        query.setFirstResult(firstIdx);
        query.setMaxResults(newsPerPage);
        List<Object> idList = (List<Object>) query.getResultList();

        List<NewsEntity> newsList = new ArrayList<NewsEntity>();
        if (idList == null || idList.isEmpty()) {
            return new NewsPageItem<>(newsList, pageNumber, newsPerPage);
        }
        for (Object row : idList) {
            long id = findIdInRow(row);
            NewsEntity newsEntity = entityManager.find(NewsEntity.class, id);
            newsList.add(newsEntity);
        }
        return new NewsPageItem<>(newsList, pageNumber, newsPerPage);
    }

    private long findIdInRow(Object row) {
        long l;
        if (row instanceof Number) {
            l = ((Number) row).longValue();
        } else {
            // we have two columns
            Object[] objArray = (Object[]) row;
            l = ((Number) (objArray[0])).longValue();
        }
        return l;
    }

    @Override
    public Long create(NewsEntity entity) throws DaoException {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(NewsEntity entity) throws DaoException {
        entityManager.merge(entity);
    }

    @Override
    public Long loadNextId(NewsSearchCriteria searchCriteria, Long newsId)
            throws DaoException {
        Long authorId = searchCriteria.getAuthorId();
        boolean isAuthorNull = authorId == null
                || Long.compare(authorId, 0) == 0;

        String strQuery = QueryHelper.loadOffsetId(searchCriteria, 1);
        Query query = entityManager.createNativeQuery(strQuery);
        int idx = 1;
        if (!isAuthorNull) {
            query.setParameter(idx++, authorId);
        }
        query.setParameter(idx++, newsId);
        List<BigDecimal> idList = query.getResultList();
        Long nextId = null;
        if (idList != null && idList.size() > 0) {
            nextId = idList.get(0).longValue();
        }
        return nextId;
    }

    @Override
    public Long loadPreviousId(NewsSearchCriteria searchCriteria, Long newsId)
            throws DaoException {
        Long authorId = searchCriteria.getAuthorId();
        boolean isAuthorNull = authorId == null
                || Long.compare(authorId, 0) == 0;

        String strQuery = QueryHelper.loadOffsetId(searchCriteria, -1);
        Query query = entityManager.createNativeQuery(strQuery);
        int idx = 1;
        if (!isAuthorNull) {
            query.setParameter(idx++, authorId);
        }
        query.setParameter(idx++, newsId);
        Long previousId = null;
        List<BigDecimal> idList = query.getResultList();
        if (idList != null && idList.size() > 0) {
            previousId = idList.get(0).longValue();
        }
        return previousId;
    }

    @Override
    public void delete(Long id) throws DaoException {
        NewsEntity entity = entityManager.find(NewsEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public void deleteList(List<Long> newsIdList) throws DaoException {
        String newsIdStr = QueryHelper.convertListToString(newsIdList);
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM NewsEntity n WHERE n.id IN (")
                .append(newsIdStr).append(")");
        Query query = entityManager.createQuery(builder.toString());
        query.executeUpdate();
    }

}
