package src;

import java.io.*;
import java.net.*;

public class ServerMain {
   public static void main(String[] args) throws IOException {
        // Set the default port to 3000
        int port = 3000;

        if (args.length > 0)
            port = Integer.parseInt(args[0]);

        // Create a server port, TCP
        ServerSocket server = new ServerSocket(port);

        while (true) { 
            // Wait for an incoming connection
            System.out.printf("Waiting for connection on port %d\n", port);

            Socket sock = server.accept();
            System.out.println("Got a new connection");
            
            // Get the input stream
            InputStream is = sock.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);
            
            // Get the output stream
            OutputStream os = sock.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            // Read from client
            String fromClient = br.readLine();
            
        }
    }
}