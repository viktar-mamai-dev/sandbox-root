package com.mamay.dao.jpaimpl;

import com.mamay.dao.NewsDao;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import com.mamay.util.QueryHelper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsEntity> loadAll() throws NewsException {
        Query query = entityManager.createQuery("from NewsEntity n");
        List<NewsEntity> newsList = query.getResultList();
        return newsList;
    }

    @Override
    public NewsEntity loadById(Long id) throws NewsException {
        NewsEntity newsEntity = entityManager.find(NewsEntity.class, id);
        return newsEntity;
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
    public Long create(NewsEntity entity) throws NewsException {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(NewsEntity entity) throws NewsException {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) throws NewsException {
        NewsEntity entity = entityManager.find(NewsEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public void deleteList(List<Long> newsIdList) throws NewsException {
        String newsIdStr = QueryHelper.convertListToString(newsIdList);
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM NewsEntity n WHERE n.id IN (")
                .append(newsIdStr).append(")");
        Query query = entityManager.createQuery(builder.toString());
        query.executeUpdate();
    }

}
