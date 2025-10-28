package ResourceObj.Enemies;

import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tentacle extends Enemy{
    int AnimationSlow;
    public Tentacle(){
        super();
        // ���ô��ֵ��˳�ʼ����ֵΪ18
        this.hp = 18;
        // ��ʼ��ͼ��֡������
        this.ImageTick = 0;
        // ���øõ���Ϊ�Ƿ��е�λ
        this.flight = false;
        // ����ʵ����ײ����ĳߴ�
        this.length=26;
        this.Imagelength=26;
        this.width=17;
        this.Imagewidth=50;
    }
    @Override
    public void Initialize(int x, int y) {
        // ����ͼ�������ʵ������
        this.Imagex=x;
        this.Imagey=y;
        this.x=x;
        // y��ƫ��33�����Ե�����ʾλ��
        this.y=y+33;
    }
    @Override
    public void UpdateImage() {
        // �����ٶȼ���������
        AnimationSlow++;
        if(AnimationSlow==3){
            // ÿ3֡����һ��ͼ��֡
            this.ImageTick++;
            if(ImageTick>5){
                // ͼ��֡ѭ�����������Ϊ5
                this.ImageTick=0;
            }
            AnimationSlow=0;
        }
    }
    @Override
    public BufferedImage getImage() {
        // ��ȡ��ǰͼ��֡��������Դ�������е�TentacleImage�б�
        return ResourceLoader.getInstance().TentacleImage.get(this.ImageTick);
    }
    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
    }
}
