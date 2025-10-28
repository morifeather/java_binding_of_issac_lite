package ResourceObj;

import ResourceObj.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * ��ʾ�����˷�����ӵ����󣬴����ӵ����˶����������ں���Ⱦ������ݡ�
 */
public class IssacBullet {
    //------------------------------------------------------------------------------------//
    // ��Դ������ʵ�������ڻ�ȡͼ����Դ
    ResourceLoader resourceloader = ResourceLoader.getInstance();

    /**
     * ������Դ������
     * @param resourceloader �µ���Դ������ʵ��
     */
    public void setResourceloader(ResourceLoader resourceloader) {
        this.resourceloader = resourceloader;
    }
    //------------------------------------------------------------------------------------//

    // �ӵ���ǰλ��
    private int Bulletx, Bullety;
    // �ӵ����ڵ�ʱ�䣨֡����
    private int BulletExistTime = 0;
    // ͼ�����λ�úͳߴ�
    private int ImageX, ImageY, ImageLength = 12, ImageWidth = 12;
    // ʵ����ײ��ߴ�
    private int width = 12, length = 12;
    // �ٶȷ���
    private int Xv = 0, Yv = 0;
    // �ӵ�ͼ��
    private BufferedImage IssacBulletImage;
    // �Ƿ�Ϊ�з��ӵ�������δʹ�ã�
    boolean Hostile = false;
    // �ӵ��Ƿ���Ȼ����
    private boolean BulletExist = true;
    // �ӵ��Ƿ������������׶�
    private boolean Death = false;
    // ����֡������
    private int ImageTick = 0;
    // �Ƿ��Ѹ��¹�λ��
    private boolean Updated = false;
    //------------------------------------------------------------------------------------//


    /**
     * �����ӵ���X����
     * @param x �µ�X����
     */
    public void setX(int x) {
        if (BulletExist) {
            this.Bulletx = x;
            this.ImageX = x;
        }
        if (BulletExist == false) {
            this.ImageX = x - 21;
        }
    }

    /**
     * �����ӵ���Y����
     * @param y �µ�Y����
     */
    public void setY(int y) {
        this.Bullety = y - 24;
    }

    /**
     * �����ӵ��Ĵ���״̬
     * @param BulletExist �µĴ���״̬
     */
    public void setIssacBulletExist(boolean BulletExist) {
        this.BulletExist = BulletExist;
    }

    /**
     * ��ʼ���ӵ�ͼ��Ĭ��ȡ��һ��ͼ��
     */
    public void setIssacBulletImage() {
        IssacBulletImage = resourceloader.BulletImage.get(0);
    }
    //------------------------------------------------------------------------------------//

    /**
     * ��ȡͼ����Ƶ�X����
     * @return ͼ����Ƶ�X����
     */
    public int getImageX() {
        return ImageX;
    }

    /**
     * ��ȡͼ����Ƶ�Y����
     * @return ͼ����Ƶ�Y����
     */
    public int getImageY() {
        return ImageY;
    }

    /**
     * ��ȡͼ��ĸ߶�
     * @return ͼ��ĸ߶�
     */
    public int getImageLength() {
        return ImageLength;
    }

    /**
     * ��ȡͼ��Ŀ��
     * @return ͼ��Ŀ��
     */
    public int getImageWidth() {
        return ImageWidth;
    }

    /**
     * ��ȡX�����ٶ�
     * @return X�����ٶ�
     */
    public int getXv() {
        return Xv;
    }

    /**
     * ��ȡY�����ٶ�
     * @return Y�����ٶ�
     */
    public int getYv() {
        return Yv;
    }

    /**
     * ��ȡ��ײ����
     * @return ��ײ����
     */
    public int getWidth() {
        return width;
    }

    /**
     * ��ȡ��ײ��߶�
     * @return ��ײ��߶�
     */
    public int getLength() {
        return length;
    }

    /**
     * ��ȡ�ӵ���X����
     * @return �ӵ���X����
     */
    public int getBulletX() {
        return Bulletx;
    }

    /**
     * ��ȡ�ӵ���Y����
     * @return �ӵ���Y����
     */
    public int getBulletY() {
        return Bullety;
    }

    /**
     * ��ȡ��ǰ�ӵ�ͼ��
     * @return ��ǰ�ӵ�ͼ��
     */
    public BufferedImage getIssacBulletImage() {
        return IssacBulletImage;
    }

    /**
     * ��ȡ�ӵ�����״̬
     * @return �ӵ�����״̬
     */
    public boolean getBulletExist() {
        return BulletExist;
    }

    /**
     * ��ȡ�ӵ��Ƿ�������״̬
     * @return �ӵ��Ƿ�������״̬
     */
    public boolean getDeath() {
        return Death;
    }
    //------------------------------------------------------------------------------------//

    /**
     * ��ʼ���ӵ���λ�ú��ٶ�
     * @param x �ӵ���ʼX����
     * @param y �ӵ���ʼY����
     * @param IssacNowXSpeed �����˵�ǰX�����ٶ�
     * @param IssacNowYSpeed �����˵�ǰY�����ٶ�
     * @param IssacShotSpeed �ӵ���������ٶ�
     * @param ShootingDirection ������� (1=��, 2=��, 3=��, 4=��)
     */
    public void BulletInitialize(int x, int y, double IssacNowXSpeed, double IssacNowYSpeed, int IssacShotSpeed, int ShootingDirection) {
        Bulletx = x;
        Bullety = y;
        if (ShootingDirection == 1/*RightShooting*/) {
            Xv += (IssacNowXSpeed / 2 + IssacShotSpeed);
            Yv += (IssacNowYSpeed / 2);
        } else if (ShootingDirection == 2/*LeftShooting*/) {
            Xv += (IssacNowXSpeed / 2 - IssacShotSpeed);
            Yv += (IssacNowYSpeed / 2);
        } else if (ShootingDirection == 3/*UpShooting*/) {
            Xv += (IssacNowXSpeed / 2);
            Yv += (IssacNowYSpeed / 2 - IssacShotSpeed);
        } else if (ShootingDirection == 4/*DownShooting*/) {
            Xv += (IssacNowXSpeed / 2);
            Yv += (IssacNowYSpeed / 2 + IssacShotSpeed);
        }
    }

    /**
     * �����ӵ�״̬
     * @param IssacRange �ӵ������з�Χ
     */
    public void UpdateBullet(int IssacRange) {
        if (!Death) {
            if (BulletExistTime < IssacRange - 7) {
                Bulletx += Xv;
                Bullety += Yv;
                ImageX = Bulletx;
                ImageY = Bullety;
                BulletExistTime++;
            } else if (BulletExistTime >= IssacRange - 7 && BulletExistTime < IssacRange) {
                Bulletx += Xv;
                ImageX = Bulletx;
                Bullety += Yv + 1;
                ImageY = Bullety;
                BulletExistTime++;
            } else {
                Death = true;
            }
            // ���߽���ײ
            if (Bulletx < 50 || Bulletx + width > 419 || Bullety + 11 < 50 || Bullety + 11 + width > 262) {
                Death = true;
            }
        } else if (Death) {
            IssacBulletDeath();
        }
    }

    /**
     * �����ӵ�������״̬
     * @param Death �µ�����״̬
     */
    public void setDeath(boolean Death) {
        this.Death = Death;
    }

    /**
     * �����ӵ�����������
     */
    public void IssacBulletDeath() {
        if (!Updated) {
            this.ImageY -= 20;
            this.ImageX -= 20;
            Updated = true;
        }
        ImageLength = 45;
        ImageWidth = 50;
        length = 0;
        width = 0;
        IssacBulletImage = resourceloader.IssacBulletDeleteImage.get(ImageTick);
        ImageTick++;
        if (ImageTick == 8) {
            BulletExist = false;
        }
    }
}
