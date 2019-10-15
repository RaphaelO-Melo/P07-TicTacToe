/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senac.jogos.trabalho2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author raphael.omelo
 */
public class ThreadOutput implements Runnable{
    private int ref = 99;
    private JButton[] b;
    private Socket socket;
    private OutputStream output;
    private boolean waiting;
    private boolean called;

    
    public ThreadOutput(JButton b[], Socket socket) throws IOException{
        this.socket = socket;
        //this.output = socket.getOutputStream();
        this.b = b;
    }

    @Override
    public void run() {

    }
    
}


