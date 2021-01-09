package iR.yzplug.AF;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase implements Listener {
	
	private Plugin auth;
	
	@Override
	public void onEnable(){
		
		this.getLogger().info("Enabled AuthUI plugin for Nukkit");
		getServer().getPluginManager().registerEvents(this, this);
		this.auth = getServer().getPluginManager().getPlugin("AdvancedAuth");
		
		if (this.getServer().getPluginManager().getPlugin("AdvancedAuth") == null){
			this.getLogger().error(TextFormat.RED + "Plugin AdvancedAuth is not found. please downloading this plugin and using this");
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		// Forms :
			// Object forms
			ElementInput password = new ElementInput("your password : ");
			ElementInput confirmPassword = new ElementInput("confirm password : ");
			// Register forms
			FormWindowCustom register = new FormWindowCustom("REGISTER");
			register.addElement(new ElementLabel("Welcome to this server, please registering in server"));
			register.addElement(password);
			register.addElement(confirmPassword);
			// login forms
			FormWindowCustom login = new FormWindowCustom("LOGIN");
			login.addElement(password);
				
		  Player p = e.getPlayer();
		  
		  if(!(p.hasPlayedBefore())){
			        	p.showFormWindow(register);
			        } else {
			        p.showFormWindow(login);	
			        }       
	}
		@EventHandler
	    public void onResponse(PlayerFormRespondedEvent event) {
			
	        if (!(event.getWindow() instanceof FormWindowCustom)) return;
	        FormWindowCustom fw = (FormWindowCustom) event.getWindow();
	        
	        if (fw.getTitle().equals("LOGIN")) {
	            String pass =  fw.getResponse().getInputResponse(0);
	            getServer().dispatchCommand(event.getPlayer(), "/login " + pass);
	            
	        } else if (fw.getTitle().equals("REGISTER")) {
	            String pass = fw.getResponse().getInputResponse(0);
	            String cpass = fw.getResponse().getInputResponse(1);
	            getServer().dispatchCommand(event.getPlayer(), "/register " + pass + " "+ cpass);
	        
	    }
	}
}
