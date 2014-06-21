/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shipsBetaServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import message.*;
import shipsBeta2.MyPoint;

/**
 * Klasa wątka serwera dla konkretnego klienta
 * @author Jakub
 */
public class Server implements Runnable{
    
    private final Socket sockfd;
    private final Socket commSockfd;
    private ObjectOutputStream oout; // output for objects
    private ObjectInputStream oin; //input for objects
    private ObjectOutputStream commOut;

    public ObjectOutputStream getCommOut() {
        return commOut;
    }

    public ObjectInputStream getCommIn() {
        return commIn;
    }
    private ObjectInputStream commIn;
    private String threadName;
    private Message message;
    private final int playerId;
    
    /**
     * Konstruktor wątku serwera dla kontretnego klienta
     * @param sock gniazdo połączenia gry z klientem
     * @param commSock gniazdo połączenia komunikacyjnego z klientem
     * @param id identyfikator gracza
     */
    
    public Server(Socket sock, Socket commSock, int id){
        this.commSockfd = commSock;
        this.sockfd = sock;
        this.playerId = id;
        try{
            this.oout = new ObjectOutputStream(this.sockfd.getOutputStream()); // output for objects
            this.oin = new ObjectInputStream(this.sockfd.getInputStream()); //input for objects
            this.commOut = new ObjectOutputStream(this.commSockfd.getOutputStream());
            this.commIn = new ObjectInputStream(this.commSockfd.getInputStream());
        }catch(IOException e){
            System.err.println("IOError from "+sockfd.getInetAddress().getHostAddress()+": "+e);
        }
    }
    
    @Deprecated
    public Server(Socket sock, int id){
        this.sockfd = sock;
        this.playerId = id;
        this.commSockfd = null;
        try{
            this.oout = new ObjectOutputStream(this.sockfd.getOutputStream()); // output for objects
            this.oin = new ObjectInputStream(this.sockfd.getInputStream()); //input for objects
        }catch(IOException e){
            System.err.println("IOError from "+sockfd.getInetAddress().getHostAddress()+": "+e);
        }
    }
    
    private void endThread(){
        try{
            oin.close();
            oout.close();
            sockfd.close();
            commIn.close();
            commOut.close();
            commSockfd.close();
            Thread.currentThread().interrupt();
        }catch(IOException e){
            
        }
    }
    /**
     * Metoda sprawdzająca przesłaną wiadomość od Klienta
     * @param mess Obiekt otrzymanej wiadomości
     * @throws IOException 
     */

    private void checkMessage(Message mess) throws IOException{
        if(mess.getMessage().equals("IDREQ")){
            this.oout.writeObject(new Message(this.playerId, "IDRESP"));
        }
        else if(mess.getMessage().equals("SHIPS")){
            ArrayList<MyPoint> points = (ArrayList<MyPoint>)mess.getObj();
            if(points != null){
                ServerFrame.addBoard(this.playerId, points);
//                DEBUG THINGY
//                for(MyPoint p : points){
//                    System.out.println(p);
//                } 
            }
           this.oout.writeObject(new Message(0, this.playerId, "OK"));
           ServerFrame.incHowManyPlayers();
           if(ServerFrame.getHowManyPlayers() == 2) ServerFrame.setWhoHasToken(0);
        }
        else if(mess.getMessage().equals("SHOT")){
            MyPoint shot = (MyPoint)mess.getObj();
//            System.err.println("Player:"+playerId+" shot at:"+shot);
            //temporary things.
            int opponentId;
            if(playerId == 0) opponentId = 1; else opponentId = 0;
            if(ServerFrame.getBoard(opponentId).contains(shot)){
                System.err.println("Player:"+playerId+" hit "+opponentId+" at "+shot);
                ServerFrame.getBoard(opponentId).remove(shot);
                this.oout.writeObject(new Message(2, this.playerId, "HIT", shot));
                ServerFrame.getServers().get(opponentId).commOut.writeObject(new Message(2, opponentId, "HIT", shot));
                int size = ServerFrame.getBoard(opponentId).size();
                System.out.println("Player "+opponentId+" has "+size+" ship points");
                if(size == 0){
                    ServerFrame.getServers().get(playerId).commOut.writeObject(new Message(6, playerId, "YOUWIN"));
                    ServerFrame.getServers().get(opponentId).commOut.writeObject(new Message(6, opponentId, "YOULOSE"));
                }
            }else{
                this.oout.writeObject(new Message(2, this.playerId, "NOHIT", shot));
                ServerFrame.getServers().get(opponentId).commOut.writeObject(new Message(2, opponentId, "NOHIT", shot));
            }
            if(ServerFrame.getWhoHasToken() == playerId) ServerFrame.setWhoHasToken(opponentId);
            else ServerFrame.setWhoHasToken(playerId);
            ServerFrame.getServers().get(ServerFrame.getWhoHasToken()).commOut.writeObject(new Message(0, ServerFrame.getWhoHasToken(), "TOKEN"));
            System.out.println("Token id: "+ServerFrame.getWhoHasToken());
        }
        else if(mess.getMessage().equals("ISTOKEN")){
            int tokId = ServerFrame.getWhoHasToken();
            if(mess.getSender() == tokId){
                this.oout.writeObject(new Message(0, mess.getSender(), "TOKEN"));
            }else
                this.oout.writeObject(new Message(0, mess.getSender(), "NOTOKEN"));
        }
        else if(mess.getMessage().equals("GETSHOTS")){
            
        }
    }
    @Override
    public void run() {
        this.threadName = Thread.currentThread().getName();
        while(!Thread.currentThread().isInterrupted()){
            try{
                message = (Message)this.oin.readObject();
                checkMessage(message);
//                System.out.println(message);
            } catch (SocketException e){ 
                System.err.println("Client Disconnected");
                this.endThread();
                Thread.currentThread().interrupt();
            } catch (IOException | ClassNotFoundException ex){
                System.err.println("Error: "+ex);
            }
        }
        
    }
    
}
