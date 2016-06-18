package project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	private Client client;
	private MainApp mainApp;
	private JLabel[] option;
	private JLabel bg;
	private int width, height, opx = 70, opy = 70;
	
	public static void main(String[] args) throws IOException{
		new Main();
	}
	
	public Main(){
		
		super("Chat!!");
		this.setLayout(null);
		
		
		
		mainApp = new MainApp();
		
		mainApp.init();
		width = mainApp.getW();
		height = mainApp.getH();
		optInit();
		
		this.add(mainApp);
		mainApp.setLocation(0, opy);
		
		
		this.setSize(width, height+opy+29);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		while(!mainApp.finRead()){
		    try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		client = new Client("127.0.0.1", 8000);
		client.setMainApp(mainApp);
		mainApp.setClient(client);
	}
	
	private void optInit(){
		option = new JLabel[4];
		option[0] = new JLabel(new ImageIcon("img/map.png"));
		option[1] = new JLabel(new ImageIcon("img/pet.png"));
		option[2] = new JLabel(new ImageIcon("img/set.png"));
		option[3] = new JLabel(new ImageIcon("img/opt.png"));
		bg = new JLabel(new ImageIcon("img/optionbg.jpg"));
		
		//appY = option[0].getIcon().getIconHeight() + 12;	
		for(int i=0; i<3; i++){
			add(option[i]);
			option[i].setBounds(15+opx*i, 0, opx, opy);
			option[i].addMouseListener(this);
		}add(option[3]);
		option[3].setBounds( 5, 5, opy, opy);
		add(bg);
		bg.setBounds(0, 0, width, opy);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0; i<3; i++){
			if(e.getSource()==option[i]){
				mainApp.setApp(i);
				option[3].setLocation( 5+opx*i, 5);
			}
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {		
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}
