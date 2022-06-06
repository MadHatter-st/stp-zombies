package ru.bgpu.zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pause implements ActionListener {

    Image hand = new ImageIcon(getClass().getResource("/image/hand.png")).getImage();
    Image fon = new ImageIcon(getClass().getResource("/image/fon0.jpg")).getImage();
    Image fon1 = new ImageIcon(getClass().getResource("/image/fon1.jpg")).getImage();
    public final static int pozitions[] = {150, 250};
    int pIndex = 0;

    int back = 0;
    Zombies zombies;

    String options[] = {"Continue","Exit to menu"};

    Timer timer = new Timer(100, this);

    public Pause(Zombies zombies) {
        this.zombies = zombies;
    }

    public void paint(Graphics g) {
        if(back==0){
            g.drawImage(fon, 0, 0, null);
        }else {
            g.drawImage(fon1, 0, 0, null);
        }
        g.setColor(Color.RED);
        g.setFont(new Font("name", Font.ITALIC, 50));
        g.drawString("Game in a pause", Zombies.Z_WIDTH / 2 - 200, Zombies.Z_HEIGHT / 2 - 200);
        g.drawImage(hand, 50, pozitions[pIndex]-50, null);
        g.drawString(options[0], 150, pozitions[0]);
        g.drawString(options[1], 150, pozitions[1]);

    }

    public void choise(){
        switch (pIndex){
            case 0:
                Zombies.state = Zombies.STATE.PLAY;
                zombies.timer.start();
                break;
            case 1:
                pIndex = 0;
                Zombies.state = Zombies.STATE.MENU;
                break;
        }
    }
    public void up() {
        if(--pIndex < 0) pIndex = 0;
    }

    public void down() {
        if(++pIndex >= pozitions.length) pIndex = pozitions.length-1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

