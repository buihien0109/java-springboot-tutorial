package com.example.relationship.repository;

import com.example.relationship.model.one_to_one.unidirection.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByCity(String city);
    Address findByStreet(String street);
}