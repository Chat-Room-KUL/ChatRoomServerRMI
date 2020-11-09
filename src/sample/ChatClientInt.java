package sample;

import java.rmi.*;
import java.io.IOException;

public interface ChatClientInt extends Remote{
    public void tell (String chatroom, String name, String sender) throws RemoteException ;
    public String getName()throws RemoteException ;
}