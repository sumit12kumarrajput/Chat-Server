import java.net.ServerSocket;
import java.net.*;
import java.io.*;

class server{
		ServerSocket server;
		Socket socket;
		BufferedReader br;
		PrintWriter out;
		public server(){
			try{
		server=new ServerSocket(5787);
		System.out.println("Server is ready. ");
		System.out.println("Waiting for client........");
		socket=server.accept();
		System.out.println("Connection Established .....");
		System.out.println("Now You can Chat.");
		br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(socket.getOutputStream());
		startReading();
		startWriting();
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
	
	public void startReading(){
		Runnable r1=()->{
			while(true){
				try{
				String msg=br.readLine();
				if(msg.equals("exit")){
					System.out.println("Client Exited.");
					break;
				}
				System.out.println("Client      : "+msg);
			}
		
		catch(Exception e){
			e.printStackTrace();
		}
		}
		};
		new Thread(r1).start();
	}
	public void startWriting(){
		Runnable r2=()->{
			
			while(true){
			try{
				BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
				String content=br1.readLine();
				out.println(content);
				out.flush();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		};
		new Thread(r2).start();
	}

	public static void main(String[] args) {
		System.out.println("Server is Started..");
		new server();
	}
}