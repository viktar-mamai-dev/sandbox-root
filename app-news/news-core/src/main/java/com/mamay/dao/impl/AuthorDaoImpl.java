package com.mamay.dao.impl;

import com.mamay.constant.SchemaSQL;
import com.mamay.dao.AuthorDao;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.DaoException;
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
    public List<AuthorEntity> loadAll() throws DaoException {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL_LOAD_ALL);
            List<AuthorEntity> authorList = new ArrayList<AuthorEntity>();
            while (rs.next()) {
                AuthorEntity entity = ResultSetCreator.createAuthor(rs);
                authorList.add(entity);
            }
            return authorList;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, statement, rs);
        }
    }

    @Override
    public List<AuthorEntity> loadActiveAuthors() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL_LOAD_ACTIVE);
            List<AuthorEntity> authorList = new ArrayList<AuthorEntity>();
            while (rs.next()) {
                AuthorEntity entity = ResultSetCreator.createAuthor(rs);
                authorList.add(entity);
            }
            return authorList;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DatabaseUtil.close(dataSource, connection, statement, rs);
        }
    }

    @Override
    public AuthorEntity loadByNewsId(Long newsId) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_LOAD_BY_NEWS_ID);
            ps.setLong(1, newsId);
            rs = ps.executeQuery();
            AuthorEntity entity = null;
            if (rs.next()) {
                entity = ResultSetCreator.createAuthor(rs);
            }
            return entity;
        } catch (SQLException e) {
            throw new DaoException("Unable to load author by news id: "
                    + newsId, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public AuthorEntity loadById(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_LOAD_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            AuthorEntity authorEntity = null;
            if (rs.next()) {
                authorEntity = ResultSetCreator.createAuthor(rs);
            }
            return authorEntity;
        } catch (SQLException e) {
            throw new DaoException("Unable to load author by id: " + id, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
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
            throw new DaoException("Unable to delete author by id: " + id, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

    @Override
    public Long create(AuthorEntity entity) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_INSERT,
                    new String[]{SchemaSQL.AUTHOR_ID});
            ps.setString(1, entity.getName());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            Long lastIndex = rs.getLong(1);
            return lastIndex;
        } catch (SQLException e) {
            throw new DaoException("Unable to create author: " + entity, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps, rs);
        }
    }

    @Override
    public void update(AuthorEntity entity) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, entity.getName());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update author: " + entity, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

    @Override
    public void makeExpired(Long authorId) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            ps = connection.prepareStatement(SQL_EXPIRED);
            ps.setLong(1, authorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to make expired author with id: "
                    + authorId, e);
        } finally {
            DatabaseUtil.close(dataSource, connection, ps);
        }
    }

}