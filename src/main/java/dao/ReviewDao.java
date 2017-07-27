package dao;


import models.Foodtype;
import models.Restaurant;
import models.Review;

import java.util.List;

public interface ReviewDao {

    //create
    void add(Review review); //G

    //read
    List<Review> getAllReviewsByRestaurant(int restaurantId); // J

    //update
    //omit for now

    //delete
    void deleteById(int id); //O
}
