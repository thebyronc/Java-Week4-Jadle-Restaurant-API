package dao;


import models.Foodtype;
import models.Restaurant;
import models.Review;

import java.util.List;

public interface RestaurantDao {

    //create
    void add (Restaurant restaurant); //L
    void addRestaurantToFoodtype(Restaurant restaurant, Foodtype foodtype); //D

    //read
    List<Restaurant> getAll(); //A
    List<Foodtype> getAllFoodtypesForARestaurant(int restaurantId); //D - we will implement this soon.

    Restaurant findById(int id); //B

    //update
    void update(int id, String name, String address, String zipcode, String phone, String website, String email, String image); //N

    //delete
    void deleteById(int id); //M
}
