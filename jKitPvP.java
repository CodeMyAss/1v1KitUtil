package jKitPvP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class jKitPvP extends JavaPlugin implements Listener {
	private FileConfiguration config;
	ArrayList<String> NoMove = new ArrayList<String>();
	HashMap<String, String> Vs = new HashMap<String, String>();
	ArrayList<String> Move = new ArrayList<String>();
	ArrayList<String> Battle = new ArrayList<String>();
	ArrayList<String> ib = new ArrayList<String>();
	ArrayList<String> has = new ArrayList<String>();
	ArrayList<String> left = new ArrayList<String>();
	public void onEnable() {
		this.saveDefaultConfig();
		config = this.getConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	 public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if(cmd.getName().equalsIgnoreCase("1v1")) {
				final Player p = (Player) sender;
				if(!(args.length > 0)) {
				if(!(Battle.contains(p.getName()))) {
			    Inventory inv = Bukkit.createInventory(p, 54, ChatColor.AQUA + "1v1");
			    ItemStack book = new ItemStack(Material.BOOK);
			    List<String> bookl = new ArrayList<String>();
			    bookl.add(ChatColor.GOLD + "Click the name of the player you want to 1v1!");
			    ItemMeta bookm = book.getItemMeta();
			    bookm.setLore(bookl);
			    bookm.setDisplayName(ChatColor.BOLD + "Help");
			    book.setItemMeta(bookm);
			    inv.addItem(book);
			    for(Player players : Bukkit.getOnlinePlayers()) {
			if(!players.getName().equals(p.getName())) {
			
					String pl = players.getName();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					SkullMeta sm = (SkullMeta) skull.getItemMeta();
					skull.setDurability((short) 3);
					sm.hasOwner();
				    sm.setOwner(pl);
					sm.setDisplayName(pl);
					List<String> lore = new ArrayList<String>();
					lore.add(ChatColor.GREEN + "Challenge " + ChatColor.RED + "" + pl + ChatColor.GREEN + " to a kitpvp 1v1!");
					sm.setLore(lore);
					skull.setItemMeta(sm);
					skull.addUnsafeEnchantment(Enchantment.THORNS, 1);
					
					inv.addItem(skull); 
			      
			}
			    }
			    
			    
			    
			    p.openInventory(inv);  
	 } 	
		} 
				if(args.length == 1) {
			if(args[0].equalsIgnoreCase("accept")) {
				final Player player = (Player) p;
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(!(players.getName().equals(player.getName()))) {
						if(Vs.containsKey(players.getName())) {
					if(Vs.get(players.getName()).equals(p.getName())) {
						if(Battle.contains(players.getName())) {
						players.sendMessage(ChatColor.GOLD + "" + player.getName() + " " + ChatColor.AQUA + "has accepted your 1v1 request!");
						for(Player playe : Bukkit.getOnlinePlayers()) {
							p.hidePlayer(playe);
							players.hidePlayer(playe);
							p.showPlayer(p);
							players.showPlayer(p);
						}
						p.sendMessage(ChatColor.RED + "Your arena has been built! Teleporting...");
						players.sendMessage(ChatColor.RED + "Your arena has been built! Teleporting...");
						p.showPlayer(players);
						players.showPlayer(p);
						NoMove.remove(p.getName());
						NoMove.remove(players.getName());
						ib.add(p.getName());
						ib.add(players.getName());
						Battle.remove(players.getName());
						Battle.remove(p.getName());
						String world = config.getString("world");
						int x1 = config.getInt("X1");
						int y1 = config.getInt("Y1");
						int z1 = config.getInt("Z1");
						int x = config.getInt("X");
						int y = config.getInt("Y");
						int z = config.getInt("Z");
						float pitch = config.getInt("pitch");
						float yaw = config.getInt("yaw");
						 float pitch1 = config.getInt("pitch1");
						 float yaw1 = config.getInt("yaw1");
						double p1x = x + .50;
						double p1y = y;
						double p1z = z + .50;
						double p2x = x1 + .50;
						double p2y = y1;
						double p2z = z1 + .50;
						players.teleport(new Location(Bukkit.getWorld("" + world), p2x, p2y, p2z, yaw1, pitch1));
						player.teleport(new Location(Bukkit.getWorld("" + world), p1x, p1y, p1z, yaw, pitch));
						Move.remove(player.getName());
						Move.remove(players.getName());
						NoMove.add(player.getName());
						NoMove.add(players.getName());
						player.getInventory().clear();
						players.getInventory().clear();
				        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
				        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
				        ItemStack helm = new ItemStack(Material.IRON_HELMET);
				        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
				        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
				        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
				        sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
				        player.setGameMode(GameMode.SURVIVAL);
				        players.setGameMode(GameMode.SURVIVAL);
				        player.getInventory().setHelmet(helm);
				        player.getInventory().setChestplate(chest);
				        player.getInventory().setLeggings(legs);
				        player.getInventory().setBoots(boots);
				        players.getInventory().setHelmet(helm);
				        players.getInventory().setChestplate(chest);
				        players.getInventory().setLeggings(legs);
				        players.getInventory().setBoots(boots);
				        players.getInventory().addItem(sword);
				        player.getInventory().addItem(sword);
				        player.getInventory().addItem(soup);
				        player.getInventory().addItem(soup);
				        player.getInventory().addItem(soup);
				        player.getInventory().addItem(soup);
				        player.getInventory().addItem(soup);
				        player.getInventory().addItem(soup);
				        player.getInventory().addItem(soup);
				        player.getInventory().addItem(soup);
				        players.getInventory().addItem(soup);
				        players.getInventory().addItem(soup);
				        players.getInventory().addItem(soup);
				        players.getInventory().addItem(soup);
				        players.getInventory().addItem(soup);
				        players.getInventory().addItem(soup);
				        players.getInventory().addItem(soup);
				        players.getInventory().addItem(soup);
				        final Player pl = (Player) players;
				this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						NoMove.remove(pl.getName());
						NoMove.remove(player.getName());
						player.sendMessage(ChatColor.GREEN + "GO");
						pl.sendMessage(ChatColor.GREEN + "GO");
						String p1 = pl.getName();
						String p2 = player.getName();
						Bukkit.getPlayer(p1).playSound(pl.getLocation(),Sound.NOTE_PLING,1, (float) 2);
						Bukkit.getPlayer(p2).playSound(player.getLocation(),Sound.NOTE_PLING,1, (float) 2);
					} }, 80L);
				this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						String p1 = pl.getName();
						String p2 = player.getName();
						player.sendMessage(ChatColor.RED + "1");
						pl.sendMessage(ChatColor.RED + "1");
						Bukkit.getPlayer(p1).playSound(pl.getLocation(),Sound.NOTE_PLING,1, (float) 1.20);
						Bukkit.getPlayer(p2).playSound(player.getLocation(),Sound.NOTE_PLING,1, (float) 1.20);
					} }, 60L);
				this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						String p1 = pl.getName();
						String p2 = player.getName();
						player.sendMessage(ChatColor.RED + "2..");
						pl.sendMessage(ChatColor.RED + "2..");
						Bukkit.getPlayer(p1).playSound(pl.getLocation(),Sound.NOTE_PLING,1, (float) 1.20);
						Bukkit.getPlayer(p2).playSound(player.getLocation(),Sound.NOTE_PLING,1, (float) 1.20);
						
					} }, 40L);
				this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						String p1 = pl.getName();
						String p2 = player.getName();
						player.sendMessage(ChatColor.RED + "3...");
						pl.sendMessage(ChatColor.RED + "3...");
						Bukkit.getPlayer(p1).playSound(pl.getLocation(),Sound.NOTE_PLING,1, (float) 1.20);
						Bukkit.getPlayer(p2).playSound(player.getLocation(),Sound.NOTE_PLING,1, (float) 1.20);
						
					} }, 20L);
				Vs.remove(player.getName());
				Vs.put(player.getName(), players.getName());
										}
									}
								}
							}
						}
					}
				}
			if(args.length > 0) {
				
					if(args[0].equalsIgnoreCase("request")) {
					
						if(args.length == 1) {
							p.sendMessage(ChatColor.RED + "Invalid arguments. Correct usage: /1v1 request <Player Name>");
						} else 
						if(args.length == 2) {
							if (p.getServer().getPlayer(args[1]) != null) {
								Player players = p.getServer().getPlayer(args[1]);
								final Player playerss = players;
									if(!(has.contains(players.getName()))) {
									p.sendMessage(ChatColor.RED + "Challenged!");
									players.sendMessage(ChatColor.GOLD + "You have been challenged to a 1v1 by " + ChatColor.RED + "" + p.getName() + ChatColor.GOLD + "!" + ChatColor.DARK_AQUA + " Type /1v1 accept to accept the request!");
									Vs.put(p.getName(), players.getName());
									Battle.add(p.getName());
									has.add(players.getName());
									this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
									public void run() {
									if(Battle.contains(p.getName())) {
										Battle.remove(p.getName());
										has.remove(playerss.getName());
										playerss.sendMessage(ChatColor.DARK_RED + "" + p.getName() + "'s" + ChatColor.RED + " 1v1 request with you has timed out!");
										p.sendMessage(ChatColor.RED + "Your attempted 1v1 with " + ChatColor.DARK_RED + "" + playerss.getName() + ChatColor.RED + " has timed out! :(");
									}
									} }, 600L);
										}
								} else {
									p.sendMessage(ChatColor.RED + "The specified player is not online!");
								}
							}
						}
					}
			}
			if(cmd.getName().equalsIgnoreCase("SetLoc1")) {
				Player p = (Player) sender;
				if(p.hasPermission("kitpvp.setloc1")) {
				p.sendMessage(ChatColor.GOLD + "Set the 1st pvp location for Kit-PvP!");
			Location location = p.getLocation();
			double X = location.getBlockX();
			 double Y = location.getBlockY();
			 double Z = location.getBlockZ();
			 double pitch = location.getPitch();
			 double yaw = location.getYaw();
			 config.set("X", X);
			 config.set("Y", Y);
			 config.set("Z", Z);
			 config.set("pitch", pitch);
			 config.set("yaw", yaw);
			 config.set("world", "" + p.getWorld().getName());
			 saveConfig(); }
		
			}
			if(cmd.getName().equalsIgnoreCase("SetLoc2")) {
				Player p = (Player) sender;
				if(p.hasPermission("kitpvp.setloc2")) {
				p.sendMessage(ChatColor.GOLD + "Set the 2nd pvp location for Kit-PvP!");
			Location location2 = p.getLocation();
			double X1 = location2.getBlockX();
			 double Y1 = location2.getBlockY();
			 double Z1 = location2.getBlockZ();
			 double pitch = location2.getPitch();
			 double yaw = location2.getYaw();
			 config.set("X1", X1);
			 config.set("Y1", Y1);
			 config.set("Z1", Z1);
			 config.set("pitch1", pitch);
			 config.set("yaw1", yaw);
			 config.set("world", "" + p.getWorld().getName());
			 saveConfig(); }
			} 
			if(cmd.getName().equalsIgnoreCase("SetReturn")) {
		//Do stuff
				
				Player p = (Player) sender;
				if(p.hasPermission("kitpvp.setreturn")) {
				p.sendMessage(ChatColor.GOLD + "Set the pvp return location for Kit-PvP!");
	     Location location = p.getLocation();
	     double X = location.getBlockX();
		 double Y = location.getBlockY();
		 double Z = location.getBlockZ();
		 double pitch = location.getPitch();
		 double yaw = location.getYaw();
		 config.set("ReturnX", X);
		 config.set("ReturnY", Y);
		 config.set("ReturnZ", Z);
		 config.set("pitchr", pitch);
		 config.set("yawr", yaw);
	 config.set("world", "" + p.getWorld().getName());
	 saveConfig(); }
	 }
		
			return true; } 
			  
	@EventHandler
	public void onPlayerChallenge(InventoryClickEvent evt) {
		ItemStack item = evt.getCurrentItem();
		final Player p = (Player) evt.getWhoClicked();
		final Player player = (Player) p;
		if(item.getType().equals(Material.BOOK)) {
			if(item.getItemMeta().hasLore()) {
				evt.setCancelled(true);
				}
			}
		if(item.getType().equals(Material.SKULL_ITEM)) {
		
			if(item.getItemMeta().hasEnchant(Enchantment.THORNS)) {
				evt.setCancelled(true);
				p.closeInventory();
		for(final Player players : Bukkit.getOnlinePlayers()) {
			ItemMeta im = item.getItemMeta();
			if(players.getName().equals(im.getDisplayName())) {
				if(!(has.contains(players.getName()))) {
				p.sendMessage(ChatColor.RED + "Challenged!");
				players.sendMessage(ChatColor.GOLD + "You have been challenged to a 1v1 by " + ChatColor.RED + " " + player.getName() + ChatColor.GOLD + "!" + ChatColor.DARK_AQUA + " Type /1v1 accept to accept the request!");
				Vs.put(p.getName(), players.getName());
				Battle.add(p.getName());
				has.add(players.getName());
				this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
				if(Battle.contains(p.getName())) {
					Battle.remove(p.getName());
					has.remove(players.getName());
					players.sendMessage(ChatColor.DARK_RED + "" + p.getName() + "'s" + ChatColor.RED + " 1v1 request with you has timed out!");
					p.sendMessage(ChatColor.RED + "Your attempted 1v1 with " + ChatColor.DARK_RED + "" + players.getName() + ChatColor.RED + " has timed out! :(");
				}
				} }, 600L);
							}
			} } }  } } 
		 
		
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent evt) {
		Player p = evt.getEntity();
		if(p instanceof Player) {
Player player = (Player) p;
		    Player k = player.getKiller();
		      for (Player players : Bukkit.getOnlinePlayers())
		      {
		        player.showPlayer(players);
		        k.showPlayer(players);
			} 
			if(Vs.containsKey(p.getName())) {
				evt.getDrops().clear();
			}
		}
		Player killer = p.getKiller();
	    Player player = (Player) p;
	if(Vs.get(p.getName()).equals(killer.getName())) {
		if(ib.contains(p.getName()) && ib.contains(killer.getName())) {
		killer.setHealth(20);
	killer.getInventory().clear();
	killer.getInventory().setArmorContents(null);
	player.getInventory().clear();
	evt.getDrops().clear();
		int x = config.getInt("ReturnX");
		int y = config.getInt("ReturnY");
		int z = config.getInt("ReturnZ");
		float pitch = config.getInt("pitchr");
		 float yaw = config.getInt("yawr");
		 double x1 = x + .50;
		 double z1 = z + .50;
		String world = config.getString("world");
		Location loc = new Location(Bukkit.getWorld(world), x1, y, z1, yaw, pitch);
		killer.teleport(loc);
		Vs.remove(killer.getName());
		Vs.remove(player.getName());
		ib.remove(killer.getName());
		ib.remove(player.getName());
		has.remove(player.getName());
		has.remove(killer.getName());
		for(Player players : Bukkit.getOnlinePlayers()) {
			killer.showPlayer(players);
				} 
			}
		}
	}
	
	@EventHandler
	public void onPlayerFirstJoin(PlayerJoinEvent evt) {
		Player p = evt.getPlayer();
		p.sendMessage(ChatColor.AQUA + "[" + ChatColor.GOLD + "1v1 System" + ChatColor.AQUA + "] " + ChatColor.RED + "Made by MayoDwarf :)");
		if(left.contains(p.getName())) {
			int x = config.getInt("ReturnX");
			int y = config.getInt("ReturnY");
			int z = config.getInt("ReturnZ");
			float pitch = config.getInt("pitchr");
			 float yaw = config.getInt("yawr");
			 double x1 = x + .50;
			 double z1 = z + .50;
			String world = config.getString("world");
			Location loc = new Location(Bukkit.getWorld(world), x1, y, z1, yaw, pitch);
			p.teleport(loc);
			p.getInventory().setArmorContents(null);
			p.getInventory().clear();
			left.remove(p.getName());
			}
		//You can't see this LOL
		//#HURPDURPHURPDURP HURPDURPHURPDURP MOJANG LEGO MINECRAFT
		}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent evt) {
		Player p = evt.getPlayer();
		if(NoMove.contains(p.getName())) {
			Location loc = p.getLocation();
			p.teleport(loc);
		}
		
		
		
		
	}
	@EventHandler
	 public void onCommand(PlayerCommandPreprocessEvent evt) {
		Player p = evt.getPlayer();  
		if(ib.contains(p.getName())) {
		  evt.setCancelled(true);
		  p.sendMessage(ChatColor.DARK_RED + "You are not allowed to use commmands during a 1v1!");
		  
		  }
	}
		@EventHandler
		public void onPlayerQuit(PlayerQuitEvent evt) {
			Player p = evt.getPlayer();
			String killer = Vs.get(p.getName());
			if(Vs.containsKey(p.getName())) {
		for(Player players : Bukkit.getOnlinePlayers()) {
			if(killer.equalsIgnoreCase(players.getName())) {
				if(ib.contains(players.getName()) && ib.contains(killer)) {
				Player kille = (Player) players;
			left.add(p.getName());
			kille.getInventory().clear();
			kille.getInventory().setArmorContents(null);
			
			
			Bukkit.broadcastMessage(ChatColor.AQUA + "[" + ChatColor.GOLD + "KitPvP" + ChatColor.AQUA + "] " + ChatColor.GREEN + "" + kille.getName() + ChatColor.RED + " has defeated " + ChatColor.GREEN + "" + p.getName() + ChatColor.RED + " by forfeit!");
				kille.sendMessage(ChatColor.AQUA + "You have won the 1v1 by forfeit!"); 
				int x = config.getInt("ReturnX");
				int y = config.getInt("ReturnY");
				int z = config.getInt("ReturnZ");
				float pitch = config.getInt("pitchr");
				 float yaw = config.getInt("yawr");
				 double x1 = x + .50;
				 double z1 = z + .50;
				String world = config.getString("world");
				Location loc = new Location(Bukkit.getWorld(world), x1, y, z1, yaw, pitch);
				kille.teleport(loc);
				Vs.remove(kille.getName());
				Vs.remove(p.getName());
				ib.remove(kille.getName());
				ib.remove(p.getName());
				Battle.remove(p.getName());
				Battle.remove(kille.getName());
				has.remove(p.getName());
				has.remove(kille.getName());
				
				for(Player player : Bukkit.getOnlinePlayers()) {
					kille.showPlayer(player);
					p.showPlayer(player);
					} 
				}
			}
		}
	}
}
		  
		  
		  
		  
		  
	
	
	

	
	
	public void onDisable() {
		
		
	}
}
