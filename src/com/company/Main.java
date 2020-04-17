package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Main {

    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(6780);
            System.out.println("Socket oprettet. Lytter til klient.");
            byte[] receiveArray = new byte[1000];

            DatagramPacket receivePacket = new DatagramPacket(receiveArray, receiveArray.length);
            datagramSocket.receive(receivePacket); // Modtager data fra klienten - receive() blokere.
            System.out.println("Modtaget data fra klient.");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

    }
}
