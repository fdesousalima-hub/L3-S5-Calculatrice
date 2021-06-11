package fr.up.calculatrice;

import fr.up.calculatrice.shell.Shell;

/**
 * Main for shell
 *
 * @see Shell
 */
public final class App {
	public static void main(String[] args) {
		Shell shell = new Shell();
		new Thread(shell, "shell").start();
	}
}
