import ResourceObj.BackGround;
import ResourceObj.KeyMonitor;
import ResourceObj.ResourceLoader;

import javax.swing.*;
import java.awt.*;


public class GameWin extends JFrame{
    ResourceLoader resourceLoader=ResourceLoader.getInstance();
    public void Load(){
        BackGround.getInstance().getIssac().LoadBodyImage();
        BackGround.getInstance().getIssac().LoadHeadImage();
        resourceLoader.ResourceLoad();


    }
    public void Initialize() {
        add(BackGround.getInstance(), BorderLayout.CENTER);
        setSize(480, 350);
        setTitle("ResourceObj.Issac's Nightmare");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        revalidate();
        repaint();

        // ʹ�� SwingWorker �첽����ͼ����ԴGB
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Load();
                BackGround.getInstance().BackGroundImg();
                return null;
            }

            @Override
            protected void done() {
                // ��������ٰ󶨰��������󽹵�
                SwingUtilities.invokeLater(() -> {
                    BackGround.getInstance().setFocusable(true);
                    BackGround.getInstance().initRoom();
                    boolean focusGained = BackGround.getInstance().requestFocusInWindow();
                    System.out.println("Request focus in window: " + focusGained);
                    BackGround.getInstance().GameLoop();
                });
            }
        }.execute();
    }




    public static void main(String[] args) {
        KeyMonitor.register();
        GameWin win=new GameWin();
        win.Initialize();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(
                "focusOwner", e -> System.out.println("��ǰ����������: " + e.getNewValue())
        );
    }
}

