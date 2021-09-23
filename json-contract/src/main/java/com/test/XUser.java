package com.test;


import java.util.*;



/**user model*/
public class XUser {
    private List<XCar> carsHolder = new ArrayList<>();
    private Map<String,XCar> taggedCarsHolder = new HashMap<>();
    private String name;




    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCars(List<XCar> cars) {
        this.carsHolder = cars;
    }

    public List<XCar> getCars() {
        return this.carsHolder;
    }

    public void setTaggedCars(Map<String,XCar> taggedCars) {
        this.taggedCarsHolder = taggedCars;
    }

    public Map<String,XCar> getTaggedCars() {
        return this.taggedCarsHolder;
    }

}