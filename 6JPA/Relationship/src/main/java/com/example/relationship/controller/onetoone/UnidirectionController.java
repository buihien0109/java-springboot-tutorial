package com.example.relationship.controller.onetoone;

import com.example.relationship.model.one_to_one.unidirection.Address;
import com.example.relationship.model.one_to_one.unidirection.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UnidirectionController {

//    @Autowired
//    private AddressRepository addressRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//    @GetMapping("/users/{id}")
//    public User getUserById(@PathVariable Integer id) {
//        return userRepository.getUserById(id);
//    }
//
//    @GetMapping("/address")
//    public List<Address> getAddresses() {
//        return addressRepository.findAll();
//    }
//
//    @GetMapping("/address/{id}")
//    public Address getAddressById(@PathVariable Integer id) {
//        return addressRepository.getAddressById(id);
//    }
}
