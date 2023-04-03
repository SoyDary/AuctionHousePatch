package com.github.SoyDary.AuctionHousePatch;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.spawnchunk.auctionhouse.events.ListItemEvent;
import com.spawnchunk.auctionhouse.modules.ListingType;

public class AuctionHousePatch extends JavaPlugin implements Listener {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onAuctionItem(ListItemEvent e) {
		if(e.getType() != ListingType.PLAYER_LISTING) return;
		OfflinePlayer of = e.getSeller();
		if(!of.isOnline()) {
			e.setCancelled(true);
			String alert = ChatColor.translateAlternateColorCodes('&', "&4[&cAHP&4] &7"+of.getName()+" intentó subastar un item estando offline.");
			Bukkit.broadcast(alert, "auctionhousepatch.alert");
			getServer().getConsoleSender().sendMessage(alert);
		}
	}
}
