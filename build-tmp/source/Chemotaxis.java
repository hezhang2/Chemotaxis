import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Chemotaxis extends PApplet {

//Helen Zhang, Block 1, Chemotaxis
//declare bacteria variables here
Bacteria [] bob = new Bacteria[20];
Player me;
public void setup(){
	//initialize bacteria variables here
	for(int i=0;i<bob.length;i++){
		bob[i]= new Bacteria();
	}
	me = new Player();
	size(410,410);
	noStroke();
	background(136,165,158);
	frameRate(120);
}
public void draw(){
	//move and show the bacteria;
	fill(136,165,158,10);
	rect(0,0,410,410);
	for(int i=0;i<bob.length;i++){
		bob[i].show();
		bob[i].move();
	}
	me.show();
	me.move();
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
	public void show(){
		fill(r,g,b,80);
		ellipse(myX,myY,bacSize,bacSize);
	}
	public void move(){
		if(dir==0){
			//move up
			int bias;
			if(myY>mouseY){
				bias=4;
			}else{
				bias=2;
			}
			if(isMoving==false){
				newY=myY-bias*bacSize;
				isMoving=true;
			}
			if(myY>newY){
				myY--;
			}else{
				resetDir();
			}
		}else if(dir==1){
			//move down
			int bias;
			if(myY<mouseY){
				bias=4;
			}else{
				bias=2;
			}
			if(isMoving==false){
				newY=myY+bias*bacSize;
				isMoving=true;
			}
			if(myY<newY){
				myY++;
			}else if(myY==newY){
				resetDir();
			}
		}else if(dir==2){
			//move left
			int bias;
			if(myX>mouseX){
				bias=4;
			}else{
				bias=2;
			}
			if(isMoving==false){
				newX=myX-bias*bacSize;
				isMoving=true;
			}
			if(myX>newX){
				myX--;
			}else if(myX==newX){
				resetDir();
			}
		}else{
			//move right
			int bias;
			if(myX<mouseX){
				bias=4;
			}else{
				bias=2;
			}
			if(isMoving==false){
				newX=myX+bias*bacSize;
				isMoving=true;
			}
			if(myX<newX){
				myX++;
			}else if(myX==newX){
				resetDir();
			}
		}
	}
	public void resetDir(){
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
	public void show(){
		fill(r,g,b);
		ellipse(myX,myY,bacSize,bacSize);
	}
	public void move(){
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
	public void chooseDir(){
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
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Chemotaxis" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
