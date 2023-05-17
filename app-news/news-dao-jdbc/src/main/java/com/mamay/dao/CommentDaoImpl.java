package com.mamay.dao;

import com.mamay.constant.SchemaSQL;
import com.mamay.entity.CommentEntity;
import com.mamay.exception.NewsException;
import com.mamay.util.DatabaseUtil;
import com.mamay.util.ResultSetCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl implements CommentDao {

    private final static String SQL_INSERT = "INSERT INTO comments (comment_id, comment_text,"
            + " creation_date, news_id) VALUES (COMMENT_SEQ.nextVal, ?, SYSTIMESTAMP, ?)";
    private final static String SQL_DELETE = "DELETE FROM comments WHERE comment_id=?";
    private final static String SQL_UPDATE = "UPDATE comments SET comment_text=?, news_id=? WHERE comment_id=?";
    private final static String SQL_LOAD_BY_ID = "SELECT comment_id, comment_text, creation_date, news_id"
            + " FROM comments WHERE comment_id=?";
    private final static String SQL_LOAD_BY_NEWS_ID = "SELECT comment_id, comment_text, creation_date,"
            + " news_id FROM comments WHERE news_id=? ORDER BY creation_date DESC";
    private final static String SQL_LOAD_ALL = "SELECT comment_id, comment_text, creation_date, news_id"
            + " FROM comments";

    @Override
    public List<CommentEntity> loadAll() throws NewsException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL_LOAD_ALL);
            List<CommentEntity> commentList = new ArrayList<CommentEntity>();
            while (rs.next()) {
                CommentEntity entity = ResultSetCreator.createComment(rs);
                commentList.add(entity);
            }
            return commentList;
        } catch (SQLException e) {
            throw new NewsException("Unable to load all comments", e);
        } finally {
            DatabaseUtil.close(dataSource, connection, statement, rs);
        }
    }

    @Override
    public List<CommentEntity> loadByNewsId(Long newsId) throws NewsException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(SQL_LOAD_BY_NEWS_ID);
            ps.setLong(1, newsId);
            rs = ps.executeQuery();
            List<CommentEntity> commentList = new ArrayList<CommentEntity>();
            while (rs.next()) {
                CommentEntity entity = ResultSetCreator.createComment(rs);
                commentList.add(entity);
            }
            return commentList;
        } catch (SQLException e) {
            throw new NewsException("Unable to load comment by id: " + newsId, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public CommentEntity loadById(Long id) throws NewsException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(SQL_LOAD_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            CommentEntity commentEntity = null;
            if (rs.next()) {
                commentEntity = ResultSetCreator.createComment(rs);
            }
            return commentEntity;
        } catch (SQLException e) {
            throw new NewsException("Unable to load comment by id" + id, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public Long create(CommentEntity entity) throws NewsException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(SQL_INSERT, new String[]{SchemaSQL.COMMENTS_ID});
            ps.setString(1, entity.getText());
            ps.setLong(2, entity.getNews().getId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            Long lastIndex = rs.getLong(1);
            return lastIndex;
        } catch (SQLException e) {
            throw new NewsException("Unable to create comment: " + entity, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public void update(CommentEntity entity) throws NewsException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, entity.getText());
            ps.setLong(2, entity.getNews().getId());
            ps.setLong(3, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new NewsException("Unable to update comment: " + entity, e);
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
            throw new NewsException("Unable to delete comment by id: " + id, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

}
