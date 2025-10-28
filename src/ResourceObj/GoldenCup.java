package ResourceObj;

import java.awt.image.BufferedImage;

/**
 * ��ɫ������Դ�����࣬���õ���ģʽ����ʵ��
 */
public class GoldenCup {
    // ͼ��������ԣ�������ߴ磩
    protected int ImageX = 218, ImageY = 122, ImageLength = 32, ImageWidth = 64;
    
    // ʵ����Ⱦ�ߴ�
    int length, width;
    
    // ͼ����Դ
    private BufferedImage GoldenCupImage;

    // ����ʵ��
    private static GoldenCup instance = new GoldenCup();

    /**
     * ��ȡ����ʵ��
     * @return GoldenCupʵ��
     */
    public static GoldenCup getInstance() {
        return instance;
    }
    
    /*--------------------------------------------------------------------------*/
    /* ���������� */
    
    /**
     * ����ʵ�ʳ���
     * @param length Ҫ���õĳ���
     */
    public void setLength(int length) {
        this.length = length;
    }
    
    /**
     * ����ʵ�ʿ��
     * @param width Ҫ���õĿ��
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * ����ͼ����Դ
     * @param GoldenCupImage Ҫ���õ�ͼ��
     */
    public void setImage(BufferedImage GoldenCupImage) {
        this.GoldenCupImage = GoldenCupImage;
    }
    /*--------------------------------------------------------------------------*/
    
    /* ���������� */
    
    /**
     * ��ȡͼ����Դ
     * @return BufferedImage���͵�ͼ����Դ
     */
    public BufferedImage getImage() {
        return GoldenCupImage;
    }

    /**
     * ��ȡͼ���������
     * @return ͼ��Ļ�������ֵ
     */
    public int getImageLength() {
        return ImageLength;
    }
    
    /**
     * ��ȡͼ��X����
     * @return ͼ���X����λ��
     */
    public int getImageX() {
        return ImageX;
    }
    
    /**
     * ��ȡͼ��Y����
     * @return ͼ���Y����λ��
     */
    public int getImageY() {
        return ImageY;
    }

    /**
     * ��ȡͼ��������
     * @return ͼ��Ļ������ֵ
     */
    public int getImageWidth() {
        return ImageWidth;
    }
    
    /**
     * ��ȡʵ�ʳ���
     * @return ʵ����Ⱦ����
     */
    public int getLength() {
        return length;
    }
    
    /**
     * ��ȡʵ�ʿ��
     * @return ʵ����Ⱦ���
     */
    public int getWidth() {
        return width;
    }
}