package sample;

import java.io.IOException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServer extends UnicastRemoteObject implements ChatServerInt{

    private Vector v = new Vector();
    public ChatServer() throws RemoteException{}

    public boolean login(ChatClientInt a) throws IOException {

        System.out.println(a.getName() + "  got connected....");

        a.tell("GroupChat","\nYou have Connected successfully.", a.getName());

        publish("GroupChat",a.getName()+ " has just connected.", a.getName());

        v.add(a);

        return true;
    }

    public void publish(String receiver, String s, String sender) throws RemoteException{
        if (receiver.equals("GroupChat")) {
            System.out.println("We sturen naar iedereen");
            for(int i = 0; i < v.size() ; i++){
                try{
                    ChatClientInt tmp = (ChatClientInt) v.get(i);
                    tmp.tell("GroupChat", s, sender);
                }catch(Exception e){
                    //problem with the client not connected.
                    //Better to remove it
                }
            }
        }
        else {
            System.out.println("Prive bericht");
            boolean found1 = false;

            int i = 0;
            while (!found1) {
                ChatClientInt tmp = (ChatClientInt) v.get(i);
                if (tmp.getName().equals(receiver)) {
                    found1 = true;
                    tmp.tell(sender, s, sender);
                    System.out.println(sender);
                }
                i++;
            }

        }
    }


    public Vector getConnected() throws RemoteException{
        return v;
    }

    public void deleteClient(String name) throws RemoteException {
        for (int i = 0; i < v.size(); i++) {
            ChatClientInt tmp = (ChatClientInt) v.get(i);
            if (tmp.getName().equals(name)) {
                v.remove(i);
                publish("GroupChat", name + " has just disconnected.", name);
                System.out.println(name + "  got disconnected....");
            }
        }
    }

    public boolean checkIfNameExists(String answer) throws RemoteException {
        for (int i = 0; i < v.size(); i++) {
            ChatClientInt tmp = (ChatClientInt) v.get(i);
            if (tmp.getName().equals(answer)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getAllTheUsers() throws RemoteException {
        List<String> allTheUsers = new ArrayList<String>();
        for (int i = 0; i < v.size(); i++) {
            ChatClientInt tmp = (ChatClientInt) v.get(i);
            allTheUsers.add(tmp.getName());
        }
        return allTheUsers;
    }
}