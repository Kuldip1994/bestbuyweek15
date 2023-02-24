package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIT() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);

    }

    //21. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("Value of limit is :" + limit);
        Assert.assertEquals(10, limit);
    }

    //22. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("Total is: " + total);
        Assert.assertEquals(51957, total);
    }

    //23. Extract the name of 5th product
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("Name of the 5th product is " + name);
        Assert.assertEquals("Duracell - C Batteries (4-Pack)", name);

    }

    //24. Extract the names of all the products
    @Test
    public void test004() {
        List<String> allproducts = response.extract().path("data.name");
        System.out.println("List of all the stores" + allproducts);
        for (String a : allproducts) {
            if (a.equals(10)) {
                Assert.assertTrue(true);
            }

        }
    }

    //25. Extract the productId of all the products
    @Test
    public void test005() {
        List<Integer> productID = response.extract().path("data.id");
        System.out.println("Product ID for all products" + productID);
        for (Integer b : productID) {
            if (b.equals(51957)) {
                Assert.assertTrue(true);
            }

        }
    }

    //26. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> datasize = response.extract().path("data");
        int size = datasize.size();
        System.out.println("The size of data list " + size);
    }

    //27.Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
    //Pack)
    @Test
    public void test007() {

        List<HashMap<String, ?>> productName = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println(productName);
    }

    //28.Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
    //Pack)

    @Test
    public void test008(){
        List<HashMap<String,?>> modelP = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println(modelP);

    }

    //29. Get all the categories of 8th products
    @Test
    public void test009(){
        List<HashMap<String,?>> categories = response.extract().path("data[7].categories");
        System.out.println(categories);
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test010(){
        List<HashMap<?,?>> categoires = response.extract().path("data[3].categories");
        System.out.println(categoires);
    }

    //31. Get all the descriptions of all the products
    @Test
    public void test011(){
        List<HashMap<?,?>> description = response.extract().path("data.description");
        System.out.println(description);

    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test012(){
        List<HashMap<?,?>> idofall = response.extract().path("data.categories.id");
        System.out.println(idofall);

    }
    //33. Find the product names Where type = HardGood
    @Test
    public void test013(){
        List<String> product = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println(product);
    }
    //34.Find the Total number of categories for the product where product name = Duracell - AA
    //1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014(){
        List<Integer> categories = response.extract().path("data.findAll{it.product = 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'" +
                "}.categories");
        System.out.println(categories);

    }
    //35.Find the createdAt for all products whose price < 5.49
    @Test
    public void test015(){
        List<Integer> createdAt = response.extract().path("data.findAll{it.price <5.49}.createdAt");
        System.out.println(createdAt);
    }
    //36.Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
    //Pack)”
    @Test
    public void test016(){
        List<HashMap<String, ?>> allcategories = response.extract().path("data.findAll{it.product ='Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println(allcategories);

    }

    //37. Find the manufacturer of all the products
    @Test
    public void test017(){
        List<String> manufacture = response.extract().path("data.manufacturer");
        System.out.println(manufacture);

    }
    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test018(){
        List<String> imgage= response.extract().path("data.findAll{it.manufacturer = 'Energizer'}.image");
        System.out.println(imgage);

    }
    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019(){
        List<Integer> createdAt = response.extract().path("data.findAll{it.price >5.99}.createdAt");
        System.out.println(createdAt);
    }
    //40. Find the uri of all the products
    @Test
    public void test020(){
        List<String> url = response.extract().path("data.url");
        System.out.println(url);
    }






}
