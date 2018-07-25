package net.cubespace.geSuiteSpawn.tasks;

import net.cubespace.geSuiteSpawn.geSuitSpawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;


public class PluginMessageTask extends BukkitRunnable {

	private final ByteArrayOutputStream bytes;

	public PluginMessageTask(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}

    @Override
	public void run() {
	    Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator();
        if (iterator.hasNext()) {
            Player player = iterator.next();
				player.sendPluginMessage(
						geSuitSpawn.INSTANCE,
						"bungeecord:geSuitSpawns",
						bytes.toByteArray());
			} else {
				System.out.println(ChatColor.RED + "Unable to send Plugin Message - No players online.");
			}
	}

}
