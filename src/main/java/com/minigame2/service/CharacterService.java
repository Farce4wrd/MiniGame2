package com.minigame2.service;

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
