package service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.ItemRepository;
import model.Item;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	private ArrayList<Item> items = new ArrayList<Item>();
	
	
	

}
