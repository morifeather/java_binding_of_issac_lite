package ResourceObj;

import ResourceObj.Enemies.Enemy;

import java.awt.image.BufferedImage;

public class EnemyBullet {
    // 子弹的坐标
    private int Bulletx, Bullety;
    // 子弹存在的时间，初始为0
    private int BulletExistTime = 0;
    // 子弹的宽度、长度和速度
    int width = 12, length = 12, Xv, Yv;
    // 子弹的图像，从资源加载器中获取
    private BufferedImage EnemyBulletImage = ResourceLoader.getInstance().EnemyBulletImage.get(0);
    // 子弹是否具有敌意（是否对玩家有害）
    boolean Hostile = true;
    // 子弹的有效范围
    private int Range = 50;
    // 子弹是否存在的标志
    private boolean BulletExist = true;
    //------------------------------------------------------------------------------------//
    // 设置子弹是否存在
    public void setBulletExist(boolean bulletExist) {
        BulletExist = bulletExist;
    }
    //------------------------------------------------------------------------------------//
    // 获取子弹的X轴速度
    public int getXv() {
        return Xv;
    }

    // 获取子弹的Y轴速度
    public int getYv() {
        return Yv;
    }

    // 获取子弹的宽度
    public int getWidth() {
        return width;
    }

    // 获取子弹的高度（长度）
    public int getLength() {
        return length;
    }

    // 获取子弹的X轴坐标
    public int getBulletX() {
        return Bulletx;
    }

    // 获取子弹的Y轴坐标
    public int getBulletY() {
        return Bullety;
    }

    // 获取子弹的图像
    public BufferedImage getEnemyBulletImage() {
        return EnemyBulletImage;
    }

    // 获取子弹是否存在的状态
    public boolean getBulletExist() {
        return BulletExist;
    }

    //------------------------------------------------------------------------------------//
    // 初始化子弹的位置和方向
    public void EnemyBullet(int x, int y, int toward) {
        Bulletx = x;
        Bullety = y;

        // 根据传入的方向设置子弹的移动方向和速度
        if (toward == 2 /* Down */) {
            Yv = 5;
            Xv = 0;
        } else if (toward == 1 /* Up */) {
            Yv = -5;
            Xv = 0;
        } else if (toward == 4 /* Right */) {
            Xv = 5;
            Yv = 0;
        } else if (toward == 3 /* Left */) {
            Xv = -5;
            Yv = 0;
        } else if (toward == 5 /* RightDown */) {
            Xv = 3;
            Yv = 3;
        } else if (toward == 6 /* LeftDown */) {
            Xv = -3;
            Yv = 3;
        } else if (toward == 7 /* RightUp */) {
            Xv = 3;
            Yv = -3;
        } else if (toward == 8 /* LeftUp */) {
            Xv = -3;
            Yv = -3;
        } else {
            // 如果方向参数无效，则输出错误信息
            System.out.println("Error:EnemyBullet Direct Worngly");
        }
    }

    // 更新子弹的状态
    public void UpdateEnemyBullet() {
        // 如果子弹超出垂直边界，则销毁子弹
        if (Bulletx < 50 || Bulletx + 12 > 419) {
            BulletExist = false;
        }

        // 如果子弹超出水平边界，则销毁子弹
        if (Bullety < 50 || Bullety + 12 > 262) {
            BulletExist = false;
        }

        // 控制子弹的移动和生存时间
        if (BulletExistTime <= Range - 7) {
            Bulletx += Xv;
            Bullety += Yv;
            BulletExistTime++;
        } else if (BulletExistTime > Range - 7 && BulletExistTime < Range) {
            Bulletx += Xv;
            Bullety += Yv + 1;
            BulletExistTime++;
        } else if (BulletExistTime >= Range) {
            BulletExist = false;
        }
    }
}
