package com.elikill58.negativity.inventories.holders.admin;

import com.elikill58.negativity.common.inventory.NegativityHolder;
import com.elikill58.negativity.universal.Cheat;

public class OneCheatHolder extends NegativityHolder {

	private final Cheat c;

	public OneCheatHolder(Cheat c) {
		this.c = c;
	}

	public Cheat getCheat() {
		return c;
	}
}
