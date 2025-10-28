package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.*;
import ResourceObj.Enemies.Boss.Pride;
import ResourceObj.Enemies.Boss.SubPride1;
import ResourceObj.Enemies.Boss.SubPride2;
import ResourceObj.obstacle.Obstacle;
import ResourceObj.obstacle.Poop;
import ResourceObj.obstacle.ShootStone;
import ResourceObj.obstacle.Stone;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class TestRoom extends Room {
    Random randit = new Random();
    @Override
    public void InsertDoorInfo(ArrayList<Door> doors) {
        doors.add(new Door(true,3));
        doors.add(new Door(true,4));
    }

    @Override
    public void InsertEnemyInfo(ArrayList<Enemy> enemies) {
        enemies.add(new Pride(50+23*8,50+23*4,0,1));
        int i=randit.nextInt(4);
        if(i==0){
            enemies.add(new RedFly());
            enemies.get(1).Initialize(50+23*7,50+23*4);
            enemies.add(new RedFly());
            enemies.get(2).Initialize(50+23*9,50+23*4);
        }
        else if(i==1){
            enemies.add(new Turkey());
            enemies.get(1).Initialize(50,50+23*4);
            enemies.add(new Turkey());
            enemies.get(2).Initialize(50+23*15,50+23*4);
        }
        else if(i==2){
            for(int a=1;a<=4;a++) enemies.add(new Tentacle());
            enemies.get(1).Initialize(50,20);
            enemies.get(2).Initialize(50+23*15,20);
            enemies.get(3).Initialize(50,20+23*8);
            enemies.get(4).Initialize(50+23*15,20+23*8);
        }
        else if(i==3){
            int Luck=randit.nextInt(10);
            if(Luck==0){
                enemies.add(new Pride(50,50+4*23,1,1));
                enemies.add(new Pride( 50+23*15,50+4*23,1,2));
                enemies.add(new SubPride2(50,50,2,1));
                enemies.add(new SubPride1(50+23*15,50+23*8,2,2));
            }
            else{
                enemies.add(new Fly());
                enemies.add(new Fly());
                enemies.get(1).Initialize(50+23*7,50+23*4);
                enemies.get(0).Initialize(50+23*9,50+23*4);
            }
        }
    }
}
