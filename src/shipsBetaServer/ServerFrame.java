/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shipsBetaServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shipsBeta2.MyPoint;

/**
 * Klasa okienka serwera. Obsługuje połączenia klienckie
 * @author Jakub
 */
public class ServerFrame extends javax.swing.JFrame {
    
    private static int howManyPlayers = 0;
    
    public static int getHowManyPlayers(){
        return howManyPlayers;
    }
    
    public static void incHowManyPlayers(){
        howManyPlayers++;
    }
    /**
     * Klasa pomocnicza obsługująca plansze konkretnych graczy
     */
    private static class Board{
        private int id;
        private ArrayList<MyPoint> points;
        /**
         * Konstruktor planszy dla konkretnego gracza
         * @param id identyfikator gracza
         * @param pts punkty statków ustawione przez gracza
         */
        public Board(int id, ArrayList<MyPoint> pts){
            this.id = id;
            this.points = pts;
        }
        
        public int getId(){
            return this.id;
        }
        
        public ArrayList getPoints(){
            return this.points;
        }
        
        @Override
        public String toString(){
            String str = "";
            str += this.id+"\n";
            for (MyPoint p : this.points)
                str += p.toString()+"\n";
            return str;
        }
    }
    
    private static ArrayList<ArrayList<MyPoint>> points = new ArrayList<ArrayList<MyPoint>>();
    private static ArrayList<Board> boards = new ArrayList<Board>();

    /**
     * Creates new form Server
     */
    public ServerFrame() {
        initComponents();
    }
    private static int whoHasToken = -1;

    public static int getWhoHasToken() {
        return whoHasToken;
    }

    public static void setWhoHasToken(int whoHasToken) {
        ServerFrame.whoHasToken = whoHasToken;
    }
    
    public static void addBoard(int playerId, ArrayList<MyPoint> pts){
        boards.add(new Board(playerId, pts));
    }
    
    public static ArrayList getBoard(int id){
        for(Board b : boards){
            if(b.getId() == id) return b.getPoints();
        }
        return null;
    }
    
    private ServerSocket sockfd;
    private ServerSocket commSockfd;
    private static ArrayList<Server> servers = new ArrayList<Server>();

    public static ArrayList<Server> getServers() {
        return servers;
    }
    private boolean stop = false;
    /**
     * Metoda odpowiedzialna za uruchomienie serwera
     * @param addr adres IP serwera do nasłuchu
     * @param port port serwera do nasłuchu
     */
    private void runServer(String addr, int port){
        System.out.println("DEBUG|Addr:"+addr+"|Port:"+port);
        this.tLogOutput.append("DEBUG|Addr:"+addr+"|Port:"+port+"\n");
        
        this.tLogOutput.append("Waiting for connection\n");
        class Ex implements Runnable{ 
            public List<Thread> thrli = new ArrayList<Thread>();
            public boolean interrupted = false;
            public void doExit(){
                tLogOutput.append("Waiting for clients disconnection...\n");
                while(true){
                    int alive = 0;
                    for (Thread t : thrli){
//                        t.interrupt();
                        if(t.isAlive()) alive++;
                    }
                    if(alive == 0){
                        this.interrupted = true;
                        break;
                    }                
                }
            }
            @Override
            public void run(){
                while(true){
                    if(stop) doExit();
                }
            }
        }
        Ex ex = new Ex();
        Thread exth = new Thread(ex);
        exth.start();   
        
        class Run implements Runnable{
            private Ex ex;
            private int playerCount = -1;
            public Run(Ex ex){
                this.ex = ex;
            }


            @Override
            public void run() {
                while(!ex.interrupted){ 
//                    wait for client to connect
                    try{
                        Socket tmpsockfd = sockfd.accept();
                        if(tmpsockfd != null){
                            Socket tmpcommsockfd = commSockfd.accept();
                            playerCount++;
                            tLogOutput.append("Client connected from "+tmpsockfd.getInetAddress().getHostAddress()+"\n");
                            servers.add(new Server(tmpsockfd, tmpcommsockfd, playerCount));
                            ex.thrli.add(new Thread(servers.get(servers.size()-1))); //TODO Servicing Class
                            ex.thrli.get(ex.thrli.size()-1).start();
                        }
                    }catch(SocketTimeoutException e){

                    }catch(IOException e){
                        System.err.println("Could not connect Client.");
                    }
                }                
                try {
                    sockfd.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex1);
                }
                System.exit(2);
            }
        }
        Run r = new Run(ex);
        Thread runth = new Thread(r);
        
            try{
                sockfd = new ServerSocket(port);
                sockfd.setSoTimeout(100);
                commSockfd = new ServerSocket(port+1);
                commSockfd.setSoTimeout(100);
                runth.start();
            }catch(IOException e){
                System.err.println("Could not listen on port: "+port);  
                System.exit(1); 
            }
        
        this.lServerStateValue.setText("Running");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bStartServer = new javax.swing.JButton();
        bStopServer = new javax.swing.JButton();
        pServerInfo = new javax.swing.JPanel();
        lServerIP = new javax.swing.JLabel();
        lServerPort = new javax.swing.JLabel();
        lServerIPValue = new javax.swing.JLabel();
        lServerPortValue = new javax.swing.JLabel();
        lServerState = new javax.swing.JLabel();
        lServerStateValue = new javax.swing.JLabel();
        pLogs = new javax.swing.JPanel();
        lLogInfo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tLogOutput = new javax.swing.JTextArea();
        bDebug = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Ships Server Beta");

        bStartServer.setText("Start Server");
        bStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartServerActionPerformed(evt);
            }
        });

        bStopServer.setText("Stop Server");
        bStopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStopServerActionPerformed(evt);
            }
        });

        lServerIP.setText("Server IP:");

        lServerPort.setText("Port:");

        lServerIPValue.setText("127.0.0.1");

        lServerPortValue.setText("8980");

        lServerState.setText("Server State:");

        lServerStateValue.setText("Not Running");

        javax.swing.GroupLayout pServerInfoLayout = new javax.swing.GroupLayout(pServerInfo);
        pServerInfo.setLayout(pServerInfoLayout);
        pServerInfoLayout.setHorizontalGroup(
            pServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pServerInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lServerIP)
                    .addComponent(lServerPort)
                    .addComponent(lServerState))
                .addGap(43, 43, 43)
                .addGroup(pServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lServerStateValue)
                    .addComponent(lServerPortValue)
                    .addComponent(lServerIPValue))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        pServerInfoLayout.setVerticalGroup(
            pServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pServerInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lServerIP)
                    .addComponent(lServerIPValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lServerPort)
                    .addComponent(lServerPortValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lServerState)
                    .addComponent(lServerStateValue))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        lLogInfo.setText("Log output:");

        tLogOutput.setEditable(false);
        tLogOutput.setColumns(20);
        tLogOutput.setRows(5);
        jScrollPane1.setViewportView(tLogOutput);

        javax.swing.GroupLayout pLogsLayout = new javax.swing.GroupLayout(pLogs);
        pLogs.setLayout(pLogsLayout);
        pLogsLayout.setHorizontalGroup(
            pLogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLogsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pLogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pLogsLayout.createSequentialGroup()
                        .addComponent(lLogInfo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pLogsLayout.setVerticalGroup(
            pLogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLogsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lLogInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );

        bDebug.setText("DebugBoards");
        bDebug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDebugActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pLogs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pServerInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(bStartServer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bStopServer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bDebug)))
                        .addGap(0, 74, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bStartServer)
                    .addComponent(bStopServer)
                    .addComponent(bDebug))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pServerInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pLogs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartServerActionPerformed
        // TODO add your handling code here:
        runServer(this.lServerIPValue.getText(), 
                Integer.valueOf(this.lServerPortValue.getText()));
    }//GEN-LAST:event_bStartServerActionPerformed

    private void stopServer(){
        this.stop = true;
    }
    private void bStopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStopServerActionPerformed
        this.stopServer();
    }//GEN-LAST:event_bStopServerActionPerformed

    private void bDebugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDebugActionPerformed
        tLogOutput.append("Boards:\n");
        for(Board b: boards){
            tLogOutput.append(b.toString());
        }
    }//GEN-LAST:event_bDebugActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDebug;
    private javax.swing.JButton bStartServer;
    private javax.swing.JButton bStopServer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lLogInfo;
    private javax.swing.JLabel lServerIP;
    private javax.swing.JLabel lServerIPValue;
    private javax.swing.JLabel lServerPort;
    private javax.swing.JLabel lServerPortValue;
    private javax.swing.JLabel lServerState;
    private javax.swing.JLabel lServerStateValue;
    private javax.swing.JPanel pLogs;
    private javax.swing.JPanel pServerInfo;
    private javax.swing.JTextArea tLogOutput;
    // End of variables declaration//GEN-END:variables
}
