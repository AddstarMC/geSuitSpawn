package net.cubespace.geSuiteSpawn.listeners;

import net.cubespace.geSuiteSpawn.geSuitSpawn;
import net.cubespace.geSuiteSpawn.managers.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class SpawnMessageListener implements PluginMessageListener, Listener {

    @Override
    public void onPluginMessageReceived( String channel, Player player, byte[] message ) {
        DataInputStream in = new DataInputStream( new ByteArrayInputStream( message ) );
        String task;

        try {
            task = in.readUTF();
            switch ( task ) {
                case "SendSpawn":
                    //                     spawnName worldName X Y Z yaw pitch
                    SpawnManager.addSpawn( in.readUTF(), in.readUTF(), in.readDouble(), in.readDouble(), in.readDouble(), in.readFloat(), in.readFloat() );
                    break;
                case "DelWorldSpawn":
                    //                           worldName
                    SpawnManager.delWorldSpawn( in.readUTF() );
                    break;
                case "GetVersion":
                    String name = null;
                    try {
                        name = in.readUTF();
                    } catch ( IOException ignored ) {
                    }
                    if ( name != null ) {
                        Player p = Bukkit.getPlayer( name );
                        p.sendMessage( ChatColor.RED + "Spawns - " + ChatColor.GOLD + geSuitSpawn.INSTANCE.getDescription().getVersion() );
                    }
                    Bukkit.getConsoleSender().sendMessage( ChatColor.RED + "Spawns - " + ChatColor.GOLD + geSuitSpawn.INSTANCE.getDescription().getVersion() );
                    SpawnManager.sendVersion();
                    break;
            }

        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }


}
