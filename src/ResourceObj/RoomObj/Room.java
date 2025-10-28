package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.Enemy;
import ResourceObj.Issac;
import ResourceObj.SoundEffect;
import ResourceObj.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.Random;

public class Room {
    Random randit = new Random();
    protected int RoomState=0;
    protected int PlusDoorToward;
    protected int MinusDoorToward;

    /**
     * 获取当前房间的正向门方向
     * @return 正向门的方向值
     */
    public int getPlusDoorToward(){
        return PlusDoorToward;
    }

    /**
     * 获取当前房间的反向门方向
     * @return 反向门的方向值
     */
    public int getMinusDoorToward(){
        return MinusDoorToward;
    }

    /**
     * 判断房间状态并更新
     * 当所有敌人都被消灭后，房间状态变为1，并可能掉落奖励
     * @param enemies 敌人列表
     */
    public void StateJudge(ArrayList<Enemy> enemies){
        if(RoomState == 0) {
            int EnemyCount = 0;
            // 计算当前存在的敌人数量
            for (Enemy enemy : enemies) {
                if (enemy.getEnemyExist()) {
                    EnemyCount++;
                }
            }

            // 如果敌人全部消灭，触发奖励机制
            if(EnemyCount == 0) {
                RoomState = 1;
                int i = randit.nextInt(9); // 随机生成0-8之间的整数

                // 根据随机数给予不同奖励
                if(i == 8){
                    // 减少头部动画播放速度（加快移动动画）
                    Issac.getInstance().setHeadTickSpeed(Issac.getInstance().getHeadTickSpeed() - 1);
                    SoundEffect.playSound("Up", "sounds/Up.mp3"); // 播放升级音效
                } else if(i == 7){
                    // 增加伤害
                    Issac.getInstance().setDamage(Issac.getInstance().getDamage() + 3);
                    SoundEffect.playSound("Up", "sounds/Up.mp3"); // 播放升级音效
                } else {
                    // 默认恢复生命
                    Issac.getInstance().setHp(Issac.getInstance().getHp() + 2);
                    SoundEffect.playSound("Heart", "sounds/EatHeart.mp3"); // 播放心形拾取音效
                    Issac.getInstance().setHeartSwitch(true); // 开启心跳特效
                }
            } else if (EnemyCount > 0) {
                // 还有敌人存在，保持房间状态为0
                RoomState = 0;
            } else {
                // 异常情况处理
                System.out.println("Room State Wrongly");
            }
        }
    }

    /**
     * 插入障碍物信息（子类可重写）
     * @param obstacles 障碍物列表
     */
    public void InsertObstacleInfo(ArrayList<Obstacle> obstacles){
    }

    /**
     * 插入敌人信息（子类可重写）
     * @param enemies 敌人列表
     */
    public void InsertEnemyInfo(ArrayList<Enemy> enemies){
    }

    /**
     * 插入门对象到门列表中
     * @param doors 门列表
     */
    public void InsertDoorInfo(ArrayList<Door> doors){
        doors.add(new Door(true, PlusDoorToward));  // 添加正向门
        doors.add(new Door(false, MinusDoorToward)); // 添加反向门
    }

    /**
     * 统一插入方法：根据房间状态决定插入哪些内容
     * @param enemies   敌人列表
     * @param obstacles 障碍物列表
     * @param doors     门列表
     */
    public void Insertion(ArrayList<Enemy> enemies, ArrayList<Obstacle> obstacles, ArrayList<Door> doors){
        if(RoomState == 0){
            InsertEnemyInfo(enemies); // 房间未清空时插入敌人
        }
        InsertObstacleInfo(obstacles); // 总是插入障碍物
        InsertDoorInfo(doors); // 总是插入门
    }

    /**
     * 获取当前房间状态
     * @return 房间状态值
     */
    public int getRoomState(){
        return RoomState;
    }
}