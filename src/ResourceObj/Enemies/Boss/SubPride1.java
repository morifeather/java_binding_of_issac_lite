package ResourceObj.Enemies.Boss;

import ResourceObj.EnemyBullet;
import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * SubPride1 类是 Pride Boss 的子形态类，继承自 Pride 类。
 * 该类实现了特定的头部图像显示、移动逻辑和射击逻辑。
 */
public class SubPride1 extends Pride {
    // 射击计时器和射弹状态标志
    protected int ShootTick = 0, ShootRate = 50;
    protected boolean Shooted = false;

    /**
     * 构造函数初始化位置和方向
     *
     * @param x      初始 x 坐标
     * @param y      初始 y 坐标
     * @param i      难度等级 (0-3)
     * @param toward 移动方向 (1=右, 2=左)
     */
    public SubPride1(int x, int y, int i, int toward) {
        super(x, y, i, toward);
    }

    /**
     * 获取当前状态下的头部图像
     *
     * @return 返回对应方向的头部图像
     */
    @Override
    public BufferedImage getHeadImage() {
        if (NowXSpeed > 0) {
            return ResourceLoader.getInstance().PrideHeadImage.get(0); // 向右方向
        } else {
            return ResourceLoader.getInstance().PrideHeadImage.get(1); // 向左方向
        }
    }

    /**
     * 获取身体部分的图像（SubPride1 没有身体图像）
     *
     * @return 总是返回 null
     */
    @Override
    public BufferedImage getBodyImage() {
        return null;
    }

    /**
     * 更新位置并处理边界反弹
     *
     * @param targetX 目标 x 坐标（未使用）
     * @param targetY 目标 y 坐标（未使用）
     * @param obstacles 障碍物列表（未使用）
     */
    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // 碰撞边界后反弹
        if (this.x + length >= 419 || this.x <= 50) {
            this.NowXSpeed = -this.NowXSpeed;
        }
        if (this.y + width >= 262 || this.y <= 50) {
            this.NowYSpeed = -this.NowYSpeed;
        }

        // 更新坐标
        this.x += NowXSpeed;
        this.y += NowYSpeed;

        // 更新头部图像位置
        this.ImageHeadx = x;
        this.ImageHeady = y;
    }

    /**
     * 实现射击逻辑，发射 4 个方向的敌方子弹
     *
     * @param enemyBullets 存储敌方子弹的列表
     */
    public void SubPrideShoot(ArrayList<EnemyBullet> enemyBullets) {
        if (ShootTick == ShootRate && !Shooted) {
            for (int i = 1; i <= 4; i++) {
                EnemyBullet enemyBullet = new EnemyBullet();
                enemyBullets.add(enemyBullet);
                enemyBullet.EnemyBullet(x + 8, y + 7, i); // 发射点偏移量为 (8,7)
            }
            Shooted = true;
            ShootTick = 0;
        } else if (ShootTick < ShootRate) {
            Shooted = false;
            ShootTick++;
        }
    }
}