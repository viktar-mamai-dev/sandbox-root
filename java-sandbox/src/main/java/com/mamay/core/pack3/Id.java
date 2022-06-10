package com.mamay.core.pack3;

/**
 * Created by admin on 9/26/2014.
 */
public class Id {
    private Object forumId;
    private int topicId;
    private int userId;

    public <T1> Id(T1 forumId) {
       this.forumId = forumId;
    }
    public <T, K>  void insert(T t, int a, K key){
    }

    public void counter(int... arg){
        System.out.println(arg.length);
    }

    public void counter(int a1, int a2){
        System.out.println(a1+a2);
    }
    public void select(String s, int... arg){
        System.out.println(arg.length);
    }
}
