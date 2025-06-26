package com.techin.CarService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String surname;
  private String specialisation;
  private String city;
  private Double rating;

  @ManyToOne
  @JoinColumn(name = "provider_id")
  private Provider provider;


  public Employee(String name, String surname, String specialisation, String city, Double rating, Provider provider) {
    this.name = name;
    this.surname = surname;
    this.specialisation = specialisation;
    this.city = city;
    this.rating = rating;
    this.provider = provider;
  }

  public Employee() {
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getSpecialisation() {
    return specialisation;
  }

  public void setSpecialisation(String specialisation) {
    this.specialisation = specialisation;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }
}
