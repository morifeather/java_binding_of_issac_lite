package ResourceObj;

import ResourceObj.Enemies.*;
import ResourceObj.Enemies.Boss.*;
import ResourceObj.RoomObj.*;
import ResourceObj.obstacle.Obstacle;
import ResourceObj.obstacle.Poop;
import ResourceObj.obstacle.ShootStone;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * 游戏主界面类，继承自 JPanel，用于绘制游戏背景和处理游戏逻辑。
 */
public class BackGround extends JPanel {
    Random randit = new Random();
    
    // 房间编号与游戏状态
    private int RoomNum = 0;
    private int GameState = 0;
    
    // 碰撞反弹速度与房间切换标志
    private int BounceSpeed = 3;
    private boolean RoomSwitch = true;
    
    // 音效播放标志
    private boolean DeathMusicActed = false;
    public boolean BackGroundMusicActed = false;
    
    // 资源加载器
    private ResourceLoader resourceloader = ResourceLoader.getInstance();
    
    // 无敌时间计时器
    private int InvincibleTime = 40;
    
    //----------------------------------------------------------------------------//
    // 图像资源
    private BufferedImage ExitImage = null;
    private BufferedImage RestartImage = null;
    private BufferedImage Door;
    
    //----------------------------------------------------------------------------//
    // 屏幕淡入淡出效果相关字段
    private int fadeAlpha = 0; // 黑色遮罩透明度 (0-255)
    private boolean isFading = false;
    private Timer fadeInTimer, fadeOutTimer;

    //----------------------------------------------------------------------------//
    // 单例模式
    public static BackGround instance = new BackGround();
    public static BackGround getInstance() {
        return instance;
    }
    
    //----------------------------------------------------------------------------//
    // 游戏对象列表
    private ArrayList<Enemy> EnemiestoAdd = new ArrayList<>();
    private ArrayList<Room> Rooms = new ArrayList<>();
    private ArrayList<Obstacle> Obstacles = new ArrayList<>();
    private ArrayList<Door> Doors = new ArrayList<>();
    private ArrayList<Enemy> Enemies = new ArrayList<>();
    private ArrayList<IssacBullet> IssacBullets = new ArrayList<>();
    private ArrayList<EnemyBullet> EnemyBullets = new ArrayList<>();
    private ArrayList<Heart> Hearts = new ArrayList<>();
    
    //----------------------------------------------------------------------------//
    // Getter 和 Setter 方法
    public Issac getIssac() { return Issac.getInstance(); }
    public int getRoomNum() { return RoomNum; }
    public void setRoomNum(int roomNum) { this.RoomNum = roomNum; }
    public void setSwitchRoom(boolean SwitchRoom) { this.RoomSwitch = SwitchRoom; }
    public boolean getSwitchRoom() { return RoomSwitch; }
    
    //----------------------------------------------------------------------------//
    // 图像资源
    private BufferedImage TitleImage;
    private BufferedImage CryImage;
    
    //----------------------------------------------------------------------------//
    /**
     * 初始化背景图像并设置组件属性
     */
    public void BackGroundImg(){
        initFadeAnimations();
        try{
            TitleImage = ImageIO.read(Issac.class.getClassLoader().getResource("images/Scene/Title.png"));
            CryImage = ImageIO.read(Issac.class.getClassLoader().getResource("images/Scene/2270.png"));
        }
        catch (IOException e){
            System.out.println("Error: images/BackGround1.png not found!");
        }
        setFocusable(true);
        setOpaque(true);
    }
    
    /**
     * 初始化房间，创建房间链路
     */
    public void initRoom() {
        Rooms.add(new Room0(Doors,Obstacles,Enemies,randit.nextInt(1),0));
        for(int a=1;a<=8;a++){
            RoomNum++;
            Rooms.add(new RoomRandomSystem().RandomRoom(Doors,Obstacles,Enemies,Rooms.get(RoomNum-1).getPlusDoorToward(),Rooms.get(RoomNum-1).getMinusDoorToward()));
        }
        Rooms.add(new BossRoom(Doors,Obstacles,Enemies,Rooms.get(RoomNum).getPlusDoorToward(),Rooms.get(RoomNum).getMinusDoorToward()));
        RoomNum=0;
        Obstacles.clear();
        Doors.clear();
        Enemies.clear();
    }
    
    /**
     * 绘制游戏画面
     * @param g Graphics 对象
     */
    @Override
    protected void paintComponent(Graphics g){
        Color Invisible = new Color(0,0,0,0);

        super.paintComponent(g);
        g.setColor(Invisible);
        
        // 绘制背景图像
        if(!ResourceLoader.getInstance().BackGroundImage.isEmpty()) {
            g.drawImage(ResourceLoader.getInstance().BackGroundImage.get(GameState), 0, 0, 468, 312, this);
        }
        g.drawImage(TitleImage,8,0,this);
        g.drawImage(CryImage,179,190,this);
        g.drawImage(GoldenCup.getInstance().getImage(),218,122,GoldenCup.getInstance().getImageLength(),GoldenCup.getInstance().getImageWidth(),this);

        // 绘制血量图标
        for(Heart heart : Hearts){
            g.drawImage(heart.getHeartImage(),heart.getX(),heart.getY(),heart.getLength(),heart.getWidth(),null);
        }
        
        // 绘制障碍物
        for(Obstacle obstacles : Obstacles){
            g.drawImage(obstacles.getImage(1),obstacles.getX(),obstacles.getY(),23,23,null);
        }
        
        // 绘制敌人
        for(Enemy enemy : Enemies){
            if(enemy instanceof Pride){
                g.drawImage(((Pride)enemy).getBodyImage(), ((Pride)enemy).getX(), ((Pride)enemy).getY(), ((Pride)enemy).getLength(),((Pride)enemy).getWidth(), null);
                g.drawImage(((Pride)enemy).getHeadImage(),((Pride)enemy).getImageHeadx(),((Pride)enemy).getImageHeady(),((Pride)enemy).getImageHeadLength(),((Pride)enemy).getImageHeadWidth(),null);
            }
            else {
                g.drawImage(enemy.getImage(), enemy.getImagex(), enemy.getImagey(), enemy.getImagelength(), enemy.getImagewidth(), null);
            }
        }
        
        // 绘制门
        for(Door doors : Doors){
            g.drawImage(doors.getDoorImage(Rooms.get(RoomNum).getRoomState()),doors.getDoorX(),doors.getDoorY(),doors.getImagelength(),doors.getImagewidth(),null);
            if(doors.getToward()==1 || doors.getToward()==2) {
                g.drawRect(doors.getDoorX()+15, doors.getDoorY(), doors.getLength(), doors.getWidth());
            }
            else if(doors.getToward()==3 || doors.getToward()==4) {
                g.drawRect(doors.getDoorX(), doors.getDoorY()+15, doors.getLength(), doors.getWidth());
            }
        }
        
        // 绘制 Isaac 角色
        if(Issac.getInstance().getHp()>0) {
            if (Issac.getInstance().getNowBodyImage() != null && Issac.getInstance().getNowHeadImage() != null) {
                if (InvincibleTime > 0 && (InvincibleTime / 5) % 2 == 0) {
                    // 当处于无敌状态时，每 5 帧切换一次是否绘制 Issac 图像，实现闪烁效果
                    g.drawImage(Issac.getInstance().getNowBodyImage(), Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, 18, 15, null);
                    g.drawImage(Issac.getInstance().getNowHeadImage(), Issac.getInstance().getX(), Issac.getInstance().getY(), 28, 25, null);
                } else if (InvincibleTime == 0) {
                    // 正常绘制 Issac 图像
                    g.drawImage(Issac.getInstance().getNowBodyImage(), Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, 18, 15, null);
                    g.drawImage(Issac.getInstance().getNowHeadImage(), Issac.getInstance().getX(), Issac.getInstance().getY(), 28, 25, null);
                }
            } else {
                System.out.println("ResourceObj.Issac Body-- Image Not Found");
            }
        }
        else  {
            g.drawImage(Issac.getInstance().getDeathImage(), Issac.getInstance().getX(), Issac.getInstance().getY(), 28, 25, null);
        }
        
        // 绘制以撒的子弹
        for(IssacBullet issacBullet : IssacBullets){
            g.drawImage(issacBullet.getIssacBulletImage(),issacBullet.getImageX(),issacBullet.getImageY(),issacBullet.getImageLength(),issacBullet.getImageWidth(),null);
        }
        
        // 绘制敌人的子弹
        for (EnemyBullet bullet : EnemyBullets) {
            if (bullet.getEnemyBulletImage() != null) {
                g.drawImage(bullet.getEnemyBulletImage(), bullet.getBulletX(), bullet.getBulletY(), 12, 12, null);
            }
        }
        
        // 绘制淡入淡出遮罩层
        if (isFading && fadeAlpha > 0) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(0, 0, 0, fadeAlpha));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
        
        // 绘制退出和重新开始按钮
        g.drawImage(ExitImage,0,230,null);
        g.drawImage(RestartImage,360,199,null);
    }
    public void GameLoop(){
        if(GameState==0){
            SoundEffect.playSound("BackGroundMusic1","sounds/StartMusic.mp3");
        }
        Timer gameTimer=new Timer(25,e-> {
            if(GameState==0){
                if (KeyMonitor.isKeyPressed(KeyEvent.VK_SPACE) && !isFading) {
                    isFading = true;
                    fadeAlpha = 0;
                    fadeInTimer.start();
                }
            }
            else if (GameState == 1){
                ExitImage=null;
                RestartImage=null;
                SoundEffect.stopSound("BackGroundMusic1");
                if (!BackGroundMusicActed) {
                    SoundEffect.playSound("BackGroundMusic2", "sounds/BackGroundMusic.mp3");
                    BackGroundMusicActed=true;
                    TitleImage=null;
                    CryImage=null;
                }
                if(RoomSwitch){
                    Iterator<Obstacle> ObstacleIterator=Obstacles.iterator();
                    Iterator<Enemy> EnemyIterator=Enemies.iterator();
                    Iterator<EnemyBullet> EnemyBulletIterator=EnemyBullets.iterator();
                    Iterator<IssacBullet> IssacBulletIterator=IssacBullets.iterator();
                    Iterator<Door> DoorIterator=Doors.iterator();
                    while(DoorIterator.hasNext()){
                        Door door=DoorIterator.next();
                        DoorIterator.remove();
                    }
                    while(EnemyIterator.hasNext()){
                        Enemy enemy=EnemyIterator.next();
                        EnemyIterator.remove();
                    }
                    while(ObstacleIterator.hasNext()){
                        Obstacle obstacle=ObstacleIterator.next();
                        ObstacleIterator.remove();
                    }
                    while(EnemyBulletIterator.hasNext()){
                        EnemyBullet enemyBullet=EnemyBulletIterator.next();
                        EnemyBulletIterator.remove();
                    }
                    while(IssacBulletIterator.hasNext()){
                        IssacBullet issacBullet=IssacBulletIterator.next();
                        IssacBulletIterator.remove();
                    }
                    BackGround.getInstance().Rooms.get(RoomNum).Insertion(Enemies,Obstacles,Doors);
                    RoomSwitch=false;
                }
                /*------------------------------------------------------------*/
                // 记录以撒的旧位置，并重置移动方向标志
                Issac.getInstance().setOldx(Issac.getInstance().getX());
                Issac.getInstance().setOldy(Issac.getInstance().getY());
                Issac.getInstance().setRunU(false);
                Issac.getInstance().setRunD(false);
                Issac.getInstance().setRunL(false);
                Issac.getInstance().setRunR(false);
                
                if(Issac.getInstance().getHp()>0) {
                    // 获取按键状态并设置移动和射击方向
                    boolean W = KeyMonitor.isKeyPressed(KeyEvent.VK_W);
                    boolean A = KeyMonitor.isKeyPressed(KeyEvent.VK_A);
                    boolean S = KeyMonitor.isKeyPressed(KeyEvent.VK_S);
                    boolean D = KeyMonitor.isKeyPressed(KeyEvent.VK_D);
                    boolean UP = KeyMonitor.isKeyPressed(KeyEvent.VK_UP);
                    boolean DOWN = KeyMonitor.isKeyPressed(KeyEvent.VK_DOWN);
                    boolean LEFT = KeyMonitor.isKeyPressed(KeyEvent.VK_LEFT);
                    boolean RIGHT = KeyMonitor.isKeyPressed(KeyEvent.VK_RIGHT);
                    
                    Issac.getInstance().setRunU(W);
                    Issac.getInstance().setRunL(A);
                    Issac.getInstance().setRunD(S);
                    Issac.getInstance().setRunR(D);
                    Issac.getInstance().setShootR(RIGHT);
                    Issac.getInstance().setShootU(UP);
                    Issac.getInstance().setShootD(DOWN);
                    Issac.getInstance().setShootL(LEFT);
                    
                    //------------------------------------------------------------------------------------//
                    // 判断射击方向
                    Issac.getInstance().ShootingDirectionJudgement();
                    
                    // 如果头部动画帧为1且未射击，则创建新子弹
                    if (Issac.getInstance().getHeadTick() == 1) {
                        IssacBullet issacbullets = new IssacBullet();
                        issacbullets.setResourceloader(resourceloader);
                        issacbullets.setIssacBulletImage();
                        
                        //------------------------------------------------------------------------------------//
                        // 根据角色左右朝向和射击方向初始化子弹位置
                        if (Issac.getInstance().getLeftOrRight() == 1 && Issac.getInstance().getShootingDirection() == 1) {
                            issacbullets.BulletInitialize(Issac.getInstance().getX() + 20, Issac.getInstance().getY() + 7, Issac.getInstance().getNowXSpeed(), Issac.getInstance().getNowYSpeed(), Issac.getInstance().getShotspeed(), Issac.getInstance().getShootingDirection());
                        } else if (Issac.getInstance().getLeftOrRight() == 2 && Issac.getInstance().getShootingDirection() == 1) {
                            issacbullets.BulletInitialize(Issac.getInstance().getX() + 20, Issac.getInstance().getY() + 10, Issac.getInstance().getNowXSpeed(), Issac.getInstance().getNowYSpeed(), Issac.getInstance().getShotspeed(), Issac.getInstance().getShootingDirection());
                        } else if (Issac.getInstance().getLeftOrRight() == 1 && Issac.getInstance().getShootingDirection() == 2) {
                            issacbullets.BulletInitialize(Issac.getInstance().getX() + 8, Issac.getInstance().getY() + 10, Issac.getInstance().getNowXSpeed(), Issac.getInstance().getNowYSpeed(), Issac.getInstance().getShotspeed(), Issac.getInstance().getShootingDirection());
                        } else if (Issac.getInstance().getLeftOrRight() == 2 && Issac.getInstance().getShootingDirection() == 2) {
                            issacbullets.BulletInitialize(Issac.getInstance().getX() + 8, Issac.getInstance().getY() + 7, Issac.getInstance().getNowXSpeed(), Issac.getInstance().getNowYSpeed(), Issac.getInstance().getShotspeed(), Issac.getInstance().getShootingDirection());
                        } else if (Issac.getInstance().getLeftOrRight() == 1 && Issac.getInstance().getShootingDirection() == 3) {
                            issacbullets.BulletInitialize(Issac.getInstance().getX() + 3, Issac.getInstance().getY() + 11, Issac.getInstance().getNowXSpeed(), Issac.getInstance().getNowYSpeed(), Issac.getInstance().getShotspeed(), Issac.getInstance().getShootingDirection());
                        } else if (Issac.getInstance().getLeftOrRight() == 2 && Issac.getInstance().getShootingDirection() == 3) {
                            issacbullets.BulletInitialize(Issac.getInstance().getX() + 15, Issac.getInstance().getY() + 11, Issac.getInstance().getNowXSpeed(), Issac.getInstance().getNowYSpeed(), Issac.getInstance().getShotspeed(), Issac.getInstance().getShootingDirection());
                        } else if (Issac.getInstance().getLeftOrRight() == 1 && Issac.getInstance().getShootingDirection() == 4) {
                            issacbullets.BulletInitialize(Issac.getInstance().getX() + 15, Issac.getInstance().getY() + 11, Issac.getInstance().getNowXSpeed(), Issac.getInstance().getNowYSpeed(), Issac.getInstance().getShotspeed(), Issac.getInstance().getShootingDirection());
                        } else if (Issac.getInstance().getLeftOrRight() == 2 && Issac.getInstance().getShootingDirection() == 4) {
                            issacbullets.BulletInitialize(Issac.getInstance().getX() + 3, Issac.getInstance().getY() + 11, Issac.getInstance().getNowXSpeed(), Issac.getInstance().getNowYSpeed(), Issac.getInstance().getShotspeed(), Issac.getInstance().getShootingDirection());
                        }

                        //------------------------------------------------------------------------------------//
                        // 如果没有射击，则标记为已射击并将子弹加入列表
                        if (!Issac.getInstance().getShooted()) {
                            Issac.getInstance().setShooted(true);
                            IssacBullets.add(issacbullets);
                        }
                    }
                    
                    //-------------------------------------射击石头射击-----------------------------------------------//
                    // 遍历障碍物，如果是射击石头并且房间状态为0则执行射击逻辑
                    for (Obstacle obstacle : Obstacles) {
                        if (obstacle instanceof ShootStone) {
                            if (Rooms.get(RoomNum).getRoomState() == 0)
                                ((ShootStone) obstacle).ShootStoneShoot(EnemyBullets);
                        }
                    }
                    
                    //-----------------------------------敌人子弹效果实现-------------------------------------------------//
                    // 遍历敌方子弹，更新其状态并与以撒碰撞检测
                    Iterator<EnemyBullet> EnemyBulletIterator = EnemyBullets.iterator();
                    while (EnemyBulletIterator.hasNext()) {
                        EnemyBullet enemybullet = EnemyBulletIterator.next();
                        enemybullet.UpdateEnemyBullet();
                        Rectangle enemybulletRect = new Rectangle(enemybullet.getBulletX(), enemybullet.getBulletY(), enemybullet.getWidth(), enemybullet.getLength());
                        
                        // 检测子弹是否与以撒相交（受伤）
                        if (enemybulletRect.intersects(Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, 18, 15)) {
                            if (InvincibleTime == 0) {
                                // 碰撞反弹效果
                                if (enemybullet.getBulletX() > Issac.getInstance().getX()) {
                                    Issac.getInstance().setNowXspeed(-BounceSpeed);
                                } else if (enemybullet.getBulletX() < Issac.getInstance().getX()) {
                                    Issac.getInstance().setNowXspeed(BounceSpeed);
                                }
                                if (enemybullet.getBulletY() > Issac.getInstance().getY()) {
                                    Issac.getInstance().setNowYspeed(-BounceSpeed);
                                } else if (enemybullet.getBulletY() < Issac.getInstance().getY()) {
                                    Issac.getInstance().setNowYspeed(BounceSpeed);
                                }
                                
                                // 减少生命值并播放音效
                                Issac.getInstance().setHp(Issac.getInstance().getHp() - 1);
                                SoundEffect.playSound("IssacHurt", "sounds/Hurt.mp3");
                                Issac.getInstance().setHeartSwitch(true);
                                InvincibleTime = 40; // 设置无敌时间
                            }
                            enemybullet.setBulletExist(false); // 子弹消失
                        }
                        
                        // 子弹与非射击石头障碍物碰撞检测
                        for (Obstacle obstacle : Obstacles) {
                            if (enemybulletRect.intersects(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA()) && !(obstacle instanceof ShootStone)) {
                                enemybullet.setBulletExist(false);
                            }
                        }
                        
                        // 移除无效子弹
                        if (!enemybullet.getBulletExist()) {
                            EnemyBulletIterator.remove();
                        }
                    }
                // 敌人行为处理和碰撞检测
                for (Enemy enemy : Enemies) {
                    // 更新受伤状态
                    enemy.HurtJudgement();
                    
                    // 处理Fly类特有的声音播放逻辑
                    if (enemy instanceof Fly) {
                        ((Fly) enemy).setFlysoundtick(((Fly) enemy).getFlysoundtick() + 1);
                        if (((Fly) enemy).getFlysoundtick() == 30) {
                            SoundEffect.playSound("FlySound","sounds/FlySound.mp3");
                            ((Fly) enemy).setFlysoundtick(0);
                        }
                    }
                    
                    // 创建敌人碰撞矩形
                    Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getLength());
                    
                    // 根据敌人类型执行特定行为
                    if (enemy instanceof Turkey) {
                        // Turkey行为：移动、动画更新和射击
                        if (Rooms.get(RoomNum).getRoomState() == 0) {
                            ((Turkey) enemy).UpdateLocation(Issac.getInstance().getX(), Issac.getInstance().getY() - 8, Obstacles);
                            enemy.UpdateImage();
                            ((Turkey) enemy).TurkeyShoot(EnemyBullets);
                        }
                    } 
                    else if (enemy instanceof Pride && 
                            !(enemy instanceof SubSubPride) && 
                            !(enemy instanceof SubPride1) &&
                            !(enemy instanceof SubPride2) &&  
                            !(enemy instanceof SubSubSubPride)) {
                        // 基础Pride行为：射击和移动
                        ((Pride) enemy).PrideShoot(EnemyBullets);
                        ((Pride)enemy).UpdateLocation(Issac.getInstance().getX(), Issac.getInstance().getY() - 8, Obstacles);
                    }
                    else if (enemy instanceof SubPride1) {
                        // SubPride1行为：特殊射击和移动
                        ((SubPride1) enemy).SubPrideShoot(EnemyBullets);
                        ((SubPride1) enemy).UpdateLocation(Issac.getInstance().getX(), Issac.getInstance().getY() - 8, Obstacles);
                    }
                    else if (enemy instanceof SubPride2){
                        // SubPride2行为：特殊射击和移动
                        ((SubPride2) enemy).SubPrideShoot(EnemyBullets);
                        ((SubPride2) enemy).UpdateLocation(Issac.getInstance().getX(), Issac.getInstance().getY() - 8, Obstacles);
                    }
                    else if (enemy instanceof Enemy) {
                        // 其他敌人通用行为：位置更新和动画
                        enemy.UpdateLocation(Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, Obstacles);
                        enemy.UpdateImage();
                    }
                    
                    // 敌人与以撒的碰撞检测
                    if (enemyRect.intersects(Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, 18, 15)) {
                        if (InvincibleTime == 0) {
                            // 碰撞反弹效果
                            if (enemy.getX() > Issac.getInstance().getX()) {
                                Issac.getInstance().setNowXspeed(-BounceSpeed);
                            } else if (enemy.getX() < Issac.getInstance().getX()) {
                                Issac.getInstance().setNowXspeed(BounceSpeed);
                            }
                            if (enemy.getY() > Issac.getInstance().getY()) {
                                Issac.getInstance().setNowYspeed(-BounceSpeed);
                            } else if (enemy.getY() < Issac.getInstance().getY()) {
                                Issac.getInstance().setNowYspeed(BounceSpeed);
                            }
                            
                            // 造成伤害
                            Issac.getInstance().setHp(Issac.getInstance().getHp() - 1);
                            Issac.getInstance().setHeartSwitch(true);
                            InvincibleTime = 40; // 设置无敌时间
                        }
                    }
                }

                // 以撒子弹处理和碰撞检测
                Iterator<IssacBullet> BulletIterator = IssacBullets.iterator();
                while (BulletIterator.hasNext()) {
                    IssacBullet issacBullet = BulletIterator.next();
                    // 子弹区域定义
                    Rectangle issacBulletRect = new Rectangle(issacBullet.getBulletX(), issacBullet.getBulletY() + 11, issacBullet.getWidth(), issacBullet.getLength());
                    
                    // 子弹死亡动画
                    if (issacBullet.getDeath()) {
                        issacBullet.IssacBulletDeath();
                    }
                    
                    // 子弹与障碍物碰撞检测
                    Iterator<Obstacle> ObstacleIterator = Obstacles.iterator();
                    while (ObstacleIterator.hasNext()) {
                        Obstacle obstacle = ObstacleIterator.next();
                        
                        // 移除无效障碍物
                        if (!obstacle.getObstacleExist()) {
                            ObstacleIterator.remove();
                            continue;
                        }
                        
                        // 子弹与可破坏障碍物碰撞
                        if (issacBulletRect.intersects(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA()) && obstacle.getBreakable()) {
                            issacBullet.setDeath(true);
                            if (obstacle instanceof Poop) {
                                ((Poop) obstacle).onBulletHit();
                            } else {
                                obstacle.setHp(obstacle.getHp() - 1);
                            }
                            if (obstacle.getHp() <= 0) {
                                ObstacleIterator.remove();
                            }
                        } 
                        // 子弹与不可破坏障碍物碰撞
                        else if (issacBulletRect.intersects(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA())) {
                            issacBullet.setDeath(true);
                        }
                    }

                    // 子弹与敌人碰撞检测
                    Iterator<Enemy> EnemyIterator = Enemies.iterator();
                    while (EnemyIterator.hasNext()) {
                        Enemy enemy = EnemyIterator.next();
                        
                        // 子弹击中敌人
                        if (issacBulletRect.intersects(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getLength())) {
                            issacBullet.setDeath(true);
                            enemy.setHp(enemy.getHp() - Issac.getInstance().getDamage());
                            
                            // 敌人死亡处理
                            if (enemy.getHp() <= 0) {
                                if (enemy instanceof RedFly) {
                                    ((RedFly) enemy).DeathBullet(EnemyBullets); // 特殊死亡效果
                                }
                                enemy.setEnemyExist(false);
                                
                                // 根据敌人类型生成分裂敌人或播放音效
                                if (enemy instanceof SubSubPride) {
                                    synchronized (Enemies) {
                                        EnemiestoAdd.add(new SubSubSubPride(enemy.getX(), enemy.getY(), 3, 1));
                                        EnemiestoAdd.add(new SubSubSubPride(enemy.getX(), enemy.getY(), 3, 2));
                                        SoundEffect.playSound("EnemyDeath","sounds/EnemyDeath.mp3");
                                    }
                                } else if (enemy instanceof SubPride1 || enemy instanceof SubPride2) {
                                    synchronized (Enemies) {
                                        EnemiestoAdd.add(new SubSubPride(enemy.getX(), enemy.getY(), 2, 1));
                                        EnemiestoAdd.add(new SubSubPride(enemy.getX(), enemy.getY(), 2, 2));
                                        SoundEffect.playSound("EnemyDeath","sounds/EnemyDeath.mp3");
                                    }
                                } else if (enemy instanceof Pride && !(enemy instanceof SubSubSubPride)) {
                                    synchronized (Enemies) {
                                        EnemiestoAdd.add(new SubPride1(enemy.getX(), enemy.getY(), 1, 1));
                                        EnemiestoAdd.add(new SubPride2(enemy.getX(), enemy.getY(), 1, 2));
                                        SoundEffect.playSound("EnemyDeath","sounds/EnemyDeath.mp3");
                                    }
                                } else if (enemy instanceof Tentacle || enemy instanceof Fly || enemy instanceof RedFly) {
                                    SoundEffect.playSound("MeatDeath","sounds/MeatDeath.mp3");
                                } else {
                                    SoundEffect.playSound("EnemyDeath","sounds/EnemyDeath.mp3");
                                }
                            }
                        }
                        
                        // 移除已死亡的敌人
                        if (!enemy.getEnemyExist()) {
                            EnemyIterator.remove();
                        }
                    }
                    
                    // 添加新生成的敌人
                    Enemies.addAll(EnemiestoAdd);
                    EnemiestoAdd = new ArrayList<>();
                    
                    // 子弹生命周期更新
                    issacBullet.UpdateBullet(Issac.getInstance().getRange());
                    if (!issacBullet.getBulletExist()) {
                        SoundEffect.playSound("BulletDeath","sounds/BulletDrop.mp3");
                        BulletIterator.remove();
                    }
                }

                /*-----------------------------------以撒控制实现---------------------------------------------*/
                // 初始化目标移动方向
                int targetX = 0;
                int targetY = 0;

                // 根据按键状态设置目标方向
                if (Issac.getInstance().getRunL()) targetX = -1;
                if (Issac.getInstance().getRunR()) targetX = 1;
                if (Issac.getInstance().getRunU()) targetY = -1;
                if (Issac.getInstance().getRunD()) targetY = 1;

                // 更新 Isaac 的移动状态
                Issac.getInstance().setMoving(targetX != 0 || targetY != 0);

                // X 轴减速逻辑：当没有水平输入时，根据当前速度和可移动性逐渐减速
                if (targetX == 0) {
                    if (Issac.getInstance().getNowXSpeed() > 0 && Issac.getInstance().getRightmovable()) {
                        Issac.getInstance().setXSlowenTick(Issac.getInstance().getXSlowenTick() + 1);
                        if (Issac.getInstance().getXSlowenTick() > 1) {
                            Issac.getInstance().setNowXspeed(Issac.getInstance().getNowXSpeed() - Issac.getInstance().getAcceleration2());
                            Issac.getInstance().setXSlowenTick(0);
                        }
                    } else if (Issac.getInstance().getNowXSpeed() < 0 && Issac.getInstance().getLeftmovable()) {
                        Issac.getInstance().setXSlowenTick(Issac.getInstance().getXSlowenTick() + 1);
                        if (Issac.getInstance().getXSlowenTick() > 1) {
                            Issac.getInstance().setNowXspeed(Issac.getInstance().getNowXSpeed() + Issac.getInstance().getAcceleration2());
                            Issac.getInstance().setXSlowenTick(0);
                        }
                    }
                } 
                // X 轴加速逻辑：根据输入方向和可移动性增加速度
                else {
                    int newXSpeed = Issac.getInstance().getNowXSpeed();
                    if (targetX > 0 && Issac.getInstance().getRightmovable()) {
                        newXSpeed += Issac.getInstance().getAcceleration1();
                    } else if (targetX < 0 && Issac.getInstance().getLeftmovable()) {
                        newXSpeed -= Issac.getInstance().getAcceleration1();
                    }

                    // 如果新速度未超过最大速度，则更新速度并进行位置修正
                    if (Math.abs(newXSpeed) <= Issac.getInstance().getXspeed()) {
                        Issac.getInstance().setNowXspeed(newXSpeed);
                        Issac.getInstance().MovingCorrection(Issac.getInstance().getLeftmovable(), Issac.getInstance().getRightmovable(), Issac.getInstance().getUpmovable(), Issac.getInstance().getDownmovable());
                    }
                }

                // Y 轴减速逻辑：当没有垂直输入时，根据当前速度和可移动性逐渐减速
                if (targetY == 0) {
                    if (Issac.getInstance().getNowYSpeed() > 0) {
                        Issac.getInstance().setYSlowenTick(Issac.getInstance().getYSlowenTick() + 1);
                        if (Issac.getInstance().getYSlowenTick() > 1) {
                            Issac.getInstance().setNowYspeed(Issac.getInstance().getNowYSpeed() - Issac.getInstance().getAcceleration2());
                            Issac.getInstance().setYSlowenTick(0);
                        }
                    } else if (Issac.getInstance().getNowYSpeed() < 0) {
                        Issac.getInstance().setYSlowenTick(Issac.getInstance().getYSlowenTick() + 1);
                        if (Issac.getInstance().getYSlowenTick() > 1) {
                            Issac.getInstance().setNowYspeed(Issac.getInstance().getNowYSpeed() + Issac.getInstance().getAcceleration2());
                            Issac.getInstance().setYSlowenTick(0);
                        }
                    }
                    // 减速时也进行位置修正
                    Issac.getInstance().MovingCorrection(Issac.getInstance().getLeftmovable(), Issac.getInstance().getRightmovable(), Issac.getInstance().getUpmovable(), Issac.getInstance().getDownmovable());
                } 
                // Y 轴加速逻辑：根据输入方向和可移动性增加速度
                else {
                    int newYSpeed = Issac.getInstance().getNowYSpeed();
                    if (targetY > 0 && Issac.getInstance().getDownmovable()) {
                        newYSpeed += Issac.getInstance().getAcceleration1();
                    } else if (targetY < 0 && Issac.getInstance().getUpmovable()) {
                        newYSpeed -= Issac.getInstance().getAcceleration1();
                    }

                    // 进行位置修正
                    Issac.getInstance().MovingCorrection(Issac.getInstance().getLeftmovable(), Issac.getInstance().getRightmovable(), Issac.getInstance().getUpmovable(), Issac.getInstance().getDownmovable());

                    // 如果新速度未超过最大速度，则更新速度并再次进行位置修正
                    if (Math.abs(newYSpeed) <= Issac.getInstance().getYspeed()) {
                        Issac.getInstance().setNowYspeed(newYSpeed);
                        Issac.getInstance().MovingCorrection(Issac.getInstance().getLeftmovable(), Issac.getInstance().getRightmovable(), Issac.getInstance().getUpmovable(), Issac.getInstance().getDownmovable());
                    }
                }

                // 最终检查可移动性
                Issac.getInstance().MovableJudgement(Obstacles);

                if (Issac.getInstance().getShootD() || Issac.getInstance().getShootL() || Issac.getInstance().getShootR() || Issac.getInstance().getShootU()) {
                    Issac.getInstance().setShooting(true);
                    repaint();
                } else {
                    Issac.getInstance().setShooting(false);
                }
                Issac.getInstance().setX(Issac.getInstance().getX() + Issac.getInstance().getNowXSpeed());
                Issac.getInstance().setY(Issac.getInstance().getY() + Issac.getInstance().getNowYSpeed());
                Issac.getInstance().show();
                /*-----------------------------------以撒控制实现---------------------------------------------*/
                Rooms.get(RoomNum).StateJudge(Enemies);
                for (Door door : Doors) {
                    door.DoorIntersactJudge(Rooms.get(RoomNum).getRoomState());
                }
                if (InvincibleTime > 0) {
                    InvincibleTime--;
                }
                /*-----------------------------------血量可视化---------------------------------------------*/
                if (Issac.getInstance().getHeartSwitch()) {
                    Iterator<Heart> HeartIterator = Hearts.iterator();
                    while (HeartIterator.hasNext()) {
                        Heart heart = HeartIterator.next();
                        HeartIterator.remove();
                    }
                    int i, num = Issac.getInstance().getHp() / 2;
                    int remainder = Issac.getInstance().getHp() % 2;
                    for (i = 1; i <= num; i++) {
                        Hearts.add(new Heart(i * 25, 20, 1));
                    }
                    if (remainder == 1) {
                        Hearts.add(new Heart(i * 25, 20, 0));
                    }
                    Issac.getInstance().setHeartSwitch(false);
                }
                Rectangle GoldCupRect=new Rectangle(GoldenCup.getInstance().getImageX(),GoldenCup.getInstance().getImageY(),GoldenCup.getInstance().getLength(),GoldenCup.getInstance().getWidth());
                if(GoldCupRect.intersects(Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, 18, 15)){
                    GameState=2;
                }
            }
            else{
                SoundEffect.stopSound("BackGroundMusic2");
                if(!DeathMusicActed) {
                    setGameState(3);
                    SoundEffect.playSound("DeathMusic", "sounds/DeathMusic.mp3");
                    DeathMusicActed=true;
                }
                Issac.getInstance().IssacDeath();
            }
                /*-----------------------------------血量可视化---------------------------------------------*/


        }
            else if(GameState==2){
                try{
                    ExitImage=ImageIO.read(Issac.class.getClassLoader().getResource("images/Exit.png"));
                    RestartImage=ImageIO.read(Issac.class.getClassLoader().getResource("images/Restart.png"));
                }
                catch (IOException f){
                    f.printStackTrace();
                }
                if(KeyMonitor.isKeyPressed(KeyEvent.VK_ESCAPE))System.exit(0);
                if(KeyMonitor.isKeyPressed(KeyEvent.VK_SPACE))restartGame();
            }
            else if(GameState==3){
                try{
                    ExitImage=ImageIO.read(Issac.class.getClassLoader().getResource("images/Exit.png"));
                    RestartImage=ImageIO.read(Issac.class.getClassLoader().getResource("images/Restart.png"));
                }
                catch (IOException f){
                    f.printStackTrace();
                }
                if(KeyMonitor.isKeyPressed(KeyEvent.VK_ESCAPE))System.exit(0);
                if(KeyMonitor.isKeyPressed(KeyEvent.VK_SPACE))restartGame();
            }
            repaint();
        });
        gameTimer.start();
    }
    public void setGameState(int GameState){
        this.GameState=GameState;
    }



    public void initFadeAnimations() {
        // 初始化淡入动画计时器
        fadeInTimer = new Timer(10, e -> {
            if (fadeAlpha < 255) {
                fadeAlpha += 5; // 增加透明度，实现淡入效果
                repaint(); // 重绘界面以显示变化
            } else {
                ((Timer) e.getSource()).stop(); // 停止淡入动画
                startFadeOut(); // 淡入完成后自动开始淡出
                if(GameState == 0){
                    GameState = 1; // 更新游戏状态为运行中
                }
                else if(GameState == 2 || GameState == 3){
                    restartGame(); // 如果是结束或胜利状态，则重新开始游戏
                }
            }
        });
        fadeInTimer.setRepeats(true); // 设置为重复执行

        // 初始化淡出动画计时器
        fadeOutTimer = new Timer(10, e -> {
            if (fadeAlpha > 0) {
                fadeAlpha -= 5; // 减少透明度，实现淡出效果
                repaint(); // 重绘界面以显示变化
            } else {
                ((Timer) e.getSource()).stop(); // 停止淡出动画
                isFading = false; // 标记淡出完成
            }
        });
        fadeOutTimer.setRepeats(true); // 设置为重复执行
    }

    /**
     * 启动淡出动画
     * 用于在屏幕切换或场景转换时渐隐当前画面
     */
    public void startFadeOut() {
        // 如果淡出动画已经在运行，则直接返回
        if (fadeOutTimer != null && fadeOutTimer.isRunning()) {
            return;
        }

        // 创建新的淡出动画计时器
        fadeOutTimer = new Timer(10, e -> {
            if (fadeAlpha > 0) {
                fadeAlpha -= 5; // 减少透明度
                repaint(); // 重绘界面
            } else {
                ((Timer) e.getSource()).stop(); // 停止动画
                isFading = false; // 标记淡出完成
            }
        });
        fadeOutTimer.setRepeats(true); // 设置为重复执行
        fadeOutTimer.start(); // 启动淡出动画
    }

    public void restartGame() {
        // 停止当前播放的音乐
        SoundEffect.stopSound("BackGroundMusic2");
        SoundEffect.stopSound("DeathMusic");

        // 重置游戏状态
        setGameState(1);
        RoomNum=0;
        DeathMusicActed = false;
        BackGroundMusicActed = false;
        GoldenCup.getInstance().setLength(0);
        GoldenCup.getInstance().setWidth(0);
        GoldenCup.getInstance().setImage(null);

        // 重置无敌时间
        Rooms.clear();
        InvincibleTime = 40;
        initRoom();
        RoomSwitch=true;

        // 清空所有游戏对象
        Enemies.clear();
        Obstacles.clear();
        Doors.clear();
        IssacBullets.clear();
        EnemyBullets.clear();

        // 重新初始化房间


        // 重置 Isaac 状态
        Issac.getInstance().reset();

        // 刷新血量显示
        Issac.getInstance().setHeartSwitch(true);

        // 重新启动背景音乐
        SoundEffect.playSound("BackGroundMusic1", "sounds/StartMusic.mp3");

        // 重置死亡动画帧
        Issac.getInstance().setDeathTick(0);

        // 重绘界面
        repaint();
    }

}
