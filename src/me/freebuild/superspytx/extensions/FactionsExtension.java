package me.freebuild.superspytx.extensions;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.P;

import me.freebuild.superspytx.Permissions;
import me.freebuild.superspytx.RegionNoPacket;

public class FactionsExtension extends RegionExtension
{

    public FactionsExtension()
    {
        super((Factions) RegionNoPacket.instance.getServer().getPluginManager().getPlugin("Factions"));
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getName()
    {
        return "Factions";
    }

    @Override
    public Permissions getPermission()
    {
        return Permissions.SEE_FACTIONS;
    }

    @Override
    public boolean inRegion(Location loc)
    {
        Faction faction = Board.getFactionAt(new FLocation(loc));
        if (faction == null)
            return false;
        
        if (!faction.isNormal())
            return false;

        if (!faction.isPeaceful()) // This region functionality is only for peaceful factions.
            return false;
        
        return true;
        
    }

    @Override
    public boolean isOwner(Player player)
    {
        Faction faction = Board.getFactionAt(new FLocation(player.getLocation()));
        if (faction == null)
            return false;
        
        return faction.getOnlinePlayers().contains(player);
    }

}
