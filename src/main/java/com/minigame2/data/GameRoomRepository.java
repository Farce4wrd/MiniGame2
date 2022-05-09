package com.minigame2.data;

import com.minigame2.model.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoom, Integer>{
	public List<GameRoom> findByid(Integer id);
	GameRoom findByitems(Integer item);
	GameRoom findById(@Param("id") int id);
	
	
}
