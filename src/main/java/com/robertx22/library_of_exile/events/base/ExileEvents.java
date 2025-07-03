package com.robertx22.library_of_exile.events.base;

import com.google.gson.GsonBuilder;
import com.robertx22.library_of_exile.components.LibMapData;
import com.robertx22.library_of_exile.database.affix.base.GrabMobAffixesEvent;
import com.robertx22.library_of_exile.database.init.PredeterminedRandomEvent;
import com.robertx22.library_of_exile.database.map_data_block.MapDataBlock;
import com.robertx22.library_of_exile.dimension.structure.MapStructure;
import com.robertx22.library_of_exile.gson_wrappers.GsonAdapter;
import com.robertx22.library_of_exile.registry.ExileRegistryEvent;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.ArrayList;
import java.util.List;

public class ExileEvents {

    public static ExileEventCaller<OnEntityTick> LIVING_ENTITY_TICK = new ExileEventCaller<>();
    public static ExileEventCaller<GrabMobAffixesEvent> GRAB_MOB_AFFIXES = new ExileEventCaller<>();
    public static ExileEventCaller<ExileRegistryEvent> EXILE_REGISTRY_GATHER = new ExileEventCaller<>();
    public static ExileEventCaller<PredeterminedRandomEvent> PREDETERMINED_RANDOM = new ExileEventCaller<>();
    public static ExileEventCaller<OnMobExpDrop> MOB_EXP_DROP = new ExileEventCaller<>();
    public static ExileEventCaller<OnMobDeath> MOB_DEATH = new ExileEventCaller<>();
    public static ExileEventCaller<OnPlayerDeath> PLAYER_DEATH = new ExileEventCaller<>();
    public static ExileEventCaller<OnMobKilledByPlayer> MOB_KILLED_BY_PLAYER = new ExileEventCaller<>();
    public static ExileEventCaller<OnSetupLootChance> SETUP_LOOT_CHANCE = new ExileEventCaller<>();
    public static ExileEventCaller<PlayerMineOreEvent> PLAYER_MINE_ORE = new ExileEventCaller<>();
    public static ExileEventCaller<PlayerMineFarmableBlockEvent> PLAYER_MINE_FARMABLE = new ExileEventCaller<>();

    public static ExileEventCaller<OnDamageEntity> DAMAGE_BEFORE_CALC = new ExileEventCaller<>();
    public static ExileEventCaller<OnDamageEntity> DAMAGE_AFTER_CALC = new ExileEventCaller<>();
    public static ExileEventCaller<OnDamageEntity> DAMAGE_BEFORE_APPLIED = new ExileEventCaller<>();
    public static ExileEventCaller<OnCheckIsDevToolsRunning> CHECK_IF_DEV_TOOLS_SHOULD_RUN = new ExileEventCaller<>();
    public static ExileEventCaller<AfterDatabaseLoaded> AFTER_DATABASE_LOADED = new ExileEventCaller<>();
    public static ExileEventCaller<OnPlayerLogin> ON_PLAYER_LOGIN = new ExileEventCaller<>();
    public static ExileEventCaller<OnChestLooted> ON_CHEST_LOOTED = new ExileEventCaller<>();
    public static ExileEventCaller<IsEntityKilledValid> IS_KILLED_ENTITY_VALID = new ExileEventCaller<>();
    public static ExileEventCaller<OnRegisterToDatabase> ON_REGISTER_TO_DATABASE = new ExileEventCaller<>();
    public static ExileEventCaller<DatapackGsonAdapterEvent> DATAPACK_GSON_ADAPTER_REGISTRY = new ExileEventCaller<>();
    public static ExileEventCaller<OnProcessMapDataBlock> PROCESS_DATA_BLOCK = new ExileEventCaller<>();
    public static ExileEventCaller<OnProcessChunkData> PROCESS_CHUNK_DATA = new ExileEventCaller<>();
    public static ExileEventCaller<GrabLibMapData> GRAB_LIB_MAP_DATA = new ExileEventCaller<>();
    public static ExileEventCaller<DungeonDataBlockPlaced> DUNGEON_DATA_BLOCK_PLACED = new ExileEventCaller<>();

    // todo maybe i can add adapters to this and save stuff like registry strings into wrapper classes??

    public static class OnProcessChunkData extends ExileEvent {
        public Player p;
        public MapStructure struc;
        public ChunkPos cp;

        public OnProcessChunkData(Player p, MapStructure struc, ChunkPos cp) {
            this.p = p;
            this.struc = struc;
            this.cp = cp;
        }
    }

    public static class DatapackGsonAdapterEvent extends ExileEvent {
        public GsonBuilder b;

        public DatapackGsonAdapterEvent(GsonBuilder b) {
            this.b = b;
        }

        public void registerAdapter(GsonAdapter ada) {
            b.registerTypeAdapter(ada.getClass(), ada);
        }

    }

    public static class DungeonDataBlockPlaced extends ExileEvent {
        public LevelAccessor levelAccessor;
        public BlockPos pos;
        public StructureTemplate.StructureBlockInfo blockInfo;
        public ResourceLocation structureId;

        public DungeonDataBlockPlaced(LevelAccessor levelAccessor, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfo, ResourceLocation structureId) {
            this.levelAccessor = levelAccessor;
            this.pos = pos;
            this.blockInfo = blockInfo;
            this.structureId = structureId;
        }
    }

    public static class GrabLibMapData extends ExileEvent {
        public Level level;
        public BlockPos pos;

        public LibMapData data;

        public GrabLibMapData(Level level, BlockPos pos) {
            this.level = level;
            this.pos = pos;
        }
    }

    public static class OnEntityTick extends ExileEvent {
        public LivingEntity entity;

        public OnEntityTick(LivingEntity entity) {
            this.entity = entity;
        }
    }

    public static class IsEntityKilledValid extends OnMobDeath {
        public boolean isValid = true;

        public IsEntityKilledValid(LivingEntity mob, LivingEntity killer) {
            super(mob, killer);
        }
    }

    public static class OnChestLooted extends ExileEvent {
        public Player player;
        public LootParams ctx;
        public Container inventory;
        public BlockPos pos;

        public OnChestLooted(Player player, LootParams ctx, Container inventory, BlockPos pos) {
            this.player = player;
            this.ctx = ctx;
            this.inventory = inventory;
            this.pos = pos;
        }
    }

    public static class OnCheckIsDevToolsRunning extends ExileEvent {
        public Boolean run = false;

        public OnCheckIsDevToolsRunning() {

        }
    }

    public static class OnProcessMapDataBlock extends ExileEvent {
        public MapDataBlock dataBlock;
        public String key;
        public BlockPos pos;
        public Level world;
        public CompoundTag nbt;


        public OnProcessMapDataBlock(MapDataBlock dataBlock, String key, BlockPos pos, Level world, CompoundTag nbt) {
            this.dataBlock = dataBlock;
            this.key = key;
            this.pos = pos;
            this.world = world;
            this.nbt = nbt;
        }
    }

    public static class PlayerMineFarmableBlockEvent extends PlayerMineOreEvent {
        public List<ItemStack> droppedItems;

        public PlayerMineFarmableBlockEvent(List<ItemStack> droppedItems, BlockState state, Player player, BlockPos pos) {
            super(state, player, pos);
            this.droppedItems = droppedItems;
        }
    }

    // it's called when player mines any block that doesn't drop itself.. Not just ores
    public static class PlayerMineOreEvent extends ExileEvent {

        public BlockState state;
        public Player player;
        public BlockPos pos;
        public List<ItemStack> itemsToAddToDrop = new ArrayList<>();

        public PlayerMineOreEvent(BlockState state, Player player, BlockPos pos) {
            this.state = state;
            this.player = player;
            this.pos = pos;
        }
    }

    public static class OnPlayerLogin extends ExileEvent {

        public ServerPlayer player;

        public OnPlayerLogin(ServerPlayer player) {
            this.player = player;
        }
    }

    public static class OnRegisterToDatabase extends ExileEvent {

        public Object item;
        public ExileRegistryType type;


        public OnRegisterToDatabase(Object item, ExileRegistryType type) {
            this.item = item;
            this.type = type;
        }
    }

    public static class AfterDatabaseLoaded extends ExileEvent {

        public AfterDatabaseLoaded() {

        }
    }

    public static class OnMobExpDrop extends ExileEvent {
        public LivingEntity mobKilled;
        public float exp;

        public OnMobExpDrop(LivingEntity mobKilled, float exp) {
            this.mobKilled = mobKilled;
            this.exp = exp;
        }
    }

    public static class OnSetupLootChance extends ExileEvent {
        public LivingEntity mobKilled;
        public Player player;
        public float lootChance;

        public OnSetupLootChance(LivingEntity mobKilled, Player player, float lootChance) {
            this.mobKilled = mobKilled;
            this.player = player;
            this.lootChance = lootChance;
        }
    }

    public static class OnMobDeath extends ExileEvent {
        public LivingEntity mob;
        public LivingEntity killer;

        public OnMobDeath(LivingEntity mob, LivingEntity killer) {
            this.mob = mob;
            this.killer = killer;
        }
    }

    public static class OnPlayerDeath extends ExileEvent {
        public Player player;
        public LivingEntity killer;

        public OnPlayerDeath(Player player) {
            this.player = player;
        }
    }

    public static class OnMobKilledByPlayer extends ExileEvent {
        public LivingEntity mob;
        public Player player;

        public OnMobKilledByPlayer(LivingEntity mob, Player player) {
            this.mob = mob;
            this.player = player;
        }
    }

    public static class OnDamageEntity extends ExileEvent {
        public DamageSource source;
        public float damage;
        public LivingEntity mob;

        public OnDamageEntity(DamageSource source, float damage, LivingEntity mob) {
            this.source = source;
            this.damage = damage;
            this.mob = mob;
        }
    }

}
