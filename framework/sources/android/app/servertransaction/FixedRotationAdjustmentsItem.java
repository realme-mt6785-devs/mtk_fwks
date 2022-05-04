package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.DisplayAdjustments;
import java.util.Objects;
/* loaded from: classes.dex */
public class FixedRotationAdjustmentsItem extends ClientTransactionItem {
    public static final Parcelable.Creator<FixedRotationAdjustmentsItem> CREATOR = new Parcelable.Creator<FixedRotationAdjustmentsItem>() { // from class: android.app.servertransaction.FixedRotationAdjustmentsItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FixedRotationAdjustmentsItem createFromParcel(Parcel in) {
            return new FixedRotationAdjustmentsItem(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FixedRotationAdjustmentsItem[] newArray(int size) {
            return new FixedRotationAdjustmentsItem[size];
        }
    };
    private DisplayAdjustments.FixedRotationAdjustments mFixedRotationAdjustments;
    private IBinder mToken;

    private FixedRotationAdjustmentsItem() {
    }

    public static FixedRotationAdjustmentsItem obtain(IBinder token, DisplayAdjustments.FixedRotationAdjustments fixedRotationAdjustments) {
        FixedRotationAdjustmentsItem instance = (FixedRotationAdjustmentsItem) ObjectPool.obtain(FixedRotationAdjustmentsItem.class);
        if (instance == null) {
            instance = new FixedRotationAdjustmentsItem();
        }
        instance.mToken = token;
        instance.mFixedRotationAdjustments = fixedRotationAdjustments;
        return instance;
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void execute(ClientTransactionHandler client, IBinder token, PendingTransactionActions pendingActions) {
        client.handleFixedRotationAdjustments(this.mToken, this.mFixedRotationAdjustments);
    }

    @Override // android.app.servertransaction.ObjectPoolItem
    public void recycle() {
        this.mToken = null;
        this.mFixedRotationAdjustments = null;
        ObjectPool.recycle(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStrongBinder(this.mToken);
        dest.writeTypedObject(this.mFixedRotationAdjustments, flags);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FixedRotationAdjustmentsItem other = (FixedRotationAdjustmentsItem) o;
        if (!Objects.equals(this.mToken, other.mToken) || !Objects.equals(this.mFixedRotationAdjustments, other.mFixedRotationAdjustments)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = (17 * 31) + Objects.hashCode(this.mToken);
        return (result * 31) + Objects.hashCode(this.mFixedRotationAdjustments);
    }

    private FixedRotationAdjustmentsItem(Parcel in) {
        this.mToken = in.readStrongBinder();
        this.mFixedRotationAdjustments = (DisplayAdjustments.FixedRotationAdjustments) in.readTypedObject(DisplayAdjustments.FixedRotationAdjustments.CREATOR);
    }
}
