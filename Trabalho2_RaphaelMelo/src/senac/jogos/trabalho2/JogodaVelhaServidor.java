package senac.jogos.trabalho2;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author
 */
public class JogodaVelhaServidor extends javax.swing.JFrame implements ActionListener {

    private JButton[] b = new JButton[9];
    private GridLayout game_layout;
    private BorderLayout main_layout;
    private JLabel labelPlacar = new JLabel("Cliente: 0  Servidor:0");
    private int gameCounter = 0;

    private OutputStream output;
    private InputStream input;
    private ServerSocket socketServidor;
    private ThreadInput threadInput;
    private String vencedor;

    public JogodaVelhaServidor() throws IOException {
        //Configura botoes
        for (int i = 0; i < 9; i++) {
            b[i] = new javax.swing.JButton();
            b[i].setText("");
            b[i].addActionListener(this);
        }

        //Define o layout do jogo da velha
        JPanel panel_game = new JPanel();
        game_layout = new GridLayout(3, 3);
        panel_game.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel_game.setLayout(game_layout);
        //Adiciona os botoes ao layout do jogo
        for (int i = 0; i < 9; i++) {
            panel_game.add(b[i], 0);
        }

        //Define o layou do score
        JPanel panel_score = new JPanel();
        panel_score.add(labelPlacar);

        //Define o layout principal
        //Jogo da velha no centro, placar ao lado direito           
        main_layout = new BorderLayout();
        getContentPane().setLayout(main_layout);
        add(panel_game, BorderLayout.CENTER);
        add(panel_score, BorderLayout.EAST);

        //Cria socket do servidor
        socketServidor = new ServerSocket(4567);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == b[i]) {
                if (possoJogar(i)) {
                    b[i].setText("X");
                    PrintWriter pw = new PrintWriter(output, true);
                    pw.println(i);
                    threadInput.setMeuTurno(false);
                    
                    if(checaFimJogo()){
                        JOptionPane.showMessageDialog(null, vencedor + " Ganhou!");
                    }
                    if (checaEmpate()){
                        JOptionPane.showMessageDialog(null, "Deu velha!");
                    }
                }
            }
        }
    }
    
    public boolean possoJogar(int i){
        if(threadInput.meuTurno()){
            if(!"X".equals(b[i].getText()) && !"O".equals(b[i].getText())){
                return true;
            }        
        }
        return false;
    }
    
    public boolean checaFimJogo(){
        //Checa possibilidades de vitória
        if (b[8].getText().equals(b[7].getText()) && b[7].getText().equals(b[6].getText())
                && ("X".equals(b[8].getText()) || "O".equals(b[8].getText()))) {
            if ("X".equals(b[8].getText())) {
                vencedor = "Servidor";
            } else {
                vencedor = "Cliente";
            }
            return true;
        }
        
        
        else if (b[5].getText().equals(b[4].getText()) && b[4].getText().equals(b[3].getText())
                && ("X".equals(b[5].getText()) || "O".equals(b[5].getText()))) {
            if ("X".equals(b[5].getText())) {
                vencedor = "Servidor";
            } else {
                vencedor = "Cliente";
            }
            return true;
        }
            
            
        else if (b[2].getText().equals(b[1].getText()) && b[1].getText().equals(b[0].getText())
                && ("X".equals(b[2].getText()) || "O".equals(b[2].getText()))) {
            if ("X".equals(b[2].getText())) {
                vencedor = "Servidor";
            } else {
                vencedor = "Cliente";
            }
            return true;
        }
            
            
        else if (b[8].getText().equals(b[4].getText()) && b[4].getText().equals(b[0].getText())
                && ("X".equals(b[8].getText()) || "O".equals(b[8].getText()))) {
            if ("X".equals(b[8].getText())) {
                vencedor = "Servidor";
            } else {
                vencedor = "Cliente";
            }
            return true;
        }
        
        
        else if (b[6].getText().equals(b[4].getText()) && b[4].getText().equals(b[2].getText())
                && ("X".equals(b[6].getText()) || "O".equals(b[6].getText()))) {
            if ("X".equals(b[6].getText())) {
                vencedor = "Servidor";
            } else {
                vencedor = "Cliente";
            }
            return true;
        }
        
        
        else if (b[0].getText().equals(b[3].getText()) && b[3].getText().equals(b[6].getText())
                && ("X".equals(b[0].getText()) || "O".equals(b[0].getText()))) {
            if ("X".equals(b[0].getText())) {
                vencedor = "Servidor";
            } else {
                vencedor = "Cliente";
            }
            return true;
        }
        
        
        else if (b[1].getText().equals(b[4].getText()) && b[4].getText().equals(b[7].getText())
                && ("X".equals(b[1].getText()) || "O".equals(b[1].getText()))) {
            if ("X".equals(b[1].getText())) {
                vencedor = "Servidor";
            } else {
                vencedor = "Cliente";
            }
            return true;
        }
        
        
        else if (b[2].getText().equals(b[5].getText()) && b[5].getText().equals(b[8].getText())
                && ("X".equals(b[2].getText()) || "O".equals(b[2].getText()))) {
            if ("X".equals(b[2].getText())) {
                vencedor = "Servidor";
            } else {
                vencedor = "Cliente";
            }
            return true;
        }

        return false;
    }
    
    public boolean checaEmpate(){
        //Checa empate
        int cont = 0;
        for (int i = 0; i < 9; i++) {
            if ("X".equals(b[i].getText()) || "O".equals(b[i].getText())) {
                cont++;
            }
        }
        if (cont == 9) {
            System.out.println("EMPATE");
            return true;
        }
        return false;
    }
    

    public void init() throws UnknownHostException, IOException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        int porta = socketServidor.getLocalPort();

        JOptionPane.showMessageDialog(null, "Aguardando cliente conectar. \n"
                + "IP do servidor: " + ip + " \n"
                + "Porta do servidor: " + porta);

        //Cria o input e o output
        Socket cliente = socketServidor.accept();
        input = cliente.getInputStream();
        output = cliente.getOutputStream();
        
        //Cria a thread que vai ficar escutando a resposta do adversário
        this.threadInput = new ThreadInput(b, input, "O");
        new Thread(threadInput).start();
        threadInput.run();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        JogodaVelhaServidor jogo = new JogodaVelhaServidor();
        jogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jogo.setSize(300, 200); // set frame size
        jogo.setVisible(true);
        jogo.setTitle("Servidor");
        jogo.init();
    }

}
