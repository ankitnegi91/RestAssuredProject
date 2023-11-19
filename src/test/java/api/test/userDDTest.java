package api.test;

import api.endpoints.userEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class userDDTest {


    @Test(priority = 0,dataProvider = "Data",dataProviderClass= DataProviders.class)
    public void createTestUser(String userid, String username, String firstname,String lastname,String email,String	password,String	phone   )
    {
        int randomNo= (int)(Math.random()*900000)+100000;
        User userPayload= new User();
        userPayload.setId(Integer.parseInt(userid));
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);
        userPayload.setFirstName(firstname);
        userPayload.setLastName(lastname);
        userPayload.setUsername(username);

        Response response= userEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);


    }

    @Test(priority = 1,dataProvider = "UserNames",dataProviderClass= DataProviders.class)
    public void getTestUser(String username)
    {

        Response response= userEndPoints.getUser(username);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);


    }

    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass= DataProviders.class)
    public void deleteTestUser(String username)
    {

        Response response= userEndPoints.deleteUser(username);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);


    }


}
