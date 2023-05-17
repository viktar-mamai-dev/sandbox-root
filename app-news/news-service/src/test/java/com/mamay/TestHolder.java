package com.mamay;

import com.mamay.entity.AuthorEntity;
import com.mamay.entity.CommentEntity;
import com.mamay.entity.NewsEntity;
import com.mamay.entity.TagEntity;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestHolder {

  public static final Random rand = new Random();

  public static int generateRandomNumber(int min, int max) {
    return rand.nextInt(max - min) + min;
  }

  public static String generateRandomString(int minLength, int maxLength) {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = generateRandomNumber(minLength, maxLength);
    return rand.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  public static List<AuthorEntity> generateAuthorList() {
    return IntStream.range(0, generateRandomNumber(5, 10))
        .mapToObj(i -> generateAuthor())
        .collect(Collectors.toList());
  }

  public static AuthorEntity generateAuthor() {
    AuthorEntity entity = new AuthorEntity();
    entity.setId((long) generateRandomNumber(1, 100));
    entity.setName(generateRandomString(5, 15));
    return entity;
  }

  public static List<NewsEntity> generateNewsList() {
    return IntStream.range(0, generateRandomNumber(5, 10))
        .mapToObj(i -> generateNewsEntity())
        .collect(Collectors.toList());
  }

  public static NewsEntity generateNewsEntity() {
    NewsEntity entity = new NewsEntity();
    entity.setId((long) generateRandomNumber(1, 100));
    entity.setTitle(generateRandomString(5, 15));
    entity.setShortText(generateRandomString(15, 20));
    entity.setViewCount(generateRandomNumber(10, 50));
    return entity;
  }

  public static List<CommentEntity> generateCommentList() {
    return IntStream.range(0, generateRandomNumber(5, 10))
        .mapToObj(i -> generateComment())
        .collect(Collectors.toList());
  }

  public static CommentEntity generateComment() {
    CommentEntity entity = new CommentEntity();
    entity.setId((long) generateRandomNumber(1, 100));
    entity.setText(generateRandomString(20, 30));
    return entity;
  }

  public static List<TagEntity> generateTagList() {
    return IntStream.range(0, generateRandomNumber(5, 10))
        .mapToObj(i -> generateTag())
        .collect(Collectors.toList());
  }

  public static TagEntity generateTag() {
    TagEntity entity = new TagEntity();
    entity.setId((long) generateRandomNumber(1, 100));
    entity.setName(generateRandomString(5, 15));
    return entity;
  }
}
