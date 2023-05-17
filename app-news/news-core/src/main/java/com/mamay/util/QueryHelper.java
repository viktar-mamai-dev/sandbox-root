package com.mamay.util;

import com.mamay.dto.NewsSearchCriteria;
import java.util.List;
import java.util.stream.Collectors;

public class QueryHelper {

  public static String convertListToString(List<Long> tagIdList) {
    return tagIdList.stream().map(String::valueOf).collect(Collectors.joining(", "));
  }

  public static String wrapQueryToRownum(String query) {
    String begin = "SELECT * FROM ( SELECT n.*, ROWNUM rn, count(*) OVER () total_count FROM (";
    String end = ") n ) WHERE rn BETWEEN ? AND ?";
    return begin + query + end;
  }

  /**
   * @param filteredItem - object which is used as a filter
   * @param offset - offset in rows for which entity identifier to be selected
   *     <p>For example: offset = 1 means we want to retrieve next id
   *     <p>similary offset = -1 means we want to retrieve previous id
   * @return - string query
   */
  public static String loadOffsetId(NewsSearchCriteria filteredItem, int offset) {
    List<Long> tagIdList = filteredItem.getTagIdList();
    Long authorId = filteredItem.getAuthorId();
    boolean isAuthorNull = authorId == null || authorId == 0;
    boolean isTagNull = tagIdList == null || tagIdList.isEmpty();
    String prefixQuery =
        "WITH tabl as (SELECT n.news_id as news_id, ROWNUM as rn FROM (SELECT n.news_id FROM"
            + " news n LEFT JOIN (SELECT c.news_id, COUNT(c.news_id) as comment_count FROM COMMENTS c GROUP BY "
            + "c.news_id) c ON n.news_id=c.news_id WHERE 1=1 ";
    StringBuilder builder = new StringBuilder(prefixQuery);
    if (!isAuthorNull) {
      builder.append(
          "and n.news_id IN (SELECT na.news_id FROM NEWS_AUTHOR na WHERE na.author_id=?) ");
    }
    if (!isTagNull) {
      builder.append("and n.news_id IN (SELECT nt.news_id FROM news_tag nt WHERE nt.tag_id in (");
      builder.append(convertListToString(tagIdList)).append(")) ");
    }
    String postfixQuery =
        "ORDER BY c.comment_count DESC NULLS LAST, n.modification_date DESC) n ) "
            + "SELECT tabl.news_id FROM tabl WHERE tabl.rn = (SELECT tabl.rn FROM tabl WHERE tabl.news_id=?)";
    String offsetPart = offset > 0 ? "+" + offset : String.valueOf(offset);
    builder.append(postfixQuery).append(offsetPart);
    return builder.toString();
  }

  /**
   * @param filteredItem - object which is used as a filter
   * @return - string query
   */
  public static String loadOrderedList(NewsSearchCriteria filteredItem) {
    List<Long> tagIdList = filteredItem.getTagIdList();
    Long authorId = filteredItem.getAuthorId();
    boolean isAuthorNull = authorId == null || authorId == 0;
    boolean isTagNull = tagIdList == null || tagIdList.isEmpty();
    String prefixQuery =
        "SELECT n.news_id FROM news n LEFT JOIN (SELECT c.news_id, COUNT(c.news_id) as "
            + "comment_count FROM COMMENTS c GROUP BY c.news_id) c ON n.news_id=c.news_id WHERE 1=1 ";
    StringBuilder builder = new StringBuilder(prefixQuery);
    if (!isAuthorNull) {
      builder.append(
          "and n.news_id IN (SELECT na.news_id FROM news_author na WHERE na.author_id=?) ");
    }
    if (!isTagNull) {
      builder.append("and n.news_id IN (SELECT nt.news_id FROM news_tag nt WHERE nt.tag_id in (");
      builder.append(convertListToString(tagIdList)).append(")) ");
    }
    builder.append(" ORDER BY c.comment_count DESC NULLS LAST, n.modification_date DESC");
    return builder.toString();
  }
}
