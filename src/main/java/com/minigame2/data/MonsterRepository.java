package com.minigame2.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minigame2.model.Monster;
@Repository
public interface MonsterRepository extends JpaRepository<Monster, Integer>{

}
