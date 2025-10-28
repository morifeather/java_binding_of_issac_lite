package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.Enemy;
import ResourceObj.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.Random;

public class RoomRandomSystem {
    Random randit = new Random();

    /**
     * ������һ��������ŵķ������������һ�����䣬�������µ��ŵķ���
     *
     * @param doors           ��ǰ��������б�
     * @param obstacles       ��ǰ������ϰ����б�
     * @param enemies         ��ǰ����ĵ����б�
     * @param LastRoomPlusDoor  ��һ������������ŷ������ϡ��ң�
     * @param LastRoomMinusDoor ��һ������ĸ����ŷ������¡���
     * @return                  ���ɵľ��巿��ʵ��
     */
    public Room RandomRoom(ArrayList<Door> doors, ArrayList<Obstacle> obstacles, ArrayList<Enemy> enemies,
                           int LastRoomPlusDoor, int LastRoomMinusDoor) {
        int i = new Random().nextInt(7) + 1; // ���ѡ��һ���������ͣ�1-7��
        int PlusDoor = 0, MinusDoor = 0; // ��ʼ���·�����ŷ���

        // ������һ������������ŷ����������·�����ŷ���
        if (LastRoomPlusDoor == 1) { // �����һ�������������������
            MinusDoor = 2; // �·���ĸ�������Ϊ����
            PlusDoor = 2; // �·����������Ҳ����Ϊ���ϣ��������е���
            if (LastRoomMinusDoor == 4) { // �����һ������ĸ�����������
                while (PlusDoor == 2 || PlusDoor == 4) { // ȷ���·���������Ų������ϻ�����
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            } else if (LastRoomMinusDoor == 3) { // �����һ������ĸ�����������
                while (PlusDoor == 2 || PlusDoor == 3) { // ȷ���·���������Ų������ϻ�����
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            } else { // �����һ������ĸ����Ų�������Ҳ��������
                while (PlusDoor == 2) { // ȷ���·���������Ų�������
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            }

        } else if (LastRoomPlusDoor == 2) { // �����һ�������������������
            MinusDoor = 1; // �·���ĸ�������Ϊ����
            PlusDoor = 1; // �·����������Ҳ����Ϊ���£��������е���
            if (LastRoomMinusDoor == 4) { // �����һ������ĸ�����������
                while (PlusDoor == 1 || PlusDoor == 4) { // ȷ���·���������Ų������»�����
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            } else if (LastRoomMinusDoor == 3) { // �����һ������ĸ�����������
                while (PlusDoor == 1 || PlusDoor == 3) { // ȷ���·���������Ų������»�����
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            } else { // �����һ������ĸ����Ų�������Ҳ��������
                while (PlusDoor == 1) { // ȷ���·���������Ų�������
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            }

        } else if (LastRoomPlusDoor == 3) { // �����һ�������������������
            MinusDoor = 4; // �·���ĸ�������Ϊ����
            PlusDoor = 4; // �·����������Ҳ����Ϊ���󣬺������е���
            if (LastRoomMinusDoor == 1) { // �����һ������ĸ�����������
                while (PlusDoor == 1 || PlusDoor == 4) { // ȷ���·���������Ų������»�����
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            } else if (LastRoomMinusDoor == 2) { // �����һ������ĸ�����������
                while (PlusDoor == 2 || PlusDoor == 4) { // ȷ���·���������Ų������ϻ�����
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            } else { // �����һ������ĸ����Ų�������Ҳ��������
                while (PlusDoor == 4) { // ȷ���·���������Ų�������
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            }

        } else if (LastRoomPlusDoor == 4) { // �����һ�������������������
            MinusDoor = 3; // �·���ĸ�������Ϊ����
            PlusDoor = 3; // �·����������Ҳ����Ϊ���ң��������е���
            if (LastRoomMinusDoor == 1) { // �����һ������ĸ�����������
                while (PlusDoor == 1 || PlusDoor == 3) { // ȷ���·���������Ų������»�����
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            } else if (LastRoomMinusDoor == 2) { // �����һ������ĸ�����������
                while (PlusDoor == 2 || PlusDoor == 3) { // ȷ���·���������Ų������ϻ�����
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            } else { // �����һ������ĸ����Ų�������Ҳ��������
                while (PlusDoor == 3) { // ȷ���·���������Ų�������
                    PlusDoor = randit.nextInt(4) + 1; // �������ѡ��һ������
                }
            }
        }

        // ���������ѡ�����ķ�������
        switch (i) {
            case 1:
                return new Room1(doors, obstacles, enemies, PlusDoor, MinusDoor);
            case 2:
                return new Room2(doors, obstacles, enemies, PlusDoor, MinusDoor);
            case 3:
                return new Room3(doors, obstacles, enemies, PlusDoor, MinusDoor);
            case 4:
                return new Room4(doors, obstacles, enemies, PlusDoor, MinusDoor);
            case 5:
                return new Room5(doors, obstacles, enemies, PlusDoor, MinusDoor);
            case 6:
                return new Room6(doors, obstacles, enemies, PlusDoor, MinusDoor);
            case 7:
                return new Room7(doors, obstacles, enemies, PlusDoor, MinusDoor);
            default:
                System.out.println("Room Load Wrongly");
                return null;
        }
    }
}
