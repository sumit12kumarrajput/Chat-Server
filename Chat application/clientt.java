import java.net.Socket;
import java.io.*;
class client{
	Socket socket;
	BufferedReader br;
	PrintWriter out;
	public client(){
		try{
			System.out.println("Sending request to srever...... ");
			socket=new Socket("localhost",5787);
			System.out.println("Connection Established.");
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
					System.out.println("Server Exited.");
					break;
				}
				System.out.println("Server      : "+msg);
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
		System.out.println("Client Responding...");
		new client();
	}
}