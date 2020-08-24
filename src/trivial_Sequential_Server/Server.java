package trivial_Sequential_Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try (var listener = new ServerSocket(8000)) {
            System.out.println("The server is running...");
            while (true){
                try (var socket = listener.accept()){
                    var out = new PrintWriter(socket.getOutputStream());
                    out.println(new Date().toString());
                    System.out.println("message sent"+ new Date().toString());
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
