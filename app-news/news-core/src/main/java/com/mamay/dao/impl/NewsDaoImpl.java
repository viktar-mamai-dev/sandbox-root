package com.mamay.dao.impl;

import com.mamay.constant.SchemaSQL;
import com.mamay.dao.NewsDao;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.DaoException;
import com.mamay.util.DatabaseUtil;
import com.mamay.util.QueryHelper;
import com.mamay.util.ResultSetCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository("newsDao")
public class NewsDaoImpl implements NewsDao {

    private final String SQL_LOAD_ALL = "SELECT n.news_id, n.short_text, n.full_text, n.title, n.creation_date,"
            + " n.modification_date FROM news n";
    private final String SQL_LOAD_BY_ID = "SELECT news_id, short_text, full_text, title, creation_date,"
            + " modification_date FROM news WHERE news_id=?";
    private final String SQL_INSERT = "INSERT INTO news (news_id, short_text, full_text, title,"
            + " creation_date, modification_date) VALUES" + " (NEWS__SEQ.nextVal, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE news SET short_text=?, full_text=?, title=?,"
            + " modification_date=? WHERE news_id=? ";
    private final String SQL_DELETE = "DELETE FROM news WHERE news_id=?";

    private final int BATCH_SIZE = 100;

    @Autowired
    private DataSource dataSource;

    @Override
    public List<NewsEntity> loadAll() throws DaoException {
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
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, statement, rs);
        }
        return newsList;
    }

    @Override
    public NewsEntity loadById(Long newsId) throws DaoException {
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
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public NewsPageItem<NewsEntity> loadByFilter(NewsSearchCriteria filteredItem, Integer pageNumber,
                                                 final int newsPerPage) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = returnStatementForLoadPage(connection, filteredItem, pageNumber, newsPerPage);
            rs = ps.executeQuery();
            List<NewsEntity> newsList = new ArrayList<NewsEntity>();
            Integer pageCount = null;
            while (rs.next()) {
                if (rs.isFirst()) {
                    pageCount = ResultSetCreator.calculatePageCount(rs, newsPerPage);
                }
                NewsEntity entity = ResultSetCreator.createNews(rs);
                newsList.add(entity);
            }
            NewsPageItem<NewsEntity> item = new NewsPageItem<NewsEntity>(newsList, pageNumber, pageCount);
            return item;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public Long loadNextId(NewsSearchCriteria filteredItem, Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = loadOffsetId(connection, filteredItem, id, 1);
            rs = ps.executeQuery();
            Long nextId = null;
            if (rs.next()) {
                nextId = rs.getLong(SchemaSQL.NEWS_ID);
            }
            return nextId;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public Long loadPreviousId(NewsSearchCriteria filteredItem, Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = loadOffsetId(connection, filteredItem, id, -1);
            rs = ps.executeQuery();
            Long previousId = null;
            if (rs.next()) {
                previousId = rs.getLong(SchemaSQL.NEWS_ID);
            }
            return previousId;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public Long create(NewsEntity entity) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_INSERT, new String[]{SchemaSQL.NEWS_ID});
            ps.setString(1, entity.getShortText());
            ps.setString(2, entity.getFullText());
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
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public void update(NewsEntity entity) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, entity.getShortText());
            ps.setString(2, entity.getFullText());
            ps.setString(3, entity.getTitle());
            ps.setTimestamp(4, Timestamp.valueOf(entity.getModificationDate()));
            Long newsId = entity.getId();
            ps.setLong(5, newsId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_DELETE);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

    @Override
    public void deleteList(List<Long> newsIdList) throws DaoException {
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
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

    private PreparedStatement loadOffsetId(Connection connection, NewsSearchCriteria filteredItem, Long newsId,
                                           int offset) throws DaoException, SQLException {
        List<Long> tagIdList = filteredItem.getTagIdList();
        Long authorId = filteredItem.getAuthorId();

        String prefixQuery = "WITH tabl as (SELECT n.news_id as news_id, ROWNUM as rn FROM (SELECT n.news_id FROM"
                + " news n LEFT JOIN (SELECT c.news_id, COUNT(c.news_id) as comment_count FROM COMMENTS c GROUP BY "
                + "c.news_id) c ON n.news_id=c.news_id WHERE 1=1 ";
        StringBuilder builder = new StringBuilder(prefixQuery);

        appendFilter(tagIdList, authorId, builder);

        String postfixQuery = "ORDER BY c.comment_count DESC NULLS LAST, n.modification_date DESC) n ) "
                + "SELECT tabl.news_id FROM tabl WHERE tabl.rn = (SELECT tabl.rn FROM tabl WHERE tabl.news_id=?)";
        String offsetPart = offset > 0 ? "+" + offset : String.valueOf(offset);
        builder.append(postfixQuery).append(offsetPart);
        PreparedStatement ps = connection.prepareStatement(builder.toString());
        int paramIdx = 1;
        boolean isAuthorNull = authorId == null || authorId == 0;
        if (!isAuthorNull) {
            ps.setLong(paramIdx++, authorId);
        }
        ps.setLong(paramIdx++, newsId);
        return ps;

    }

    private PreparedStatement returnStatementForLoadPage(Connection connection, NewsSearchCriteria filteredItem,
                                                         Integer pageNumber, final int newsPerPage) throws DaoException, SQLException {
        List<Long> tagIdList = filteredItem.getTagIdList();
        Long authorId = filteredItem.getAuthorId();

        String prefixQuery = "SELECT n.news_id, n.short_text, n.full_text, n.title, n.creation_date, n.modification_date"
                + " FROM news n LEFT JOIN (SELECT c.news_id, COUNT(c.news_id) as comment_count FROM COMMENTS c GROUP BY"
                + " c.news_id) c ON n.NEWS_ID=c.news_id WHERE 1=1 ";
        StringBuilder builder = new StringBuilder(prefixQuery);

        appendFilter(tagIdList, authorId, builder);

        builder.append(" ORDER BY c.comment_count DESC NULLS LAST, n.modification_date DESC");
        PreparedStatement ps = connection.prepareStatement(QueryHelper.wrapQueryToRownum(builder.toString()));
        int paramIdx = 1;
        boolean isAuthorNull = authorId == null || authorId == 0;
        if (!isAuthorNull) {
            ps.setLong(paramIdx++, authorId);
        }
        int startRow = (pageNumber - 1) * newsPerPage + 1;
        ps.setInt(paramIdx++, startRow);
        int finishRow = pageNumber * newsPerPage;
        ps.setInt(paramIdx++, finishRow);
        return ps;
    }

    private void appendFilter(List<Long> tagIdList, Long authorId, StringBuilder builder) {
        boolean isAuthorNull = authorId == null || authorId == 0;
        boolean isTagNull = tagIdList == null || tagIdList.isEmpty();
        if (!isAuthorNull) {
            builder.append("and n.news_id IN (SELECT na.news_id FROM NEWS_AUTHOR na WHERE na.AUTHOR_ID=?) ");
        }
        if (!isTagNull) {
            builder.append("and n.news_id IN (SELECT nt.news_id FROM news_tag nt WHERE nt.TAG_ID in (")
                    .append(QueryHelper.convertListToString(tagIdList)).append(")) ");
        }
    }

}
