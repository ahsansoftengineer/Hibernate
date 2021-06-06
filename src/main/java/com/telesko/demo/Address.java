package com.telesko.demo;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
  private String street;
  private String city;
  private int postalCode;
  Address(){}
  Address(String street, String city, int postalCode){
    this.street = street;
    this.city = city;
    this.postalCode = postalCode;
  }
  public String toString(){
    return street + " : " + city + " : " + postalCode;
  }
}
