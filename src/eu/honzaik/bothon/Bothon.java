package eu.honzaik.bothon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.jibble.pircbot.PircBot;

public class Bothon extends PircBot{
	
	public static final String nick = "nick";
	public static final String pass = "pass";
	public static final String helloMsg = "Hello everyone, honzaik's bot is here!";
	public static final int port = 6667;	
	public static String server = "irc server";
	public static String channel;
	private static String derp;
	private static boolean running = true;
	
	public void onMessage(String channel, String sender, String login, String hostname, String message){
		Response resp = new Response(message, sender);
		String newMsg = resp.getResponse();
		if(resp.isResponse){
			sendMessage(channel, newMsg);
			if(resp.shouldDie){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		}
	}
	
	private void setChannel(String server){
		if(server.length() < 11){
			System.out.println("Wrong server name, shutting down");
			System.exit(0);
		}
		channel = "#" + server.substring(0, server.length() - 11);
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		Bothon bothon = new Bothon();
		bothon.setName(nick);
		bothon.setVerbose(true);
		if(args.length > 0) server = args[0];
		bothon.setChannel(server);
		try {
			bothon.connect(server, port, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bothon.joinChannel(channel);
		bothon.sendMessage(channel, helloMsg);
		while(running){
			derp = bufferRead.readLine();
			if(derp.equals("die")) System.exit(0);
			if(derp.length() > 3 && derp.substring(0,  3).equals("msg")){
				bothon.sendMessage(channel, derp.substring(4));
			}
		}
	}

}
