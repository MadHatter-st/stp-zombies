package ru.bgpu.zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Go implements ActionListener {

    int money = 0;


//    Image hand = new ImageIcon(getClass().getResource("/image/hand.png")).getImage();
    Image fon = new ImageIcon(getClass().getResource("/image/fon1.jpg")).getImage();
    Image GO = new ImageIcon(getClass().getResource("/image/go.png")).getImage();
    Image h = new ImageIcon(getClass().getResource("/image/h.png")).getImage();
    Image d = new ImageIcon(getClass().getResource("/image/d.png")).getImage();
    Zombies zombies;

    Timer timer = new Timer(100, this);
    Font font = new Font(Font.DIALOG, Font.BOLD,30);
    public Go(Zombies zombies){
        this.zombies = zombies;
    }

    public void paint(Graphics g) {
        g.drawImage(GO, (Zombies.Z_WIDTH-GO.getWidth(null))/2, (Zombies.Z_HEIGHT-GO.getHeight(null))/2, null);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("Ваш счет:"+Integer.toString(money), Zombies.Z_WIDTH/2-200, Zombies.Z_HEIGHT-100);
        g.drawString("Нажмите 'Enter' выхода в меню", Zombies.Z_WIDTH/2-200, Zombies.Z_HEIGHT-50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
