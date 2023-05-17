package com.mamay.leetcode.practice.medium3;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class MovieRentingSystem {
  private final Comparator<Entry> comparator =
      Comparator.comparingInt(Entry::getPrice)
          .thenComparingInt(Entry::getShop)
          .thenComparingInt(Entry::getMovie);
  TreeSet<Entry> rented = new TreeSet<>(comparator);
  HashMap<Integer, Set<Entry>> unrentedMap = new HashMap<>(); // Map moveId -> TreeSet of Entries
  HashMap<Integer, HashMap<Integer, Integer>> movieToPrice =
      new HashMap<>(); // Map (shop, movie) -> price

  public MovieRentingSystem(int n, int[][] entries) {
    for (int[] entry : entries) {
      int shop = entry[0], movie = entry[1], price = entry[2];
      Entry movieEntry = new Entry(price, shop, movie);
      unrentedMap.computeIfAbsent(movie, x -> new TreeSet<>(comparator)).add(movieEntry);
      HashMap<Integer, Integer> map = movieToPrice.getOrDefault(shop, new HashMap<>());
      map.put(movie, price);
      movieToPrice.put(shop, map);
    }
  }

  public List<Integer> search(int movie) {
    return unrentedMap.getOrDefault(movie, Collections.emptySet()).stream()
        .limit(5)
        .map(Entry::getShop)
        .collect(Collectors.toList());
  }

  public void rent(int shop, int movie) {
    int price = movieToPrice.get(shop).get(movie);
    Entry entry = new Entry(price, shop, movie);
    unrentedMap.get(movie).remove(entry);
    rented.add(entry);
  }

  public void drop(int shop, int movie) {
    int price = movieToPrice.get(shop).get(movie);
    Entry entry = new Entry(price, shop, movie);
    unrentedMap.get(movie).add(entry);
    rented.remove(entry);
  }

  public List<List<Integer>> report() {
    return rented.stream().limit(5).map(e -> List.of(e.shop, e.movie)).collect(Collectors.toList());
  }

  static final class Entry {
    private final int price, shop, movie;

    public Entry(int price, int shop, int movie) {
      this.price = price;
      this.shop = shop;
      this.movie = movie;
    }

    public int getPrice() {
      return price;
    }

    public int getShop() {
      return shop;
    }

    public int getMovie() {
      return movie;
    }
  }
}
