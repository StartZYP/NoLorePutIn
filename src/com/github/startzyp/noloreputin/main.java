package com.github.startzyp.noloreputin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class main extends JavaPlugin implements Listener {
    private String Msg;
    @Override
    public void onEnable(){
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()){
            saveDefaultConfig();
        }
        Msg = getConfig().getString("Msg");
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        super.onEnable();
    }

    @EventHandler
    public void PlayerClickInventory(InventoryClickEvent event){
        try{
            if (event.getInventory().getType().getDefaultTitle().equalsIgnoreCase("Enchanting")){
                if (event.getClick()== ClickType.SHIFT_LEFT){
                    event.setCancelled(true);
                }
                ItemStack cursor = event.getCurrentItem();
                if (cursor.hasItemMeta()){
                    if (cursor.getItemMeta().hasLore()){
                        event.getWhoClicked().sendMessage(Msg);
                        event.setCancelled(true);
                    }
                }
            }
        }catch (Exception e){

        }
    }

    @EventHandler
    public void PlayerDragInventory(InventoryInteractEvent event){
        try{
            if (event.getView().getTitle().equalsIgnoreCase("Enchanting")){
                System.out.println("Enter");
                event.setCancelled(true);
            }
        }catch (Exception e){

        }
    }

    @Override
    public void onDisable(){
        super.onDisable();
    }
}
