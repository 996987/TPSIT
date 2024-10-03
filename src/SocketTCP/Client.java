package src.SocketTCP;

import java.io.*;  // Importa le classi per la gestione dell'input/output
import java.net.*; // Importa le classi per la gestione delle reti

public class Client {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            // Connessione al server
            socket = new Socket("localhost", 4999);

            // Imposta un timeout di 5 secondi (5000 millisecondi) per le operazioni di lettura dal socket
            socket.setSoTimeout(5000);

            // Invia un messaggio al server
            PrintWriter pr = new PrintWriter(socket.getOutputStream());
            pr.println("Ciao server!");
            pr.flush();

            // Lettura della risposta dal server
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            // Legge la risposta con timeout
            String str = bf.readLine();
            System.out.println("Messaggio dal server: " + str);

        } catch (UnknownHostException e) {
            System.err.println("Errore: host sconosciuto.");
        } catch (ConnectException e) {
            System.err.println("Errore: impossibile connettersi al server.");
        } catch (SocketTimeoutException e) {
            System.err.println("Errore: timeout di connessione o di lettura.");
        } catch (SocketException e) {
            System.err.println("Errore di socket: " + e.getMessage());
        } catch (InterruptedIOException e) {
            System.err.println("Errore: operazione interrotta a causa di un timeout.");
        } catch (IOException e) {
            System.err.println("Errore di I/O: " + e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();  // Chiude il socket per liberare le risorse
                }
            } catch (IOException e) {
                System.err.println("Errore durante la chiusura del socket: " + e.getMessage());
            }
        }
    }
}
