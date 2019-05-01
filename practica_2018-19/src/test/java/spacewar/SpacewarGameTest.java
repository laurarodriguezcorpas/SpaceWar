package spacewar;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.BeforeClass;
import org.junit.Test;

public class SpacewarGameTest {

	@BeforeClass
	public static void startServer() {	//Comprueba que se levante el servidor
		Application.main(new String[] { "--server.port=9000" });
	}

	@Test
	public void testConnection() throws Exception {	//Comprueba que ocurra una conexión entre cliente y servidor

		WebSocketClient ws = new WebSocketClient();
		ws.connect("ws://127.0.0.1:9000/spacewar");
		ws.disconnect();
	}

	@Test
	public void testJoin() throws Exception {	//Comprueba que un jugador pueda unirse a una partida

		AtomicReference<String> firstMsg = new AtomicReference<String>();

		WebSocketClient ws = new WebSocketClient();
		
		//Cuando me llega un mensaje, lo recupero así
		ws.onMessage((session, msg) -> {
			System.out.println("TestMessage: " + msg);
			firstMsg.compareAndSet(null, msg);	//Cada vez que me llega un mensaje, lo pongo en firstMsg
		});

		ws.connect("ws://127.0.0.1:9000/spacewar");
		System.out.println("Connected");
		Thread.sleep(1000);
		String msg = firstMsg.get();

		//Si lo que me ha enviado contiene la palabra join, haz esto verdadero, si no, ponlo falso e informame
		assertTrue("The fist message should contain 'join', but it is " + msg, msg.contains("JOIN"));	
		ws.disconnect();
	}
	
	//TO DO: Tests de inicio automático de juego, inicio manual del juego, fin de juego con un jugador, 
	//espera para entrar, y de carga 
	@Test
	public void testInicioAutomatico() throws Exception {
		//Aquí habrá que crear threads. P.e. qué sucede al llegar el 5o thread, ...
	}
	@Test
	public void testInicioManual() throws Exception {
		
	}
	@Test
	public void testFinDeJuego() throws Exception {
		
	}
	@Test
	public void testEsperaParaEntrar() throws Exception {
		
	}
	@Test
	public void testDeCarga() throws Exception {
		
	}
}
