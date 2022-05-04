package com.android.internal.app;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.app.ChooserActivity;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public interface IResolverActivityExt {
    public static final IResolverActivityExt DEFAULT = new IResolverActivityExt() { // from class: com.android.internal.app.IResolverActivityExt.1
    };

    default void setMultiProfilePagerAdapter(AbstractMultiProfilePagerAdapter abstractMultiProfilePagerAdapter) {
    }

    default ResolverActivity getResolverActivity() {
        return null;
    }

    default void hookonCreate(Bundle savedInstanceState) {
    }

    default void hookonResume() {
    }

    default void hookonPause() {
    }

    default void hookFinish() {
    }

    default void hookonDestroy() {
    }

    default boolean hookonSaveInstanceState(Bundle outState, String tabKey) {
        return false;
    }

    default boolean hookonRestoreInstanceState(Bundle savedInstanceState, String tabKey) {
        return false;
    }

    default void hookonMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
    }

    default void hookonConfigurationChanged(Configuration newConfig) {
    }

    default void cacheCustomInfo(Intent intent) {
    }

    default void setOriginTheme(int resId) {
    }

    default boolean isOriginUi() {
        return true;
    }

    default boolean addExtraOneAppFinish() {
        return true;
    }

    default boolean hooksetLastChosen(ResolverListController controller, Intent intent, IntentFilter filter, int bestMatch) throws RemoteException {
        return false;
    }

    default void hookconfigureContentView(boolean safeForwardingMode, boolean supportsAlwaysUseOption, int layoutId) {
    }

    default void initView(boolean safeForwardingMode, boolean supportsAlwaysUseOption) {
    }

    default void updateView(boolean safeForwardingMode, boolean supportsAlwaysUseOption) {
    }

    default void initActionSend() {
    }

    default void statisticsData(ResolveInfo ri, int which) {
    }

    default boolean hookonListRebuilt() {
        return false;
    }

    default boolean initPreferenceAndPinList() {
        return true;
    }

    default void performAnimation() {
    }

    default void setChooserPreFileSingleIconView(int iconResId, ImageView fileIconView, boolean needTry, TextView fileNameView, boolean singleFile, String fileName, Uri uri) {
        fileIconView.setImageResource(iconResId);
    }

    default ViewGroup getDisplayFileContentPreview(LayoutInflater layoutInflater, ViewGroup parent) {
        return (ViewGroup) layoutInflater.inflate(R.layout.chooser_grid_preview_file, parent, false);
    }

    default ViewGroup hookdisplayTextContentPreview(Intent targetIntent, LayoutInflater layoutInflater, ViewGroup parent, View.OnClickListener listener) {
        return (ViewGroup) layoutInflater.inflate(R.layout.chooser_grid_preview_text, parent, false);
    }

    default ViewGroup getDisplayImageContentPreview(LayoutInflater layoutInflater, ViewGroup parent) {
        return (ViewGroup) layoutInflater.inflate(R.layout.chooser_grid_preview_image, parent, false);
    }

    default void restoreProfilePager(int pageNumber) {
    }

    default boolean tryApkResourceName(Uri uri, ImageView imageView, TextView textView) {
        return false;
    }

    default void displayTextAddActionButton(ViewGroup actionRow, View.OnClickListener listener) {
    }

    default void setCustomRoundImage(Paint roundRectPaint, Paint textPaint, Paint overlayPaint) {
    }

    default void setCornerRadius(Context context, ChooserActivity.RoundedRectImageView imageView) {
    }

    default void addNearbyAction(ViewGroup parent, Intent targetIntent) {
    }

    default boolean isOpShareUi() {
        return false;
    }

    default void setActionButtonTextColor(Button button) {
    }

    default void checkOpShare() {
    }

    default void setChooserHeadBackground(View elevatedView) {
    }

    default View createTypeNormalView(View v, int targetWidth) {
        return v;
    }

    default int getChooserActionRowId() {
        return R.id.chooser_action_row;
    }

    default void setTextOptionColor(Context context, TextView textView) {
    }

    default UserHandle getWorkProfileUserHandle() {
        return UserHandle.CURRENT;
    }

    default void getWorkProfileUserHandle(UserHandle userHandle) {
    }

    default void startSelected(int which, boolean always, boolean hasIndexBeenFiltered) {
    }

    default int getLaunchedFromUid() {
        return UserHandle.myUserId();
    }

    default boolean hasCustomFlag(int flag) {
        return false;
    }

    default AbstractMultiProfilePagerAdapter getMultiProfilePagerAdapter() {
        return null;
    }

    default boolean hookonCopyButtonClicked(ArrayList<Uri> streams) {
        return false;
    }

    default ClipData getclipData(Intent targetIntent) {
        return null;
    }

    default boolean hookgetNearbySharingComponent() {
        return false;
    }

    default void hookmaybeHideContentPreview() {
    }

    default void hookprepareIntentForCrossProfileLaunch(Intent intent) {
    }

    default String getChooserPreFileName(Context context, int fileCount, String fileName) {
        return context.getResources().getQuantityString(R.plurals.file_count, fileCount - 1, fileName, Integer.valueOf(fileCount - 1));
    }

    default boolean getFileSharedDisabled() {
        return false;
    }

    default void clearInactiveProfileCache(int page) {
    }
}
