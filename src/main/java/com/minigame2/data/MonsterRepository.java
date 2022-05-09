package com.minigame2.data;

import com.minigame2.model.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MonsterRepository extends JpaRepository<Monster, Integer>{

}
