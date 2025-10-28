package ResourceObj.Enemies;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.ArrayList;

import ResourceObj.obstacle.Obstacle;

public class Enemy {
    // 敌人基础属性
    protected int hp;                   // 生命值
    protected int x, y;                 // 实际坐标
    protected int Imagelength, Imagewidth; // 图像尺寸
    protected int Imagex, Imagey;       // 图像坐标（用于动画过渡）
    protected int length, width;        // 实体大小
    protected int Xspeed = 1, Yspeed = 1; // 移动速度
    protected int ImageTick;            // 动画计时器
    protected boolean flight;           // 是否飞行（无视障碍）
    protected boolean EnemyExist = true; // 是否存活
    protected boolean LeftMovable, RightMovable, UpMovable, DownMovable; // 各方向是否可移动
    protected BufferedImage nowImage;   // 当前图像
    protected int NowXSpeed, NowYSpeed; // 当前实际移动速度
    protected boolean Hurt=false;       // 是否受伤闪烁
    protected int HurtTick;             // 受伤闪烁计时

    /**
     * 受伤状态更新逻辑
     * 每帧调用，控制受伤闪烁效果
     */
    public void HurtJudgement(){
         if(Hurt){
             HurtTick++;
             if(HurtTick==1) {
                 HurtTick=0;
                 Hurt = false;
             }
         }
    }

    /**
     * 设置受伤状态
     */
    public void setHurt(){
         Hurt = true;
    }

    /**
     * 获取受伤状态
     * @return 是否正在受伤闪烁
     */
    public boolean getHurt() {
         return Hurt;
    }

    /**
     * 获取当前显示图像
     * @return 当前BufferedImage对象
     */
    public BufferedImage getImage() {
        return nowImage;
    }

    /**
     * 初始化敌人位置
     * @param x 初始x坐标
     * @param y 初始y坐标
     */
    public void Initialize(int x, int y) {
        this.x = x;
        this.y = y;
        this.Imagex = x;
        this.Imagey = y;
    }
    /*---------------------------------------------------------------------------------------------------*/

    /**
     * 设置生命值
     * @param Hp 新的生命值
     */
    public void setHp(int Hp) {
        this.hp = Hp;
    }

    /**
     * 设置图像x坐标
     * @param x 新的x坐标
     */
    public void setX(int x) {
        this.Imagex = x;
    }

    /**
     * 设置图像y坐标
     * @param y 新的y坐标
     */
    public void setY(int y) {
        this.Imagey = y;
    }

    /**
     * 设置敌人的存活状态
     * @param EnemyExist 是否存活
     */
    public void setEnemyExist(boolean EnemyExist) {
        this.EnemyExist = EnemyExist;
    }
    /*--------------------------------------------------------------------------------------------------*/

    // Getters and Setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getNowXSpeed() { return NowXSpeed; }
    public int getNowYSpeed() { return NowYSpeed; }
    public int getImagex() { return Imagex; }
    public int getImagey() { return Imagey; }
    public int getImagelength() { return Imagelength; }
    public int getLength() { return length; }
    public int getImagewidth() { return Imagewidth; }
    public int getWidth() { return width; }
    public int getHp() { return hp; }
    public boolean getEnemyExist() { return EnemyExist; }
    public boolean getFlight() { return flight; }

    /**
     * 更新图像（子类实现）
     * 子类应重写此方法以实现动画逻辑
     */
    public void UpdateImage() {
        // 默认为空实现
    }

    /**
     * 判断敌人的移动是否被阻挡
     * @param obstacles 障碍物列表
     */
    public void MovableJudgement(ArrayList<Obstacle> obstacles) {
        // 初始化为所有方向可移动
        LeftMovable = true;
        RightMovable = true;
        UpMovable = true;
        DownMovable = true;

        if (!flight) { // 如果不是飞行单位才检测障碍
            for (Obstacle obstacle : obstacles) {
                // 创建障碍物矩形区域
                Rectangle obstacleRect = new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA());

                // 检测左侧碰撞
                if (NowXSpeed < 0 && obstacleRect.intersects(x + NowXSpeed, y, width, length)) {
                    LeftMovable = false;
                }
                // 检测右侧碰撞
                if (NowXSpeed > 0 && obstacleRect.intersects(x + NowXSpeed, y, width, length)) {
                    RightMovable = false;
                }
                // 检测上侧碰撞
                if (NowYSpeed < 0 && obstacleRect.intersects(x, y + NowYSpeed, width, length)) {
                    UpMovable = false;
                }
                // 检测下侧碰撞
                if (NowYSpeed > 0 && obstacleRect.intersects(x, y + NowYSpeed, width, length)) {
                    DownMovable = false;
                }
            }

            // 应用移动修正
            applyMovement();
        }
    }

    /**
     * 根据可移动性标志限制移动
     * 修正移动速度以防止穿过障碍
     */
    private void applyMovement() {
        if (!LeftMovable && NowXSpeed < 0) NowXSpeed = 0;
        if (!RightMovable && NowXSpeed > 0) NowXSpeed = 0;
        if (!UpMovable && NowYSpeed < 0) NowYSpeed = 0;
        if (!DownMovable && NowYSpeed > 0) NowYSpeed = 0;
    }

    /**
     * 敌人基础移动逻辑
     * @param targetX 目标x坐标
     * @param targetY 目标y坐标
     * @param obstacles 障碍物列表
     */
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // 简单 AI 追踪逻辑示例
        if (targetX < Imagex) {
            NowXSpeed = -Xspeed;
        } else if (targetX > Imagex) {
            NowXSpeed = Xspeed;
        } else {
            NowXSpeed = 0;
        }

        if (targetY < Imagey) {
            NowYSpeed = -Yspeed;
        } else if (targetY > Imagey) {
            NowYSpeed = Yspeed;
        } else {
            NowYSpeed = 0;
        }

        // 判断障碍物并应用移动修正
        MovableJudgement(obstacles);
        Imagex += NowXSpeed;
        Imagey += NowYSpeed;

        // 同步实际坐标
        x = Imagex;
        y = Imagey + 11;
    }
}




