package com.Ezenweb.domain.entity.Gbcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GbcategoryRepository extends JpaRepository< GbcategoryEntity , Integer> {

}
