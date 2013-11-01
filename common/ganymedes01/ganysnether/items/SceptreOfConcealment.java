package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.ConcealableHandler;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SceptreOfConcealment extends Sceptre {

	public static final int DEFAULT_DUR = 128;

	public SceptreOfConcealment() {
		super(ModIDs.SCEPTRE_OF_CONCEALMENT_ID);
		setTextureName(Utils.getItemTexture(Strings.SCEPTRE_OF_CONCEALMENT_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SCEPTRE_OF_CONCEALMENT_NAME));
		setMaxDamage(GanysNether.sceptreOfConcealmentDurability > 0 ? GanysNether.sceptreOfConcealmentDurability : DEFAULT_DUR);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (!(entity instanceof EntityLivingBase))
			return false;
		EntityLivingBase target = (EntityLivingBase) entity;

		if (target.isChild())
			return false;
		if (ConcealableHandler.canBeConcealed(target))
			if (player instanceof EntityPlayer)
				if (player.inventory.consumeInventoryItem(Item.egg.itemID)) {
					if (!player.worldObj.isRemote) {
						player.worldObj.playSoundAtEntity(target, "random.breath", 1.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);
						target.entityDropItem(ConcealableHandler.getEggFromEntity(target), 1.0F);
						target.setDead();
						stack.damageItem(1, player);
					}
					return true;
				}
		return false;
	}
}
