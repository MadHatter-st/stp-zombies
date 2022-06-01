package ru.bgpu.zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    Image player = new ImageIcon(getClass().getResource("/image/player.png")).getImage();
    Image fon = new ImageIcon(getClass().getResource("/image/fon.jpg")).getImage();
    public final static int pozitions[] = {150, 250, 350};
    int pIndex = 0;
    Zombies zombies;

    String options[] = {"Play","Comming soon","Exit"};

    Timer timer = new Timer(100, this);

    public Menu(Zombies zombies) {
        this.zombies = zombies;
    }

    public void paint(Graphics g) {
        g.drawImage(fon, 0, 0, null);
        g.setColor(Color.RED);
        g.setFont(new Font("Bauhaus 93", Font.ITALIC, 50));
        g.drawString("Zombies ATACK!!!", Zombies.Z_WIDTH / 2 - 200, Zombies.Z_HEIGHT / 2 - 200);
        g.drawImage(player, 50, pozitions[pIndex], null);
        g.drawString(options[0], 100, pozitions[0]);
        g.drawString(options[1], 100, pozitions[1]);
        g.drawString(options[2], 100, pozitions[2]);


    }

    public void choise(){
        switch (pIndex){
            case 0:
                Zombies.state = Zombies.STATE.PLAY;
                zombies.timer.restart();
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
