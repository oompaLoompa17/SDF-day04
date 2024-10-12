package src;

import java.io.*;
import java.net.*;
import day04.ClientHandler;
import java.util.concurrent.*;

public class ThreadedServerMain {

    // create a thread pool
    ExecutorService thrPool = Executors.newFixedThreadPool(2);
    String threadName = Thread.currentThread().getName();

    // Set the default port to 3000
    int port = 3000;

    if (args.length > 0){
        port = Integer.parseInt(args[0]);
    }
    // Create a server port, TCP
    ServerSocket server = new ServerSocket(port);

    while (true) { 
        // Wait for an incoming connection
        System.out.printf("Waiting for connection on port %d\n", port);

        Socket sock = server.accept();
        System.out.println("Got a new connection");
        
        // Create a worker to handle the work
        ClientHandler handler = new ClientHandler(sock);
        // Pass work to worker
        thrPool.submit(handler);
    }
    
}
