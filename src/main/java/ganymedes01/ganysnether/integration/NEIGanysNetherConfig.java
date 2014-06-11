package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.integration.nei.AnvilHandler;
import ganymedes01.ganysnether.integration.nei.MagmaticCentrifugeRecipeHandler;
import ganymedes01.ganysnether.integration.nei.OreDictionaryHandler;
import ganymedes01.ganysnether.integration.nei.ReproducerRecipeHandler;
import ganymedes01.ganysnether.integration.nei.VolcanicFurnaceYieldHandler;
import ganymedes01.ganysnether.lib.Reference;
import net.minecraft.item.ItemStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class NEIGanysNetherConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new MagmaticCentrifugeRecipeHandler());
		API.registerUsageHandler(new MagmaticCentrifugeRecipeHandler());

		API.registerRecipeHandler(new ReproducerRecipeHandler());
		API.registerUsageHandler(new ReproducerRecipeHandler());

		if (GanysNether.enableOreDictNEILookUp) {
			API.registerRecipeHandler(new OreDictionaryHandler());
			API.registerUsageHandler(new OreDictionaryHandler());
		}

		if (GanysNether.enableAnvilRepairNEILookUp) {
			API.registerRecipeHandler(new AnvilHandler());
			API.registerUsageHandler(new AnvilHandler());
		}

		API.registerUsageHandler(new VolcanicFurnaceYieldHandler());
		API.registerRecipeHandler(new VolcanicFurnaceYieldHandler());

		API.hideItem(new ItemStack(ModBlocks.tilledNetherrack));
		API.hideItem(new ItemStack(ModBlocks.spectreWheat));
		API.hideItem(new ItemStack(ModBlocks.quarzBerryBush));
		API.hideItem(new ItemStack(ModBlocks.glowingReed));
		API.hideItem(new ItemStack(ModBlocks.witherShrub));
		API.hideItem(new ItemStack(ModBlocks.weepingPod));
		API.hideItem(new ItemStack(ModBlocks.hellBush));
		if (!GanysNether.enableUndertaker)
			API.hideItem(new ItemStack(ModBlocks.undertaker));
	}

	@Override
	public String getName() {
		return Reference.MOD_NAME;
	}

	@Override
	public String getVersion() {
		return Reference.VERSION_NUMBER;
	}
}