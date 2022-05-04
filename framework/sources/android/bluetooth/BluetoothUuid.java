package android.bluetooth;

import android.annotation.SystemApi;
import android.os.ParcelUuid;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
@SystemApi
/* loaded from: classes.dex */
public final class BluetoothUuid {
    @SystemApi
    public static final int UUID_BYTES_128_BIT = 16;
    @SystemApi
    public static final int UUID_BYTES_16_BIT = 2;
    @SystemApi
    public static final int UUID_BYTES_32_BIT = 4;
    @SystemApi
    public static final ParcelUuid A2DP_SINK = ParcelUuid.fromString("0000110B-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid A2DP_SOURCE = ParcelUuid.fromString("0000110A-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid ADV_AUDIO_DIST = ParcelUuid.fromString("0000110D-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid HSP = ParcelUuid.fromString("00001108-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid HSP_AG = ParcelUuid.fromString("00001112-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid HFP = ParcelUuid.fromString("0000111E-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid HFP_AG = ParcelUuid.fromString("0000111F-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid AVRCP_CONTROLLER = ParcelUuid.fromString("0000110E-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid AVRCP_TARGET = ParcelUuid.fromString("0000110C-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid OBEX_OBJECT_PUSH = ParcelUuid.fromString("00001105-0000-1000-8000-00805f9b34fb");
    @SystemApi
    public static final ParcelUuid HID = ParcelUuid.fromString("00001124-0000-1000-8000-00805f9b34fb");
    @SystemApi
    public static final ParcelUuid HOGP = ParcelUuid.fromString("00001812-0000-1000-8000-00805f9b34fb");
    @SystemApi
    public static final ParcelUuid PANU = ParcelUuid.fromString("00001115-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid NAP = ParcelUuid.fromString("00001116-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid BNEP = ParcelUuid.fromString("0000000f-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid PBAP_PCE = ParcelUuid.fromString("0000112e-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid PBAP_PSE = ParcelUuid.fromString("0000112f-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid MAP = ParcelUuid.fromString("00001134-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid MNS = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid MAS = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid SAP = ParcelUuid.fromString("0000112D-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid HEARING_AID = ParcelUuid.fromString("0000FDF0-0000-1000-8000-00805f9b34fb");
    @SystemApi
    public static final ParcelUuid LE_AUDIO = ParcelUuid.fromString("EEEEEEEE-EEEE-EEEE-EEEE-EEEEEEEEEEEE");
    @SystemApi
    public static final ParcelUuid DIP = ParcelUuid.fromString("00001200-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid VOLUME_CONTROL = ParcelUuid.fromString("00001844-0000-1000-8000-00805F9B34FB");
    @SystemApi
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");

    @SystemApi
    public static boolean containsAnyUuid(ParcelUuid[] uuidA, ParcelUuid[] uuidB) {
        if (uuidA == null && uuidB == null) {
            return true;
        }
        if (uuidA == null) {
            if (uuidB.length == 0) {
                return true;
            }
            return false;
        } else if (uuidB != null) {
            HashSet<ParcelUuid> uuidSet = new HashSet<>(Arrays.asList(uuidA));
            for (ParcelUuid uuid : uuidB) {
                if (uuidSet.contains(uuid)) {
                    return true;
                }
            }
            return false;
        } else if (uuidA.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static int getServiceIdentifierFromParcelUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        long value = (uuid.getMostSignificantBits() & (-4294967296L)) >>> 32;
        return (int) value;
    }

    @SystemApi
    public static ParcelUuid parseUuidFrom(byte[] uuidBytes) {
        long shortUuid;
        if (uuidBytes != null) {
            int length = uuidBytes.length;
            if (length != 2 && length != 4 && length != 16) {
                throw new IllegalArgumentException("uuidBytes length invalid - " + length);
            } else if (length == 16) {
                ByteBuffer buf = ByteBuffer.wrap(uuidBytes).order(ByteOrder.LITTLE_ENDIAN);
                long msb = buf.getLong(8);
                long lsb = buf.getLong(0);
                return new ParcelUuid(new UUID(msb, lsb));
            } else {
                if (length == 2) {
                    long shortUuid2 = uuidBytes[0] & 255;
                    shortUuid = shortUuid2 + ((uuidBytes[1] & 255) << 8);
                } else {
                    long shortUuid3 = uuidBytes[0] & 255;
                    shortUuid = ((uuidBytes[3] & 255) << 24) + shortUuid3 + ((uuidBytes[1] & 255) << 8) + ((uuidBytes[2] & 255) << 16);
                }
                ParcelUuid parcelUuid = BASE_UUID;
                long msb2 = parcelUuid.getUuid().getMostSignificantBits() + (shortUuid << 32);
                long lsb2 = parcelUuid.getUuid().getLeastSignificantBits();
                return new ParcelUuid(new UUID(msb2, lsb2));
            }
        } else {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
    }

    public static byte[] uuidToBytes(ParcelUuid uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("uuid cannot be null");
        } else if (is16BitUuid(uuid)) {
            int uuidVal = getServiceIdentifierFromParcelUuid(uuid);
            return new byte[]{(byte) (uuidVal & 255), (byte) ((65280 & uuidVal) >> 8)};
        } else if (is32BitUuid(uuid)) {
            int uuidVal2 = getServiceIdentifierFromParcelUuid(uuid);
            return new byte[]{(byte) (uuidVal2 & 255), (byte) ((65280 & uuidVal2) >> 8), (byte) ((16711680 & uuidVal2) >> 16), (byte) (((-16777216) & uuidVal2) >> 24)};
        } else {
            long msb = uuid.getUuid().getMostSignificantBits();
            long lsb = uuid.getUuid().getLeastSignificantBits();
            byte[] uuidBytes = new byte[16];
            ByteBuffer buf = ByteBuffer.wrap(uuidBytes).order(ByteOrder.LITTLE_ENDIAN);
            buf.putLong(8, msb);
            buf.putLong(0, lsb);
            return uuidBytes;
        }
    }

    public static boolean is16BitUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() == BASE_UUID.getUuid().getLeastSignificantBits() && (uuid.getMostSignificantBits() & (-281470681743361L)) == 4096;
    }

    public static boolean is32BitUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() == BASE_UUID.getUuid().getLeastSignificantBits() && !is16BitUuid(parcelUuid) && (uuid.getMostSignificantBits() & 4294967295L) == 4096;
    }

    private BluetoothUuid() {
    }
}
