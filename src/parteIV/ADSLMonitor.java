package parteIV;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Implementa un GUI que sive para controlar un objeto ADSL.
 * 
 * Implementa una GUI che sive per controllare un oggetto ADSL.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class ADSLMonitor extends JFrame {

	private ADSLImpl adsl;
	private JButton startReg;
	private JButton startUp;
	private JButton shutdown;
	private JButton exit;
	private JTextField port;
	private static JTextArea log;
	private JLabel puerto;
	private JLabel console1;
	private JLabel console2;
	private JSeparator separador;
	// Parte superior
	private JPanel panel1;
	// Parte media
	private JPanel panel2;
	// Panel inferior
	private JPanel panel3;
	// RMI iniciado?
	private boolean iniciado;
	private static JPanel inferior;

	/**
	 * Contructor del GUI ADSLMonitor.
	 * 
	 * Costruttore ADSLMonitor GUI.
	 */
	public ADSLMonitor() {
		super("Agent Directory Service Layer Monitor");
		// Look and feel
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		startReg = new JButton("Start reg");
		startUp = new JButton("Start up");
		shutdown = new JButton("Shutdown");
		exit = new JButton("exit");
		port = new JTextField("2000");
		log = new JTextArea(6, 22);
		// Salto del log entre palabras
		log.setLineWrap(true);
		log.setWrapStyleWord(true);
		// No permitir la edicion del log por el usuario
		log.setEditable(false);
		// Tamaño de letra del log
		log.setFont(log.getFont().deriveFont(10f));
		puerto = new JLabel("Port:        ");
		console1 = new JLabel("Conection");
		console2 = new JLabel("Console:");
		separador = new JSeparator(JSeparator.HORIZONTAL);
		iniciado = false;

		// 4 filas 0 columnas
		this.setLayout(new BorderLayout(4, 0));

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		// Los añadimos al JFrame
		this.add(panel1);
		this.add(separador);
		this.add(panel2);
		this.add(panel3);

		// Label, puerto y boton startreg
		JPanel superior = new JPanel();
		superior.setLayout(new BorderLayout(22, 0));
		panel1.add(superior);
		port.setPreferredSize(new Dimension(215, 25));
		superior.add(puerto, BorderLayout.LINE_START);
		superior.add(port, BorderLayout.CENTER);
		superior.add(startReg, BorderLayout.LINE_END);

		// Label, log y botones
		inferior = new JPanel();
		inferior.setLayout(new BorderLayout(15, 0));
		panel2.add(inferior);
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
		botones.setLayout(new BorderLayout(0, 3));
		botones.add(startUp, BorderLayout.NORTH);
		botones.add(shutdown, BorderLayout.SOUTH);
		inferior.add(botones, BorderLayout.LINE_END);

		// boton exit
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(panel3);
		exit.setPreferredSize(new Dimension(388, 30));
		panel3.add(exit);

		// Listeners JButtons
		// Boton Start reg
		startReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					int p = Integer.parseInt((port.getText()));
					adsl = new ADSLImpl("127.0.0.1", "ADSL", p);
					adsl.startRMIRegistry();
					iniciado = true;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Fallo al iniciar registro.", "Error",
							JOptionPane.ERROR_MESSAGE);
					iniciado = false;
				}
			}
		});

		// Boton Start up
		startUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (iniciado) {
					try {
						adsl.startADSL();
						log.append("-> Iniciado\n");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"Fallo al realizar el Rebind.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"No esta iniciado el RMI.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// Boton Shutdown
		shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (iniciado) {
					try {
						adsl.stopADSL();
						log.append("-> Finalizado\n");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"Fallo al realizar el Unbind.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"No esta iniciado el RMI.", "Warning",
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
	}

	/**
	 * Inicia el GUI de ADSLMonitor.
	 * 
	 * Avviare la GUI ADSLMonitor.
	 */
	public static void start() {
		ADSLMonitor monitor = new ADSLMonitor();
		monitor.setLayout(new BoxLayout(monitor.getContentPane(),
				BoxLayout.Y_AXIS));
		monitor.pack();
		monitor.setSize(415, 245);
		monitor.setResizable(false);
		monitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		monitor.setVisible(true);
	}

	/**
	 * Muestra en el log cuando se usa el metodo getRemoteBox.
	 * 
	 * Mostrato nel registro quando si utilizza il metodo getRemoteBox.
	 * 
	 * @param agent
	 */
	public static void get(AgentID agent) {
		log.append("Richiesto box " + agent + "\n");
		inferior.repaint();
		// System.out.println("Richiesto box " + agent);
	}

	/**
	 * Muestra en el log cuando se usa el metodo insertRemoteBox.
	 * 
	 * Mostrato nel registro quando si utilizza il metodo insertRemoteBox.
	 * 
	 * @param agent
	 */
	public static void insert(AgentID agent) {
		log.append("Iscrizione nuovo box per " + agent + "\n");
		// System.out.println("Inscrizione box " + agent);
		inferior.repaint();
	}

	/**
	 * Muestra en el log cuando se usa el metodo removeRemoteBox.
	 * 
	 * Mostrato nel registro quando si utilizza il metodo removeRemoteBox.
	 * 
	 * @param agent
	 */
	public static void remove(AgentID agent) {
		log.append("Cancellato box  " + agent + "\n");
		// System.out.println("Cancellato box " + agent);
		inferior.repaint();
	}

	/**
	 * Metodo main.
	 * 
	 * Metodo main.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		start();
	}

}
