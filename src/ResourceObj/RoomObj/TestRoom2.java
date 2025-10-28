package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.Boss.Pride;
import ResourceObj.Enemies.Boss.SubPride1;
import ResourceObj.Enemies.Boss.SubPride2;
import ResourceObj.Enemies.Enemy;
import ResourceObj.Enemies.Turkey;
import ResourceObj.obstacle.Obstacle;
import ResourceObj.obstacle.Stone;

import java.util.ArrayList;

public class TestRoom2 extends Room{
    @Override
    public void InsertDoorInfo(ArrayList<Door> doors) {
        doors.add(new Door(false,3));
        doors.add(new Door(false,4));
    }
    @Override
    public void InsertEnemyInfo(ArrayList<Enemy> enemies) {
        enemies.add(new Turkey());
        enemies.add(new Turkey());
        enemies.add(new Turkey());
        enemies.add(new Turkey());
        enemies.get(0).Initialize(50+23,50+23);
        enemies.get(1).Initialize(50+23,50+23*7);
        enemies.get(2).Initialize(50+23*14,50+23);
        enemies.get(3).Initialize(50+23*14,50+23*7);
    }
    @Override
    public void InsertObstacleInfo(ArrayList<Obstacle> obstacles) {
        for(int i=1;i<=12;i++){
            obstacles.add(new Stone());
        }
        obstacles.get(0).Initialize(0,0,1);
        obstacles.get(1).Initialize(1,0,1);
        obstacles.get(2).Initialize(0,1,1);
        obstacles.get(3).Initialize(0,7,1);
        obstacles.get(4).Initialize(0,8,1);
        obstacles.get(5).Initialize(1,8,1);
        obstacles.get(6).Initialize(14,0,1);
        obstacles.get(7).Initialize(15,0,1);
        obstacles.get(8).Initialize(15,1,1);
        obstacles.get(9).Initialize(15,7,1);
        obstacles.get(10).Initialize(15,8,1);
        obstacles.get(11).Initialize(14,8,1);
    }
}
