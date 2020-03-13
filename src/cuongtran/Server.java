package cuongtran;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
            ArrayList<Student> fakeData = new ArrayList<Student>();

            fakeData.add(new Student(17020076, "Cuong Tran", "62CLC", "14/10/1999", "Nghe An"));
            fakeData.add(new Student(17020077, "Ngoc Dang", "62CLC", "14/08/1999", "Nghe An"));
            fakeData.add(new Student(17020078, "Do Phong", "62CLC", "12/01/1999", "Ha Noi"));

//            System.out.println(fakeData.get(0).toString());
//            System.out.println(fakeData.get(1).toString());
//            System.out.println(fakeData.get(2).toString());


            server = new ServerSocket(port);

            System.out.println("Application is running.");

            while (true) {
                Socket socket = server.accept();

                System.out.println("Waiting for the client request");

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                int input = (int) ois.readObject();

                if(input==-1) break;

                System.out.println("Message received: " + input);

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                for (Student student : fakeData) {
                    if (student.getNumber() == input) {
                        oos.writeObject(student);
                        continue;
                    }
                }

                oos.writeObject("Can not find this student");

                ois.close();
                oos.close();
                socket.close();
            }

            System.out.println("Application is closed.");
            server.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}