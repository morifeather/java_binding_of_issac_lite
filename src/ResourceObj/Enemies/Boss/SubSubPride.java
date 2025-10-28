package ResourceObj.Enemies.Boss;

import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SubSubPride extends Pride{
    // 构造函数：调用父类Pride的构造方法初始化位置、状态和方向
    public SubSubPride(int x,int y,int i,int toward){
        super(x,y,i,toward);
    }
    
    // 重写获取头部图像的方法，返回PrideHeadImage中的第2张图像
    @Override
    public BufferedImage getHeadImage(){
        return ResourceLoader.getInstance().PrideHeadImage.get(2);
    }
    
    // 重写获取身体图像的方法，返回null，表示该子类不提供身体图像
    @Override
    public BufferedImage getBodyImage(){
        return null;
    }
    
    // 重写更新位置的方法，实现碰到边界时反弹的逻辑
    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // 检查是否到达水平边界，若到达则反向水平速度
        if(this.x+length>=419 || this.x<=50){
            this.NowXSpeed=-this.NowXSpeed;
        }
        
        // 检查是否到达垂直边界，若到达则反向垂直速度
        if(this.y+width>=262 || this.y<=50){
            this.NowYSpeed=-this.NowYSpeed;
        }
        
        // 根据当前速度更新x和y坐标
        this.x+=NowXSpeed;
        this.y+=NowYSpeed;
        
        // 更新头部图像的位置与主体同步
        this.ImageHeadx=x;
        this.ImageHeady=y;
    }
}
