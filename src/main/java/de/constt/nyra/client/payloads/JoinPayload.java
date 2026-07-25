package de.constt.nyra.client.payloads;

//? if <1.20.5 {
/*import net.minecraft.resources.Identifier;

public class JoinPayload {

    public static final Identifier ID = new Identifier(VarUtils.getModID(), "join");
}
*///?} else {
import de.constt.nyra.client.utils.VarUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
//? if >=1.21.11 {
import net.minecraft.resources.Identifier;
//? } else {
/*import net.minecraft.resources.Identifier;
 *///?}

public class JoinPayload implements CustomPacketPayload {

    //? if >=1.21.11 {
    public static final Identifier ID = Identifier.fromNamespaceAndPath(VarUtils.getModID(), "join");
    //? } elif >=1.21.7 {
    /*public static final Identifier ID = Identifier.fromNamespaceAndPath(VarUtils.getModID(), "join");
     *///?} else {
    /*public static final Identifier ID = Identifier.tryBuild(VarUtils.getModID(), "join");
     *///?}

    public static final StreamCodec<FriendlyByteBuf, JoinPayload> CODEC =
            StreamCodec.of(
                    (buf, payload) -> {},
                    buf -> new JoinPayload()
            );

    public static final Type<JoinPayload> TYPE =
            new Type<>(ID);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
//?}