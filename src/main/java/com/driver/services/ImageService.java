package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(description, dimensions, blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String[] screenSizeArr = screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();
        String imageDimension = image.getDimensions();
        String[] imageSizeArr = imageDimension.split("X");
//        int screenArea = Integer.parseInt(screenSizeArr[0]) * Integer.parseInt(screenSizeArr[1]);
//        int imageArea = Integer.parseInt(imageSizeArr[0]) * Integer.parseInt(imageSizeArr[1]);
//        return screenArea / imageArea;
        int sl = Integer.parseInt(screenSizeArr[0]);
        int sb = Integer.parseInt(screenSizeArr[1]);

        int il = Integer.parseInt(imageSizeArr[0]);
        int ib = Integer.parseInt(imageSizeArr[1]);

        return (sl /il) * (sb/ ib);
    }
}
