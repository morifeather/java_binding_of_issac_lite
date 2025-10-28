package ResourceObj;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Door 类用于表示游戏中的门对象
 * 包含门的位置、方向、尺寸等属性以及与 Isaac 角色的交互逻辑
 */
public class Door {
    // 门的坐标
    private int Doorx, Doory;
    // 门的图像长度和宽度
    private int Imagelength, Imagewidth;
    // 门的方向（1: 下门, 2: 上门, 3: 右门, 4: 左门）
    private int toward;
    // 门的增减标志（true 表示增加房间号，false 表示减少房间号）
    private boolean PlusOrMinus; /* True 表示加号方向，False 表示减号方向 */

    /**
     * 获取门的方向
     * @return 返回门的方向值
     */
    public int getToward() {
        return toward;
    }

    /**
     * 构造函数，根据方向初始化门的位置
     * @param PlusOrMinus 门的增减标志
     * @param DoorToward  门的方向
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
     * 获取门的图像长度
     * @return 返回图像长度
     */
    public int getImagelength() {
        return Imagelength;
    }

    /**
     * 获取门的图像宽度
     * @return 返回图像宽度
     */
    public int getImagewidth() {
        return Imagewidth;
    }

    /**
     * 获取对应房间状态的门图像
     * @param RoomState 房间状态（0: 关闭, 1: 打开）
     * @return 返回对应的 BufferedImage 对象
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
     * 获取门的 X 坐标
     * @return 返回 X 坐标
     */
    public int getDoorX() {
        return Doorx;
    }

    /**
     * 获取门的 Y 坐标
     * @return 返回 Y 坐标
     */
    public int getDoorY() {
        return Doory;
    }

    /**
     * 获取门的长度
     * @return 返回长度值
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
     * 获取门的宽度
     * @return 返回宽度值
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
     * 判断 Isaac 是否与门发生交互
     * @param RoomState 当前房间的状态（0: 关闭, 1: 打开）
     */
    public void DoorIntersactJudge(int RoomState) {
        // 创建 Isaac 的矩形区域用于碰撞检测
        Rectangle IssacRectangle = new Rectangle(Issac.getInstance().getX() + 5, Issac.getInstance().getY() + 18, 18, 15);

        if (toward == 1 || toward == 2) { // 上下方向的门
            if (IssacRectangle.intersects(getDoorX() + 15, getDoorY(), getLength(), getWidth())) {
                if (RoomState == 1) { // 门打开时允许通过
                    if (toward == 1) { // 向下的门
                        Issac.getInstance().setY(51);
                    } else if (toward == 2) { // 向上的门
                        Issac.getInstance().setY(228);
                    }
                    
                    // 更新房间编号并切换房间
                    if (PlusOrMinus) {
                        BackGround.getInstance().setRoomNum(BackGround.getInstance().getRoomNum() + 1);
                    } else {
                        BackGround.getInstance().setRoomNum(BackGround.getInstance().getRoomNum() - 1);
                    }
                    BackGround.getInstance().setSwitchRoom(true);
                }
            }
        } else if (toward == 3 || toward == 4) { // 左右方向的门
            if (RoomState == 1) { // 门打开时允许通过
                if (IssacRectangle.intersects(getDoorX(), getDoorY() + 15, getLength(), getWidth())) {
                    if (toward == 3) { // 向右的门
                        Issac.getInstance().setX(51);
                    } else if (toward == 4) { // 向左的门
                        Issac.getInstance().setX(395);
                    }
                    
                    // 更新房间编号并切换房间
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
