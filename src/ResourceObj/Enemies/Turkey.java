package ResourceObj.Enemies;

import ResourceObj.EnemyBullet;
import ResourceObj.Issac;
import ResourceObj.ResourceLoader;
import ResourceObj.SoundEffect;
import ResourceObj.obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Turkey extends Enemy {
    private int AnimationSlow; // 动画帧计数器，控制动画播放速度
    private int MovingSlow;   // 移动速度控制器，用于减缓移动频率
    private int ShootRate = 60, ShootTick = 0; // 射击冷却时间和当前冷却计时
    private int ImageTick = 1; // 当前图像帧索引，用于切换动画帧
    private int MovingDirection; // 移动方向：1-左，2-右
    boolean Shooted = false; // 是否已经射击的标志

    public Turkey() {
        super();
        this.hp = 21; // 设置初始血量为21
        this.Imagelength = 44; // 图像高度
        this.Imagewidth = 42;  // 图像宽度
        this.length = 29; // 实际碰撞箱高度
        this.width = 31;  // 实际碰撞箱宽度
        this.Xspeed = 1; // 水平移动速度
        this.Yspeed = 1; // 垂直移动速度
        this.flight = false; // 是否飞行状态（false表示不能飞行）
    }

    /*---------------------------------------------------------------------------------------------*/
    @Override
    public int getX() {
        return x; // 返回实际x坐标
    }

    @Override
    public int getY() {
        return y; // 返回实际y坐标
    }

    @Override
    public BufferedImage getImage() {
        // 根据移动方向选择对应的图像资源
        if (MovingDirection == 1) {
            if(ImageTick==0){
                ImageTick = 1; // 如果图像帧为0则设为1防止越界
            }
            return ResourceLoader.getInstance().TurkeyLeftImage.get(ImageTick - 1); // 获取向左的图像帧
        } else if (MovingDirection == 2) {
            if(ImageTick==0){
                ImageTick = 1; // 如果图像帧为0则设为1防止越界
            }
            return ResourceLoader.getInstance().TurkeyRightImage.get(ImageTick - 1); // 获取向右的图像帧
        } else {
            if(ImageTick==0){
                ImageTick = 1; // 如果图像帧为0则设为1防止越界
            }
            return ResourceLoader.getInstance().TurkeyRightImage.get(ImageTick - 1); // 默认返回向右的图像帧
        }
    }

    @Override
    public int getImagex() {
        return Imagex; // 返回图像的x坐标位置
    }

    @Override
    public int getImagey() {
        return Imagey; // 返回图像的y坐标位置
    }

    @Override
    public int getImagelength() {
        return Imagelength; // 返回图像的高度
    }

    @Override
    public int getImagewidth() {
        return Imagewidth; // 返回图像的宽度
    }

    @Override
    public int getLength() {
        return length; // 返回碰撞箱的高度
    }

    @Override
    public int getWidth() {
        return width; // 返回碰撞箱的宽度
    }

    /*---------------------------------------------------------------------------------------------*/
    @Override
    public void Initialize(int x, int y) {
        this.Imagex = x; // 初始化图像的x坐标
        this.Imagey = y; // 初始化图像的y坐标
        this.x = Imagex; // 同步更新实际x坐标
        this.y = Imagey + 11; // 同步更新实际y坐标，并加上偏移值
    }

    // Turkey 的射击方法
    public void TurkeyShoot(ArrayList<EnemyBullet> enemyBullets) {
        // 如果射击冷却时间达到设定值且尚未射击
        if (ShootTick == ShootRate && !Shooted) {
            // 发射4发子弹，方向分别为上、下、左、右
            for (int i = 1; i <= 4; i++) {
                EnemyBullet enemyBullet = new EnemyBullet(); // 创建新的子弹对象
                enemyBullets.add(enemyBullet); // 添加到子弹列表中
                enemyBullet.EnemyBullet(x + 10, y + 20, i); // 设置子弹起始位置和方向
            }
            Shooted = true; // 标记为已射击
            ShootTick = 0; // 重置射击计时器
        } else if (ShootTick < ShootRate) {
            Shooted = false; // 如果未达到射击冷却时间，则保持未射击状态
            ShootTick++; // 增加射击计时器
        }
    }

    @Override
    public void UpdateImage() {
        AnimationSlow++; // 增加动画计时器
        if (AnimationSlow == 1) {
            ImageTick++; // 更新图像帧索引
            if(ImageTick==3){ // 在特定帧播放音效
                SoundEffect.playSound("MeatJump","sounds/FleshJump.mp3");
            }
            AnimationSlow = 0; // 重置动画计时器
        }
        if (ImageTick > 11) { // 循环播放动画帧
            ImageTick = 1;
        }
    }

    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        MovableJudgement(obstacles); // 判断障碍物并设置可移动状态

        if (MovingSlow == 2) {
            // 根据目标坐标调整位置，同时考虑障碍物阻挡情况
            if (this.Imagex < targetX && RightMovable) {
                this.Imagex += Xspeed;
                NowXSpeed = Xspeed;
                MovingDirection = 2;
            } else if (this.Imagex > targetX && LeftMovable) {
                this.Imagex -= Xspeed;
                NowXSpeed = -Xspeed;
                MovingDirection = 1;
            }

            if (this.Imagey < targetY && DownMovable) {
                this.Imagey += Yspeed;
                NowYSpeed = Yspeed;
            } else if (this.Imagey > targetY && UpMovable) {
                this.Imagey -= Yspeed;
                NowYSpeed = -Yspeed;
            }

            MovingSlow = 0; // 重置移动控制器
        }

        MovingSlow++; // 增加移动控制器计时
        this.x = Imagex; // 更新实际x坐标
        this.y = Imagey + 11; // 更新实际y坐标并加上偏移值
    }
}
