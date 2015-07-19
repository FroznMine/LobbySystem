package de.pesacraft.lobbysystem.user;

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

import de.pesacraft.lobbysystem.LobbySystem;
import de.pesacraft.lobbysystem.lobby.event.LobbyJoinEvent;

public class InventoryHandler implements Listener {
	private InventoryMode mode;
	private int currentItem;
	private final PlayerInventory inv;
	private final List<ItemStack> items;
	private boolean scrollingMenubar;

	public InventoryHandler(final User user) {
		this.inv = user.getPlayer().getInventory();
		this.currentItem = 0;
		this.scrollingMenubar = false;
		this.items = new ArrayList<ItemStack>();
		this.mode = InventoryMode.LOBBY;
		
		Bukkit.getServer().getPluginManager().registerEvents(this, LobbySystem.PLUGIN);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onItemSlotChange(final PlayerItemHeldEvent event) {
		if (this.inv.getHolder().getUniqueId().compareTo(event.getPlayer().getUniqueId()) == 0)
			if (this.mode == InventoryMode.INGAME)
				return;
			if (this.scrollingMenubar) {
				event.setCancelled(true);
				if (event.getPreviousSlot() < event.getNewSlot())
					// sroll right to left
					this.currentItem = (this.currentItem + 1) % this.items.size();
				else
					// scroll left to right
					this.currentItem = (this.currentItem + this.items.size() - 1) % this.items.size();

				this.updateInventory();
			}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onLobbyJoin(final LobbyJoinEvent event) {
		if (this.inv.getHolder().getUniqueId().compareTo(event.getUser().uuid) == 0) {
//			final ItemStack[] itemsToAdd = event.getLobby().getLobbyItems();
//
//			if (this.items.size() + itemsToAdd.length > 9) {
//				this.inv.setHeldItemSlot(4);
//				this.scrollingMenubar = true;
//			}
//			final Map<Integer, ItemStack> notFitting = this.inv.addItem();
//			if (notFitting != null) {
//
//			}
		}
	}

	private void updateInventory() {
		if (mode == InventoryMode.INGAME)
			return;
		
		for (int i = 0; i < 9; i++) {
			final int index = (this.currentItem + i + 1) % this.items.size();
			this.inv.setItem(i, this.items.get(index));
		}
	}
	
	public InventoryMode getMode() {
		return mode;
	}
	
	public void setMode(InventoryMode mode) {
		this.mode = mode;
		
		if (this.mode == InventoryMode.INGAME)
			inv.clear();
		else
			updateInventory();
	}
}
