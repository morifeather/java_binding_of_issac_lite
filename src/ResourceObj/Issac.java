package ResourceObj;

import ResourceObj.obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Issac{
    // Isaac 的位置坐标
    private int oldx,oldy;
    private int x=240;      // X轴初始位置
    private int y=175;      // Y轴初始位置
    
    // 移动速度相关
    private int Xspeed=5;   // X轴最大速度
    private int Yspeed=5;   // Y轴最大速度
    private int nowXspeed=0; // 当前X轴速度
    private int nowYspeed=0; // 当前Y轴速度
    
    // 角色属性
    private int damage=999;       // 攻击伤害
    private int shotspeed=6;    // 子弹速度
    private int hp=4;           // 生命值
    private int range=25;       // 攻击范围
    private int flight;         // 飞行能力（可能用于特殊状态）
    
    // 动画控制参数
    private int acceleration1=2;  // 加速系数1
    private int acceleration2=1;  // 加速系数2
    private int HeadTick=0;        // 头部动画帧计数器
    private int HeadTickSpeed=7;  // 头部动画播放速度
    private int HeadTickCount=0;  // 头部动画时钟计数器
    private int BodyTick=0;        // 身体动画帧计数器
    
    // 尺寸参数
    private int IssacLength=15;   // Isaac 的长度
    private int IssacWidth=18;    // Isaac 的宽度
    
    // 方向与状态
    private int LeftOrRight;       // 左右方向标识
    private int shootingDirection; // 射击方向
    private int XSlowenTick=0;     // X轴减速计时器
    private int YSlowenTick=0;     // Y轴减速计时器
    private int DeathTick=0;       // 死亡动画帧计数器
    
    //----------------------------------------------------------------------------//
    // 状态标志位
    private boolean runL=false;    // 是否正在向左跑
    private boolean runR=false;    // 是否正在向右跑
    private boolean runU=false;    // 是否正在向上跑
    private boolean runD=false;    // 是否正在向下跑
    
    private boolean shootL=false;  // 是否正在向左射击
    private boolean shootR=false;  // 是否正在向右射击
    private boolean shootU=false;  // 是否正在向上射击
    private boolean shootD=false;  // 是否正在向下射击
    
    private boolean Leftmovable=true;  // 左侧是否可移动
    private boolean Rightmovable=true; // 右侧是否可移动
    private boolean Upmovable=true;    // 上方是否可移动
    private boolean Downmovable=true;  // 下方是否可移动
    
    private boolean moving;      // 是否正在移动
    private boolean shooting;    // 是否正在射击
    private volatile boolean shooted; // 是否已经射击
    private boolean HeartSwitch=true; // 心脏开关（可能用于血量显示切换）

    //----------------------------------------------------------------------------//
    // 单例模式实例
    public static Issac instance=new Issac();
    public static Issac getInstance(){ return instance;}
    
    //----------------------------------------------------------------------------//
    // 障碍物对象及图像资源
    private Obstacle obstacle=new Obstacle();
    private BufferedImage nowHeadImage;   // 当前头部图像
    private BufferedImage nowBodyImage;   // 当前身体图像
    
    //----------------------------------------------------------------------------//
    // 获取方法
    public int getHeadTickSpeed(){return HeadTickSpeed;} // 获取头部动画速度
    
    //----------------------------------------------------------------------------//
    // 图像集合
    // 跑动方向的图像集合
    private ArrayList<BufferedImage> IssacRunLeft =new ArrayList<>();
    private ArrayList<BufferedImage> IssacRunRight =new ArrayList<>();
    private ArrayList<BufferedImage> IssacRunUp =new ArrayList<>();
    private ArrayList<BufferedImage> IssacRunDown =new ArrayList<>();
    
    // 头部方向的图像集合
    private ArrayList<BufferedImage> IssacHeadLeft =new ArrayList<>();
    private ArrayList<BufferedImage> IssacHeadRight =new ArrayList<>();
    private ArrayList<BufferedImage> IssacHeadUp =new ArrayList<>();
    private ArrayList<BufferedImage> IssacHeadDown =new ArrayList<>();
    
    // 当前使用的图像集合
    public ArrayList<BufferedImage> NowHeadImage = new ArrayList<>();
    public ArrayList<BufferedImage> NowBodyImage = new ArrayList<>();

    //----------------------------------------------------------------------------//
    // 设置当前头身图像
    public void setNowHeadImage(BufferedImage nowHeadImage){this.nowHeadImage=nowHeadImage;}
    public void setNowBodyImage(BufferedImage nowBodyImage){this.nowBodyImage=nowBodyImage;}
    
    //----------------------------------------------------------------------------//
    // 各类setter方法
    public void setHeadTickSpeed(int HeadTickSpeed){this.HeadTickSpeed=HeadTickSpeed;}
    public void setHeartSwitch(boolean HeartSwitch){this.HeartSwitch=HeartSwitch;}
    public void setXSlowenTick(int XslowenTick){this.XSlowenTick=XslowenTick;}
    public void setYSlowenTick(int YslowenTick){this.YSlowenTick=YslowenTick;}
    public void setRunL(boolean runL){this.runL=runL;}
    public void setRunR(boolean runR){this.runR=runR;}
    public void setRunU(boolean runU){this.runU=runU;}
    public void setRunD(boolean runD){this.runD=runD;}
    public void setShootL(boolean shootL){this.shootL=shootL;}
    public void setShootR(boolean shootR){this.shootR=shootR;}
    public void setShootU(boolean shootU){this.shootU=shootU;}
    public void setShootD(boolean shootD){this.shootD=shootD;}
    public void setMoving(boolean moving){this.moving=moving;}
    public void setLeftmovable(boolean leftmovable){this.Leftmovable=leftmovable;}
    public void setRightmovable(boolean rightmovable){this.Rightmovable=rightmovable;}
    public void setUpmovable(boolean upmovable){this.Upmovable=upmovable;}
    public void setDownmovable(boolean downmovable){this.Downmovable=downmovable;}
    public void setShooting(boolean shooting){this.shooting=shooting;}
    public void setNowXspeed(int nowXspeed){this.nowXspeed=nowXspeed;}
    public void setNowYspeed(int nowYspeed){this.nowYspeed=nowYspeed;}
    public void setFlight(int flight){
        this.flight=flight;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public void setXSpeed(int Xspeed){
        this.Xspeed=Xspeed;
    }
    public void setYSpeed(int Yspeed){
        this.Yspeed=Yspeed;
    }
    public void setDamage(int damage){
        this.damage=damage;
    }
    public void setShotspeed(int shotspeed){
        this.shotspeed=shotspeed;
    }
    public void setHp(int hp){
        this.hp=hp;
    }
    public void setOldx(int oldx){this.oldx=oldx;}
    public void setOldy(int oldy){this.oldy=oldy;}
    public void setShooted(boolean shooted){this.shooted=shooted;}
    public void setDeathTick(int DeathTick){this.DeathTick=DeathTick;}
    
    //----------------------------------------------------------------------------//
    // 各类getter方法
    public boolean getRunL(){return runL;}
    public boolean getRunR(){return runR;}
    public boolean getRunU(){return runU;}
    public boolean getRunD(){return runD;}
    public boolean getShootL(){return shootL;}
    public boolean getShootR(){return shootR;}
    public boolean getShootU(){return shootU;}
    public boolean getShootD(){return shootD;}
    public boolean getShooting(){return shooting;}
    public boolean getMoving(){return moving;}
    public boolean getLeftmovable(){return Leftmovable;}
    public boolean getRightmovable(){return Rightmovable;}
    public boolean getUpmovable(){return Upmovable;}
    public boolean getDownmovable(){return Downmovable;}
    public boolean getShooted(){return shooted;}
    public boolean getHeartSwitch(){return HeartSwitch;}
    
    //----------------------------------------------------------------------------//
    public boolean CollidingWithObstacle(Obstacle obstacle) {
        int issacWidth = 18; // Isaac 的宽度
        int issacHeight = 15; // Isaac 的高度
        
        // 创建 Isaac 和障碍物的矩形区域用于碰撞检测
        Rectangle issacRect = new Rectangle(x+nowXspeed, y+nowYspeed, issacWidth, issacHeight);
        Rectangle obstacleRect = new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA());
        
        // 返回两个矩形是否相交（即是否发生碰撞）
        return issacRect.intersects(obstacleRect);
    }
    public void MovableJudgement(ArrayList<Obstacle> obstacles) {
        Leftmovable = true;
        Rightmovable = true;
        Upmovable = true;
        Downmovable = true;

        // Isaac 的当前位置矩形
        Rectangle issacRect = new Rectangle(x+5, y+18, IssacWidth, IssacLength);

        // 边界判断
        if(x + 6 <= 50) Leftmovable = false;
        if(x + 23 >= 419) Rightmovable = false;
        if(y + 18 <= 50) Upmovable = false;
        if(y + 33 >= 262) Downmovable = false;

        // 障碍物判断（每帧都做）
        for (Obstacle obstacle : obstacles) {
            Rectangle obstacleRect = new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA());
            /*System.out.println("Obstacle pos - x: " + ResourceObj.obstacle.getX() + ", y: " + ResourceObj.obstacle.getY());
*/
            // 预测下一步是否会撞墙
            if (nowXspeed < 0) {
                Rectangle leftCheck = new Rectangle(x+5 + nowXspeed, y+18, IssacWidth, IssacLength);
                if (leftCheck.intersects(obstacleRect)) Leftmovable = false;
            } else if (nowXspeed > 0) {
                Rectangle rightCheck = new Rectangle(x+5 + nowXspeed, y+18, IssacWidth, IssacLength);
                if (rightCheck.intersects(obstacleRect)) Rightmovable = false;
            }

            if (nowYspeed < 0) {
                Rectangle upCheck = new Rectangle(x+5, y+18 + nowYspeed, IssacWidth, IssacLength);
                if (upCheck.intersects(obstacleRect)) Upmovable = false;
            } else if (nowYspeed > 0) {
                Rectangle downCheck = new Rectangle(x+5, y+18 + nowYspeed, IssacWidth, IssacLength);
                if (downCheck.intersects(obstacleRect)) Downmovable = false;
            }
        }

        // 即使 nowXspeed == 0，也禁止越过障碍物边界
        if (!Leftmovable && x <= 50) Leftmovable = false;
        if (!Rightmovable && x >= 419 - IssacWidth) Rightmovable = false;
        if (!Upmovable && y <= 50) Upmovable = false;
        if (!Downmovable && y >= 262 - IssacLength) Downmovable = false;

        // 只在对应方向不可移动时限制速度为 0 或轻微反向速度
        if (!Leftmovable && nowXspeed < 0) nowXspeed = 0; // 左侧不能移动，则停止左移
        if (!Rightmovable && nowXspeed > 0) nowXspeed = 0; // 右侧不能移动，则停止右移
        if (!Upmovable && nowYspeed < 0) nowYspeed = 0; // 上方不能移动，则停止上移
        if (!Downmovable && nowYspeed > 0) nowYspeed = 0; // 下方不能移动，则停止下移
    }


    //----------------------------------------------------------------------------//
    // 获取加速系数1，用于控制角色移动加速效果
    public int getAcceleration1(){return acceleration1;}
    // 获取加速系数2，用于控制角色移动减速效果
    public int getAcceleration2(){return acceleration2;}
    // 获取当前X轴速度，表示角色水平方向的移动速度
    public int getNowXSpeed(){return nowXspeed;}
    // 获取当前Y轴速度，表示角色垂直方向的移动速度
    public int getNowYSpeed(){return nowYspeed;}
    // 获取头部动画帧计数器，用于控制射击时头部动画播放
    public int getHeadTick(){return HeadTick;}
    // 获取攻击范围，用于确定子弹发射距离或其他影响范围
    public int getRange(){return range;}
    // 获取角色攻击力，决定对敌人造成伤害的数值
    public int getDamage() {
        return damage;
    }
    // 获取X轴最大速度，定义角色在水平方向的最大移动速度
    public int getXspeed() {
        return Xspeed;
    }
    // 获取Y轴最大速度，定义角色在垂直方向的最大移动速度
    public int getYspeed(){
        return Yspeed;
    }
    // 获取当前生命值，用于判断角色是否死亡或显示血量信息
    public int getHp() { return hp;
    }
    // 获取子弹速度，控制子弹在屏幕上的移动速度
    public int getShotspeed(){
        return shotspeed;
    }
    // 获取上一帧的X坐标，用于位置变化计算或碰撞检测
    public int getOldx(){return oldx;}
    // 获取上一帧的Y坐标，用于位置变化计算或碰撞检测
    public int getOldy(){return oldy;}
    // 获取当前X坐标，用于定位角色在游戏地图中的位置
    public int getX() {
        return x;
    }
    // 获取当前Y坐标，用于定位角色在游戏地图中的位置
    public int getY() {
        return y;
    }
    // 获取飞行能力状态，可能用于特殊技能或无敌状态
    public int getFlight(){
        return flight;
    }
    // 获取射击方向，用于确定子弹发射的方向
    public int getShootingDirection(){return shootingDirection;}
    // 获取左右方向标识，通常与动画播放相关
    public int getLeftOrRight(){return LeftOrRight;}
    // 获取X轴减速计时器，用于控制减速过程的时间
    public int getXSlowenTick(){return XSlowenTick;}
    // 获取Y轴减速计时器，用于控制减速过程的时间
    public int getYSlowenTick(){return YSlowenTick;}
    //----------------------------------------------------------------------------------------//
    // 获取当前身体图像，用于渲染角色主体部分
    public BufferedImage getNowBodyImage(){return nowBodyImage;}
    // 获取当前头部图像，用于渲染角色头部以表现不同动作
    public BufferedImage getNowHeadImage(){return nowHeadImage;}
    //----------------------------------------------------------------------------------------//


    // 加载角色身体各方向的动画帧图片资源
    public void LoadBodyImage(){
        try{
                // 左侧移动时的身体动画帧加载
                IssacRunLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/LEFT/IssacBody1.png")));
                IssacRunLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/LEFT/IssacBody2.png")));
                IssacRunLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/LEFT/IssacBody3.png")));
                IssacRunLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/LEFT/IssacBody4.png")));
                IssacRunLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/LEFT/IssacBody5.png")));
                IssacRunLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/LEFT/IssacBody6.png")));
                IssacRunLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/LEFT/IssacBody7.png")));
                IssacRunLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/LEFT/IssacBody8.png")));
                IssacRunLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/LEFT/IssacBody9.png")));
                IssacRunLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/LEFT/IssacBody10 .png")));
                //----------------------------------------------------------------------------------------//
                // 右侧移动时的身体动画帧加载
                IssacRunRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/RIGHT/IssacBody1.png")));
                IssacRunRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/RIGHT/IssacBody2.png")));
                IssacRunRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/RIGHT/IssacBody3.png")));
                IssacRunRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/RIGHT/IssacBody4.png")));
                IssacRunRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/RIGHT/IssacBody5.png")));
                IssacRunRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/RIGHT/IssacBody6.png")));
                IssacRunRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/RIGHT/IssacBody7.png")));
                IssacRunRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/RIGHT/IssacBody8.png")));
                IssacRunRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/RIGHT/IssacBody9.png")));
                IssacRunRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/RIGHT/IssacBody10.png")));
                //----------------------------------------------------------------------------------------//
                // 向上移动时的身体动画帧加载
                IssacRunUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody1.png")));
                IssacRunUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody2.png")));
                IssacRunUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody3.png")));
                IssacRunUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody4.png")));
                IssacRunUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody5.png")));
                IssacRunUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody6.png")));
                IssacRunUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody7.png")));
                IssacRunUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody8.png")));
                IssacRunUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody9.png")));
                IssacRunUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody10.png")));

                //----------------------------------------------------------------------------------------//
                // 向下移动时的身体动画帧加载（倒序排列以实现从后往前的效果）
                IssacRunDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody10.png")));
                IssacRunDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody9.png")));
                IssacRunDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody8.png")));
                IssacRunDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody7.png")));
                IssacRunDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody6.png")));
                IssacRunDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody5.png")));
                IssacRunDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody4.png")));
                IssacRunDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody3.png")));
                IssacRunDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody2.png")));
                IssacRunDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Body/UP-DOWN/IssacBody1.png")));
        }
        catch(IOException e){
            System.out.println("Load ResourceObj.Issac Body Image Failed");
        }
    }
    
    // 加载角色头部各方向的动画帧图片资源
    public void LoadHeadImage(){
        try {
            // 各个方向的头部图像加载
            IssacHeadDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Head/Issachead1.png")));
            IssacHeadDown.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Head/Issachead2.png")));
            IssacHeadRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Head/Issachead3.png")));
            IssacHeadRight.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Head/Issachead4.png")));
            IssacHeadUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Head/Issachead5.png")));
            IssacHeadUp.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Head/Issachead6.png")));
            IssacHeadLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Head/Issachead7.png")));
            IssacHeadLeft.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/Head/Issachead8.png")));
        }
        catch (IOException e){
            System.out.println("Load ResourceObj.Issac Head Image Failed");
        }
    }
    //----------------------------------------------------------------------------------------//
    // 移动校正函数：根据可移动性标志位调整速度，防止角色穿过障碍物
    public void MovingCorrection(boolean Leftmovable,boolean Rightmovable,boolean Upmovable,boolean Downmovable){
        if(!Leftmovable && nowXspeed<0)
        {
            nowXspeed=0;
        }
        if(!Rightmovable && nowXspeed>0)
        {
            nowXspeed=0;
        }
        if(!Upmovable &&  nowYspeed<0){
            nowYspeed=0;
        }
        if(!Downmovable && nowYspeed>0){
            nowYspeed=0;
        }
    }

    //----------------------------------------------------------------------------------------//
    // 射击方向判断函数：设置射击方向变量以便后续逻辑处理
    public void ShootingDirectionJudgement(){
        if(shootL){
            shootingDirection=2;
        }
        else if(shootR){
            shootingDirection=1;
        }
        else if(shootU){
            shootingDirection=3;
        }
        else  if(shootD){
            shootingDirection=4;
        }

    }
    //----------------------------------------------------------------------------------------//
    // 更新和绘制角色图像，包括身体和头部的动画帧选择与播放
    public void show(){
        // 根据移动方向选择对应的身体动画序列
        if((nowXspeed<0 && nowYspeed>0) || (nowXspeed >0 && nowYspeed>0)){
            NowBodyImage = IssacRunUp;
        }
        else if((nowXspeed >0 && nowYspeed<0) || (nowXspeed<0 &&nowYspeed<0)){
            NowBodyImage = IssacRunDown;
        }
        else if(nowXspeed>0){
            NowBodyImage = IssacRunRight;
        }
        else if(nowXspeed <0){
            NowBodyImage = IssacRunLeft;
        }
        else if(nowXspeed==0 && nowYspeed==0){
            NowBodyImage = IssacRunDown;
        }
        //----------------------------------------------------------------------------------------//
        // 根据是否正在射击以及射击方向选择对应的头部动画序列
        if(shooting) {
            if (shootL) {
                NowHeadImage = IssacHeadLeft;
            } else if (shootR) {
                NowHeadImage = IssacHeadRight;
            } else if (shootU) {
                NowHeadImage = IssacHeadUp;
            } else {
                NowHeadImage = IssacHeadDown;
            }
        }
        else{
            // 如果没有射击，则根据移动方向选择头部动画序列
            if (runL) {
                NowHeadImage = IssacHeadLeft;
            } else if (runR) {
                NowHeadImage = IssacHeadRight;
            } else if (runU) {
                NowHeadImage = IssacHeadUp;
            } else {
                NowHeadImage = IssacHeadDown;
            }
        }
        
        // 播放身体动画帧
        if(nowXspeed !=0 || nowYspeed !=0){
            nowBodyImage=NowBodyImage.get(BodyTick);
            BodyTick++;
            if(BodyTick==9){
                BodyTick=0;
            }
        }
        else{
            nowBodyImage=NowBodyImage.get(2);
            BodyTick=0;
        }
        
        // 播放头部动画帧
        if(shooting){
            nowHeadImage=NowHeadImage.get(HeadTick);
            HeadTickCount++;
            if(HeadTick==0){
                shooted=false;
            }
            if(HeadTickCount==HeadTickSpeed){
                HeadTickCount=0;
                HeadTick++;
            }
            if(HeadTick==2){
                HeadTick=0;
                LeftOrRight++;
            }
            if(LeftOrRight==3){
                LeftOrRight=1;
            }
        }
        else{
            nowHeadImage=NowHeadImage.get(0);
            HeadTick=0;
            LeftOrRight=1;
        }
    }
    
    // 获取死亡动画帧图像
    public BufferedImage getDeathImage(){
        return ResourceLoader.getInstance().IssacDeathImage.get(DeathTick);
    }

    // 处理角色死亡动画逻辑
    public void IssacDeath(){
        if(DeathTick<2){
            DeathTick++;
        }
        /*BackGround.getInstance().setGameState(3);*/
    }

    // 重置角色属性到初始状态
    public void reset() {
        hp = 6; // 初始血量
        damage = 3; // 初始伤害
        x = 200; // 初始 X 坐标
        y = 150; // 初始 Y 坐标
        nowXspeed = 0;
        nowYspeed = 0;
        HeartSwitch=true;// 触发血量刷新
        shooting = false;
        shootL = shootR = shootU = shootD = false;
        /*leftmovable = rightmovable = upmovable = downmovable = true;*/
        // 其他需要重置的属性...
    }
}
