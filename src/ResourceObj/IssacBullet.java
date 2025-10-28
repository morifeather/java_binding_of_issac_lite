package ResourceObj;

import ResourceObj.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * 表示伊萨克发射的子弹对象，处理子弹的运动、生命周期和渲染相关数据。
 */
public class IssacBullet {
    //------------------------------------------------------------------------------------//
    // 资源加载器实例，用于获取图像资源
    ResourceLoader resourceloader = ResourceLoader.getInstance();

    /**
     * 设置资源加载器
     * @param resourceloader 新的资源加载器实例
     */
    public void setResourceloader(ResourceLoader resourceloader) {
        this.resourceloader = resourceloader;
    }
    //------------------------------------------------------------------------------------//

    // 子弹当前位置
    private int Bulletx, Bullety;
    // 子弹存在的时间（帧数）
    private int BulletExistTime = 0;
    // 图像绘制位置和尺寸
    private int ImageX, ImageY, ImageLength = 12, ImageWidth = 12;
    // 实际碰撞框尺寸
    private int width = 12, length = 12;
    // 速度分量
    private int Xv = 0, Yv = 0;
    // 子弹图像
    private BufferedImage IssacBulletImage;
    // 是否为敌方子弹（可能未使用）
    boolean Hostile = false;
    // 子弹是否仍然存在
    private boolean BulletExist = true;
    // 子弹是否处于死亡动画阶段
    private boolean Death = false;
    // 动画帧计数器
    private int ImageTick = 0;
    // 是否已更新过位置
    private boolean Updated = false;
    //------------------------------------------------------------------------------------//


    /**
     * 设置子弹的X坐标
     * @param x 新的X坐标
     */
    public void setX(int x) {
        if (BulletExist) {
            this.Bulletx = x;
            this.ImageX = x;
        }
        if (BulletExist == false) {
            this.ImageX = x - 21;
        }
    }

    /**
     * 设置子弹的Y坐标
     * @param y 新的Y坐标
     */
    public void setY(int y) {
        this.Bullety = y - 24;
    }

    /**
     * 设置子弹的存在状态
     * @param BulletExist 新的存在状态
     */
    public void setIssacBulletExist(boolean BulletExist) {
        this.BulletExist = BulletExist;
    }

    /**
     * 初始化子弹图像（默认取第一个图像）
     */
    public void setIssacBulletImage() {
        IssacBulletImage = resourceloader.BulletImage.get(0);
    }
    //------------------------------------------------------------------------------------//

    /**
     * 获取图像绘制的X坐标
     * @return 图像绘制的X坐标
     */
    public int getImageX() {
        return ImageX;
    }

    /**
     * 获取图像绘制的Y坐标
     * @return 图像绘制的Y坐标
     */
    public int getImageY() {
        return ImageY;
    }

    /**
     * 获取图像的高度
     * @return 图像的高度
     */
    public int getImageLength() {
        return ImageLength;
    }

    /**
     * 获取图像的宽度
     * @return 图像的宽度
     */
    public int getImageWidth() {
        return ImageWidth;
    }

    /**
     * 获取X方向速度
     * @return X方向速度
     */
    public int getXv() {
        return Xv;
    }

    /**
     * 获取Y方向速度
     * @return Y方向速度
     */
    public int getYv() {
        return Yv;
    }

    /**
     * 获取碰撞框宽度
     * @return 碰撞框宽度
     */
    public int getWidth() {
        return width;
    }

    /**
     * 获取碰撞框高度
     * @return 碰撞框高度
     */
    public int getLength() {
        return length;
    }

    /**
     * 获取子弹的X坐标
     * @return 子弹的X坐标
     */
    public int getBulletX() {
        return Bulletx;
    }

    /**
     * 获取子弹的Y坐标
     * @return 子弹的Y坐标
     */
    public int getBulletY() {
        return Bullety;
    }

    /**
     * 获取当前子弹图像
     * @return 当前子弹图像
     */
    public BufferedImage getIssacBulletImage() {
        return IssacBulletImage;
    }

    /**
     * 获取子弹存在状态
     * @return 子弹存在状态
     */
    public boolean getBulletExist() {
        return BulletExist;
    }

    /**
     * 获取子弹是否处于死亡状态
     * @return 子弹是否处于死亡状态
     */
    public boolean getDeath() {
        return Death;
    }
    //------------------------------------------------------------------------------------//

    /**
     * 初始化子弹的位置和速度
     * @param x 子弹初始X坐标
     * @param y 子弹初始Y坐标
     * @param IssacNowXSpeed 伊萨克当前X方向速度
     * @param IssacNowYSpeed 伊萨克当前Y方向速度
     * @param IssacShotSpeed 子弹基础射击速度
     * @param ShootingDirection 射击方向 (1=右, 2=左, 3=上, 4=下)
     */
    public void BulletInitialize(int x, int y, double IssacNowXSpeed, double IssacNowYSpeed, int IssacShotSpeed, int ShootingDirection) {
        Bulletx = x;
        Bullety = y;
        if (ShootingDirection == 1/*RightShooting*/) {
            Xv += (IssacNowXSpeed / 2 + IssacShotSpeed);
            Yv += (IssacNowYSpeed / 2);
        } else if (ShootingDirection == 2/*LeftShooting*/) {
            Xv += (IssacNowXSpeed / 2 - IssacShotSpeed);
            Yv += (IssacNowYSpeed / 2);
        } else if (ShootingDirection == 3/*UpShooting*/) {
            Xv += (IssacNowXSpeed / 2);
            Yv += (IssacNowYSpeed / 2 - IssacShotSpeed);
        } else if (ShootingDirection == 4/*DownShooting*/) {
            Xv += (IssacNowXSpeed / 2);
            Yv += (IssacNowYSpeed / 2 + IssacShotSpeed);
        }
    }

    /**
     * 更新子弹状态
     * @param IssacRange 子弹最大飞行范围
     */
    public void UpdateBullet(int IssacRange) {
        if (!Death) {
            if (BulletExistTime < IssacRange - 7) {
                Bulletx += Xv;
                Bullety += Yv;
                ImageX = Bulletx;
                ImageY = Bullety;
                BulletExistTime++;
            } else if (BulletExistTime >= IssacRange - 7 && BulletExistTime < IssacRange) {
                Bulletx += Xv;
                ImageX = Bulletx;
                Bullety += Yv + 1;
                ImageY = Bullety;
                BulletExistTime++;
            } else {
                Death = true;
            }
            // 检查边界碰撞
            if (Bulletx < 50 || Bulletx + width > 419 || Bullety + 11 < 50 || Bullety + 11 + width > 262) {
                Death = true;
            }
        } else if (Death) {
            IssacBulletDeath();
        }
    }

    /**
     * 设置子弹的死亡状态
     * @param Death 新的死亡状态
     */
    public void setDeath(boolean Death) {
        this.Death = Death;
    }

    /**
     * 处理子弹的死亡动画
     */
    public void IssacBulletDeath() {
        if (!Updated) {
            this.ImageY -= 20;
            this.ImageX -= 20;
            Updated = true;
        }
        ImageLength = 45;
        ImageWidth = 50;
        length = 0;
        width = 0;
        IssacBulletImage = resourceloader.IssacBulletDeleteImage.get(ImageTick);
        ImageTick++;
        if (ImageTick == 8) {
            BulletExist = false;
        }
    }
}
