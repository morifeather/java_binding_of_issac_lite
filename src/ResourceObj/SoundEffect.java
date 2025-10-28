
package ResourceObj;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SoundEffect {

    // �洢ÿ����Ч�̼߳���ֹͣ��־
    private static final Map<String, SoundPlayback> activeSounds = new HashMap<>();
    public static void playSound(String soundKey, String relativePathInResources) {
        // ʹ���������������Դ��
        InputStream inputStream = SoundEffect.class.getClassLoader().getResourceAsStream(relativePathInResources);

        if (inputStream == null) {
            System.err.println("Resource not found: " + relativePathInResources);
            return;
        }

        // �����µĲ�������
        SoundPlayback playback = new SoundPlayback(soundKey, inputStream);
        Thread thread = new Thread(playback);
        playback.thread = thread;

        activeSounds.put(soundKey, playback);
        thread.start();
    }


    /**
     * ָֹͣ�� key ����Ч
     */
    public static void stopSound(String soundKey) {
        SoundPlayback playback = activeSounds.get(soundKey);
        if (playback != null) {
            playback.stopPlayback();
            activeSounds.remove(soundKey);
        }
    }

    /**
     * ֹͣ�������ڲ��ŵ���Ч
     */
    public static void stopAllSounds() {
        for (String key : activeSounds.keySet()) {
            stopSound(key);
        }
        activeSounds.clear();
    }

    /**
     * �ڲ��ࣺ��������Ч�Ĳ��ź�ֹͣ
     */
    private static class SoundPlayback implements Runnable {
        private final String soundKey;
        private final InputStream audioStream;
        private volatile boolean playing = true;
        private Thread thread;

        public SoundPlayback(String soundKey, InputStream audioStream) {
            this.soundKey = soundKey;
            this.audioStream = audioStream;
        }

        public void stopPlayback() {
            playing = false;
        }

        @Override
        public void run() {
            try {
                Player player = new Player(audioStream);

                while (playing && player.play(1)) {
                    // ÿ֡����Ƿ�ֹͣ����
                }

                player.close();
                System.out.println("Stopped: " + soundKey);
            } catch (Exception e) {
                System.err.println("Error playing MP3 file: " + soundKey);
                e.printStackTrace();
            } finally {
                activeSounds.remove(soundKey);
            }
        }
    }
}


