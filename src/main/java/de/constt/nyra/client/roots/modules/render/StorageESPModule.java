package de.constt.nyra.client.roots.modules.render;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.libs.render.RenderingLibrary;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.settings.ColorSettingImplementation;
import de.constt.nyra.client.roots.implementations.settings.SelectSettingImplementation;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.ArrayList;
import java.util.List;

@ModuleInfoAnnotation(
        name = "Storage ESP",
        description = "Displays storage esp information on screen.",
        category = CategoryImplementation.Categories.RENDER,
        internalModuleName = "storageesp"
)
public class StorageESPModule extends ModuleImplementation {
    private final ColorSettingImplementation chestBlockColor;
    private final ColorSettingImplementation furnaceBlockColor;
    private final ColorSettingImplementation barrelBlockColor;
    private final ColorSettingImplementation shulkerBlockColor;
    private final ColorSettingImplementation enderchestBlockColor;
    private final ColorSettingImplementation trappedChestBlockColor;
    private final SelectSettingImplementation storageBlocks;

    private final Minecraft mc = Minecraft.getInstance();
    private final List<FoundStorage> foundBlocks = new ArrayList<>();

    private int scanTicks;

    public StorageESPModule() {
        chestBlockColor = new ColorSettingImplementation("Chest Color", 0x99FFCD00);
        furnaceBlockColor = new ColorSettingImplementation("Furnace Color", 0x99000000);
        barrelBlockColor = new ColorSettingImplementation("Barrel Color", 0x99FF7D00);
        shulkerBlockColor = new ColorSettingImplementation("Shulker Box Color", 0x990042FF);
        enderchestBlockColor = new ColorSettingImplementation("Ender Chest Color", 0x997200FF);
        trappedChestBlockColor = new ColorSettingImplementation("Trapped Chest Color", 0x99FF0000);

        storageBlocks = new SelectSettingImplementation(
                "Storage",
                new String[]{
                        "Chest",
                        "Trapped Chest",
                        "Barrel",
                        "Shulker Box",
                        "Ender Chest",
                        "Furnace"
                },
                new String[]{
                        "Chest",
                        "Barrel",
                        "Shulker Box"
                }
        );

        registerSetting(chestBlockColor);
        registerSetting(furnaceBlockColor);
        registerSetting(barrelBlockColor);
        registerSetting(shulkerBlockColor);
        registerSetting(enderchestBlockColor);
        registerSetting(trappedChestBlockColor);
        registerSetting(storageBlocks);

        LevelRenderEvents.END_MAIN.register(context -> render());
    }

    @Override
    public void onDisable() {
        foundBlocks.clear();
        scanTicks = 0;
    }

    @Override
    public void tick() {
        if (!isEnabled() || mc.level == null || mc.player == null) {
            foundBlocks.clear();
            return;
        }

        if (++scanTicks < 10) {
            return;
        }

        scanTicks = 0;
        foundBlocks.clear();

        int chunkRadius = mc.options.getEffectiveRenderDistance();

        int playerChunkX = mc.player.chunkPosition().x();
        int playerChunkZ = mc.player.chunkPosition().z();

        for (int chunkX = playerChunkX - chunkRadius;
             chunkX <= playerChunkX + chunkRadius;
             chunkX++) {

            for (int chunkZ = playerChunkZ - chunkRadius;
                 chunkZ <= playerChunkZ + chunkRadius;
                 chunkZ++) {

                if (!mc.level.hasChunk(chunkX, chunkZ)) {
                    continue;
                }

                LevelChunk chunk = mc.level.getChunk(chunkX, chunkZ);

                for (BlockEntity blockEntity : chunk.getBlockEntities().values()) {
                    BlockPos pos = blockEntity.getBlockPos();

                    String type = getStorageType(pos);

                    if (type != null) {
                        foundBlocks.add(new FoundStorage(pos.immutable(), type));
                    }
                }
            }
        }
    }

    private String getStorageType(BlockPos pos) {
        if (mc.level.getBlockState(pos).is(Blocks.CHEST) && storageBlocks.isSelected("Chest")) {
            return "Chest";
        }

        if (mc.level.getBlockState(pos).is(Blocks.TRAPPED_CHEST) && storageBlocks.isSelected("Trapped Chest")) {
            return "Trapped Chest";
        }

        if (mc.level.getBlockState(pos).is(Blocks.BARREL) && storageBlocks.isSelected("Barrel")) {
            return "Barrel";
        }

        if (isShulkerBox(pos) && storageBlocks.isSelected("Shulker Box")) {
            return "Shulker Box";
        }

        if (mc.level.getBlockState(pos).is(Blocks.ENDER_CHEST) && storageBlocks.isSelected("Ender Chest")) {
            return "Ender Chest";
        }

        if (mc.level.getBlockState(pos).is(Blocks.FURNACE) && storageBlocks.isSelected("Furnace")) {
            return "Furnace";
        }

        return null;
    }

    private boolean isShulkerBox(BlockPos pos) {
        return mc.level.getBlockState(pos).is(Blocks.SHULKER_BOX)
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.white())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.orange())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.magenta())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.lightBlue())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.yellow())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.lime())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.pink())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.gray())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.lightGray())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.cyan())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.purple())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.blue())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.brown())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.green())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.red())
                || mc.level.getBlockState(pos).is(Blocks.DYED_SHULKER_BOX.black());
    }

    private void render() {
        if (!isEnabled() || mc.level == null || mc.player == null) {
            return;
        }

        for (FoundStorage storage : foundBlocks) {
            float[] rgba = getColor(storage.type()).getRGBA();
            BlockPos pos = storage.pos();

            RenderingLibrary.addBox(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    1.0,
                    1.0,
                    1.0,
                    rgba[0],
                    rgba[1],
                    rgba[2],
                    rgba[3]
            );
        }
    }

    private ColorSettingImplementation getColor(String type) {
        return switch (type) {
            case "Chest" -> chestBlockColor;
            case "Trapped Chest" -> trappedChestBlockColor;
            case "Barrel" -> barrelBlockColor;
            case "Shulker Box" -> shulkerBlockColor;
            case "Ender Chest" -> enderchestBlockColor;
            case "Furnace" -> furnaceBlockColor;
            default -> chestBlockColor;
        };
    }

    private record FoundStorage(BlockPos pos, String type) {
    }
}