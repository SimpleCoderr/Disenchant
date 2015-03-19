package me.simplecoder.disenchant;

import java.util.Map;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class DisenchantListener
  implements Listener
{
  private Disenchant plugin;
  
  public DisenchantListener(Disenchant plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onInventoryClick(InventoryClickEvent event)
  {
    if (!(event.getWhoClicked() instanceof Player)) {
      return;
    }
    if (!event.getInventory().getName().equalsIgnoreCase(this.plugin.invTitle)) {
      return;
    }
    event.setCancelled(true);
    if ((event.getRawSlot() < 0) || (event.getRawSlot() > 8)) {
      return;
    }
    ItemStack itm = event.getInventory().getContents()[event.getRawSlot()];
    if (itm.getType() != Material.ENCHANTED_BOOK) {
      return;
    }
    Player p = (Player)event.getWhoClicked();
    
    int cost = Integer.parseInt(((String)itm.getItemMeta().getLore().get(0)).split(" ")[1]);
    if (p.getLevel() < cost)
    {
      p.sendMessage(this.plugin.tooExpensiveMessage);
      return;
    }
    p.setLevel(p.getLevel() - cost);
    
    p.closeInventory();
    for (Enchantment enchant : ((EnchantmentStorageMeta)itm.getItemMeta()).getStoredEnchants().keySet()) {
      p.getItemInHand().removeEnchantment(enchant);
    }
  }
}

