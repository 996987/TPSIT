package src.SocketTCP;

import java.net.*;  // Importa le classi per la gestione delle reti
import java.io.*;   // Importa le classi per la gestione dell'input/output

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            //Crea un oggetto ServerSocket che ascolta le connessioni sulla porta 4999
            serverSocket = new ServerSocket(4999);
            System.out.println("Server in ascolto sulla porta 4999...");


            clientSocket = serverSocket.accept();
            System.out.println("Client connesso");

            //Ottiene il flusso di input dal client
            InputStreamReader in = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            //Legge una linea di testo inviata dal client
            String str = bf.readLine();
            System.out.println("Messaggio dal client: " + str);

            //otiene il flusso di output verso il client (per inviare dati al client)
            PrintWriter pr = new PrintWriter(clientSocket.getOutputStream());
            pr.println("Ciao client, messaggio ricevuto!");
            pr.flush();

        } catch (BindException e) {
            System.err.println("Errore di bind: la porta 4999 è già in uso.");
        } catch (SocketException e) {
            System.err.println("Errore di socket: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Errore di I/O: " + e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.err.println("Errore durante la chiusura delle connessioni: " + e.getMessage());
            }
        }
    }
}
