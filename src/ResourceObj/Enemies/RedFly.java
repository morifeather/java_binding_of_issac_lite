package ResourceObj.Enemies;

import ResourceObj.EnemyBullet;
import ResourceObj.Issac;
import ResourceObj.ResourceLoader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RedFly extends Fly{
    // ���캯������ʼ�� RedFly ������
    public RedFly()
    {
        super(); // ���ø��� Fly �Ĺ��캯��
        this.hp = 12; // ��������ֵΪ 12
        this.Xspeed = 1; // ˮƽ�ٶ�Ϊ 1
        this.Yspeed = 1; // ��ֱ�ٶ�Ϊ 1
        this.Imagelength = 19; // ͼ�񳤶�Ϊ 19
        this.Imagewidth = 23; // ͼ����Ϊ 23
        this.length = 11; // ʵ����ײ���򳤶�Ϊ 11
        this.width = 10; // ʵ����ײ������Ϊ 10
    }

    // ��ʼ������������ͼ�������ʵ��λ������
    @Override
    public void Initialize(int x, int y)
    {
        this.Imagex = x; // ����ͼ��� X ����
        this.Imagey = y; // ����ͼ��� Y ����
        this.x = x + 2; // ����ʵ��λ�õ� X ���꣬ƫ�� 2
        this.y = y + 11; // ����ʵ��λ�õ� Y ���꣬ƫ�� 11
    }

    // ����ͼ��֡�ķ���
    @Override
    public void UpdateImage() {
        ImageTick++; // ͼ��֡����������
        if(ImageTick > 1){
            ImageTick = 0; // ����������ֵ��������Ϊ 0��ʵ��ѭ������
        }
    }

    // ��ȡ��ǰͼ��ķ���
    @Override
    public BufferedImage getImage() {
        // ���� Isaac ��λ�þ���ʹ�����������ҵ�ͼ��
        if (Issac.getInstance().getX() > x) {
            return ResourceLoader.getInstance().RedFlyRightImage.get(ImageTick); // ʹ�����ҵ�ͼ��
        }
        else {
            return ResourceLoader.getInstance().RedFlyLeftImage.get(ImageTick); // ʹ�������ͼ��
        }
    }

    // ����ʱ�����ӵ��ķ���
    public void DeathBullet(ArrayList<EnemyBullet> enemyBullets){
        // ѭ������ 4 ���ӵ���i �� 1 �� 4��
        for(int i=1;i<5;i++){
            EnemyBullet enemyBullet=new EnemyBullet(); // ����һ�ŵз��ӵ�����
            enemyBullets.add(enemyBullet); // �����ӵ���ӵ��ӵ��б���
            enemyBullet.EnemyBullet(x,y,i); // ��ʼ���ӵ���λ�úͷ���
        }
    }
}
