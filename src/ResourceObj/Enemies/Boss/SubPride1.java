package ResourceObj.Enemies.Boss;

import ResourceObj.EnemyBullet;
import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * SubPride1 ���� Pride Boss ������̬�࣬�̳��� Pride �ࡣ
 * ����ʵ�����ض���ͷ��ͼ����ʾ���ƶ��߼�������߼���
 */
public class SubPride1 extends Pride {
    // �����ʱ�����䵯״̬��־
    protected int ShootTick = 0, ShootRate = 50;
    protected boolean Shooted = false;

    /**
     * ���캯����ʼ��λ�úͷ���
     *
     * @param x      ��ʼ x ����
     * @param y      ��ʼ y ����
     * @param i      �Ѷȵȼ� (0-3)
     * @param toward �ƶ����� (1=��, 2=��)
     */
    public SubPride1(int x, int y, int i, int toward) {
        super(x, y, i, toward);
    }

    /**
     * ��ȡ��ǰ״̬�µ�ͷ��ͼ��
     *
     * @return ���ض�Ӧ�����ͷ��ͼ��
     */
    @Override
    public BufferedImage getHeadImage() {
        if (NowXSpeed > 0) {
            return ResourceLoader.getInstance().PrideHeadImage.get(0); // ���ҷ���
        } else {
            return ResourceLoader.getInstance().PrideHeadImage.get(1); // ������
        }
    }

    /**
     * ��ȡ���岿�ֵ�ͼ��SubPride1 û������ͼ��
     *
     * @return ���Ƿ��� null
     */
    @Override
    public BufferedImage getBodyImage() {
        return null;
    }

    /**
     * ����λ�ò�����߽練��
     *
     * @param targetX Ŀ�� x ���꣨δʹ�ã�
     * @param targetY Ŀ�� y ���꣨δʹ�ã�
     * @param obstacles �ϰ����б�δʹ�ã�
     */
    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // ��ײ�߽�󷴵�
        if (this.x + length >= 419 || this.x <= 50) {
            this.NowXSpeed = -this.NowXSpeed;
        }
        if (this.y + width >= 262 || this.y <= 50) {
            this.NowYSpeed = -this.NowYSpeed;
        }

        // ��������
        this.x += NowXSpeed;
        this.y += NowYSpeed;

        // ����ͷ��ͼ��λ��
        this.ImageHeadx = x;
        this.ImageHeady = y;
    }

    /**
     * ʵ������߼������� 4 ������ĵз��ӵ�
     *
     * @param enemyBullets �洢�з��ӵ����б�
     */
    public void SubPrideShoot(ArrayList<EnemyBullet> enemyBullets) {
        if (ShootTick == ShootRate && !Shooted) {
            for (int i = 1; i <= 4; i++) {
                EnemyBullet enemyBullet = new EnemyBullet();
                enemyBullets.add(enemyBullet);
                enemyBullet.EnemyBullet(x + 8, y + 7, i); // �����ƫ����Ϊ (8,7)
            }
            Shooted = true;
            ShootTick = 0;
        } else if (ShootTick < ShootRate) {
            Shooted = false;
            ShootTick++;
        }
    }
}