package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Controller.Controller;

public class NPC extends Character{
	Controller gameScreen;
	public float x, y;

	public int dir;//ĳ���Ͱ� ���� ���� 0-��, 1-��
	public int status;//ĳ������ ����
	
	int dirBuff;
	int statusBuff;//ĳ������ ���� ���� 
	
	public ArrayList<Integer> frameList;//ĳ���Ͱ� ������ ������ ���
	public ArrayList<Long> frameDelay;//���� ���������� ��ȯ�ϱ���� ������
	
	int nowFrame;//���� ������ �ε���
	long nowDelay;//���� �������� �����ְ� �ִ� ������
	
	boolean isRoof;//�ִϸ��̼� ���� ����
	int nextAnimation;//������ �ƴ� ��� ���� �ִϸ��̼�

	public NPC(Controller gameScreen, int x, int y) {
		// TODO Auto-generated constructor stub
		
		this.gameScreen = gameScreen;
		
		frameList = new ArrayList<Integer>();
		frameDelay = new ArrayList<Long>();
		
		dir = 1;
		dirBuff = 1;
		setIdle();
		
		this.x = x;
		this.y = y;
	}
	
	public NPC() {
		// TODO Auto-generated constructor stub
		
	//	this.gameScreen = gameScreen;
		
		frameList = new ArrayList<Integer>();
		frameDelay = new ArrayList<Long>();
		
		dir = 1;
		dirBuff = 1;
		setIdle();
	}

	public void process(int cnt){

		//���º�ȯ ���� Ȯ��
		if(status!=statusBuff || dir!=dirBuff){
			switch(0){
			case 0:
				setIdle();
				break;			
			}
			
			if(status!=statusBuff)
				statusBuff = status;
			
			if(dir!=dirBuff)
				dirBuff = dir;
			
			return;
		}
		
		//�ִϸ��̼� ���� ����
		if(nowDelay--<=0){
			
			if(nowFrame==frameList.size()-1){//�ִϸ��̼��� ������ �����ӿ� �����ߴ°�
				if(isRoof)//�ݺ��� ���ΰ�
					nowFrame = 0;//�������� ó������ �ǵ�����
				else{
					//�ݺ����� �ʴ´ٸ�
					switch(nextAnimation){
					case -1://������ �������� �״�� �����ش�
						break;
					//�̿ܿ��� ������ �ִϸ��̼��� ȣ���Ѵ�
					case 0:
						setIdle();
						break;					
					}
					
				}
			}else
				nowFrame++;
			
			nowDelay = frameDelay.get(nowFrame);
		}
	}
	public void draw(Graphics2D g, BufferedImage img) {	
			
			gameScreen.drawImageClip(g, img, (int) x, (int) y, 0, 0, 65, 133, 7);		
	}

	public void setIdle(){
		
	}
	
	public void setWalk(){

	}
	
	public void setAttack(){

	}
	
	public void setDead(){
		
	}
}
