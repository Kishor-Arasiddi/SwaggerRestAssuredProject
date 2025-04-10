package api_test;

import api_endpoints.PetEndPoints;
import api_payload.Pet;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetTest {

    Faker faker;
    Pet petPayLoad;

    @BeforeClass
    public void setUp(){
        faker=new Faker();
        petPayLoad=new Pet();

        petPayLoad.setId(faker.idNumber().hashCode());
        petPayLoad.setName(faker.name().username());
        petPayLoad.setStatus("available");
    }

    @Test(priority = 1)
    public void testAddPet(){
        Response postResponse = PetEndPoints.addPet(petPayLoad);
        postResponse.then().log().all();

        Assert.assertEquals(postResponse.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testGetPet(){
        Response getResponse = PetEndPoints.getPet(this.petPayLoad.getId());
        getResponse.then().log().all();

        Assert.assertEquals(getResponse.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testDeletePet(){
        Response deleteResponse = PetEndPoints.deletePet(this.petPayLoad.getId());
        deleteResponse.then().statusCode(200);

        Assert.assertEquals(deleteResponse.getStatusCode(),200);
    }
}
