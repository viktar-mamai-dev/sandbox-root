package com.mamay.dao.jpaimpl;

import com.mamay.dao.CommentDao;
import com.mamay.entity.CommentEntity;
import com.mamay.exception.DaoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CommentEntity> loadAll() throws DaoException {
        return entityManager.createQuery("from CommentEntity c", CommentEntity.class).getResultList();
    }

    @Override
    public CommentEntity loadById(Long id) throws DaoException {
        return entityManager.find(CommentEntity.class, id);
    }

    @Override
    public Long create(CommentEntity entity) throws DaoException {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(CommentEntity entity) throws DaoException {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) throws DaoException {
        CommentEntity entity = loadById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public List<CommentEntity> loadByNewsId(Long newsId) throws DaoException {
        return null;
    }
}
