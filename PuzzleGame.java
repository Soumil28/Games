import java.io.*;import java.awt.*;import java.awt.event.*;import java.applet.*;
/*<applet code=PuzzleGame.class width=621 height=411></applet>*/
public class PuzzleGame extends Applet implements MouseListener,KeyListener
{
    byte board[][],blankr,blankc,count,ii,jj;short i,j;Font f1,f2;boolean correct;
    public void init()
    {
	addMouseListener(this);addKeyListener(this);requestFocus();ii=1;jj=3;
	board=new byte[3][3];board[0][0]=3;board[0][1]=2;board[0][2]=5;
	board[1][0]=7;board[1][1]=0;board[1][2]=6;board[2][0]=4;board[2][1]=8;board[2][2]=1;
	blankr=1;blankc=1;i=0;j=0;count=0;correct=false;
	f1=new Font("Arial",Font.BOLD,50);f2=new Font("Arial",Font.BOLD,25);
    }
    public void paint(Graphics g)
    {
	g.setColor(Color.blue);
	for(i=10;i<=230;i+=110)
	for(j=10;j<=230;j+=110)
	if((i-10)/110!=blankr||(j-10)/110!=blankc)
	g.drawRect(j,i,100,100);
	displayboard(g);g.setFont(f2);count=0;
	g.drawString("ANSWER",475,80);
	for(i=90;i<=200;i+=55)
	for(j=450;j<=560;j+=55,count++)
	if(count!=0)
	{
	    g.drawRect(j,i,50,50);g.drawString(String.valueOf(count),j+18,i+35);
	}
	if(correct==true)
	{
	    g.setFont(f1);g.drawString("CORRECT !",33,400);
	}
	g.setColor(Color.red);g.drawRect(0,0,620,410);
	g.drawRect(110*jj+20,110*ii+20,80,80);g.drawRect(110*jj+21,110*ii+21,78,78);
    }
    public void displayboard(Graphics g)
    {
	g.setFont(f1);g.setColor(Color.blue);
	for(i=0;i<3;i++)
	for(j=0;j<3;j++)
	if(board[i][j]!=0)
	g.drawString(String.valueOf(board[i][j]),(j*110)+45,(i*110)+80);
    }
    public void mousePressed(MouseEvent e){} public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){} public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e)
    {
	int x=e.getX();int y=e.getY();int clickr,clickc;
	if((x-11)%110<99&&(y-11)%110<99&&x>10&&y>10&&x<330&&y<330)
	{
	    boolean modified=false;clickr=(y-11)/110;clickc=(x-11)/110;
	    modified=analyse(clickr,clickc);if(modified==true) repaint();
	}
    }
    public void keyTyped(KeyEvent ke){} public void keyReleased(KeyEvent ke){}
    public void keyPressed(KeyEvent ke)
    {
	int key=ke.getKeyCode();boolean modified=false;
	switch(key)
	{
	    case KeyEvent.VK_LEFT:if(jj==0) jj=3;else jj--;modified=true;break;
	    case KeyEvent.VK_RIGHT:if(jj==3) jj=0;else jj++;modified=true;break;
	    case KeyEvent.VK_UP:if(ii==0) ii=2;else ii--;modified=true;break;
	    case KeyEvent.VK_DOWN:if(ii==2) ii=0;else ii++;modified=true;break;
	    case KeyEvent.VK_ENTER:if(jj<3) modified=analyse(ii,jj);
	}
	if(modified==true) repaint();
    }
    public boolean analyse(int row,int col)
    {
	boolean flag=false;
	if(row==blankr&&Math.abs(col-blankc)==1)
	{
	    board[blankr][blankc]=board[row][col];
	    board[row][col]=0;blankr=(byte)row;blankc=(byte)col;flag=true;
	}
	else if(col==blankc&&Math.abs(row-blankr)==1)
	{
	    board[blankr][blankc]=board[row][col];
	    board[row][col]=0;blankr=(byte)row;blankc=(byte)col;flag=true;
	}
	if(flag==true)
	{
	    A:for(i=0;i<3;i++)
	    for(j=0;j<3;j++)
	    if(board[i][j]!=3*i+j)
	    break A;
	    if(i==3&&j==3) correct=true;else correct=false;
	}
	return flag;
    }
}