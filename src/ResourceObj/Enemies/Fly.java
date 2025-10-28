package ResourceObj.Enemies;

import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Fly extends Enemy{
    private int flysoundtick=0;
    public Fly(){
        super();
        this.hp = 9; // ����Fly���������ֵΪ9
        this.Xspeed = 2; // ����ˮƽ�ƶ��ٶ�Ϊ2
        this.Yspeed = 2; // ���ô�ֱ�ƶ��ٶ�Ϊ2
        this.flight = true; // ��ʾ�õ��˿��Է��У������ϰ��
        this.Imagelength=19; // ͼ�񳤶�Ϊ19����
        this.Imagewidth=15; // ͼ����Ϊ15����
        this.length=8; // ʵ����ײ�䳤��Ϊ8����
        this.width=8; // ʵ����ײ����Ϊ8����
    }
    
    @Override
    public void Initialize(int x,int y){
        this.Imagex=x;this.Imagey=y; // ����ͼ�����ʼ����
        x=Imagex+5;y=Imagey+7; // �޸Ĵ����x��y���꣬��������ʵ��λ�ã���δʹ�ã�
    }
    
    @Override
    public BufferedImage getImage(){
        return ResourceLoader.getInstance().FlyImage.get(ImageTick); // ��ȡ��ǰ֡��Flyͼ��
    }
    
    @Override
    public void UpdateImage(){
        ImageTick++; // ����ͼ������
        if(ImageTick>3){ // ���ͼ����������3
            ImageTick=0; // ����Ϊ0��ʵ��ͼ��ѭ������
        }
    }
    
    @Override
    public void UpdateLocation(int x, int y, ArrayList<Obstacle> obstacles){
        if(this.Imagex<x){ // �����ǰx����С��Ŀ��x����
            this.Imagex+=Xspeed; // �����ƶ�
            this.NowXSpeed=Xspeed; // ��¼��ǰx�����ٶ�
        }
        if(this.Imagex>x){ // �����ǰx�������Ŀ��x����
            this.Imagex-=Xspeed; // �����ƶ�
            this.NowXSpeed=-Xspeed; // ��¼��ǰx�����ٶ�
        }
        if(this.Imagey<y){ // �����ǰy����С��Ŀ��y����
            this.Imagey+=Yspeed; // �����ƶ�
            this.NowYSpeed=Yspeed; // ��¼��ǰy�����ٶ�
        }
        if(this.Imagey>y){ // �����ǰy�������Ŀ��y����
            this.Imagey-=Yspeed; // �����ƶ�
            this.NowYSpeed=-Yspeed; // ��¼��ǰy�����ٶ�
        }
        this.x=Imagex+5;this.y=Imagey+7; // ����ʵ������
    }

    public void setFlysoundtick(int flysoundtick){
         this.flysoundtick=flysoundtick; // ����flysoundtickֵ
    }
    
    public int  getFlysoundtick(){
        return flysoundtick; // ��ȡflysoundtickֵ
    }

}
