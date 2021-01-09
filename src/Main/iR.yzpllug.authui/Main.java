package iR.yzplug.authui;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import SimpleAuth.SimpleAuth;

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
	public void onPlaerJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		// Register form
		ElementInput password = new ElementInput("your password : ");
		ElementInput confirmPassword = new ElementInput("confirm password : ");
		
		FormWindowCustom register = new FormWindowCustom("Register");
		register.addElement(new ElementLabel("Welcome to this server, please registering in server"));
		register.addElement(password);
		register.addElement(confirmPassword);
		
		FormWindowCustom login = new FormWindowCustom("Login");
		login.addElement(new ElementLabel("Welcome back to this server, please login in server"));
		login.addElement(password);
		if(!(p.hasPlayedBefore())){
			
			p.showFormWindow(register);
			getServer().dispatchCommand(p, "register" + password.getText() + " " + confirmPassword.getText());
		} else {
			
			p.showFormWindow(login);
		}
		
	}
}
