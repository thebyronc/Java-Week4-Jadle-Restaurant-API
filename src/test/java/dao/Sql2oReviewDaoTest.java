package dao;
import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
/**
 * Created by Guest on 1/22/18.
 */
public class Sql2oReviewDaoTest {

    private Sql2oReviewDao reviewDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addReviewSetsId() throws Exception {
        Review review = setupNewReview();
        int originalReviewId = review.getId();
        reviewDao.add(review);
        assertNotEquals(originalReviewId, review.getId());
    }

    @Test
    public void getAllReviews() throws Exception {
        Review review1 = setupNewReview();
        Review review2 = new Review("Mocha", 3, "Smells Good", 1);
        reviewDao.add(review1);
        reviewDao.add(review2);
        assertEquals(2, reviewDao.getAll().size());
    }

    @Test
    public void findReviewById() throws Exception {
        Review review = setupNewReview();
        reviewDao.add(review);
        Review foundReview = reviewDao.findById(review.getId());
        assertEquals(review, foundReview);
    }

    @Test
    public void deleteAllReviews() throws Exception {
        Review review = setupNewReview();
        reviewDao.add(review);
        reviewDao.deleteAll();
        assertEquals(0, reviewDao.getAll().size());
    }

    @Test
    public void findByRestaurantId() throws Exception {
        Review review1 = setupNewReview();
        Review review2 = new Review("Mocha", 3, "Smells Good", 1);
        reviewDao.add(review1);
        reviewDao.add(review2);
        int restaurantId = review1.getRestaurantId();
        assertEquals(2, reviewDao.getAllReviewsByRestaurant(restaurantId).size());
    }

    public Review setupNewReview() {
        return new Review("Byron Chang", 4, "The one glorious pizza", 1);
    }

}