import java.awt.Color;
import java.awt.event.*;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import java.io.*;
import java.util.*;

public class textEditor  implements ActionListener {
    JFrame f;
    JMenuBar mb;
    JMenu file, edit,format, help;
    JMenuItem cut, copy, paste, selectAll,New,Open,Save,Print,helps,bg,fg;
    JTextArea ta;
    private char[] text;

    textEditor() {
        f = new JFrame("Notepad");

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("SelectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        New = new JMenuItem("New");
        Open = new JMenuItem("Open");
        Save= new JMenuItem("Save");
        Print = new JMenuItem("Print");
        New.addActionListener(this);
        Open.addActionListener(this);
        Save.addActionListener(this);
        Print.addActionListener(this);


        helps = new JMenuItem("helps");


        bg = new JMenuItem("Set Background");
        fg = new JMenuItem("Set Foreground");
        bg.addActionListener(this);
        fg.addActionListener(this);
       

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
         format= new JMenu("Format");

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        file.add(New);
        file.add(Open);
        file.add(Save);
        file.add(Print);

        format.add(bg);
        format.add(fg);

        help.add(helps);

        mb.add(file);
        mb.add(edit);
        mb.add(format);
        mb.add(help);

        ta = new JTextArea();
        ta.setBounds(1, 1, 1000, 1000);
        f.add(mb);
        f.add(ta);
        f.setJMenuBar(mb);

        f.setLayout(null);
        f.setSize(1000, 1000);
        f.setVisible(true);
        f.getContentPane().setBackground(Color.white);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        else if (e.getSource() == paste)
            ta.paste();
        else if (e.getSource() == copy)
            ta.copy();
        else if (e.getSource() == selectAll)
            ta.selectAll();
        else if (e.getSource() == bg){
            JColorChooser color_box=new JColorChooser();
            Color c=color_box.showDialog(f, "Select a Color", Color.white);
            ta.setBackground(c);
        }
        else if (e.getSource() == fg){
        JColorChooser color_box=new JColorChooser();
        Color c=color_box.showDialog(f, "Select a Color", Color.white);
        ta.setForeground(c);
        }
        else if (e.getSource() == New){
            ta.setText("");
            ta.setBackground(Color.white);
        }
        else if (e.getSource() == Open){
            JFileChooser fileChooser=new JFileChooser();
            int option=fileChooser.showOpenDialog(f);
            File f=fileChooser.getSelectedFile();

            try{
               Scanner sc =new Scanner(f);
               while(sc.hasNextLine()){
                ta.append("/n"+sc.nextLine());

               }
            }
            catch(Exception ex){

            }
        }
            
        else if (e.getSource() == Save){
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            int option=fileChooser.showSaveDialog(f);
            if(option==JFileChooser.APPROVE_OPTION){
                try{
                     File f=fileChooser.getSelectedFile();
                     String txt=ta.getText();
                     FileWriter myw=new FileWriter(f);
                     
                    myw.write(text);
                     myw.close();
                     System.out.println("Successfully");   
                }
                catch(Exception ex){

                }
            }
        }
            
        else if (e.getSource() == Print)
        ta.paste();
    }

    public static void main(String[] args) {
        new textEditor();
    }

}
