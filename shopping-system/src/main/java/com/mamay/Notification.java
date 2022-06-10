package com.mamay;

import java.time.LocalDateTime;

public class Notification {
    private int notificationId;
    private LocalDateTime createdOn;
    private String content;

    public boolean sendNotification(Account account) {return false;}
}
