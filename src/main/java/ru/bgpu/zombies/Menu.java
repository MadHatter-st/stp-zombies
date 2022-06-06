package ru.bgpu.zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    Zombies zombies;
    public Menu(Zombies zombies) {
        this.zombies = zombies;
    }
    int back = 0;
    Image hand = new ImageIcon(getClass().getResource("/image/hand.png")).getImage();
    Image fon = new ImageIcon(getClass().getResource("/image/fon"+0+".jpg")).getImage();
    Image fon1 = new ImageIcon(getClass().getResource("/image/fon"+1+".jpg")).getImage();
    public final static int pozitions[] = {150, 250, 350};
    int pIndex = 0;

    String options[] = {"Play","Prizes","Exit"};

    Timer timer = new Timer(100, this);

    public void paint(Graphics g) {
        if(back==0){
            g.drawImage(fon, 0, 0, null);
        }else {
            g.drawImage(fon1, 0, 0, null);
        }
        g.setColor(Color.RED);
        g.setFont(new Font("namd", Font.ITALIC, 50));
        g.drawString("Zombies ATACK!!!", Zombies.Z_WIDTH / 2 - 200, Zombies.Z_HEIGHT / 2 - 200);
        g.drawImage(hand, 50, pozitions[pIndex]-50, null);
        g.drawString(options[0], 150, pozitions[0]);
        g.drawString(options[1], 150, pozitions[1]);
        g.drawString(options[2], 150, pozitions[2]);


    }

    public void choise(){
        switch (pIndex){
            case 0:
                zombies.timer.restart();
                zombies.zombis.clear();
                zombies.player.pIndex = 0;
                zombies.live.live = 5;
                zombies.live.charge = 0;
                zombies.live.money = 0;
                Zombies.state = Zombies.STATE.PLAY;
                break;
            case 1:
                Zombies.state = Zombies.STATE.PRIZES;
                break;
            case 2:
                System.exit(0);
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
