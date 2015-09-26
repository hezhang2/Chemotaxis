//Helen Zhang, Block 1, Chemotaxis
//declare bacteria variables here
Bacteria [] bob = new Bacteria[20];
Player me;
boolean turn=true;
int attrX, attrY;
void setup(){
	//initialize bacteria variables here
	for(int i=0;i<bob.length;i++){
		bob[i]= new Bacteria();
	}
	me = new Player();
	size(610,610);
	noStroke();
	background(136,165,158);
}
void draw(){
	//move and show the bacteria;
	fill(136,165,158,10);
	rect(0,0,610,610);
	for(int i=0;i<bob.length;i++){
		bob[i].show();
		bob[i].move();
	}
	me.show();
	me.move();
}
void mousePressed(){
	turn=!turn;
}
class Bacteria {
	int myX, myY, bacSize;
	int r,g,b,t;
	int dir, newX, newY;
	boolean isMoving;
	Bacteria(){
		myX=(int)(Math.random()*19)*20+5;
		myY=(int)(Math.random()*19)*20+5;
		dir=(int)(Math.random()*3);
		bacSize=10;
		r=(int)(Math.random()*200);
		b=(int)(Math.random()*200);
		g=(int)(Math.random()*200);
	}
	void show(){
		fill(r,g,b,80);
		ellipse(myX,myY,bacSize,bacSize);
	}
	void move(){
		if (turn==true){
			attrX=me.x();
			attrY=me.y();
		}else{
			attrX=mouseX;
			attrY=mouseY;
		}
		if(dir==0){
			//move up
			int bias;
			if(myY>attrY){
				bias=4;
			}else{
				bias=2;
			}
			if(isMoving==false){
				newY=myY-bias*bacSize;
				isMoving=true;
			}
			if(myY>newY){
				myY-=2;
			}else{
				resetDir();
			}
		}else if(dir==1){
			//move down
			int bias;
			if(myY<attrY){
				bias=4;
			}else{
				bias=2;
			}
			if(isMoving==false){
				newY=myY+bias*bacSize;
				isMoving=true;
			}
			if(myY<newY){
				myY+=2;
			}else if(myY==newY){
				resetDir();
			}
		}else if(dir==2){
			//move left
			int bias;
			if(myX>attrX){
				bias=4;
			}else{
				bias=2;
			}
			if(isMoving==false){
				newX=myX-bias*bacSize;
				isMoving=true;
			}
			if(myX>newX){
				myX-=2;
			}else if(myX==newX){
				resetDir();
			}
		}else{
			//move right
			int bias;
			if(myX<attrX){
				bias=4;
			}else{
				bias=2;
			}
			if(isMoving==false){
				newX=myX+bias*bacSize;
				isMoving=true;
			}
			if(myX<newX){
				myX+=2;
			}else if(myX==newX){
				resetDir();
			}
		}
	}
	void resetDir(){
		//reset the direction
		dir = (int)(Math.random()*4);
		isMoving=false;
	}
}
class Player {
	int myX, myY, bacSize;
	int r,g,b,t;
	int dir, newX, newY;
	boolean isMoving;
	Player(){
		myX=205;
		myY=205;
		dir=(int)(Math.random()*3);
		bacSize=10;
		r=0;
		b=0;
		g=0;
	}
	void show(){
		fill(r,g,b);
		ellipse(myX,myY,bacSize,bacSize);
	}
	void move(){
		if(dir==0){
			//move up
			if(isMoving==false){
				newY=myY-2*bacSize;
				isMoving=true;
			}
			if(myY>newY){
				myY--;
			}else{
				chooseDir();
			}
		}else if(dir==1){
			//move down
			if(isMoving==false){
				newY=myY+2*bacSize;
				isMoving=true;
			}
			if(myY<newY){
				myY++;
			}else{
				chooseDir();
			}
		}else if(dir==2){
			//move left
			if(isMoving==false){
				newX=myX-2*bacSize;
				isMoving=true;
			}
			if(myX>newX){
				myX--;
			}else{
				chooseDir();
			}
		}else{
			//move right
			if(isMoving==false){
				newX=myX+2*bacSize;
				isMoving=true;
			}
			if(myX<newX){
				myX++;
			}else{
				chooseDir();
			}
		}
	}
	void chooseDir(){
		if(key=='w'){
			dir=0;
		}else if (key=='s'){
			dir=1;
		}else if(key=='a'){
			dir=2;
		}else if(key=='d'){
			dir=3;
		}
		isMoving=false;
	}
	int x(){
		return myX;
	}
	int y(){
		return myY;
	}
}