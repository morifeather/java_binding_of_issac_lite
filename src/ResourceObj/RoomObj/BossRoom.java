package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.*;
import ResourceObj.Enemies.Boss.Pride;
import ResourceObj.Enemies.Boss.SubPride1;
import ResourceObj.Enemies.Boss.SubPride2;
import ResourceObj.GoldenCup;
import ResourceObj.Issac;
import ResourceObj.obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BossRoom extends Room {
    Random randit = new Random();
    
    /**
     * 重写父类方法，添加反向门到doors列表中
     * @param doors 存储门对象的列表
     */
    @Override
    public void InsertDoorInfo(ArrayList<Door> doors) {
        doors.add(new Door(false, MinusDoorToward));
    }

    /**
     * 构造函数，用于初始化Boss房间
     * @param doors 存储门对象的列表
     * @param obstacles 存储障碍物对象的列表
     * @param enemies 存储敌人类对象的列表
     * @param PlusDoorToward 房间正向门的方向
     * @param MinusDoorToward 房间负向门的方向
     */
    public BossRoom(ArrayList<Door> doors, ArrayList<Obstacle> obstacles, ArrayList<Enemy> enemies, int PlusDoorToward, int MinusDoorToward) {
        super();
        this.PlusDoorToward = PlusDoorToward;
        
        // 根据正向门方向设置反向门方向
        if (PlusDoorToward == 1) {
            this.MinusDoorToward = 2;
        } else if (PlusDoorToward == 2) {
            this.MinusDoorToward = 1;
        } else if (PlusDoorToward == 3) {
            this.MinusDoorToward = 4;
        } else if (PlusDoorToward == 4) {
            this.MinusDoorToward = 3;
        }
        
        InsertDoorInfo(doors);      // 插入门信息
        InsertEnemyInfo(enemies);   // 插入敌人信息
        InsertObstacleInfo(obstacles); // 插入障碍物信息
    }

    /**
     * 插入敌人信息到enemies列表中
     * @param enemies 存储敌人类对象的列表
     */
    @Override
    public void InsertEnemyInfo(ArrayList<Enemy> enemies) {
        // 添加一个初始状态的 Pride Boss
        enemies.add(new Pride(50 + 23 * 8, 50 + 23 * 4, 0, 1));
        
        // 随机生成不同类型的敌人组合
        int i = randit.nextInt(4);
        
        if (i == 0) {
            // 添加两只 RedFly
            enemies.add(new RedFly());
            enemies.get(1).Initialize(50 + 23 * 7, 50 + 23 * 4);
            enemies.add(new RedFly());
            enemies.get(2).Initialize(50 + 23 * 9, 50 + 23 * 4);
        } else if (i == 1) {
            // 添加两只 Turkey
            enemies.add(new Turkey());
            enemies.get(1).Initialize(50, 50 + 23 * 4);
            enemies.add(new Turkey());
            enemies.get(2).Initialize(50 + 23 * 15, 50 + 23 * 4);
        } else if (i == 2) {
            // 添加四只 Tentacle
            for (int a = 1; a <= 4; a++) enemies.add(new Tentacle());
            enemies.get(1).Initialize(50, 20);
            enemies.get(2).Initialize(50 + 23 * 15, 20);
            enemies.get(3).Initialize(50, 20 + 23 * 8);
            enemies.get(4).Initialize(50 + 23 * 15, 20 + 23 * 8);
        } else if (i == 3) {
            // 再次随机决定是否添加更多 Boss 敌人或普通 Fly
            int Luck = randit.nextInt(10);
            if (Luck == 0) {
                // 添加两个 Pride、一个 SubPride2 和一个 SubPride1
                enemies.add(new Pride(50, 50 + 4 * 23, 1, 1));
                enemies.add(new Pride(50 + 23 * 15, 50 + 4 * 23, 1, 2));
                enemies.add(new SubPride2(50, 50, 2, 1));
                enemies.add(new SubPride1(50 + 23 * 15, 50 + 23 * 8, 2, 2));
            } else {
                // 添加两只 Fly
                enemies.add(new Fly());
                enemies.add(new Fly());
                enemies.get(1).Initialize(50 + 23 * 7, 50 + 23 * 4);
                enemies.get(0).Initialize(50 + 23 * 9, 50 + 23 * 4);
            }
        }
    }

    /**
     * 判断当前房间状态，如果所有敌人都被消灭，则触发奖励逻辑
     * @param enemies 存储敌人类对象的列表
     */
    @Override
    public void StateJudge(ArrayList<Enemy> enemies){
        if (RoomState == 0) {
            int EnemyCount = 0;
            for (Enemy enemy : enemies) {
                if (enemy.getEnemyExist()) {
                    EnemyCount++;
                }
            }
            
            if (EnemyCount == 0) {
                // 所有敌人死亡，房间状态更新为已清理
                RoomState = 1;
                
                // Isaac 回复一点生命值并切换爱心显示状态
                Issac.getInstance().setHp(Issac.getInstance().getHp() + 1);
                Issac.getInstance().setHeartSwitch(true);
                
                // 加载 GoldenCup 图像资源
                try {
                    GoldenCup.getInstance().setImage(ImageIO.read(Issac.class.getClassLoader().getResource("images/GoldenCup.png")));
                    GoldenCup.getInstance().setLength(32);
                    GoldenCup.getInstance().setWidth(64);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (EnemyCount > 0) {
                // 房间中仍有敌人存在
                RoomState = 0;
            } else {
                // 异常情况：房间状态错误
                System.out.println("Room State Wrongly");
            }
        }
    }
}
