package com.mamay.dao;

import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.util.QueryHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

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

  @Override
  public NewsPageItem<NewsEntity> loadByFilter(
      NewsSearchCriteria searchCriteria, Integer pageNumber, int newsPerPage) {
    Long authorId = searchCriteria.getAuthorId();
    boolean isAuthorNull = authorId == null || authorId == 0;
    String strQuery = QueryHelper.loadOrderedList(searchCriteria);
    Query query = entityManager.createNativeQuery(strQuery, NewsEntity.class);
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
  public Long loadNextId(NewsSearchCriteria searchCriteria, Long newsId) {
    Long authorId = searchCriteria.getAuthorId();
    boolean isAuthorNull = authorId == null || authorId == 0;

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
  public Long loadPreviousId(NewsSearchCriteria searchCriteria, Long newsId) {
    Long authorId = searchCriteria.getAuthorId();
    boolean isAuthorNull = authorId == null || authorId == 0;

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
