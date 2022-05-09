package com.minigame2.model;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 */

/**
 * @author Peter
 * @version 1.0
 * Course: ITEC 3860 Spring 2022
 * Written: February 8, 2022 12:50am.
 * 
 * This class is responsible for encapsulating the room id of other room objects and directions which would be used for user options.
 */
@Entity
@Table(name="EXIT", schema="EXIT")
public class Exit implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private int roomid;
	private String direction;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="room_id")
	private GameRoom room;
	
	public Exit(String direction, int roomId) {
		this.direction = direction;
		this.roomid = roomId;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getRoomId() {
		return roomid;
	}

	public void setRoomId(int roomId) {
		this.roomid = roomId;
	}
	
	public Exit() {
		
	}

	@Override
	public String toString() {
		return "Exit [id=" + id + ", roomid=" + roomid + ", direction=" + direction + ", room=" + room + "]";
	}

}
