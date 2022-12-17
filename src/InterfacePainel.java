import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InterfacePainel extends JPanel {

	private static final long serialVersionUID = -9192718173635316257L;
    
	// Variaveis das utilizadas no c�digo
	ActionListener clicar; //A��o que ativa quando o usuario clica no bot�o
	private GridLayout LayoutTeclado; // Layout principal para organiza��o os paineis
	private int NumeroPalavra = 0,TeclaClicada = 100, qtErros = 0; //Define qual foi a tecla clicada
	private JButton teclado[]; //Variavel de todos os bot�es do teclado, que totalizam 26 teclas
	private JogoPainel auxilia = new JogoPainel();
	private JPanel panelmedio,panelsuperior,panelbaixo; //Paineis utilizados na interface para colocar o label e os bot�es
	private Label palavra;
	String PalavraEspaco = "_";
	private String[] palavras = {"Dinossauro","Banana Prata"};
	private String[] teclas = {"Q","W","E","R","T","Y","U","I","O","P","A","S",
	"D","F","G","H","J","K","L","Z","X","C","V","B","N","M"}; //Teclas do teclado em ordem do teclado americano
	
	
	//Construtura respons�vel por chamar as outras fun��es e para ser adicionar ao frame principal
	public InterfacePainel() {
		DefinirPalavraDaTela();
		AcaoDoBotao();
		DefinirInterface();
	}
	
	//Respons�vel por chamar todas as partes presentes na interface, que s�o os paineis, botoes e labels
	public void DefinirInterface() {
		setBackground(Color.WHITE);
		CriarPaineis();
		CriarLabel();
		CriarBotao();
	}

	//Respons�vel por criar os paineis onde foram colocados os bot�es e os labels
	public void CriarPaineis() {
		LayoutTeclado = new GridLayout(3,1,5,5);
		setLayout(LayoutTeclado);
		
		panelmedio = new JPanel();
		panelsuperior = new JPanel();
		panelbaixo = new JPanel();	
		
		panelmedio.setBackground(Color.WHITE);
		panelsuperior.setBackground(Color.WHITE);
		panelbaixo.setBackground(Color.WHITE);
		
		add(panelsuperior);
		add(panelmedio);
		add(panelbaixo);
	
		panelsuperior.setLayout(new FlowLayout());
		panelmedio.setLayout(new GridLayout(2,1,5,5));
		panelbaixo.setLayout(new GridLayout(2,1,5,5));		
	}
	
	//Respons�vel por criar os 26 bot�es da tela
	public void CriarBotao() {
		teclado = new JButton[teclas.length];
		
		for(int i = 0; i < 10; i++) //10 = valor da primeira fileira de teclas do teclado
		{
			teclado[i] = new JButton(teclas[i]);
			teclado[i].setBackground(Color.WHITE);
			teclado[i].addActionListener(clicar);	
			panelmedio.add(teclado[i]);
		}
		
		for(int i = 10; i < 19; i++) //19 = soma do valor das 2 primeiras fileiras de teclad do teclado
		{
			teclado[i] = new JButton(teclas[i]);
			teclado[i].setBackground(Color.WHITE);
			teclado[i].addActionListener(clicar);	
			panelmedio.add(teclado[i]);
		}
		
		panelbaixo.add(new Label()); //Label respons�vel por adicionar o recuo antes da 3 fileira de bot�es
		
		for(int i = 19; i < 26; i++) //26 = N�mero total de teclas
		{
			teclado[i] = new JButton(teclas[i]);
			teclado[i].setBackground(Color.WHITE);
			teclado[i].addActionListener(clicar);	
			panelbaixo.add(teclado[i]);
		}	
		
		for(int i = 26; i < 37; i++) { //37 = Fileiras de teclado mais um espa�o para melhorar o visual do teclado
			panelbaixo.add(new Label());
		}
		
	}
	
	//Fun��o respons�vel por criar a dica na tela
	public void CriarLabel() {
		
		palavra = new Label(palavras[0].replaceAll ("(.)","_  "));
		palavra.setFont(new Font("Times new Roman", Font.BOLD, 40)); //Mudando a fonte para Times new Roman 40
		panelsuperior.add(palavra,BorderLayout.CENTER);	
	}	
	
	// Define a palavra que est� na tela com _
	public void DefinirPalavraDaTela() {
		for(int i=1;i<palavras[NumeroPalavra].length();i++) {
			PalavraEspaco += "_";
		}		
	}
	
	// Respons�vel por colocar o acerto do usuario na tela
	public void ColocarAcertos() {
		boolean temEssaLetra = false;
		String Auxiliar = "";
		String AuxiliarPalavraCompleta = "";
		
		for(int i=0;i< palavras[NumeroPalavra].length();i++) {
			 if((palavras[NumeroPalavra].substring(i,i+1).toLowerCase().equals(teclas[TeclaClicada].toLowerCase()) && (PalavraEspaco.substring(i,i+1).equals("_") ))){
				temEssaLetra = true;
				 
				 Auxiliar += palavras[NumeroPalavra].substring(i,i+1);
				 Auxiliar += "  ";
			 }else {
				if (temEssaLetra == false) {
						qtErros++;
						auxilia.setQuantidadeErros(qtErros);
				} 
				 
				 Auxiliar += "_  ";
			 }
		 }
		 Auxiliar = Auxiliar.replaceAll("  ","");
		 
		 for(int i=0;i<Auxiliar.length();i++)
			 if(Auxiliar.substring(i,i+1).equals("_")){
				 if (PalavraEspaco.substring(i,i+1).equals("_")) {
					 AuxiliarPalavraCompleta += "_";	 
				 }else {
					 AuxiliarPalavraCompleta += PalavraEspaco.substring(i,i+1);	 
				 }
			}else {
				 AuxiliarPalavraCompleta += Auxiliar.substring(i,i+1);	 
			 } 
		 
		 PalavraEspaco = AuxiliarPalavraCompleta.replaceAll ("(.)","$1  ");					 
		 palavra.setText(PalavraEspaco);
		 PalavraEspaco = PalavraEspaco.replaceAll (" ","");
	}
	
	// Respons�vel por definir o que vai acontecer quando o usuario clicar no bot�o
	public void AcaoDoBotao() {
		clicar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				JogoPainel jogo = new JogoPainel();
				
				for(int i=0;i<teclas.length; i++) {
					if(e.getSource() == teclado[i]) {
						TeclaClicada = i;
					}		
				}
				if(PalavraEspaco.contains(teclas[TeclaClicada].toLowerCase())) {}{
				 if(palavras[NumeroPalavra].toLowerCase().contains(teclas[TeclaClicada].toLowerCase())) {
					 teclado[TeclaClicada].setBackground(Color.GREEN);
					 String mouseclickacerto = "./Sons/MouseClickCerto.wav";
					 @SuppressWarnings("unused")
					Musica clickcerto = new Musica(mouseclickacerto);
					ColocarAcertos();
					 
				 }else {
					 teclado[TeclaClicada].setBackground(Color.RED);
					 String mouseclickerro = "./Sons/MouseClickErrado.wav";
					 @SuppressWarnings("unused")
					 Musica ClickErrado = new Musica(mouseclickerro);
					 int n = jogo.getQuantidadeErros();
					 jogo.setQuantidadeErros(n++);
				 };
				}
			}
		};	
	}
	
	// Fun��o para pegar a palavra sorteada
	public String getPalavras() {
		return palavras[NumeroPalavra];
	}
	
	// Fun��o para pegar a tecla clicada
	public String getTeclas() {
		return teclas[TeclaClicada];
	}
	
}



