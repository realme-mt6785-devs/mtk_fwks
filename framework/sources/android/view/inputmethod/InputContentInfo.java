package android.view.inputmethod;

import android.content.ClipDescription;
import android.content.ContentProvider;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import com.android.internal.inputmethod.IInputContentUriToken;
import java.security.InvalidParameterException;
/* loaded from: classes3.dex */
public final class InputContentInfo implements Parcelable {
    public static final Parcelable.Creator<InputContentInfo> CREATOR = new Parcelable.Creator<InputContentInfo>() { // from class: android.view.inputmethod.InputContentInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InputContentInfo createFromParcel(Parcel source) {
            return new InputContentInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InputContentInfo[] newArray(int size) {
            return new InputContentInfo[size];
        }
    };
    private final Uri mContentUri;
    private final int mContentUriOwnerUserId;
    private final ClipDescription mDescription;
    private final Uri mLinkUri;
    private IInputContentUriToken mUriToken;

    public InputContentInfo(Uri contentUri, ClipDescription description) {
        this(contentUri, description, null);
    }

    public InputContentInfo(Uri contentUri, ClipDescription description, Uri linkUri) {
        validateInternal(contentUri, description, linkUri, true);
        this.mContentUri = contentUri;
        this.mContentUriOwnerUserId = ContentProvider.getUserIdFromUri(contentUri, UserHandle.myUserId());
        this.mDescription = description;
        this.mLinkUri = linkUri;
    }

    public boolean validate() {
        return validateInternal(this.mContentUri, this.mDescription, this.mLinkUri, false);
    }

    private static boolean validateInternal(Uri contentUri, ClipDescription description, Uri linkUri, boolean throwException) {
        if (contentUri == null) {
            if (!throwException) {
                return false;
            }
            throw new NullPointerException("contentUri");
        } else if (description != null) {
            String contentUriScheme = contentUri.getScheme();
            if (!"content".equals(contentUriScheme)) {
                if (!throwException) {
                    return false;
                }
                throw new InvalidParameterException("contentUri must have content scheme");
            } else if (linkUri == null) {
                return true;
            } else {
                String scheme = linkUri.getScheme();
                if (scheme != null && (scheme.equalsIgnoreCase(IntentFilter.SCHEME_HTTP) || scheme.equalsIgnoreCase(IntentFilter.SCHEME_HTTPS))) {
                    return true;
                }
                if (!throwException) {
                    return false;
                }
                throw new InvalidParameterException("linkUri must have either http or https scheme");
            }
        } else if (!throwException) {
            return false;
        } else {
            throw new NullPointerException("description");
        }
    }

    public Uri getContentUri() {
        if (this.mContentUriOwnerUserId != UserHandle.myUserId()) {
            return ContentProvider.maybeAddUserId(this.mContentUri, this.mContentUriOwnerUserId);
        }
        return this.mContentUri;
    }

    public ClipDescription getDescription() {
        return this.mDescription;
    }

    public Uri getLinkUri() {
        return this.mLinkUri;
    }

    public void setUriToken(IInputContentUriToken token) {
        if (this.mUriToken == null) {
            this.mUriToken = token;
            return;
        }
        throw new IllegalStateException("URI token is already set");
    }

    public void requestPermission() {
        IInputContentUriToken iInputContentUriToken = this.mUriToken;
        if (iInputContentUriToken != null) {
            try {
                iInputContentUriToken.take();
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
        }
    }

    public void releasePermission() {
        IInputContentUriToken iInputContentUriToken = this.mUriToken;
        if (iInputContentUriToken != null) {
            try {
                iInputContentUriToken.release();
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Uri.writeToParcel(dest, this.mContentUri);
        dest.writeInt(this.mContentUriOwnerUserId);
        this.mDescription.writeToParcel(dest, flags);
        Uri.writeToParcel(dest, this.mLinkUri);
        if (this.mUriToken != null) {
            dest.writeInt(1);
            dest.writeStrongBinder(this.mUriToken.asBinder());
            return;
        }
        dest.writeInt(0);
    }

    private InputContentInfo(Parcel source) {
        this.mContentUri = Uri.CREATOR.createFromParcel(source);
        this.mContentUriOwnerUserId = source.readInt();
        this.mDescription = ClipDescription.CREATOR.createFromParcel(source);
        this.mLinkUri = Uri.CREATOR.createFromParcel(source);
        if (source.readInt() == 1) {
            this.mUriToken = IInputContentUriToken.Stub.asInterface(source.readStrongBinder());
        } else {
            this.mUriToken = null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
