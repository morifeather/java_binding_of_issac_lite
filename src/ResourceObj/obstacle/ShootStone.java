package ResourceObj.obstacle;

import ResourceObj.Enemies.Enemy;
import ResourceObj.EnemyBullet;
import ResourceObj.ResourceLoader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ShootStone extends Obstacle{
    protected ResourceLoader resourceloader=ResourceLoader.getInstance();
    protected BufferedImage ShootStoneImage;
    protected int NowRoomState;
    protected int x,y,Toward;
    protected int ShootTick;
    protected boolean Shooted;
    protected int ShootRate=60;

    /**
     * ��ȡ�ϰ���X����
     * @return X����ֵ
     */
    public int getX(){return x;}
    
    /**
     * ��ȡ�ϰ���Y����
     * @return Y����ֵ
     */
    public int getY(){return y;}

    /**
     * ��ʼ�����ʯͷ����
     * @param x ����X����
     * @param y ����Y����
     * @param Toward �������0-3��
     */
    @Override
    public void Initialize(int x,int y,int Toward){
        // ���������������ʵ������λ��
        this.x = 50+23*x;
        this.y = 50+23*y;
        // ���ض�Ӧ�����ͼƬ
        this.ShootStoneImage = resourceloader.ShootStoneImage.get(Toward);
        this.Toward=Toward;
    }
    
    /**
     * ��ȡ��ǰ״̬�µ���ʾͼƬ
     * @param i ����״̬��0:�ر�, 1:������
     * @return ��Ӧ״̬�µ�ͼƬ
     */
    @Override
    public BufferedImage getImage(int i){
        // ��¼��ǰ����״̬
        NowRoomState=i;
        
        if(i==1){
            // ����״̬���ػ�������ͼƬ
            return resourceloader.ShootStoneImage.get(Toward);
        }
        else if(i==0){
            // �ر�״̬����ƫ��4�������Ļ�������ͼƬ
            return resourceloader.ShootStoneImage.get(Toward+4);
        }
        else{
            // ����״̬����
            System.out.println("Room State Error");
            return null;
        }
    }

    /**
     * ����߼�������
     * @param enemyBullets �з��ӵ��б�����
     */
    public void ShootStoneShoot(ArrayList<EnemyBullet> enemyBullets){
        // �������δ������ִ������߼�
        if (NowRoomState != 1) return;

        // ����Ƿ񵽴���������δ�����
        if (ShootTick == ShootRate && !Shooted) {
            // ���ü�ʱ�������Ϊ�����
            ShootTick = 0;
            Shooted = true;
            
            // �������ӵ�����ӵ��б���
            EnemyBullet enemyBullet = new EnemyBullet();
            enemyBullets.add(enemyBullet);
            
            // ���ݳ������ӵ�
            if(Toward==0) {
                // ���·���
                enemyBullet.EnemyBullet(x, y,2);
            }
            else if(Toward==1){
                // ���Ϸ���
                enemyBullet.EnemyBullet(x, y,1);
            }
            else if(Toward==2){
                // ���ҷ���
                enemyBullet.EnemyBullet(x, y,4);
            }
             else if(Toward==3){
                // ������
                enemyBullet.EnemyBullet(x, y,3);
            }
        } else if (ShootTick < ShootRate) {
            // �ӵ���ȴ����ʱ
            Shooted = false;
            ShootTick++;
        }
    }
}
