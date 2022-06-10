package com.mamay.dao.hibernateimpl;

import com.mamay.dao.TagDao;
import com.mamay.entity.TagEntity;
import com.mamay.exception.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TagEntity> loadAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<TagEntity>) session.createCriteria(TagEntity.class)
                .addOrder(Order.asc("name")).list();
    }

    @Override
    public TagEntity loadById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (TagEntity) session.get(TagEntity.class, id);
    }

    @Override
    public Long create(TagEntity entity) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Long) session.save(entity);
    }

    @Override
    public void update(TagEntity entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        TagEntity entity = (TagEntity) session.get(TagEntity.class, id);
        if (entity != null) {
            session.delete(entity);
        }
    }

    @Override
    public List<TagEntity> loadByNewsId(Long newsId) throws DaoException {
        return null;
    }
}
