package com.mamay.leetcode.practice.medium3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TodoList {

  private final HashMap<Integer, TreeSet<Task>> map;

  public TodoList() {
    map = new HashMap<>();
  }

  public int addTask(int userId, String taskDescription, int dueDate, List<String> tags) {
    Task task = new Task(taskDescription, dueDate, tags);
    TreeSet<Task> set = map.getOrDefault(userId, new TreeSet<>());
    set.add(task);
    map.put(userId, set);
    return task.id;
  }

  public List<String> getAllTasks(int userId) {
    TreeSet<Task> treeSet = map.get(userId);
    if (treeSet == null || treeSet.isEmpty()) {
      return new ArrayList<>();
    }
    return treeSet.stream().map(task -> task.description).collect(Collectors.toList());
  }

  public List<String> getTasksForTag(int userId, String tag) {
    TreeSet<Task> treeSet = map.get(userId);
    if (treeSet == null || treeSet.isEmpty()) {
      return new ArrayList<>();
    }
    return treeSet.stream()
        .filter(task -> task.tags.contains(tag))
        .map(task -> task.description)
        .collect(Collectors.toList());
  }

  public void completeTask(int userId, int taskId) {
    TreeSet<Task> treeSet = map.get(userId);
    if (treeSet == null || treeSet.isEmpty()) return;
    Iterator<Task> iterator = treeSet.iterator();
    while (iterator.hasNext()) {
      Task task = iterator.next();
      if (task.id == taskId) {
        iterator.remove();
        break;
      }
    }
  }

  private static class Task implements Comparable<Task> {
    private static int nextId = 1;
    private final String description;
    private final int dueDate, id;
    private final List<String> tags;

    public Task(String description, int dueDate, List<String> tags) {
      this.description = description;
      this.dueDate = dueDate;
      this.tags = tags;
      this.id = nextId++;
    }

    @Override
    public int compareTo(Task o) {
      return Integer.compare(this.dueDate, o.dueDate);
    }
  }
}
