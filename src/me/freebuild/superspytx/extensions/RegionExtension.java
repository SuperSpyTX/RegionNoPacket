package me.freebuild.superspytx.extensions;

import me.freebuild.superspytx.Permissions;

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
    
    public Permissions getPermission()
    {
        return null;
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
