package com.minigame2.data;

import com.minigame2.model.GameRoom;
import com.minigame2.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByVariety(String Weapon);
	List<Item> findByroom(GameRoom room);
	
	void removeByroom(GameRoom room);

}
