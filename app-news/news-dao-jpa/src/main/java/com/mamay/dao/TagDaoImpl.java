package com.mamay.dao;

import com.mamay.entity.TagEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl implements TagDao {
  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<TagEntity> loadAll() {
    return entityManager
        .createQuery("from TagEntity t order by t.name asc", TagEntity.class)
        .getResultList();
  }

  @Override
  public TagEntity loadById(Long id) {
    return entityManager.find(TagEntity.class, id);
  }

  @Override
  public Long create(TagEntity entity) {
    entityManager.persist(entity);
    entityManager.flush();
    return entity.getId();
  }

  @Override
  public void update(TagEntity entity) {
    entityManager.merge(entity);
  }

  @Override
  public void delete(Long id) {
    TagEntity entity = loadById(id);
    if (entity != null) {
      entityManager.remove(entity);
    }
  }

  @Override
  public List<TagEntity> loadByNewsId(Long newsId) {
    return null;
  }
}
