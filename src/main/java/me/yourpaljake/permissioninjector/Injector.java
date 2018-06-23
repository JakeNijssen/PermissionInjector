package me.yourpaljake.permissioninjector;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;

import java.lang.reflect.Field;

@SuppressWarnings("all")
public class Injector {
    private String CRAFTBUKKIT_PREFIX = "org.bukkit.craftbukkit";
    private String VERSION;
    private String className;
    private String fieldName;

    public Injector(){
        Class serverClass = Bukkit.getServer().getClass();
        if(!serverClass.getSimpleName().equals("CraftServer")){
            this.VERSION = null;
        }else if(serverClass.getName().equals("org.bukkit.craftbukkit.CraftServer")){
            this.VERSION = ".";
        }else{
            String string = serverClass.getName();
            string = string.substring("org.bukkit.craftbukkit".length());
            this.VERSION = string = string.substring(0, string.length() - "CraftServer".length());
        }
        this.className = getCBClassName("entity.CraftHumanEntity");
        this.fieldName = "perm";
    }

    public String getCBClassName(String className){
        if(this.VERSION == null){
            return null;
        }
        return this.CRAFTBUKKIT_PREFIX + this.VERSION + className;
    }

    public void inject(Player player, PermissibleBase permissibleBase){
        try{
            Field permissibleField = this.getPermissibleField(player);
            if(permissibleBase == null){
                return;
            }
            permissibleField.set((Object)player, (Object)permissibleBase);
        }catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    private Field getPermissibleField(Player player)throws NoSuchFieldException{
        Class craftHumanEntityClass = null;
        try{
            craftHumanEntityClass = Class.forName(this.className);
        }catch (ClassNotFoundException e){
            System.out.print("Invalid server jar!");
        }
        if(!craftHumanEntityClass.isAssignableFrom(player.getClass())){
            Bukkit.getLogger().severe("Failed to inject permissions for " + player.getName());
            return null;
        }
        Field permissibleField = craftHumanEntityClass.getDeclaredField(this.fieldName);
        permissibleField.setAccessible(true);
        return permissibleField;

    }
}
