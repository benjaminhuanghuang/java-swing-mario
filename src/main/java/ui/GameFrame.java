package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import mario.Mario;
import role.Boom;
import role.Enemy;
import role.Pipe;
import util.Map;


public class GameFrame extends JFrame{
	public Mario mario;
	public Enemy pipe ,cion , brick;
	public BackgroundImage bg ;
	public ArrayList<Enemy> enemyList = new ArrayList<>();
	public ArrayList<Boom> boomList = new ArrayList<>();
	public int bspeed=0;

	public int[][] map = null;
	{
		Map mp = new Map();
		map = mp.readMap();
	}

	public GameFrame() throws Exception {
		this.setSize(800,450);
		this.setTitle("Super Mario");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		mario = new Mario(this);

		bg = new BackgroundImage();

		// Draw map
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j]==1){
					// 1 is brick
					brick = new Pipe(j*30,i*30,30,30,new ImageIcon("image/brick.png").getImage());
					enemyList.add(brick);
				}
				if(map[i][j]==2){
					// 2 is coin_brick
					cion = new Pipe(j*30,i*30,30,30,new ImageIcon("image/coin_brick.png").getImage());
					enemyList.add(cion);
				}
				if(map[i][j]==3){
					// 3
					pipe = new Pipe(j*30,i*30,60,120,new ImageIcon("image/pipe.png").getImage());
					enemyList.add(pipe);
				}
			}
		}

		mario.start();

		new Thread(){
			public void run(){
				while(true){
					repaint();
					//checkBoom();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();


		//���ñ�������
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				MusicUtil.playBackground();
//			}
//		}).start();
	}

	@Override
	public void paint(Graphics g) {
		BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);
		Graphics big = bi.getGraphics();
		big.drawImage(bg.img, bg.x, bg.y, null);

		for (int i = 0; i < enemyList.size(); i++) {
			Enemy e = enemyList.get(i);
			big.drawImage(e.img, e.x, e.y, e.width, e.height,null);
		}

		for (int i = 0; i < boomList.size(); i++) {
			Boom b =boomList.get(i);
			Color c =big.getColor();
			big.setColor(Color.red);
			big.fillOval(b.x+=b.speed, b.y, b.width, b.width);
			big.setColor(c);
		}

		big.drawImage(mario.img, mario.x, mario.y, mario.width, mario.height,null);
		g.drawImage(bi,0,0,null);

	}

	public void checkBoom(){
		for (int i = 0; i < boomList.size(); i++) {
			Boom b = boomList.get(i);
			if(b.x<0 || b.x>800){
				boomList.remove(i);
			}
		}
	}

}
