package com.minigame2.service;

import org.springframework.stereotype.Service;

import com.minigame2.data.CharacterRepository;
import com.minigame2.model.Character;

@Service
public class CharacterService {
	private CharacterRepository characterRepository;
	 public void characterSave(Character character)
	 {
		 characterRepository.save(character);
	 }
}
