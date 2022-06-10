package com.mamay.dao.jpaimpl;

import com.mamay.dao.AuthorDao;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.DaoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AuthorEntity> loadAll() throws DaoException {
        Query query = entityManager.createQuery("from AuthorEntity a order by a.name DESC", AuthorEntity.class);
        return query.getResultList();
    }

    @Override
    public AuthorEntity loadById(Long id) throws DaoException {
        AuthorEntity author = entityManager.find(AuthorEntity.class, id);
        return author;
    }

    @Override
    public Long create(AuthorEntity entity) throws DaoException {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(AuthorEntity entity) throws DaoException {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) throws DaoException {
        AuthorEntity entity = loadById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public AuthorEntity loadByNewsId(Long newsId) throws DaoException {
        return null;
    }

    @Override
    public void makeExpired(Long authorId) throws DaoException {
        AuthorEntity author = entityManager.find(AuthorEntity.class, authorId);
        author.setExpiredDate(LocalDateTime.now());
        entityManager.merge(author);
    }

    @Override
    public List<AuthorEntity> loadActiveAuthors() throws DaoException {
        Query query = entityManager.createNamedQuery("Author.loadActiveAuthors", AuthorEntity.class);
        return query.getResultList();
    }

}
