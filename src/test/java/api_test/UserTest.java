package api_test;

import api_endpoints.UserEndPoints;
import api_payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void setupData(){
        faker=new Faker();
        userPayload=new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testPostUser(){
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testGetUser(){
        Response getresponse = UserEndPoints.getUser(this.userPayload.getUsername());
        getresponse.then().log().all();

        Assert.assertEquals(getresponse.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUser(){
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response updateResponse = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        updateResponse.then().log().body().statusCode(200); //this is also validation. It is by default validate the specific response in RestAssured.

        Assert.assertEquals(updateResponse.getStatusCode(),200);

        //Checking data after the update
        Response responseAfterUpdate = UserEndPoints.getUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
    }

    @Test(priority = 4)
    public void testDeleteUser(){
        Response deleteResponse = UserEndPoints.deleteUser(this.userPayload.getUsername());
        deleteResponse.then().log().all();

        Assert.assertEquals(deleteResponse.getStatusCode(),200);
    }

}
