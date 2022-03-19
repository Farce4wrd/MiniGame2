package com.minigame2.model;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

@Component
public class Player {
	
	private ArrayList<Item> backpack;
	private ReentrantLock lock;
	
	public Player() {
		backpack = new ArrayList<Item>();
		lock = new ReentrantLock();
	}
	//Returns the inventory list in backpack
	public ArrayList<Item> getBackpack(){
		lock.lock();
		try {
			return this.backpack;
		}finally {
			lock.unlock();
		}
		
	}
	//add items to the backpack
	public void addItemToBackpack(Item item) {
		lock.lock();
		try {
			this.backpack.add(item);
			System.out.println(item.getName()+" has been added");
		}finally {
			lock.unlock();
		}
		
	}
	
	//Removes item from backpack
	public void removeFromBackpack(Item item) {
		lock.lock();
		try	{
			this.backpack.remove(item);
		}finally {
			lock.unlock();
		}
		
	}

}
