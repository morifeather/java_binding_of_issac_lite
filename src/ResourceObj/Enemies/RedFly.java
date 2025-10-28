package ResourceObj.Enemies;

import ResourceObj.EnemyBullet;
import ResourceObj.Issac;
import ResourceObj.ResourceLoader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RedFly extends Fly{
    // 构造函数：初始化 RedFly 的属性
    public RedFly()
    {
        super(); // 调用父类 Fly 的构造函数
        this.hp = 12; // 设置生命值为 12
        this.Xspeed = 1; // 水平速度为 1
        this.Yspeed = 1; // 垂直速度为 1
        this.Imagelength = 19; // 图像长度为 19
        this.Imagewidth = 23; // 图像宽度为 23
        this.length = 11; // 实际碰撞区域长度为 11
        this.width = 10; // 实际碰撞区域宽度为 10
    }

    // 初始化方法：设置图像坐标和实际位置坐标
    @Override
    public void Initialize(int x, int y)
    {
        this.Imagex = x; // 设置图像的 X 坐标
        this.Imagey = y; // 设置图像的 Y 坐标
        this.x = x + 2; // 设置实际位置的 X 坐标，偏移 2
        this.y = y + 11; // 设置实际位置的 Y 坐标，偏移 11
    }

    // 更新图像帧的方法
    @Override
    public void UpdateImage() {
        ImageTick++; // 图像帧计数器递增
        if(ImageTick > 1){
            ImageTick = 0; // 如果超过最大值，则重置为 0，实现循环播放
        }
    }

    // 获取当前图像的方法
    @Override
    public BufferedImage getImage() {
        // 根据 Isaac 的位置决定使用向左还是向右的图像
        if (Issac.getInstance().getX() > x) {
            return ResourceLoader.getInstance().RedFlyRightImage.get(ImageTick); // 使用向右的图像
        }
        else {
            return ResourceLoader.getInstance().RedFlyLeftImage.get(ImageTick); // 使用向左的图像
        }
    }

    // 死亡时发射子弹的方法
    public void DeathBullet(ArrayList<EnemyBullet> enemyBullets){
        // 循环创建 4 颗子弹（i 从 1 到 4）
        for(int i=1;i<5;i++){
            EnemyBullet enemyBullet=new EnemyBullet(); // 创建一颗敌方子弹对象
            enemyBullets.add(enemyBullet); // 将该子弹添加到子弹列表中
            enemyBullet.EnemyBullet(x,y,i); // 初始化子弹的位置和方向
        }
    }
}
