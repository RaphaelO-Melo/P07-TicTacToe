/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senac.jogos.trabalho2;

import javax.swing.JButton;
import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 *
 * @author raphael.omelo
 */
public class ThreadInput implements Runnable{
    private String idAdversario;    //String que guarda o símbolo do adversário
    private JButton [] b;           //Vetor que guarda o vetor de botões
    private InputStream input;      //InputStream que guarda o InputStream
    private boolean meuTurno = true;
    private String vencedor;
    
    /**
     * @param botoes : Array de botões recebido
     * @param input : InputStream recebido
     * @param idA : ID do adversário
     * @throws IOException 
     */
    public ThreadInput(JButton[] botoes,InputStream input, String idA) 
            throws IOException{
        this.input = input;
        this.b = botoes;
        this.idAdversario = idA;
    }
    
    @Override
    public void run() {
        //Loop que prende a Thread para sempre estar lendo o input
        while (true) {
            try {
                InputStreamReader reader = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(reader);
                //Trava no readLine() até ter alguma coisa para ler
                String pressedButton = br.readLine();
                meuTurno = true;
                //Quando ler, seta o botão recebido com o ID do adversário
                b[Integer.parseInt(pressedButton)].setText(idAdversario);

                if (checaFimJogo()) {
                    JOptionPane.showMessageDialog(null, vencedor + " Ganhou!");
                }
                if (checaEmpate()) {
                    JOptionPane.showMessageDialog(null, "Deu velha!");
                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadInput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean meuTurno(){
        return meuTurno;
    }
    
    public void setMeuTurno(boolean turno){
        this.meuTurno = turno;
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
}
