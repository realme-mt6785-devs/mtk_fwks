package android.app.search;

import android.annotation.SystemApi;
import android.appwidget.AppWidgetProviderInfo;
import android.content.pm.ShortcutInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
@SystemApi
/* loaded from: classes.dex */
public final class SearchTarget implements Parcelable {
    public static final Parcelable.Creator<SearchTarget> CREATOR = new Parcelable.Creator<SearchTarget>() { // from class: android.app.search.SearchTarget.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchTarget createFromParcel(Parcel parcel) {
            return new SearchTarget(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchTarget[] newArray(int size) {
            return new SearchTarget[size];
        }
    };
    public static final String LAYOUT_TYPE_ICON = "icon";
    public static final String LAYOUT_TYPE_ICON_ROW = "icon_row";
    public static final String LAYOUT_TYPE_SHORT_ICON_ROW = "short_icon_row";
    public static final int RESULT_TYPE_APPLICATION = 1;
    public static final int RESULT_TYPE_SHORTCUT = 2;
    public static final int RESULT_TYPE_SLICE = 4;
    public static final int RESULT_TYPE_WIDGETS = 8;
    private final AppWidgetProviderInfo mAppWidgetProviderInfo;
    private final Bundle mExtras;
    private final boolean mHidden;
    private final String mId;
    private final String mLayoutType;
    private final String mPackageName;
    private String mParentId;
    private final int mResultType;
    private final float mScore;
    private final SearchAction mSearchAction;
    private final ShortcutInfo mShortcutInfo;
    private final Uri mSliceUri;
    private final UserHandle mUserHandle;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SearchLayoutType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SearchResultType {
    }

    private SearchTarget(Parcel parcel) {
        this.mResultType = parcel.readInt();
        this.mLayoutType = parcel.readString();
        this.mId = parcel.readString();
        this.mParentId = parcel.readString();
        this.mScore = parcel.readFloat();
        this.mHidden = parcel.readBoolean();
        this.mPackageName = parcel.readString();
        this.mUserHandle = UserHandle.of(parcel.readInt());
        this.mSearchAction = (SearchAction) parcel.readTypedObject(SearchAction.CREATOR);
        this.mShortcutInfo = (ShortcutInfo) parcel.readTypedObject(ShortcutInfo.CREATOR);
        this.mAppWidgetProviderInfo = (AppWidgetProviderInfo) parcel.readTypedObject(AppWidgetProviderInfo.CREATOR);
        this.mSliceUri = (Uri) parcel.readTypedObject(Uri.CREATOR);
        this.mExtras = parcel.readBundle(getClass().getClassLoader());
    }

    private SearchTarget(int resultType, String layoutType, String id, String parentId, float score, boolean hidden, String packageName, UserHandle userHandle, SearchAction action, ShortcutInfo shortcutInfo, Uri sliceUri, AppWidgetProviderInfo appWidgetProviderInfo, Bundle extras) {
        this.mResultType = resultType;
        Objects.requireNonNull(layoutType);
        this.mLayoutType = layoutType;
        Objects.requireNonNull(id);
        this.mId = id;
        this.mParentId = parentId;
        this.mScore = score;
        this.mHidden = hidden;
        Objects.requireNonNull(packageName);
        this.mPackageName = packageName;
        Objects.requireNonNull(userHandle);
        this.mUserHandle = userHandle;
        this.mSearchAction = action;
        this.mShortcutInfo = shortcutInfo;
        this.mAppWidgetProviderInfo = appWidgetProviderInfo;
        this.mSliceUri = sliceUri;
        this.mExtras = extras;
        int published = 0;
        published = action != null ? 0 + 1 : published;
        published = shortcutInfo != null ? published + 1 : published;
        published = appWidgetProviderInfo != null ? published + 1 : published;
        if ((sliceUri != null ? published + 1 : published) > 1) {
            throw new IllegalStateException("Only one of SearchAction, ShortcutInfo, AppWidgetProviderInfo, SliceUri can be assigned in a SearchTarget.");
        }
    }

    public int getResultType() {
        return this.mResultType;
    }

    public String getLayoutType() {
        return this.mLayoutType;
    }

    public String getId() {
        return this.mId;
    }

    public String getParentId() {
        return this.mParentId;
    }

    public float getScore() {
        return this.mScore;
    }

    @Deprecated
    public boolean shouldHide() {
        return this.mHidden;
    }

    public boolean isHidden() {
        return this.mHidden;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public UserHandle getUserHandle() {
        return this.mUserHandle;
    }

    public ShortcutInfo getShortcutInfo() {
        return this.mShortcutInfo;
    }

    public AppWidgetProviderInfo getAppWidgetProviderInfo() {
        return this.mAppWidgetProviderInfo;
    }

    public Uri getSliceUri() {
        return this.mSliceUri;
    }

    public SearchAction getSearchAction() {
        return this.mSearchAction;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mResultType);
        parcel.writeString(this.mLayoutType);
        parcel.writeString(this.mId);
        parcel.writeString(this.mParentId);
        parcel.writeFloat(this.mScore);
        parcel.writeBoolean(this.mHidden);
        parcel.writeString(this.mPackageName);
        parcel.writeInt(this.mUserHandle.getIdentifier());
        parcel.writeTypedObject(this.mSearchAction, flags);
        parcel.writeTypedObject(this.mShortcutInfo, flags);
        parcel.writeTypedObject(this.mAppWidgetProviderInfo, flags);
        parcel.writeTypedObject(this.mSliceUri, flags);
        parcel.writeBundle(this.mExtras);
    }

    @SystemApi
    /* loaded from: classes.dex */
    public static final class Builder {
        private AppWidgetProviderInfo mAppWidgetProviderInfo;
        private Bundle mExtras;
        private String mId;
        private String mLayoutType;
        private String mPackageName;
        private String mParentId;
        private int mResultType;
        private SearchAction mSearchAction;
        private ShortcutInfo mShortcutInfo;
        private Uri mSliceUri;
        private UserHandle mUserHandle;
        private float mScore = 1.0f;
        private boolean mHidden = false;

        public Builder(int resultType, String layoutType, String id) {
            this.mId = id;
            Objects.requireNonNull(layoutType);
            this.mLayoutType = layoutType;
            this.mResultType = resultType;
        }

        public Builder setParentId(String parentId) {
            Objects.requireNonNull(parentId);
            this.mParentId = parentId;
            return this;
        }

        public Builder setPackageName(String packageName) {
            Objects.requireNonNull(packageName);
            this.mPackageName = packageName;
            return this;
        }

        public Builder setUserHandle(UserHandle userHandle) {
            Objects.requireNonNull(userHandle);
            this.mUserHandle = userHandle;
            return this;
        }

        public Builder setShortcutInfo(ShortcutInfo shortcutInfo) {
            Objects.requireNonNull(shortcutInfo);
            this.mShortcutInfo = shortcutInfo;
            String str = this.mPackageName;
            if (str == null || str.equals(shortcutInfo.getPackage())) {
                this.mPackageName = shortcutInfo.getPackage();
                return this;
            }
            throw new IllegalStateException("SearchTarget packageName is different from shortcut's packageName");
        }

        public Builder setAppWidgetProviderInfo(AppWidgetProviderInfo appWidgetProviderInfo) {
            Objects.requireNonNull(appWidgetProviderInfo);
            this.mAppWidgetProviderInfo = appWidgetProviderInfo;
            String str = this.mPackageName;
            if (str == null || str.equals(appWidgetProviderInfo.provider.getPackageName())) {
                return this;
            }
            throw new IllegalStateException("SearchTarget packageName is different from appWidgetProviderInfo's packageName");
        }

        public Builder setSliceUri(Uri sliceUri) {
            this.mSliceUri = sliceUri;
            return this;
        }

        public Builder setSearchAction(SearchAction searchAction) {
            this.mSearchAction = searchAction;
            return this;
        }

        public Builder setExtras(Bundle extras) {
            Objects.requireNonNull(extras);
            this.mExtras = extras;
            return this;
        }

        public Builder setScore(float score) {
            this.mScore = score;
            return this;
        }

        public Builder setHidden(boolean hidden) {
            this.mHidden = hidden;
            return this;
        }

        @Deprecated
        public Builder setShouldHide(boolean shouldHide) {
            this.mHidden = shouldHide;
            return this;
        }

        public SearchTarget build() {
            return new SearchTarget(this.mResultType, this.mLayoutType, this.mId, this.mParentId, this.mScore, this.mHidden, this.mPackageName, this.mUserHandle, this.mSearchAction, this.mShortcutInfo, this.mSliceUri, this.mAppWidgetProviderInfo, this.mExtras);
        }
    }
}
