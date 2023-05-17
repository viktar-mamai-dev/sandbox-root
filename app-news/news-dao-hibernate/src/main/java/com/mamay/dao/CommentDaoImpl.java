package com.mamay.dao;

import com.mamay.entity.CommentEntity;
import com.mamay.exception.NewsException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CommentDaoImpl extends BaseDaoImpl implements CommentDao {

    @Override
    public List<CommentEntity> loadAll() {
        Session session = getSession();
        CriteriaQuery<CommentEntity> criteria = session.getCriteriaBuilder().createQuery(CommentEntity.class);
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public CommentEntity loadById(Long id) {
        Session session = getSession();
        return session.get(CommentEntity.class, id);
    }

    @Override
    public Long create(CommentEntity entity) {
        Session session = getSession();
        entity.setCreationDate(LocalDateTime.now());
        return (Long) session.save(entity);
    }

    @Override
    public void update(CommentEntity entity) {
        Session session = getSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public void delete(Long id) {
        Session session = getSession();
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
