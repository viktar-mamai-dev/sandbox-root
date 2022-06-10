package com.mamay.core.pack3;

/**
 * Created by admin on 9/26/2014.
 */
public class Message <K> {
    private K messageId;
    private String content;

    public Message(K messageId, String content) {

        this.messageId = messageId;
        this.content = content;
    }

    public boolean equalsMessageById (Message<?> m){
        return this.messageId.equals(m.messageId);
    }
    public K getMessageId() {
        return messageId;
    }

    public void setMessageId(K messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", content='" + content + '\'' +
                '}';
    }
}
