package com.elikill58.negativity.velocity;

import java.util.HashMap;

import com.elikill58.negativity.universal.Cheat;
import com.elikill58.negativity.universal.NegativityPlayer;
import com.velocitypowered.api.proxy.Player;

public class VelocityNegativityPlayer extends NegativityPlayer {

	private static HashMap<Player, VelocityNegativityPlayer> players = new HashMap<>();
	private Player p;

	public VelocityNegativityPlayer(Player p) {
		super(p.getUniqueId());
		this.p = p;
		players.put(p, this);
	}

	public static VelocityNegativityPlayer getNegativityPlayer(Player p) {
		return players.containsKey(p) ? players.get(p) : new VelocityNegativityPlayer(p);
	}

	@Override
	public Object getPlayer() {
		return p;
	}

	@Override
	public boolean hasDefaultPermission(String s) {
		return p.hasPermission(s);
	}

	@Override
	public double getLife() {
		return -1;
	}

	@Override
	public String getName() {
		return p.getUsername();
	}

	@Override
	public String getGameMode() {
		return "unknow";
	}

	@Override
	public float getWalkSpeed() {
		return -1;
	}

	@Override
	public int getLevel() {
		return -1;
	}

	@Override
	public void kickPlayer(String reason, String time, String by, boolean def) {
		p.disconnect(VelocityMessages.getMessage(p, "ban.kick_" + (def ? "def" : "time"), "%reason%", reason, "%time%", String.valueOf(time), "%by%", by));
	}

	@Override
	public void banEffect() {
	}

	@Override
	public void startAllAnalyze() {
	}

	@Override
	public int getWarn(Cheat c) {
		return 0;
	}
	
	@Override
	public void startAnalyze(Cheat c) {

	}

	@Override
	public void updateMinerateInFile() {
		
	}

	@Override
	public boolean isOp() {
		return false;
	}
	
	@Override
	public String getIP() {
		return p.getRemoteAddress().getHostName();
	}

	@Override
	public int getAllWarn(Cheat c) {
		return 0;
	}

	@Override
	public String getReason(Cheat c) {
		return null;
	}
}
