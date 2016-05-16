package jam;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Implementa un GUI que sive para controlar un objeto JAMAgent.
 * 
 * Implementa una GUI che sive per controllare un oggetto JAMAgent.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class JAMAgentMonitor extends JFrame implements Observer {

	private JButton init;
	private JButton start;
	private JButton destroy;
	private JButton exit;
	private static JTextArea log;
	private JLabel console1;
	private JLabel console2;
	// Parte media
	private JPanel panel;
	// Panel inferior
	private JPanel panel2;
	// RMI iniciado?
	private boolean iniciado;
	private static JPanel inferior;
	private JAMAgent agent;

	/**
	 * Contructor del GUI JAMAgentMonitor.
	 * 
	 * Costruttore JAMAgentMonitor GUI.
	 * 
	 * @param ag
	 */
	public JAMAgentMonitor(JAMAgent ag) {
		super("Agent Directory Service Layer Monitor");
		agent = ag;
		agent.addObserver(this);
		// Look and feel
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		init = new JButton("Init");
		start = new JButton("Start");
		destroy = new JButton("Destroy");
		exit = new JButton("exit");
		log = new JTextArea(6, 22);
		// Salto del log entre palabras
		log.setLineWrap(true);
		log.setWrapStyleWord(true);
		// No permitir la edicion del log por el usuario
		log.setEditable(false);
		// Tamaño de letra del log
		log.setFont(log.getFont().deriveFont(10f));
		console1 = new JLabel("Conection");
		console2 = new JLabel("Console:");
		iniciado = false;

		this.setLayout(new BorderLayout());

		panel = new JPanel();
		panel2 = new JPanel();

		// Los añadimos al JFrame
		this.add(panel);
		this.add(panel2);

		// Label, log y botones
		inferior = new JPanel();
		inferior.setLayout(new BorderLayout(15, 0));
		panel.add(inferior, BorderLayout.CENTER);
		JPanel textoconsola = new JPanel();
		textoconsola.setLayout(new BoxLayout(textoconsola, BoxLayout.Y_AXIS));

		textoconsola.add(console1, BorderLayout.NORTH);
		textoconsola.add(console2, BorderLayout.SOUTH);
		inferior.add(textoconsola, BorderLayout.LINE_START);
		inferior.add(log, BorderLayout.CENTER);
		// Añadir scroll al JTextArea
		JScrollPane scrollPane = new JScrollPane(log);
		inferior.add(scrollPane);
		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(3, 0));
		botones.add(init);
		botones.add(start);
		botones.add(destroy);
		inferior.add(botones, BorderLayout.LINE_END);

		// boton exit
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(panel2);
		exit.setPreferredSize(new Dimension(388, 30));
		panel2.add(exit);

		// Listeners JButtons
		// Boton init
		init.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					initJAMAgent();
					iniciado = true;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Fallo al realizar el lookup.", "Error",
							JOptionPane.ERROR_MESSAGE);
					iniciado = false;
				}
			}
		});

		// Boton Start
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (iniciado) {
					try {
						startJAMAgent();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"Fallo al realizar el Rebind.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"No esta realizado el lookup.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// Boton destroy
		destroy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (iniciado) {
					try {
						destroyJAMAgent();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"Fallo al quitar el box.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"No esta realizado el lookup.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		// Boton exit
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});		 
		
		// Se muestra
		setLayout(new BoxLayout(getContentPane(),
				BoxLayout.Y_AXIS));
		pack();
		setSize(415, 245);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Efectua el lookup del registro RMI e inscribe su RemoteMessageBox.
	 * 
	 * Effettuato il Registro di sistema di ricerca RMI e inscrivere il suo
	 * RemoteMessageBox.
	 * 
	 * @throws JAMADSLException
	 */
	public void initJAMAgent() throws JAMADSLException {
		agent.init();
	}

	/**
	 * Habilita un nuevo Thread por cada comportamiento contenido en el agente.
	 * 
	 * Consente un nuovo thread per ogni comportamento contenute nel agente.
	 */
	public void startJAMAgent() {
		agent.start();
	}

	/**
	 * Elimina del ADSL el RemoteMessageBox.
	 * 
	 * Elimina la RemoteMessageBox dall ADSL.
	 * 
	 * @throws JAMADSLException
	 */
	public void destroyJAMAgent() throws JAMADSLException {
		agent.destroy();
	}

	/**
	 * Actualiza el log del GUI.
	 * 
	 * Aggiorna il log della GUI.
	 */
	public void update(Observable arg0, Object arg1) {
		log.append((String) arg1 + "\n");
	}

}
