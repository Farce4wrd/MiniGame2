package com.minigame2.service;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
>>>>>>> fd1ad9b28329724956f1045f124d33e2684b02de
import com.minigame2.data.CharacterRepository;
import com.minigame2.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
	
	private final CharacterRepository characterRepository;
	
	@Autowired
	public CharacterService(CharacterRepository characterRepository) {
		this.characterRepository = characterRepository;
	}
	public void characterSave(Character character)
	 {
		 this.characterRepository.save(character);
	 }
	
}
