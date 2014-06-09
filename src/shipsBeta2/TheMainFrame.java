/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shipsBeta2;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.*;

/**
 *
 * @author Qbass
 */
public class TheMainFrame extends javax.swing.JFrame {
    
    private boolean battlemode = false;
    public static int[] shipsAvailable = new int[4];
    private Socket socket = null;
    private Socket commSocket = null;
    private ObjectInputStream oin = null;
    private ObjectOutputStream oout = null;
    private ObjectInputStream commIn = null;
    private ObjectOutputStream commOut = null;
    private final int PORT = 8980;
    private int player = -2;
    private int gametype = 0;
    private Message message;

    
    private void initShips(int gametype){
        switch(gametype){
            case 0:{    //standard game
                TheMainFrame.shipsAvailable[0] = 4;
                TheMainFrame.shipsAvailable[1] = 3;
                TheMainFrame.shipsAvailable[2] = 2;
                TheMainFrame.shipsAvailable[3] = 1;
            }
        }
    }
    /**
     * Creates new form NewJFrame
     */
    public TheMainFrame() {
        initComponents();
        pGame.putClientProperty("ship", -1);
        pGame.putClientProperty("battlemode", (boolean)false);
        this.initShips(gametype);
        this.refreshCounts();
        this.lGameMode.setText("Setting Mode");
    }
    
    private void refreshCounts(){
        this.lShipDestroyerCount.setText(String.valueOf(shipsAvailable[0]));
        this.lShipCruiserCount.setText(String.valueOf(shipsAvailable[1]));
        this.lShipBattleshipCount.setText(String.valueOf(shipsAvailable[2]));
        this.lShipCarrierCount.setText(String.valueOf(shipsAvailable[3]));
    }
    
    private boolean checkCounts(){
        for(int i : shipsAvailable)
            if(i>0) return false;        
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bConnect = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        pGame = new MainPanel(0);
        pGameInfo = new javax.swing.JPanel();
        lShipsAvailable = new javax.swing.JLabel();
        lShipCruiser = new javax.swing.JLabel();
        lShipDestroyer = new javax.swing.JLabel();
        lShipBattleship = new javax.swing.JLabel();
        lShipCarrier = new javax.swing.JLabel();
        lShipDestroyerCount = new javax.swing.JLabel();
        lShipCruiserCount = new javax.swing.JLabel();
        lShipBattleshipCount = new javax.swing.JLabel();
        lShipCarrierCount = new javax.swing.JLabel();
        pGame2 = new MainPanel(1);
        bAccept = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        lPlayer = new javax.swing.JLabel();
        lPlayerIdValue = new javax.swing.JLabel();
        pStatusBar = new javax.swing.JPanel();
        lInfo = new javax.swing.JLabel();
        lGameMode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("..::The Ships Beta 0.02b::..");

        bConnect.setText("Connect");
        bConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConnectActionPerformed(evt);
            }
        });

        bExit.setText("Exit");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        pGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pGameMouseClicked(evt);
            }
        });
        pGame.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pGameMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout pGameLayout = new javax.swing.GroupLayout(pGame);
        pGame.setLayout(pGameLayout);
        pGameLayout.setHorizontalGroup(
            pGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );
        pGameLayout.setVerticalGroup(
            pGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        lShipsAvailable.setText("Ships available:");

        lShipCruiser.setText("Cruisers(3): ");
        lShipCruiser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lShipCruiserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lShipCruiserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lShipCruiserMouseExited(evt);
            }
        });

        lShipDestroyer.setText("Destroyers(2):");
        lShipDestroyer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lShipDestroyerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lShipDestroyerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lShipDestroyerMouseExited(evt);
            }
        });

        lShipBattleship.setText("Battleships(4):");
        lShipBattleship.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lShipBattleshipMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lShipBattleshipMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lShipBattleshipMouseExited(evt);
            }
        });

        lShipCarrier.setText("Aircraft Carriers(5): ");
        lShipCarrier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lShipCarrierMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lShipCarrierMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lShipCarrierMouseExited(evt);
            }
        });

        lShipDestroyerCount.setText("4");

        lShipCruiserCount.setText("3");

        lShipBattleshipCount.setText("2");

        lShipCarrierCount.setText("1");

        javax.swing.GroupLayout pGame2Layout = new javax.swing.GroupLayout(pGame2);
        pGame2.setLayout(pGame2Layout);
        pGame2Layout.setHorizontalGroup(
            pGame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pGame2Layout.setVerticalGroup(
            pGame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        bAccept.setText("Accept");
        bAccept.setEnabled(false);
        bAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAcceptActionPerformed(evt);
            }
        });

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pGameInfoLayout = new javax.swing.GroupLayout(pGameInfo);
        pGameInfo.setLayout(pGameInfoLayout);
        pGameInfoLayout.setHorizontalGroup(
            pGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pGameInfoLayout.createSequentialGroup()
                .addComponent(pGame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pGameInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pGameInfoLayout.createSequentialGroup()
                        .addGroup(pGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lShipCarrier, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(lShipBattleship, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lShipCruiser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lShipDestroyer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lShipBattleshipCount)
                            .addComponent(lShipCruiserCount)
                            .addComponent(lShipDestroyerCount)
                            .addComponent(lShipCarrierCount)))
                    .addGroup(pGameInfoLayout.createSequentialGroup()
                        .addComponent(lShipsAvailable)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pGameInfoLayout.createSequentialGroup()
                        .addComponent(bAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bReset)))
                .addContainerGap())
        );
        pGameInfoLayout.setVerticalGroup(
            pGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pGameInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pGameInfoLayout.createSequentialGroup()
                        .addComponent(lShipDestroyerCount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lShipCruiserCount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lShipBattleshipCount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lShipCarrierCount))
                    .addGroup(pGameInfoLayout.createSequentialGroup()
                        .addComponent(lShipsAvailable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lShipDestroyer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lShipCruiser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lShipBattleship)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lShipCarrier)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAccept)
                    .addComponent(bReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pGame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );

        lPlayer.setText("Player ID:");

        lPlayerIdValue.setText("N/C");

        lInfo.setText("Info...");

        javax.swing.GroupLayout pStatusBarLayout = new javax.swing.GroupLayout(pStatusBar);
        pStatusBar.setLayout(pStatusBarLayout);
        pStatusBarLayout.setHorizontalGroup(
            pStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pStatusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lInfo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pStatusBarLayout.setVerticalGroup(
            pStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pStatusBarLayout.createSequentialGroup()
                .addComponent(lInfo)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        lGameMode.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bConnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lPlayer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lPlayerIdValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lGameMode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bExit))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pGameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bConnect)
                        .addComponent(bExit)
                        .addComponent(lGameMode))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lPlayer)
                        .addComponent(lPlayerIdValue)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pGameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bExitActionPerformed

    private void lShipCarrierMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipCarrierMouseEntered
        lShipCarrier.setFont(lShipCarrier.getFont().deriveFont(Font.BOLD));                                       
        lShipCarrierCount.setFont(lShipCarrierCount.getFont().deriveFont(Font.BOLD));
    }//GEN-LAST:event_lShipCarrierMouseEntered

    private void lShipCarrierMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipCarrierMouseExited
        if(addShipSelected != 3){
            lShipCarrier.setFont(lShipCarrier.getFont().deriveFont(Font.PLAIN));                                     
            lShipCarrierCount.setFont(lShipCarrierCount.getFont().deriveFont(Font.PLAIN));
        }
    }//GEN-LAST:event_lShipCarrierMouseExited

    private void lShipBattleshipMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipBattleshipMouseEntered
        lShipBattleship.setFont(lShipBattleship.getFont().deriveFont(Font.BOLD));                                        
        lShipBattleshipCount.setFont(lShipBattleshipCount.getFont().deriveFont(Font.BOLD));
    }//GEN-LAST:event_lShipBattleshipMouseEntered

    private void lShipBattleshipMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipBattleshipMouseExited
        if(addShipSelected != 2){
            lShipBattleship.setFont(lShipBattleship.getFont().deriveFont(Font.PLAIN));
            lShipBattleshipCount.setFont(lShipBattleshipCount.getFont().deriveFont(Font.PLAIN));
        }                                     
    }//GEN-LAST:event_lShipBattleshipMouseExited

    private void lShipCruiserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipCruiserMouseEntered
        lShipCruiser.setFont(lShipCruiser.getFont().deriveFont(Font.BOLD));                                      
        lShipCruiserCount.setFont(lShipCruiserCount.getFont().deriveFont(Font.BOLD));
    }//GEN-LAST:event_lShipCruiserMouseEntered

    private void lShipCruiserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipCruiserMouseExited
        if(addShipSelected != 1){
            lShipCruiser.setFont(lShipCruiser.getFont().deriveFont(Font.PLAIN));                                    
            lShipCruiserCount.setFont(lShipCruiserCount.getFont().deriveFont(Font.PLAIN));
        }
    }//GEN-LAST:event_lShipCruiserMouseExited

    private void lShipDestroyerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipDestroyerMouseEntered
        lShipDestroyer.setFont(lShipDestroyer.getFont().deriveFont(Font.BOLD));                                        
        lShipDestroyerCount.setFont(lShipDestroyerCount.getFont().deriveFont(Font.BOLD));
    }//GEN-LAST:event_lShipDestroyerMouseEntered

    private void lShipDestroyerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipDestroyerMouseExited
        if(addShipSelected != 0){
            lShipDestroyer.setFont(lShipDestroyer.getFont().deriveFont(Font.PLAIN));                                    
            lShipDestroyerCount.setFont(lShipDestroyerCount.getFont().deriveFont(Font.PLAIN));
        }
    }//GEN-LAST:event_lShipDestroyerMouseExited

    class MessThread implements Runnable{
        
        private void checkMessage(Message mess){
            System.out.println("Checking Message");
            System.out.println(mess);
            if(message.getType() == 2){
                MyPoint p = (MyPoint)message.getObj();
                if(message.getMessage().equals("HIT")){  
                    p.setCol(Color.red);                          
                    pGame2.putClientProperty("HITOP", p);
                }else if(message.getMessage().equals("NOHIT")){
                    p.setCol(Color.gray);
                    pGame2.putClientProperty("NOHITOP", p);
                }else{
                    System.err.println("message not known:"+message.getMessage());
                }
                pGame2.repaint();
            }else if(message.getType() == 0){
                System.out.println("TOKEN "+message.getMessage());
                if(message.getMessage().equals("TOKEN")) lInfo.setText("You have token now");
            }
        }

        @Override
        public void run() {
            while(true){
                Message mess;
                try {
                    mess = (Message)commIn.readObject();
                    System.out.println("Got mess");
                    this.checkMessage(mess);
                } catch (IOException ex) {
                    Logger.getLogger(TheMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TheMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
    }
    
    
    private boolean init() {
        boolean isOk = false;
        try {
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            socket = new Socket(addr, PORT);
            commSocket = new Socket(addr, PORT+1);
            System.out.println("połączono!");
            this.oin = new ObjectInputStream(this.socket.getInputStream()); //input for objects
            this.oout = new ObjectOutputStream(this.socket.getOutputStream()); // output for objects
            this.commIn = new ObjectInputStream(this.commSocket.getInputStream());
            this.commOut = new ObjectOutputStream(this.commSocket.getOutputStream());
            bConnect.setText("Connected");
            this.lInfo.setText("Connected");
            bConnect.setEnabled(false);
            isOk = true;
        } catch (IOException e) {
            System.err.println("IOErrorr!");
            bConnect.setText("Connect (Try again)");
            this.lInfo.setText("Cannot connect"); 
            isOk = false;
        }
        return isOk;
    }
    
    private class Connector implements Runnable{
        private int requestId() throws IOException, ClassNotFoundException{
            oout.writeObject(new Message(-1, "IDREQ"));
            Message response = (Message)oin.readObject();
            if(response.getType() == -1 && "IDRESP".equals(response.getMessage()))
                return (int)response.getSender();
            
            return -2;
        }
        
        public void sendShipsToServer() throws IOException{
            oout.writeObject(new Message(1, player, "SHIPS", pGame.getClientProperty("SHIPS")));
            oout.writeObject(new Message(1, player, "SHIPSS", pGame.getClientProperty("SHIPSS")));
        }
        
        public boolean checkToken(){
            boolean token = false;
            try {
                oout.writeObject(new Message(0, player, "ISTOKEN"));
                Message response = (Message)oin.readObject();
                if(response.getType() == 0 && "TOKEN".equals(response.getMessage())) return true;
            } catch (IOException ex) {
                Logger.getLogger(TheMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TheMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            return token;
        }
        
        public void shoot(MyPoint dest){
            try {
                oout.writeObject(new Message(2, player, "SHOT", dest));
                message = (Message)oin.readObject();
                if(message.getSender() == player){
                    if(message.getType() == 2){
                        MyPoint p = (MyPoint)message.getObj();
                        if(message.getMessage().equals("HIT")){  
                            p.setCol(Color.red);                          
                            pGame.putClientProperty("HIT", p);
                        }else if(message.getMessage().equals("NOHIT")){
                            p.setCol(Color.gray);
                            pGame.putClientProperty("NOHIT", p);
                        }else{
                            System.err.println("message not known:"+message.getMessage());
                        }
                        pGame.repaint();
                    }
                }
            } catch (IOException ex) {
                System.err.println("IOEx: "+ex);
            } catch (ClassNotFoundException ex) {
                System.err.println("ClassNotFound: "+ex);
            }
        }
        
        @Override
        public void run() {
            try {
                int tmpId = requestId();
                if(tmpId != -2){
                    player = tmpId;
                    System.out.println("Your id is: "+player);
                    lPlayerIdValue.setText(String.valueOf(player));
                }
                else System.err.println("Cannot assign playerId");
//                oout.writeObject(new Message(0, player, "TestMessage"));
            } catch (IOException ex) {
                Logger.getLogger(TheMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TheMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void checkMessage(){
            try {
                Message mess = (Message)oin.readObject();
            } catch (IOException ex) {
                Logger.getLogger(TheMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TheMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    Connector connector;
    MessThread mThread;
    private void bConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConnectActionPerformed
        if(init()){
            connector = new Connector();
            Thread connThread = new Thread(connector);
            connThread.start();
            this.bAccept.setEnabled(true);
            mThread = new MessThread();
            Thread messThread = new Thread(mThread);
            messThread.start();
        }
        
    }//GEN-LAST:event_bConnectActionPerformed
    int addShipSelected = -1;

    private void addSelectShip(int ship){        
        pGame.putClientProperty("ship", ship);
        lShipDestroyer.setFont(lShipDestroyer.getFont().deriveFont(Font.PLAIN));                                    
        lShipDestroyerCount.setFont(lShipDestroyerCount.getFont().deriveFont(Font.PLAIN));
        lShipCruiser.setFont(lShipCruiser.getFont().deriveFont(Font.PLAIN));                                    
        lShipCruiserCount.setFont(lShipCruiserCount.getFont().deriveFont(Font.PLAIN));
        lShipBattleship.setFont(lShipBattleship.getFont().deriveFont(Font.PLAIN));                                    
        lShipBattleshipCount.setFont(lShipBattleshipCount.getFont().deriveFont(Font.PLAIN));
        lShipCarrier.setFont(lShipCarrier.getFont().deriveFont(Font.PLAIN));                                    
        lShipCarrierCount.setFont(lShipCarrierCount.getFont().deriveFont(Font.PLAIN));
        switch(ship){
            case 0 :{
                lShipDestroyer.setFont(lShipDestroyer.getFont().deriveFont(Font.BOLD));                                        
                lShipDestroyerCount.setFont(lShipDestroyerCount.getFont().deriveFont(Font.BOLD));
                break;
            }
            case 1 :{
                lShipCruiser.setFont(lShipCruiser.getFont().deriveFont(Font.BOLD));                                    
                lShipCruiserCount.setFont(lShipCruiserCount.getFont().deriveFont(Font.BOLD));
                break;
            }
            case 2 :{                
                lShipBattleship.setFont(lShipBattleship.getFont().deriveFont(Font.BOLD));                                    
                lShipBattleshipCount.setFont(lShipBattleshipCount.getFont().deriveFont(Font.BOLD));
                break;
            }
            case 3 :{
                lShipCarrier.setFont(lShipCarrier.getFont().deriveFont(Font.BOLD));                                    
                lShipCarrierCount.setFont(lShipCarrierCount.getFont().deriveFont(Font.BOLD));
                break;
            }
                
        }
        this.refreshCounts();
    }
    private void lShipDestroyerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipDestroyerMouseClicked
        // TODO add your handling code here:
        if(addShipSelected != 0) addShipSelected = 0; else addShipSelected = -1;
        addSelectShip(addShipSelected);
    }//GEN-LAST:event_lShipDestroyerMouseClicked

    private void lShipCruiserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipCruiserMouseClicked
        // TODO add your handling code here:
        if(addShipSelected != 1) addShipSelected = 1; else addShipSelected = -1;
        addSelectShip(addShipSelected);
    }//GEN-LAST:event_lShipCruiserMouseClicked

    private void lShipBattleshipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipBattleshipMouseClicked
        // TODO add your handling code here:
        if(addShipSelected != 2) addShipSelected = 2; else addShipSelected = -1;
        addSelectShip(addShipSelected);
    }//GEN-LAST:event_lShipBattleshipMouseClicked

    private void lShipCarrierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lShipCarrierMouseClicked
        // TODO add your handling code here:
        if(addShipSelected != 3) addShipSelected = 3; else addShipSelected = -1;
        addSelectShip(addShipSelected);
    }//GEN-LAST:event_lShipCarrierMouseClicked

    private void pGameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pGameMouseMoved
        lInfo.setText("");
    }//GEN-LAST:event_pGameMouseMoved
    private boolean token;
    private void pGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pGameMouseClicked
        this.refreshCounts();
        if(battlemode){
//            System.err.println("DEBUG|BattleModeON");
            if(connector.checkToken()){
                MyPoint p = (MyPoint)pGame.getClientProperty("shot");
                if(p != null){
                    System.out.println(p);
                    connector.shoot(p);
                }
            }else{
                lInfo.setText("You have no token");
            }
        }else{
//            System.err.println("DEBUG|BattleModeOFF");
        }
    }//GEN-LAST:event_pGameMouseClicked

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        pGame.putClientProperty("isReset", (boolean)true);
        pGame.repaint();
        initShips(gametype);
        
    }//GEN-LAST:event_bResetActionPerformed

    private void bAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAcceptActionPerformed
        if(checkCounts())
            try {
                connector.sendShipsToServer();
                message = (Message)this.oin.readObject();
                System.err.println("DEBUG|ShipsSent");
                if(message.getMessage().equals("OK")){
                    pGame2.putClientProperty("SHIPS2", pGame.getClientProperty("SHIPS"));
                    pGame2.repaint();
                    pGame.putClientProperty("isReset", (boolean)true);
                    pGame.putClientProperty("battlemode", (boolean)true);
                    pGame.repaint();
                    this.bAccept.setEnabled(false);
                    this.bReset.setEnabled(false);
                }
                this.lGameMode.setText("Battle Mode!");
                this.battlemode = true;
            }catch(IOException e){
                System.err.println("IOException: "+e);
            }catch(ClassNotFoundException e){
                System.err.println("ClassNotFound: "+e);
            }
        else
            lInfo.setText("You have to use ALL ships");
    }//GEN-LAST:event_bAcceptActionPerformed

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
            java.util.logging.Logger.getLogger(TheMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TheMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TheMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TheMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TheMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAccept;
    private javax.swing.JButton bConnect;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bReset;
    private javax.swing.JLabel lGameMode;
    private javax.swing.JLabel lInfo;
    private javax.swing.JLabel lPlayer;
    private javax.swing.JLabel lPlayerIdValue;
    private javax.swing.JLabel lShipBattleship;
    private javax.swing.JLabel lShipBattleshipCount;
    private javax.swing.JLabel lShipCarrier;
    private javax.swing.JLabel lShipCarrierCount;
    private javax.swing.JLabel lShipCruiser;
    private javax.swing.JLabel lShipCruiserCount;
    private javax.swing.JLabel lShipDestroyer;
    private javax.swing.JLabel lShipDestroyerCount;
    private javax.swing.JLabel lShipsAvailable;
    private javax.swing.JPanel pGame;
    private javax.swing.JPanel pGame1;
    private javax.swing.JPanel pGame2;
    private javax.swing.JPanel pGameInfo;
    private javax.swing.JPanel pStatusBar;
    // End of variables declaration//GEN-END:variables
}