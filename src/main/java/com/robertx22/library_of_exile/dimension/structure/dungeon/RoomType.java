package com.robertx22.library_of_exile.dimension.structure.dungeon;

import com.robertx22.library_of_exile.main.ExileLog;
import com.robertx22.library_of_exile.registry.IWeighted;
import com.robertx22.library_of_exile.utils.RandomUtils;
import net.minecraft.world.level.block.Rotation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum RoomType implements IWeighted {


    FOUR_WAY("four_way") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR), Rotation.NONE));
            // we double the four way rooms with this
            // all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR), Rotation.NONE));
            //  all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR), Rotation.NONE));
            return all;
        }

    },
    STRAIGHT_HALLWAY("straight_hallway") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

    },
    CURVED_HALLWAY("curved_hallway") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

    },
    TRIPLE_HALLWAY("triple_hallway") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

    },
    END("end") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

    },
    ENTRANCE("entrance") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

    };

    public RoomSides sides;
    public String id;

    RoomType(String id) {
        this.id = id;
    }


    public abstract List<RoomRotation> getRotations();


    public DungeonRoom getRandomRoom(IDungeon dun, DungeonBuilder builder) {

        List<DungeonRoom> possible = dun.getDungeonData().getRoomsOfType(this);


        if (possible.isEmpty()) {
            ExileLog.get().warn("No possible rooms?");
            return BuiltRoom.getBarrier().room;
        }

        return RandomUtils.weightedRandom(possible, builder.rand.nextDouble());


    }

    // if filtering returns nothing, dont filter
    public static List<DungeonRoom> tryFilter(List<DungeonRoom> rooms, Predicate<DungeonRoom> pred) {
        List<DungeonRoom> filtered = rooms.stream()
                .filter(pred)
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            return rooms;
        } else {
            return filtered;
        }

    }

    //todo i think if i let this one check all side rooms including unbuilts, it will fix the weird build bugs.
    public List<RoomRotation> getPossibleFor(UnbuiltRoom room) {
        return getRotations().stream().filter(x -> x.sides.matches(room.sides)).collect(Collectors.toList());
    }

    @Override
    public int Weight() {
        return 1000;
    }
}

// XOX
// OOO
// XOX