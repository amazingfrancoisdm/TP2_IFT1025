package main;

import main.models.Course;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientSimple {
    public static void main(String[] args) {

        try {
            Socket clientSocket = new Socket("127.0.0.1", 8080);

            ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());


            Scanner scanner = new Scanner(System.in);

            System.out.println("What would you like to do?");
            String szn = scanner.nextLine();

            os.writeObject(szn);

            //System.out.println("Request sent to the server.");

            ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());

            ArrayList<Course> cours = new ArrayList<>();

            cours = (ArrayList<Course>) is.readObject();

            //System.out.println("Cours reçu");

            //System.out.println(cours.get(1).toString());

            os.close();
            is.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
