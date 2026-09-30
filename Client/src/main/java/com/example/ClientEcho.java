package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientEcho {
    public static void main(String[] args) {
        //il server gira sullo stesso pc
        String serverAddress = "localhost";
        int port = 5000;

        try{
            //connessione con il server
            Socket socket = new Socket (serverAddress, port);
            System.out.println("connesso al server");

            //canale per inviare dati al server
            PrintWriter out = new PrintWriter(
                //chiede dove mandare i dati
                socket.getOutputStream(), 
                //invia immediatamente evitando bloccaggio
                true
            );

            // canale per ricevere dati dal server in memoria temporanea
            BufferedReader in = new BufferedReader(
                //caratteri leggibili
                new InputStreamReader(socket.getInputStream())
            );

            //scanner per leggere tastiera
            Scanner tastiera = new Scanner(System.in);

            System.out.println("inserisci cosa inviare al server: ");
            //aspetta una frase da inviare
            String fraseUtente = tastiera.nextLine();

            //invio frase al server
            out.println(fraseUtente);

            //rimani in attesa della risposta
            String rispostaServer = in.readLine();

            //mostra la risposta ricevuta
            System.out.println("risposta: "+ rispostaServer);

            //chiudo tutti flussi e socket
            tastiera.close();
            out.close();
            in.close();
            socket.close();

            System.out.println("connessione chiusa senza problemi.");

        }catch (UnknownHostException e){
            System.err.println("errore: impossibile trovare l'host"+ serverAddress);
        }catch (IOException e){
            System.err.println("errore: "+ e.getMessage());
        }
    }
}
