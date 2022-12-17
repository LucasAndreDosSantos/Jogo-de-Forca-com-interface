import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics2D.*;
import java.awt.geom.*;
import java.util.Scanner;

public class JogoPainel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;	
	
	private Thread animacao;
	private int tempoEspera = 10000, quantidadeErros = 0;
	private boolean jogando = false;
	private Image nuvem = Toolkit.getDefaultToolkit().getImage("./Imagens/Nuvem.png");
	private Image grama = Toolkit.getDefaultToolkit().getImage("./Imagens/Grama.jpg");
	private Image X = Toolkit.getDefaultToolkit().getImage("./Imagens/X.png");
	private Image lingua = Toolkit.getDefaultToolkit().getImage("./Imagens/Lingua.png");
	String palavra = "arroz";

	// Define informações da janela.
	public JogoPainel() {
		// cor de fundo.
		setBackground(Color.WHITE);
		setFocusable(true);
	}	
	// Controle de início do jogo.
	public void addNotify() {
		super.addNotify();
		iniciarJogo();
	}
    
	// Função que da start nas funções de início do jogo
	private void iniciarJogo() {
		if (animacao == null || !jogando) {
				animacao = new Thread(this);
				animacao.start();
		}
	}

	public void run() {
		jogando = true;
		/*
		 * Loop do jogo. Aqui as coisas acontecem.
		 */
		while (jogando) {
			
			repaint();
			//System.out.print(quantidadeErros);
			/* fim aÃ§Ãµes para testes */
			try {
				Thread.sleep(tempoEspera);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/**
		 * As "coordenadas" das cores foram encontradsa em
		 * https://teaching.csse.uwa.edu.au/units/CITS1001/colorinfo.html
		 **/
		Color BROWN = new Color(153, 102, 0); // Cria a cor marrom
		Color DARK_BROWN = new Color(153, 80, 0);// Cria a cor marrom escuro
		Color DARK_YELLOW = new Color(255, 204, 0); // Cria a cor amarelo escuro
		Color DARK_GREEN = new Color(0, 153, 0); // Cra a cor verde escuro
		
		// Nuvens
		g.drawImage(nuvem, 500, 10, this);
		g.drawImage(nuvem, -60, 10, this);
		
		// Grama
		g.drawImage(grama, 550, 277, this);
		g.drawImage(grama, 350, 277, this);
		g.drawImage(grama, 150, 277, this);
		g.drawImage(grama, -50, 277, this);
		
		/* Forca */
		g.setColor(BROWN);
		g.fillRect(220, 65, 50, 360); // Madeira vertical da forca
		g.fillRect(170, 90, 320, 40); // Madeira horizontal da forca

		// Sombras da forca
		g.setColor(DARK_BROWN);
		g.fillRect(210, 90, 10, 40); // Sombra na madeira vertical da forca

		// Corda da forca
		g.setColor(DARK_YELLOW);
		g.fillRect(442, 130, 6, 20);
		g.fillOval(436, 148, 18, 6);
		g.fillOval(436, 152, 18, 6);

		// Chão
		g.setColor(DARK_GREEN);
		g.fillRect(0, 424, 726, 20);
		
		/*** Enforcado ***/
		g.setColor(Color.DARK_GRAY);
		if (quantidadeErros > 0) {
			g.fillOval(420, 136, 50, 50); // Desenha a cabeça
		}
		if (quantidadeErros > 1) {
			g.fillRect(420, 195, 50, 90); // Desenha o tronco
		}
		if (quantidadeErros > 2) {
			g.fillRect(400, 198, 15, 90); // Desenha o braço esquerdo
		}
		if (quantidadeErros > 3) {
			g.fillRect(476, 198, 15, 90); // Desenha o braço direito
		}
		if (quantidadeErros > 4) {
			g.fillRect(420, 290, 16, 90); // Desenha a perna esquerda
		}
		if (quantidadeErros > 5) {
			g.fillRect(454, 290, 16, 90); // Desenha a perna direita
			g.drawImage(X, 425, 146, this);
			g.drawImage(X, 450, 146, this);
			g.drawImage(lingua, 421, 166, this);
		}
	}
	
	public int getQuantidadeErros() {
		return quantidadeErros;
	}
	public void setQuantidadeErros(int quantidadeErros) {
		this.quantidadeErros = quantidadeErros;
		repaint();
	}// fim verificaSeAcertou
	
} /* Fim */
