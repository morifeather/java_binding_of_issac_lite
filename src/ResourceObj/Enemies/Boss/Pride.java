package ResourceObj.Enemies.Boss;

import ResourceObj.Enemies.Enemy;
import ResourceObj.EnemyBullet;
import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Pride 类表示骄傲 Boss，是游戏中的一个敌人角色。
 * 它继承自 Enemy 类，并实现了特定的移动和攻击行为。
 */
public class Pride extends Enemy {
    // 头部图像的位置和尺寸
    protected int ImageHeadx, ImageHeady, ImageHeadlength, ImageHeadwidth;
    // 实体位置和尺寸
    protected int x, y;
    protected int length, width;
    // 当前速度
    protected int NowXSpeed, NowYSpeed;
    // 射击计时器和射速
    protected int ShootTick = 0, ShootRate = 50;
    protected boolean Shooted = false;

    /**
     * 构造函数用于创建 Pride 实例。
     *
     * @param x      初始 x 坐标
     * @param y      初始 y 坐标
     * @param i      难度等级 (0-3)
     * @param toward 移动方向 (1=右, 2=左)
     */
    public Pride(int x, int y, int i, int toward) {
        if (i == 0) {
            this.x = x;
            this.y = y;
            this.hp = 201;
            this.ImageHeadx = x - 6;
            this.ImageHeady = y - 19;
            this.length = 16;
            this.width = 15;
            this.ImageHeadlength = 28;
            this.ImageHeadwidth = 25;
            if (toward == 1) {
                this.NowXSpeed = 1;
            } else if (toward == 2) {
                this.NowXSpeed = -1;
            }
            this.NowYSpeed = 1;
        } else if (i == 1) {
            this.x = x;
            this.y = y;
            this.ImageHeadx = x;
            this.ImageHeady = y;
            this.hp = 102;
            this.ImageHeadlength = 28;
            this.ImageHeadwidth = 25;
            this.length = 28;
            this.width = 25;
            if (toward == 1) {
                this.NowXSpeed = 2;
            } else if (toward == 2) {
                this.NowXSpeed = -2;
            }
            this.NowYSpeed = 2;
        } else if (i == 2) {
            this.x = x;
            this.y = y;
            this.ImageHeadx = x;
            this.ImageHeady = y;
            this.hp = 51;
            this.ImageHeadlength = 20;
            this.ImageHeadwidth = 19;
            this.length = 20;
            this.width = 19;
            if (toward == 1) {
                this.NowXSpeed = 3;
            } else if (toward == 2) {
                this.NowXSpeed = -3;
            }
            this.NowYSpeed = 3;
        } else if (i == 3) {
            this.x = x;
            this.y = y;
            this.ImageHeadx = x;
            this.ImageHeady = y;
            this.hp = 27;
            this.ImageHeadlength = 13;
            this.ImageHeadwidth = 13;
            this.length = 13;
            this.width = 13;
            if (toward == 1) {
                this.NowXSpeed = 5;
            } else if (toward == 2) {
                this.NowXSpeed = -5;
            }
            this.NowYSpeed = 5;
        }
    }

    /**
     * 获取头部图像。
     *
     * @return 返回当前状态下的头部图像
     */
    public BufferedImage getHeadImage() {
        if (NowXSpeed < 0) {
            return ResourceLoader.getInstance().PrideHeadImage.get(1);
        } else {
            return ResourceLoader.getInstance().PrideHeadImage.get(0);
        }
    }

    /**
     * 获取身体图像。
     *
     * @return 返回当前状态下的身体图像
     */
    public BufferedImage getBodyImage() {
        if (NowXSpeed < 0) {
            return ResourceLoader.getInstance().PrideBodyImage.get(1);
        } else {
            return ResourceLoader.getInstance().PrideBodyImage.get(0);
        }
    }

    /*--------------------------------------------------------------------------------------------
     * Getter 方法
     *--------------------------------------------------------------------------------------------*/

    public int getImageHeadx() {
        return ImageHeadx;
    }

    public int getImageHeady() {
        return ImageHeady;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    public int getImageHeadLength() {
        return ImageHeadlength;
    }

    public int getImageHeadWidth() {
        return ImageHeadwidth;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public int getWidth() {
        return width;
    }

    /*--------------------------------------------------------------------------------------------
     * 其他方法实现
     *--------------------------------------------------------------------------------------------*/

    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // 碰撞检测：碰到边界则反向移动
        if (this.x + length >= 419 || this.x <= 50) {
            this.NowXSpeed = -this.NowXSpeed;
        }
        if (this.y + width >= 262 || this.y <= 50) {
            this.NowYSpeed = -this.NowYSpeed;
        }
        // 更新坐标
        this.x += NowXSpeed;
        this.y += NowYSpeed;
        // 计算头部位置
        this.ImageHeadx = x - 6;
        this.ImageHeady = y - 19;
    }

    /**
     * PrideBoss 的射击逻辑，发射八方向子弹。
     *
     * @param enemyBullets 敌方子弹列表
     */
    public final void PrideShoot(ArrayList<EnemyBullet> enemyBullets) {
        if (ShootTick == ShootRate && !Shooted) {
            for (int i = 1; i <= 8; i++) {
                EnemyBullet enemyBullet = new EnemyBullet();
                enemyBullets.add(enemyBullet);
                enemyBullet.EnemyBullet(x + 8, y + 7, i); // 发射点在中心偏移位置
            }
            Shooted = true;
            ShootTick = 0;
        } else if (ShootTick < ShootRate) {
            Shooted = false;
            ShootTick++;
        }
    }
}
