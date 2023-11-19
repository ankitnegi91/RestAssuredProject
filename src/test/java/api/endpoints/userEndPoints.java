package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import org.testng.annotations.Test;

import javax.swing.text.AbstractDocument;
//created to perform crud operation methods.

public class userEndPoints {

    static ResourceBundle getUrl()
    {
        ResourceBundle routes=ResourceBundle.getBundle("Routes");
        return routes;
    }

    public static Response createUser(User Payload)
    {   String createUser=getUrl().getString("createUser");
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .when().post(createUser);
        return response;
    }

    public static Response getUser(String userName)
    {
        Response response=given()
                .pathParam("username",userName)
                .when().get(Routes.getUser);
        return response;
    }

    public static Response updateUser(String userName, User Payload )
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .pathParam("username",userName)
                .when().put(Routes.updateUser);
        return response;
    }

    public static Response deleteUser(String userName )
    {
        Response response=given()
                .pathParam("username",userName)
                .when().delete(Routes.deleteUser);
        return response;
    }

}
