package ResourceObj.Enemies;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.ArrayList;

import ResourceObj.obstacle.Obstacle;

public class Enemy {
    // ���˻�������
    protected int hp;                   // ����ֵ
    protected int x, y;                 // ʵ������
    protected int Imagelength, Imagewidth; // ͼ��ߴ�
    protected int Imagex, Imagey;       // ͼ�����꣨���ڶ������ɣ�
    protected int length, width;        // ʵ���С
    protected int Xspeed = 1, Yspeed = 1; // �ƶ��ٶ�
    protected int ImageTick;            // ������ʱ��
    protected boolean flight;           // �Ƿ���У������ϰ���
    protected boolean EnemyExist = true; // �Ƿ���
    protected boolean LeftMovable, RightMovable, UpMovable, DownMovable; // �������Ƿ���ƶ�
    protected BufferedImage nowImage;   // ��ǰͼ��
    protected int NowXSpeed, NowYSpeed; // ��ǰʵ���ƶ��ٶ�
    protected boolean Hurt=false;       // �Ƿ�������˸
    protected int HurtTick;             // ������˸��ʱ

    /**
     * ����״̬�����߼�
     * ÿ֡���ã�����������˸Ч��
     */
    public void HurtJudgement(){
         if(Hurt){
             HurtTick++;
             if(HurtTick==1) {
                 HurtTick=0;
                 Hurt = false;
             }
         }
    }

    /**
     * ��������״̬
     */
    public void setHurt(){
         Hurt = true;
    }

    /**
     * ��ȡ����״̬
     * @return �Ƿ�����������˸
     */
    public boolean getHurt() {
         return Hurt;
    }

    /**
     * ��ȡ��ǰ��ʾͼ��
     * @return ��ǰBufferedImage����
     */
    public BufferedImage getImage() {
        return nowImage;
    }

    /**
     * ��ʼ������λ��
     * @param x ��ʼx����
     * @param y ��ʼy����
     */
    public void Initialize(int x, int y) {
        this.x = x;
        this.y = y;
        this.Imagex = x;
        this.Imagey = y;
    }
    /*---------------------------------------------------------------------------------------------------*/

    /**
     * ��������ֵ
     * @param Hp �µ�����ֵ
     */
    public void setHp(int Hp) {
        this.hp = Hp;
    }

    /**
     * ����ͼ��x����
     * @param x �µ�x����
     */
    public void setX(int x) {
        this.Imagex = x;
    }

    /**
     * ����ͼ��y����
     * @param y �µ�y����
     */
    public void setY(int y) {
        this.Imagey = y;
    }

    /**
     * ���õ��˵Ĵ��״̬
     * @param EnemyExist �Ƿ���
     */
    public void setEnemyExist(boolean EnemyExist) {
        this.EnemyExist = EnemyExist;
    }
    /*--------------------------------------------------------------------------------------------------*/

    // Getters and Setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getNowXSpeed() { return NowXSpeed; }
    public int getNowYSpeed() { return NowYSpeed; }
    public int getImagex() { return Imagex; }
    public int getImagey() { return Imagey; }
    public int getImagelength() { return Imagelength; }
    public int getLength() { return length; }
    public int getImagewidth() { return Imagewidth; }
    public int getWidth() { return width; }
    public int getHp() { return hp; }
    public boolean getEnemyExist() { return EnemyExist; }
    public boolean getFlight() { return flight; }

    /**
     * ����ͼ������ʵ�֣�
     * ����Ӧ��д�˷�����ʵ�ֶ����߼�
     */
    public void UpdateImage() {
        // Ĭ��Ϊ��ʵ��
    }

    /**
     * �жϵ��˵��ƶ��Ƿ��赲
     * @param obstacles �ϰ����б�
     */
    public void MovableJudgement(ArrayList<Obstacle> obstacles) {
        // ��ʼ��Ϊ���з�����ƶ�
        LeftMovable = true;
        RightMovable = true;
        UpMovable = true;
        DownMovable = true;

        if (!flight) { // ������Ƿ��е�λ�ż���ϰ�
            for (Obstacle obstacle : obstacles) {
                // �����ϰ����������
                Rectangle obstacleRect = new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getA(), obstacle.getA());

                // ��������ײ
                if (NowXSpeed < 0 && obstacleRect.intersects(x + NowXSpeed, y, width, length)) {
                    LeftMovable = false;
                }
                // ����Ҳ���ײ
                if (NowXSpeed > 0 && obstacleRect.intersects(x + NowXSpeed, y, width, length)) {
                    RightMovable = false;
                }
                // ����ϲ���ײ
                if (NowYSpeed < 0 && obstacleRect.intersects(x, y + NowYSpeed, width, length)) {
                    UpMovable = false;
                }
                // ����²���ײ
                if (NowYSpeed > 0 && obstacleRect.intersects(x, y + NowYSpeed, width, length)) {
                    DownMovable = false;
                }
            }

            // Ӧ���ƶ�����
            applyMovement();
        }
    }

    /**
     * ���ݿ��ƶ��Ա�־�����ƶ�
     * �����ƶ��ٶ��Է�ֹ�����ϰ�
     */
    private void applyMovement() {
        if (!LeftMovable && NowXSpeed < 0) NowXSpeed = 0;
        if (!RightMovable && NowXSpeed > 0) NowXSpeed = 0;
        if (!UpMovable && NowYSpeed < 0) NowYSpeed = 0;
        if (!DownMovable && NowYSpeed > 0) NowYSpeed = 0;
    }

    /**
     * ���˻����ƶ��߼�
     * @param targetX Ŀ��x����
     * @param targetY Ŀ��y����
     * @param obstacles �ϰ����б�
     */
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // �� AI ׷���߼�ʾ��
        if (targetX < Imagex) {
            NowXSpeed = -Xspeed;
        } else if (targetX > Imagex) {
            NowXSpeed = Xspeed;
        } else {
            NowXSpeed = 0;
        }

        if (targetY < Imagey) {
            NowYSpeed = -Yspeed;
        } else if (targetY > Imagey) {
            NowYSpeed = Yspeed;
        } else {
            NowYSpeed = 0;
        }

        // �ж��ϰ��ﲢӦ���ƶ�����
        MovableJudgement(obstacles);
        Imagex += NowXSpeed;
        Imagey += NowYSpeed;

        // ͬ��ʵ������
        x = Imagex;
        y = Imagey + 11;
    }
}




