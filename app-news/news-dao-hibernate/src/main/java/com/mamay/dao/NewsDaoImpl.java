package com.mamay.dao;

import com.mamay.entity.NewsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class NewsDaoImpl extends BaseDaoImpl implements NewsDao {
    @Override
    public List<NewsEntity> loadAll() {
        Session session = getSession();
        return (List<NewsEntity>) session.createCriteria(NewsEntity.class).list();
    }

    @Override
    public NewsEntity loadById(Long id) {
        Session session = getSession();
        return session.get(NewsEntity.class, id);
    }

    @Override
    public Long create(NewsEntity entity) {
        Session session = getSession();
        entity.setModificationDate(LocalDateTime.now());
        return (Long) session.save(entity);
    }

    @Override
    public void update(NewsEntity entity) {
        Session session = getSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public void delete(Long id) {
        Session session = getSession();
        NewsEntity entity = session.get(NewsEntity.class, id);
        if (entity != null) {
            session.delete(entity);
        }
    }

    @Override
    public void deleteList(List<Long> newsIdList) {
        Session session = getSession();
        Query query = session.getNamedQuery("News.deleteList");
        query.setParameterList("idList", newsIdList);
        query.executeUpdate();
    }
}
