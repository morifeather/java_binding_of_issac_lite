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
 * ��Ϸ�������࣬�̳��� JPanel�����ڻ�����Ϸ�����ʹ�����Ϸ�߼���
 */
public class BackGround extends JPanel {
    Random randit = new Random();
    
    // ����������Ϸ״̬
    private int RoomNum = 0;
    private int GameState = 0;
    
    // ��ײ�����ٶ��뷿���л���־
    private int BounceSpeed = 3;
    private boolean RoomSwitch = true;
    
    // ��Ч���ű�־
    private boolean DeathMusicActed = false;
    public boolean BackGroundMusicActed = false;
    
    // ��Դ������
    private ResourceLoader resourceloader = ResourceLoader.getInstance();
    
    // �޵�ʱ���ʱ��
    private int InvincibleTime = 40;
    
    //----------------------------------------------------------------------------//
    // ͼ����Դ
    private BufferedImage ExitImage = null;
    private BufferedImage RestartImage = null;
    private BufferedImage Door;
    
    //----------------------------------------------------------------------------//
    // ��Ļ���뵭��Ч������ֶ�
    private int fadeAlpha = 0; // ��ɫ����͸���� (0-255)
    private boolean isFading = false;
    private Timer fadeInTimer, fadeOutTimer;

    //----------------------------------------------------------------------------//
    // ����ģʽ
    public static BackGround instance = new BackGround();
    public static BackGround getInstance() {
        return instance;
    }
    
    //----------------------------------------------------------------------------//
    // ��Ϸ�����б�
    private ArrayList<Enemy> EnemiestoAdd = new ArrayList<>();
    private ArrayList<Room> Rooms = new ArrayList<>();
    private ArrayList<Obstacle> Obstacles = new ArrayList<>();
    private ArrayList<Door> Doors = new ArrayList<>();
    private ArrayList<Enemy> Enemies = new ArrayList<>();
    private ArrayList<IssacBullet> IssacBullets = new ArrayList<>();
    private ArrayList<EnemyBullet> EnemyBullets = new ArrayList<>();
    private ArrayList<Heart> Hearts = new ArrayList<>();
    
    //----------------------------------------------------------------------------//
    // Getter �� Setter ����
    public Issac getIssac() { return Issac.getInstance(); }
    public int getRoomNum() { return RoomNum; }
    public void setRoomNum(int roomNum) { this.RoomNum = roomNum; }
    public void setSwitchRoom(boolean SwitchRoom) { this.RoomSwitch = SwitchRoom; }
    public boolean getSwitchRoom() { return RoomSwitch; }
    
    //----------------------------------------------------------------------------//
    // ͼ����Դ
    private BufferedImage TitleImage;
    private BufferedImage CryImage;
    
    //----------------------------------------------------------------------------//
    /**
     * ��ʼ������ͼ�������������
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
     * ��ʼ�����䣬����������·
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
     * ������Ϸ����
     * @param g Graphics ����
     */
    @Override
    protected void paintComponent(Graphics g){
        Color Invisible = new Color(0,0,0,0);

        super.paintComponent(g);
        g.setColor(Invisible);
        
        // ���Ʊ���ͼ��
        if(!ResourceLoader.getInstance().BackGroundImage.isEmpty()) {
            g.drawImage(ResourceLoader.getInstance().BackGroundImage.get(GameState), 0, 0, 468, 312, this);
        }
        g.drawImage(TitleImage,8,0,this);
        g.drawImage(CryImage,179,190,this);
        g.drawImage(GoldenCup.getInstance().getImage(),218,122,GoldenCup.getInstance().getImageLength(),GoldenCup.getInstance().getImageWidth(),this);

        // ����Ѫ��ͼ��
        for(Heart heart : Hearts){
            g.drawImage(heart.getHeartImage(),heart.getX(),heart.getY(),heart.getLength(),heart.getWidth(),null);
        }
        
        // �����ϰ���
        for(Obstacle obstacles : Obstacles){
            g.drawImage(obstacles.getImage(1),obstacles.getX(),obstacles.getY(),23,23,null);
        }
        
        // ���Ƶ���
        for(Enemy enemy : Enemies){
            if(enemy instanceof Pride){
                g.drawImage(((Pride)enemy).getBodyImage(), ((Pride)enemy).getX(), ((Pride)enemy).getY(), ((Pride)enemy).getLength(),((Pride)enemy).getWidth(), null);
                g.drawImage(((Pride)enemy).getHeadImage(),((Pride)enemy).getImageHeadx(),((Pride)enemy).getImageHeady(),((Pride)enemy).getImageHeadLength(),((Pride)enemy).getImageHeadWidth(),null);
            }
            else {
                g.drawImage(enemy.getImage(), enemy.getImagex(), enemy.getImagey(), enemy.getImagelength(), enemy.getImagewidth(), null);
            }
        }
        
        // ������
        for(Door doors : Doors){
            g.drawImage(doors.getDoorImage(Rooms.get(RoomNum).getRoomState()),doors.getDoorX(),doors.getDoorY(),doors.getImagelength(),doors.getImagewidth(),null);
            if(doors.getToward()==1 || doors.getToward()==2) {
                g.drawRect(doors.getDoorX()+15, doors.getDoorY(), doors.getLength(), doors.getWidth());
            }
            else if(doors.getToward()==3 || doors.getToward()==4) {
                g.drawRect(doors.getDoorX(), doors.getDoorY()+15, doors.getLength(), doors.getWidth());
            }
        }
        
        // ���� Isaac ��ɫ
        if(Issac.getInstance().getHp()>0) {
            if (Issac.getInstance().getNowBodyImage() != null && Issac.getInstance().getNowHeadImage() != null) {
                if (InvincibleTime > 0 && (InvincibleTime / 5) % 2 == 0) {
                    // �������޵�״̬ʱ��ÿ 5 ֡�л�һ���Ƿ���� Issac ͼ��ʵ����˸Ч��
                    g.drawImage(Issac.getInstance().getNowBodyImage(), Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, 18, 15, null);
                    g.drawImage(Issac.getInstance().getNowHeadImage(), Issac.getInstance().getX(), Issac.getInstance().getY(), 28, 25, null);
                } else if (InvincibleTime == 0) {
                    // �������� Issac ͼ��
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
        
        // �����������ӵ�
        for(IssacBullet issacBullet : IssacBullets){
            g.drawImage(issacBullet.getIssacBulletImage(),issacBullet.getImageX(),issacBullet.getImageY(),issacBullet.getImageLength(),issacBullet.getImageWidth(),null);
        }
        
        // ���Ƶ��˵��ӵ�
        for (EnemyBullet bullet : EnemyBullets) {
            if (bullet.getEnemyBulletImage() != null) {
                g.drawImage(bullet.getEnemyBulletImage(), bullet.getBulletX(), bullet.getBulletY(), 12, 12, null);
            }
        }
        
        // ���Ƶ��뵭�����ֲ�
        if (isFading && fadeAlpha > 0) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(0, 0, 0, fadeAlpha));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
        
        // �����˳������¿�ʼ��ť
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
                // ��¼�����ľ�λ�ã��������ƶ������־
                Issac.getInstance().setOldx(Issac.getInstance().getX());
                Issac.getInstance().setOldy(Issac.getInstance().getY());
                Issac.getInstance().setRunU(false);
                Issac.getInstance().setRunD(false);
                Issac.getInstance().setRunL(false);
                Issac.getInstance().setRunR(false);
                
                if(Issac.getInstance().getHp()>0) {
                    // ��ȡ����״̬�������ƶ����������
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
                    // �ж��������
                    Issac.getInstance().ShootingDirectionJudgement();
                    
                    // ���ͷ������֡Ϊ1��δ������򴴽����ӵ�
                    if (Issac.getInstance().getHeadTick() == 1) {
                        IssacBullet issacbullets = new IssacBullet();
                        issacbullets.setResourceloader(resourceloader);
                        issacbullets.setIssacBulletImage();
                        
                        //------------------------------------------------------------------------------------//
                        // ���ݽ�ɫ���ҳ������������ʼ���ӵ�λ��
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
                        // ���û�����������Ϊ����������ӵ������б�
                        if (!Issac.getInstance().getShooted()) {
                            Issac.getInstance().setShooted(true);
                            IssacBullets.add(issacbullets);
                        }
                    }
                    
                    //-------------------------------------���ʯͷ���-----------------------------------------------//
                    // �����ϰ����������ʯͷ���ҷ���״̬Ϊ0��ִ������߼�
                    for (Obstacle obstacle : Obstacles) {
                        if (obstacle instanceof ShootStone) {
                            if (Rooms.get(RoomNum).getRoomState() == 0)
                                ((ShootStone) obstacle).ShootStoneShoot(EnemyBullets);
                        }
                    }
                    
                    //-----------------------------------�����ӵ�Ч��ʵ��-------------------------------------------------//
                    // �����з��ӵ���������״̬����������ײ���
                    Iterator<EnemyBullet> EnemyBulletIterator = EnemyBullets.iterator();
                    while (EnemyBulletIterator.hasNext()) {
                        EnemyBullet enemybullet = EnemyBulletIterator.next();
                        enemybullet.UpdateEnemyBullet();
                        Rectangle enemybulletRect = new Rectangle(enemybullet.getBulletX(), enemybullet.getBulletY(), enemybullet.getWidth(), enemybullet.getLength());
                        
                        // ����ӵ��Ƿ��������ཻ�����ˣ�
                        if (enemybulletRect.intersects(Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, 18, 15)) {
                            if (InvincibleTime == 0) {
                                // ��ײ����Ч��
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
                                
                                // ��������ֵ��������Ч
                                Issac.getInstance().setHp(Issac.getInstance().getHp() - 1);
                                SoundEffect.playSound("IssacHurt", "sounds/Hurt.mp3");
                                Issac.getInstance().setHeartSwitch(true);
                                InvincibleTime = 40; // �����޵�ʱ��
                            }
                            enemybullet.setBulletExist(false); // �ӵ���ʧ
                        }
                        
                        // �ӵ�������ʯͷ�ϰ�����ײ���
                        for (Obstacle obstacle : Obstacles) {
                            if (enemybulletRect.intersects(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA()) && !(obstacle instanceof ShootStone)) {
                                enemybullet.setBulletExist(false);
                            }
                        }
                        
                        // �Ƴ���Ч�ӵ�
                        if (!enemybullet.getBulletExist()) {
                            EnemyBulletIterator.remove();
                        }
                    }
                // ������Ϊ�������ײ���
                for (Enemy enemy : Enemies) {
                    // ��������״̬
                    enemy.HurtJudgement();
                    
                    // ����Fly�����е����������߼�
                    if (enemy instanceof Fly) {
                        ((Fly) enemy).setFlysoundtick(((Fly) enemy).getFlysoundtick() + 1);
                        if (((Fly) enemy).getFlysoundtick() == 30) {
                            SoundEffect.playSound("FlySound","sounds/FlySound.mp3");
                            ((Fly) enemy).setFlysoundtick(0);
                        }
                    }
                    
                    // ����������ײ����
                    Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getLength());
                    
                    // ���ݵ�������ִ���ض���Ϊ
                    if (enemy instanceof Turkey) {
                        // Turkey��Ϊ���ƶ����������º����
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
                        // ����Pride��Ϊ��������ƶ�
                        ((Pride) enemy).PrideShoot(EnemyBullets);
                        ((Pride)enemy).UpdateLocation(Issac.getInstance().getX(), Issac.getInstance().getY() - 8, Obstacles);
                    }
                    else if (enemy instanceof SubPride1) {
                        // SubPride1��Ϊ������������ƶ�
                        ((SubPride1) enemy).SubPrideShoot(EnemyBullets);
                        ((SubPride1) enemy).UpdateLocation(Issac.getInstance().getX(), Issac.getInstance().getY() - 8, Obstacles);
                    }
                    else if (enemy instanceof SubPride2){
                        // SubPride2��Ϊ������������ƶ�
                        ((SubPride2) enemy).SubPrideShoot(EnemyBullets);
                        ((SubPride2) enemy).UpdateLocation(Issac.getInstance().getX(), Issac.getInstance().getY() - 8, Obstacles);
                    }
                    else if (enemy instanceof Enemy) {
                        // ��������ͨ����Ϊ��λ�ø��ºͶ���
                        enemy.UpdateLocation(Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, Obstacles);
                        enemy.UpdateImage();
                    }
                    
                    // ��������������ײ���
                    if (enemyRect.intersects(Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, 18, 15)) {
                        if (InvincibleTime == 0) {
                            // ��ײ����Ч��
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
                            
                            // ����˺�
                            Issac.getInstance().setHp(Issac.getInstance().getHp() - 1);
                            Issac.getInstance().setHeartSwitch(true);
                            InvincibleTime = 40; // �����޵�ʱ��
                        }
                    }
                }

                // �����ӵ��������ײ���
                Iterator<IssacBullet> BulletIterator = IssacBullets.iterator();
                while (BulletIterator.hasNext()) {
                    IssacBullet issacBullet = BulletIterator.next();
                    // �ӵ�������
                    Rectangle issacBulletRect = new Rectangle(issacBullet.getBulletX(), issacBullet.getBulletY() + 11, issacBullet.getWidth(), issacBullet.getLength());
                    
                    // �ӵ���������
                    if (issacBullet.getDeath()) {
                        issacBullet.IssacBulletDeath();
                    }
                    
                    // �ӵ����ϰ�����ײ���
                    Iterator<Obstacle> ObstacleIterator = Obstacles.iterator();
                    while (ObstacleIterator.hasNext()) {
                        Obstacle obstacle = ObstacleIterator.next();
                        
                        // �Ƴ���Ч�ϰ���
                        if (!obstacle.getObstacleExist()) {
                            ObstacleIterator.remove();
                            continue;
                        }
                        
                        // �ӵ�����ƻ��ϰ�����ײ
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
                        // �ӵ��벻���ƻ��ϰ�����ײ
                        else if (issacBulletRect.intersects(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA())) {
                            issacBullet.setDeath(true);
                        }
                    }

                    // �ӵ��������ײ���
                    Iterator<Enemy> EnemyIterator = Enemies.iterator();
                    while (EnemyIterator.hasNext()) {
                        Enemy enemy = EnemyIterator.next();
                        
                        // �ӵ����е���
                        if (issacBulletRect.intersects(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getLength())) {
                            issacBullet.setDeath(true);
                            enemy.setHp(enemy.getHp() - Issac.getInstance().getDamage());
                            
                            // ������������
                            if (enemy.getHp() <= 0) {
                                if (enemy instanceof RedFly) {
                                    ((RedFly) enemy).DeathBullet(EnemyBullets); // ��������Ч��
                                }
                                enemy.setEnemyExist(false);
                                
                                // ���ݵ����������ɷ��ѵ��˻򲥷���Ч
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
                        
                        // �Ƴ��������ĵ���
                        if (!enemy.getEnemyExist()) {
                            EnemyIterator.remove();
                        }
                    }
                    
                    // ��������ɵĵ���
                    Enemies.addAll(EnemiestoAdd);
                    EnemiestoAdd = new ArrayList<>();
                    
                    // �ӵ��������ڸ���
                    issacBullet.UpdateBullet(Issac.getInstance().getRange());
                    if (!issacBullet.getBulletExist()) {
                        SoundEffect.playSound("BulletDeath","sounds/BulletDrop.mp3");
                        BulletIterator.remove();
                    }
                }

                /*-----------------------------------��������ʵ��---------------------------------------------*/
                // ��ʼ��Ŀ���ƶ�����
                int targetX = 0;
                int targetY = 0;

                // ���ݰ���״̬����Ŀ�귽��
                if (Issac.getInstance().getRunL()) targetX = -1;
                if (Issac.getInstance().getRunR()) targetX = 1;
                if (Issac.getInstance().getRunU()) targetY = -1;
                if (Issac.getInstance().getRunD()) targetY = 1;

                // ���� Isaac ���ƶ�״̬
                Issac.getInstance().setMoving(targetX != 0 || targetY != 0);

                // X ������߼�����û��ˮƽ����ʱ�����ݵ�ǰ�ٶȺͿ��ƶ����𽥼���
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
                // X ������߼����������뷽��Ϳ��ƶ��������ٶ�
                else {
                    int newXSpeed = Issac.getInstance().getNowXSpeed();
                    if (targetX > 0 && Issac.getInstance().getRightmovable()) {
                        newXSpeed += Issac.getInstance().getAcceleration1();
                    } else if (targetX < 0 && Issac.getInstance().getLeftmovable()) {
                        newXSpeed -= Issac.getInstance().getAcceleration1();
                    }

                    // ������ٶ�δ��������ٶȣ�������ٶȲ�����λ������
                    if (Math.abs(newXSpeed) <= Issac.getInstance().getXspeed()) {
                        Issac.getInstance().setNowXspeed(newXSpeed);
                        Issac.getInstance().MovingCorrection(Issac.getInstance().getLeftmovable(), Issac.getInstance().getRightmovable(), Issac.getInstance().getUpmovable(), Issac.getInstance().getDownmovable());
                    }
                }

                // Y ������߼�����û�д�ֱ����ʱ�����ݵ�ǰ�ٶȺͿ��ƶ����𽥼���
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
                    // ����ʱҲ����λ������
                    Issac.getInstance().MovingCorrection(Issac.getInstance().getLeftmovable(), Issac.getInstance().getRightmovable(), Issac.getInstance().getUpmovable(), Issac.getInstance().getDownmovable());
                } 
                // Y ������߼����������뷽��Ϳ��ƶ��������ٶ�
                else {
                    int newYSpeed = Issac.getInstance().getNowYSpeed();
                    if (targetY > 0 && Issac.getInstance().getDownmovable()) {
                        newYSpeed += Issac.getInstance().getAcceleration1();
                    } else if (targetY < 0 && Issac.getInstance().getUpmovable()) {
                        newYSpeed -= Issac.getInstance().getAcceleration1();
                    }

                    // ����λ������
                    Issac.getInstance().MovingCorrection(Issac.getInstance().getLeftmovable(), Issac.getInstance().getRightmovable(), Issac.getInstance().getUpmovable(), Issac.getInstance().getDownmovable());

                    // ������ٶ�δ��������ٶȣ�������ٶȲ��ٴν���λ������
                    if (Math.abs(newYSpeed) <= Issac.getInstance().getYspeed()) {
                        Issac.getInstance().setNowYspeed(newYSpeed);
                        Issac.getInstance().MovingCorrection(Issac.getInstance().getLeftmovable(), Issac.getInstance().getRightmovable(), Issac.getInstance().getUpmovable(), Issac.getInstance().getDownmovable());
                    }
                }

                // ���ռ����ƶ���
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
                /*-----------------------------------��������ʵ��---------------------------------------------*/
                Rooms.get(RoomNum).StateJudge(Enemies);
                for (Door door : Doors) {
                    door.DoorIntersactJudge(Rooms.get(RoomNum).getRoomState());
                }
                if (InvincibleTime > 0) {
                    InvincibleTime--;
                }
                /*-----------------------------------Ѫ�����ӻ�---------------------------------------------*/
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
                /*-----------------------------------Ѫ�����ӻ�---------------------------------------------*/


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
        // ��ʼ�����붯����ʱ��
        fadeInTimer = new Timer(10, e -> {
            if (fadeAlpha < 255) {
                fadeAlpha += 5; // ����͸���ȣ�ʵ�ֵ���Ч��
                repaint(); // �ػ��������ʾ�仯
            } else {
                ((Timer) e.getSource()).stop(); // ֹͣ���붯��
                startFadeOut(); // ������ɺ��Զ���ʼ����
                if(GameState == 0){
                    GameState = 1; // ������Ϸ״̬Ϊ������
                }
                else if(GameState == 2 || GameState == 3){
                    restartGame(); // ����ǽ�����ʤ��״̬�������¿�ʼ��Ϸ
                }
            }
        });
        fadeInTimer.setRepeats(true); // ����Ϊ�ظ�ִ��

        // ��ʼ������������ʱ��
        fadeOutTimer = new Timer(10, e -> {
            if (fadeAlpha > 0) {
                fadeAlpha -= 5; // ����͸���ȣ�ʵ�ֵ���Ч��
                repaint(); // �ػ��������ʾ�仯
            } else {
                ((Timer) e.getSource()).stop(); // ֹͣ��������
                isFading = false; // ��ǵ������
            }
        });
        fadeOutTimer.setRepeats(true); // ����Ϊ�ظ�ִ��
    }

    /**
     * ������������
     * ��������Ļ�л��򳡾�ת��ʱ������ǰ����
     */
    public void startFadeOut() {
        // ������������Ѿ������У���ֱ�ӷ���
        if (fadeOutTimer != null && fadeOutTimer.isRunning()) {
            return;
        }

        // �����µĵ���������ʱ��
        fadeOutTimer = new Timer(10, e -> {
            if (fadeAlpha > 0) {
                fadeAlpha -= 5; // ����͸����
                repaint(); // �ػ����
            } else {
                ((Timer) e.getSource()).stop(); // ֹͣ����
                isFading = false; // ��ǵ������
            }
        });
        fadeOutTimer.setRepeats(true); // ����Ϊ�ظ�ִ��
        fadeOutTimer.start(); // ������������
    }

    public void restartGame() {
        // ֹͣ��ǰ���ŵ�����
        SoundEffect.stopSound("BackGroundMusic2");
        SoundEffect.stopSound("DeathMusic");

        // ������Ϸ״̬
        setGameState(1);
        RoomNum=0;
        DeathMusicActed = false;
        BackGroundMusicActed = false;
        GoldenCup.getInstance().setLength(0);
        GoldenCup.getInstance().setWidth(0);
        GoldenCup.getInstance().setImage(null);

        // �����޵�ʱ��
        Rooms.clear();
        InvincibleTime = 40;
        initRoom();
        RoomSwitch=true;

        // ���������Ϸ����
        Enemies.clear();
        Obstacles.clear();
        Doors.clear();
        IssacBullets.clear();
        EnemyBullets.clear();

        // ���³�ʼ������


        // ���� Isaac ״̬
        Issac.getInstance().reset();

        // ˢ��Ѫ����ʾ
        Issac.getInstance().setHeartSwitch(true);

        // ����������������
        SoundEffect.playSound("BackGroundMusic1", "sounds/StartMusic.mp3");

        // ������������֡
        Issac.getInstance().setDeathTick(0);

        // �ػ����
        repaint();
    }

}
