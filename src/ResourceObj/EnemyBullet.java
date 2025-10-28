package ResourceObj;

import ResourceObj.Enemies.Enemy;

import java.awt.image.BufferedImage;

public class EnemyBullet {
    // �ӵ�������
    private int Bulletx, Bullety;
    // �ӵ����ڵ�ʱ�䣬��ʼΪ0
    private int BulletExistTime = 0;
    // �ӵ��Ŀ�ȡ����Ⱥ��ٶ�
    int width = 12, length = 12, Xv, Yv;
    // �ӵ���ͼ�񣬴���Դ�������л�ȡ
    private BufferedImage EnemyBulletImage = ResourceLoader.getInstance().EnemyBulletImage.get(0);
    // �ӵ��Ƿ���е��⣨�Ƿ������к���
    boolean Hostile = true;
    // �ӵ�����Ч��Χ
    private int Range = 50;
    // �ӵ��Ƿ���ڵı�־
    private boolean BulletExist = true;
    //------------------------------------------------------------------------------------//
    // �����ӵ��Ƿ����
    public void setBulletExist(boolean bulletExist) {
        BulletExist = bulletExist;
    }
    //------------------------------------------------------------------------------------//
    // ��ȡ�ӵ���X���ٶ�
    public int getXv() {
        return Xv;
    }

    // ��ȡ�ӵ���Y���ٶ�
    public int getYv() {
        return Yv;
    }

    // ��ȡ�ӵ��Ŀ��
    public int getWidth() {
        return width;
    }

    // ��ȡ�ӵ��ĸ߶ȣ����ȣ�
    public int getLength() {
        return length;
    }

    // ��ȡ�ӵ���X������
    public int getBulletX() {
        return Bulletx;
    }

    // ��ȡ�ӵ���Y������
    public int getBulletY() {
        return Bullety;
    }

    // ��ȡ�ӵ���ͼ��
    public BufferedImage getEnemyBulletImage() {
        return EnemyBulletImage;
    }

    // ��ȡ�ӵ��Ƿ���ڵ�״̬
    public boolean getBulletExist() {
        return BulletExist;
    }

    //------------------------------------------------------------------------------------//
    // ��ʼ���ӵ���λ�úͷ���
    public void EnemyBullet(int x, int y, int toward) {
        Bulletx = x;
        Bullety = y;

        // ���ݴ���ķ��������ӵ����ƶ�������ٶ�
        if (toward == 2 /* Down */) {
            Yv = 5;
            Xv = 0;
        } else if (toward == 1 /* Up */) {
            Yv = -5;
            Xv = 0;
        } else if (toward == 4 /* Right */) {
            Xv = 5;
            Yv = 0;
        } else if (toward == 3 /* Left */) {
            Xv = -5;
            Yv = 0;
        } else if (toward == 5 /* RightDown */) {
            Xv = 3;
            Yv = 3;
        } else if (toward == 6 /* LeftDown */) {
            Xv = -3;
            Yv = 3;
        } else if (toward == 7 /* RightUp */) {
            Xv = 3;
            Yv = -3;
        } else if (toward == 8 /* LeftUp */) {
            Xv = -3;
            Yv = -3;
        } else {
            // ������������Ч�������������Ϣ
            System.out.println("Error:EnemyBullet Direct Worngly");
        }
    }

    // �����ӵ���״̬
    public void UpdateEnemyBullet() {
        // ����ӵ�������ֱ�߽磬�������ӵ�
        if (Bulletx < 50 || Bulletx + 12 > 419) {
            BulletExist = false;
        }

        // ����ӵ�����ˮƽ�߽磬�������ӵ�
        if (Bullety < 50 || Bullety + 12 > 262) {
            BulletExist = false;
        }

        // �����ӵ����ƶ�������ʱ��
        if (BulletExistTime <= Range - 7) {
            Bulletx += Xv;
            Bullety += Yv;
            BulletExistTime++;
        } else if (BulletExistTime > Range - 7 && BulletExistTime < Range) {
            Bulletx += Xv;
            Bullety += Yv + 1;
            BulletExistTime++;
        } else if (BulletExistTime >= Range) {
            BulletExist = false;
        }
    }
}
