import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.text.*;
public class Main extends JFrame implements ActionListener {
    JFrame p;
    JTextArea t;

    Main() {
        //initialising the frame title
        //setting the editors name , text area & font
        p = new JFrame("saam's first notepad Text Editor");
        t = new JTextArea();
        Font font = new Font("SAAM", Font.TRUETYPE_FONT, 16);
        t.setFont(font);
        t.setForeground(Color.black);// characters  colors
        t.setBackground(Color.white);//editor area bg
        t.setTabSize(4); //tab size

        JMenuBar mb = new JMenuBar();//intializing the menu bar as mb

        JMenu m1 = new JMenu("File"); //creating the files option
        JMenuItem mi1 = new JMenuItem("Open");// the choices in file menu
        mi1.setBackground(Color.LIGHT_GRAY);// color of the file menu
        JMenuItem mi2 = new JMenuItem("New");//creating
        mi2.setBackground(Color.LIGHT_GRAY);//bg color
        JMenuItem mi3 = new JMenuItem("Save");//creating
        mi3.setBackground(Color.LIGHT_GRAY);//bg color
        JMenuItem mi9 = new JMenuItem("Print");//creating
        mi9.setBackground(Color.LIGHT_GRAY);//bg color
        mi1.addActionListener(this);//adding actionListers
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);
        m1.add(mi1);//adding menu items to the menu  bar
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        JMenu m2 = new JMenu("Edit");//creating more menu options and giving the bg color
        JMenuItem mi4 = new JMenuItem("Cut");
        mi4.setBackground(Color.LIGHT_GRAY);
        mi4.addActionListener(this);
        JMenuItem mi5 = new JMenuItem("Copy");
        mi5.setBackground(Color.LIGHT_GRAY);
        mi5.addActionListener(this);
        JMenuItem mi6 = new JMenuItem("Paste");
        mi6.setBackground(Color.LIGHT_GRAY);
        mi6.addActionListener(this);
        //adding  menuitems to edit menu
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
        //create close button
        JMenuItem close = new JMenuItem("Close");
        close.setBackground(Color.WHITE);
        close.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(close);
        mb.setBackground(Color.WHITE);
        p.setJMenuBar(mb);

        JScrollPane scroll = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        p.add(scroll);
        p.setSize(600, 900);
        p.setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setVisible(true);
    }
    //funtion for adding the funtionality to each of the menuitem
    public void actionPerformed(ActionEvent e) {
        //extracting the button pressed
        String s = e.getActionCommand();
        if (s.equals("New")) {
            t.setText("");
        } else if (s.equals("Open")) {
            //botton pressing action innitializing
            JFileChooser j = new JFileChooser("f:");
            //opendialog  box in an integer
            int r = j.showOpenDialog(null);
            //user apporval
            if (r == JFileChooser.APPROVE_OPTION) {
                try {
                    //set the label to the path of the selected ile location
                    File fi = new File(j.getSelectedFile().getAbsolutePath());
                    String s1 = "", sl = "";
                    FileReader fr = new FileReader(fi);
                    BufferedReader br = new BufferedReader(fr);
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + s1 + "\n";
                    }
                    t.setText(sl);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "user cancelled");
            }
        } else if (s.equals("Save")) {
            JFileChooser j = new JFileChooser("f:");
            int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    FileWriter fw = new FileWriter(fi);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(t.getText());
                    bw.flush();
                    bw.close();
                } catch (Exception e3) {
                    JOptionPane.showMessageDialog(null, e3.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "user cancelled");
            }
        } else if (s.equals("Print")) {
            try {
                t.print();
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, e2.getMessage());
            }
        } else if (s.equals("Cut")) {
            t.cut();
        } else if (s.equals("Copy")) {
            t.copy();
        } else if (s.equals("Paste")) {
            t.paste();
        } else if (s.equals("Close")) {
            p.setVisible(false);
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        Main pad = new Main();
    }
}