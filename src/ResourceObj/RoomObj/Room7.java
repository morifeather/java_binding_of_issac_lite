package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.Enemy;
import ResourceObj.obstacle.Obstacle;
import ResourceObj.obstacle.Poop;
import ResourceObj.obstacle.Stone;

import java.util.ArrayList;

public class Room7 extends Room{
    public Room7(ArrayList<Door> doors, ArrayList<Obstacle> obstacles, ArrayList<Enemy> enemies, int PlusDoorToward, int MinusDoorToward) {
        super();
        this.PlusDoorToward = PlusDoorToward;
        this.MinusDoorToward = MinusDoorToward;
        InsertDoorInfo(doors);
        InsertEnemyInfo(enemies);
        InsertObstacleInfo(obstacles);
    }
    @Override
    public void InsertObstacleInfo(ArrayList<Obstacle> obstacles) {
        int Num=0;
        for(int i=2;i<=6;i++){
            for(int j=2;j<=13;j++){
                obstacles.add(new Poop());
                if(obstacles.get(Num) instanceof Poop)((Poop)obstacles.get(Num)).Initialize(j,i,1);
                Num++;
            }
        }
    }
}
