package com.mamay.dao.jpaimpl;

import com.mamay.dao.TagDao;
import com.mamay.entity.TagEntity;
import com.mamay.exception.NewsException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagEntity> loadAll() throws NewsException {
        return entityManager.createQuery("from TagEntity t order by t.name asc", TagEntity.class).getResultList();
    }

    @Override
    public TagEntity loadById(Long id) throws NewsException {
        return entityManager.find(TagEntity.class, id);
    }

    @Override
    public Long create(TagEntity entity) throws NewsException {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(TagEntity entity) throws NewsException {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) throws NewsException {
        TagEntity entity = loadById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public List<TagEntity> loadByNewsId(Long newsId) throws NewsException {
        return null;
    }
}
