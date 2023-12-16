package com.mamay.dao;

import com.mamay.entity.AuthorEntity;
import com.mamay.exception.NewsException;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDaoImpl extends BaseDaoImpl implements AuthorDao {
  @Override
  public List<AuthorEntity> loadAll() {
    Session session = getSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<AuthorEntity> criteria = builder.createQuery(AuthorEntity.class);
    Root<AuthorEntity> root = criteria.from(AuthorEntity.class);
    criteria.select(root).orderBy(builder.asc(root.get("name")));
    return session.createQuery(criteria).getResultList();
  }

  @Override
  public List<AuthorEntity> loadActiveAuthors() {
    Session session = getSession();
    return session.createNamedQuery("Author.loadActiveAuthors", AuthorEntity.class).getResultList();
  }

  @Override
  public AuthorEntity loadById(Long id) {
    Session session = getSession();
    return session.get(AuthorEntity.class, id);
  }

  @Override
  public Long create(AuthorEntity entity) {
    return (Long) getSession().save(entity);
  }

  @Override
  public void update(AuthorEntity entity) {
    getSession().saveOrUpdate(entity);
  }

  @Override
  public void delete(Long id) {
    Session session = getSession();
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
    Session session = getSession();
    AuthorEntity author = session.get(AuthorEntity.class, authorId);
    author.setExpiredDate(LocalDateTime.now());
    session.update(author);
  }
}
