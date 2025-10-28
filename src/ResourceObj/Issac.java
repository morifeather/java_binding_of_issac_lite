package ResourceObj;

import ResourceObj.obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Issac{
    // Isaac ��λ������
    private int oldx,oldy;
    private int x=240;      // X���ʼλ��
    private int y=175;      // Y���ʼλ��
    
    // �ƶ��ٶ����
    private int Xspeed=5;   // X������ٶ�
    private int Yspeed=5;   // Y������ٶ�
    private int nowXspeed=0; // ��ǰX���ٶ�
    private int nowYspeed=0; // ��ǰY���ٶ�
    
    // ��ɫ����
    private int damage=999;       // �����˺�
    private int shotspeed=6;    // �ӵ��ٶ�
    private int hp=4;           // ����ֵ
    private int range=25;       // ������Χ
    private int flight;         // ����������������������״̬��
    
    // �������Ʋ���
    private int acceleration1=2;  // ����ϵ��1
    private int acceleration2=1;  // ����ϵ��2
    private int HeadTick=0;        // ͷ������֡������
    private int HeadTickSpeed=7;  // ͷ�����������ٶ�
    private int HeadTickCount=0;  // ͷ������ʱ�Ӽ�����
    private int BodyTick=0;        // ���嶯��֡������
    
    // �ߴ����
    private int IssacLength=15;   // Isaac �ĳ���
    private int IssacWidth=18;    // Isaac �Ŀ��
    
    // ������״̬
    private int LeftOrRight;       // ���ҷ����ʶ
    private int shootingDirection; // �������
    private int XSlowenTick=0;     // X����ټ�ʱ��
    private int YSlowenTick=0;     // Y����ټ�ʱ��
    private int DeathTick=0;       // ��������֡������
    
    //----------------------------------------------------------------------------//
    // ״̬��־λ
    private boolean runL=false;    // �Ƿ�����������
    private boolean runR=false;    // �Ƿ�����������
    private boolean runU=false;    // �Ƿ�����������
    private boolean runD=false;    // �Ƿ�����������
    
    private boolean shootL=false;  // �Ƿ������������
    private boolean shootR=false;  // �Ƿ������������
    private boolean shootU=false;  // �Ƿ������������
    private boolean shootD=false;  // �Ƿ������������
    
    private boolean Leftmovable=true;  // ����Ƿ���ƶ�
    private boolean Rightmovable=true; // �Ҳ��Ƿ���ƶ�
    private boolean Upmovable=true;    // �Ϸ��Ƿ���ƶ�
    private boolean Downmovable=true;  // �·��Ƿ���ƶ�
    
    private boolean moving;      // �Ƿ������ƶ�
    private boolean shooting;    // �Ƿ��������
    private volatile boolean shooted; // �Ƿ��Ѿ����
    private boolean HeartSwitch=true; // ���࿪�أ���������Ѫ����ʾ�л���

    //----------------------------------------------------------------------------//
    // ����ģʽʵ��
    public static Issac instance=new Issac();
    public static Issac getInstance(){ return instance;}
    
    //----------------------------------------------------------------------------//
    // �ϰ������ͼ����Դ
    private Obstacle obstacle=new Obstacle();
    private BufferedImage nowHeadImage;   // ��ǰͷ��ͼ��
    private BufferedImage nowBodyImage;   // ��ǰ����ͼ��
    
    //----------------------------------------------------------------------------//
    // ��ȡ����
    public int getHeadTickSpeed(){return HeadTickSpeed;} // ��ȡͷ�������ٶ�
    
    //----------------------------------------------------------------------------//
    // ͼ�񼯺�
    // �ܶ������ͼ�񼯺�
    private ArrayList<BufferedImage> IssacRunLeft =new ArrayList<>();
    private ArrayList<BufferedImage> IssacRunRight =new ArrayList<>();
    private ArrayList<BufferedImage> IssacRunUp =new ArrayList<>();
    private ArrayList<BufferedImage> IssacRunDown =new ArrayList<>();
    
    // ͷ�������ͼ�񼯺�
    private ArrayList<BufferedImage> IssacHeadLeft =new ArrayList<>();
    private ArrayList<BufferedImage> IssacHeadRight =new ArrayList<>();
    private ArrayList<BufferedImage> IssacHeadUp =new ArrayList<>();
    private ArrayList<BufferedImage> IssacHeadDown =new ArrayList<>();
    
    // ��ǰʹ�õ�ͼ�񼯺�
    public ArrayList<BufferedImage> NowHeadImage = new ArrayList<>();
    public ArrayList<BufferedImage> NowBodyImage = new ArrayList<>();

    //----------------------------------------------------------------------------//
    // ���õ�ǰͷ��ͼ��
    public void setNowHeadImage(BufferedImage nowHeadImage){this.nowHeadImage=nowHeadImage;}
    public void setNowBodyImage(BufferedImage nowBodyImage){this.nowBodyImage=nowBodyImage;}
    
    //----------------------------------------------------------------------------//
    // ����setter����
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
    // ����getter����
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
        int issacWidth = 18; // Isaac �Ŀ��
        int issacHeight = 15; // Isaac �ĸ߶�
        
        // ���� Isaac ���ϰ���ľ�������������ײ���
        Rectangle issacRect = new Rectangle(x+nowXspeed, y+nowYspeed, issacWidth, issacHeight);
        Rectangle obstacleRect = new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA());
        
        // �������������Ƿ��ཻ�����Ƿ�����ײ��
        return issacRect.intersects(obstacleRect);
    }
    public void MovableJudgement(ArrayList<Obstacle> obstacles) {
        Leftmovable = true;
        Rightmovable = true;
        Upmovable = true;
        Downmovable = true;

        // Isaac �ĵ�ǰλ�þ���
        Rectangle issacRect = new Rectangle(x+5, y+18, IssacWidth, IssacLength);

        // �߽��ж�
        if(x + 6 <= 50) Leftmovable = false;
        if(x + 23 >= 419) Rightmovable = false;
        if(y + 18 <= 50) Upmovable = false;
        if(y + 33 >= 262) Downmovable = false;

        // �ϰ����жϣ�ÿ֡������
        for (Obstacle obstacle : obstacles) {
            Rectangle obstacleRect = new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA());
            /*System.out.println("Obstacle pos - x: " + ResourceObj.obstacle.getX() + ", y: " + ResourceObj.obstacle.getY());
*/
            // Ԥ����һ���Ƿ��ײǽ
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

        // ��ʹ nowXspeed == 0��Ҳ��ֹԽ���ϰ���߽�
        if (!Leftmovable && x <= 50) Leftmovable = false;
        if (!Rightmovable && x >= 419 - IssacWidth) Rightmovable = false;
        if (!Upmovable && y <= 50) Upmovable = false;
        if (!Downmovable && y >= 262 - IssacLength) Downmovable = false;

        // ֻ�ڶ�Ӧ���򲻿��ƶ�ʱ�����ٶ�Ϊ 0 ����΢�����ٶ�
        if (!Leftmovable && nowXspeed < 0) nowXspeed = 0; // ��಻���ƶ�����ֹͣ����
        if (!Rightmovable && nowXspeed > 0) nowXspeed = 0; // �Ҳ಻���ƶ�����ֹͣ����
        if (!Upmovable && nowYspeed < 0) nowYspeed = 0; // �Ϸ������ƶ�����ֹͣ����
        if (!Downmovable && nowYspeed > 0) nowYspeed = 0; // �·������ƶ�����ֹͣ����
    }


    //----------------------------------------------------------------------------//
    // ��ȡ����ϵ��1�����ڿ��ƽ�ɫ�ƶ�����Ч��
    public int getAcceleration1(){return acceleration1;}
    // ��ȡ����ϵ��2�����ڿ��ƽ�ɫ�ƶ�����Ч��
    public int getAcceleration2(){return acceleration2;}
    // ��ȡ��ǰX���ٶȣ���ʾ��ɫˮƽ������ƶ��ٶ�
    public int getNowXSpeed(){return nowXspeed;}
    // ��ȡ��ǰY���ٶȣ���ʾ��ɫ��ֱ������ƶ��ٶ�
    public int getNowYSpeed(){return nowYspeed;}
    // ��ȡͷ������֡�����������ڿ������ʱͷ����������
    public int getHeadTick(){return HeadTick;}
    // ��ȡ������Χ������ȷ���ӵ�������������Ӱ�췶Χ
    public int getRange(){return range;}
    // ��ȡ��ɫ�������������Ե�������˺�����ֵ
    public int getDamage() {
        return damage;
    }
    // ��ȡX������ٶȣ������ɫ��ˮƽ���������ƶ��ٶ�
    public int getXspeed() {
        return Xspeed;
    }
    // ��ȡY������ٶȣ������ɫ�ڴ�ֱ���������ƶ��ٶ�
    public int getYspeed(){
        return Yspeed;
    }
    // ��ȡ��ǰ����ֵ�������жϽ�ɫ�Ƿ���������ʾѪ����Ϣ
    public int getHp() { return hp;
    }
    // ��ȡ�ӵ��ٶȣ������ӵ�����Ļ�ϵ��ƶ��ٶ�
    public int getShotspeed(){
        return shotspeed;
    }
    // ��ȡ��һ֡��X���꣬����λ�ñ仯�������ײ���
    public int getOldx(){return oldx;}
    // ��ȡ��һ֡��Y���꣬����λ�ñ仯�������ײ���
    public int getOldy(){return oldy;}
    // ��ȡ��ǰX���꣬���ڶ�λ��ɫ����Ϸ��ͼ�е�λ��
    public int getX() {
        return x;
    }
    // ��ȡ��ǰY���꣬���ڶ�λ��ɫ����Ϸ��ͼ�е�λ��
    public int getY() {
        return y;
    }
    // ��ȡ��������״̬�������������⼼�ܻ��޵�״̬
    public int getFlight(){
        return flight;
    }
    // ��ȡ�����������ȷ���ӵ�����ķ���
    public int getShootingDirection(){return shootingDirection;}
    // ��ȡ���ҷ����ʶ��ͨ���붯���������
    public int getLeftOrRight(){return LeftOrRight;}
    // ��ȡX����ټ�ʱ�������ڿ��Ƽ��ٹ��̵�ʱ��
    public int getXSlowenTick(){return XSlowenTick;}
    // ��ȡY����ټ�ʱ�������ڿ��Ƽ��ٹ��̵�ʱ��
    public int getYSlowenTick(){return YSlowenTick;}
    //----------------------------------------------------------------------------------------//
    // ��ȡ��ǰ����ͼ��������Ⱦ��ɫ���岿��
    public BufferedImage getNowBodyImage(){return nowBodyImage;}
    // ��ȡ��ǰͷ��ͼ��������Ⱦ��ɫͷ���Ա��ֲ�ͬ����
    public BufferedImage getNowHeadImage(){return nowHeadImage;}
    //----------------------------------------------------------------------------------------//


    // ���ؽ�ɫ���������Ķ���֡ͼƬ��Դ
    public void LoadBodyImage(){
        try{
                // ����ƶ�ʱ�����嶯��֡����
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
                // �Ҳ��ƶ�ʱ�����嶯��֡����
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
                // �����ƶ�ʱ�����嶯��֡����
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
                // �����ƶ�ʱ�����嶯��֡���أ�����������ʵ�ִӺ���ǰ��Ч����
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
    
    // ���ؽ�ɫͷ��������Ķ���֡ͼƬ��Դ
    public void LoadHeadImage(){
        try {
            // ���������ͷ��ͼ�����
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
    // �ƶ�У�����������ݿ��ƶ��Ա�־λ�����ٶȣ���ֹ��ɫ�����ϰ���
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
    // ��������жϺ��������������������Ա�����߼�����
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
    // ���ºͻ��ƽ�ɫͼ�񣬰��������ͷ���Ķ���֡ѡ���벥��
    public void show(){
        // �����ƶ�����ѡ���Ӧ�����嶯������
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
        // �����Ƿ���������Լ��������ѡ���Ӧ��ͷ����������
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
            // ���û�������������ƶ�����ѡ��ͷ����������
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
        
        // �������嶯��֡
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
        
        // ����ͷ������֡
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
    
    // ��ȡ��������֡ͼ��
    public BufferedImage getDeathImage(){
        return ResourceLoader.getInstance().IssacDeathImage.get(DeathTick);
    }

    // �����ɫ���������߼�
    public void IssacDeath(){
        if(DeathTick<2){
            DeathTick++;
        }
        /*BackGround.getInstance().setGameState(3);*/
    }

    // ���ý�ɫ���Ե���ʼ״̬
    public void reset() {
        hp = 6; // ��ʼѪ��
        damage = 3; // ��ʼ�˺�
        x = 200; // ��ʼ X ����
        y = 150; // ��ʼ Y ����
        nowXspeed = 0;
        nowYspeed = 0;
        HeartSwitch=true;// ����Ѫ��ˢ��
        shooting = false;
        shootL = shootR = shootU = shootD = false;
        /*leftmovable = rightmovable = upmovable = downmovable = true;*/
        // ������Ҫ���õ�����...
    }
}
