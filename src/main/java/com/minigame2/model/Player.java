package com.minigame2.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class Player {
	private String name;
	private int hp;
	private int life;
	private int points;
	private int level;
	
	private GameRoom room;
	
	private ArrayList<Item> backpack;
	private ReentrantLock lock;
	
	public Player() {
		backpack = new ArrayList<Item>();
		lock = new ReentrantLock();
	}
	/**Returns the inventory list in backpack
	 * 
	 * Method: @return
	 *
	 * ArrayList<Item>
	 */
	public ArrayList<Item> getBackpack(){
		lock.lock();
		try {
			return this.backpack;
		}finally {
			lock.unlock();
		}
		
	}
	/**add items to the backpack
	 * 
	 * Method: @param item
	 *
	 * void
	 */
	public void addItemToBackpack(Item item) {
		lock.lock();
		try {
			this.backpack.add(item);
			System.out.println(item.getName()+" has been added");
		}finally {
			lock.unlock();
		}
		
	}
	
	/**Removes item from backpack
	 * 
	 * Method: @param item
	 *
	 * void
	 */
	public void removeFromBackpack(Item item) {
		lock.lock();
		try	{
			this.backpack.remove(item);
		}finally {
			lock.unlock();
		}
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public GameRoom getRoom() {
		return room;
	}
	public void setRoom(GameRoom room) {
		this.room = room;
	}

}
