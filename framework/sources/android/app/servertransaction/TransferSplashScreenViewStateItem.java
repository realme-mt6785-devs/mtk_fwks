package android.app.servertransaction;

import android.app.ActivityThread;
import android.app.ClientTransactionHandler;
import android.os.Parcel;
import android.os.Parcelable;
import android.window.SplashScreenView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class TransferSplashScreenViewStateItem extends ActivityTransactionItem {
    public static final int ATTACH_TO = 0;
    public static final Parcelable.Creator<TransferSplashScreenViewStateItem> CREATOR = new Parcelable.Creator<TransferSplashScreenViewStateItem>() { // from class: android.app.servertransaction.TransferSplashScreenViewStateItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransferSplashScreenViewStateItem createFromParcel(Parcel in) {
            return new TransferSplashScreenViewStateItem(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransferSplashScreenViewStateItem[] newArray(int size) {
            return new TransferSplashScreenViewStateItem[size];
        }
    };
    public static final int HANDOVER_TO = 1;
    private int mRequest;
    private SplashScreenView.SplashScreenViewParcelable mSplashScreenViewParcelable;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TransferRequest {
    }

    @Override // android.app.servertransaction.ActivityTransactionItem
    public void execute(ClientTransactionHandler client, ActivityThread.ActivityClientRecord r, PendingTransactionActions pendingActions) {
        switch (this.mRequest) {
            case 0:
                client.handleAttachSplashScreenView(r, this.mSplashScreenViewParcelable);
                return;
            case 1:
                client.handOverSplashScreenView(r);
                return;
            default:
                return;
        }
    }

    @Override // android.app.servertransaction.ObjectPoolItem
    public void recycle() {
        ObjectPool.recycle(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mRequest);
        dest.writeTypedObject(this.mSplashScreenViewParcelable, flags);
    }

    private TransferSplashScreenViewStateItem() {
    }

    private TransferSplashScreenViewStateItem(Parcel in) {
        this.mRequest = in.readInt();
        this.mSplashScreenViewParcelable = (SplashScreenView.SplashScreenViewParcelable) in.readTypedObject(SplashScreenView.SplashScreenViewParcelable.CREATOR);
    }

    public static TransferSplashScreenViewStateItem obtain(int state, SplashScreenView.SplashScreenViewParcelable parcelable) {
        TransferSplashScreenViewStateItem instance = (TransferSplashScreenViewStateItem) ObjectPool.obtain(TransferSplashScreenViewStateItem.class);
        if (instance == null) {
            instance = new TransferSplashScreenViewStateItem();
        }
        instance.mRequest = state;
        instance.mSplashScreenViewParcelable = parcelable;
        return instance;
    }
}
