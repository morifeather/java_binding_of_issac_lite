package ResourceObj;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Door �����ڱ�ʾ��Ϸ�е��Ŷ���
 * �����ŵ�λ�á����򡢳ߴ�������Լ��� Isaac ��ɫ�Ľ����߼�
 */
public class Door {
    // �ŵ�����
    private int Doorx, Doory;
    // �ŵ�ͼ�񳤶ȺͿ��
    private int Imagelength, Imagewidth;
    // �ŵķ���1: ����, 2: ����, 3: ����, 4: ���ţ�
    private int toward;
    // �ŵ�������־��true ��ʾ���ӷ���ţ�false ��ʾ���ٷ���ţ�
    private boolean PlusOrMinus; /* True ��ʾ�Ӻŷ���False ��ʾ���ŷ��� */

    /**
     * ��ȡ�ŵķ���
     * @return �����ŵķ���ֵ
     */
    public int getToward() {
        return toward;
    }

    /**
     * ���캯�������ݷ����ʼ���ŵ�λ��
     * @param PlusOrMinus �ŵ�������־
     * @param DoorToward  �ŵķ���
     */
    public Door(boolean PlusOrMinus, int DoorToward) {
        this.PlusOrMinus = PlusOrMinus;
        this.toward = DoorToward;
        
        if (toward == 2 /* UpDoor */) {
            this.Doorx = 209;
            this.Doory = 18;
        } else if (toward == 1 /* DownDoor */) {
            this.Doorx = 209;
            this.Doory = 260;
        } else if (toward == 4 /* LeftDoor */) {
            this.Doorx = 21;
            this.Doory = 131;
        } else if (toward == 3 /* RightDoor */) {
            this.Doorx = 415;
            this.Doory = 131;
        } else if (toward == 0 /* NullDoor */) {
            this.Doorx = -100;
            this.Doory = -100;
        } else {
            System.out.println("Error: Door Direct Wrongly");
        }
    }

    /**
     * ��ȡ�ŵ�ͼ�񳤶�
     * @return ����ͼ�񳤶�
     */
    public int getImagelength() {
        return Imagelength;
    }

    /**
     * ��ȡ�ŵ�ͼ����
     * @return ����ͼ����
     */
    public int getImagewidth() {
        return Imagewidth;
    }

    /**
     * ��ȡ��Ӧ����״̬����ͼ��
     * @param RoomState ����״̬��0: �ر�, 1: �򿪣�
     * @return ���ض�Ӧ�� BufferedImage ����
     */
    public BufferedImage getDoorImage(int RoomState) {
        if (RoomState == 0) {
            if (toward != 0) {
                return ResourceLoader.getInstance().DoorImage.get(toward - 1);
            } else {
                return null;
            }
        } else {
            if (toward != 0) {
                return ResourceLoader.getInstance().DoorImage.get(toward - 1 + 4);
            } else {
                return null;
            }
        }
    }

    /**
     * ��ȡ�ŵ� X ����
     * @return ���� X ����
     */
    public int getDoorX() {
        return Doorx;
    }

    /**
     * ��ȡ�ŵ� Y ����
     * @return ���� Y ����
     */
    public int getDoorY() {
        return Doory;
    }

    /**
     * ��ȡ�ŵĳ���
     * @return ���س���ֵ
     */
    public int getLength() {
        if (toward == 1 || toward == 2) {
            Imagelength = 49;
            return 20;
        } else if (toward == 3 || toward == 4) {
            Imagelength = 33;
            return 35;
        } else {
            System.out.println("Door Direct Wrongly");
            return 0;
        }
    }

    /**
     * ��ȡ�ŵĿ��
     * @return ���ؿ��ֵ
     */
    public int getWidth() {
        if (toward == 1 || toward == 2) {
            Imagewidth = 33;
            return 35;
        } else if (toward == 3 || toward == 4) {
            Imagewidth = 49;
            return 20;
        } else {
            System.out.println("Door Direct Wrongly");
            return 0;
        }
    }

    /**
     * �ж� Isaac �Ƿ����ŷ�������
     * @param RoomState ��ǰ�����״̬��0: �ر�, 1: �򿪣�
     */
    public void DoorIntersactJudge(int RoomState) {
        // ���� Isaac �ľ�������������ײ���
        Rectangle IssacRectangle = new Rectangle(Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, 18, 15);

        if (toward == 1 || toward == 2) { // ���·������
            if (IssacRectangle.intersects(getDoorX() + 15, getDoorY(), getLength(), getWidth())) {
                if (RoomState == 1) { // �Ŵ�ʱ����ͨ��
                    if (toward == 1) { // ���µ���
                        Issac.getInstance().setY(51);
                    } else if (toward == 2) { // ���ϵ���
                        Issac.getInstance().setY(228);
                    }
                    
                    // ���·����Ų��л�����
                    if (PlusOrMinus) {
                        BackGround.getInstance().setRoomNum(BackGround.getInstance().getRoomNum() + 1);
                    } else {
                        BackGround.getInstance().setRoomNum(BackGround.getInstance().getRoomNum() - 1);
                    }
                    BackGround.getInstance().setSwitchRoom(true);
                }
            }
        } else if (toward == 3 || toward == 4) { // ���ҷ������
            if (RoomState == 1) { // �Ŵ�ʱ����ͨ��
                if (IssacRectangle.intersects(getDoorX(), getDoorY() + 15, getLength(), getWidth())) {
                    if (toward == 3) { // ���ҵ���
                        Issac.getInstance().setX(51);
                    } else if (toward == 4) { // �������
                        Issac.getInstance().setX(395);
                    }
                    
                    // ���·����Ų��л�����
                    if (PlusOrMinus) {
                        BackGround.getInstance().setRoomNum(BackGround.getInstance().getRoomNum() + 1);
                    } else {
                        BackGround.getInstance().setRoomNum(BackGround.getInstance().getRoomNum() - 1);
                    }
                    BackGround.getInstance().setSwitchRoom(true);
                }
            }
        }
    }
}
