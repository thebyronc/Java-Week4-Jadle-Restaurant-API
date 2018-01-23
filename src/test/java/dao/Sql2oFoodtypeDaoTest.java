package dao;

import models.Foodtype;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by Guest on 1/22/18.
 */
public class Sql2oFoodtypeDaoTest {

    private Sql2oFoodtypeDao foodtypeDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        foodtypeDao = new Sql2oFoodtypeDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingFoodTypeSetsId() throws Exception {
        Foodtype foodtype = setupNewFoodtype();
        int originalFoodtypeId = foodtype.getId();
        foodtypeDao.add(foodtype);
        assertNotEquals(originalFoodtypeId, foodtype.getId());
    }

//    @Test
//    public void getAll() throws Exception {
//    }
//
//    @Test
//    public void findById() throws Exception {
//    }
//
//    @Test
//    public void update() throws Exception {
//    }
//
//    @Test
//    public void deleteAll() throws Exception {
//    }
//
//    @Test
//    public void deleteById() throws Exception {
//    }

    public Foodtype setupNewFoodtype() {
        return new Foodtype("Pizza");
    }
}