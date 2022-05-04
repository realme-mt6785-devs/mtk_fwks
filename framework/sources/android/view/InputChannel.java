package android.view;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import libcore.util.NativeAllocationRegistry;
/* loaded from: classes3.dex */
public final class InputChannel implements Parcelable {
    private static final boolean DEBUG = false;
    private static final String TAG = "InputChannel";
    private long mPtr;
    private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(InputChannel.class.getClassLoader(), nativeGetFinalizer());
    public static final Parcelable.Creator<InputChannel> CREATOR = new Parcelable.Creator<InputChannel>() { // from class: android.view.InputChannel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InputChannel createFromParcel(Parcel source) {
            InputChannel result = new InputChannel();
            result.readFromParcel(source);
            return result;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InputChannel[] newArray(int size) {
            return new InputChannel[size];
        }
    };

    private native void nativeDispose(long j);

    private native long nativeDup(long j);

    private static native long nativeGetFinalizer();

    private native String nativeGetName(long j);

    private native IBinder nativeGetToken(long j);

    private static native long[] nativeOpenInputChannelPair(String str);

    private native long nativeReadFromParcel(Parcel parcel);

    private native void nativeWriteToParcel(Parcel parcel, long j);

    private void setNativeInputChannel(long nativeChannel) {
        if (nativeChannel == 0) {
            throw new IllegalArgumentException("Attempting to set native input channel to null.");
        } else if (this.mPtr == 0) {
            sRegistry.registerNativeAllocation(this, nativeChannel);
            this.mPtr = nativeChannel;
        } else {
            throw new IllegalArgumentException("Already has native input channel.");
        }
    }

    public static InputChannel[] openInputChannelPair(String name) {
        if (name != null) {
            InputChannel[] channels = new InputChannel[2];
            long[] nativeChannels = nativeOpenInputChannelPair(name);
            for (int i = 0; i < 2; i++) {
                channels[i] = new InputChannel();
                channels[i].setNativeInputChannel(nativeChannels[i]);
            }
            return channels;
        }
        throw new IllegalArgumentException("name must not be null");
    }

    public String getName() {
        String name = nativeGetName(this.mPtr);
        return name != null ? name : "uninitialized";
    }

    public void dispose() {
        nativeDispose(this.mPtr);
    }

    public void release() {
    }

    public void copyTo(InputChannel outParameter) {
        if (outParameter == null) {
            throw new IllegalArgumentException("outParameter must not be null");
        } else if (outParameter.mPtr == 0) {
            outParameter.setNativeInputChannel(nativeDup(this.mPtr));
        } else {
            throw new IllegalArgumentException("Other object already has a native input channel.");
        }
    }

    public InputChannel dup() {
        InputChannel target = new InputChannel();
        target.setNativeInputChannel(nativeDup(this.mPtr));
        return target;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 1;
    }

    public void readFromParcel(Parcel in) {
        if (in != null) {
            long nativeIn = nativeReadFromParcel(in);
            if (nativeIn != 0) {
                setNativeInputChannel(nativeIn);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("in must not be null");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        if (out != null) {
            nativeWriteToParcel(out, this.mPtr);
            if ((flags & 1) != 0) {
                dispose();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("out must not be null");
    }

    public String toString() {
        return getName();
    }

    public IBinder getToken() {
        return nativeGetToken(this.mPtr);
    }
}
