package com.eos.service;

import com.eos.dto.CustomerAddressDto;
import com.eos.dto.CustomerDto;
import com.eos.entities.Customer;
import com.eos.entities.CustomerAddress;
import com.eos.repo.CustomerAddressRepository;
import com.eos.repo.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private CustomerAddressRepository customerAddressRepository;

  @Transactional
  public void addCustomer(CustomerDto customerDto) {
    Customer customer = getCustomerEntityFromDto(customerDto);
    customer = customerRepository.save(customer);
    if (!CollectionUtils.isEmpty(customerDto.getCustomerAddresses())) {
      List<CustomerAddress> customerAddresses = getCustomerAddresses(
          customerDto.getCustomerAddresses(), customer.getId());
      customerAddressRepository.saveAll(customerAddresses);
    }

  }

  private List<CustomerAddress> getCustomerAddresses(List<CustomerAddressDto> customerAddressDtos,
      Long id) {
    List<CustomerAddress> customerAddresses = new ArrayList();
    customerAddressDtos.forEach(
        customerAddressDto -> customerAddresses.add(getCustomerAddress(customerAddressDto, id)));
    return customerAddresses;
  }

  private CustomerAddress getCustomerAddress(CustomerAddressDto customerAddressDto,
      Long id) {
    CustomerAddress customerAddress = new CustomerAddress();
    customerAddress.setCustomerId(id);
    customerAddress.setAddress(customerAddressDto.getAddress());
    return customerAddress;
  }

  private Customer getCustomerEntityFromDto(CustomerDto customerDto) {
    Customer customer = new Customer();
    customer.setFirstName(customerDto.getFirstName());
    customer.setLastName(customerDto.getLastName());
    customer.setBrandId(customerDto.getBrandId());
    return customer;
  }

  public void updateCustomer(CustomerDto customerDto) {
  }

  public CustomerDto getCustomer(Long id) {
    Customer customer = customerRepository.getOne(id);
    List<CustomerAddress> customerAddresses = customerAddressRepository
        .findByCustomerId(customer.getId());
    return getCustomerDtoFromEntity(customer, customerAddresses);
  }

  private CustomerDto getCustomerDtoFromEntity(Customer customer,
      List<CustomerAddress> customerAddresses) {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setBrandId(customer.getBrandId());
    customerDto.setFirstName(customer.getFirstName());
    customerDto.setLastName(customer.getLastName());
    customerDto.setId(customer.getId());
    customerDto.setCustomerAddresses(getCustomerAddressDtoFromEntity(customerAddresses));
    return customerDto;
  }

  private List<CustomerAddressDto> getCustomerAddressDtoFromEntity(
      List<CustomerAddress> customerAddresses) {
    List<CustomerAddressDto> customerAddressDtos = new ArrayList<>();
    customerAddresses.forEach(customerAddress -> {
      CustomerAddressDto customerAddressDto = new CustomerAddressDto();
      customerAddressDto.setAddress(customerAddress.getAddress());
      customerAddressDto.setId(customerAddress.getId());
      customerAddressDtos.add(customerAddressDto);
    });
    return customerAddressDtos;
  }
}
