package ru.bgpu.zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Prizes implements ActionListener {

    public int globalMoney = 0;
    int Pskin = 0;
    int Pback = 0;
    Image hand = new ImageIcon(getClass().getResource("/image/hand.png")).getImage();
    Image fon = new ImageIcon(getClass().getResource("/image/fon"+0+".jpg")).getImage();
    Image fon1 = new ImageIcon(getClass().getResource("/image/fon"+1+".jpg")).getImage();

    public final static int pozitions[] = {150, 250, 450};

    boolean skin = false;
    boolean back = false;

    int pIndex = 0;

    int yIndex = 0;

    Zombies zombies;
    String options[] = {"Player skin: 1000$","New background: 1500$","Return to the menu"};
    String options1[][] = {{"Player skin(1)","Background(1)"},
                           {"Player skin(2)","Background(2)"}};

    Timer timer = new Timer(100, this);

    public Prizes(Zombies zombies){
        this.zombies = zombies;
    }

    public void paint(Graphics g) {
        if(Pback==0){
            g.drawImage(fon, 0, 0, null);
        }else {
            g.drawImage(fon1, 0, 0, null);
        }
        g.setColor(Color.RED);
        g.setFont(new Font("name", Font.ITALIC, 50));
        g.drawString("Prizes", Zombies.Z_WIDTH / 2 - 200, Zombies.Z_HEIGHT / 2 - 200);
        g.setColor(Color.GREEN);
        g.drawString(Integer.toString(globalMoney), Zombies.Z_WIDTH-150, 37);
        g.drawImage(hand, 50, pozitions[pIndex]-50, null);
        if(!skin){
            g.setColor(Color.RED);
            g.drawString(options[0], 150, pozitions[0]);
        }
        else {
            g.setColor(Color.GREEN);
            g.drawString(options1[Pskin][0], 150, pozitions[0]);
        }
        if(!back){
            g.setColor(Color.RED);
            g.drawString(options[1], 150, pozitions[1]);
        }
        else {
            g.setColor(Color.GREEN);
            g.drawString(options1[Pback][1], 150, pozitions[1]);
        }
        g.setColor(Color.WHITE);
        g.drawString(options[2], 150, pozitions[2]);
    }

    public void choise(){
        switch (pIndex){
            case 0:
                if(globalMoney>=1000&&!skin){
                    globalMoney-=1000;
                    skin = true;
                }else {
                    if(skin){
                    Pskin = Pskin==0?1:0;
                    zombies.Player = Pskin;
                    }
                }
                break;
            case 1:
                if(globalMoney>=1500&&!back){
                    globalMoney-=1500;
                    back = true;
                }else {
                    if(back){
                        Pback = Pback==0?1:0;
                        zombies.stage = Pback;
                    }
                }
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

//    public void left() {
//        if(--yIndex < 0) yIndex = 0;
//    }
//    public void right() {
//        if(++yIndex >= Pskin.length) yIndex = Pskin.length-1;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
