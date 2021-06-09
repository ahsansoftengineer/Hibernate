package com.telesko.types;

import javax.persistence.Embeddable;
// This will save the record in Employee Table
@Embeddable
public class Address {
  private String street;
  private String city;
  private int postalCode;
  public Address(){}
  public Address(String street, String city, int postalCode){
    this.street = street;
    this.city = city;
    this.postalCode = postalCode;
  }
  @Override
  public String toString(){
    return street + " : " + city + " : " + postalCode;
  }
}
