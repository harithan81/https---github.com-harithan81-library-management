package com.lm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lm.domain.gen.Address;
import com.lm.repository.AddressRepository;
import com.mysema.query.types.Predicate;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address findOne(int addressId) {
        return addressRepository.findOne(addressId);
    }

    public Page<Address> findAll(Predicate predicate, Pageable pageable) {
        return addressRepository.findAll(predicate, pageable);
    }

}
