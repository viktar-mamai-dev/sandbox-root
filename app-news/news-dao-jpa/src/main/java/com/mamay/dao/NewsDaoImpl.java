package com.mamay.dao;

import com.mamay.entity.NewsEntity;
import com.mamay.util.QueryHelper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<NewsEntity> loadAll() {
    TypedQuery<NewsEntity> query = entityManager.createQuery("from NewsEntity n", NewsEntity.class);
    return query.getResultList();
  }

  @Override
  public NewsEntity loadById(Long id) {
    return entityManager.find(NewsEntity.class, id);
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
  public Long create(NewsEntity entity) {
    entityManager.persist(entity);
    entityManager.flush();
    return entity.getId();
  }

  @Override
  public void update(NewsEntity entity) {
    entityManager.merge(entity);
  }

  @Override
  public void delete(Long id) {
    NewsEntity entity = entityManager.find(NewsEntity.class, id);
    if (entity != null) {
      entityManager.remove(entity);
    }
  }

  @Override
  public void deleteList(List<Long> newsIdList) {
    String newsIdStr = QueryHelper.convertListToString(newsIdList);
    String builder = "DELETE FROM NewsEntity n WHERE n.id IN (" + newsIdStr + ")";
    Query query = entityManager.createQuery(builder);
    query.executeUpdate();
  }
}
