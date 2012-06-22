package me.freebuild.superspytx.extensions;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;

public class GPExtension extends RegionExtension
{

    public GPExtension(GriefPrevention plug)
    {
        super(plug);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String getName()
    {
        return "Grief Prevention";
    }
    
    @Override
    public String getPermFriendlyName()
    {
        return "griefprevention";
    }

    @Override
    public boolean inRegion(Location loc)
    {
        GriefPrevention gp = (GriefPrevention) plugin;
        return gp.dataStore.getClaimAt(loc, false, null) != null;
    }

    @Override
    public boolean isOwner(Player player)
    {
        GriefPrevention gp = (GriefPrevention) plugin;
        Claim claim = gp.dataStore.getClaimAt(player.getLocation(), false, null);
        if (claim == null)
            return false;
        return claim.getOwnerName().equalsIgnoreCase(player.getName());
    }

}
