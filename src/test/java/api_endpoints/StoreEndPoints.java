package api_endpoints;

import api_payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class StoreEndPoints {

public static Response postOrder(Store payload){
    Response postResponse = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(payload)

            .when()
            .post(Routes.post_url2);

    return postResponse;
}

public static Response getOrder(int id){
    Response getResponse = given()
            .pathParam("orderId", id)

            .when()
            .get(Routes.get_url2);

    return getResponse;
}

public static Response deleteOrder(int id){
    Response deleteResponse = given()
            .pathParam("orderId", id)

            .when()
            .delete(Routes.delete_url2);

    return deleteResponse;
}
}
