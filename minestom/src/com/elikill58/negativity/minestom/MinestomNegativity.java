package com.elikill58.negativity.minestom;

import com.elikill58.negativity.api.yaml.Configuration;
import com.elikill58.negativity.minestom.listeners.*;
import com.elikill58.negativity.universal.Adapter;
import com.elikill58.negativity.universal.Negativity;
import com.elikill58.negativity.universal.ban.BanManager;
import com.elikill58.negativity.universal.storage.account.NegativityAccountStorage;
import com.elikill58.negativity.universal.warn.WarnManager;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinestomNegativity {

	public static final List<String> ALL_COMMANDS = new ArrayList<>();
	public static final Logger logger = LoggerFactory.getLogger("Negativity");

	public final Path dataDirectory;

    public MinestomNegativity(Path dataDirectory) {
        this.dataDirectory = dataDirectory;
    }

    public void initialize() {
		new File(getDataDirectory().toFile(), "user" + File.separator + "proof").mkdirs();

		Adapter.setAdapter(new MinestomAdapter(this, logger));

		Negativity.loadNegativity();

		NegativityAccountStorage.setDefaultStorage("file");

		new BlockListeners(getEventNode());
		new PlayersListeners(getEventNode());
		new EntityListeners(getEventNode());
		new InventoryListeners(getEventNode());
		new PacketListeners(getEventNode());

		loadCommands();
		logger.info("Negativity loaded.");
	}

	public Path getDataDirectory() {
		return dataDirectory;
	}

	public EventNode<Event> getEventNode() {
		return MinecraftServer.getGlobalEventHandler();
	}

	public void terminate() {
		Negativity.closeNegativity();
	}

	private void loadCommands() {
		registerCommand(null, "negativity", "neg", "n");
		registerCommand("nmod", "nmod", "mod");
		registerCommand("kick", "nkick", "kick");
		registerCommand("lang", "nlang", "lang");
		registerCommand("report", "nreport", "report", "repot");
		registerCommand("ban", "nban", "negban", "ban");
		registerCommand("unban", "nunban", "negunban", "unban");
		registerCommand("chat.clear", "nclearchat", "clearchat");
		registerCommand("chat.lock", "nlockchat", "lockchat");
		registerCommand("warn", "nwarn", "warn");
	}

	private void registerCommand(String configKey, String cmd, String... alias) {
		Configuration conf = Adapter.getAdapter().getConfig();
		if (configKey != null) {
			if (configKey.endsWith("ban"))
				conf = BanManager.getBanConfig();
			if (configKey.endsWith("warn"))
				conf = WarnManager.getWarnConfig();
		}
		if (configKey == null || conf.getBoolean("commands." + configKey)) {
			ALL_COMMANDS.add(cmd);
			ALL_COMMANDS.addAll(Arrays.asList(alias));
			MinecraftServer.getCommandManager().register(new MinestomCommand(cmd, alias));
		}
	}

	public static List<Player> getOnlinePlayers() {
		return new ArrayList<>(MinecraftServer.getConnectionManager().getOnlinePlayers());
	}
}
