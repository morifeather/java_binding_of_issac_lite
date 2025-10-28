package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.Enemy;
import ResourceObj.Enemies.Tentacle;
import ResourceObj.Enemies.Turkey;
import ResourceObj.obstacle.Obstacle;
import ResourceObj.obstacle.ShootStone;
import ResourceObj.obstacle.Stone;

import java.util.ArrayList;

public class Room6 extends Room{
    public Room6(ArrayList<Door> doors, ArrayList<Obstacle> obstacles, ArrayList<Enemy> enemies, int PlusDoorToward, int MinusDoorToward) {
        super();
        this.PlusDoorToward = PlusDoorToward;
        this.MinusDoorToward = MinusDoorToward;
        InsertDoorInfo(doors);
        InsertEnemyInfo(enemies);
        InsertObstacleInfo(obstacles);
    }
    @Override
    public void InsertObstacleInfo(ArrayList<Obstacle> obstacles) {
        for(int i=1;i<=8;i++){
            obstacles.add(new ShootStone());
        }
        if(obstacles.get(0) instanceof ShootStone)((ShootStone)obstacles.get(0)).Initialize(0,2,2);
        if(obstacles.get(1) instanceof ShootStone)((ShootStone)obstacles.get(1)).Initialize(0,6,2);
        if(obstacles.get(2) instanceof ShootStone)((ShootStone)obstacles.get(2)).Initialize(3,0,0);
        if(obstacles.get(3) instanceof ShootStone)((ShootStone)obstacles.get(3)).Initialize(12,0,0);
        if(obstacles.get(4) instanceof ShootStone)((ShootStone)obstacles.get(4)).Initialize(15,2,3);
        if(obstacles.get(5) instanceof ShootStone)((ShootStone)obstacles.get(5)).Initialize(15,6,3);
        if(obstacles.get(6) instanceof ShootStone)((ShootStone)obstacles.get(6)).Initialize(3,8,1);
        if(obstacles.get(7) instanceof ShootStone)((ShootStone)obstacles.get(7)).Initialize(12,8,1);
    }
    @Override
    public void InsertEnemyInfo(ArrayList<Enemy> enemies) {
        for(int i=1;i<=16;i++){
            enemies.add(new Tentacle());
        }
        enemies.get(0).Initialize(50,20);
        enemies.get(1).Initialize(50+23,20);
        enemies.get(2).Initialize(50+23*2,20);
        enemies.get(3).Initialize(50+23*13,20);
        enemies.get(4).Initialize(50+23*14,20);
        enemies.get(5).Initialize(50+23*15,20);
        enemies.get(6).Initialize(50,20+23);
        enemies.get(7).Initialize(50+15*23,20+23);
        enemies.get(8).Initialize(50,20+23*7);
        enemies.get(9).Initialize(50+23*15,20+23*7);
        enemies.get(10).Initialize(50,20+23*8);
        enemies.get(11).Initialize(50+23*2,20+23*8);
        enemies.get(12).Initialize(50+23*13,20+23*8);
        enemies.get(13).Initialize(50+23*14,20+23*8);
        enemies.get(14).Initialize(50+23*15,20+23*8);
        enemies.get(15).Initialize(50+23*1,20+23*8);
    }
}
