package io.ix0rai.bodacious_berries.registry;

import io.ix0rai.bodacious_berries.BodaciousBerries;
import io.ix0rai.bodacious_berries.block.BerryHarvesterBlock;
import io.ix0rai.bodacious_berries.block.JuicerBlock;
import io.ix0rai.bodacious_berries.block.entity.BerryHarvesterBlockEntity;
import io.ix0rai.bodacious_berries.block.entity.BerryHarvesterScreenHandler;
import io.ix0rai.bodacious_berries.block.entity.JuicerBlockEntity;
import io.ix0rai.bodacious_berries.block.entity.JuicerScreenHandler;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BodaciousBlocks {
    public static final Identifier BERRY_HARVESTER = BodaciousBerries.id("berry_harvester");
    public static final BerryHarvesterBlock BERRY_HARVESTER_BLOCK = new BerryHarvesterBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final BlockEntityType<BerryHarvesterBlockEntity> BERRY_HARVESTER_ENTITY = FabricBlockEntityTypeBuilder.create(BerryHarvesterBlockEntity::new, BERRY_HARVESTER_BLOCK).build(null);
    public static final ScreenHandlerType<BerryHarvesterScreenHandler> BERRY_HARVESTER_SCREEN_HANDLER = new ScreenHandlerType<>(BerryHarvesterScreenHandler::new);

    public static final Identifier JUICER = BodaciousBerries.id("juicer");
    public static final JuicerBlock JUICER_BLOCK = new JuicerBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final BlockEntityType<JuicerBlockEntity> JUICER_ENTITY = FabricBlockEntityTypeBuilder.create(JuicerBlockEntity::new, JUICER_BLOCK).build(null);
    public static final ScreenHandlerType<JuicerScreenHandler> JUICER_SCREEN_HANDLER = new ScreenHandlerType<>(JuicerScreenHandler::new);

    public static void register() {
        register(BERRY_HARVESTER, BERRY_HARVESTER_BLOCK, BERRY_HARVESTER_ENTITY, BERRY_HARVESTER_SCREEN_HANDLER, ItemGroup.REDSTONE);
        register(JUICER, JUICER_BLOCK, JUICER_ENTITY, JUICER_SCREEN_HANDLER, ItemGroup.BREWING);
    }

    private static void register(Identifier id, Block block, BlockEntityType<?> entity, ScreenHandlerType<?> handler, ItemGroup group) {
        Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.SCREEN_HANDLER, id, handler);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, BodaciousBerries.id(id.getPath() + "_entity"), entity);
        Registry.register(Registry.ITEM, id, new BlockItem(block, new Settings().group(group)));
    }
}
