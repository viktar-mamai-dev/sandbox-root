package com.mamay.constant;

/**
 * @author Viktar_Mamai
 *     <p>defines table names and columns that used at db
 */
public interface SchemaSQL {

  String NEWS_TABLE = "news";
  String NEWS_ID = "news_id";
  String NEWS_SHORT_TEXT = "short_text";
  String NEWS_VIEW_COUNT = "view_count";
  String NEWS_TITLE = "title";
  String NEWS_CREATION_DATE = "creation_date";
  String NEWS_MODIFICATION_DATE = "modification_date";

  String COMMENTS_TABLE = "comments";
  String COMMENTS_ID = "comment_id";
  String COMMENT_TEXT = "comment_text";
  String COMMENT_CREATION_DATE = "creation_date";

  String AUTHOR_TABLE = "author";
  String AUTHOR_ID = "author_id";
  String AUTHOR_NAME = "name";
  String AUTHOR_EXPIRED = "expired";

  String TAG_TABLE = "tag";
  String TAG_ID = "tag_id";
  String TAG_NAME = "tag_name";

  String NEWS_TAG_TABLE = "news_tag";
  String NEWS_AUTHOR_TABLE = "news_author";

  String TOTAL_COUNT = "total_count";
}
