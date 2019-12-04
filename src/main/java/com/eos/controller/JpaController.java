package com.eos.controller;

import com.eos.dto.CustomerDto;
import com.eos.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jpa")
public class JpaController {

  @Autowired
  private CustomerService customerService;


  @GetMapping("/test")
  public ResponseEntity<String> test() {
    return new ResponseEntity<String>("OKK", HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity addCustomer(@RequestBody CustomerDto customerDto) {
    customerService.addCustomer(customerDto);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity updateCustomer(@RequestBody CustomerDto customerDto) {
    customerService.updateCustomer(customerDto);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
    return new ResponseEntity<CustomerDto>(customerService.getCustomer(id), HttpStatus.OK);
  }

}
