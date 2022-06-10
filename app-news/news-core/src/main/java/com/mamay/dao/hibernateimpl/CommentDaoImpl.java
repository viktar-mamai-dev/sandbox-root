package com.mamay.dao.hibernateimpl;

import com.mamay.dao.CommentDao;
import com.mamay.entity.CommentEntity;
import com.mamay.exception.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CommentEntity> loadAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<CommentEntity>) session.createCriteria(CommentEntity.class).list();
    }

    @Override
    public CommentEntity loadById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (CommentEntity) session.get(CommentEntity.class, id);
    }

    @Override
    public Long create(CommentEntity entity) {
        Session session = this.sessionFactory.getCurrentSession();
        entity.setCreationDate(LocalDateTime.now());
        return (Long) session.save(entity);
    }

    @Override
    public void update(CommentEntity entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        CommentEntity entity = (CommentEntity) session.get(CommentEntity.class, id);
        if (entity != null) {
            session.delete(entity);
        }
    }

    @Override
    public List<CommentEntity> loadByNewsId(Long newsId) throws DaoException {
        return null;
    }
}
