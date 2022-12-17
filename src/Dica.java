import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JPanel;

public class Dica extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Label dica;
	private String[] dicas = {"Verdura","Fruta"};
	
	public Dica() {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		dica = new Label(dicas[1]);
		dica.setFont(new Font("Times new Roman", Font.BOLD, 40));
		add(dica);
	}
}