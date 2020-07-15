package com.elikill58.negativity.inventories.admin;

import com.elikill58.negativity.common.entity.Player;
import com.elikill58.negativity.common.events.inventory.InventoryClickEvent;
import com.elikill58.negativity.common.inventory.AbstractInventory;
import com.elikill58.negativity.common.inventory.Inventory;
import com.elikill58.negativity.common.inventory.InventoryManager;
import com.elikill58.negativity.common.inventory.NegativityHolder;
import com.elikill58.negativity.common.item.ItemBuilder;
import com.elikill58.negativity.common.item.Material;
import com.elikill58.negativity.common.item.Materials;
import com.elikill58.negativity.inventories.holders.admin.CheatManagerHolder;
import com.elikill58.negativity.spigot.Inv;
import com.elikill58.negativity.universal.Cheat;
import com.elikill58.negativity.universal.Messages;
import com.elikill58.negativity.universal.utils.UniversalUtils;

public class CheatManagerInventory extends AbstractInventory {

	public CheatManagerInventory() {
		super(NegativityInventory.CHEAT_MANAGER);
	}
	
	@Override
	public void openInventory(Player p, Object... args){
		Inventory inv = Inventory.createInventory(Inv.CHEAT_MANAGER, UniversalUtils.getMultipleOf(Cheat.values().size() + 3, 9, 1, 54), new CheatManagerHolder((boolean) args[0]));
		int slot = 0;
		for(Cheat c : Cheat.values())
			if(c.getMaterial() != null)
				inv.set(slot++, ItemBuilder.Builder(c.getMaterial()).displayName(c.getName()).build());

		inv.set(inv.getSize() - 2, ItemBuilder.Builder(Materials.ARROW).displayName(Messages.getMessage(p, "inventory.back")).build());
		inv.set(inv.getSize() - 1, ItemBuilder.Builder(Materials.BARRIER).displayName(Messages.getMessage(p, "inventory.close")).build());
		p.openInventory(inv);
	}

	@Override
	public void manageInventory(InventoryClickEvent e, Material m, Player p, NegativityHolder nh) {
		if (m.equals(Materials.ARROW))
			InventoryManager.open(((CheatManagerHolder) nh).isFromAdmin() ? NegativityInventory.ADMIN : NegativityInventory.MOD, p);
		else {
			UniversalUtils.getCheatFromItem(m).ifPresent((c) -> InventoryManager.open(NegativityInventory.ONE_CHEAT, p, c));
		}
	}

	@Override
	public boolean isInstance(NegativityHolder nh) {
		return nh instanceof CheatManagerHolder;
	}
}
