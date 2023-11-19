package api.endpoints;
//from swagger url
public class Routes {

    public static String base_url="https://petstore.swagger.io/v2";
    //public so tht can be accesible anywhere in project, static so can be used directly with class name and no need to create obj..

    //create user
    public static String createUser=base_url+"/user";
    //get user
    public static String getUser=base_url+"/user/{username}";
    //update user
    public static String updateUser=base_url+"/user/{username}";
    //delete user
    public static String deleteUser=base_url+"/user/{username}";

    //Store module

    //pet module





}
