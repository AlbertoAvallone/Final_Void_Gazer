package execution;

import javax.swing.SwingUtilities;


public class TestApp {
	/* main */
	public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new GameFrame();
				}
			});
		}
}

