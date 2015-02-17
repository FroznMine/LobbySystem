package de.froznmine.lobbysystem.scoreboard;

import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import de.froznmine.lobbysystem.LobbySystem;

public class Scoreboards {
	/**
	 * Displays the scoreboard with the given time for the given amount of
	 * ticks. After that switching back to previous.
	 *
	 * @param name
	 *            the scoreboard to display
	 * @param ticks
	 *            the time in ticks to display it
	 */
	public static void displayScoreboard(final Player p, final Scoreboard scoreboard, final int ticks) {
		final Scoreboard old = p.getScoreboard();

		p.setScoreboard(scoreboard);

		final BukkitRunnable runnable = new BukkitRunnable() {
			@Override
			public void run() {
				p.setScoreboard(old);
			}
		};

		runnable.runTaskLater(LobbySystem.PLUGIN, ticks);
	}
}
