package com.mamay.dao.hibernateimpl;

import com.mamay.dao.CommentDao;
import com.mamay.entity.CommentEntity;
import com.mamay.exception.NewsException;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl implements CommentDao {

  @Autowired private SessionFactory sessionFactory;

  @Override
  public List<CommentEntity> loadAll() {
    Session session = this.sessionFactory.getCurrentSession();
    CriteriaQuery<CommentEntity> criteria =
        session.getCriteriaBuilder().createQuery(CommentEntity.class);
    return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
  }

  @Override
  public CommentEntity loadById(Long id) {
    Session session = this.sessionFactory.getCurrentSession();
    return session.get(CommentEntity.class, id);
  }

  @Override
  public Long create(CommentEntity entity) {
    Session session = this.sessionFactory.getCurrentSession();
    entity.setCreationDate(LocalDateTime.now());
    return (Long) session.save(entity);
  }

  @Override
  public void update(CommentEntity entity) {
    Session session = this.sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
  }

  @Override
  public void delete(Long id) {
    Session session = this.sessionFactory.getCurrentSession();
    CommentEntity entity = session.get(CommentEntity.class, id);
    if (entity != null) {
      session.delete(entity);
    }
  }

  @Override
  public List<CommentEntity> loadByNewsId(Long newsId) throws NewsException {
    return null;
  }
}
