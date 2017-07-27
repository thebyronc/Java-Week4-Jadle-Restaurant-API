package dao;


import models.Foodtype;
import models.Restaurant;
import models.Review;

import java.util.List;

public interface FoodtypeDao {

    //create
    void add(Foodtype foodtype); // Q

    //read
    List<Foodtype> getAll(); // we may need this in the future. Even though it does not 100% match a specific user story, it should be implemented.
    // List<Restaurant> getAllRestaurantsForAFoodtype(int id); //E we will implement this soon.

    //update
    //omit for now

    //delete
    void deleteById(int id); //see above
}
