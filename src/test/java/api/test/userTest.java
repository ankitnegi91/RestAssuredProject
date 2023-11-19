package api.test;

import api.endpoints.userEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class userTest {
User userPayload;
Logger logger;
    @BeforeClass
    public void setupData(){
        int randomNo= (int)(Math.random()*900000)+100000;
        userPayload= new User();
        userPayload.setId(randomNo);
        userPayload.setUserStatus(1);
        userPayload.setEmail("email"+randomNo+"@gmail.com");
        userPayload.setPassword(String.valueOf(randomNo));
        userPayload.setPhone(String.valueOf(randomNo));
        userPayload.setFirstName("firstName"+randomNo);
        userPayload.setLastName("lastName"+randomNo);
        userPayload.setUsername("username"+randomNo);
        logger= LogManager.getLogger(this.getClass());
    }




    @Test(priority = 0)
    public void createTestUser()
    {   logger.info("create test started");
        logger.debug("test");
        Response response=userEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
        logger.info("create test ended");


    }
    @Test(priority = 1)
    public void getTestUser()
    {
        logger.info("get test started");
        Response response=userEndPoints.getUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
        logger.info("get test ended");
    }


    @Test(priority = 2)
    public void updateTestUser()
    {   logger.info("updated test started");
        userPayload.setFirstName("firstnameupdated");
        Response response=userEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
        response=userEndPoints.getUser(this.userPayload.getUsername());
        response.then().log().all();
        logger.info("update test ended");

    }


    @Test(priority = 3)
    public void deleteTestUser()
    {logger.info("delete test started");
        Response response=userEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
        logger.info("delete test ended");
    }

}
