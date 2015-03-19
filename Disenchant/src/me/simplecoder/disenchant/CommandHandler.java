package me.simplecoder.disenchant;

import me.simplecoder.disenchant.config.Config;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.plugin.PluginDescriptionFile;

public class CommandHandler
  implements CommandExecutor
{
  private Disenchant plugin;
  
  public CommandHandler(Disenchant plugin)
  {
    this.plugin = plugin;
    plugin.getCommand("de").setExecutor(this);
    plugin.getCommand("disenchant").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    boolean player = sender instanceof Player;
    if (cmd.getLabel().equalsIgnoreCase("disenchant"))
    {
      if ((args.length == 1) && 
        (args[0].equalsIgnoreCase("reload")))
      {
        if ((player) && (!sender.hasPermission("disenchant.reload"))) {
          return true;
        }
        this.plugin.config.getSettings();
        if (player) {
          sender.sendMessage(ChatColor.GREEN + "Config Reloaded!");
        }
        return true;
      }
      sender.sendMessage(ChatColor.AQUA + "---[ " + ChatColor.BLUE + "Disenchant -  By SimpleCoder" + ChatColor.AQUA + " ]------------");
      if ((player) && (sender.hasPermission("disenchant.use"))) {
        sender.sendMessage(ChatColor.AQUA + "disenchant" + ChatColor.BLUE + " - Disenchant");
      }
      if ((!player) || (sender.hasPermission("disenchant.reload"))) {
        sender.sendMessage(ChatColor.AQUA + "disenchant reload" + ChatColor.BLUE + " - Reload the config");
      }
      return true;
    }
    if (cmd.getLabel().equalsIgnoreCase("disenchant"))
    {
      if (!player) {
        return true;
      }
      if (!sender.hasPermission("disenchant.use"))
      {
        sender.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
        return true;
      }
      Player p = (Player)sender;
      if (p.getItemInHand().getEnchantments().isEmpty())
      {
        p.sendMessage(this.plugin.noEnchantsMessage);
        return true;
      }
      int baseCost = 25;
      for (Enchantment enchant : p.getItemInHand().getEnchantments().keySet()) {
        baseCost += EnchantCost.getCost(enchant).intValue() * p.getItemInHand().getEnchantmentLevel(enchant) - 1;
      }
      int lastIncrease = 0;
      for (int i = 0; i < p.getItemInHand().getEnchantments().size(); i++)
      {
        lastIncrease++;
        baseCost += lastIncrease;
      }
      Inventory inv = this.plugin.getServer().createInventory(null, 9, this.plugin.invTitle);
      for (Enchantment enchant : p.getItemInHand().getEnchantments().keySet())
      {
        ItemStack itm = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta)itm.getItemMeta();
        meta.addStoredEnchant(enchant, p.getItemInHand().getEnchantmentLevel(enchant), true);
        int cost = baseCost - (EnchantCost.getCost(enchant).intValue() * p.getItemInHand().getEnchantmentLevel(enchant) - 1);
        
        List<String> lore = new ArrayList();
        StringBuffer sb = new StringBuffer(ChatColor.RED.toString());
        if (p.getLevel() < cost) {
          sb.append(ChatColor.STRIKETHROUGH.toString());
        }
        sb.append(ChatColor.ITALIC.toString());
        sb.append("Cost: ");
        sb.append(cost);
        sb.append(" Levels");
        lore.add(sb.toString());
        if (p.getLevel() < cost) {
          lore.add(ChatColor.GOLD.toString() + ChatColor.ITALIC + "You can't afford this!");
        }
        meta.setLore(lore);
        
        itm.setItemMeta(meta);
        inv.addItem(new ItemStack[] { itm });
      }
      p.openInventory(inv);
      return true;
    }
    return false;
  }
}

