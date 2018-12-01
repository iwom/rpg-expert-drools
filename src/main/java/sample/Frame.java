package sample;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public final class Frame extends JFrame {
    public static JFrame frame;

    public static void init(){
        frame = new JFrame();
        frame.setUndecorated( true );
        frame.setVisible( true );
        frame.setLocationRelativeTo( null );
    }

    public static void frameDispose() {
        frame.dispose();
        System.exit(0);
    }

    public static String optionDialog(String question, String ...answers) {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 18));
        int selection =  JOptionPane.showOptionDialog(
                null,
                question,
                "Title",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                answers,
                answers[0]
        );
        return answers[selection];
    }

    public static void answerDialog(String value) {
        JOptionPane.showMessageDialog(null, value, "We've found an answer!", JOptionPane.INFORMATION_MESSAGE);

    }
}
