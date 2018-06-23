# PermissionInjector [![](https://jitpack.io/v/me.yourpaljake/PermissionInjector.svg?style=flat-square)](https://jitpack.io/#me.yourpaljake/PermissionInjector) [![](https://img.shields.io/badge/Spigot-v1.0-brightgreen.svg?style=flat-square)](https://www.spigotmc.org/resources/permissioninjector.58048/) [![Discord server](https://img.shields.io/badge/Join-YourPalJake%20Coding-7289DA.svg?style=flat-square&logo=discord)](https://discord.gg/7ECY95m)

[Spigot](https://www.spigotmc.org/) API allowing you to easily inject your own PermissibleBase into a player without doing the tricky part!

## How to add PermissionInjector to your project

Hence that `@VERSION` is the latest available version on [JitPack](https://jitpack.io/#me.yourpaljake/PermissionInjector) or [Spigot](https://www.spigotmc.org/resources/permissioninjector.58048/)

### With maven

In your `pom.xml` add
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>me.yourpaljake</groupId>
    <artifactId>PermissionInjector</artifactId>
    <version>@VERSION</version>
    <scope>provided</scope>
  </dependency>
</dependencies>
```

### With buildpath

**IntelliJ**

Module Settings > Dependencies > click the + > JARs or directories > Select your JAR file


**Eclipse**

Project Properties > Java Build Path > Add the jar file

### How to use the API

**NOTE** These are examples how I did it in my permission plugins, you can totaly go a different way if you want but this shows how to use the Injector class!

**Manager class example**
```java
package yourname.yourplugin;

import me.yourpaljake.permissioninjector.Injector;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class Manager implements Listener {
    private Injector injector;
    
    public Manager(){
        injector = new Injector();
    }
    
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){
        this.injector.inject(e.getPlayer(), new ModifiedPermissible(e.getPlayer())); //Inject stuff
    }
    
    //Your storage system can go here
    
}
```

**PermissibleBase class example**
```java
package yourname.yourplugin;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;

public class ModifiedPermissible extends PermissibleBase {

    public ModifiedPermissible(Player player){
        super(player);
    }

    @Override
    public boolean hasPermission(String inName) {
        //You do your storage check here!
        return false;
    }
}
```

**Good luck!**

### Support
**If you need any aditional help you can go to these following things:**

* My [discord](https://discord.gg/7ECY95m) server.
* My discord: YourPalJake#2493
* My [spigot](https://www.spigotmc.org/members/yourpaljake.34926/) profile.
