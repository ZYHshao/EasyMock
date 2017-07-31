package ui;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NewDialog extends Dialog {

	private TextField textField;
	private Button button;
	public NewDialog(Frame owner) {
		super(owner);
		// TODO Auto-generated constructor stub
		this.setTitle("请设置api名称");
		this.setModal(true);
		this.setResizable(false);
		this.setBounds(new Rectangle(500, 300, 400, 250));
		Panel pannel = new Panel(null);
		this.add(pannel);
		textField = new TextField();
		textField.setBounds(50, 50, 300, 30);
		pannel.add(textField);
		button = new Button("确定");
		button.setBounds(new Rectangle(165, 100, 70, 30));
		pannel.add(button);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				e.getWindow().setVisible(false);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}



}
