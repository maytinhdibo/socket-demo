package cuongtran;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

/**
 * This class implements java Socket server
 *
 * @author pankaj
 */
public class Server {

    private static ServerSocket server;
    private static int port = 1410;

    public static void main(String args[]) {
        try {
            // Generates fake data
            ArrayList<Student> fakeData = new ArrayList<Student>();
            fakeData.add(new Student(17020076, "Cuong Tran", "62CLC", "14/10/1999", "Nghe An"));
            fakeData.add(new Student(17020077, "Ngoc Dang", "62CLC", "14/08/1999", "Nghe An"));
            fakeData.add(new Student(17020078, "Do Phong", "62CLC", "12/01/1999", "Ha Noi"));


            // Creates welcoming socket
            server = new ServerSocket(port);

            System.out.println("Application is running.");

            // Waits for client socket
            while (true) {
                System.out.println("Waiting for the client request");

                // Accepts the client connection and creates connection socket
                Socket socket = server.accept();

                // Reads data from client
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                int input = (int) ois.readObject();

                // Searches the student with student ID == input
                System.out.println("Message received: " + input);
                Optional<Student> ret = fakeData.stream().filter(student -> student.getNumber() == input).findFirst();

                // Sends result to Client
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                if (ret.isPresent())
                    oos.writeObject(ret.get());
                else
                    oos.writeObject("Can not find this student");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}