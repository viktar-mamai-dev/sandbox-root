package com.mamay.dao.impl;

import com.mamay.constant.SchemaSQL;
import com.mamay.dao.TagDao;
import com.mamay.entity.TagEntity;
import com.mamay.exception.NewsException;
import com.mamay.util.DatabaseUtil;
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
import java.util.ArrayList;
import java.util.List;

@Repository("tagDao")
public class TagDaoImpl implements TagDao {

    private final String SQL_LOAD_ALL = "SELECT tag_id, tag_name FROM tag ORDER BY tag_name";
    private final String SQL_LOAD_BY_ID = "SELECT tag_id, tag_name FROM tag WHERE tag_id=?";
    private final String SQL_LOAD_BY_NEWS_ID = "SELECT t.tag_id, t.tag_name FROM tag t INNER JOIN"
            + " news_tag nt ON t.tag_id=nt.tag_id WHERE nt.news_id=?";

    private final String SQL_INSERT = "INSERT INTO tag(tag_id, tag_name)"
            + " VALUES (TAG_SEQ.nextVal, ?)";
    private final String SQL_UPDATE = "UPDATE tag SET tag_name=? WHERE tag_id=?";
    private final String SQL_DELETE = "DELETE FROM tag WHERE tag_id=?";

    @Autowired
    private DataSource dataSource;

    @Override
    public List<TagEntity> loadAll() throws NewsException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL_LOAD_ALL);
            List<TagEntity> tagList = new ArrayList<TagEntity>();
            while (rs.next()) {
                TagEntity entity = ResultSetCreator.createTag(rs);
                tagList.add(entity);
            }
            return tagList;
        } catch (SQLException e) {
            throw new NewsException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, statement, rs);
        }
    }

    @Override
    public List<TagEntity> loadByNewsId(Long newsId) throws NewsException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_LOAD_BY_NEWS_ID);
            ps.setLong(1, newsId);
            rs = ps.executeQuery();
            List<TagEntity> tagList = new ArrayList<TagEntity>();
            while (rs.next()) {
                TagEntity entity = ResultSetCreator.createTag(rs);
                tagList.add(entity);
            }
            return tagList;
        } catch (SQLException e) {
            throw new NewsException("Unable to load tag by news id: " + newsId, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public TagEntity loadById(Long id) throws NewsException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_LOAD_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            TagEntity tagEntity = null;
            if (rs.next()) {
                tagEntity = ResultSetCreator.createTag(rs);
            }
            return tagEntity;
        } catch (SQLException e) {
            throw new NewsException("Unable to load tag by id: " + id, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public Long create(TagEntity entity) throws NewsException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_INSERT, new String[]{SchemaSQL.TAG_ID});
            ps.setString(1, entity.getName());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            Long lastIndex = rs.getLong(1);
            return lastIndex;
        } catch (SQLException e) {
            throw new NewsException("Unable to create tag: " + entity, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public void update(TagEntity entity) throws NewsException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, entity.getName());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new NewsException("Unable to update tag: " + entity, e);
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
            throw new NewsException("Unable to delete tag with id: " + id, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

}
