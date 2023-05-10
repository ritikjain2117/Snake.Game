package com.example.snake;
import android.util.Log;

import java.util.Random;

public class Body {


private GameActivity game;
private int lastMov;
private int block[][] = new int[48][27];
private int lastMovement = 1;
    private int FirstMovement = 10;

private SnakeHead Head;
private Random rand;

///=====>>Model<<======

/*
* empity_space = 0
* wall = -1
* apple = -4
* poison = -5
* snake:
*       -head = 3
*       -body = 2
*       -tail = 2
*
*
* lastMovement {
* -1 = right
* -2 = left
* -3 = up
* -4 = down
* }
* */
public Body(GameActivity game){
    this.game = game;
    //the costructor is only for reference GameActivity class

}

public void GenerateMap(int type){
    Head = new SnakeHead();
    //crea i bordi della mappa
    for(int row=0;row<=47;row++){
        for(int clm=0;clm<=26;clm++) {

            if ((row == 0) || (row == 47) || (clm == 0) || (clm == 26)) { //set the position of block in the border of map
                block[row][clm] = -1;
            } else {
                block[row][clm] = 0;
            }

        }

    }
    switch(type){ //draw the rest of the map according to the type of map, the map has been choosen by the gamer
        case 0: break;
    case 2:


        for(int i = 1; i<15;i++){
        block[6][6 + i] = -1;
        block[42][6+i] = -1;
    }

    break;
    case 1:

        for(int i=-5; i<5; i++){

            block[14+i][5] = -1;
            block[14+i][22] = -1;
        }


        break;
    }


        //placement of the snake
        //body
        block[23][13] = 2;
        //head
        block[23][14] = 3;
        Head.setX(23);
        Head.setY(14);
        Head.setHead(3);
        //tail
        block[23][12] = 2;
        generateApple();
        generateNoAplle();//generate mushrooms


}

public void generateApple(){
    rand = new Random();
    Boolean Flag = true;
    int rdmRow, rdmClm;
    while(Flag) { //until an apple has been inserted
    rdmRow = rand.nextInt(45);//random placement of the apple
    rdmClm = rand.nextInt(24);
    if(block[rdmRow][rdmClm] == 0){
        block[rdmRow][rdmClm] = -4;
        Flag = false;
    }
}
}

public void generateNoAplle(){
    rand = new Random();
    int rdmRow, rdmClm;
    for(int i =0;i<6;i++){ //random number of mushrooms between 0 and 5
        rdmRow = rand.nextInt(47);
        rdmClm = rand.nextInt(26);
        if (block[rdmRow][rdmClm] == 0 ) {
            block[rdmRow][rdmClm] = -5;
        }
    }}

public int showBody(int row,int clm){//metodo di collegamento con la View cioè GraphicsSnake
        return block[row][clm];
}

public void setLastMovement(int movement){
    lastMovement = movement;
}

public int getLastMovement(){
    return lastMov;
}

public int getHead(){return Head.getHead();}

    public void decreseMatrix(){
        //decrese matrix where block[][]>=1, where the snake is
        for(int row = 1;row <=46;row++){
            for(int clm = 1;clm<=25;clm ++){
                if (block[row][clm] >=1){
                    block[row][clm] --;
                }
            }
        }

    }

public void moveGame() {

    int row;
    int clm;
    int zindex;
    med= Head.getZ();
    row = Head.getX();
    clm = Head.getY();


    //move the snake according by lastMovement
    switch (lastMovement) {
        case 1: //right
            if(block[row][clm+1]!= -1 && block[row][clm+1] <= 0){
               if(block[row][clm+1] == -4){ //apple
                   Head.setHead(Head.getHead()+1);
                   generateApple();
                   game.bitsound();

               }
                if(block[row][clm+1]==-5){ lose(); }

            block[row][clm + 1] = Head.getHead() + 1; //mve the value of head +1 according to the last movement
            Head.setY(clm + 1);
        }else { lose(); }
            break;
        case 2: //Left
            if(block[row][clm-1] != -1 && block[row][clm-1]<= 0) {
                if (block[row][clm-1] == -4) {
                    Head.setHead(Head.getHead() + 1);
                    generateApple();
                    game.bitsound();
                }
                if(block[row][clm-1]==-5){ lose(); }

                block[row][clm - 1] = Head.getHead() + 1;
                Head.setY(clm - 1);
            }else{ lose(); }
            break;
        case 3: // up
            if(block[row-1][clm] != -1 && block[row-1][clm] <= 0) {
                if (block[row-1][clm] == -4) {
                    Head.setHead(Head.getHead() + 1);
                    generateApple();
                    game.bitsound();
                }
                if(block[row-1][clm]==-5){

                    lose();
                }
                block[row - 1][clm] = Head.getHead() + 1;
                Head.setX(row - 1);
            }else{lose();}
            break;

        case 4: //down
            if(block[row+1][clm] != -1 && block[row+1][clm] <= 0) {
                if (block[row+1][clm] == -4) {
                    Head.setHead(Head.getHead() + 1);
                    generateApple();
                    game.bitsound();
                }
                if(block[row+1][clm]==-5){lose();}

                block[row + 1][clm] = Head.getHead() + 1;
                Head.setX(row + 1);
            }else{ lose(); }
            break;

    }
    decreseMatrix();
    lastMov = lastMovement;

}
public void lose(){
    game.gameOver();
}//called by moveGame() when the snake eat a mushrooms or is positioned in a block

}//end class
