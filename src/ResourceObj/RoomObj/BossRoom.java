package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.*;
import ResourceObj.Enemies.Boss.Pride;
import ResourceObj.Enemies.Boss.SubPride1;
import ResourceObj.Enemies.Boss.SubPride2;
import ResourceObj.GoldenCup;
import ResourceObj.Issac;
import ResourceObj.obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BossRoom extends Room {
    Random randit = new Random();
    
    /**
     * ��д���෽������ӷ����ŵ�doors�б���
     * @param doors �洢�Ŷ�����б�
     */
    @Override
    public void InsertDoorInfo(ArrayList<Door> doors) {
        doors.add(new Door(false, MinusDoorToward));
    }

    /**
     * ���캯�������ڳ�ʼ��Boss����
     * @param doors �洢�Ŷ�����б�
     * @param obstacles �洢�ϰ��������б�
     * @param enemies �洢�����������б�
     * @param PlusDoorToward ���������ŵķ���
     * @param MinusDoorToward ���为���ŵķ���
     */
    public BossRoom(ArrayList<Door> doors, ArrayList<Obstacle> obstacles, ArrayList<Enemy> enemies, int PlusDoorToward, int MinusDoorToward) {
        super();
        this.PlusDoorToward = PlusDoorToward;
        
        // ���������ŷ������÷����ŷ���
        if (PlusDoorToward == 1) {
            this.MinusDoorToward = 2;
        } else if (PlusDoorToward == 2) {
            this.MinusDoorToward = 1;
        } else if (PlusDoorToward == 3) {
            this.MinusDoorToward = 4;
        } else if (PlusDoorToward == 4) {
            this.MinusDoorToward = 3;
        }
        
        InsertDoorInfo(doors);      // ��������Ϣ
        InsertEnemyInfo(enemies);   // ���������Ϣ
        InsertObstacleInfo(obstacles); // �����ϰ�����Ϣ
    }

    /**
     * ���������Ϣ��enemies�б���
     * @param enemies �洢�����������б�
     */
    @Override
    public void InsertEnemyInfo(ArrayList<Enemy> enemies) {
        // ���һ����ʼ״̬�� Pride Boss
        enemies.add(new Pride(50 + 23 * 8, 50 + 23 * 4, 0, 1));
        
        // ������ɲ�ͬ���͵ĵ������
        int i = randit.nextInt(4);
        
        if (i == 0) {
            // �����ֻ RedFly
            enemies.add(new RedFly());
            enemies.get(1).Initialize(50 + 23 * 7, 50 + 23 * 4);
            enemies.add(new RedFly());
            enemies.get(2).Initialize(50 + 23 * 9, 50 + 23 * 4);
        } else if (i == 1) {
            // �����ֻ Turkey
            enemies.add(new Turkey());
            enemies.get(1).Initialize(50, 50 + 23 * 4);
            enemies.add(new Turkey());
            enemies.get(2).Initialize(50 + 23 * 15, 50 + 23 * 4);
        } else if (i == 2) {
            // �����ֻ Tentacle
            for (int a = 1; a <= 4; a++) enemies.add(new Tentacle());
            enemies.get(1).Initialize(50, 20);
            enemies.get(2).Initialize(50 + 23 * 15, 20);
            enemies.get(3).Initialize(50, 20 + 23 * 8);
            enemies.get(4).Initialize(50 + 23 * 15, 20 + 23 * 8);
        } else if (i == 3) {
            // �ٴ���������Ƿ���Ӹ��� Boss ���˻���ͨ Fly
            int Luck = randit.nextInt(10);
            if (Luck == 0) {
                // ������� Pride��һ�� SubPride2 ��һ�� SubPride1
                enemies.add(new Pride(50, 50 + 4 * 23, 1, 1));
                enemies.add(new Pride(50 + 23 * 15, 50 + 4 * 23, 1, 2));
                enemies.add(new SubPride2(50, 50, 2, 1));
                enemies.add(new SubPride1(50 + 23 * 15, 50 + 23 * 8, 2, 2));
            } else {
                // �����ֻ Fly
                enemies.add(new Fly());
                enemies.add(new Fly());
                enemies.get(1).Initialize(50 + 23 * 7, 50 + 23 * 4);
                enemies.get(0).Initialize(50 + 23 * 9, 50 + 23 * 4);
            }
        }
    }

    /**
     * �жϵ�ǰ����״̬��������е��˶��������򴥷������߼�
     * @param enemies �洢�����������б�
     */
    @Override
    public void StateJudge(ArrayList<Enemy> enemies){
        if (RoomState == 0) {
            int EnemyCount = 0;
            for (Enemy enemy : enemies) {
                if (enemy.getEnemyExist()) {
                    EnemyCount++;
                }
            }
            
            if (EnemyCount == 0) {
                // ���е�������������״̬����Ϊ������
                RoomState = 1;
                
                // Isaac �ظ�һ������ֵ���л�������ʾ״̬
                Issac.getInstance().setHp(Issac.getInstance().getHp() + 1);
                Issac.getInstance().setHeartSwitch(true);
                
                // ���� GoldenCup ͼ����Դ
                try {
                    GoldenCup.getInstance().setImage(ImageIO.read(Issac.class.getClassLoader().getResource("images/GoldenCup.png")));
                    GoldenCup.getInstance().setLength(32);
                    GoldenCup.getInstance().setWidth(64);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (EnemyCount > 0) {
                // ���������е��˴���
                RoomState = 0;
            } else {
                // �쳣���������״̬����
                System.out.println("Room State Wrongly");
            }
        }
    }
}
