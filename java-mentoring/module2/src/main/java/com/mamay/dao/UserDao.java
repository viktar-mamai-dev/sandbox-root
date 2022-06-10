package com.mamay.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mamay.entity.Image;
import com.mamay.entity.User;

public class UserDao {

    private static Long nextUserId = 1l;

    private static Map<Long, User> userMap = new HashMap<Long, User>();

    static {
        userMap.put(nextUserId++, new User("Viktar", "Gugo", "Viktar2", "viktar.gugo@doodle.com"));
        userMap.put(nextUserId++, new User("Bernard", "Show", "showmustgoON", "bernard@doodle.com"));
        userMap.put(nextUserId++, new User("Richard", "Show", "showmustgoOFF", "richard@doodle.com"));
        userMap.put(nextUserId++, new User("Jack", "London", "londonIsTheCapital", "jackieRider@para.com"));
        userMap.put(nextUserId++, new User("Agata", "Kristie", "AuntAgata123", "agata123@para.com"));
    }

    public List<User> retrieveAll() {
        return new ArrayList<User>(userMap.values());
    }

    public User retrieve(Long userId) {
        return userMap.get(userId);
    }

    public Long create(User user) {
        Long userId = nextUserId++;
        user.setId(userId);
        userMap.put(userId, user);
        return userId;
    }

    public void update(User user) {
        userMap.put(user.getId(), user);
    }

    public void delete(Long userId) {
        userMap.remove(userId);
    }

    public void upload(Long userId, Image image) {
        userMap.get(userId).setImage(image);
    }
}
