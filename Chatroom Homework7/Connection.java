/**
 * This is the separate thread that services each
 * incoming echo client request.
 *
 * @author Greg Gagne 
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class Connection implements Runnable
{
 private Socket client;
 private Vector list;
 private static Handler handler = new Handler();
 
 public Connection(Socket client, Vector list) {
  this.client = client;
  this.list = list;
 }

    /**
     * This method runs in a separate thread.
     */ 
 public void run() { 
  try {
   handler.process(client,list);
  }
  catch (java.io.IOException ioe) {
   System.err.println(ioe);
  }
 }
}

