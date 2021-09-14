import java.io.*;import java.awt.*;import java.awt.event.*;import java.applet.*;
/*<applet code=XandO.class width=521 height=418></applet>*/
public class XandO extends Applet implements MouseListener,KeyListener
{
    byte board[][],i,j,ii,jj;boolean occupied[][],flag,win[];Font f1,f2;
    public void init()
    {
	addMouseListener(this);addKeyListener(this);requestFocus();board=new byte[3][3];
	occupied=new boolean[3][3];flag=false;i=0;j=0;ii=1;jj=3;win=new boolean[2];
	f1=new Font("Arial",Font.BOLD,16);f2=new Font("Arial",Font.BOLD,40);
    }
    public void paint(Graphics g)
    {
	g.setColor(Color.blue);
	for(i=0;i<4;i++)
	{
	    g.drawLine(10,i*100+10,310,i*100+10);g.drawLine(i*100+10,10,i*100+10,310);
	}
	for(i=0;i<3;i++)
	for(j=0;j<3;j++)
	{
	    if(board[i][j]==1)
	    {
		g.drawLine(j*100+44,i*100+44,j*100+76,i*100+76);
		g.drawLine(j*100+44,i*100+76,j*100+76,i*100+44);
	    }
	    if(board[i][j]==2)
	    g.drawOval(j*100+44,i*100+44,32,32);
	}
	g.drawRect(410,10,100,100);g.setFont(f1);g.drawString("New Game",420,40);
	g.drawString("Click or",431,67);g.drawString("Press N",431,94);
	g.setFont(f2);
	if(win[0]==true)
	{
	    g.drawString("Player 1 wins !",25,400);
	}
	else if(win[1]==true)
	{
	    g.drawString("Player 2 wins !",25,400);
	}
	else
	{
	    A:for(j=0;j<3;j++)
	    for(i=0;i<3;i++)
	    if(occupied[j][i]==false) break A;
	    if(i==3&&j==3&&win[0]==false&&win[1]==false)
	    g.drawString("Match Drawn",40,400);
	}
	g.setColor(Color.red);g.drawRect(0,0,520,417);
	g.drawRect(100*jj+20,100*ii+20,80,80);g.drawRect(100*jj+21,100*ii+21,78,78);
    }
    public void mousePressed(MouseEvent e){} public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){} public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e)
    {
	int x=e.getX();int y=e.getY();int clickr,clickc;
	if(x>10&&x<310&&y>10&&y<310&&(x-10)%100!=0&&(y-10)%100!=0)
	{
	    clickc=(x-10)/100;clickr=(y-10)/100;
	    if(occupied[clickr][clickc]==false)
	    {
		analyse(clickr,clickc);repaint();
	    }
	}
	if(x>410&&x<510&&y>10&&y<110)//new game
	{
	    newgame();repaint();
	}
    }
    public void keyTyped(KeyEvent ke){} public void keyReleased(KeyEvent ke){}
    public void keyPressed(KeyEvent ke)
    {
	int key=ke.getKeyCode();
	switch(key)
	{
	    case KeyEvent.VK_LEFT:if(jj==0) jj=3;else jj--;break;
	    case KeyEvent.VK_RIGHT:if(jj==3) jj=0;else jj++;break;
	    case KeyEvent.VK_UP:if(ii==0) ii=2;else ii--;break;
	    case KeyEvent.VK_DOWN:if(ii==2) ii=0;else ii++;break;
	    case KeyEvent.VK_N:newgame();break;
	    case KeyEvent.VK_ENTER:if(jj<3&&occupied[ii][jj]==false) analyse(ii,jj);
	}
	repaint();
    }
    public void analyse(int row,int col)
    {
	if(flag==false)//X
	{
	    board[row][col]=1;flag=true;
	}
	else//O
	{
	    board[row][col]=2;flag=false;
	}
	occupied[row][col]=true;
	for(i=0;i<3;i++)//check rows,columns
	{
	    if(board[i][0]==board[i][1]&&board[i][1]==board[i][2])
	    {
		if(board[i][0]==1) win[0]=true;if(board[i][0]==2) win[1]=true;
	    }
	    if(board[0][i]==board[1][i]&&board[1][i]==board[2][i])
	    {
		if(board[0][i]==1) win[0]=true;if(board[0][i]==2) win[1]=true;
	    }
	}
	if(board[0][0]==board[1][1]&&board[1][1]==board[2][2])//check diags
	{
	    if(board[0][0]==1) win[0]=true;if(board[0][0]==2) win[1]=true;
	}
	if(board[0][2]==board[1][1]&&board[1][1]==board[2][0])
	{
	    if(board[0][2]==1) win[0]=true;if(board[0][2]==2) win[1]=true;
	}
	if(win[0]==true||win[1]==true)
	for(i=0;i<3;i++)
	for(j=0;j<3;j++)
	occupied[i][j]=true;
    }
    public void newgame()
    {
	for(i=0;i<3;i++)
	for(j=0;j<3;j++)
	{
	    board[i][j]=0;occupied[i][j]=false;
	}
	flag=false;win[0]=false;win[1]=false;ii=1;jj=3;
    }
}