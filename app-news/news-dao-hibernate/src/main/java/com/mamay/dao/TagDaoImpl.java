package com.mamay.dao;

import com.mamay.entity.TagEntity;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl extends BaseDaoImpl implements TagDao {

  @Override
  public List<TagEntity> loadAll() {
    Session session = getSession();
    CriteriaQuery<TagEntity> criteria = session.getCriteriaBuilder().createQuery(TagEntity.class);
    return session.createQuery(criteria).getResultList();
  }

  @Override
  public TagEntity loadById(Long id) {
    return getSession().get(TagEntity.class, id);
  }

  @Override
  public Long create(TagEntity entity) {
    return (Long) getSession().save(entity);
  }

  @Override
  public void update(TagEntity entity) {
    getSession().saveOrUpdate(entity);
  }

  @Override
  public void delete(Long id) {
    Session session = getSession();
    TagEntity entity = session.get(TagEntity.class, id);
    if (entity != null) {
      session.delete(entity);
    }
  }

  @Override
  public List<TagEntity> loadByNewsId(Long newsId) {
    return null;
  }
}
