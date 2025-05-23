package com.robertx22.orbs_of_crafting.register.reqs.base;

import com.robertx22.library_of_exile.custom_ser.CustomSerializer;
import com.robertx22.library_of_exile.custom_ser.GsonCustomSer;
import com.robertx22.library_of_exile.database.init.LibDatabase;
import com.robertx22.library_of_exile.localization.ITranslated;
import com.robertx22.library_of_exile.registry.Database;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import com.robertx22.library_of_exile.registry.JsonExileRegistry;
import com.robertx22.library_of_exile.registry.register_info.ExileRegistrationInfo;
import com.robertx22.orbs_of_crafting.misc.StackHolder;
import com.robertx22.orbs_of_crafting.register.CustomSerializers;
import com.robertx22.orbs_of_crafting.register.reqs.HasNoEnchantsReq;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public abstract class ItemRequirement implements JsonExileRegistry<ItemRequirement>, ITranslated, GsonCustomSer<ItemRequirement> {

    public static ItemRequirement SERIALIZER = new HasNoEnchantsReq("");

    public String serializer = "";

    public String id = "";

    public transient String locname = "";

    public int weight = 1000;


    public ItemRequirement(String serializer, String id) {
        this.serializer = serializer;
        this.id = id;
    }


    public abstract MutableComponent getDescWithParams();


    @Override
    public void addToSerializables(ExileRegistrationInfo info) {
        getSerMap().register(this);
        Database.getRegistry(this.getExileRegistryType()).addSerializable(this, info);
    }

    @Override
    public CustomSerializer getSerMap() {
        return CustomSerializers.ITEM_REQ;
    }

    @Override
    public String getSer() {
        return serializer;
    }


    @Override
    public ExileRegistryType getExileRegistryType() {
        return LibDatabase.ITEM_REQ;
    }


    @Override
    public String GUID() {
        return id;
    }


    @Override
    public int Weight() {
        return weight;
    }

    public abstract boolean isValid(Player p, StackHolder obj);


}
