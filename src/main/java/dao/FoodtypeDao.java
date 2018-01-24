package dao;

import models.Foodtype;
import models.Restaurant;

import java.util.List;

/**
 * Created by Guest on 1/22/18.
 */
public interface FoodtypeDao {

    //Create
    void add(Foodtype foodtype); // Q
    void addFoodtypeToRestaurant(Foodtype foodtype, Restaurant restaurant); // E

    //Read
    List<Foodtype> getAll(); // we may need this in the future. Even though it does not 100% match a specific user story, it should be implemented.
    List<Restaurant> getAllRestaurantsForAFoodtype(int id); //E we will implement this NOW :)

    //Update
//    void update(int id, String name);

    //Delete
//    void deleteAll();
    void deleteById(int id);


}
