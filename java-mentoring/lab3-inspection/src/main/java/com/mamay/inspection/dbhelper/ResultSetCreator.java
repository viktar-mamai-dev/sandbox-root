package com.mamay.inspection.dbhelper;

import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.entity.Period;
import com.mamay.inspection.entity.Reservation;
import com.mamay.inspection.entity.Status;
import com.mamay.inspection.entity.Subscription;
import com.mamay.inspection.entity.User;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class ResultSetCreator {

    public static Subscription createSubscription(ResultSet rs) {
        Subscription sub = null;
        try {
            int id = rs.getInt("subscription_id");
            String subIndex = rs.getString("sub_index");
            String duration = rs.getString("duration");
            String price = rs.getString("price");
            sub = new Subscription(id, subIndex, duration, price);
            Magazine mag = createMagazine(rs);
            sub.setMagazine(mag);
        } catch (SQLException e) {
            log.error(e);
        }
        return sub;
    }

    public static Magazine createMagazine(ResultSet rs) {
        Magazine magazine = null;
        try {
            int id = rs.getInt("magazine_id");
            String title = rs.getString("title");
            String annotation = rs.getString("annotation");
            String location = rs.getString("location");
            magazine = new Magazine(id, title, annotation);
            if (location != null && !"".equals(location.trim())) {
                magazine.setLocation(location);
            }
            Period period = createPeriod(rs);
            magazine.setPeriod(period);
        } catch (SQLException e) {
            log.error(e);
        }
        return magazine;
    }

    public static Period createPeriod(ResultSet rs) {
        Period period = null;
        try {
            int id = rs.getInt("period_id");
            String periodicity = rs.getString("periodicity");
            period = new Period(id, periodicity);
        } catch (SQLException e) {
            log.error(e);
        }
        return period;
    }

    public static User createUser(ResultSet rs) {
        User user = null;
        try {
            int id = rs.getInt("user_id");
            String userName = rs.getString("user_name");
            int age = rs.getInt("age");
            String login = rs.getString("login");
            user = new User(id, userName, age, login);
            String rolename = rs.getString("role_name");
            user.setRoleName(rolename);
        } catch (SQLException e) {
            log.error(e);
        }
        return user;
    }

    public static Reservation createReservation(ResultSet rs) {
        Reservation reserv = null;
        try {
            int id = rs.getInt("id");
            int count = rs.getInt("count");
            reserv = new Reservation(id, count);
        } catch (SQLException e) {
            log.error(e);
        }
        User user = ResultSetCreator.createUser(rs);
        Subscription sub = ResultSetCreator.createSubscription(rs);
        Status status = ResultSetCreator.createStatus(rs);
        reserv.setUser(user);
        reserv.setSubscription(sub);
        reserv.setStatus(status);
        return reserv;
    }

    public static Status createStatus(ResultSet rs) {
        Status status = null;
        try {
            int id = rs.getInt("status_id");
            String description = rs.getString("status_description");
            status = new Status(id, description);
        } catch (SQLException e) {
            log.error(e);
        }
        return status;
    }
}
