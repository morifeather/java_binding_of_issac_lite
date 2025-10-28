package ResourceObj.RoomObj;

import ResourceObj.Door;
import ResourceObj.Enemies.Enemy;
import ResourceObj.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.Random;

public class RoomRandomSystem {
    Random randit = new Random();

    /**
     * 根据上一个房间的门的方向随机生成下一个房间，并设置新的门的方向
     *
     * @param doors           当前房间的门列表
     * @param obstacles       当前房间的障碍物列表
     * @param enemies         当前房间的敌人列表
     * @param LastRoomPlusDoor  上一个房间的正向门方向（如上、右）
     * @param LastRoomMinusDoor 上一个房间的负向门方向（如下、左）
     * @return                  生成的具体房间实例
     */
    public Room RandomRoom(ArrayList<Door> doors, ArrayList<Obstacle> obstacles, ArrayList<Enemy> enemies,
                           int LastRoomPlusDoor, int LastRoomMinusDoor) {
        int i = new Random().nextInt(7) + 1; // 随机选择一个房间类型（1-7）
        int PlusDoor = 0, MinusDoor = 0; // 初始化新房间的门方向

        // 根据上一个房间的正向门方向来决定新房间的门方向
        if (LastRoomPlusDoor == 1) { // 如果上一个房间的正向门是向下
            MinusDoor = 2; // 新房间的负向门设为向上
            PlusDoor = 2; // 新房间的正向门也先设为向上，后续进行调整
            if (LastRoomMinusDoor == 4) { // 如果上一个房间的负向门是向左
                while (PlusDoor == 2 || PlusDoor == 4) { // 确保新房间的正向门不是向上或向左
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            } else if (LastRoomMinusDoor == 3) { // 如果上一个房间的负向门是向右
                while (PlusDoor == 2 || PlusDoor == 3) { // 确保新房间的正向门不是向上或向右
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            } else { // 如果上一个房间的负向门不是向左也不是向右
                while (PlusDoor == 2) { // 确保新房间的正向门不是向上
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            }

        } else if (LastRoomPlusDoor == 2) { // 如果上一个房间的正向门是向上
            MinusDoor = 1; // 新房间的负向门设为向下
            PlusDoor = 1; // 新房间的正向门也先设为向下，后续进行调整
            if (LastRoomMinusDoor == 4) { // 如果上一个房间的负向门是向左
                while (PlusDoor == 1 || PlusDoor == 4) { // 确保新房间的正向门不是向下或向左
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            } else if (LastRoomMinusDoor == 3) { // 如果上一个房间的负向门是向右
                while (PlusDoor == 1 || PlusDoor == 3) { // 确保新房间的正向门不是向下或向右
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            } else { // 如果上一个房间的负向门不是向左也不是向右
                while (PlusDoor == 1) { // 确保新房间的正向门不是向下
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            }

        } else if (LastRoomPlusDoor == 3) { // 如果上一个房间的正向门是向右
            MinusDoor = 4; // 新房间的负向门设为向左
            PlusDoor = 4; // 新房间的正向门也先设为向左，后续进行调整
            if (LastRoomMinusDoor == 1) { // 如果上一个房间的负向门是向下
                while (PlusDoor == 1 || PlusDoor == 4) { // 确保新房间的正向门不是向下或向左
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            } else if (LastRoomMinusDoor == 2) { // 如果上一个房间的负向门是向上
                while (PlusDoor == 2 || PlusDoor == 4) { // 确保新房间的正向门不是向上或向左
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            } else { // 如果上一个房间的负向门不是向下也不是向上
                while (PlusDoor == 4) { // 确保新房间的正向门不是向左
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            }

        } else if (LastRoomPlusDoor == 4) { // 如果上一个房间的正向门是向左
            MinusDoor = 3; // 新房间的负向门设为向右
            PlusDoor = 3; // 新房间的正向门也先设为向右，后续进行调整
            if (LastRoomMinusDoor == 1) { // 如果上一个房间的负向门是向下
                while (PlusDoor == 1 || PlusDoor == 3) { // 确保新房间的正向门不是向下或向右
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            } else if (LastRoomMinusDoor == 2) { // 如果上一个房间的负向门是向上
                while (PlusDoor == 2 || PlusDoor == 3) { // 确保新房间的正向门不是向上或向右
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            } else { // 如果上一个房间的负向门不是向下也不是向上
                while (PlusDoor == 3) { // 确保新房间的正向门不是向右
                    PlusDoor = randit.nextInt(4) + 1; // 重新随机选择一个方向
                }
            }
        }

        // 根据随机数选择具体的房间类型
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
