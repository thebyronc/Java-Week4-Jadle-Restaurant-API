package dao;

import models.Restaurant;
import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oReviewDaoTest {

    private Connection conn;
    private Sql2oReviewDao reviewDao;
    private Sql2oRestaurantDao restaurantDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingReviewSetsId() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDao.add(testRestaurant);
        Review testReview = new Review("Captain Kirk", 3, "foodcoma!",testRestaurant.getId());
        reviewDao.add(testReview);
        assertEquals(1,testReview.getId());
    }

    @Test
    public void getAllReviewsByRestaurant() throws Exception {

        Restaurant testRestaurant = setupRestaurant();
        restaurantDao.add(testRestaurant);

        Restaurant newRestaurant = setupRestaurant(); //add in some extra data to see if it interferes
        restaurantDao.add(newRestaurant);

        Review testReview = new Review("Captain Kirk", 3, "foodcoma!",testRestaurant.getId());
        reviewDao.add(testReview);

        Review otherReview = new Review("Mr. Spock", 1, "passable", testRestaurant.getId());
        reviewDao.add(otherReview);

        Review secondReview = new Review("Mr Spock", 1, "passable", 1234); //to be sure the right one gets deleted, i am adding a second review for a fake restaurant.
        reviewDao.add(secondReview);

        assertEquals(testReview, reviewDao.getAllReviewsByRestaurant(testRestaurant.getId()).get(0));

    }

    //helpers

    public Restaurant setupRestaurant (){
        return new Restaurant("Fish Witch", "214 NE Broadway", "97232", "503-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com");
    }
}