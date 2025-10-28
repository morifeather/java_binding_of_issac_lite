package ResourceObj.Enemies.Boss;

import ResourceObj.EnemyBullet;
import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * SubPride2 ���ʾ Pride Boss �ĵڶ���̬��
 * ����̳��� Pride����ʵ�����ض��Ĺ������ƶ���Ϊ��
 */
public class SubPride2 extends Pride {
    
    // �����ʱ�������״̬��־
    protected int ShootTick = 0, ShootRate = 50;    
    protected boolean Shooted = false;

    /**
     * ���캯������ʼ�� SubPride2 ��λ�á��ٶȵ����ԡ�
     *
     * @param x      ��ʼ x ����
     * @param y      ��ʼ y ����
     * @param i      �Ѷȵȼ� (0-3)
     * @param toward �ƶ����� (1=��, 2=��)
     */
    public SubPride2(int x, int y, int i, int toward) {
        super(x, y, i, toward);
    }

    /**
     * ��ȡͷ��ͼ�񣬸��ݵ�ǰˮƽ�ٶȷ���ѡ��ͬ��ͼ��
     *
     * @return ���ص�ǰ״̬�µ�ͷ��ͼ��
     */
    @Override
    public BufferedImage getHeadImage() {
        if (NowXSpeed > 0) {
            return ResourceLoader.getInstance().PrideHeadImage.get(0);
        } else {
            return ResourceLoader.getInstance().PrideHeadImage.get(1);
        }
    }

    /**
     * ��ȡ����ͼ��SubPride2 û������ͼ����˷��� null��
     *
     * @return ���� null
     */
    @Override
    public BufferedImage getBodyImage() {
        return null;
    }

    /**
     * ����λ�ã�����߽���ײ������ͷ���λ�á�
     *
     * @param targetX Ŀ�� x ���꣨δʹ�ã�
     * @param targetY Ŀ�� y ���꣨δʹ�ã�
     * @param obstacles �ϰ����б�δʹ�ã�
     */
    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // �������ұ߽����ײ
        if (this.x + length >= 419 || this.x <= 50) {
            this.NowXSpeed = -this.NowXSpeed;
        }
        // �������±߽����ײ
        if (this.y + width >= 262 || this.y <= 50) {
            this.NowYSpeed = -this.NowYSpeed;
        }
        // ��������
        this.x += NowXSpeed;
        this.y += NowYSpeed;
        // ����ͷ��λ��
        this.ImageHeadx = x;
        this.ImageHeady = y;
    }

    /**
     * SubPride2 ����������߼������� 5-8 ������ӵ���
     *
     * @param enemyBullets �з��ӵ��б�
     */
    public void SubPrideShoot(ArrayList<EnemyBullet> enemyBullets) {
        if (ShootTick == ShootRate && !Shooted) {
            for (int i = 5; i <= 8; i++) {
                EnemyBullet enemyBullet = new EnemyBullet();
                enemyBullets.add(enemyBullet);
                enemyBullet.EnemyBullet(x + 8, y + 7, i); // �����ƫ��
            }
            Shooted = true;
            ShootTick = 0;
        } else if (ShootTick < ShootRate) {
            Shooted = false;
            ShootTick++;
        }
    }
}
