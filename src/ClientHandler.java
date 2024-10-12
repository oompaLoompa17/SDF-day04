package src;

import java.net.*;
import java.io.*;

// Work for a thread ot perform
public class ClientHandler implements Runnable {

    public final Socket sock;

    public  ClientHandler (Socket s){
        sock = s;
    }

    // Entry point for the thread
    @Override
    public void run() {
        
        String threadName = Thread.currentThread().getName();

        try{
            // Get the input stream
            InputStream is = sock.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);
            
            // Get the output stream
            OutputStream os = sock.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);
            
            // Read from the client
            String fromClient = br.readLine();
            System.out.printf(">>> CLIENT: %s\n", fromClient);
            
            // Process the data
            fromClient = (new Date()).toString() + " " + fromClient.toUpperCase();
            
            // Write result back to client
            bw.write(fromClient);
            bw.newLine();
            bw.flush();
            os.flush();
            os.close();
            is.close();
            sock.close();
        } catch (IOException ex){
            // Exception handler is placed here and not on the run() method,
            // ie the responsibility of handling the exception falls on the catch situation
            // putting on the method means you throw the exception for something else to handle
            ex.printStackTrace();
        }
    }
}
