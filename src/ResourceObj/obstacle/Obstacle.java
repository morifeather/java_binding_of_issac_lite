package ResourceObj.obstacle;

import ResourceObj.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle {
    // ���������ֶ�
    protected int x, y, a = 23; // ����͹̶��ߴ�
    protected int Hp = 1; // ����ֵ
    protected ResourceLoader resourceloader = ResourceLoader.getInstance(); // ��Դ����������
    protected BufferedImage Image; // ͼ����Դ
    protected boolean obstacleExist = true; // �ϰ������״̬
    protected boolean Breakable = false; // �Ƿ���ƻ�
    //----------------------------------------------------------------------------//

    /**
     * ������Դ�����������ڶ�̬������Դ��
     */
    public void setResourceloader() {
        this.resourceloader = ResourceLoader.getInstance();
    }

    /**
     * �����ϰ���ͼ��
     * @param i ͼ������
     */
    public void setObstacleImage(int i) {
        this.Image = resourceloader.StoneImage.get(i);
    }

    /**
     * ����X����
     * @param Rectx ����X����
     */
    public void setX(int Rectx) {
        this.x = 50 + 23 * Rectx;
    }

    /**
     * ����Y����
     * @param Recty ����Y����
     */
    public void setY(int Recty) {
        this.y = 50 + 23 * Recty;
    }

    /**
     * ��������ֵ
     * @param Hp �µ�����ֵ
     */
    public void setHp(int Hp) {
        this.Hp = Hp;
    }

    /**
     * ���ÿ��ƻ���
     * @param Breakable �Ƿ���ƻ�
     */
    public void setBreakable(boolean Breakable) {
        this.Breakable = Breakable;
    }

    /**
     * ��ʼ������
     * @param x ����X����
     * @param y ����Y����
     * @param i ͼ������
     */
    public void Initialize(int x, int y, int i) {
        this.x = 50 + 23 * x;
        this.y = 50 + 23 * y;
        this.Image = resourceloader.StoneImage.get(i);
    }
    //----------------------------------------------------------------------------//

    /**
     * ��ȡ��ǰͼ��
     * @param i ͼ��������δʹ�ã�������Ҫ�Ż���
     * @return ��ǰͼ��
     */
    public BufferedImage getImage(int i) {
        return Image;
    }

    /**
     * ��ȡ���ƻ�״̬
     * @return �Ƿ���ƻ�
     */
    public boolean getBreakable() {
        return Breakable;
    }

    /**
     * ��ȡ�ϰ������״̬
     * @return �Ƿ����
     */
    public boolean getObstacleExist() {
        return obstacleExist;
    }

    /**
     * ��ȡX����
     * @return X����
     */
    public int getX() {
        return x;
    }

    /**
     * ��ȡY����
     * @return Y����
     */
    public int getY() {
        return y;
    }

    /**
     * ��ȡ�ߴ�ֵa
     * @return �ߴ�ֵ
     */
    public int getA() {
        return a;
    }

    /**
     * ��ȡ��ǰ����ֵ
     * @return ����ֵ
     */
    public int getHp() {
        return Hp;
    }
}