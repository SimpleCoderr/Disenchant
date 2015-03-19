package me.simplecoder.disenchant;

import me.simplecoder.disenchant.config.Config;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Disenchant
  extends JavaPlugin
{
  private static Disenchant instance;
  public Logger log;
  public PluginDescriptionFile pdf;
  public Config config;
  public CommandHandler cHandler;
  
  public static Disenchant getInstance()
  {
    return instance;
  }
  
  public Disenchant()
  {
    instance = this;
  }
  
  public final String invTitle = ChatColor.RED + "Remove an enchantment!";
  public String noEnchantsMessage = "&cThis item has no enchantments to remove...";
  public String tooExpensiveMessage = "&cYou can not afford this disenchant!";
  public String enchantRemovedMessage = "&6Removed the enchantment for the cost of: &e%cost% Levels";
  
  public void onEnable()
  {
    this.log = getLogger();
    this.pdf = getDescription();
    this.config = new Config(this);
    
    getServer().getPluginManager().registerEvents(new DisenchantListener(this), this);
    
    this.cHandler = new CommandHandler(this);
    
    this.log.info(this.pdf.getFullName() + " started successfully!");
  }
  
  public void onDisable()
  {
    this.log.info(this.pdf.getFullName() + " stopped successfully!");
  }
}

