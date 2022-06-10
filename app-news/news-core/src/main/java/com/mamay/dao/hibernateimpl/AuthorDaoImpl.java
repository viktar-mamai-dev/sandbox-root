package com.mamay.dao.hibernateimpl;

import com.mamay.dao.AuthorDao;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.DaoException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<AuthorEntity> loadAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(AuthorEntity.class);
        criteria.addOrder(Order.asc("name"));
        return (List<AuthorEntity>) criteria.list();
    }

    @Override
    public List<AuthorEntity> loadActiveAuthors() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("Author.loadActiveAuthors");
        return (List<AuthorEntity>) query.list();
    }

    @Override
    public AuthorEntity loadById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (AuthorEntity) session.get(AuthorEntity.class, id);
    }

    @Override
    public Long create(AuthorEntity entity) {
        Session session = this.sessionFactory.getCurrentSession();
        Long id = (Long) session.save(entity);
        return id;

    }

    @Override
    public void update(AuthorEntity entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        AuthorEntity entity = (AuthorEntity) session.get(AuthorEntity.class, id);
        if (entity != null) {
            session.delete(entity);
        }
    }

    @Override
    public AuthorEntity loadByNewsId(Long newsId) throws DaoException {
        return null;
    }

    @Override
    public void makeExpired(Long authorId) {
        Session session = this.sessionFactory.getCurrentSession();
        AuthorEntity author = (AuthorEntity) session.get(AuthorEntity.class, authorId);
        author.setExpiredDate(LocalDateTime.now());
        session.update(author);
    }

}
