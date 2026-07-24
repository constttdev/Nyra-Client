package de.constt.nyra.client.payloads;

//? if <1.20.5 {
/*import net.minecraft.resources.Identifier;

public class JoinPayload {

    public static final Identifier ID = new Identifier("nyra", "join");
}
*///?} else {
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
    public static final Identifier ID = Identifier.fromNamespaceAndPath("nyra", "join");
    //? } elif >=1.21.7 {
    /*public static final Identifier ID = Identifier.fromNamespaceAndPath("nyra", "join");
     *///?} else {
    /*public static final Identifier ID = Identifier.tryBuild("nyra", "join");
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