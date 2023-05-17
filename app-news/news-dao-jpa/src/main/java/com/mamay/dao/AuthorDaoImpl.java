package com.mamay.dao;

import com.mamay.entity.AuthorEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AuthorEntity> loadAll() {
        TypedQuery<AuthorEntity> query = entityManager.createQuery("from AuthorEntity a order by a.name DESC",
                AuthorEntity.class);
        return query.getResultList();
    }

    @Override
    public AuthorEntity loadById(Long id) {
        return entityManager.find(AuthorEntity.class, id);
    }

    @Override
    public Long create(AuthorEntity entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(AuthorEntity entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        AuthorEntity entity = loadById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public AuthorEntity loadByNewsId(Long newsId) {
        return null;
    }

    @Override
    public void makeExpired(Long authorId) {
        AuthorEntity author = entityManager.find(AuthorEntity.class, authorId);
        author.setExpiredDate(LocalDateTime.now());
        entityManager.merge(author);
    }

    @Override
    public List<AuthorEntity> loadActiveAuthors() {
        TypedQuery<AuthorEntity> query = entityManager.createNamedQuery("Author.loadActiveAuthors", AuthorEntity.class);
        return query.getResultList();
    }

}
