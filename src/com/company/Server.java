package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class Server {
    static HashMap<InetAddress, Integer> clients = new HashMap<>();

    public static void main(String[] args) {
        try {

            DatagramSocket datagramSocket = new DatagramSocket(6780);
            System.out.println("Socket oprettet. Lytter til klient.");
            byte[] receiveArray = new byte[1000];
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveArray, receiveArray.length);
                datagramSocket.receive(receivePacket); // Modtager data fra klienten - receive() blokere.
                clients.put(receivePacket.getAddress(), receivePacket.getPort());
                // Modtag besked fra klient.
                String inMsg = new String(receiveArray, 0, receivePacket.getLength());
                System.out.println("Besked modtaget : "+inMsg);
                // Sende svar tilbage til alle klienter:
                for (Map.Entry<InetAddress, Integer> entry: clients.entrySet()) {
                    sendTo(datagramSocket, entry.getKey(), entry.getValue(), inMsg);
                    System.out.println("Sendt data til IP: "+entry.getKey()+", Portnummer: "+entry.getValue());
                }

                if (inMsg.equalsIgnoreCase("quit")) {
                    System.out.println("Klienten har lukket forbindelsen.");
                    break;
                }
                // Send svar tilbage til klient.
                /*
                Scanner scan = new Scanner(System.in);
                String msg = scan.nextLine();
                for (Map.Entry<InetAddress, Integer> entry: clients.entrySet()) {
                    sendTo(datagramSocket, entry.getKey(), entry.getValue(), msg);
                    System.out.println("Sendt data til IP: "+entry.getKey());
                }
                */
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    static void sendTo(DatagramSocket socket, InetAddress address, int port, String message) throws IOException {

        byte[] sendArray = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendArray, sendArray.length,
               address, port);
        socket.send(sendPacket);
    }
}
