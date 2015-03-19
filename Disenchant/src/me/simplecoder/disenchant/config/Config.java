package me.simplecoder.disenchant.config;

import me.simplecoder.disenchant.Disenchant;
import me.simplecoder.disenchant.utils.MessageUtils;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;

public class Config
{
  private Disenchant plugin;
  
  public Config(Disenchant plugin)
  {
    this.plugin = plugin;
    
    getSettings();
  }
  
  public void getSettings()
  {
    this.plugin.log.info("Reading config...");
    this.plugin.saveDefaultConfig();
    this.plugin.reloadConfig();
    
    this.plugin.noEnchantsMessage = MessageUtils.color(this.plugin.getConfig().getString("noEnchantsMessage", this.plugin.noEnchantsMessage));
    this.plugin.tooExpensiveMessage = MessageUtils.color(this.plugin.getConfig().getString("tooExpensiveMessage", this.plugin.tooExpensiveMessage));
    this.plugin.enchantRemovedMessage = MessageUtils.color(this.plugin.getConfig().getString("enchantRemovedMessage", this.plugin.enchantRemovedMessage));
    

    this.plugin.log.info("Config Updated!");
  }
}

