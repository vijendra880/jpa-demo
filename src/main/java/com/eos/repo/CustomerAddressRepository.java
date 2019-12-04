package com.eos.repo;

import com.eos.entities.CustomerAddress;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Long> {

  List<CustomerAddress> findByCustomerId(Long id);
}
