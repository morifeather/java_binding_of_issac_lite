package ResourceObj.RoomObj;

import ResourceObj.BackGround;
import ResourceObj.Door;
import ResourceObj.Enemies.Enemy;
import ResourceObj.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.Random;

public class Room0 extends Room{
    Random randit=new Random();
    int PlusDoorTowardNum=randit.nextInt(4)+1;
    public Room0(ArrayList<Door> doors, ArrayList<Obstacle> obstacles, ArrayList<Enemy> enemies,  int PlusDoorToward,int MinusDoorToward){
        super();
        this.PlusDoorToward=PlusDoorTowardNum;
        this.MinusDoorToward=0;
        InsertDoorInfo(doors);

    }
    @Override
    public void InsertDoorInfo(ArrayList<Door> doors) {
        doors.add(new Door(true,PlusDoorToward));
    }

}
