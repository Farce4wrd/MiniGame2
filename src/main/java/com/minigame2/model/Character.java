package com.minigame2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Entity
@Table(name="CHARACTER")
public class Character implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String name;
	private int hp;
	private int life;
	private int points;
	private int level;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="character")
	private GameRoom location;
	@Transient
	private List<Item> inventory;
	@Transient
	private ReentrantLock lock;

	public Character() {
	}

	public Character(String name, GameRoom location, int hp, int life, int points, int level) {
		lock= new ReentrantLock();
		this.name = name;
		this.location = location;
		this.inventory = new ArrayList<Item>();
		this.hp = 50; //for now...
		this.points = points;
		this.level = 0;
	}

	public List<Item> getInventory()	{
		lock.lock();
		try {
			return this.inventory;
		}finally {
			lock.unlock();
		}
	}
	
	public GameRoom getLocation() {
		return location;
	}
	
	public void setLocation(GameRoom location) {
		this.location = location;
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

	public void setInventory(List<Item> characterInventory) {
		this.inventory = characterInventory;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", hp=" + hp + ", life=" + life + ", points=" + points
				+ ", level=" + level + ", location=" + location + ", inventory=" + inventory + "]";
	}
	
	
}
