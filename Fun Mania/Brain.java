import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

class Brain extends JFrame implements ActionListener 
{
	BufferedReader reader,reader1,reader2;
	String line,line1,line2;
	final private int id=5;
	JLabel l;
	JRadioButton jb[]=new JRadioButton[4];
	JButton b1,b2,b3;
	ButtonGroup bg;
	int current=1,score=0;
	int m[]=new int[15];
	
	Connection c=null;
	Statement st=null;
	ResultSet rs=null;
	
	Brain(String s) throws Exception
	{
		super(s);
		l=new JLabel();
		add(l);
		bg=new ButtonGroup();
		for(int i=0;i<4;i++)
		{
			jb[i]=new JRadioButton();	
			add(jb[i]);
			bg.add(jb[i]);
		}
		b1=new JButton("Next");
		b2=new JButton("Highscores");
		b3=new JButton("Exit");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		add(b1);
		add(b2);
		add(b3);
		l.setBounds(30,40,700,20);
		jb[0].setBounds(50,80,100,20);
		jb[1].setBounds(50,110,100,20);
		jb[2].setBounds(50,140,100,20);
		jb[3].setBounds(50,170,100,20);
		b1.setBounds(100,220,100,30);
		b2.setBounds(250,220,120,30);
		b3.setBounds(400,220,100,30);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setLocation(350,200);
		setResizable(false);
		setVisible(true);
		setSize(600,300);
		
		reader= new BufferedReader(new FileReader("res/questions.txt"));
		reader1 = new BufferedReader(new FileReader("res/answers.txt"));
		line = reader.readLine();
		line1=reader1.readLine();
		l.setText(line);
		String[] splited=line1.split("\\s+");
		jb[0].setText(splited[0]);
		jb[1].setText(splited[1]);
		jb[2].setText(splited[2]);
		jb[3].setText(splited[3]);		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b3)
		{
			JOptionPane.showMessageDialog(this,"Correct answers: "+score);
//			System.exit(1);
			new player(id,score);
			dispose();
		}
		
		try{
			if(e.getSource()==b2){
				int i=2;
				Class.forName("org.postgresql.Driver");
				c=DriverManager.getConnection("jdbc:postgresql://192.168.100.253/tydb34", "ty34", "");
				st=c.createStatement();
				rs=st.executeQuery("select pname,score from player,score where gid=5 and score.pid=player.pid");
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
				//JTable tab=new JTable(data,col);
				//JScrollPane sp=new JScrollPane(tab);
				JFrame f=new JFrame();
				f.setSize(500,500);
				f.setTitle("Highscores");
				f.setLocationRelativeTo(null);
				f.setLayout(new GridLayout(10,2));
				for(int k=0;k<12;k++){
					f.add(l[k]);
				}
				f.setVisible(true);
				f.setResizable(false);
				f.setLocationRelativeTo(null);
				f.pack();
				//JOptionPane.showMessageDialog(null, "", "Highscores", JOptionPane.INFORMATION_MESSAGE);

			}
		}
		catch(Exception c){
			System.out.println("Exception : "+c);
		}
		
		if(e.getSource()==b1)
		{			
			if(check())
				score=score+1;
			
			if(b1.getText()=="Result"){
				JOptionPane.showMessageDialog(this,"Correct answers: "+score);
				//System.exit(1);
				new player(id,score);
				dispose();
			}
			
			current++;
			if(current==40)
			{
				b1.setText("Result");
			}
			
			try {
				line = reader.readLine();
				line1=reader1.readLine();
				l.setText(line);
				String[] splited=line1.split("\\s+");
				jb[0].setText(splited[0]);
				jb[1].setText(splited[1]);
				jb[2].setText(splited[2]);
				jb[3].setText(splited[3]);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		bg.clearSelection();
	}
	
	boolean check()
	{
		if(current==1)
			return(jb[2].isSelected());
		if(current==2)
			return(jb[2].isSelected());
		if(current==3)
			return(jb[3].isSelected());
		if(current==4)
			return(jb[3].isSelected());
		if(current==5)
			return(jb[3].isSelected());
		if(current==6)
			return(jb[0].isSelected());
		if(current==7)
			return(jb[0].isSelected());
		if(current==8)
			return(jb[2].isSelected());
		if(current==9)
			return(jb[3].isSelected());
		if(current==10)
			return(jb[2].isSelected());
		if(current==11)
			return(jb[3].isSelected());
		if(current==12)
			return(jb[2].isSelected());
		if(current==13)
			return(jb[3].isSelected());
		if(current==14)
			return(jb[0].isSelected());
		if(current==15)
			return(jb[2].isSelected());
		if(current==16)
			return(jb[2].isSelected());
		if(current==17)
			return(jb[0].isSelected());
		if(current==18)
			return(jb[0].isSelected());
		if(current==19)
			return(jb[2].isSelected());
		if(current==20)
			return(jb[1].isSelected());
		if(current==21)
			return(jb[1].isSelected());
		if(current==22)
			return(jb[1].isSelected());
		if(current==23)
			return(jb[1].isSelected());
		if(current==24)
			return(jb[0].isSelected());
		if(current==25)
			return(jb[3].isSelected());
		if(current==26)
			return(jb[1].isSelected());
		if(current==27)
			return(jb[3].isSelected());
		if(current==28)
			return(jb[3].isSelected());
		if(current==29)
			return(jb[1].isSelected());
		if(current==30)
			return(jb[2].isSelected());
		if(current==31)
			return(jb[0].isSelected());
		if(current==32)
			return(jb[3].isSelected());
		if(current==33)
			return(jb[0].isSelected());
		if(current==34)
			return(jb[2].isSelected());
		if(current==35)
			return(jb[1].isSelected());
		if(current==36)
			return(jb[3].isSelected());
		if(current==37)
			return(jb[3].isSelected());
		if(current==38)
			return(jb[2].isSelected());
		if(current==39)
			return(jb[1].isSelected());
		if(current==40)
			return(jb[2].isSelected());
		return false;
	}
	public static void main(String s[]) throws Exception
	{
		new Brain("Your Test");
	}
}
