

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class Tetris_Menu implements ActionListener{
	public static JFrame obj;
	static JPanel p;
	private JLabel imglabel;
	private ImageIcon img;
	public static Brick_Gameplay gameplay;
	static JButton play,high,exit;
	Connection c=null;
	Statement st=null;
	ResultSet rs=null;
	Tetris_Menu(){
		obj=new JFrame();
		obj.setLayout(null);
		gameplay=new Brick_Gameplay();
		obj.setBounds(0,0,600,720);
		
		img = new ImageIcon("./res/Green.jpg");;
		imglabel = new JLabel(img);
		obj.add(imglabel);
		imglabel.setBounds(0,0,600,720);
		
		play=new JButton("Play");
		play.setBackground(Color.LIGHT_GRAY);
		play.setBounds(225,100,150,40);
		play.setVisible(true);
		
		high=new JButton("Highscore");
		high.setBackground(Color.LIGHT_GRAY);
		high.setBounds(225,250,150,40);
		high.setVisible(true);
		
		exit=new JButton("Exit");
		exit.setBackground(Color.LIGHT_GRAY);
		exit.setBounds(225,400,150,40);
		exit.setVisible(true);
		
		obj.setTitle("Tetris");
		obj.setResizable(false);
		obj.setLocationRelativeTo(null);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(obj.DISPOSE_ON_CLOSE);
		
		play.addActionListener(this);
		high.addActionListener(this);
		exit.addActionListener(this);
		imglabel.add(play);
		imglabel.add(high);
		imglabel.add(exit);
		//obj.add(gameplay);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==play){
			obj.dispose();
			Tetris.main(null);
		}
		try{
			if(e.getSource()==high){
				int i=2;
				Class.forName("org.postgresql.Driver");
				c=DriverManager.getConnection("jdbc:postgresql://192.168.100.253/tydb34", "ty34", "");
				st=c.createStatement();
				rs=st.executeQuery("select pname,score from player,score where gid=2 and score.pid=player.pid");
				JLabel[] l=new JLabel[12];
				for(int j=0;j<12;j++){
					l[j]=new JLabel();
				}
				l[0].setText("          Name");
				l[1].setText("          Score");
				while(rs.next()){
					l[i].setText("          "+rs.getString(1));
					i++;
					l[i].setText("          "+String.valueOf(rs.getInt(2)));
					i++;
				}
				c.close();
				JFrame f=new JFrame();
				f.setSize(500,500);
				f.setTitle("Highscores");
				f.setLayout(new GridLayout(10,2));
				for(int k=0;k<12;k++){
					f.add(l[k]);
				}
				f.setVisible(true);
				f.setResizable(false);
				f.setLocationRelativeTo(null);
				f.pack();
			}
		}
		catch(Exception c){
			System.out.println("Exception : "+c);
		}
		if(e.getSource()==exit){
			obj.dispose();
		}
	}
}
