
package ru.bgpu.zombies;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Zombies extends JFrame implements ActionListener, KeyListener {
    
    public static final int Z_WIDTH  = 800;
    public static final int Z_HEIGHT = 500;

//    Image fon;
    
    int maxCount = 3;
    int maxSpeed = 15;
    int interval = 10;
    boolean flock = false;
    int iteration = 0;
    boolean end = false;
    boolean stop = false;

    ArrayList<Zombi> zombis = new ArrayList<>();

    Timer timer = new Timer(100, this);

//    boolean start_timer = true;

    public enum STATE{MENU,PLAY,GO,PAUSE};
    public static STATE state = STATE.MENU;
    Player player = new Player(this);
    Live  live = new Live();
    
    BufferedImage fon = new BufferedImage(Z_WIDTH, Z_HEIGHT, BufferedImage.TYPE_INT_RGB);

    JPanel fonPanel = new JPanel() {
        @Override
        public void paint(Graphics g) {
            if (!state.equals(STATE.GO)) {
                if (state.equals(STATE.PLAY)) {
                    if (!stop) {
                        g.drawImage(fon, 0, 0, null);
                        player.paint(g);
                        for (int i = 0; i < zombis.size(); i++) {
                            if (zombis.get(i).x < 0) {
                                zombis.remove(i);
                                live.kill();
                                if (live.live == 0) {
                                    end = true;
                                    live.live = 5;
                                    zombis.clear();
                                    state = STATE.GO;
                                }
                                i--;
                                continue;
                            }
                            if (live.live != 0) {
                                zombis.get(i).paint(g);
                            }
                        }
                        live.paint(g);
                    } else {
                        timer.stop();
                    }
                } else {
                    g.drawImage(fon, 0, 0, null);
                    g.setColor(Color.RED);
                    g.setFont(new Font("Bauhaus 93", Font.ITALIC, 50));
                    g.drawString("Press 'Enter' to start", Zombies.Z_WIDTH / 2 - 200, Zombies.Z_HEIGHT / 2);
                }
            }
            else{
                timer.stop();
            }
        }
    };


    public Zombies(int a) {
        setTitle("Zombies!");
        timer.start();
        fon.createGraphics().drawImage(new ImageIcon(getClass().getResource("/image/fon.jpg")).getImage(),0,0,null);
        setContentPane(fonPanel);
        fonPanel.setPreferredSize(new Dimension(Z_WIDTH, Z_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        pack();

    }

    public static void main(String[] args) {
        Zombies zombies = new Zombies(2);
        zombies.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        zombies.setLocation(((width-Z_WIDTH)/2), ((height-Z_HEIGHT)/2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(state.equals(STATE.PLAY)) {
            fonPanel.repaint();
            if (iteration++ > interval) {
                Random r = new Random();
                int count = r.nextInt(maxCount);
                for (int i = 0; i < count; i++) {
                    Zombi z = new Zombi(this, Z_WIDTH);
                    z.spid = 10 + r.nextInt(maxSpeed);
                    z.pIndex = r.nextInt(3);
                    zombis.add(z);
                }
                iteration = 0;
            }
        }
        else if(state.equals(STATE.MENU)){
            timer.stop();
        }
        
    }
    
    
    public void fire(int line) {
        int index = -1;
        for(int i=0; i<zombis.size(); i++) {
            if(zombis.get(i).x > 50 && !zombis.get(i).kill && zombis.get(i).pIndex == line) {
                if(index == -1) {
                    index = i;
                } else if(zombis.get(i).x < zombis.get(index).x) {
                    index = i;
                }
            }
        }
        if(index != -1) {
            zombis.get(index).fire();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.up();
                break;
            case KeyEvent.VK_DOWN:
                player.down();
                break;
            case KeyEvent.VK_SPACE:
                if(flock) break;
                flock = true;
                player.fire();
                fire(player.pIndex);
                break;
            case KeyEvent.VK_S:
                player.ult();
                break;
            case KeyEvent.VK_ESCAPE:
                player.pause();
                break;
            case KeyEvent.VK_ENTER:
                state=STATE.PLAY;
                timer.start();
                if(end){
                    live.go=false;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                flock = false;
        }
    }
    
    
    public static synchronized void playSound(final String url) {
    new Thread(new Runnable() {
    // The wrapper thread is unnecessary, unless it blocks on the
    // Clip finishing; see comments.
      @Override
      public void run() {
        try {
          Clip clip = AudioSystem.getClip();
          AudioInputStream inputStream = AudioSystem.getAudioInputStream( new BufferedInputStream(
            Zombies.class.getResourceAsStream("/sounds/" + url)));
//          InputStream bufferedIn = new BufferedInputStream(audioSrc);
//AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
          clip.open(inputStream);
          clip.start(); 
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
    }).start();
  }
}
