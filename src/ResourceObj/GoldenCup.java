package ResourceObj;

import java.awt.image.BufferedImage;

/**
 * 金色杯子资源对象类，采用单例模式管理实例
 */
public class GoldenCup {
    // 图像基础属性（坐标与尺寸）
    protected int ImageX = 218, ImageY = 122, ImageLength = 32, ImageWidth = 64;
    
    // 实际渲染尺寸
    int length, width;
    
    // 图像资源
    private BufferedImage GoldenCupImage;

    // 单例实例
    private static GoldenCup instance = new GoldenCup();

    /**
     * 获取单例实例
     * @return GoldenCup实例
     */
    public static GoldenCup getInstance() {
        return instance;
    }
    
    /*--------------------------------------------------------------------------*/
    /* 设置器方法 */
    
    /**
     * 设置实际长度
     * @param length 要设置的长度
     */
    public void setLength(int length) {
        this.length = length;
    }
    
    /**
     * 设置实际宽度
     * @param width 要设置的宽度
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * 设置图像资源
     * @param GoldenCupImage 要设置的图像
     */
    public void setImage(BufferedImage GoldenCupImage) {
        this.GoldenCupImage = GoldenCupImage;
    }
    /*--------------------------------------------------------------------------*/
    
    /* 访问器方法 */
    
    /**
     * 获取图像资源
     * @return BufferedImage类型的图像资源
     */
    public BufferedImage getImage() {
        return GoldenCupImage;
    }

    /**
     * 获取图像基础长度
     * @return 图像的基础长度值
     */
    public int getImageLength() {
        return ImageLength;
    }
    
    /**
     * 获取图像X坐标
     * @return 图像的X坐标位置
     */
    public int getImageX() {
        return ImageX;
    }
    
    /**
     * 获取图像Y坐标
     * @return 图像的Y坐标位置
     */
    public int getImageY() {
        return ImageY;
    }

    /**
     * 获取图像基础宽度
     * @return 图像的基础宽度值
     */
    public int getImageWidth() {
        return ImageWidth;
    }
    
    /**
     * 获取实际长度
     * @return 实际渲染长度
     */
    public int getLength() {
        return length;
    }
    
    /**
     * 获取实际宽度
     * @return 实际渲染宽度
     */
    public int getWidth() {
        return width;
    }
}