package eu.honzaik.bothon;

public class Response {
	
	private String message;
	private String sender;
	private String resp = "";
	private String[] prikazy = {
			"!time",
			"!die",
			"!fuckyou",
			"!cmds",
			"!gods"
	};
	private String[] desc = {
			"Retuns the current time in millis.",
			"Shuts down Bothon. (Only gods can do that :P)",
			"Guess...",
			"Shows this.",
			"Shows the god list."
			
	};
	private String[] gods = {
			"honzaik",
	};
	public boolean isResponse = false;
	public boolean shouldDie = false;
	
	public Response(String message, String sender){
		this.message = message;
		this.sender = sender;
	}
	
	public String getResponse(){	
		switch(message){
			case "!time": resp = getTime();
			break;
			case "!die": die();
			break;
			case "!fuckyou": resp = fuckYou();
			break;
			case "!cmds": resp = getCommands();
			break;
			case "!gods": resp = getGods();
			break;
		}
		if(!resp.equals("")){
			isResponse = true;
		}
		return resp;
	}
	
	private String getTime() {
		return System.currentTimeMillis()+"";	
	}
	
	private void die() {
		for(int i = 0; i < gods.length; i++){
			if(sender.equals(gods[i])){
				resp = "God " + gods[i] + " killed me :(, I'll be back";
				shouldDie = true;
				System.out.println("Turning of Bothon");
			}
		}
	}
	
	private String fuckYou() {
		return "Fuck you too!";
	}
	
	private String getCommands(){
		String cmds = "";
		for(int i = 0; i < prikazy.length; i++){
			cmds += prikazy[i] + " : " + desc[i] + " "; 
		}
		return cmds;
	}
	
	private String getGods(){
		String godList = "";
		for(int i = 0; i < gods.length; i++){
			godList += gods[i] + "; ";
		}
		return godList;
	}

	
}
