package src.SocketTCP;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4999);

        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        pr.println("Ciao server!");
        pr.flush();

        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("server: " + str);

        socket.close();
    }
}