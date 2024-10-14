package com.mamay;

import com.mamay.entity.AuthorEntity;
import com.mamay.entity.CommentEntity;
import com.mamay.entity.NewsEntity;
import com.mamay.entity.TagEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class TestHolder {

  public static final Random rand = new Random();

  public static int generateRandomNumber(int min, int max) {
    return rand.nextInt(max - min) + min;
  }

  public static String generateRandomString(int minLength, int maxLength) {
    return RandomStringUtils.randomAlphabetic(minLength, maxLength);
  }

  public static List<AuthorEntity> generateAuthorList() {
    ArrayList<AuthorEntity> authorList = new ArrayList<AuthorEntity>();
    int size = generateRandomNumber(5, 10);
    for (int i = 0; i < size; i++) {
      authorList.add(generateAuthor());
    }
    return authorList;
  }

  public static AuthorEntity generateAuthor() {
    AuthorEntity entity = new AuthorEntity();
    entity.setId((long) generateRandomNumber(1, 100));
    entity.setName(generateRandomString(5, 15));
    return entity;
  }

  public static List<NewsEntity> generateNewsList() {
    List<NewsEntity> newsList = new ArrayList<NewsEntity>();
    int size = generateRandomNumber(5, 10);
    for (int i = 0; i < size; i++) {
      newsList.add(generateNewsEntity());
    }
    return newsList;
  }

  public static NewsEntity generateNewsEntity() {
    NewsEntity entity = new NewsEntity();
    entity.setId((long) generateRandomNumber(1, 100));
    entity.setTitle(generateRandomString(5, 15));
    return entity;
  }

  public static List<CommentEntity> generateCommentList() {
    List<CommentEntity> commentList = new ArrayList<CommentEntity>();
    int size = generateRandomNumber(5, 10);
    for (int i = 0; i < size; i++) {
      commentList.add(generateComment());
    }
    return commentList;
  }

  public static CommentEntity generateComment() {
    CommentEntity entity = new CommentEntity();
    entity.setId((long) generateRandomNumber(1, 100));
    entity.setText(generateRandomString(20, 30));
    return entity;
  }

  public static List<TagEntity> generateTagList() {
    ArrayList<TagEntity> tagList = new ArrayList<TagEntity>();
    int size = generateRandomNumber(5, 10);
    for (int i = 0; i < size; i++) {
      tagList.add(generateTag());
    }
    return tagList;
  }

  public static TagEntity generateTag() {
    TagEntity entity = new TagEntity();
    entity.setId((long) generateRandomNumber(1, 100));
    entity.setName(generateRandomString(5, 15));
    return entity;
  }
}
