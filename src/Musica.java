import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musica {
	
	AudioInputStream Musica ;
	Clip ClipeMusica;

	public Musica(String diretorio) {
	    try {
	    	Musica = AudioSystem.getAudioInputStream(new File(diretorio).getAbsoluteFile());
	    	ClipeMusica = AudioSystem.getClip();
	    	ClipeMusica.open(Musica);
	    	ClipeMusica.start();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}
}