package com.minigame2.data;

import com.minigame2.model.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoom, Integer>{
	public List<GameRoom> findByid(Integer id);
	GameRoom findByitems(Integer item);
	
	@Query("SELECT DISTINCT roo FROM GameRoom roo JOIN FETCH roo.items items")
	List<GameRoom> retrieveAll();
	GameRoom findGameRoomById(int id);
	
	
	GameRoom findById(int id);
	
	GameRoom findByItems(int id);
	
}
