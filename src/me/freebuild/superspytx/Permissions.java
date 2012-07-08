package me.freebuild.superspytx;

import org.bukkit.entity.Player;

public enum Permissions
{

    /* Basic Functionality */
    SEE,
    VANISH,
    SMOKE,

    /* Extensions */

    SEE_GRIEFPREVENTION,
    SEE_FACTIONS;

    String permission = "";

    Permissions()
    {
        this.permission = "RegionNoPacket." + this.name().toLowerCase().replace("_", ".");
    }

    public boolean checkPermission(Player pl)
    {
        return pl.hasPermission(this.permission);
    }

    @Override
    public String toString()
    {
        return this.permission;
    }

}
