package src.SocketTCP;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4999);
        Socket clientSocket = serverSocket.accept();
        System.out.println("client connected");

        InputStreamReader in = new InputStreamReader(
                clientSocket.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("client : " + str);

        PrintWriter pr = new PrintWriter(
                clientSocket.getOutputStream());
        pr.println("Ciao client, messaggio ricevuto!");
        pr.flush();

        clientSocket.close();
        serverSocket.close();
    }
}