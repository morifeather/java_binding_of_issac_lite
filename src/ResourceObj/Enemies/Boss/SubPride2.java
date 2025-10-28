package ResourceObj.Enemies.Boss;

import ResourceObj.EnemyBullet;
import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * SubPride2 类表示 Pride Boss 的第二形态。
 * 该类继承自 Pride，并实现了特定的攻击和移动行为。
 */
public class SubPride2 extends Pride {
    
    // 射击计时器和射击状态标志
    protected int ShootTick = 0, ShootRate = 50;    
    protected boolean Shooted = false;

    /**
     * 构造函数，初始化 SubPride2 的位置、速度等属性。
     *
     * @param x      初始 x 坐标
     * @param y      初始 y 坐标
     * @param i      难度等级 (0-3)
     * @param toward 移动方向 (1=右, 2=左)
     */
    public SubPride2(int x, int y, int i, int toward) {
        super(x, y, i, toward);
    }

    /**
     * 获取头部图像，根据当前水平速度方向选择不同的图像。
     *
     * @return 返回当前状态下的头部图像
     */
    @Override
    public BufferedImage getHeadImage() {
        if (NowXSpeed > 0) {
            return ResourceLoader.getInstance().PrideHeadImage.get(0);
        } else {
            return ResourceLoader.getInstance().PrideHeadImage.get(1);
        }
    }

    /**
     * 获取身体图像。SubPride2 没有身体图像，因此返回 null。
     *
     * @return 返回 null
     */
    @Override
    public BufferedImage getBodyImage() {
        return null;
    }

    /**
     * 更新位置，处理边界碰撞并更新头像的位置。
     *
     * @param targetX 目标 x 坐标（未使用）
     * @param targetY 目标 y 坐标（未使用）
     * @param obstacles 障碍物列表（未使用）
     */
    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // 处理左右边界的碰撞
        if (this.x + length >= 419 || this.x <= 50) {
            this.NowXSpeed = -this.NowXSpeed;
        }
        // 处理上下边界的碰撞
        if (this.y + width >= 262 || this.y <= 50) {
            this.NowYSpeed = -this.NowYSpeed;
        }
        // 更新坐标
        this.x += NowXSpeed;
        this.y += NowYSpeed;
        // 更新头像位置
        this.ImageHeadx = x;
        this.ImageHeady = y;
    }

    /**
     * SubPride2 的特殊射击逻辑，发射 5-8 方向的子弹。
     *
     * @param enemyBullets 敌方子弹列表
     */
    public void SubPrideShoot(ArrayList<EnemyBullet> enemyBullets) {
        if (ShootTick == ShootRate && !Shooted) {
            for (int i = 5; i <= 8; i++) {
                EnemyBullet enemyBullet = new EnemyBullet();
                enemyBullets.add(enemyBullet);
                enemyBullet.EnemyBullet(x + 8, y + 7, i); // 发射点偏移
            }
            Shooted = true;
            ShootTick = 0;
        } else if (ShootTick < ShootRate) {
            Shooted = false;
            ShootTick++;
        }
    }
}
