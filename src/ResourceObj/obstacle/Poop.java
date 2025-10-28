package ResourceObj.obstacle;

import java.awt.image.BufferedImage;


public class Poop extends Obstacle {
    int a=23;

    public int getA(){return a;}

    // 构造函数，设置可破坏、初始 HP 和图片
    public Poop() {
        this.Breakable = true;  // 设置为可破坏
        this.Hp = 5;            // 初始血量为 5
    }

    @Override
    public BufferedImage getImage(int i) {
        // 根据当前 Hp 获取对应的图片
        if (resourceloader != null && resourceloader.PoopImage != null && Hp > 0 && Hp <= resourceloader.PoopImage.size()) {
            return resourceloader.PoopImage.get(Hp - 1); // 图片索引从 0 开始
        }
        return null;
    }

    // 子弹击中时调用此方法
    public void onBulletHit() {
        if (Breakable && Hp > 0) {
            Hp--;
            if (Hp == 1) {
                a=0; // 血量为 0 后障碍消失
            }
        }
    }
}