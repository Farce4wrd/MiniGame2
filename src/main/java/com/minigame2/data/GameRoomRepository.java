package com.minigame2.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minigame2.model.GameRoom;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoom, Integer>{
	public List<GameRoom> findByid(Integer id);
	

	
}
