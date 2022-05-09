package com.minigame2.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.minigame2.model.GameRoom;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoom, Integer>{
	public List<GameRoom> findByid(Integer id);
	GameRoom findByitems(Integer item);
	
	@Query("SELECT DISTINCT roo FROM GameRoom roo JOIN FETCH roo.items items")
	List<GameRoom> retrieveAll();
	@Query("SELECT DISTINCT roo FROM GameRoom roo JOIN FETCH roo.items JOIN FETCH roo.monsters JOIN FETCH roo.exits JOIN FETCH roo.character WHERE roo.id=?1")
	GameRoom findGameRoomById(int id);
	GameRoom findById(int id);
	
	
}
