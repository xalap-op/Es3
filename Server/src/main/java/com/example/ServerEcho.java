package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class ServerEcho {

    public static void main(String[] args) {
        int porta = 5000;
        
        System.out.println(
            "server in ascolto sulla porta "+ porta+ " ..."
        );

        try(
            // crea nuovo server socket sulla porta 5000
            ServerSocket serversocket = new ServerSocket(porta);
            // attende connessione client
            Socket socket = serversocket.accept();
            //aggiunge un buffer che permette di leggere comodamente una riga
            BufferedReader input = new BufferedReader(
                //converte dti ricevuti in caratteri
                new InputStreamReader(
                    //contiene i dati inviati da lient a server
                    socket.getInputStream()
                )
            );
            //trasforma lo stream di output in un oggetto inviabile facilmente in testo
            PrintWriter output = new PrintWriter(
                //ottiene lo stream di output server ==> client
                socket.getOutputStream(),
                //forza l'invio dei dati nel buffer
                true
            )


        ){
            //connessione stabilita, eseguito dopo che è stato accettato il client
            System.out.println("Client connesso.");
            //legge una riga client
            String messaggio=input.readLine();
            if(messaggio != null){
                //conversione standard indipendente dalla lingua
                String risposta = messaggio.toUpperCase(Locale.ROOT);
                System.out.println("Messaggio ricevuto: "+ messaggio);
                System.out.println("Risposta inviata: "+ risposta);
                output.println(risposta);

            }
            System.out.println("connessione chiusa.");
            System.out.println("Server terminato.");


        }catch( IOException e){
            //stampa messaggio di errore con descrizione
            System.err.println("Errore: "+ e.getMessage());
        }
    }
}
