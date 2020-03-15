package cuongtran;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This class implements java socket client
 *
 * @author pankaj
 */
public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        System.out.println("Connecting to server...");
        InetAddress host = InetAddress.getLocalHost();

        // Uses ObjectOutputStream
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Please enter student number, type -1 to exit: ");

            // Gets input from user
            int number = scan.nextInt();

            if (number == -1) {
                // Closes stream, exit application
                System.out.println("Application is closed.");
                if (ois != null) ois.close();
                if (oos != null) oos.close();
                break;
            } else {
                System.out.println("Sending id: " + number);

                // Connects to socket server
                Socket clientSocket = new Socket(host, 1410);

                // Sends Student id to server
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                oos.writeObject(number);

                // Reads response message
                ois = new ObjectInputStream(clientSocket.getInputStream());
                Object message = ois.readObject();

                // Closes Client Socket
                System.out.println("Result: " + message.toString());
                clientSocket.close();
            }
        }
    }
}