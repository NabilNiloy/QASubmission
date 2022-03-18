package com.qa.cats.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.cats.entity.Cat;


@Repository
public interface CatRepo extends JpaRepository<Cat, Long>{

}