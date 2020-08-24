package trivial_Sequential_Server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        var socket = new Socket("192.168.43.73", 8000);
        var in = new Scanner(socket.getInputStream());
        var input = in.nextLine();
        System.out.println("Server response: " + input);
    }
}
