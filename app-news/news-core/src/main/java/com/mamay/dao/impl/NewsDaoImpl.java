package com.mamay.dao.impl;

import com.mamay.constant.SchemaSQL;
import com.mamay.dao.NewsDao;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import com.mamay.util.DatabaseUtil;
import com.mamay.util.QueryHelper;
import com.mamay.util.ResultSetCreator;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

@Repository("newsDao")
public class NewsDaoImpl implements NewsDao {

  private final String SQL_LOAD_ALL =
      "SELECT n.news_id, n.short_text, n.full_text, n.title, n.creation_date,"
          + " n.modification_date FROM news n";
  private final String SQL_LOAD_BY_ID =
      "SELECT news_id, short_text, full_text, title, creation_date,"
          + " modification_date FROM news WHERE news_id=?";
  private final String SQL_INSERT =
      "INSERT INTO news (news_id, short_text, full_text, title,"
          + " creation_date, modification_date) VALUES"
          + " (NEWS__SEQ.nextVal, ?, ?, ?, ?, ?)";
  private final String SQL_UPDATE =
      "UPDATE news SET short_text=?, full_text=?, title=?,"
          + " modification_date=? WHERE news_id=? ";
  private final String SQL_DELETE = "DELETE FROM news WHERE news_id=?";

  private final int BATCH_SIZE = 100;

  @Autowired private DataSource dataSource;

  @Override
  public List<NewsEntity> loadAll() throws NewsException {
    List<NewsEntity> newsList = new ArrayList<NewsEntity>();
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    try {
      connection = DataSourceUtils.getConnection(dataSource);
      statement = connection.createStatement();
      rs = statement.executeQuery(SQL_LOAD_ALL);
      while (rs.next()) {
        NewsEntity magazine = ResultSetCreator.createNews(rs);
        newsList.add(magazine);
      }
    } catch (SQLException e) {
      throw new NewsException(e);
    } finally {
      DatabaseUtil.close(dataSource, connection, statement, rs);
    }
    return newsList;
  }

  @Override
  public NewsEntity loadById(Long newsId) throws NewsException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = DataSourceUtils.getConnection(dataSource);
      ps = connection.prepareStatement(SQL_LOAD_BY_ID);
      ps.setLong(1, newsId);
      rs = ps.executeQuery();
      NewsEntity news = null;
      if (rs.next()) {
        news = ResultSetCreator.createNews(rs);
      }
      return news;
    } catch (SQLException e) {
      throw new NewsException(e);
    } finally {
      DatabaseUtil.close(dataSource, connection, ps, rs);
    }
  }

  @Override
  public Long create(NewsEntity entity) throws NewsException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = DataSourceUtils.getConnection(dataSource);
      ps = connection.prepareStatement(SQL_INSERT, new String[] {SchemaSQL.NEWS_ID});
      ps.setString(1, entity.getTitle());
      ps.setTimestamp(2, Timestamp.valueOf(entity.getCreationDate()));
      ps.setTimestamp(3, Timestamp.valueOf(entity.getCreationDate()));
      ps.executeUpdate();
      rs = ps.getGeneratedKeys();
      Long lastIndex = null;
      if (rs.next()) {
        lastIndex = rs.getLong(1);
      }
      return lastIndex;
    } catch (SQLException e) {
      throw new NewsException(e);
    } finally {
      DatabaseUtil.close(dataSource, connection, ps, rs);
    }
  }

  @Override
  public void update(NewsEntity entity) throws NewsException {
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = DataSourceUtils.getConnection(dataSource);
      ps = connection.prepareStatement(SQL_UPDATE);
      ps.setString(1, entity.getTitle());
      ps.setTimestamp(2, Timestamp.valueOf(entity.getModificationDate()));
      ps.setLong(3, entity.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new NewsException(e);
    } finally {
      DatabaseUtil.close(dataSource, connection, ps);
    }
  }

  @Override
  public void delete(Long id) throws NewsException {
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = DataSourceUtils.getConnection(dataSource);
      ps = connection.prepareStatement(SQL_DELETE);
      ps.setLong(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new NewsException(e);
    } finally {
      DatabaseUtil.close(dataSource, connection, ps);
    }
  }

  @Override
  public void deleteList(List<Long> newsIdList) throws NewsException {
    Connection connection = null;
    PreparedStatement ps = null;
    String baseQuery = "DELETE FROM news WHERE (news_id in (";
    String inQuery = QueryHelper.convertListToString(newsIdList);
    StringBuilder builder = new StringBuilder(baseQuery);
    builder.append(inQuery).append("))");
    try {
      connection = DataSourceUtils.getConnection(dataSource);
      ps = connection.prepareStatement(builder.toString());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new NewsException(e);
    } finally {
      DatabaseUtil.close(dataSource, connection, ps);
    }
  }
}
