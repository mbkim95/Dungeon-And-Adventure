package Model;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Controller.Controller;


public abstract class Character implements Moveable, Attackable {
	
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

	public Character(Controller gameScreen) {
		// TODO Auto-generated constructor stub
		
		this.gameScreen = gameScreen;
		
		frameList = new ArrayList<Integer>();
		frameDelay = new ArrayList<Long>();
		
		dir = 1;
		dirBuff = 1;
		setIdle();
	}
	
	public Character() {
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
			switch(status){
			case 0:
				setIdle();
				break;
			case 1:
				setWalk();
				break;
			case 2:
				setAttack();
				break;
			case 3:
				setDead();
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
					case 1:
						setWalk();
						break;
					case 2:
						setAttack();
						break;
					case 3:
						setDead();
						break;
					}
					
				}
			}else
				nowFrame++;
			
			nowDelay = frameDelay.get(nowFrame);
		}
	}
	public void draw(Graphics2D g, BufferedImage img){
		
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
