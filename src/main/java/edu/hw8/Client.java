package edu.hw8;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("личности");
        String response = in.readLine();
        System.out.println("Response: " + response);

        out.close();
        in.close();
        socket.close();
    }
}

