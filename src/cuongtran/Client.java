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


        while (true) {
            Socket socket = new Socket(host.getHostName(), 1410);

            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter student number, type -1 to exit: ");

            int number = scan.nextInt();

            oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject(number);

            if (number == -1) {
                //close stream
                if (ois != null) ois.close();
                oos.close();
                socket.close();
                System.out.println("Application is closed.");
                break;
            } else {
                ois = new ObjectInputStream(socket.getInputStream());
                //read response message
                Object message = ois.readObject();
                System.out.println("Result: " + message.toString());
            }

        }
    }
}