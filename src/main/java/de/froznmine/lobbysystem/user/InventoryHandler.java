package de.froznmine.lobbysystem.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import de.froznmine.lobbysystem.LobbySystem;
import de.froznmine.lobbysystem.lobby.event.LobbyJoinEvent;

public class InventoryHandler implements Listener {
	private PlayerInventory inv;
	private int currentItem;
	private boolean scrollingMenubar;
	private List<ItemStack> items;
	
	public InventoryHandler(User user) {
		this.inv = user.getPlayer().getInventory();
		this.currentItem = 0;
		this.scrollingMenubar = false;
		this.items = new ArrayList<ItemStack>();
		
		Bukkit.getServer().getPluginManager().registerEvents(this, LobbySystem.PLUGIN);
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onLobbyJoin(LobbyJoinEvent event) {
		if (inv.getHolder().getUniqueId().compareTo(event.getGameUser().uuid) == 0) {
				ItemStack[] itemsToAdd = event.getLobby().getLobbyItems();
			
			if (items.size() + itemsToAdd.length > 9) {
				inv.setHeldItemSlot(4);
				scrollingMenubar = true;
			}
			Map<Integer, ItemStack> notFitting = inv.addItem();
			if (notFitting != null) {
				
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onItemSlotChange(PlayerItemHeldEvent event) {
		if (inv.getHolder().getUniqueId().compareTo(event.getPlayer().getUniqueId()) == 0) {
			if (scrollingMenubar) {
				event.setCancelled(true);
				if (event.getPreviousSlot() < event.getNewSlot()) {
					// sroll right to left
					currentItem = (currentItem + 1) % items.size();
				}
				else {
					//scroll left to right
					currentItem = (currentItem + items.size() - 1) % items.size();
				}
				
				updateInventory();
			}
		}
	}

	private void updateInventory() {
		for (int i = 0; i < 9; i++) {
			int index = items.size() + currentItem + i + 1;
			inv.setItem(0, items.get(index));
		}
	}
}
