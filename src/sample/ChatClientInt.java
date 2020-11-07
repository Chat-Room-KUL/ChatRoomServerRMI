package sample;

import java.rmi.*;
import java.io.IOException;

public interface ChatClientInt extends Remote{
    public void tell (String name) throws RemoteException ;
    public String getName()throws RemoteException ;
}