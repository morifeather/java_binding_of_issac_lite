package ResourceObj.obstacle;

import ResourceObj.ResourceLoader;

import java.awt.image.BufferedImage;

public class Stone extends Obstacle {
    // 使用父类中已有的resourceloader字段和a字段
    
    //----------------------------------------------------------------------------//
    // 构造函数或初始化方法可以根据需要添加，这里保持默认构造函数
    
    /**
     * 设置X坐标
     * @param Rectx 网格X坐标
     */
    @Override
    public void setX(int Rectx) {
        this.x = 50 + 23 * Rectx;
    }

    /**
     * 设置Y坐标
     * @param Recty 网格Y坐标
     */
    @Override
    public void setY(int Recty) {
        this.y = 50 + 23 * Recty;
    }

    /**
     * 初始化方法，设置位置和图像
     * @param x 网格X坐标
     * @param y 网格Y坐标
     * @param i 图像索引
     */
    @Override
    public void Initialize(int x, int y, int i) {
        super.Initialize(x, y, i);  // 调用父类初始化方法
        this.Image = resourceloader.StoneImage.get(i);  // 根据参数设置图像
    }

    /**
     * 设置资源加载器（用于多态加载不同资源）
     * @param resourceloader 新的资源加载器
     */
    public void setResourceloader(ResourceLoader resourceloader) {
        this.resourceloader = resourceloader;
    }

    /**
     * 设置障碍物图像
     * @param i 图像索引
     */
    @Override
    public void setObstacleImage(int i) {
        this.Image = resourceloader.StoneImage.get(i);
    }
    //----------------------------------------------------------------------------//

    /**
     * 获取X坐标
     * @return X坐标值
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * 获取Y坐标
     * @return Y坐标值
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * 获取对象尺寸
     * @return 尺寸值a
     */
    @Override
    public int getA() {
        return a;
    }

    /**
     * 获取当前图像
     * @param i 图像索引（此处未使用，保留为了接口一致性）
     * @return 当前图像
     */
    @Override
    public BufferedImage getImage(int i) {
        return Image;
    }
}