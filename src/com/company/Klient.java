package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Klient {
    private DatagramSocket datagramSocket;
    private InetAddress address;
    private int port = 6780;

    public static void main(String[] args) {
        new Klient();
    }

    public Klient() {
        UserInput userInput = new UserInput(this);
        Thread userInputThread = new Thread(userInput);
        userInputThread.start();
        System.out.println("Velkommen til chatten!\nSkriv din besked:");
        try {
            datagramSocket = new DatagramSocket();
            address = InetAddress.getByName("localhost"); // Metode til at automatisk få fat i addressen til localhost.
            while (true) {
                System.out.println("Modtaget besked fra server: "+receive(datagramSocket));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void send(String message) {
        sendTo(datagramSocket, address, port, message);
    }

    private String receive(DatagramSocket socket) {
        byte[] receiveArray = new byte[1000];
        DatagramPacket receivePacket = new DatagramPacket(receiveArray, receiveArray.length);
        try {
            socket.receive(receivePacket);
            return new String(receiveArray, 0, receivePacket.getLength());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return "";
    }

    private void sendTo(DatagramSocket socket, InetAddress address, int port, String message) {

        byte[] sendArray = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendArray, sendArray.length,
                address, port);
        try {
            socket.send(sendPacket);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }

    }
}