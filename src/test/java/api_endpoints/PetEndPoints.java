package api_endpoints;

import api_payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PetEndPoints {

    public static Response addPet(Pet payLoad){
        Response postResponse = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payLoad)

                .when()
                .post(Routes.post_url3);

        return postResponse;
    }

    public static Response getPet(int id){
        Response getResponse = given()
                .pathParam("petId", id)

                .when()
                .get(Routes.get_url3);

        return getResponse;
    }

    public static Response deletePet(int id){
        Response deleteResponse = given()
                .pathParam("petId", id)

                .when()
                .delete(Routes.delete_url3);

        return deleteResponse;
    }
}
