package com.minigame2;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class CreateMonster {
	
	public void createTxt() {
		
		try {
			FileReader fr = new FileReader("monster.txt");
			PrintWriter output = new PrintWriter("MONSTERINSERT.txt");
			Scanner sc = new Scanner(fr);
			
			while(sc.hasNextLine()){
				String isBoss = sc.nextLine();
				String name = sc.nextLine();
				String variety = sc.nextLine();
				String psuedohp = sc.nextLine();
				int hp = Integer.parseInt(psuedohp);
				String psuedoDmg = sc.nextLine();
				int dmg = Integer.parseInt(psuedoDmg);
				String psuedoRoomId = sc.nextLine();
				int roomId = Integer.parseInt(psuedoRoomId);
				output.printf("INSERT INTO MONSTER(DAMAGE,HP,IS_BOSS,NAME,VARIETY,MONSTERS_ROOMID) VALUES(%d,%d,'%s','%s','%s',%d');\n",dmg,hp,isBoss,name,variety,roomId );
			}
			output.close();
			sc.close();
			fr.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
