package com.mamay.dao;

import com.mamay.entity.CommentEntity;
import com.mamay.exception.NewsException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CommentEntity> loadAll() {
        return entityManager.createQuery("from CommentEntity c", CommentEntity.class).getResultList();
    }

    @Override
    public CommentEntity loadById(Long id) {
        return entityManager.find(CommentEntity.class, id);
    }

    @Override
    public Long create(CommentEntity entity) throws NewsException {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(CommentEntity entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        CommentEntity entity = loadById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public List<CommentEntity> loadByNewsId(Long newsId) {
        return null;
    }
}
