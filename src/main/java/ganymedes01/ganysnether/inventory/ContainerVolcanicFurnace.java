package ganymedes01.ganysnether.inventory;

import ganymedes01.ganysnether.inventory.slots.BetterSlot;
import ganymedes01.ganysnether.recipes.VolcanicFurnaceHandler;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class ContainerVolcanicFurnace extends GanysContainer {

	private final TileEntityVolcanicFurnace furnace;

	public ContainerVolcanicFurnace(InventoryPlayer inventory, TileEntityVolcanicFurnace tile) {
		super(tile);
		furnace = tile;
		addSlotToContainer(new BetterSlot(tile, 0, 48, 36));
		addSlotToContainer(new BetterSlot(tile, 1, 136, 17));
		addSlotToContainer(new BetterSlot(tile, 2, 136, 53));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

		for (int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < crafters.size(); i++)
			furnace.sendGUIData(this, (ICrafting) crafters.get(i));
	}

	@Override
	public void updateProgressBar(int id, int value) {
		furnace.getGUIData(id, value);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack itemStack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotItemStack = slot.getStack();
			itemStack = slotItemStack.copy();

			if (slotIndex < 3) {
				if (!mergeItemStack(slotItemStack, 3, inventorySlots.size(), true))
					return null;
			} else if (FluidContainerRegistry.isEmptyContainer(slotItemStack)) {
				if (!mergeItemStack(slotItemStack, 1, 2, false))
					return null;
			} else if (VolcanicFurnaceHandler.isItemMeltable(slotItemStack)) {
				if (!mergeItemStack(slotItemStack, 0, 1, false))
					return null;
			} else
				return null;

			if (slotItemStack.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();
		}

		return itemStack;
	}
}
