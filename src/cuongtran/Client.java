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


        //use ObjectOutputStream
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        Scanner scan = new Scanner(System.in);
        while (true) {

            System.out.print("Please enter student number, type -1 to exit: ");
            int number = scan.nextInt();

            if (number == -1) {
                //close stream
                System.out.println("Application is closed.");
                if (ois != null) ois.close();
                oos.close();
                break;
            } else {
                System.out.println("Sending id: " + number);
                Socket clientSocket = new Socket(host, 1410);
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                oos.writeObject(number);
                ois = new ObjectInputStream(clientSocket.getInputStream());
                //read response message
                Object message = ois.readObject();
                System.out.println("Result: " + message.toString());
                clientSocket.close();
            }
        }
    }
}