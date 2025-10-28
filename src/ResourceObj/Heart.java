package ResourceObj;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Heart {
    // 心脏对象的位置和尺寸属性
    private int x, y, length = 25, width = 16;
    // 图像索引，用于从资源加载器中获取对应的图像
    private int ImageNum;

    // 构造函数，初始化位置和图像索引
    public Heart(int x, int y, int ImageNum) {
        this.x = x;
        this.y = y;
        this.ImageNum = ImageNum;
    }

    // 获取心脏图像的宽度
    public int getWidth() {
        return width;
    }

    // 获取心脏图像的高度
    public int getLength() {
        return length;
    }

    // 获取心脏对象的x坐标
    public int getX() {
        return x;
    }

    // 获取心脏对象的y坐标
    public int getY() {
        return y;
    }

    // 从资源加载器中获取对应索引的心脏图像
    public BufferedImage getHeartImage() {
        return ResourceLoader.getInstance().HeartImage.get(ImageNum);
    }

    /**
     * 此方法原本用于将角色的生命值可视化为多个心形图标
     * 目前被注释掉，可能在需要时启用
     */
    /*public void HpVisualize(ArrayList<Heart> hearts, boolean HeartSwitch) {
        if (HeartSwitch) {
            // 清空当前的心形列表
            Iterator<Heart> iterator = hearts.iterator();
            while (iterator.hasNext()) {
                Heart heart = iterator.next();
                hearts.remove(heart);
            }

            // 根据角色当前生命值计算需要显示的心形数量
            int i, num = Issac.getInstance().getHp() / 2;
            int remainder = Issac.getInstance().getHp() % 2;

            // 添加完整心形
            for (i = 1; i <= num; i++) {
                hearts.add(new Heart(i * 25, 20, 1));
            }

            // 如果有余数，添加半爱心
            if (remainder == 1) {
                hearts.add(new Heart(i * 25, 20, 0));
            }

            // 关闭更新开关
            Issac.getInstance().setHeartSwitch(false);
        }
    }*/
}
