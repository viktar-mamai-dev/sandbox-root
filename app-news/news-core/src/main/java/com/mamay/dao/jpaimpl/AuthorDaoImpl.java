package com.mamay.dao.jpaimpl;

import com.mamay.dao.AuthorDao;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.NewsException;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDaoImpl implements AuthorDao {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<AuthorEntity> loadAll() throws NewsException {
    TypedQuery<AuthorEntity> query =
        entityManager.createQuery("from AuthorEntity a order by a.name DESC", AuthorEntity.class);
    return query.getResultList();
  }

  @Override
  public AuthorEntity loadById(Long id) throws NewsException {
    return entityManager.find(AuthorEntity.class, id);
  }

  @Override
  public Long create(AuthorEntity entity) throws NewsException {
    entityManager.persist(entity);
    entityManager.flush();
    return entity.getId();
  }

  @Override
  public void update(AuthorEntity entity) throws NewsException {
    entityManager.merge(entity);
  }

  @Override
  public void delete(Long id) throws NewsException {
    AuthorEntity entity = loadById(id);
    if (entity != null) {
      entityManager.remove(entity);
    }
  }

  @Override
  public AuthorEntity loadByNewsId(Long newsId) throws NewsException {
    return null;
  }

  @Override
  public void makeExpired(Long authorId) throws NewsException {
    AuthorEntity author = entityManager.find(AuthorEntity.class, authorId);
    author.setExpiredDate(LocalDateTime.now());
    entityManager.merge(author);
  }

  @Override
  public List<AuthorEntity> loadActiveAuthors() throws NewsException {
    TypedQuery<AuthorEntity> query =
        entityManager.createNamedQuery("Author.loadActiveAuthors", AuthorEntity.class);
    return query.getResultList();
  }
}
