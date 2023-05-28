package firstProject;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("The first");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
        gamePanel.startGameThread();
    }


}
