package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.Enemy;
import ResourceObj.Enemies.Fly;
import ResourceObj.Enemies.RedFly;
import ResourceObj.Enemies.Turkey;
import ResourceObj.obstacle.Obstacle;

import java.util.ArrayList;

public class Room3 extends Room{
    public Room3(ArrayList<Door> doors, ArrayList<Obstacle> obstacles, ArrayList<Enemy> enemies, int PlusDoorToward, int MinusDoorToward) {
        super();
        this.PlusDoorToward=PlusDoorToward;
        this.MinusDoorToward=MinusDoorToward;
        InsertDoorInfo(doors);
        InsertEnemyInfo(enemies);
        InsertObstacleInfo(obstacles);
    }
    @Override
    public void InsertEnemyInfo(ArrayList<Enemy> enemies) {
        for(int i=1;i<=4;i++){
            enemies.add(new Turkey());
        }
        for(int i=1;i<=2;i++){
            enemies.add(new Fly());
        }
        enemies.get(0).Initialize(50,50);
        enemies.get(1).Initialize(50,50+23*8);
        enemies.get(2).Initialize(50+23*15,50);
        enemies.get(3).Initialize(50+23*15,50+23*8);
        enemies.get(4).Initialize(50+23*7,50+23*4);
        enemies.get(5).Initialize(50+23*9,50+23*4);
    }

}
