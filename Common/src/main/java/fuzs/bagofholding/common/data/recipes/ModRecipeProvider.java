package fuzs.bagofholding.common.data.recipes;

import fuzs.bagofholding.common.BagOfHolding;
import fuzs.bagofholding.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import fuzs.puzzleslib.common.api.data.v3.recipes.TransmuteShapedRecipeBuilder;
import fuzs.puzzleslib.common.api.init.v3.registry.ContentRegistrationHelper;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Blocks;

public class ModRecipeProvider extends AbstractRecipeProvider {
    private final HolderGetter<Item> itemLookup;

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
        this.itemLookup = recipeOutput.lookup(Registries.ITEM);
    }

    @Override
    public void buildRecipes() {
        ShapedRecipeBuilder.shaped(this.itemLookup, RecipeCategory.TOOLS, ModRegistry.LEATHER_BAG_OF_HOLDING_ITEM.value())
                .define('C', Blocks.CHEST)
                .define('S', Items.STRING)
                .define('I', Items.LEATHER)
                .define('W', ItemTags.WOOL)
                .pattern("SIS")
                .pattern("ICI")
                .pattern("WIW")
                .unlockedBy(getHasName(Blocks.CHEST), this.has(Blocks.CHEST))
                .save(this.output);
        ResourceKey<RecipeSerializer<?>> resourceKey = ContentRegistrationHelper.getTransmuteShapedRecipeSerializer(
                BagOfHolding.MOD_ID);
        TransmuteShapedRecipeBuilder.shaped(resourceKey,
                        this.itemLookup,
                        RecipeCategory.TOOLS,
                        ModRegistry.IRON_BAG_OF_HOLDING_ITEM.value())
                .define('C', ModRegistry.LEATHER_BAG_OF_HOLDING_ITEM.value())
                .define('S', Items.STRING)
                .define('I', Items.IRON_INGOT)
                .define('G', Items.DIAMOND)
                .define('W', ItemTags.WOOL)
                .input(ModRegistry.LEATHER_BAG_OF_HOLDING_ITEM.value())
                .pattern("SGS")
                .pattern("ICI")
                .pattern("WIW")
                .unlockedBy(getHasName(ModRegistry.LEATHER_BAG_OF_HOLDING_ITEM.value()),
                        this.has(ModRegistry.LEATHER_BAG_OF_HOLDING_ITEM.value()))
                .save(this.output);
        TransmuteShapedRecipeBuilder.shaped(resourceKey,
                        this.itemLookup,
                        RecipeCategory.TOOLS,
                        ModRegistry.GOLDEN_BAG_OF_HOLDING_ITEM.value())
                .define('C', ModRegistry.IRON_BAG_OF_HOLDING_ITEM.value())
                .define('S', Items.STRING)
                .define('I', Items.GOLD_INGOT)
                .define('G', Items.AMETHYST_SHARD)
                .define('W', ItemTags.WOOL)
                .input(ModRegistry.IRON_BAG_OF_HOLDING_ITEM.value())
                .pattern("SGS")
                .pattern("ICI")
                .pattern("WIW")
                .unlockedBy(getHasName(ModRegistry.IRON_BAG_OF_HOLDING_ITEM.value()),
                        this.has(ModRegistry.IRON_BAG_OF_HOLDING_ITEM.value()))
                .save(this.output);
    }
}
