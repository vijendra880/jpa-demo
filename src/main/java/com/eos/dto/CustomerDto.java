package com.eos.dto;

import java.util.List;

public class CustomerDto {
  private Long id;
  private String firstName;
  private String lastName;
  private List<CustomerAddressDto> customerAddresses;
  private Integer brandId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<CustomerAddressDto> getCustomerAddresses() {
    return customerAddresses;
  }

  public void setCustomerAddresses(List<CustomerAddressDto> customerAddresses) {
    this.customerAddresses = customerAddresses;
  }

  public Integer getBrandId() {
    return brandId;
  }

  public void setBrandId(Integer brandId) {
    this.brandId = brandId;
  }

}
