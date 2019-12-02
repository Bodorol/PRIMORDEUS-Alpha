
import javax.swing.*;
import java.awt.event.*;

public class guiMainMenu extends JFrame{
    private JButton startGame;
    private JButton optionsButton;
    private JButton exitGameButton;
    private JButton creditsButton;
    private JButton saveGameButton;
    private JPanel rootPanel;
    private JLabel mainMenuImage;
    private JLabel versionCopy;
    private int counter=0;
    private String versionInfo = " Alpha Version 1.1.0  Copyright © 2019 (Ian Anders, Brenner Lattin). All Rights Reserved.";

    public guiMainMenu() {
        Global glb = new Global();
        add(rootPanel);
        setTitle("PRIMORDEUS");
        setSize(693,670);
        revalidate();
        setVisible(true);
        setImage("bin/mainMenuImage.png");
        startGame.setBorder(null);
        optionsButton.setBorder(null);
        exitGameButton.setBorder(null);
        creditsButton.setBorder(null);
        saveGameButton.setBorder(null);
        startGame.setFocusPainted(false);
        optionsButton.setFocusPainted(false);
        exitGameButton.setFocusPainted(false);
        creditsButton.setFocusPainted(false);
        saveGameButton.setFocusPainted(false);
        startGame.setPressedIcon(new ImageIcon());

        versionCopy.setText(versionInfo);

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.buttonClicked="startGame";
                glb.playSoundEasy("click.wav");
            }
        });

        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.buttonClicked="exitGame";
                glb.playSoundEasy("click.wav");
                System.exit(0);
            }
        });

        rootPanel.addComponentListener(new ComponentAdapter() {
        });
        mainMenuImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getXOnScreen()<50&e.getYOnScreen()<50)
                {
                    if (counter==5)
                    {
                        Global.overridesActive=true;
                        versionCopy.setText(versionInfo+" OVERRIDES ENABLED");
                        counter++;
                        revalidate();
                    }
                    else if (counter>5)
                    {
                        counter=0;
                        Global.overridesActive=false;
                        versionCopy.setText(versionInfo);
                        revalidate();
                    }
                    else {counter++;}
                }
            }
        });
        rootPanel.addComponentListener(new ComponentAdapter() {
        });
    }
    public void setStartGameButton(String x)
    {
        startGame.setText(x);
    }
    public void setImage(String x)
    {
        mainMenuImage.setIcon(new ImageIcon(x));
    }
}
