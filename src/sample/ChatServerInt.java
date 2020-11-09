package sample;

import java.io.IOException;
import java.rmi.*;
import java.util.*;

public interface ChatServerInt extends Remote{
    public boolean login (ChatClientInt a) throws IOException;
    public void publish (String receiver, String message, String sender) throws RemoteException ;
    public Vector getConnected() throws RemoteException ;
    public void deleteClient(String name) throws RemoteException;
    public boolean checkIfNameExists(String answer) throws RemoteException;
    public List<String> getAllTheUsers() throws RemoteException;
}