package com.mamay.dao.jpaimpl;

import com.mamay.dao.TagDao;
import com.mamay.entity.TagEntity;
import com.mamay.exception.DaoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagEntity> loadAll() throws DaoException {
        return entityManager.createQuery("from TagEntity t order by t.name asc", TagEntity.class).getResultList();
    }

    @Override
    public TagEntity loadById(Long id) throws DaoException {
        return entityManager.find(TagEntity.class, id);
    }

    @Override
    public Long create(TagEntity entity) throws DaoException {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(TagEntity entity) throws DaoException {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) throws DaoException {
        TagEntity entity = loadById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public List<TagEntity> loadByNewsId(Long newsId) throws DaoException {
        return null;
    }
}
