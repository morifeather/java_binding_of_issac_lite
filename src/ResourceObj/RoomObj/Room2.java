package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.Boss.SubPride1;
import ResourceObj.Enemies.Enemy;
import ResourceObj.Enemies.RedFly;
import ResourceObj.obstacle.Obstacle;
import ResourceObj.obstacle.Stone;

import java.util.ArrayList;

public class Room2 extends Room{
    public Room2(ArrayList<Door> doors, ArrayList<Obstacle> obstacles, ArrayList<Enemy> enemies, int PlusDoorToward, int MinusDoorToward) {
        super();
        this.PlusDoorToward=PlusDoorToward;
        this.MinusDoorToward=MinusDoorToward;
        InsertDoorInfo(doors);
        InsertEnemyInfo(enemies);
        InsertObstacleInfo(obstacles);
    }
    @Override
    public void InsertObstacleInfo(ArrayList<Obstacle> obstacles) {
        for(int i=1;i<=28;i++){
            obstacles.add(new Stone());
        }
        obstacles.get(0).Initialize(1,1,1);
        obstacles.get(1).Initialize(2,1,1);
        obstacles.get(2).Initialize(3,1,1);
        obstacles.get(3).Initialize(4,1,1);
        obstacles.get(4).Initialize(5,1,1);
        obstacles.get(5).Initialize(6,1,1);
        obstacles.get(6).Initialize(14,1,1);
        obstacles.get(7).Initialize(9,1,1);
        obstacles.get(8).Initialize(10,1,1);
        obstacles.get(9).Initialize(11,1,1);
        obstacles.get(10).Initialize(12,1,1);
        obstacles.get(11).Initialize(13,1,1);
        obstacles.get(12).Initialize(1,2,1);
        obstacles.get(13).Initialize(14,2,1);
        obstacles.get(14).Initialize(1,6,1);
        obstacles.get(15).Initialize(14,6,1);
        obstacles.get(16).Initialize(1,7,1);
        obstacles.get(17).Initialize(2,7,1);
        obstacles.get(18).Initialize(3,7,1);
        obstacles.get(19).Initialize(4,7,1);
        obstacles.get(20).Initialize(5,7,1);
        obstacles.get(21).Initialize(6,7,1);
        obstacles.get(22).Initialize(14,7,1);
        obstacles.get(23).Initialize(9,7,1);
        obstacles.get(24).Initialize(10,7,1);
        obstacles.get(25).Initialize(11,7,1);
        obstacles.get(26).Initialize(12,7,1);
        obstacles.get(27).Initialize(13,7,1);
    }
    @Override
    public void InsertEnemyInfo(ArrayList<Enemy> enemies) {
        for(int i=1;i<=4;i++){
            enemies.add(new RedFly());
        };
        enemies.get(0).Initialize(50,50);
        enemies.get(1).Initialize(50,50+23*7);
        enemies.get(2).Initialize(50+23*14,50);
        enemies.get(3).Initialize(50+23*14,50+23*7);
    }

}
