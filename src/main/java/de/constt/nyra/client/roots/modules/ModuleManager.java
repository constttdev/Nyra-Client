package de.constt.nyra.client.roots.modules;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.modules.combat.*;
import de.constt.nyra.client.roots.modules.exploits.AutoBookBanSetupModule;
import de.constt.nyra.client.roots.modules.misc.*;
import de.constt.nyra.client.roots.modules.movement.*;
import de.constt.nyra.client.roots.modules.player.*;
import de.constt.nyra.client.roots.modules.render.*;
import de.constt.nyra.client.roots.modules.world.*;
import de.constt.nyra.client.utils.ModuleCacheUtils;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static final List<ModuleImplementation> MODULES = new ArrayList<>();

    public static void init() {
        // MODULES
        // -- COMBAT --
        MODULES.add(new AimAssistModule());
        MODULES.add(new AnchorAuraModule());
        MODULES.add(new AntiAnchorModule());
        MODULES.add(new AntiAnvilModule());
        MODULES.add(new AntiBedModule());
        MODULES.add(new AttributeSwapModule());
        MODULES.add(new AutoAnvilModule());
        MODULES.add(new AutoArmorModule());
        MODULES.add(new AutoEXPModule());
        MODULES.add(new AutoLogModule());
        MODULES.add(new AutoTotemModule());
        MODULES.add(new AutoTrapModule());
        MODULES.add(new AutoWeaponModule());
        MODULES.add(new AutoWebModule());
        MODULES.add(new BedAuraModule());
        MODULES.add(new BowAimbotModule());
        MODULES.add(new BowSpamModule());
        MODULES.add(new BurrowModule());
        MODULES.add(new CriticalsModule());
        MODULES.add(new CrystalAuraModule());
        MODULES.add(new HitboxesModule());
        MODULES.add(new HitCrystalModule());
        MODULES.add(new HoleFillerModule());
        MODULES.add(new KillAuraModule());
        MODULES.add(new MaceArmorBreakerModule());
        MODULES.add(new OffhandModule());
        MODULES.add(new PearlCatcherModule());
        MODULES.add(new SafeAnchorModule());
        MODULES.add(new SelfAnvilModule());
        MODULES.add(new SelfTrapModule());
        MODULES.add(new SelfWebModule());
        MODULES.add(new ShieldBreakerModule());
        MODULES.add(new SpearDashModule());
        MODULES.add(new SurroundModule());
        MODULES.add(new TriggerbotModule());

        // -- EXPLOITS --
        MODULES.add(new AutoBookBanSetupModule());

        // -- MISC --
        MODULES.add(new AntiSpamModule());
        MODULES.add(new ArmorDurabilityModule());
        MODULES.add(new ArrayListModule());
        MODULES.add(new AutoItemRenameModule());
        MODULES.add(new AutoNametagModule());
        MODULES.add(new BetterChatModule());
        MODULES.add(new BetterTabModule());
        MODULES.add(new BiomeModule());
        MODULES.add(new BookBotModule());
        MODULES.add(new CPSCounterModule());
        MODULES.add(new ChatSpammerModule());
        MODULES.add(new ClockModule());
        MODULES.add(new CoordinatesModule());
        MODULES.add(new DirectionCompassModule());
        MODULES.add(new DiscordRPCModule());
        MODULES.add(new FPSGraphModule());
        MODULES.add(new GPUUsageModule());
        MODULES.add(new InventoryHUDModule());
        MODULES.add(new LifetimeStatisticsModule());
        MODULES.add(new MusicDisplayModule());
        MODULES.add(new NotesModule());
        MODULES.add(new NotifierModule());
        MODULES.add(new PacketCounterModule());
        MODULES.add(new PingGraphModule());
        MODULES.add(new PlaytimeModule());
        MODULES.add(new PotionHUDModule());
        MODULES.add(new RAMUsageModule());
        MODULES.add(new ScreenshotManagerModule());
        MODULES.add(new SelfDestructModule());
        MODULES.add(new SessionStatsModule());
        MODULES.add(new SoundBlockerModule());
        MODULES.add(new TPSGraphModule());
        MODULES.add(new WeatherModule());

        // -- MOVEMENT --
        MODULES.add(new AirJumpModule());
        MODULES.add(new AntiVoidModule());
        MODULES.add(new AutoJumpModule());
        MODULES.add(new AutoSneakModule());
        MODULES.add(new AutoSprintModule());
        MODULES.add(new AutoWalkModule());
        MODULES.add(new BoatFlyModule());
        MODULES.add(new ClickTPModule());
        MODULES.add(new ElytraBoostModule());
        MODULES.add(new EntityControlModule());
        MODULES.add(new FastClimbModule());
        MODULES.add(new FlightModule());
        MODULES.add(new GUIMoveModule());
        MODULES.add(new HighJumpModule());
        MODULES.add(new IceModeModule());
        MODULES.add(new JesusModule());
        MODULES.add(new LongJumpModule());
        MODULES.add(new NoFallModule());
        MODULES.add(new NoPushModule());
        MODULES.add(new NoSlowModule());
        MODULES.add(new ParkourModule());
        MODULES.add(new ReverseStepModule());
        MODULES.add(new SafeWalkModule());
        MODULES.add(new ScaffoldModule());
        MODULES.add(new SpearBoostModule());
        MODULES.add(new SpiderModule());
        MODULES.add(new StepModule());
        MODULES.add(new TimerModule());
        MODULES.add(new TridentBoostModule());
        MODULES.add(new VelocityModule());

        // -- PLAYER --
        MODULES.add(new AntiAFKModule());
        MODULES.add(new AntiHungerModule());
        MODULES.add(new AutoAcceptTPAModule());
        MODULES.add(new AutoBreedModule());
        MODULES.add(new AutoBrewModule());
        MODULES.add(new AutoClickerModule());
        MODULES.add(new AutoDenyTPAModule());
        MODULES.add(new AutoEatModule());
        MODULES.add(new AutoFarmerModule());
        MODULES.add(new AutoFishModule());
        MODULES.add(new AutoGAppleModule());
        MODULES.add(new AutoGGModule());
        MODULES.add(new AutoMaceModule());
        MODULES.add(new AutoMendModule());
        MODULES.add(new AutoMountModule());
        MODULES.add(new AutoReconnectModule());
        MODULES.add(new AutoReplenishModule());
        MODULES.add(new AutoReplyModule());
        MODULES.add(new AutoRespawnModule());
        MODULES.add(new AutoShearModule());
        MODULES.add(new AutoSignModule());
        MODULES.add(new AutoToolModule());
        MODULES.add(new AutoTradeModule());
        MODULES.add(new ChestSwapperModule());
        MODULES.add(new CustomBreakDelayModule());
        MODULES.add(new EChestFarmerModule());
        MODULES.add(new FastUseModule());
        MODULES.add(new GhostInteractModule());
        MODULES.add(new InventoryManagerModule());
        MODULES.add(new NameProtectModule());
        MODULES.add(new NoGhostBlocksModule());
        MODULES.add(new NoRotateModule());
        MODULES.add(new PacketMineModule());
        MODULES.add(new ReachModule());
        MODULES.add(new SpeedMineModule());
        MODULES.add(new StreamerModeModule());

        // -- RENDER
        MODULES.add(new BetterBeaconsModule());
        MODULES.add(new BetterTooltipsModule());
        MODULES.add(new BlockOutlineModule());
        MODULES.add(new BreadcrumbsModule());
        MODULES.add(new CameraExtrasModule());
        MODULES.add(new ChamsModule());
        MODULES.add(new CustomCrosshairModule());
        MODULES.add(new CustomHitSoundsModule());
        MODULES.add(new ESPModule());
        MODULES.add(new EntityOwnerModule());
        MODULES.add(new FreeLookModule());
        MODULES.add(new FreecamModule());
        MODULES.add(new FullbrightModule());
        MODULES.add(new HandModelModule());
        MODULES.add(new HoleESPModule());
        MODULES.add(new InventoryTweaksModule());
        MODULES.add(new ItemPhysicsModule());
        MODULES.add(new LightOverlayModule());
        MODULES.add(new LogoutMarkersModule());
        MODULES.add(new MenuBlurModule());
        MODULES.add(new MusicPacketInspectorModule());
        MODULES.add(new NametagExtrasModule());
        MODULES.add(new RenderExtrasModule());
        MODULES.add(new ScreenshotMarkersModule());
        MODULES.add(new SoundESPModule());
        MODULES.add(new SoundLoggerModule());
        MODULES.add(new StorageESPModule());
        MODULES.add(new TargetHudModule());
        MODULES.add(new TimeChangerModule());
        MODULES.add(new TracersModule());
        MODULES.add(new TrajectoriesModule());
        MODULES.add(new TunnelESPModule());
        MODULES.add(new VoidESPModule());
        MODULES.add(new VolumeProfilesModule());
        MODULES.add(new WallVisionModule());
        MODULES.add(new WeatherChangerModule());
        MODULES.add(new XrayModule());
        MODULES.add(new ZoomModule());

        // -- WORLD --
        MODULES.add(new BeaconRangeModule());
        MODULES.add(new BiomeBordersModule());
        MODULES.add(new BlockCounterModule());
        MODULES.add(new BlockInfoModule());
        MODULES.add(new ChunkAgeVisualizerModule());
        MODULES.add(new CircleSpherePreviewModule());
        MODULES.add(new ConduitRangeModule());
        MODULES.add(new CropGrowthOverlayModule());
        MODULES.add(new DebugOverlayModule());
        MODULES.add(new EventLoggerModule());
        MODULES.add(new EventProfilerModule());
        MODULES.add(new FogControlModule());
        MODULES.add(new LiquidFillerModule());
        MODULES.add(new ModuleProfilerModule());
        MODULES.add(new OreStatisticsModule());
        MODULES.add(new PacketCancellerModule());
        MODULES.add(new PacketLoggerModule());
        MODULES.add(new RegionViewertModule());
        MODULES.add(new SeedToolsModule());
        MODULES.add(new ServerSpoofModule());
        MODULES.add(new ShapeBuilderModule());
        MODULES.add(new SkyboxEditorModule());
        MODULES.add(new SlimeChunkViewerModule());
        MODULES.add(new SpawnChunkOverlayModule());
        MODULES.add(new SpawnProoferModule());
        MODULES.add(new TerrainSlopeVisualizerModule());
        MODULES.add(new TickProfilerModule());

        ModuleCacheUtils.loadAll();
    }

    public static List<ModuleImplementation> getModules() {
        return MODULES;
    }

    public static void setBind(ModuleImplementation module, int keyBinding) {
        module.keyBindingCode = keyBinding;
    }

    public static int numModules() {
        return getModules().size();
    }

    public static <T extends ModuleImplementation> T getModule(Class<T> moduleClass) {
        for (var module : getModules()) {
            if (module.getClass() == moduleClass) {
                return moduleClass.cast(module);
            }
        }
        return null;
    }

    public static boolean isEnabled(Class<? extends ModuleImplementation> moduleClass) {
        var module = getModule(moduleClass);
        return module != null && module.getEnabledStatus();
    }

    public static void toggle(Class<? extends ModuleImplementation> moduleClass) {
        var module = getModule(moduleClass);
        if (module != null) {
            module.toggle();
        }
    }

    public static CategoryImplementation.Categories getCategory(Class<?> clazz) {
        return clazz.getAnnotation(ModuleInfoAnnotation.class).category();
    }
}