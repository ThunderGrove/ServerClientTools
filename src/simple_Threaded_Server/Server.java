package simple_Threaded_Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Server{

    public Server() throws IOException{
    }

    public static void main(String[] args) throws Exception{
        try(var listener=new ServerSocket(8000)){
            System.out.println("The capitalization server is running...");
            var pool=Executors.newFixedThreadPool(20);
            while(true){
                pool.execute(new Capitalizer(listener.accept()));
            }
        }
    }

    private static class Capitalizer implements Runnable{
        private Socket socket;

        Capitalizer(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run(){
            System.out.println("Connected: "+socket);
            try {
                var in=new Scanner(socket.getInputStream());
                var out=new PrintWriter(socket.getOutputStream(),true);
                while(in.hasNextLine()){
                    out.println(in.nextLine().toUpperCase());
                }
            } catch(Exception e){
                System.out.println("Error:"+socket);
            }finally{
                try{
                    socket.close();
                } catch (IOException e){
                }
                System.out.println("Closed: "+socket);
            }
        }
    }
}