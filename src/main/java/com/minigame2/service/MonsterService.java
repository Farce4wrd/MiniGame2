package com.minigame2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minigame2.data.MonsterRepository;
import com.minigame2.model.Monster;

@Service
public class MonsterService {
	private MonsterRepository monsterRepository;
	
	@Autowired
	public MonsterService(MonsterRepository monsterRepository) {
		this.monsterRepository = monsterRepository;
	}
	
	public List<Monster> getAllMonsters(){
		List<Monster> monsters = new ArrayList<Monster>();
		for(Monster monster: this.monsterRepository.findAll()) {
			monsters.add(monster);
		}
		return monsters;
	}

}
