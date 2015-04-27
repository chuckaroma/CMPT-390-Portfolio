/**
 * Handler class containing the logic for echoing results back
 * to the client. 
 *
 * @author Charles Bailey 
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Arrays;

public class Handler
{
  
  
  /**
   * this method is invoked by a separate thread
   */
  public void process(Socket client, Vector list) throws java.io.IOException {
    ServerSocket sock = null;
    BufferedReader  fromClient = null;
    DataOutputStream toClient = null;
    String line;
    int slash = 0;
    int firstspace = 0;
    int secondspace = 0;
    String verb = "[Welcome";
    String verb1 = "[UsernameTaken";
    String login = "/login";
    String users = "/users";
    String activeUsers = "[ActiveUsers" + " ";
    String error = "[Error";
    String closeCommand = "/close";
    String connected = "[Connected";
    String disconnected = "[Disconnected";
    String pub = "/public";
    String priv = "/private";
    byte[] text = verb.getBytes("UTF-8"); 
    byte[] text1 = verb1.getBytes("UTF-8"); 
    byte[] text2 = login.getBytes("UTF-8");
    byte[] text3 = closeCommand.getBytes("UTF-8");
    byte[] text4 = error.getBytes("UTF-8");
    byte[] text5 = connected.getBytes("UTF-8");
    byte[] text6 = disconnected.getBytes("UTF-8");
    byte[] text7 = users.getBytes("UTF-8");
    byte[] text8 = activeUsers.getBytes("UTF-8");
    byte[] text9 = pub.getBytes("UTF-8");
    byte[] text10 = priv.getBytes("UTF-8");
    int bytesread = verb.length();
    int bytesread1 = verb1.length();
    int bytesread2 = login.length();
    int bytesread3 = closeCommand.length();
    int bytesread4 = error.length();
    int bytesread5 = connected.length();
    int bytesread6 = disconnected.length();
    int bytesread7 = users.length();
    int bytesread8 = activeUsers.length();
    int bytesread9 = pub.length();
    int bytesread10 = priv.length();   
    
    fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    toClient = new DataOutputStream(client.getOutputStream());
    String name = null;
    while((line = fromClient.readLine()) != null)
    {
      System.out.println(line);
      // System.out.println(userName[1]);
      String[] userName = line.split("\\s+");
      
      if(userName[0].equals(login) && list.contains(userName[1]))
      {
        toClient.write(text1,0,bytesread1);
        fromClient.readLine();
      }
      else if(userName[0].equals(login) && !list.contains(userName[1]))
      {
        toClient.write(text,0,bytesread);
        list.add(userName[1]);
        name = userName[1];
      }
      
      System.out.println(list);
      
      if (userName[0].equals("/users"))
      {
        toClient.write(text8,0,bytesread8);
        Iterator<String> itr = list.iterator();
        while (itr.hasNext())
        {
          String i = itr.next() + ",";
          byte[] iterator = i.getBytes("UTF-8");
          int readlist = i.length();
          toClient.write(iterator,0,readlist);
        }
        System.out.println(activeUsers + " " + list);
        
      }
      
      if (userName[0].equals("/public"))
      {
         Iterator<String> itr = list.iterator();
         
        while (itr.hasNext())
        {
          String message = itr.next();
         byte[] mess = message.getBytes("UTF-8");
         int readmessage = message.length();
         message = fromClient.readLine();
         toClient.write(mess,0,readmessage);
      }
      }
//      else if (userName[0].equals("/private") && userName[1].equals(line))
//      {
//        Iterator<String> itr = list.iterator();
//        String message = itr.next();
//         byte[] mess = message.getBytes("UTF-8");
//         int readmessage = message.length();
//         message = fromClient.readLine();
//        line = fromClient.readLine();
//        toClient.write(mess,0,readmessage);  
//      }
//      
    //  System.out.println(connected + " " + userName[1]);
      if (userName[0].equals("/close"))
      {
        list.remove(name);
        System.out.println(disconnected + " " + name);
        client.close();
      }
      // closing the output stream closes the socket as well
      // client.close();
    }
    
  }
}
  
  
  
  /** continually loop until the client closes the connection */
  



