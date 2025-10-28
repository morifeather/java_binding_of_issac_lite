package ResourceObj.Enemies;

import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tentacle extends Enemy{
    int AnimationSlow;
    public Tentacle(){
        super();
        // 设置触手敌人初始生命值为18
        this.hp = 18;
        // 初始化图像帧计数器
        this.ImageTick = 0;
        // 设置该敌人为非飞行单位
        this.flight = false;
        // 设置实体碰撞区域的尺寸
        this.length=26;
        this.Imagelength=26;
        this.width=17;
        this.Imagewidth=50;
    }
    @Override
    public void Initialize(int x, int y) {
        // 设置图像坐标和实际坐标
        this.Imagex=x;
        this.Imagey=y;
        this.x=x;
        // y轴偏移33像素以调整显示位置
        this.y=y+33;
    }
    @Override
    public void UpdateImage() {
        // 动画速度计数器递增
        AnimationSlow++;
        if(AnimationSlow==3){
            // 每3帧更新一次图像帧
            this.ImageTick++;
            if(ImageTick>5){
                // 图像帧循环，最大索引为5
                this.ImageTick=0;
            }
            AnimationSlow=0;
        }
    }
    @Override
    public BufferedImage getImage() {
        // 获取当前图像帧，来自资源加载器中的TentacleImage列表
        return ResourceLoader.getInstance().TentacleImage.get(this.ImageTick);
    }
    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
    }
}
