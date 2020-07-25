package mario;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
//
import ui.GameFrame;
import role.Enemy;

//�Լ��Ľ�ɫ��
public class Mario extends Thread {
	
	public GameFrame gf;
	
	public boolean jumpFlag=true;
	
	//����µ�����
	public int x=0,y=358;
	//����µ��ٶ�
	public int xspeed=5 , yspeed=1;
	//����µĿ��
	public int width=30,height=32;
	//����µ�ͼƬ
	public Image img = new ImageIcon("image/mari1.png").getImage();
	
	public boolean left=false,right=false,down=false,up=false;
	
	public String Dir_Up="Up",Dir_Left="Left",Dir_Right="Right",Dir_Down="Down";
	
	
	public Mario (GameFrame gf) {
		this.gf = gf;
		this.Gravity();
	}

	// ����������߼� ���ƶ����߼��������
	public void run(){
		while(true){
			//������
			if(left){
				//��ײ����
				if(hit(Dir_Left)){
					this.xspeed=0;
				}
				
				if(this.x>=0){
					this.x-=this.xspeed;
					this.img=new ImageIcon("image/mari_left.gif").getImage();
				}
				
				this.xspeed=5;
			}
			
			//������
			if(right){
				// �ұ���ײ����Ӧ���������ߵ�ʱ����
				// ������ײ��⣺�������ǣ���������ײ�
				if(hit(Dir_Right)){
					this.xspeed=0;
				}
				//�����������ƶ�
				if(this.x<400){
					this.x += this.xspeed;
					this.img=new ImageIcon("image/mari_right.gif").getImage();
				}
				
				if(this.x>=400){
					//���������ƶ�
					gf.bg.x-=this.xspeed;
					//�ϰ��������ƶ�
					for (int i = 0; i <gf.eneryList.size(); i++) {
						Enemy enery = gf.eneryList.get(i);
						enery.x-=this.xspeed;
					}
					this.img= new ImageIcon("image/mari_right.gif").getImage();
				}
				this.xspeed=5;
			}
			
			//������
			if(up){

				if(jumpFlag && !isGravity){
					jumpFlag=false;
					new Thread(){
						public void run(){
							jump();
							jumpFlag=true;
						}
					}.start();
				}
			}
			
			try {
				this.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//�������ĺ���
	public void jump(){
		int jumpHeigh=0;
		for (int i = 0; i < 150; i++) {
			gf.mario.y-=this.yspeed;
			jumpHeigh++;
			if(hit(Dir_Up)){
				break;
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i <jumpHeigh; i++) {
			gf.mario.y+=this.yspeed;
			if(hit(Dir_Down)){
				this.yspeed=0;
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		this.yspeed=1;//��ԭ�ٶ�
	}
	
	//�����ײ
	public boolean hit(String dir){
		// Swing�����У��˼��Ѿ��ṩ�ˣ���
		Rectangle myrect = new Rectangle(this.x,this.y,this.width,this.height);

		Rectangle rect =null;
		
		for (int i = 0; i < gf.eneryList.size(); i++) {
			Enemy enery = gf.eneryList.get(i);
			
			if(dir.equals("Left")){
				rect = new Rectangle(enery.x+2,enery.y,enery.width,enery.height);
			}else if(dir.equals("Right")){
				// �Ҳ���ײ���⡣
				rect = new Rectangle(enery.x-2,enery.y,enery.width,enery.height);
			}
			
			else if(dir.equals("Up")){
				rect = new Rectangle(enery.x,enery.y+1,enery.width,enery.height);
			}else if(dir.equals("Down")){
				rect = new Rectangle(enery.x,enery.y-2,enery.width,enery.height);
			}
			//��ײ���
			if(myrect.intersects(rect)){
				return true;
			}
		}
		
		return false;
	}
	
	//����Ƿ�����
	public boolean isGravity=false;

	// �����̣߳�
	public void Gravity(){
			new Thread(){
				public void run(){
					
					while(true){
						try {
							sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						

						while(true){
							if(!jumpFlag){
								break;
							}
							
							if(hit(Dir_Down)){
								break;
							}
							
							if(y>=358){
								isGravity=false;
							}else{
								isGravity=true;
								y+=yspeed;
							}
							
							try {
								sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
					}
				}
				}
			}.start();
	
	}
}
