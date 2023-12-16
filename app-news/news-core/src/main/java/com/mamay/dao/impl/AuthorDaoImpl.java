package com.mamay.dao.impl;

import com.mamay.constant.SchemaSQL;
import com.mamay.dao.AuthorDao;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.NewsException;
import com.mamay.util.DatabaseUtil;
import com.mamay.util.ResultSetCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("authorDao")
public class AuthorDaoImpl implements AuthorDao {

    private final String SQL_LOAD_ALL = "SELECT author_id, name, expired FROM author ORDER BY name";
    private final String SQL_LOAD_ACTIVE = "SELECT author_id, name, expired FROM author WHERE EXPIRED is NULL";
    private final String SQL_LOAD_BY_ID = "SELECT author_id, name, expired FROM author WHERE author_id=?";
    private final String SQL_LOAD_BY_NEWS_ID = "SELECT a.author_id, a.name, a.expired FROM author a"
            + " INNER JOIN news_author na ON a.author_id=na.author_id WHERE na.news_id=?";

    private final String SQL_INSERT = "INSERT INTO author(author_id, name) VALUES"
            + " (AUTHOR_SEQ.nextVal, ?)";
    private final String SQL_UPDATE = "UPDATE author SET name=? WHERE author_id=?";
    private final String SQL_DELETE = "DELETE FROM author WHERE author_id=?";
    private final String SQL_EXPIRED = "UPDATE author SET expired=SYSDATE WHERE author_id=?";

    @Autowired
    private DataSource dataSource;

    @Override
    public List<AuthorEntity> loadAll() throws NewsException {
        try (Connection connection = DataSourceUtils.getConnection(dataSource);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_LOAD_ALL)) {
            List<AuthorEntity> authorList = new ArrayList<AuthorEntity>();
            while (rs.next()) {
                AuthorEntity entity = ResultSetCreator.createAuthor(rs);
                authorList.add(entity);
            }
            return authorList;
        } catch (SQLException e) {
            throw new NewsException(e);
        }
    }

    @Override
    public List<AuthorEntity> loadActiveAuthors() throws NewsException {
        try (Connection connection = DataSourceUtils.getConnection(dataSource);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_LOAD_ACTIVE)) {
            List<AuthorEntity> authorList = new ArrayList<AuthorEntity>();
            while (rs.next()) {
                AuthorEntity entity = ResultSetCreator.createAuthor(rs);
                authorList.add(entity);
            }
            return authorList;
        } catch (SQLException e) {
            throw new NewsException(e);
        }
    }

    @Override
    public AuthorEntity loadByNewsId(Long newsId) throws NewsException {
        try (Connection connection = DataSourceUtils.getConnection(dataSource);
             PreparedStatement statement = connection.prepareStatement(SQL_LOAD_BY_NEWS_ID)) {
            statement.setLong(1, newsId);
            ResultSet rs = statement.executeQuery();
            AuthorEntity entity = null;
            if (rs.next()) {
                entity = ResultSetCreator.createAuthor(rs);
            }
            return entity;
        } catch (SQLException e) {
            throw new NewsException("Unable to load author by news id: "
                    + newsId, e);
        }
    }

    @Override
    public AuthorEntity loadById(Long id) throws NewsException {
        try (Connection connection = DataSourceUtils.getConnection(dataSource);
             PreparedStatement statement = connection.prepareStatement(SQL_LOAD_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            AuthorEntity authorEntity = null;
            if (rs.next()) {
                authorEntity = ResultSetCreator.createAuthor(rs);
            }
            return authorEntity;
        } catch (SQLException e) {
            throw new NewsException("Unable to load author by id: " + id, e);
        }
    }

    @Override
    public void delete(Long id) throws NewsException {
        try (Connection connection = DataSourceUtils.getConnection(dataSource);
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setLong(1, id);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new NewsException("Unable to delete author by id: " + id, e);
        }
    }

    @Override
    public Long create(AuthorEntity entity) throws NewsException {
        try (Connection connection = DataSourceUtils.getConnection(dataSource);
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                     new String[]{SchemaSQL.AUTHOR_ID})) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new NewsException("Unable to create author: " + entity, e);
        }
    }

    @Override
    public void update(AuthorEntity entity) throws NewsException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, entity.getName());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new NewsException("Unable to update author: " + entity, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

    @Override
    public void makeExpired(Long authorId) throws NewsException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_EXPIRED);
            ps.setLong(1, authorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new NewsException("Unable to make expired author with id: " + authorId, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

}