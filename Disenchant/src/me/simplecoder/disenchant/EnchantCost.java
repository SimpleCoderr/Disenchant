package me.simplecoder.disenchant;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.enchantments.Enchantment;

public enum EnchantCost
{
  DAMAGE_ALL(
    Enchantment.DAMAGE_ALL, 1),  DAMAGE_UNDEAD(Enchantment.DAMAGE_UNDEAD, 2),  DAMAGE_ARTHROPODS(Enchantment.DAMAGE_ARTHROPODS, 2),  KNOCKBACK(Enchantment.KNOCKBACK, 2),  FIRE_ASPECT(Enchantment.FIRE_ASPECT, 4),  LOOT_BONUS_MOBS(Enchantment.LOOT_BONUS_MOBS, 4),  DIG_SPEED(
    Enchantment.DIG_SPEED, 1),  DURABILITY(Enchantment.DURABILITY, 2),  LOOT_BONUS_BLOCKS(Enchantment.LOOT_BONUS_BLOCKS, 4),  SILK_TOUCH(Enchantment.SILK_TOUCH, 8),  PROTECTION_ENVIRONMENTAL(
    Enchantment.PROTECTION_ENVIRONMENTAL, 1),  PROTECTION_FIRE(Enchantment.PROTECTION_FIRE, 2),  PROTECTION_PROJECTILE(Enchantment.PROTECTION_PROJECTILE, 2),  PROTECTION_FALL(Enchantment.PROTECTION_FALL, 2),  PROTECTION_EXPLOSIONS(Enchantment.PROTECTION_EXPLOSIONS, 4),  OXYGEN(Enchantment.OXYGEN, 4),  WATER_WORKER(Enchantment.WATER_WORKER, 4),  THORNS(Enchantment.THORNS, 8),  ARROW_DAMAGE(
    Enchantment.ARROW_DAMAGE, 1),  ARROW_KNOCKBACK(Enchantment.ARROW_KNOCKBACK, 4),  ARROW_FIRE(Enchantment.ARROW_FIRE, 4),  ARROW_INFINITE(Enchantment.ARROW_INFINITE, 8);
  
  private static Map<Enchantment, Integer> enchantToCostMapping;
  private Enchantment enchant;
  private int cost;
  
  private EnchantCost(Enchantment enchant, int cost)
  {
    this.enchant = enchant;
    this.cost = cost;
  }
  
  public static Integer getCost(Enchantment enchant)
  {
    if (enchantToCostMapping == null) {
      initMapping();
    }
    return (Integer)enchantToCostMapping.get(enchant);
  }
  
  private static void initMapping()
  {
    enchantToCostMapping = new HashMap();
    for (EnchantCost ec : values()) {
      enchantToCostMapping.put(ec.enchant, Integer.valueOf(ec.cost));
    }
  }
  
  public Enchantment getEnchant()
  {
    return this.enchant;
  }
  
  public int getCost()
  {
    return this.cost;
  }
  
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("EnchantCost");
    sb.append("{cost=").append(this.cost);
    sb.append('}');
    return sb.toString();
  }
}

