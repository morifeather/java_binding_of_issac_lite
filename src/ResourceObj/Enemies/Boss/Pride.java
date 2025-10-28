package ResourceObj.Enemies.Boss;

import ResourceObj.Enemies.Enemy;
import ResourceObj.EnemyBullet;
import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Pride ���ʾ���� Boss������Ϸ�е�һ�����˽�ɫ��
 * ���̳��� Enemy �࣬��ʵ�����ض����ƶ��͹�����Ϊ��
 */
public class Pride extends Enemy {
    // ͷ��ͼ���λ�úͳߴ�
    protected int ImageHeadx, ImageHeady, ImageHeadlength, ImageHeadwidth;
    // ʵ��λ�úͳߴ�
    protected int x, y;
    protected int length, width;
    // ��ǰ�ٶ�
    protected int NowXSpeed, NowYSpeed;
    // �����ʱ��������
    protected int ShootTick = 0, ShootRate = 50;
    protected boolean Shooted = false;

    /**
     * ���캯�����ڴ��� Pride ʵ����
     *
     * @param x      ��ʼ x ����
     * @param y      ��ʼ y ����
     * @param i      �Ѷȵȼ� (0-3)
     * @param toward �ƶ����� (1=��, 2=��)
     */
    public Pride(int x, int y, int i, int toward) {
        if (i == 0) {
            this.x = x;
            this.y = y;
            this.hp = 201;
            this.ImageHeadx = x - 6;
            this.ImageHeady = y - 19;
            this.length = 16;
            this.width = 15;
            this.ImageHeadlength = 28;
            this.ImageHeadwidth = 25;
            if (toward == 1) {
                this.NowXSpeed = 1;
            } else if (toward == 2) {
                this.NowXSpeed = -1;
            }
            this.NowYSpeed = 1;
        } else if (i == 1) {
            this.x = x;
            this.y = y;
            this.ImageHeadx = x;
            this.ImageHeady = y;
            this.hp = 102;
            this.ImageHeadlength = 28;
            this.ImageHeadwidth = 25;
            this.length = 28;
            this.width = 25;
            if (toward == 1) {
                this.NowXSpeed = 2;
            } else if (toward == 2) {
                this.NowXSpeed = -2;
            }
            this.NowYSpeed = 2;
        } else if (i == 2) {
            this.x = x;
            this.y = y;
            this.ImageHeadx = x;
            this.ImageHeady = y;
            this.hp = 51;
            this.ImageHeadlength = 20;
            this.ImageHeadwidth = 19;
            this.length = 20;
            this.width = 19;
            if (toward == 1) {
                this.NowXSpeed = 3;
            } else if (toward == 2) {
                this.NowXSpeed = -3;
            }
            this.NowYSpeed = 3;
        } else if (i == 3) {
            this.x = x;
            this.y = y;
            this.ImageHeadx = x;
            this.ImageHeady = y;
            this.hp = 27;
            this.ImageHeadlength = 13;
            this.ImageHeadwidth = 13;
            this.length = 13;
            this.width = 13;
            if (toward == 1) {
                this.NowXSpeed = 5;
            } else if (toward == 2) {
                this.NowXSpeed = -5;
            }
            this.NowYSpeed = 5;
        }
    }

    /**
     * ��ȡͷ��ͼ��
     *
     * @return ���ص�ǰ״̬�µ�ͷ��ͼ��
     */
    public BufferedImage getHeadImage() {
        if (NowXSpeed < 0) {
            return ResourceLoader.getInstance().PrideHeadImage.get(1);
        } else {
            return ResourceLoader.getInstance().PrideHeadImage.get(0);
        }
    }

    /**
     * ��ȡ����ͼ��
     *
     * @return ���ص�ǰ״̬�µ�����ͼ��
     */
    public BufferedImage getBodyImage() {
        if (NowXSpeed < 0) {
            return ResourceLoader.getInstance().PrideBodyImage.get(1);
        } else {
            return ResourceLoader.getInstance().PrideBodyImage.get(0);
        }
    }

    /*--------------------------------------------------------------------------------------------
     * Getter ����
     *--------------------------------------------------------------------------------------------*/

    public int getImageHeadx() {
        return ImageHeadx;
    }

    public int getImageHeady() {
        return ImageHeady;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    public int getImageHeadLength() {
        return ImageHeadlength;
    }

    public int getImageHeadWidth() {
        return ImageHeadwidth;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public int getWidth() {
        return width;
    }

    /*--------------------------------------------------------------------------------------------
     * ��������ʵ��
     *--------------------------------------------------------------------------------------------*/

    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // ��ײ��⣺�����߽������ƶ�
        if (this.x + length >= 419 || this.x <= 50) {
            this.NowXSpeed = -this.NowXSpeed;
        }
        if (this.y + width >= 262 || this.y <= 50) {
            this.NowYSpeed = -this.NowYSpeed;
        }
        // ��������
        this.x += NowXSpeed;
        this.y += NowYSpeed;
        // ����ͷ��λ��
        this.ImageHeadx = x - 6;
        this.ImageHeady = y - 19;
    }

    /**
     * PrideBoss ������߼�������˷����ӵ���
     *
     * @param enemyBullets �з��ӵ��б�
     */
    public final void PrideShoot(ArrayList<EnemyBullet> enemyBullets) {
        if (ShootTick == ShootRate && !Shooted) {
            for (int i = 1; i <= 8; i++) {
                EnemyBullet enemyBullet = new EnemyBullet();
                enemyBullets.add(enemyBullet);
                enemyBullet.EnemyBullet(x + 8, y + 7, i); // �����������ƫ��λ��
            }
            Shooted = true;
            ShootTick = 0;
        } else if (ShootTick < ShootRate) {
            Shooted = false;
            ShootTick++;
        }
    }
}
