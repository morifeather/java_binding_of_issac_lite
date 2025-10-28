package ResourceObj.Enemies;

import ResourceObj.EnemyBullet;
import ResourceObj.Issac;
import ResourceObj.ResourceLoader;
import ResourceObj.SoundEffect;
import ResourceObj.obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Turkey extends Enemy {
    private int AnimationSlow; // ����֡�����������ƶ��������ٶ�
    private int MovingSlow;   // �ƶ��ٶȿ����������ڼ����ƶ�Ƶ��
    private int ShootRate = 60, ShootTick = 0; // �����ȴʱ��͵�ǰ��ȴ��ʱ
    private int ImageTick = 1; // ��ǰͼ��֡�����������л�����֡
    private int MovingDirection; // �ƶ�����1-��2-��
    boolean Shooted = false; // �Ƿ��Ѿ�����ı�־

    public Turkey() {
        super();
        this.hp = 21; // ���ó�ʼѪ��Ϊ21
        this.Imagelength = 44; // ͼ��߶�
        this.Imagewidth = 42;  // ͼ����
        this.length = 29; // ʵ����ײ��߶�
        this.width = 31;  // ʵ����ײ����
        this.Xspeed = 1; // ˮƽ�ƶ��ٶ�
        this.Yspeed = 1; // ��ֱ�ƶ��ٶ�
        this.flight = false; // �Ƿ����״̬��false��ʾ���ܷ��У�
    }

    /*---------------------------------------------------------------------------------------------*/
    @Override
    public int getX() {
        return x; // ����ʵ��x����
    }

    @Override
    public int getY() {
        return y; // ����ʵ��y����
    }

    @Override
    public BufferedImage getImage() {
        // �����ƶ�����ѡ���Ӧ��ͼ����Դ
        if (MovingDirection == 1) {
            if(ImageTick==0){
                ImageTick = 1; // ���ͼ��֡Ϊ0����Ϊ1��ֹԽ��
            }
            return ResourceLoader.getInstance().TurkeyLeftImage.get(ImageTick - 1); // ��ȡ�����ͼ��֡
        } else if (MovingDirection == 2) {
            if(ImageTick==0){
                ImageTick = 1; // ���ͼ��֡Ϊ0����Ϊ1��ֹԽ��
            }
            return ResourceLoader.getInstance().TurkeyRightImage.get(ImageTick - 1); // ��ȡ���ҵ�ͼ��֡
        } else {
            if(ImageTick==0){
                ImageTick = 1; // ���ͼ��֡Ϊ0����Ϊ1��ֹԽ��
            }
            return ResourceLoader.getInstance().TurkeyRightImage.get(ImageTick - 1); // Ĭ�Ϸ������ҵ�ͼ��֡
        }
    }

    @Override
    public int getImagex() {
        return Imagex; // ����ͼ���x����λ��
    }

    @Override
    public int getImagey() {
        return Imagey; // ����ͼ���y����λ��
    }

    @Override
    public int getImagelength() {
        return Imagelength; // ����ͼ��ĸ߶�
    }

    @Override
    public int getImagewidth() {
        return Imagewidth; // ����ͼ��Ŀ��
    }

    @Override
    public int getLength() {
        return length; // ������ײ��ĸ߶�
    }

    @Override
    public int getWidth() {
        return width; // ������ײ��Ŀ��
    }

    /*---------------------------------------------------------------------------------------------*/
    @Override
    public void Initialize(int x, int y) {
        this.Imagex = x; // ��ʼ��ͼ���x����
        this.Imagey = y; // ��ʼ��ͼ���y����
        this.x = Imagex; // ͬ������ʵ��x����
        this.y = Imagey + 11; // ͬ������ʵ��y���꣬������ƫ��ֵ
    }

    // Turkey ���������
    public void TurkeyShoot(ArrayList<EnemyBullet> enemyBullets) {
        // ��������ȴʱ��ﵽ�趨ֵ����δ���
        if (ShootTick == ShootRate && !Shooted) {
            // ����4���ӵ�������ֱ�Ϊ�ϡ��¡�����
            for (int i = 1; i <= 4; i++) {
                EnemyBullet enemyBullet = new EnemyBullet(); // �����µ��ӵ�����
                enemyBullets.add(enemyBullet); // ��ӵ��ӵ��б���
                enemyBullet.EnemyBullet(x + 10, y + 20, i); // �����ӵ���ʼλ�úͷ���
            }
            Shooted = true; // ���Ϊ�����
            ShootTick = 0; // ���������ʱ��
        } else if (ShootTick < ShootRate) {
            Shooted = false; // ���δ�ﵽ�����ȴʱ�䣬�򱣳�δ���״̬
            ShootTick++; // ���������ʱ��
        }
    }

    @Override
    public void UpdateImage() {
        AnimationSlow++; // ���Ӷ�����ʱ��
        if (AnimationSlow == 1) {
            ImageTick++; // ����ͼ��֡����
            if(ImageTick==3){ // ���ض�֡������Ч
                SoundEffect.playSound("MeatJump","sounds/FleshJump.mp3");
            }
            AnimationSlow = 0; // ���ö�����ʱ��
        }
        if (ImageTick > 11) { // ѭ�����Ŷ���֡
            ImageTick = 1;
        }
    }

    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        MovableJudgement(obstacles); // �ж��ϰ��ﲢ���ÿ��ƶ�״̬

        if (MovingSlow == 2) {
            // ����Ŀ���������λ�ã�ͬʱ�����ϰ����赲���
            if (this.Imagex < targetX && RightMovable) {
                this.Imagex += Xspeed;
                NowXSpeed = Xspeed;
                MovingDirection = 2;
            } else if (this.Imagex > targetX && LeftMovable) {
                this.Imagex -= Xspeed;
                NowXSpeed = -Xspeed;
                MovingDirection = 1;
            }

            if (this.Imagey < targetY && DownMovable) {
                this.Imagey += Yspeed;
                NowYSpeed = Yspeed;
            } else if (this.Imagey > targetY && UpMovable) {
                this.Imagey -= Yspeed;
                NowYSpeed = -Yspeed;
            }

            MovingSlow = 0; // �����ƶ�������
        }

        MovingSlow++; // �����ƶ���������ʱ
        this.x = Imagex; // ����ʵ��x����
        this.y = Imagey + 11; // ����ʵ��y���겢����ƫ��ֵ
    }
}
