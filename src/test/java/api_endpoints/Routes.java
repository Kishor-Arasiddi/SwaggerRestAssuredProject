package api_endpoints;

public class Routes {

    public static String base_url="https://petstore.swagger.io/v2/";

    //User Module
    public static String post_url=base_url+"user";
    public static String get_url=base_url+"user/{username}";
    public static String update_url=base_url+"user/{username}";
    public static String delete_url=base_url+"user/{username}";


    //Store Module
    public static String post_url2=base_url+"store/order";
    public static String get_url2=base_url+"store/order/{orderId}";
    public static String delete_url2=base_url+"store/order/{orderId}";


    //Pet Module
    public static String post_url3=base_url+"pet";
    public static String get_url3=base_url+"pet/{petId}";
    public static String delete_url3=base_url+"pet/{petId}";


}
