package ResourceObj.obstacle;

import java.awt.image.BufferedImage;


public class Poop extends Obstacle {
    int a=23;

    public int getA(){return a;}

    // ���캯�������ÿ��ƻ�����ʼ HP ��ͼƬ
    public Poop() {
        this.Breakable = true;  // ����Ϊ���ƻ�
        this.Hp = 5;            // ��ʼѪ��Ϊ 5
    }

    @Override
    public BufferedImage getImage(int i) {
        // ���ݵ�ǰ Hp ��ȡ��Ӧ��ͼƬ
        if (resourceloader != null && resourceloader.PoopImage != null && Hp > 0 && Hp <= resourceloader.PoopImage.size()) {
            return resourceloader.PoopImage.get(Hp - 1); // ͼƬ������ 0 ��ʼ
        }
        return null;
    }

    // �ӵ�����ʱ���ô˷���
    public void onBulletHit() {
        if (Breakable && Hp > 0) {
            Hp--;
            if (Hp == 1) {
                a=0; // Ѫ��Ϊ 0 ���ϰ���ʧ
            }
        }
    }
}