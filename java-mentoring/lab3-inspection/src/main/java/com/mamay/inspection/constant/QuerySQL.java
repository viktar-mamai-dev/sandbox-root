package com.mamay.inspection.constant;

public interface QuerySQL {
    // USER QUERIES
    String FIND_ALL_USERS = "SELECT * FROM user u INNER JOIN role r ON" + " u.role_id=r.role_id";
    String FIND_USER_BY_ID = FIND_ALL_USERS + " WHERE u.user_id=?";
    String FIND_USER_BY_LOGIN = FIND_ALL_USERS + " WHERE u.login=?";
    String FIND_USER_BY_LOGIN_PASS = FIND_ALL_USERS + " WHERE u.login=? and u.password=?";
    String DELETE_USER_BY_ID = "DELETE FROM user WHERE user_id=?";
    String CREATE_USER = "INSERT INTO user(user_name, age, login, password)" + " VALUES(?, ?, ?, ?)";
    String UPDATE_USER = "UPDATE user SET user_name=?, age=?, login=?" + " WHERE user_id=?";

    // MAGAZINE QUERIES
    String MAGAZINE_ORDER = " ORDER BY m.magazine_id DESC";
    String MAGAZINE_LIMIT = "  LIMIT ?, 9";
    String FIND_ALL_MAGAZINES = "SELECT * FROM magazine m INNER JOIN period p" + " ON m.period_id=p.period_id";
    String FIND_MAGAZINE_BY_ID = FIND_ALL_MAGAZINES + " WHERE magazine_id=?";
    String DELETE_MAGAZINE_BY_ID = "DELETE FROM magazine WHERE magazine_id=?";
    String CREATE_MAGAZINE = "INSERT INTO magazine"
            + "(title, annotation, period_id, location) VALUES(?, ?, ?, ?)";
    String UPDATE_MAGAZINE = "UPDATE magazine SET title=?, annotation=?,"
            + " period_id=? WHERE magazine_id=?";
    String MAGAZINE_COUNT = "SELECT COUNT(*) AS count FROM magazine";

    // SUBSCRIPTION QUERIES
    String FIND_ALL_SUBS = "SELECT * FROM subscription s INNER JOIN magazine m"
            + " ON s.magazine_id=m.magazine_id INNER JOIN period p"
            + " ON m.period_id=p.period_id";
    String FIND_SUB_BY_ID = FIND_ALL_SUBS + " WHERE s.subscription_id=?";
    String FIND_SUBS_BY_MAGAZINE = FIND_ALL_SUBS + " WHERE s.magazine_id=?";
    String DELETE_SUB_BY_ID = "DELETE FROM subscription WHERE subscription_id=?";
    String DELETE_SUB_BY_MAGAZINE = "DELETE FROM subscription WHERE magazine_id=?";
    String CREATE_SUB = "INSERT INTO subscription"
            + "(magazine_id, sub_index, duration, price) VALUES(?, ?, ?, ?)";
    String UPDATE_SUB = "UPDATE subscription SET sub_index=?, duration=?,"
            + " price=? WHERE subscription_id=?";

    // RESERVATION QUERIES
    String FIND_ALL_RESERV = "SELECT * FROM reservation r"
            + " INNER JOIN subscription s ON r.subscription_id=s.subscription_id"
            + " INNER JOIN user u ON r.user_id=u.user_id"
            + " INNER JOIN role ro ON ro.role_id=u.role_id"
            + " INNER JOIN status st ON st.status_id=r.status_id"
            + " INNER JOIN magazine m ON m.magazine_id=s.magazine_id"
            + " INNER JOIN period p ON p.period_id=m.period_id";
    String FIND_RESERV_BY_ID = FIND_ALL_RESERV + " WHERE r.id=?";
    String FIND_RESERV_BY_USER = FIND_ALL_RESERV + " WHERE r.user_id=?";
    String DELETE_RESERV_BY_ID = "DELETE FROM reservation WHERE id=?";
    String UPDATE_RESERV = "UPDATE reservation SET status_id=? WHERE id=?";
    String CREATE_RESERV = "INSERT INTO reservation" + " (user_id, subscription_id, count) VALUES(?, ?, ?)";

    // TYPE QUERIES
    String FIND_ALL_PERIODS = "SELECT * FROM period";
    String FIND_PERIOD_BY_ID = FIND_ALL_PERIODS + " WHERE period_id=?";
    String FIND_ALL_STATUS = "SELECT * FROM status";
    String FIND_STATUS_BY_ID = FIND_ALL_STATUS + " WHERE status_id=?";

}
