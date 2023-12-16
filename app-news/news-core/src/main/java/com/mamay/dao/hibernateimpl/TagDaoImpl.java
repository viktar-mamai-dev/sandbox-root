package com.mamay.dao.hibernateimpl;

import com.mamay.dao.TagDao;
import com.mamay.entity.TagEntity;
import com.mamay.exception.NewsException;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl implements TagDao {

  @Autowired private SessionFactory sessionFactory;

  @Override
  public List<TagEntity> loadAll() {
    Session session = this.sessionFactory.getCurrentSession();
    CriteriaQuery<TagEntity> criteria = session.getCriteriaBuilder().createQuery(TagEntity.class);
    return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
  }

  @Override
  public TagEntity loadById(Long id) {
    return this.sessionFactory.getCurrentSession().get(TagEntity.class, id);
  }

  @Override
  public Long create(TagEntity entity) {
    return (Long) this.sessionFactory.getCurrentSession().save(entity);
  }

  @Override
  public void update(TagEntity entity) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
  }

  @Override
  public void delete(Long id) {
    Session session = this.sessionFactory.getCurrentSession();
    TagEntity entity = session.get(TagEntity.class, id);
    if (entity != null) {
      session.delete(entity);
    }
  }

  @Override
  public List<TagEntity> loadByNewsId(Long newsId) throws NewsException {
    return null;
  }
}
