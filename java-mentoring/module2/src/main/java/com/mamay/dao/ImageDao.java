/*
 * Copyright (c) 2023
 */
package com.mamay.dao;

import com.mamay.entity.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageDao {

  private static Long nextImageId = 1L;

  private static final Map<Long, Image> imageMap = new HashMap<Long, Image>();

  public void save(Image image) {
    Long imageId = nextImageId++;
    image.setId(imageId);
    imageMap.put(imageId, image);
  }

  public Image retrieve(Long imageId) {
    return imageMap.get(imageId);
  }
}
