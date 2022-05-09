package com.minigame2.data;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minigame2.model.Character;
=======
import com.minigame2.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
>>>>>>> fd1ad9b28329724956f1045f124d33e2684b02de

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

	Optional<Character>findById(int id);
}
