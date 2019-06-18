package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Controller.Controller;

public class NPC extends Character{
	Controller gameScreen;
	public float x, y;

	public int dir;//캐릭터가 향한 방향 0-좌, 1-우
	public int status;//캐릭터의 상태
	
	int dirBuff;
	int statusBuff;//캐릭터의 이전 상태 
	
	public ArrayList<Integer> frameList;//캐릭터가 보여줄 프레임 목록
	public ArrayList<Long> frameDelay;//다음 프레임으로 전환하기까지 딜레이
	
	int nowFrame;//현재 프레임 인덱스
	long nowDelay;//현재 프레임을 보여주고 있는 딜레이
	
	boolean isRoof;//애니메이션 루프 여부
	int nextAnimation;//루프가 아닐 경우 다음 애니메이션

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

		//상태변환 여부 확인
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
		
		//애니메이션 정보 변경
		if(nowDelay--<=0){
			
			if(nowFrame==frameList.size()-1){//애니메이션의 마지막 프레임에 도달했는가
				if(isRoof)//반복할 것인가
					nowFrame = 0;//프레임을 처음으로 되돌린다
				else{
					//반복하지 않는다면
					switch(nextAnimation){
					case -1://마지막 프레임을 그대로 보여준다
						break;
					//이외에는 지정한 애니메이션을 호출한다
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
