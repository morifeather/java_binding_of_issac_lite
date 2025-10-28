package ResourceObj;

import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.util.HashSet;
import java.util.Set;

public class KeyMonitor implements KeyEventDispatcher {
    // �洢��ǰ�����¼��ļ��ϣ�ʹ��Set�ṹ��֤Ψһ��
    private static final Set<Integer> pressedKeys = new HashSet<>();

    /**
     * ���ָ�����Ƿ񱻰���
     * @param keyCode Ҫ���İ�����
     * @return ����������·���true�����򷵻�false
     */
    public static boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    /**
     * ��������¼��ķ��������ݰ������ͷ��¼�����pressedKeys����
     * @param e �����ļ����¼�
     * @return ����false��ʾ�������¼����������ݸ�����������
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        synchronized (pressedKeys) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                // ��ӱ����µļ���������
                pressedKeys.add(e.getKeyCode());
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                // �Ӽ������Ƴ����ͷŵļ�
                pressedKeys.remove(e.getKeyCode());
            }
        }
        return false; // �������¼����������ݸ�����������
    }

    /**
     * ע�ᵱǰKeyMonitorʵ��Ϊ�����¼��ַ���
     */
    public static void register() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyMonitor());
    }
}
