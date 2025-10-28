package ResourceObj.obstacle;

import ResourceObj.Enemies.Enemy;
import ResourceObj.EnemyBullet;
import ResourceObj.ResourceLoader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ShootStone extends Obstacle{
    protected ResourceLoader resourceloader=ResourceLoader.getInstance();
    protected BufferedImage ShootStoneImage;
    protected int NowRoomState;
    protected int x,y,Toward;
    protected int ShootTick;
    protected boolean Shooted;
    protected int ShootRate=60;

    /**
     * 获取障碍物X坐标
     * @return X坐标值
     */
    public int getX(){return x;}
    
    /**
     * 获取障碍物Y坐标
     * @return Y坐标值
     */
    public int getY(){return y;}

    /**
     * 初始化射击石头对象
     * @param x 网格X坐标
     * @param y 网格Y坐标
     * @param Toward 射击方向（0-3）
     */
    @Override
    public void Initialize(int x,int y,int Toward){
        // 根据网格坐标计算实际像素位置
        this.x = 50+23*x;
        this.y = 50+23*y;
        // 加载对应方向的图片
        this.ShootStoneImage = resourceloader.ShootStoneImage.get(Toward);
        this.Toward=Toward;
    }
    
    /**
     * 获取当前状态下的显示图片
     * @param i 房间状态（0:关闭, 1:开启）
     * @return 对应状态下的图片
     */
    @Override
    public BufferedImage getImage(int i){
        // 记录当前房间状态
        NowRoomState=i;
        
        if(i==1){
            // 开启状态返回基本方向图片
            return resourceloader.ShootStoneImage.get(Toward);
        }
        else if(i==0){
            // 关闭状态返回偏移4个索引的基本方向图片
            return resourceloader.ShootStoneImage.get(Toward+4);
        }
        else{
            // 错误状态处理
            System.out.println("Room State Error");
            return null;
        }
    }

    /**
     * 射击逻辑处理方法
     * @param enemyBullets 敌方子弹列表容器
     */
    public void ShootStoneShoot(ArrayList<EnemyBullet> enemyBullets){
        // 如果房间未开启则不执行射击逻辑
        if (NowRoomState != 1) return;

        // 检查是否到达射击间隔且未射击过
        if (ShootTick == ShootRate && !Shooted) {
            // 重置计时器并标记为已射击
            ShootTick = 0;
            Shooted = true;
            
            // 创建新子弹并添加到列表中
            EnemyBullet enemyBullet = new EnemyBullet();
            enemyBullets.add(enemyBullet);
            
            // 根据朝向发射子弹
            if(Toward==0) {
                // 向下发射
                enemyBullet.EnemyBullet(x, y,2);
            }
            else if(Toward==1){
                // 向上发射
                enemyBullet.EnemyBullet(x, y,1);
            }
            else if(Toward==2){
                // 向右发射
                enemyBullet.EnemyBullet(x, y,4);
            }
             else if(Toward==3){
                // 向左发射
                enemyBullet.EnemyBullet(x, y,3);
            }
        } else if (ShootTick < ShootRate) {
            // 子弹冷却倒计时
            Shooted = false;
            ShootTick++;
        }
    }
}
