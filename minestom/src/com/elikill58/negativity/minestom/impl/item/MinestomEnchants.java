package com.elikill58.negativity.minestom.impl.item;

import com.elikill58.negativity.api.item.Enchantment;
import net.minestom.server.MinecraftServer;
import net.minestom.server.utils.NamespaceID;

public class MinestomEnchants {
	
	public static net.minestom.server.item.enchant.Enchantment getEnchant(Enchantment enchant) {
		return MinecraftServer.getEnchantmentRegistry().get(NamespaceID.from(enchant.getId()));
	}
}
