package com.minigame2.data;

import com.minigame2.model.Exit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExitRepository extends JpaRepository<Exit, Integer>{

}
