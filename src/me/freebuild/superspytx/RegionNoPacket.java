package me.freebuild.superspytx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import me.freebuild.superspytx.extensions.GPExtension;
import me.freebuild.superspytx.extensions.RegionExtension;
import me.ryanhamshire.GriefPrevention.GriefPrevention;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RegionNoPacket extends JavaPlugin implements Listener
{

    private List<RegionExtension> regionplugins = new ArrayList<RegionExtension>();
    private List<String> vanished = new CopyOnWriteArrayList<String>();

    public void onDisable()
    {

    }

    public void onEnable()
    {
        System.out.println("Enabling RegionNoPacket");
        if (getServer().getPluginManager().getPlugin("GriefPrevention") != null)
        {
            regionplugins.add(new GPExtension((GriefPrevention) getServer().getPluginManager().getPlugin("GriefPrevention")));
        }

        //that's the only region plugin, so we're cool.
        
        getServer().getPluginManager().registerEvents(this, this);

        System.out.println("Enabled RegionNoPacket Successfully!");
    }

    public void vanishCheck(Player player)
    {
        if (regionplugins.size() < 1)
            return;

        boolean justvanished = false;
        boolean justunvanished = false;

        for (RegionExtension e : regionplugins)
        {
            if (justvanished || justunvanished)
                break;
            if (!vanished.contains(player.getName()))
            {
                if (e.inRegion(player.getLocation()))
                {
                    if (e.isOwner(player) && player.hasPermission("rnp.vanish." + e.getPermFriendlyName()))
                    {
                        vanished.add(player.getName());
                        for (Player pl : getServer().getOnlinePlayers())
                        {
                            if (!pl.hasPermission("rnp.see"))
                            {
                                pl.hidePlayer(player);
                            }
                        }

                        // Yes this is from VanishNoPacket :3
                        for (int i = 0; i < 10; i++)
                        {
                            player.getLocation().getWorld().playEffect(player.getLocation(), Effect.SMOKE, (new Random()).nextInt(9));
                        }

                        justvanished = true;
                    }
                }
            }
            else
            {
                if (!e.inRegion(player.getLocation()))
                {
                    vanished.remove(player.getName());
                    for (Player pl : getServer().getOnlinePlayers())
                    {
                        if (!pl.hasPermission("rnp.see." + e.getPermFriendlyName()))
                        {
                            pl.showPlayer(player);
                        }
                    }

                    // Yes this is from VanishNoPacket :3
                    for (int i = 0; i < 10; i++)
                    {
                        player.getLocation().getWorld().playEffect(player.getLocation(), Effect.SMOKE, (new Random()).nextInt(9));
                    }

                    justunvanished = true;
                }
            }
        }

        if (justvanished)
            player.sendMessage(ChatColor.GREEN + "You've vanished from everybody around you because you've entered your own home.");

        if (justunvanished)
            player.sendMessage(ChatColor.GREEN + "You've unvanished from everybody around you because you've left your own home.");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        vanishCheck(player);

        if (vanished.size() < 1)
            return;

        if (player.hasPermission("rnp.see"))
            return;

        for (String playerstring : vanished)
        {
            Player pl = getServer().getPlayerExact(playerstring);
            if (pl != null)
            {
                player.hidePlayer(pl);
            }
        }

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        vanishCheck(player);
    }
}
