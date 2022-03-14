package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	

}
