package dao;

import enums.DiningStyle;
import models.Foodtype;
import models.Restaurant;
import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oRestaurantDaoTest {

    private Connection conn;
    private Sql2oRestaurantDao restaurantDao;
    private Sql2oFoodtypeDao foodtypeDao;
    private Sql2oReviewDao reviewDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        foodtypeDao = new Sql2oFoodtypeDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingFoodSetsId() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        int originalRestaurantId = testRestaurant.getId();
        restaurantDao.add(testRestaurant);
        assertNotEquals(originalRestaurantId,testRestaurant.getId());
    }

    @Test
    public void addedRestaurantsAreReturnedFromGetAll() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDao.add(testRestaurant);
        assertEquals(1, restaurantDao.getAll().size());
    }

    @Test
    public void noRestaurantsReturnsEmptyList() throws Exception {
        assertEquals(0, restaurantDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectRestaurant() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDao.add(testRestaurant);
        restaurantDao.deleteById(testRestaurant.getId());
        assertEquals(0, restaurantDao.getAll().size());
    }

    @Test
    public void getAllFoodtypesForARestaurantReturnsFoodtypesCorrectly() throws Exception {
            Foodtype testFoodtype  = new Foodtype("Seafood");
            foodtypeDao.add(testFoodtype);

            Foodtype otherFoodtype  = new Foodtype("Bar Food");
            foodtypeDao.add(otherFoodtype);

            Restaurant testRestaurant = setupRestaurant();
            restaurantDao.add(testRestaurant);
            restaurantDao.addRestaurantToFoodtype(testRestaurant,testFoodtype);
            restaurantDao.addRestaurantToFoodtype(testRestaurant,otherFoodtype);
        System.out.println(restaurantDao.getAllFoodtypesForARestaurant(testRestaurant.getId()));

            Foodtype[] foodtypes = {testFoodtype, otherFoodtype}; //oh hi what is this?

            assertEquals(restaurantDao.getAllFoodtypesForARestaurant(testRestaurant.getId()), Arrays.asList(foodtypes));
        }

    @Test
    public void deleteingRestaurantAlsoUpdatesJoinTable() throws Exception {
        Foodtype testFoodtype  = new Foodtype("Seafood");
        foodtypeDao.add(testFoodtype);

        Restaurant testRestaurant = setupRestaurant();
        restaurantDao.add(testRestaurant);

        Restaurant altRestaurant = setupAltRestaurant();
        restaurantDao.add(altRestaurant);

        restaurantDao.addRestaurantToFoodtype(testRestaurant,testFoodtype);
        restaurantDao.addRestaurantToFoodtype(altRestaurant, testFoodtype);

        restaurantDao.deleteById(testRestaurant.getId());
        assertEquals(0, restaurantDao.getAllFoodtypesForARestaurant(testRestaurant.getId()).size());
    }

    //helpers

    public Restaurant setupRestaurant (){
        return new Restaurant("Fish Witch", "214 NE Broadway", "97232", "503-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com", "fishwitch.jpg", DiningStyle.CASUAL );
    }

    public Restaurant setupAltRestaurant (){
        return new Restaurant("Fish Witch", "214 NE Broadway", "97232", "503-402-9874", DiningStyle.CASUAL);
    }
}