package dao;

import models.Review;

import java.util.List;

/**
 * Created by Guest on 1/22/18.
 */
public interface ReviewDao {

    //Create
    void add(Review review);

    //Read
    List<Review> getAll();
    Review findById(int id);
    List<Review> getAllReviewsByRestaurant(int restaurantId);
    //Update


    //Delete
    void deleteAll();
    void deleteById(int id);

}
