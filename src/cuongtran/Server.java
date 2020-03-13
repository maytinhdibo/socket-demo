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

                System.out.println("Message received: " + input);

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                Optional<Student> ret = fakeData.stream().filter(student -> student.getNumber() == input).findFirst();
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