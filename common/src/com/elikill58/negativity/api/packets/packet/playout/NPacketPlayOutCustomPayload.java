package com.elikill58.negativity.api.packets.packet.playout;

import com.elikill58.negativity.api.packets.PacketType;
import com.elikill58.negativity.api.packets.PacketType.Server;
import com.elikill58.negativity.api.packets.nms.PacketSerializer;
import com.elikill58.negativity.api.packets.packet.NPacketPlayOut;
import com.elikill58.negativity.universal.Version;

public class NPacketPlayOutCustomPayload implements NPacketPlayOut {

	public String channel;
	public byte[] data;
	
	@Override
	public void read(PacketSerializer serializer, Version version) {
		this.channel = serializer.readString();
		this.data = serializer.readByteArray(1048576);
	}

	@Override
	public void write(PacketSerializer serializer, Version version) {
		serializer.writeString(channel);
		serializer.writeBytes(data);
	}
	
	@Override
	public PacketType getPacketType() {
		return Server.CUSTOM_PAYLOAD;
	}
}
