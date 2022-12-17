import javax.swing.JFrame;

public class Jogo extends JFrame{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {	

		JogoPainel jogoPainel = new JogoPainel();
		Dica dicas = new Dica();
		InterfacePainel Interface = new InterfacePainel();
		JFrame jogo = new JFrame("Forca");
		
		jogo.add(dicas).setBounds(120, 0, 500, 60);
		jogo.add(Interface).setBounds(110, 450, 520, 240);
		jogo.add(jogoPainel);
		
		jogo.pack();
		jogo.setSize(740, 740);
		jogo.setResizable(false);
		jogo.setVisible(true);
		jogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

}