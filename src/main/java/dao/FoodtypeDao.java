package dao;

import models.Foodtype;
import models.Restaurant;

import java.util.List;

/**
 * Created by Guest on 1/22/18.
 */
public interface FoodtypeDao {

    //Create
    void add(Foodtype foodtype);
    void addFoodtypeToRestaurant(Foodtype foodtype, Restaurant restaurant); // E

    //Read
    List<Foodtype> getAll();
    Foodtype findById(int id);
    List<Restaurant> getAllRestaurantsForAFoodtype(int id); //E we will implement this NOW :)

    //Update
    void update(int id, String name);

    //Delete
    void deleteAll();
    void deleteById(int id);


}
