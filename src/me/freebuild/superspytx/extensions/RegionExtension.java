package me.freebuild.superspytx.extensions;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RegionExtension
{
    protected Object plugin = null;
    
    public RegionExtension(Object plug)
    {
        plugin = plug;
    }
    
    public String getName()
    {
        return "Dummy Extension";
    }
    
    public String getPermFriendlyName()
    {
        return "dummyextension";
    }
    
    public boolean inRegion(Location loc)
    {
        return false;
    }
    
    public boolean isOwner(Player player)
    {
        return false;
    }
    
}
