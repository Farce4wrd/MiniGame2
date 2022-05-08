package com.minigame2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minigame2.data.CharacterRepository;
import com.minigame2.model.Character;

@Service

public class CharacterService {
	
	private CharacterRepository characterRepository;
	
	@Autowired
	public CharacterService(CharacterRepository characterRepository) {
		this.characterRepository = characterRepository;
	}
	public void characterSave(Character character)
	 {
		 this.characterRepository.save(character);
	 }
}
