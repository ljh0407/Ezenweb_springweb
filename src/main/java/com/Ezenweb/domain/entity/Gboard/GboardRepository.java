package com.Ezenweb.domain.entity.Gboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GboardRepository extends JpaRepository < GboardEntity , Integer >{

}
