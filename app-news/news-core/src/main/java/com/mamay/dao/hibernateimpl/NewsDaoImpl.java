package com.mamay.dao.hibernateimpl;

import com.mamay.dao.NewsDao;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl implements NewsDao {

  @Autowired private SessionFactory sessionFactory;

  @Override
  public List<NewsEntity> loadAll() {
    Session session = this.sessionFactory.getCurrentSession();
    return (List<NewsEntity>) session.createCriteria(NewsEntity.class).list();
  }

  @Override
  public NewsEntity loadById(Long id) throws NewsException {
    Session session = this.sessionFactory.getCurrentSession();
    return session.get(NewsEntity.class, id);
  }

  @Override
  public Long create(NewsEntity entity) {
    Session session = this.sessionFactory.getCurrentSession();
    entity.setModificationDate(LocalDateTime.now());
    return (Long) session.save(entity);
  }

  @Override
  public void update(NewsEntity entity) {
    Session session = this.sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
  }

  @Override
  public void delete(Long id) {
    Session session = this.sessionFactory.getCurrentSession();
    NewsEntity entity = session.get(NewsEntity.class, id);
    if (entity != null) {
      session.delete(entity);
    }
  }

  @Override
  public void deleteList(List<Long> newsIdList) {
    Session session = this.sessionFactory.getCurrentSession();
    Query query = session.getNamedQuery("News.deleteList");
    query.setParameterList("idList", newsIdList);
    query.executeUpdate();
  }
}
