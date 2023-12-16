package com.mamay.dao;

import com.mamay.constant.SchemaSQL;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import com.mamay.util.DatabaseUtil;
import com.mamay.util.QueryHelper;
import com.mamay.util.ResultSetCreator;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("newsDao")
public class NewsDaoImpl extends BaseDaoImpl implements NewsDao {

    private static final String SQL_LOAD_ALL =
            "SELECT n.news_id, n.short_text, n.view_count, n.title, n.creation_date,"
                    + " n.modification_date FROM news n";
    private static final String SQL_LOAD_BY_ID =
            "SELECT news_id, short_text, view_count, title, creation_date,"
                    + " modification_date FROM news WHERE news_id=?";
    private static final String SQL_INSERT =
            "INSERT INTO news (news_id, short_text, view_count, title,"
                    + " creation_date, modification_date) VALUES"
                    + " (NEWS__SEQ.nextVal, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE news SET short_text=?, view_count=?, title=?,"
                    + " modification_date=? WHERE news_id=? ";
    private static final String SQL_DELETE = "DELETE FROM news WHERE news_id=?";

    private static final int BATCH_SIZE = 100;

    @Override
    public List<NewsEntity> loadAll() throws NewsException {
        List<NewsEntity> newsList = new ArrayList<NewsEntity>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
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
            connection = getConnection();
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
            connection = getConnection();
            ps = connection.prepareStatement(SQL_INSERT, new String[]{SchemaSQL.NEWS_ID});
            ps.setString(3, entity.getTitle());
            ps.setTimestamp(4, Timestamp.valueOf(entity.getCreationDate()));
            ps.setTimestamp(5, Timestamp.valueOf(entity.getCreationDate()));
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
            connection = getConnection();
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(3, entity.getTitle());
            ps.setTimestamp(4, Timestamp.valueOf(entity.getModificationDate()));
            Long newsId = entity.getId();
            ps.setLong(5, newsId);
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
            connection = getConnection();
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
            connection = getConnection();
            ps = connection.prepareStatement(builder.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new NewsException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

    private void appendFilter(List<Long> tagIdList, Long authorId, StringBuilder builder) {
        boolean isAuthorNull = authorId == null || authorId == 0;
        boolean isTagNull = tagIdList == null || tagIdList.isEmpty();
        if (!isAuthorNull) {
            builder.append(
                    "and n.news_id IN (SELECT na.news_id FROM NEWS_AUTHOR na WHERE na.AUTHOR_ID=?) ");
        }
        if (!isTagNull) {
            builder
                    .append("and n.news_id IN (SELECT nt.news_id FROM news_tag nt WHERE nt.TAG_ID in (")
                    .append(QueryHelper.convertListToString(tagIdList))
                    .append(")) ");
        }
    }
}
