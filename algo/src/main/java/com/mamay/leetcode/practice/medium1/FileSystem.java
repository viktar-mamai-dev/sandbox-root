package com.mamay.leetcode.practice.medium1;

import java.util.HashMap;

class FileSystem {

    private final String SEPARATOR = "/";
    private final FileSystemNode rootNode = new FileSystemNode(0);

    public FileSystem() {

    }

    public boolean createPath(String path, int value) {
        FileSystemNode current = rootNode;
        String[] pathArray = path.split(SEPARATOR);
        int len = pathArray.length;
        for (int i = 1; i < len; i++) {
            String folder = pathArray[i];
            FileSystemNode child = current.childList.get(folder);
            if (child == null) {
                if (i < len - 1) return false;
                child = new FileSystemNode(value);
                current.childList.put(folder, child);
            } else {
                if (i == len - 1) return false;
            }
            current = child;
        }
        return true;
    }

    public int get(String path) {
        FileSystemNode current = rootNode;
        String[] pathArray = path.split(SEPARATOR);
        int len = pathArray.length;
        for (int i = 1; i < len; i++) {
            String folder = pathArray[i];
            current = current.childList.get(folder);
            if (current == null) {
                return -1;
            }
        }
        return current.value;
    }
}

class FileSystemNode {
    public Integer value;
    public HashMap<String, FileSystemNode> childList = new HashMap<>();
    public FileSystemNode(Integer value) {
        this.value = value;
    }
}

class FileSystemRunner {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        System.out.println(fileSystem.createPath("/a", 1));
        System.out.println(fileSystem.get("/a"));
    }
}