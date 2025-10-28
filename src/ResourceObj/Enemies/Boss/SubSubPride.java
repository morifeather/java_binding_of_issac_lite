package ResourceObj.Enemies.Boss;

import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SubSubPride extends Pride{
    // ���캯�������ø���Pride�Ĺ��췽����ʼ��λ�á�״̬�ͷ���
    public SubSubPride(int x,int y,int i,int toward){
        super(x,y,i,toward);
    }
    
    // ��д��ȡͷ��ͼ��ķ���������PrideHeadImage�еĵ�2��ͼ��
    @Override
    public BufferedImage getHeadImage(){
        return ResourceLoader.getInstance().PrideHeadImage.get(2);
    }
    
    // ��д��ȡ����ͼ��ķ���������null����ʾ�����಻�ṩ����ͼ��
    @Override
    public BufferedImage getBodyImage(){
        return null;
    }
    
    // ��д����λ�õķ�����ʵ�������߽�ʱ�������߼�
    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // ����Ƿ񵽴�ˮƽ�߽磬����������ˮƽ�ٶ�
        if(this.x+length>=419 || this.x<=50){
            this.NowXSpeed=-this.NowXSpeed;
        }
        
        // ����Ƿ񵽴ﴹֱ�߽磬����������ֱ�ٶ�
        if(this.y+width>=262 || this.y<=50){
            this.NowYSpeed=-this.NowYSpeed;
        }
        
        // ���ݵ�ǰ�ٶȸ���x��y����
        this.x+=NowXSpeed;
        this.y+=NowYSpeed;
        
        // ����ͷ��ͼ���λ��������ͬ��
        this.ImageHeadx=x;
        this.ImageHeady=y;
    }
}
