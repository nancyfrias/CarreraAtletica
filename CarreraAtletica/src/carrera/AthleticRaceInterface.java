package carrera;
import javax.swing.*;
import java.awt.event.*;

public class AthleticRaceInterface extends JFrame {

    private JTextField txtName;
    private JTextArea txtRunners, txtResults;
    private JButton btnRegister, btnStart, btnReset, btnExit;

    private Runner[] runners = new Runner[5];
    private int count = 0;

    public AthleticRaceInterface() {
        setTitle("Carrera Atlética");
        setSize(400, 500);
        setLayout(null);

        // Campo nombre
        txtName = new JTextField();
        txtName.setBounds(20, 20, 200, 25);
        add(txtName);

        // Botón registrar
        btnRegister = new JButton("Registrar");
        btnRegister.setBounds(230, 20, 120, 25);
        add(btnRegister);

        // Área corredores
        txtRunners = new JTextArea();
        JScrollPane sp1 = new JScrollPane(txtRunners);
        sp1.setBounds(20, 60, 330, 100);
        add(sp1);

        // Área resultados
        txtResults = new JTextArea();
        JScrollPane sp2 = new JScrollPane(txtResults);
        sp2.setBounds(20, 180, 330, 100);
        add(sp2);

        // Botones
        btnStart = new JButton("Iniciar");
        btnStart.setBounds(20, 300, 90, 30);
        add(btnStart);

        btnReset = new JButton("Reiniciar");
        btnReset.setBounds(130, 300, 100, 30);
        add(btnReset);

        btnExit = new JButton("Terminar");
        btnExit.setBounds(250, 300, 100, 30);
        add(btnExit);

        // EVENTOS
        btnRegister.addActionListener(e -> registerRunner());
        btnStart.addActionListener(e -> startRace());
        btnReset.addActionListener(e -> resetRace());
        btnExit.addActionListener(e -> System.exit(0));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void registerRunner() {
        String name = txtName.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre vacío");
            return;
        }

        if (count >= 5) {
            JOptionPane.showMessageDialog(this, "Máximo 5 corredores");
            return;
        }

        runners[count] = new Runner(name);
        txtRunners.append(name + "\n");
        count++;
        txtName.setText("");
    }

    private void startRace() {
        if (count < 5) {
            JOptionPane.showMessageDialog(this, "Faltan corredores");
            return;
        }

        txtResults.setText("");

        for (int i = 0; i < 5; i++) {
            ThreadRunner tr = new ThreadRunner(runners[i], txtResults);
            tr.start();
        }
    }

    private void resetRace() {
        count = 0;
        runners = new Runner[5];
        txtRunners.setText("");
        txtResults.setText("");
    }

    public static void main(String[] args) {
        new AthleticRaceInterface();
    }
}