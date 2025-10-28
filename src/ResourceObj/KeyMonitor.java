package ResourceObj;

import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.util.HashSet;
import java.util.Set;

public class KeyMonitor implements KeyEventDispatcher {
    // 存储当前被按下键的集合，使用Set结构保证唯一性
    private static final Set<Integer> pressedKeys = new HashSet<>();

    /**
     * 检查指定键是否被按下
     * @param keyCode 要检查的按键码
     * @return 如果键被按下返回true，否则返回false
     */
    public static boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    /**
     * 处理键盘事件的方法，根据按键和释放事件更新pressedKeys集合
     * @param e 触发的键盘事件
     * @return 返回false表示不拦截事件，继续传递给其他监听器
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        synchronized (pressedKeys) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                // 添加被按下的键到集合中
                pressedKeys.add(e.getKeyCode());
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                // 从集合中移除被释放的键
                pressedKeys.remove(e.getKeyCode());
            }
        }
        return false; // 不拦截事件，继续传递给其他监听器
    }

    /**
     * 注册当前KeyMonitor实例为键盘事件分发器
     */
    public static void register() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyMonitor());
    }
}
