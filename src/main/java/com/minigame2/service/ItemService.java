package com.minigame2.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minigame2.data.ItemRepository;
import com.minigame2.model.Item;

@Service
public class ItemService {
	
	
	private ItemRepository itemRepository;
	
	private ArrayList<Item> items = new ArrayList<Item>();
	
	
	

}
