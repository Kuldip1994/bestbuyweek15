package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jdk.management.resource.internal.TotalResourceContext;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIT(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port= 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001(){
        int limit = response.extract().path("limit");
        System.out.println("Value of limit is:"+limit);
        Assert.assertEquals(10,limit);

    }
    //2. Extract the total
    @Test
    public void test002(){
        int total = response.extract().path("total");
        System.out.println("Total is "+total);
        Assert.assertEquals(1561,total);
    }
    //3. Extract the name of 5th store
    @Test
    public void test003(){
        String name=response.extract().path("data[4].name");
        System.out.println("Name of the 5th Store is "+name);
        Assert.assertEquals("Maplewood",name);
    }

    //4. Extract the names of all the store
    @Test
    public void test004(){
        List<String> allstore = response.extract().path("data.name");
        System.out.println("List of all the stores:"+allstore);
        //System.out.println(allstore.get(8)); // this also works
        for (String a :allstore) {
            if (a.equals(8)){
                Assert.assertTrue(true);

            }

        }

    }
    //5. Extract the storeId of all the store
    @Test
    public void test005(){
        List<Integer> StoresID = response.extract().path("data.id");
        System.out.println("Store ID for all the stores"+StoresID);
        for (Integer b:StoresID) {
            if (b.equals(1561)){
                Assert.assertTrue(true);
            }

        }
    }

    //6. Print the size of the data list
    @Test
    public void test006(){
        List<Integer>datasize=response.extract().path("data");
        int size =datasize.size();
        System.out.println("The size of data list it "+size);
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007(){
        List<HashMap<String,?>> storeName =response.extract().path("data.findAll{it.name =='St Cloud'}");
        System.out.println(storeName);

    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println(address);
    }

    //9. Get all the services of 8th store
    @Test
    public void test009(){
        List<HashMap<String,?>> services = response.extract().path("data[7].services");
        System.out.println(services);

    }
    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010(){

        List<HashMap<?, ?>> storeservice = response.extract().path("data.findAll{it.storeservices ='Windows Store'}.services");
        System.out.println(storeservice);
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011(){
        List<HashMap<?,?>> storeid = response.extract().path("data.services.storeservices.storeId");
        System.out.println(storeid);
    }
    //12. Get id of all the store
    @Test
    public void test012(){
        List<HashMap<?,?>> idofall = response.extract().path("data.id");
        System.out.println("ID of all the stores"+idofall);
    }
    //13. Find the store names Where state = ND
    @Test
    public void test013(){

        List<String> storename = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println(storename);

    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014(){

        List<Integer> totalnoofservice = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println(totalnoofservice);

    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015(){

        List<Integer> createdAt = response.extract().path("data.findAll{it.name ='Windows Store'}.services");
        System.out.println(createdAt);
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016(){
        List<String> allserice = response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println(allserice);
    }

    //17. Find the zip of all the store
    @Test
    public void test017(){
        List<Integer> zip = response.extract().path("data.zip");
        System.out.println(zip);
    }

    //18. Find the zip of store name = Roseville
   @Test
    public void test018(){
        List<Integer> zips = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
       System.out.println(zips);

    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019(){
        List<?> storeserviess =response.extract().path("data.findAll{it.name ='Magnolia Home Theater'}.services");
        System.out.println(storeserviess);
    }

    //20. Find the lat of all the stores

    @Test
    public void test020(){
        List<Integer>  latof = response.extract().path("data.lat");
        System.out.println(latof);

    }












}
