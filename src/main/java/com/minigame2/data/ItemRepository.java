package com.minigame2.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minigame2.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByVariety(String Weapon);

}
