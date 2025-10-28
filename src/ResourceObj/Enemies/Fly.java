package ResourceObj.Enemies;

import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Fly extends Enemy{
    private int flysoundtick=0;
    public Fly(){
        super();
        this.hp = 9; // 设置Fly怪物的生命值为9
        this.Xspeed = 2; // 设置水平移动速度为2
        this.Yspeed = 2; // 设置垂直移动速度为2
        this.flight = true; // 表示该敌人可以飞行（忽略障碍物）
        this.Imagelength=19; // 图像长度为19像素
        this.Imagewidth=15; // 图像宽度为15像素
        this.length=8; // 实际碰撞箱长度为8像素
        this.width=8; // 实际碰撞箱宽度为8像素
    }
    
    @Override
    public void Initialize(int x,int y){
        this.Imagex=x;this.Imagey=y; // 设置图像的起始坐标
        x=Imagex+5;y=Imagey+7; // 修改传入的x和y坐标，用于设置实际位置（但未使用）
    }
    
    @Override
    public BufferedImage getImage(){
        return ResourceLoader.getInstance().FlyImage.get(ImageTick); // 获取当前帧的Fly图像
    }
    
    @Override
    public void UpdateImage(){
        ImageTick++; // 增加图像索引
        if(ImageTick>3){ // 如果图像索引超过3
            ImageTick=0; // 重置为0，实现图像循环播放
        }
    }
    
    @Override
    public void UpdateLocation(int x, int y, ArrayList<Obstacle> obstacles){
        if(this.Imagex<x){ // 如果当前x坐标小于目标x坐标
            this.Imagex+=Xspeed; // 向右移动
            this.NowXSpeed=Xspeed; // 记录当前x方向速度
        }
        if(this.Imagex>x){ // 如果当前x坐标大于目标x坐标
            this.Imagex-=Xspeed; // 向左移动
            this.NowXSpeed=-Xspeed; // 记录当前x方向速度
        }
        if(this.Imagey<y){ // 如果当前y坐标小于目标y坐标
            this.Imagey+=Yspeed; // 向下移动
            this.NowYSpeed=Yspeed; // 记录当前y方向速度
        }
        if(this.Imagey>y){ // 如果当前y坐标大于目标y坐标
            this.Imagey-=Yspeed; // 向上移动
            this.NowYSpeed=-Yspeed; // 记录当前y方向速度
        }
        this.x=Imagex+5;this.y=Imagey+7; // 更新实际坐标
    }

    public void setFlysoundtick(int flysoundtick){
         this.flysoundtick=flysoundtick; // 设置flysoundtick值
    }
    
    public int  getFlysoundtick(){
        return flysoundtick; // 获取flysoundtick值
    }

}
