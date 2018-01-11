package dao;

import models.Foodtype;
import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oFoodtypeDaoTest {

    private Sql2oFoodtypeDao foodtypeDao;
    private Sql2oRestaurantDao restaurantDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        foodtypeDao = new Sql2oFoodtypeDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingFoodSetsId() throws Exception {
        Foodtype testFoodtype = setupNewFoodtype();
        int originalFoodtypeId = testFoodtype.getId();
        foodtypeDao.add(testFoodtype);
        assertNotEquals(originalFoodtypeId,testFoodtype.getId());
    }

    @Test
    public void addedFoodtypesAreReturnedFromGetAll() throws Exception {
        Foodtype testfoodtype = setupNewFoodtype();
        foodtypeDao.add(testfoodtype);
        assertEquals(1, foodtypeDao.getAll().size());
    }

    @Test
    public void noFoodtypesReturnsEmptyList() throws Exception {
        assertEquals(0, foodtypeDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectFoodtype() throws Exception {
        Foodtype foodtype = setupNewFoodtype();
        foodtypeDao.add(foodtype);
        foodtypeDao.deleteById(foodtype.getId());
        assertEquals(0, foodtypeDao.getAll().size());
    }

    @Test
    public void addFoodTypeToRestaurantAddsTypeCorrectly() throws Exception {

        Restaurant testRestaurant = setupRestaurant();
        Restaurant altRestaurant = setupAltRestaurant();

        restaurantDao.add(testRestaurant);
        restaurantDao.add(altRestaurant);

        Foodtype testFoodtype = setupNewFoodtype();

        foodtypeDao.add(testFoodtype);

        foodtypeDao.addFoodtypeToRestaurant(testFoodtype, testRestaurant);
        foodtypeDao.addFoodtypeToRestaurant(testFoodtype, altRestaurant);

        assertEquals(2, foodtypeDao.getAllRestaurantsForAFoodtype(testFoodtype.getId()).size());
    }

    @Test
    public void deleteingFoodtypeAlsoUpdatesJoinTable() throws Exception {

        Restaurant testRestaurant = setupRestaurant();

        restaurantDao.add(testRestaurant);

        Foodtype testFoodtype = setupNewFoodtype();
        Foodtype otherFoodType = new Foodtype("Japanese");

        foodtypeDao.add(testFoodtype);
        foodtypeDao.add(otherFoodType);

        foodtypeDao.addFoodtypeToRestaurant(testFoodtype, testRestaurant);
        foodtypeDao.addFoodtypeToRestaurant(otherFoodType,testRestaurant);

        foodtypeDao.deleteById(testRestaurant.getId());
        assertEquals(0, foodtypeDao.getAllRestaurantsForAFoodtype(testFoodtype.getId()).size());
    }


    public void getAllRestaurantsByFoodtypeReturnsRestaurantsCorrectly() throws Exception {
        Foodtype foodtype = setupNewFoodtype();
        foodtypeDao.add(foodtype);
        int foodtypeId = foodtype.getId();
        Restaurant newRestaurant = setupRestaurant();
        Restaurant otherRestaurant = setupAltRestaurant();
        Restaurant thirdRestaurant = new Restaurant("La Iconique", "1300 NW Raleigh", "97202", "503-102-1874", "http://laiconique.com", "info@laiconique.com");
        restaurantDao.add(newRestaurant);
        restaurantDao.add(otherRestaurant); //we are not adding restaurant 3 so we can test things precisely.
        foodtypeDao.addFoodtypeToRestaurant(foodtype,newRestaurant);
        foodtypeDao.addFoodtypeToRestaurant(foodtype,otherRestaurant);


        assertTrue(foodtypeDao.getAllRestaurantsForAFoodtype(foodtypeId).size() == 2);
        assertTrue(foodtypeDao.getAllRestaurantsForAFoodtype(foodtypeId).contains(newRestaurant));
        assertTrue(foodtypeDao.getAllRestaurantsForAFoodtype(foodtypeId).contains(otherRestaurant));
        assertFalse(foodtypeDao.getAllRestaurantsForAFoodtype(foodtypeId).contains(thirdRestaurant)); //things are accurate!
    }

    public Foodtype setupNewFoodtype(){
        return new Foodtype("Sushi");
    }

    public Restaurant setupRestaurant (){
        return new Restaurant("Fish Witch", "214 NE Broadway", "97232", "503-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com");
    }

    public Restaurant setupAltRestaurant (){
        return new Restaurant("Fish Witch", "214 NE Broadway", "97232", "503-402-9874");
    }

}