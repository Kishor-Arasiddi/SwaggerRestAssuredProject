package api_endpoints;

import api_payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    public static ResourceBundle getUrl() {
        //It loads the properties file     //We can also use FileInputStream with properties
        ResourceBundle resource = ResourceBundle.getBundle("Routes");
        return resource;
    }


    public static Response createUser(User payload){
        String PostUrl = getUrl().getString("Post_Url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .post(PostUrl);

         return response;
    }

    public static Response getUser(String username){
        String GetUrl = getUrl().getString("Get_Url");

        Response getResponse = given()
                .pathParam("username",username)

                .when()
                .get(GetUrl);

        return getResponse;
    }

    public static Response updateUser(String username,User payload){
        String UpdatetUrl = getUrl().getString("Update_Url");

        Response UpdateResponse = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)

                .when()
                .put(UpdatetUrl);

        return UpdateResponse;
    }

    public static Response deleteUser(String username){
        String DeleteUrl = getUrl().getString("Delete_Url");

        Response DeleteResponse = given()
                .pathParam("username",username)

                .when()
                .delete(DeleteUrl);

        return DeleteResponse;
    }
}
