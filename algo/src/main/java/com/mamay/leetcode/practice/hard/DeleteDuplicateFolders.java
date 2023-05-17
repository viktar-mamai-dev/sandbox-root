package com.mamay.leetcode.practice.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeleteDuplicateFolders {
  private final Folder root = new Folder("");
  private final Map<String, Integer> keys = new HashMap<>();

  public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
    paths.forEach(this::populateFolderTree);
    generateKeys(root);
    root.getChildren().forEach(this::updateDeleteStatus);
    return paths.stream().filter(this::isValid).collect(Collectors.toList());
  }

  private void generateKeys(Folder root) {
    for (Folder child : root.getChildren()) {
      generateKeys(child);
      String key = child.generateKey();
      keys.put(key, keys.getOrDefault(key, 0) + 1);
    }
  }

  private boolean isValid(List<String> path) {
    Folder current = root;

    for (String f : path) {
      current = current.getChild(f);

      if (current.isDeleted()) return false;
    }

    return true;
  }

  private void updateDeleteStatus(Folder f) {
    List<Folder> children = f.getChildren();
    String key = f.generateKey();
    if (!children.isEmpty() && keys.getOrDefault(key, 0) > 1) {
      f.setDeleted(true);
      return;
    }

    for (Folder fold : children) {
      updateDeleteStatus(fold);
    }
  }

  private void populateFolderTree(List<String> path) {
    Folder current = root;

    for (String f : path) {
      Folder child = current.getChild(f);
      if (child == null) {
        child = new Folder(f);
        current.addChild(f, child);
      }

      current = child;
    }
  }
}

class Folder {
  private final String name;
  private final Map<String, Folder> map;
  private boolean isDeleted;

  Folder(String name) {
    this.name = name;
    map = new HashMap<>();
    isDeleted = false;
  }

  public String getName() {
    return name;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean deleted) {
    isDeleted = deleted;
  }

  public List<Folder> getChildren() {
    List<Folder> children = new ArrayList<>(map.values());
    children.sort(Comparator.comparing(Folder::getName));
    return children;
  }

  public Folder getChild(String path) {
    return map.get(path);
  }

  public void addChild(String path, Folder child) {
    map.put(path, child);
  }

  @Override
  public int hashCode() {
    int hashCode = this.name.hashCode();
    if (this.map.isEmpty()) return hashCode;

    return hashCode + getChildren().stream().map(Folder::hashCode).mapToInt(i -> i).sum();
  }

  public String generateKey() {
    List<Folder> children = getChildren();
    if (children == null || children.isEmpty()) return "";
    return children.stream()
        .map(ch -> '(' + ch.name + ch.generateKey() + ')')
        .collect(Collectors.joining());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Folder)) return false;
    Folder that = (Folder) obj;
    return this.name.equals(that.name) && getChildren().equals(that.getChildren());
  }
}
