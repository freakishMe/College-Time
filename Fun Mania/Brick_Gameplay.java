import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Brick_Gameplay extends JPanel implements KeyListener,ActionListener{
	private boolean play=false;
	private int score=0;
	final private int gid=2;
	
	private static int level=1;
	
	private int totalBricks;
	
	private Timer time;
	private int delay=8;
	
	private int playerX=310;
	
	private int ballposX=120;
	private int ballposY=350;
	private int ballXdir=-1;
	private int ballYdir=-2;
	
	private MapGenerator bricks;
	
	public Brick_Gameplay(){
		mapping();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time=new Timer(delay,this);
		time.start();
	}
	
	public void paint(Graphics g){
		//back
		g.setColor(Color.BLACK);
		g.fillRect(1,1,692,592);
		
		//mapping
		bricks.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.YELLOW);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		//scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString("Score : "+score,500,30);
		
		//paddle
		g.setColor(Color.GREEN);
		g.fillRect(playerX,550,100,8);
		
		//ball
		g.setColor(Color.RED);
		g.fillOval(ballposX,ballposY,20,20);
		
		if(ballposY>570 && play){
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over, Score : "+score,190 ,300 );
			reset();
			//System.exit(0);
			//player scrren for data entry here 
			//TODO:player screen for data entry here open
		}
		
		g.dispose();
	}
	
	void reset(){

		level=1;
		mapping();
		ballposX=120;
		ballposY=350;
		ballXdir=-1;
		ballYdir=-2;
		play=false;
		new player(gid,score);
		score=0;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if(play){
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
				ballYdir=-ballYdir;
			}
			
			A:for(int i=0;i<bricks.map.length;i++){
				for(int j=0;j<bricks.map[0].length;j++){
					if(bricks.map[i][j]>0){
						int brickX=j*bricks.brickWidth+80;
						int brickY=i*bricks.brickHeight+50;
						int brickWidth=bricks.brickWidth;
						int brickHeight=bricks.brickHeight;
						
						Rectangle brickRect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
						Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
						
						if(ballRect.intersects(brickRect)){
							bricks.setBrickValue(0, i, j);
							totalBricks--;
							score=score+5;
							
							if(ballposX+19<=brickRect.x || ballposY+1>=brickRect.x+brickRect.width){
								ballXdir=-ballXdir;
							}
							else{
								ballYdir=-ballYdir;
							}
							break A;
						}
					}
				}
			}
			//level increment
			if(totalBricks==0){
				level++;
				mapping();
				ballposX=120;
				ballposY=350;
				ballXdir=-1;
				ballYdir=-2;
				play=false;
				repaint();
			}
			
			ballposX=ballposX+ballXdir;
			ballposY=ballposY+ballYdir;
			if(ballposX<0){
				ballXdir=-ballXdir;
			}
			if(ballposY<0){
				ballYdir=-ballYdir;
			}
			if(ballposX>670){
				ballXdir=-ballXdir;
			}
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			if(playerX>=600){
				playerX=600;
			}
			else{
				moveRight();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			if(playerX<10){
				playerX=10;
			}
			else{
				moveLeft();
			}
		}
	}
	
	public void moveRight(){
		play=true;
		playerX=playerX+20;
	}
	
	public void moveLeft(){
		play=true;
		playerX=playerX-20;
	}
	
	public void mapping(){
		if(level==1){
			bricks=new MapGenerator(3,7);
			totalBricks=3*7;
//			System.out.println("Map 1 set");
		}
		if(level==2){
			bricks=new MapGenerator(4,6);
			totalBricks=4*6;
//			System.out.println("Map 2 set");
		}
		if(level==3){
			bricks=new MapGenerator(5,8);
			totalBricks=5*8;
//			System.out.println("Map 1 set");
		}
		if(level==4){
			bricks=new MapGenerator(6,10);
			totalBricks=6*10;
//			System.out.println("Map 2 set");
		}
		if(level==5){
			bricks=new MapGenerator(10,10);
			totalBricks=10*10;
//			System.out.println("Map 2 set");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
