package ResourceObj.obstacle;

import ResourceObj.ResourceLoader;

import java.awt.image.BufferedImage;

public class Stone extends Obstacle {
    // ʹ�ø��������е�resourceloader�ֶκ�a�ֶ�
    
    //----------------------------------------------------------------------------//
    // ���캯�����ʼ���������Ը�����Ҫ��ӣ����ﱣ��Ĭ�Ϲ��캯��
    
    /**
     * ����X����
     * @param Rectx ����X����
     */
    @Override
    public void setX(int Rectx) {
        this.x = 50 + 23 * Rectx;
    }

    /**
     * ����Y����
     * @param Recty ����Y����
     */
    @Override
    public void setY(int Recty) {
        this.y = 50 + 23 * Recty;
    }

    /**
     * ��ʼ������������λ�ú�ͼ��
     * @param x ����X����
     * @param y ����Y����
     * @param i ͼ������
     */
    @Override
    public void Initialize(int x, int y, int i) {
        super.Initialize(x, y, i);  // ���ø����ʼ������
        this.Image = resourceloader.StoneImage.get(i);  // ���ݲ�������ͼ��
    }

    /**
     * ������Դ�����������ڶ�̬���ز�ͬ��Դ��
     * @param resourceloader �µ���Դ������
     */
    public void setResourceloader(ResourceLoader resourceloader) {
        this.resourceloader = resourceloader;
    }

    /**
     * �����ϰ���ͼ��
     * @param i ͼ������
     */
    @Override
    public void setObstacleImage(int i) {
        this.Image = resourceloader.StoneImage.get(i);
    }
    //----------------------------------------------------------------------------//

    /**
     * ��ȡX����
     * @return X����ֵ
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * ��ȡY����
     * @return Y����ֵ
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * ��ȡ����ߴ�
     * @return �ߴ�ֵa
     */
    @Override
    public int getA() {
        return a;
    }

    /**
     * ��ȡ��ǰͼ��
     * @param i ͼ���������˴�δʹ�ã�����Ϊ�˽ӿ�һ���ԣ�
     * @return ��ǰͼ��
     */
    @Override
    public BufferedImage getImage(int i) {
        return Image;
    }
}