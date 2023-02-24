package com.bestbuy.crudestpackages;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;


import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ProductsCRUDTest extends TestBase {

    int idNumber;

    @BeforeClass
    public static void InIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath ="/products";
    }


    @Test
    public void test001() {
       given()
               .when()
               .log().all()
               .get()
               .then().log().all().statusCode(200);


    }


    @Test()
    public void addnewproduct() {

        ProductPojo pojo = new ProductPojo();


        pojo.setName("Durcaell AA self charge (4-Pack)");
        pojo.setType("GoodLifeBattery");
        pojo.setPrice(66);
        pojo.setShipping(45);
        pojo.setUpc("1466854555");
        pojo.setDescription("Compatible with all type of devices");
        pojo.setManufacturer("Ovarallcel");
        pojo.setModel("OV27100OAf");
        pojo.setUrl("www.webuybattery,com");
        pojo.setImage("img/kkpp");

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(pojo)
                .post("/products");
        response.then().statusCode(201);
        int idNumber =response.then().extract().path("id");
        System.out.println(idNumber);


    }


    //update ID
    @Test
    public void update() {
        ProductPojo pojo = new ProductPojo();
      pojo.setName("calculatorcel AA self double (4-Pack)");
      pojo.setShipping(25);
      pojo.setPrice(33);

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", "9999680")
                .when()
                .body(pojo)
                .patch("/{id}");
      response.then().statusCode(200);


    }
    //delete
    @Test
    public void delete(){
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","9999680")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }
    //retrive
    @Test
    public void retriveId(){
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","9999680")
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }


}
