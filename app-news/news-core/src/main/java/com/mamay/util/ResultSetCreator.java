package com.mamay.util;

import com.mamay.constant.SchemaSQL;
import com.mamay.entity.AuthorEntity;
import com.mamay.entity.CommentEntity;
import com.mamay.entity.NewsEntity;
import com.mamay.entity.TagEntity;
import com.mamay.exception.NewsException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * utility class that transforms object stored in database into entity, by extracting appropriate
 * fields
 */
public class ResultSetCreator {
  public static NewsEntity createNews(ResultSet rs) throws NewsException {
    try {
      Long id = rs.getLong(SchemaSQL.NEWS_ID);
      String title = rs.getString(SchemaSQL.NEWS_TITLE);
      LocalDateTime creationDate = rs.getObject(SchemaSQL.NEWS_CREATION_DATE, LocalDateTime.class);
      LocalDateTime modificationDate =
          rs.getObject(SchemaSQL.NEWS_MODIFICATION_DATE, LocalDateTime.class);
      NewsEntity entity = new NewsEntity();
      entity.setTitle(title);
      entity.setCreationDate(creationDate);
      entity.setId(id);
      entity.setModificationDate(modificationDate);
      return entity;
    } catch (SQLException e) {
      throw new NewsException(e);
    }
  }

  public static TagEntity createTag(ResultSet rs) throws NewsException {
    try {
      Long id = rs.getLong(SchemaSQL.TAG_ID);
      String name = rs.getString(SchemaSQL.TAG_NAME);
      TagEntity entity = new TagEntity();
      entity.setName(name);
      entity.setId(id);
      return entity;
    } catch (SQLException e) {
      throw new NewsException(e);
    }
  }

  public static AuthorEntity createAuthor(ResultSet rs) throws NewsException {
    try {
      Long id = rs.getLong(SchemaSQL.AUTHOR_ID);
      String name = rs.getString(SchemaSQL.AUTHOR_NAME);
      LocalDateTime expireddate = rs.getObject(SchemaSQL.AUTHOR_EXPIRED, LocalDateTime.class);
      AuthorEntity entity = new AuthorEntity();
      entity.setName(name);
      entity.setExpiredDate(expireddate);
      entity.setId(id);
      return entity;
    } catch (SQLException e) {
      throw new NewsException(e);
    }
  }

  public static CommentEntity createComment(ResultSet rs) throws NewsException {
    try {
      Long id = rs.getLong(SchemaSQL.COMMENTS_ID);
      String text = rs.getString(SchemaSQL.COMMENT_TEXT);
      LocalDateTime creationDate =
          rs.getObject(SchemaSQL.COMMENT_CREATION_DATE, LocalDateTime.class);
      Long newsId = rs.getLong(SchemaSQL.NEWS_ID);
      CommentEntity entity = new CommentEntity();
      entity.setText(text);
      // TODO setNewsId
      entity.setCreationDate(creationDate);
      entity.setId(id);
      return entity;
    } catch (SQLException e) {
      throw new NewsException(e);
    }
  }

  /**
   * @param rs - {@link ResultSet}
   * @param NEWS_PER_PAGE - count of news to be viewed on list page
   * @return - total count of pages
   * @throws NewsException - when there is a connection error
   */
  public static Integer calculatePageCount(ResultSet rs, final int NEWS_PER_PAGE)
      throws NewsException {
    try {
      Integer totalCount = rs.getInt(SchemaSQL.TOTAL_COUNT);
      Integer pageCount = null;
      if (totalCount != null) {
        pageCount = (int) Math.ceil(totalCount * 1.0 / NEWS_PER_PAGE);
      }
      return pageCount;
    } catch (SQLException e) {
      throw new NewsException(e);
    }
  }
}
