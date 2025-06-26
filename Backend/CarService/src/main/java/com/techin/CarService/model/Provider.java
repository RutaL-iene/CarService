package com.techin.CarService.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "providers")
public class Provider {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String address;
  private String manager;
  @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Employee> employees;

  public Provider(String title, String address, String manager, List<Employee> employees) {
    this.title = title;
    this.address = address;
    this.manager = manager;
    this.employees = employees;
  }

  public Provider() {
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }
}
