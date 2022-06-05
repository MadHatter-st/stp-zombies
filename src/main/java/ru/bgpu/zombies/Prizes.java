package ru.bgpu.zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Prizes implements ActionListener {

    public int globalMoney;

    Image hand = new ImageIcon(getClass().getResource("/image/hand.png")).getImage();
    Image fon = new ImageIcon(getClass().getResource("/image/fon1.jpg")).getImage();

    public final static int pozitions[] = {150, 250, 350};

    int pIndex = 0;

    Zombies zombies;

    String options[] = {"Player skin: 500$","New background: 1000$","Return to the menu"};

    Timer timer = new Timer(100, this);

    public Prizes(Zombies zombies){
        this.zombies = zombies;
    }

    public void paint(Graphics g) {
        g.drawImage(fon, 0, 0, null);
        g.setColor(Color.RED);
        g.setFont(new Font("name", Font.ITALIC, 50));
        g.drawString("Prizes", Zombies.Z_WIDTH / 2 - 200, Zombies.Z_HEIGHT / 2 - 200);
        g.setColor(Color.GREEN);
        g.drawString(Integer.toString(globalMoney), Zombies.Z_WIDTH-150, 37);
        g.drawImage(hand, 50, pozitions[pIndex]-50, null);
        g.drawString(options[0], 150, pozitions[0]);
        g.drawString(options[1], 150, pozitions[1]);
        g.drawString(options[2], 150, pozitions[2]);

    }

    public void choise(){
        switch (pIndex){
            case 0:

                break;
            case 1:

                break;
            case 2:
                Zombies.state = Zombies.STATE.MENU;
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
