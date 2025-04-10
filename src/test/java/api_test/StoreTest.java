package api_test;

import api_endpoints.StoreEndPoints;
import api_payload.Store;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StoreTest {

    Faker faker;
    Store storePayload;

    @BeforeClass
    public void setUp(){
        faker=new Faker();
        storePayload=new Store();

       storePayload.setId(faker.idNumber().hashCode());
       storePayload.setPetId(faker.number().numberBetween(8,25));
       storePayload.setQuantity(faker.number().numberBetween(5,20));
       storePayload.setShipDate("2025-04-10T10:00:00.000Z");
       storePayload.setStatus("complete");
    }

    @Test(priority = 1)
    public void testPostOrder(){
        Response postResponse = StoreEndPoints.postOrder(storePayload);
        postResponse.then().log().all();

        Assert.assertEquals(postResponse.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testGetOrder(){
        Response getResponse = StoreEndPoints.getOrder(this.storePayload.getId());
        getResponse.then().log().all();

        Assert.assertEquals(getResponse.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testDeleteOrder(){
        Response deleteResponse = StoreEndPoints.deleteOrder(this.storePayload.getId());
        deleteResponse.then().statusCode(200);

        Assert.assertEquals(deleteResponse.getStatusCode(),200);
    }
}
