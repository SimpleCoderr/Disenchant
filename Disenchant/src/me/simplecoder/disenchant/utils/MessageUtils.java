package me.simplecoder.disenchant.utils;

import org.bukkit.ChatColor;

public class MessageUtils
{
  public static final String color(String message)
  {
    return ChatColor.translateAlternateColorCodes('&', message);
  }
}

