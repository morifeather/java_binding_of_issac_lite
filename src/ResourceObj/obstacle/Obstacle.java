package ResourceObj.obstacle;

import ResourceObj.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle {
    // 基础属性字段
    protected int x, y, a = 23; // 坐标和固定尺寸
    protected int Hp = 1; // 生命值
    protected ResourceLoader resourceloader = ResourceLoader.getInstance(); // 资源加载器单例
    protected BufferedImage Image; // 图像资源
    protected boolean obstacleExist = true; // 障碍物存在状态
    protected boolean Breakable = false; // 是否可破坏
    //----------------------------------------------------------------------------//

    /**
     * 设置资源加载器（用于动态更换资源）
     */
    public void setResourceloader() {
        this.resourceloader = ResourceLoader.getInstance();
    }

    /**
     * 设置障碍物图像
     * @param i 图像索引
     */
    public void setObstacleImage(int i) {
        this.Image = resourceloader.StoneImage.get(i);
    }

    /**
     * 设置X坐标
     * @param Rectx 网格X坐标
     */
    public void setX(int Rectx) {
        this.x = 50 + 23 * Rectx;
    }

    /**
     * 设置Y坐标
     * @param Recty 网格Y坐标
     */
    public void setY(int Recty) {
        this.y = 50 + 23 * Recty;
    }

    /**
     * 设置生命值
     * @param Hp 新的生命值
     */
    public void setHp(int Hp) {
        this.Hp = Hp;
    }

    /**
     * 设置可破坏性
     * @param Breakable 是否可破坏
     */
    public void setBreakable(boolean Breakable) {
        this.Breakable = Breakable;
    }

    /**
     * 初始化方法
     * @param x 网格X坐标
     * @param y 网格Y坐标
     * @param i 图像索引
     */
    public void Initialize(int x, int y, int i) {
        this.x = 50 + 23 * x;
        this.y = 50 + 23 * y;
        this.Image = resourceloader.StoneImage.get(i);
    }
    //----------------------------------------------------------------------------//

    /**
     * 获取当前图像
     * @param i 图像索引（未使用，可能需要优化）
     * @return 当前图像
     */
    public BufferedImage getImage(int i) {
        return Image;
    }

    /**
     * 获取可破坏状态
     * @return 是否可破坏
     */
    public boolean getBreakable() {
        return Breakable;
    }

    /**
     * 获取障碍物存在状态
     * @return 是否存在
     */
    public boolean getObstacleExist() {
        return obstacleExist;
    }

    /**
     * 获取X坐标
     * @return X坐标
     */
    public int getX() {
        return x;
    }

    /**
     * 获取Y坐标
     * @return Y坐标
     */
    public int getY() {
        return y;
    }

    /**
     * 获取尺寸值a
     * @return 尺寸值
     */
    public int getA() {
        return a;
    }

    /**
     * 获取当前生命值
     * @return 生命值
     */
    public int getHp() {
        return Hp;
    }
}