package com.lm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.lm.domain.gen.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>,QueryDslPredicateExecutor<Address>{

}
