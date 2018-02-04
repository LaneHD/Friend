package ch.lanehd.Friend.bukkit.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Main extends JavaPlugin implements PluginMessageListener {
	@Override
	public void onEnable() {
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
	}

	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if (subchannel.equals("SomeSubChannel")) {
			// Use the code sample in the 'Response' sections below to read
			// the data.
		}
	}

	public void sendPluginMessage(String channel, Player p, byte[] message) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Subchannel");
		out.writeUTF("Argument");

		// If you don't care about the player
		// Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
		// Else, specify them
		Player player = Bukkit.getPlayerExact("Example");

		player.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}
}