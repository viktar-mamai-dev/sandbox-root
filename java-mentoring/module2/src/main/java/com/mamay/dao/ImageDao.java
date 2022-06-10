package com.mamay.dao;

import java.util.HashMap;
import java.util.Map;

import com.mamay.entity.Image;

public class ImageDao {

    private static Long nextImageId = 1l;

    private static Map<Long, Image> imageMap = new HashMap<Long, Image>();

    public void save(Image image) {
        Long imageId = nextImageId++;
        image.setId(imageId);
        imageMap.put(imageId, image);
    }

    public Image retrieve(Long imageId) {
        return imageMap.get(imageId);
    }
}
