package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.Enemy;
import ResourceObj.Issac;
import ResourceObj.SoundEffect;
import ResourceObj.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.Random;

public class Room {
    Random randit = new Random();
    protected int RoomState=0;
    protected int PlusDoorToward;
    protected int MinusDoorToward;

    /**
     * ��ȡ��ǰ����������ŷ���
     * @return �����ŵķ���ֵ
     */
    public int getPlusDoorToward(){
        return PlusDoorToward;
    }

    /**
     * ��ȡ��ǰ����ķ����ŷ���
     * @return �����ŵķ���ֵ
     */
    public int getMinusDoorToward(){
        return MinusDoorToward;
    }

    /**
     * �жϷ���״̬������
     * �����е��˶�������󣬷���״̬��Ϊ1�������ܵ��佱��
     * @param enemies �����б�
     */
    public void StateJudge(ArrayList<Enemy> enemies){
        if(RoomState == 0) {
            int EnemyCount = 0;
            // ���㵱ǰ���ڵĵ�������
            for (Enemy enemy : enemies) {
                if (enemy.getEnemyExist()) {
                    EnemyCount++;
                }
            }

            // �������ȫ�����𣬴�����������
            if(EnemyCount == 0) {
                RoomState = 1;
                int i = randit.nextInt(9); // �������0-8֮�������

                // ������������費ͬ����
                if(i == 8){
                    // ����ͷ�����������ٶȣ��ӿ��ƶ�������
                    Issac.getInstance().setHeadTickSpeed(Issac.getInstance().getHeadTickSpeed() - 1);
                    SoundEffect.playSound("Up", "sounds/Up.mp3"); // ����������Ч
                } else if(i == 7){
                    // �����˺�
                    Issac.getInstance().setDamage(Issac.getInstance().getDamage() + 3);
                    SoundEffect.playSound("Up", "sounds/Up.mp3"); // ����������Ч
                } else {
                    // Ĭ�ϻָ�����
                    Issac.getInstance().setHp(Issac.getInstance().getHp() + 2);
                    SoundEffect.playSound("Heart", "sounds/EatHeart.mp3"); // ��������ʰȡ��Ч
                    Issac.getInstance().setHeartSwitch(true); // ����������Ч
                }
            } else if (EnemyCount > 0) {
                // ���е��˴��ڣ����ַ���״̬Ϊ0
                RoomState = 0;
            } else {
                // �쳣�������
                System.out.println("Room State Wrongly");
            }
        }
    }

    /**
     * �����ϰ�����Ϣ���������д��
     * @param obstacles �ϰ����б�
     */
    public void InsertObstacleInfo(ArrayList<Obstacle> obstacles){
    }

    /**
     * ���������Ϣ���������д��
     * @param enemies �����б�
     */
    public void InsertEnemyInfo(ArrayList<Enemy> enemies){
    }

    /**
     * �����Ŷ������б���
     * @param doors ���б�
     */
    public void InsertDoorInfo(ArrayList<Door> doors){
        doors.add(new Door(true, PlusDoorToward));  // ���������
        doors.add(new Door(false, MinusDoorToward)); // ��ӷ�����
    }

    /**
     * ͳһ���뷽�������ݷ���״̬����������Щ����
     * @param enemies   �����б�
     * @param obstacles �ϰ����б�
     * @param doors     ���б�
     */
    public void Insertion(ArrayList<Enemy> enemies, ArrayList<Obstacle> obstacles, ArrayList<Door> doors){
        if(RoomState == 0){
            InsertEnemyInfo(enemies); // ����δ���ʱ�������
        }
        InsertObstacleInfo(obstacles); // ���ǲ����ϰ���
        InsertDoorInfo(doors); // ���ǲ�����
    }

    /**
     * ��ȡ��ǰ����״̬
     * @return ����״ֵ̬
     */
    public int getRoomState(){
        return RoomState;
    }
}