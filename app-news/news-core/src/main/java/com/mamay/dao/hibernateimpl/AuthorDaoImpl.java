package com.mamay.dao.hibernateimpl;

import com.mamay.dao.AuthorDao;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.NewsException;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDaoImpl implements AuthorDao {

  @Autowired private SessionFactory sessionFactory;

  @Override
  public List<AuthorEntity> loadAll() {
    Session session = this.sessionFactory.getCurrentSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<AuthorEntity> criteria = builder.createQuery(AuthorEntity.class);
    Root<AuthorEntity> root = criteria.from(AuthorEntity.class);
    criteria.select(root).orderBy(builder.asc(root.get("name")));
    return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
  }

  @Override
  public List<AuthorEntity> loadActiveAuthors() {
    Session session = this.sessionFactory.getCurrentSession();
    return session.createNamedQuery("Author.loadActiveAuthors", AuthorEntity.class).getResultList();
  }

  @Override
  public AuthorEntity loadById(Long id) {
    Session session = this.sessionFactory.getCurrentSession();
    return session.get(AuthorEntity.class, id);
  }

  @Override
  public Long create(AuthorEntity entity) {
    return (Long) this.sessionFactory.getCurrentSession().save(entity);
  }

  @Override
  public void update(AuthorEntity entity) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
  }

  @Override
  public void delete(Long id) {
    Session session = this.sessionFactory.getCurrentSession();
    AuthorEntity entity = session.get(AuthorEntity.class, id);
    if (entity != null) {
      session.delete(entity);
    }
  }

  @Override
  public AuthorEntity loadByNewsId(Long newsId) throws NewsException {
    return null;
  }

  @Override
  public void makeExpired(Long authorId) {
    Session session = this.sessionFactory.getCurrentSession();
    AuthorEntity author = session.get(AuthorEntity.class, authorId);
    author.setExpiredDate(LocalDateTime.now());
    session.update(author);
  }
}
