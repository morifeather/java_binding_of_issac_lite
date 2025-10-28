package ResourceObj;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Heart {
    // ��������λ�úͳߴ�����
    private int x, y, length = 25, width = 16;
    // ͼ�����������ڴ���Դ�������л�ȡ��Ӧ��ͼ��
    private int ImageNum;

    // ���캯������ʼ��λ�ú�ͼ������
    public Heart(int x, int y, int ImageNum) {
        this.x = x;
        this.y = y;
        this.ImageNum = ImageNum;
    }

    // ��ȡ����ͼ��Ŀ��
    public int getWidth() {
        return width;
    }

    // ��ȡ����ͼ��ĸ߶�
    public int getLength() {
        return length;
    }

    // ��ȡ��������x����
    public int getX() {
        return x;
    }

    // ��ȡ��������y����
    public int getY() {
        return y;
    }

    // ����Դ�������л�ȡ��Ӧ����������ͼ��
    public BufferedImage getHeartImage() {
        return ResourceLoader.getInstance().HeartImage.get(ImageNum);
    }

    /**
     * �˷���ԭ�����ڽ���ɫ������ֵ���ӻ�Ϊ�������ͼ��
     * Ŀǰ��ע�͵�����������Ҫʱ����
     */
    /*public void HpVisualize(ArrayList<Heart> hearts, boolean HeartSwitch) {
        if (HeartSwitch) {
            // ��յ�ǰ�������б�
            Iterator<Heart> iterator = hearts.iterator();
            while (iterator.hasNext()) {
                Heart heart = iterator.next();
                hearts.remove(heart);
            }

            // ���ݽ�ɫ��ǰ����ֵ������Ҫ��ʾ����������
            int i, num = Issac.getInstance().getHp() / 2;
            int remainder = Issac.getInstance().getHp() % 2;

            // �����������
            for (i = 1; i <= num; i++) {
                hearts.add(new Heart(i * 25, 20, 1));
            }

            // �������������Ӱ밮��
            if (remainder == 1) {
                hearts.add(new Heart(i * 25, 20, 0));
            }

            // �رո��¿���
            Issac.getInstance().setHeartSwitch(false);
        }
    }*/
}
