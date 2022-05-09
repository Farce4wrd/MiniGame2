package com.minigame2;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@Component
public class CreateInsert {

	public void createQuery() throws IOException {
		try {
			FileReader fr = null;
			PrintWriter output = new PrintWriter("data.sql");
			Scanner sc = null;

			try {
				fr = new FileReader("Rooms.txt");
				sc = new Scanner(fr);
			} catch (IOException e) {
				System.out.println("File not found, exiting.");
				System.exit(2);

			}
			while (sc.hasNextLine()) {
				String roomName = sc.nextLine();
				String description = sc.nextLine();
		
				output.printf("INSERT INTO ROOM(NAME, DESCRIPTION, HASVISITED) VALUES('%s','%s',%b)\n", roomName, description,
						false);

			}
			// print sql queries out of Items.txt
			try {
				fr = new FileReader("Items.txt");
				sc = new Scanner(fr);
			} catch (IOException e) {
				System.out.println("File not found, exiting.");
				System.exit(2);

			}
			while (sc.hasNextLine()) {
				String itemName = sc.nextLine();
				String description = sc.nextLine();
				String type = sc.nextLine();

				if (type.equalsIgnoreCase("Consumable")) {
					String up = sc.nextLine();
					int upgrade = Integer.parseInt(up);
					String room = sc.nextLine();
					int roomId = Integer.parseInt(room);
					
					output.printf("INSERT INTO ITEM(NAME, DESCRIPTION, VARIETY, UPGRADE, ROOM_ID) VALUES('%s','%s','%s',%d,%d)\n", itemName,description,type,upgrade,roomId);
					
				} else if (type.equalsIgnoreCase("Weapon")) {
					String monsterType = sc.nextLine();
					String lev = sc.nextLine();
					int level = Integer.parseInt(lev);
					String dam = sc.nextLine();
					int damage = Integer.parseInt(dam);
					String room = sc.nextLine();
					int roomId = Integer.parseInt(room);
					System.out.println(level);
					System.out.println(damage);
					System.out.println(roomId);
					output.printf("INSERT INTO ITEM(NAME, DESCRIPTION, VARIETY, MONSTERTYPE, LEVEL, DAMAGE, ROOM_ID) VALUES('%s','%s','%s','%s',%d ,%d ,%d)\n",itemName,description,type,monsterType,level,damage,roomId );

				} else if (type.equalsIgnoreCase("Puzzle")) {
					String room = sc.nextLine();
					int roomId = Integer.parseInt(room);
					output.printf("INSERT INTO ITEM(NAME, DESCRIPTION, VARIETY, ROOM_ID) VALUES('$s','%s','$s',$d) \n",itemName, description,type,roomId );

				}
			}
			output.close();
			sc.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
