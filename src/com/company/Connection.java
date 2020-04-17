package com.company;

import java.net.InetAddress;

public class Connection {
    private String username;
    private InetAddress ipaddress;
    private int portnumber;

    public Connection(String username, InetAddress ipaddress, int portnumber) {
        this.username = username;
        this.ipaddress = ipaddress;
        this.portnumber = portnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InetAddress getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(InetAddress ipaddress) {
        this.ipaddress = ipaddress;
    }

    public int getPortnumber() {
        return portnumber;
    }

    public void setPortnumber(int portnumber) {
        this.portnumber = portnumber;
    }
}
