package android.media;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes2.dex */
public abstract class DrmInitData {
    @Deprecated
    public abstract SchemeInitData get(UUID uuid);

    public int getSchemeInitDataCount() {
        return 0;
    }

    public SchemeInitData getSchemeInitDataAt(int index) {
        throw new IndexOutOfBoundsException();
    }

    /* loaded from: classes2.dex */
    public static final class SchemeInitData {
        public static final UUID UUID_NIL = new UUID(0, 0);
        public final byte[] data;
        public final String mimeType;
        public final UUID uuid;

        public SchemeInitData(UUID uuid, String mimeType, byte[] data) {
            Objects.requireNonNull(uuid);
            this.uuid = uuid;
            Objects.requireNonNull(mimeType);
            this.mimeType = mimeType;
            Objects.requireNonNull(data);
            this.data = data;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SchemeInitData)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            SchemeInitData other = (SchemeInitData) obj;
            return this.uuid.equals(other.uuid) && this.mimeType.equals(other.mimeType) && Arrays.equals(this.data, other.data);
        }

        public int hashCode() {
            return this.uuid.hashCode() + ((this.mimeType.hashCode() + (Arrays.hashCode(this.data) * 31)) * 31);
        }
    }
}
