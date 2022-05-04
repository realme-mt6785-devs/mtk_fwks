package android.view;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.SurfaceControl;
import android.view.WindowManager;
import android.view.accessibility.IAccessibilityEmbeddedConnection;
import java.util.Objects;
/* loaded from: classes3.dex */
public class SurfaceControlViewHost {
    private IAccessibilityEmbeddedConnection mAccessibilityEmbeddedConnection;
    private SurfaceControl mSurfaceControl;
    private final ViewRootImpl mViewRoot;
    private WindowlessWindowManager mWm;

    /* loaded from: classes3.dex */
    public static final class SurfacePackage implements Parcelable {
        public static final Parcelable.Creator<SurfacePackage> CREATOR = new Parcelable.Creator<SurfacePackage>() { // from class: android.view.SurfaceControlViewHost.SurfacePackage.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SurfacePackage createFromParcel(Parcel in) {
                return new SurfacePackage(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SurfacePackage[] newArray(int size) {
                return new SurfacePackage[size];
            }
        };
        private final IAccessibilityEmbeddedConnection mAccessibilityEmbeddedConnection;
        private final IBinder mInputToken;
        private SurfaceControl mSurfaceControl;

        SurfacePackage(SurfaceControl sc, IAccessibilityEmbeddedConnection connection, IBinder inputToken) {
            this.mSurfaceControl = sc;
            this.mAccessibilityEmbeddedConnection = connection;
            this.mInputToken = inputToken;
        }

        public SurfacePackage(SurfacePackage other) {
            SurfaceControl otherSurfaceControl = other.mSurfaceControl;
            if (otherSurfaceControl != null && otherSurfaceControl.isValid()) {
                SurfaceControl surfaceControl = new SurfaceControl();
                this.mSurfaceControl = surfaceControl;
                surfaceControl.copyFrom(otherSurfaceControl, "SurfacePackage");
            }
            this.mAccessibilityEmbeddedConnection = other.mAccessibilityEmbeddedConnection;
            this.mInputToken = other.mInputToken;
        }

        private SurfacePackage(Parcel in) {
            SurfaceControl surfaceControl = new SurfaceControl();
            this.mSurfaceControl = surfaceControl;
            surfaceControl.readFromParcel(in);
            this.mAccessibilityEmbeddedConnection = IAccessibilityEmbeddedConnection.Stub.asInterface(in.readStrongBinder());
            this.mInputToken = in.readStrongBinder();
        }

        public SurfaceControl getSurfaceControl() {
            return this.mSurfaceControl;
        }

        public IAccessibilityEmbeddedConnection getAccessibilityEmbeddedConnection() {
            return this.mAccessibilityEmbeddedConnection;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            this.mSurfaceControl.writeToParcel(out, flags);
            out.writeStrongBinder(this.mAccessibilityEmbeddedConnection.asBinder());
            out.writeStrongBinder(this.mInputToken);
        }

        public void release() {
            SurfaceControl surfaceControl = this.mSurfaceControl;
            if (surfaceControl != null) {
                surfaceControl.release();
            }
            this.mSurfaceControl = null;
        }

        public IBinder getInputToken() {
            return this.mInputToken;
        }
    }

    public SurfaceControlViewHost(Context c, Display d, WindowlessWindowManager wwm) {
        this(c, d, wwm, false);
    }

    public SurfaceControlViewHost(Context c, Display d, WindowlessWindowManager wwm, boolean useSfChoreographer) {
        this.mWm = wwm;
        ViewRootImpl viewRootImpl = new ViewRootImpl(c, d, this.mWm, useSfChoreographer);
        this.mViewRoot = viewRootImpl;
        this.mAccessibilityEmbeddedConnection = viewRootImpl.getAccessibilityEmbeddedConnection();
    }

    public SurfaceControlViewHost(Context context, Display display, IBinder hostToken) {
        this.mSurfaceControl = new SurfaceControl.Builder().setContainerLayer().setName("SurfaceControlViewHost").setCallsite("SurfaceControlViewHost").build();
        this.mWm = new WindowlessWindowManager(context.getResources().getConfiguration(), this.mSurfaceControl, hostToken);
        ViewRootImpl viewRootImpl = new ViewRootImpl(context, display, this.mWm);
        this.mViewRoot = viewRootImpl;
        this.mAccessibilityEmbeddedConnection = viewRootImpl.getAccessibilityEmbeddedConnection();
    }

    protected void finalize() throws Throwable {
        this.mViewRoot.die(false);
    }

    public SurfacePackage getSurfacePackage() {
        if (this.mSurfaceControl == null || this.mAccessibilityEmbeddedConnection == null) {
            return null;
        }
        return new SurfacePackage(this.mSurfaceControl, this.mAccessibilityEmbeddedConnection, this.mViewRoot.getInputToken());
    }

    public void setView(View view, int width, int height) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(width, height, 2, 0, -2);
        setView(view, lp);
    }

    public void setView(View view, WindowManager.LayoutParams attrs) {
        Objects.requireNonNull(view);
        attrs.flags |= 16777216;
        view.setLayoutParams(attrs);
        this.mViewRoot.setView(view, attrs, null);
    }

    public View getView() {
        return this.mViewRoot.getView();
    }

    public IWindow getWindowToken() {
        return this.mViewRoot.mWindow;
    }

    public WindowlessWindowManager getWindowlessWM() {
        return this.mWm;
    }

    public void relayout(WindowManager.LayoutParams attrs) {
        this.mViewRoot.setLayoutParams(attrs, false);
        this.mViewRoot.setReportNextDraw();
        this.mWm.setCompletionCallback(this.mViewRoot.mWindow.asBinder(), SurfaceControlViewHost$$ExternalSyntheticLambda0.INSTANCE);
    }

    public void relayout(int width, int height) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(width, height, 2, 0, -2);
        relayout(lp);
    }

    public void release() {
        this.mViewRoot.die(true);
    }
}
