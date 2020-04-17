package com.company;

import java.util.Scanner;

public class UserInput implements Runnable {
    private Klient klient;

    public UserInput(Klient klient) {
        this.klient = klient;

    }

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String message = scan.nextLine();
            klient.send(message);
            if (message.equalsIgnoreCase("quit")) {
                return;
            }
        }
    }
}
