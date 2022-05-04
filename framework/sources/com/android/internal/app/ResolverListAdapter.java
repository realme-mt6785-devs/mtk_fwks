package com.android.internal.app;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.PermissionChecker;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.app.ResolverActivity;
import com.android.internal.app.chooser.DisplayResolveInfo;
import com.android.internal.app.chooser.TargetInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes4.dex */
public class ResolverListAdapter extends BaseAdapter {
    private static final String TAG = "ResolverListAdapter";
    private static ColorMatrixColorFilter sSuspendedMatrixColorFilter;
    private final List<ResolveInfo> mBaseResolveList;
    protected final Context mContext;
    private boolean mFilterLastUsed;
    private final int mIconDpi;
    protected final LayoutInflater mInflater;
    private final Intent[] mInitialIntents;
    private final List<Intent> mIntents;
    private final boolean mIsAudioCaptureDevice;
    private boolean mIsTabLoaded;
    protected ResolveInfo mLastChosen;
    private DisplayResolveInfo mOtherProfile;
    private int mPlaceholderCount;
    private final PackageManager mPm;
    private Runnable mPostListReadyRunnable;
    protected IResolverListAdapterExt mResolverListAdapterExt;
    final ResolverListCommunicator mResolverListCommunicator;
    ResolverListController mResolverListController;
    private List<ResolverActivity.ResolvedComponentInfo> mUnfilteredResolveList;
    private int mLastChosenPosition = -1;
    List<DisplayResolveInfo> mDisplayList = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public interface ResolverListCommunicator {
        Intent getReplacementIntent(ActivityInfo activityInfo, Intent intent);

        Intent getTargetIntent();

        void onHandlePackagesChanged(ResolverListAdapter resolverListAdapter);

        void onPostListReady(ResolverListAdapter resolverListAdapter, boolean z, boolean z2);

        boolean resolveInfoMatch(ResolveInfo resolveInfo, ResolveInfo resolveInfo2);

        void sendVoiceChoicesIfNeeded();

        boolean shouldGetActivityMetadata();

        void updateProfileViewButton();

        boolean useLayoutWithDefault();
    }

    public ResolverListAdapter(Context context, List<Intent> payloadIntents, Intent[] initialIntents, List<ResolveInfo> rList, boolean filterLastUsed, ResolverListController resolverListController, ResolverListCommunicator resolverListCommunicator, boolean isAudioCaptureDevice) {
        this.mResolverListAdapterExt = IResolverListAdapterExt.DEFAULT;
        this.mContext = context;
        this.mIntents = payloadIntents;
        this.mInitialIntents = initialIntents;
        this.mBaseResolveList = rList;
        this.mInflater = LayoutInflater.from(context);
        this.mPm = context.getPackageManager();
        this.mFilterLastUsed = filterLastUsed;
        this.mResolverListController = resolverListController;
        this.mResolverListCommunicator = resolverListCommunicator;
        this.mIsAudioCaptureDevice = isAudioCaptureDevice;
        ActivityManager am = (ActivityManager) context.getSystemService("activity");
        this.mIconDpi = am.getLauncherLargeIconDensity();
        this.mResolverListAdapterExt = ResolverListAdapterExtPlugin.constructor.newInstance(this);
    }

    public void handlePackagesChanged() {
        this.mResolverListCommunicator.onHandlePackagesChanged(this);
    }

    public void setPlaceholderCount(int count) {
        this.mPlaceholderCount = count;
    }

    public int getPlaceholderCount() {
        return this.mPlaceholderCount;
    }

    public DisplayResolveInfo getFilteredItem() {
        int i;
        if (!this.mFilterLastUsed || (i = this.mLastChosenPosition) < 0) {
            return null;
        }
        return this.mDisplayList.get(i);
    }

    public DisplayResolveInfo getOtherProfile() {
        return this.mOtherProfile;
    }

    public int getFilteredPosition() {
        int i;
        if (!this.mFilterLastUsed || (i = this.mLastChosenPosition) < 0) {
            return -1;
        }
        return i;
    }

    public boolean hasFilteredItem() {
        return this.mFilterLastUsed && this.mLastChosen != null;
    }

    public float getScore(DisplayResolveInfo target) {
        return this.mResolverListController.getScore(target);
    }

    public float getScore(ComponentName componentName) {
        return this.mResolverListController.getScore(componentName);
    }

    public List<ComponentName> getTopComponentNames(int topK) {
        return this.mResolverListController.getTopComponentNames(topK);
    }

    public void updateModel(ComponentName componentName) {
        this.mResolverListController.updateModel(componentName);
    }

    public void updateChooserCounts(String packageName, String action) {
        this.mResolverListController.updateChooserCounts(packageName, getUserHandle().getIdentifier(), action);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<ResolverActivity.ResolvedComponentInfo> getUnfilteredResolveList() {
        return this.mUnfilteredResolveList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean rebuildList(boolean doPostProcessing) {
        List<ResolverActivity.ResolvedComponentInfo> currentResolveList;
        this.mOtherProfile = null;
        this.mLastChosen = null;
        this.mLastChosenPosition = -1;
        this.mDisplayList.clear();
        this.mIsTabLoaded = false;
        if (this.mBaseResolveList != null) {
            List<ResolverActivity.ResolvedComponentInfo> arrayList = new ArrayList<>();
            this.mUnfilteredResolveList = arrayList;
            this.mResolverListController.addResolveListDedupe(arrayList, this.mResolverListCommunicator.getTargetIntent(), this.mBaseResolveList);
            currentResolveList = arrayList;
        } else {
            List<ResolverActivity.ResolvedComponentInfo> currentResolveList2 = this.mResolverListController.getResolversForIntent(true, this.mResolverListCommunicator.shouldGetActivityMetadata(), this.mIntents);
            this.mUnfilteredResolveList = currentResolveList2;
            if (currentResolveList2 == null) {
                processSortedList(currentResolveList2, doPostProcessing);
                return true;
            }
            List<ResolverActivity.ResolvedComponentInfo> originalList = this.mResolverListController.filterIneligibleActivities(currentResolveList2, true);
            if (originalList != null) {
                this.mUnfilteredResolveList = originalList;
            }
            currentResolveList = currentResolveList2;
        }
        Iterator<ResolverActivity.ResolvedComponentInfo> it = currentResolveList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ResolverActivity.ResolvedComponentInfo info = it.next();
            ResolveInfo resolveInfo = info.getResolveInfoAt(0);
            if (resolveInfo.targetUserId != -2) {
                Intent pOrigIntent = this.mResolverListCommunicator.getReplacementIntent(resolveInfo.activityInfo, info.getIntentAt(0));
                Intent replacementIntent = this.mResolverListCommunicator.getReplacementIntent(resolveInfo.activityInfo, this.mResolverListCommunicator.getTargetIntent());
                this.mOtherProfile = new DisplayResolveInfo(info.getIntentAt(0), resolveInfo, resolveInfo.loadLabel(this.mPm), resolveInfo.loadLabel(this.mPm), pOrigIntent != null ? pOrigIntent : replacementIntent, makePresentationGetter(resolveInfo));
                currentResolveList.remove(info);
            }
        }
        if (this.mOtherProfile == null) {
            try {
                this.mLastChosen = this.mResolverListController.getLastChosen();
            } catch (RemoteException re) {
                Log.d(TAG, "Error calling getLastChosenActivity\n" + re);
            }
        }
        setPlaceholderCount(0);
        if (currentResolveList == null || currentResolveList.size() <= 0) {
            processSortedList(currentResolveList, doPostProcessing);
            return true;
        }
        List<ResolverActivity.ResolvedComponentInfo> originalList2 = this.mResolverListController.filterLowPriority(currentResolveList, this.mUnfilteredResolveList == currentResolveList);
        if (originalList2 != null) {
            this.mUnfilteredResolveList = originalList2;
        }
        if (currentResolveList.size() > 1 || (currentResolveList.size() >= 1 && this.mResolverListAdapterExt.hasCustomFlag(512))) {
            int placeholderCount = currentResolveList.size();
            if (this.mResolverListCommunicator.useLayoutWithDefault()) {
                placeholderCount--;
            }
            setPlaceholderCount(placeholderCount);
            this.mResolverListAdapterExt.setPlaceholderResolveList(currentResolveList);
            createSortingTask(doPostProcessing).execute(currentResolveList);
            postListReadyRunnable(doPostProcessing, false);
            return false;
        }
        processSortedList(currentResolveList, doPostProcessing);
        return true;
    }

    AsyncTask<List<ResolverActivity.ResolvedComponentInfo>, Void, List<ResolverActivity.ResolvedComponentInfo>> createSortingTask(final boolean doPostProcessing) {
        return new AsyncTask<List<ResolverActivity.ResolvedComponentInfo>, Void, List<ResolverActivity.ResolvedComponentInfo>>() { // from class: com.android.internal.app.ResolverListAdapter.1
            /* JADX INFO: Access modifiers changed from: protected */
            public List<ResolverActivity.ResolvedComponentInfo> doInBackground(List<ResolverActivity.ResolvedComponentInfo>... params) {
                ResolverListAdapter.this.mResolverListController.sort(params[0]);
                return params[0];
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void onPostExecute(List<ResolverActivity.ResolvedComponentInfo> sortedComponents) {
                ResolverListAdapter.this.processSortedList(sortedComponents, doPostProcessing);
                ResolverListAdapter.this.notifyDataSetChanged();
                if (doPostProcessing) {
                    ResolverListAdapter.this.mResolverListCommunicator.updateProfileViewButton();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void processSortedList(List<ResolverActivity.ResolvedComponentInfo> sortedComponents, boolean doPostProcessing) {
        ResolveInfo ri;
        if (this.mResolverListAdapterExt.sortComponentsNull(sortedComponents, true)) {
            if (this.mInitialIntents != null) {
                int i = 0;
                while (true) {
                    Intent[] intentArr = this.mInitialIntents;
                    if (i >= intentArr.length) {
                        break;
                    }
                    Intent ii = intentArr[i];
                    if (!(ii == null || (ri = this.mResolverListAdapterExt.getResolveInfo(ii, this.mPm)) == null)) {
                        UserManager userManager = (UserManager) this.mContext.getSystemService("user");
                        if (ii instanceof LabeledIntent) {
                            LabeledIntent li = (LabeledIntent) ii;
                            ri.resolvePackageName = li.getSourcePackage();
                            ri.labelRes = li.getLabelResource();
                            ri.nonLocalizedLabel = li.getNonLocalizedLabel();
                            ri.icon = li.getIconResource();
                            ri.iconResourceId = ri.icon;
                        }
                        if (userManager.isManagedProfile()) {
                            ri.noResourceId = true;
                            ri.icon = 0;
                        }
                        if (!this.mResolverListAdapterExt.hookAddResolveInfo(this.mContext, this, ii, ri, this.mDisplayList)) {
                            addResolveInfo(new DisplayResolveInfo(ii, ri, ri.loadLabel(this.mPm), null, ii, makePresentationGetter(ri)));
                        }
                    }
                    i++;
                }
            }
            if (this.mResolverListAdapterExt.sortComponentsNull(sortedComponents, false)) {
                for (ResolverActivity.ResolvedComponentInfo rci : sortedComponents) {
                    if (rci.getResolveInfoAt(0) != null) {
                        addResolveInfoWithAlternates(rci);
                    }
                }
            }
        }
        this.mResolverListAdapterExt.addMultiAppResolveInfoIfNeed(sortedComponents, this.mIntents, this.mResolverListController, this.mPm, this.mDisplayList);
        this.mResolverListCommunicator.sendVoiceChoicesIfNeeded();
        postListReadyRunnable(doPostProcessing, true);
        this.mIsTabLoaded = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postListReadyRunnable(final boolean doPostProcessing, final boolean rebuildCompleted) {
        if (this.mPostListReadyRunnable == null) {
            this.mPostListReadyRunnable = new Runnable() { // from class: com.android.internal.app.ResolverListAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    ResolverListAdapter.this.mResolverListCommunicator.onPostListReady(ResolverListAdapter.this, doPostProcessing, rebuildCompleted);
                    ResolverListAdapter.this.mPostListReadyRunnable = null;
                }
            };
            this.mContext.getMainThreadHandler().post(this.mPostListReadyRunnable);
        }
    }

    private void addResolveInfoWithAlternates(ResolverActivity.ResolvedComponentInfo rci) {
        int count = rci.getCount();
        Intent intent = rci.getIntentAt(0);
        ResolveInfo add = rci.getResolveInfoAt(0);
        Intent replaceIntent = this.mResolverListCommunicator.getReplacementIntent(add.activityInfo, intent);
        Intent defaultIntent = this.mResolverListCommunicator.getReplacementIntent(add.activityInfo, this.mResolverListCommunicator.getTargetIntent());
        DisplayResolveInfo dri = new DisplayResolveInfo(intent, add, replaceIntent != null ? replaceIntent : defaultIntent, makePresentationGetter(add));
        dri.setPinned(rci.isPinned());
        if (rci.isPinned()) {
            Log.i(TAG, "Pinned item: " + rci.name);
        }
        addResolveInfo(dri);
        if (replaceIntent == intent) {
            for (int i = 1; i < count; i++) {
                Intent altIntent = rci.getIntentAt(i);
                dri.addAlternateSourceIntent(altIntent);
            }
        }
        updateLastChosenPosition(add);
    }

    private void updateLastChosenPosition(ResolveInfo info) {
        if (this.mOtherProfile != null) {
            this.mLastChosenPosition = -1;
            return;
        }
        ResolveInfo resolveInfo = this.mLastChosen;
        if (resolveInfo != null && resolveInfo.activityInfo.packageName.equals(info.activityInfo.packageName) && this.mLastChosen.activityInfo.name.equals(info.activityInfo.name)) {
            this.mLastChosenPosition = this.mDisplayList.size() - 1;
        }
    }

    private void addResolveInfo(DisplayResolveInfo dri) {
        if (dri != null && dri.getResolveInfo() != null && dri.getResolveInfo().targetUserId == -2 && shouldAddResolveInfo(dri)) {
            this.mDisplayList.add(dri);
            Log.i(TAG, "Add DisplayResolveInfo component: " + dri.getResolvedComponentName() + ", intent component: " + dri.getResolvedIntent().getComponent());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean shouldAddResolveInfo(DisplayResolveInfo dri) {
        for (DisplayResolveInfo existingInfo : this.mDisplayList) {
            if (this.mResolverListCommunicator.resolveInfoMatch(dri.getResolveInfo(), existingInfo.getResolveInfo())) {
                return false;
            }
        }
        return true;
    }

    public ResolveInfo resolveInfoForPosition(int position, boolean filtered) {
        TargetInfo target = targetInfoForPosition(position, filtered);
        if (target != null) {
            return target.getResolveInfo();
        }
        return null;
    }

    public TargetInfo targetInfoForPosition(int position, boolean filtered) {
        if (filtered) {
            return getItem(position);
        }
        if (this.mDisplayList.size() > position) {
            return this.mDisplayList.get(position);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int totalSize;
        List<DisplayResolveInfo> list = this.mDisplayList;
        if (list == null || list.isEmpty()) {
            totalSize = this.mPlaceholderCount;
        } else {
            totalSize = this.mDisplayList.size();
        }
        if (!this.mFilterLastUsed || this.mLastChosenPosition < 0) {
            return totalSize;
        }
        return totalSize - 1;
    }

    public int getUnfilteredCount() {
        return this.mDisplayList.size();
    }

    @Override // android.widget.Adapter
    public TargetInfo getItem(int position) {
        int i;
        if (this.mFilterLastUsed && (i = this.mLastChosenPosition) >= 0 && position >= i) {
            position++;
        }
        if (this.mDisplayList.size() > position) {
            return this.mDisplayList.get(position);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public int getDisplayResolveInfoCount() {
        return this.mDisplayList.size();
    }

    public DisplayResolveInfo getDisplayResolveInfo(int index) {
        return this.mDisplayList.get(index);
    }

    @Override // android.widget.Adapter
    public final View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null || (view != null && view.getTag() == null)) {
            view = createView(parent);
        }
        onBindView(view, getItem(position), position);
        return view;
    }

    public final View createView(ViewGroup parent) {
        View view = onCreateView(parent);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return view;
    }

    View onCreateView(ViewGroup parent) {
        return this.mResolverListAdapterExt.hookonCreateView(this.mInflater, R.layout.resolve_list_item, parent);
    }

    public final void bindView(int position, View view) {
        onBindView(view, getItem(position), position);
    }

    protected void onBindView(View view, TargetInfo info, int position) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (info == null) {
            holder.icon.setImageDrawable(this.mContext.getDrawable(R.drawable.resolver_icon_placeholder));
            return;
        }
        if (!(info instanceof DisplayResolveInfo) || ((DisplayResolveInfo) info).hasDisplayLabel()) {
            holder.bindLabel(info.getDisplayLabel(), info.getExtendedInfo(), alwaysShowSubLabel());
        } else {
            getLoadLabelTask((DisplayResolveInfo) info, holder).execute(new Void[0]);
        }
        if (!(info instanceof DisplayResolveInfo) || ((DisplayResolveInfo) info).hasDisplayIcon()) {
            holder.bindIcon(info);
        } else {
            new LoadIconTask((DisplayResolveInfo) info, holder).execute(new Void[0]);
        }
    }

    protected LoadLabelTask getLoadLabelTask(DisplayResolveInfo info, ViewHolder holder) {
        return new LoadLabelTask(info, holder);
    }

    public void onDestroy() {
        if (this.mPostListReadyRunnable != null) {
            this.mContext.getMainThreadHandler().removeCallbacks(this.mPostListReadyRunnable);
            this.mPostListReadyRunnable = null;
        }
        ResolverListController resolverListController = this.mResolverListController;
        if (resolverListController != null) {
            resolverListController.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ColorMatrixColorFilter getSuspendedColorMatrix() {
        if (sSuspendedMatrixColorFilter == null) {
            ColorMatrix tempBrightnessMatrix = new ColorMatrix();
            float[] mat = tempBrightnessMatrix.getArray();
            mat[0] = 0.5f;
            mat[6] = 0.5f;
            mat[12] = 0.5f;
            mat[4] = 127;
            mat[9] = 127;
            mat[14] = 127;
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0.0f);
            matrix.preConcat(tempBrightnessMatrix);
            sSuspendedMatrixColorFilter = new ColorMatrixColorFilter(matrix);
        }
        return sSuspendedMatrixColorFilter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActivityInfoPresentationGetter makePresentationGetter(ActivityInfo ai) {
        return new ActivityInfoPresentationGetter(this.mContext, this.mIconDpi, ai);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveInfoPresentationGetter makePresentationGetter(ResolveInfo ri) {
        return new ResolveInfoPresentationGetter(this.mContext, this.mIconDpi, ri);
    }

    Drawable loadIconForResolveInfo(ResolveInfo ri) {
        return makePresentationGetter(ri).getIcon(getUserHandle());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadFilteredItemIconTaskAsync(final ImageView iconView) {
        final DisplayResolveInfo iconInfo = getFilteredItem();
        if (iconView != null && iconInfo != null) {
            new AsyncTask<Void, Void, Drawable>() { // from class: com.android.internal.app.ResolverListAdapter.3
                /* JADX INFO: Access modifiers changed from: protected */
                public Drawable doInBackground(Void... params) {
                    return ResolverListAdapter.this.loadIconForResolveInfo(iconInfo.getResolveInfo());
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public void onPostExecute(Drawable d) {
                    iconView.setImageDrawable(d);
                }
            }.execute(new Void[0]);
        }
    }

    public UserHandle getUserHandle() {
        return this.mResolverListController.getUserHandle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<ResolverActivity.ResolvedComponentInfo> getResolversForUser(UserHandle userHandle) {
        return this.mResolverListController.getResolversForIntentAsUser(true, this.mResolverListCommunicator.shouldGetActivityMetadata(), this.mIntents, userHandle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<Intent> getIntents() {
        return this.mIntents;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isTabLoaded() {
        return this.mIsTabLoaded;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void markTabLoaded() {
        this.mIsTabLoaded = true;
    }

    protected boolean alwaysShowSubLabel() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class ViewHolder {
        public Drawable defaultItemViewBackground;
        public ImageView icon;
        public View itemView;
        public TextView text;
        public TextView text2;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ViewHolder(View view) {
            this.itemView = view;
            this.defaultItemViewBackground = view.getBackground();
            this.text = (TextView) view.findViewById(16908308);
            this.text2 = (TextView) view.findViewById(16908309);
            this.icon = (ImageView) view.findViewById(16908294);
        }

        public void bindLabel(CharSequence label, CharSequence subLabel, boolean showSubLabel) {
            this.text.setText(label);
            if (TextUtils.equals(label, subLabel)) {
                subLabel = null;
            }
            this.text2.setText(subLabel);
            if (showSubLabel || subLabel != null) {
                this.text2.setVisibility(0);
            } else {
                this.text2.setVisibility(8);
            }
            this.itemView.setContentDescription(null);
        }

        public void updateContentDescription(String description) {
            this.itemView.setContentDescription(description);
        }

        public void bindIcon(TargetInfo info) {
            this.icon.setImageDrawable(info.getDisplayIcon(this.itemView.getContext()));
            if (info.isSuspended()) {
                this.icon.setColorFilter(ResolverListAdapter.getSuspendedColorMatrix());
            } else {
                this.icon.setColorFilter((ColorFilter) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class LoadLabelTask extends AsyncTask<Void, Void, CharSequence[]> {
        private final DisplayResolveInfo mDisplayResolveInfo;
        private final ViewHolder mHolder;

        protected LoadLabelTask(DisplayResolveInfo dri, ViewHolder holder) {
            this.mDisplayResolveInfo = dri;
            this.mHolder = holder;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public CharSequence[] doInBackground(Void... voids) {
            ResolveInfoPresentationGetter pg = ResolverListAdapter.this.makePresentationGetter(this.mDisplayResolveInfo.getResolveInfo());
            if (ResolverListAdapter.this.mIsAudioCaptureDevice) {
                ActivityInfo activityInfo = this.mDisplayResolveInfo.getResolveInfo().activityInfo;
                String packageName = activityInfo.packageName;
                int uid = activityInfo.applicationInfo.uid;
                boolean hasRecordPermission = PermissionChecker.checkPermissionForPreflight(ResolverListAdapter.this.mContext, Manifest.permission.RECORD_AUDIO, -1, uid, packageName) == 0;
                if (!hasRecordPermission) {
                    return new CharSequence[]{pg.getLabel(), ResolverListAdapter.this.mContext.getString(R.string.usb_device_resolve_prompt_warn)};
                }
            }
            return new CharSequence[]{pg.getLabel(), pg.getSubLabel()};
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(CharSequence[] result) {
            this.mDisplayResolveInfo.setDisplayLabel(result[0]);
            this.mDisplayResolveInfo.setExtendedInfo(result[1]);
            this.mHolder.bindLabel(result[0], result[1], ResolverListAdapter.this.alwaysShowSubLabel());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class LoadIconTask extends AsyncTask<Void, Void, Drawable> {
        protected final DisplayResolveInfo mDisplayResolveInfo;
        private ViewHolder mHolder;
        private ILoadIconTaskExt mLoadIconTaskExt;
        private final ResolveInfo mResolveInfo;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LoadIconTask(DisplayResolveInfo dri, ViewHolder holder) {
            this.mLoadIconTaskExt = ILoadIconTaskExt.DEFAULT;
            this.mDisplayResolveInfo = dri;
            this.mResolveInfo = dri.getResolveInfo();
            this.mHolder = holder;
            this.mLoadIconTaskExt = LoadIconTaskExtPlugin.constructor.newInstance(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Drawable doInBackground(Void... params) {
            return ResolverListAdapter.this.loadIconForResolveInfo(this.mResolveInfo);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(Drawable d) {
            DisplayResolveInfo otherProfile = ResolverListAdapter.this.getOtherProfile();
            DisplayResolveInfo displayResolveInfo = this.mDisplayResolveInfo;
            if (otherProfile == displayResolveInfo) {
                ResolverListAdapter.this.mResolverListCommunicator.updateProfileViewButton();
                return;
            }
            displayResolveInfo.setDisplayIcon(d);
            this.mHolder.bindIcon(this.mDisplayResolveInfo);
            this.mLoadIconTaskExt.hookonPostExecute(ResolverListAdapter.this.mResolverListAdapterExt, this.mDisplayResolveInfo);
        }

        public void setViewHolder(ViewHolder holder) {
            if (!this.mLoadIconTaskExt.hooksetViewHolder(ResolverListAdapter.this.mResolverListAdapterExt, this.mDisplayResolveInfo, holder)) {
                this.mHolder = holder;
                holder.bindIcon(this.mDisplayResolveInfo);
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class ResolveInfoPresentationGetter extends ActivityInfoPresentationGetter {
        private final ResolveInfo mRi;

        public ResolveInfoPresentationGetter(Context ctx, int iconDpi, ResolveInfo ri) {
            super(ctx, iconDpi, ri.activityInfo);
            this.mRi = ri;
        }

        @Override // com.android.internal.app.ResolverListAdapter.ActivityInfoPresentationGetter, com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        Drawable getIconSubstituteInternal() {
            Drawable dr = null;
            try {
                if (!(this.mRi.resolvePackageName == null || this.mRi.icon == 0)) {
                    dr = loadIconFromResource(this.mPm.getResourcesForApplication(this.mRi.resolvePackageName), this.mRi.icon);
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(ResolverListAdapter.TAG, "SUBSTITUTE_SHARE_TARGET_APP_NAME_AND_ICON permission granted but couldn't find resources for package", e);
            }
            return dr == null ? super.getIconSubstituteInternal() : dr;
        }

        @Override // com.android.internal.app.ResolverListAdapter.ActivityInfoPresentationGetter, com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        String getAppSubLabelInternal() {
            return this.mRi.loadLabel(this.mPm).toString();
        }
    }

    /* loaded from: classes4.dex */
    public static class ActivityInfoPresentationGetter extends TargetPresentationGetter {
        private final ActivityInfo mActivityInfo;

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        public /* bridge */ /* synthetic */ Drawable getIcon(UserHandle userHandle) {
            return super.getIcon(userHandle);
        }

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        public /* bridge */ /* synthetic */ Bitmap getIconBitmap(UserHandle userHandle) {
            return super.getIconBitmap(userHandle);
        }

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        public /* bridge */ /* synthetic */ String getLabel() {
            return super.getLabel();
        }

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        public /* bridge */ /* synthetic */ String getSubLabel() {
            return super.getSubLabel();
        }

        public ActivityInfoPresentationGetter(Context ctx, int iconDpi, ActivityInfo activityInfo) {
            super(ctx, iconDpi, activityInfo.applicationInfo);
            this.mActivityInfo = activityInfo;
        }

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        Drawable getIconSubstituteInternal() {
            try {
                if (this.mActivityInfo.icon == 0) {
                    return null;
                }
                Drawable dr = loadIconFromResource(this.mPm.getResourcesForApplication(this.mActivityInfo.applicationInfo), this.mActivityInfo.icon);
                return dr;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(ResolverListAdapter.TAG, "SUBSTITUTE_SHARE_TARGET_APP_NAME_AND_ICON permission granted but couldn't find resources for package", e);
                return null;
            }
        }

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        String getAppSubLabelInternal() {
            return (String) this.mActivityInfo.loadLabel(this.mPm);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static abstract class TargetPresentationGetter {
        private final ApplicationInfo mAi;
        private Context mCtx;
        private final boolean mHasSubstitutePermission;
        private final int mIconDpi;
        protected PackageManager mPm;

        abstract String getAppSubLabelInternal();

        abstract Drawable getIconSubstituteInternal();

        TargetPresentationGetter(Context ctx, int iconDpi, ApplicationInfo ai) {
            this.mCtx = ctx;
            PackageManager packageManager = ctx.getPackageManager();
            this.mPm = packageManager;
            this.mAi = ai;
            this.mIconDpi = iconDpi;
            this.mHasSubstitutePermission = packageManager.checkPermission(Manifest.permission.SUBSTITUTE_SHARE_TARGET_APP_NAME_AND_ICON, ai.packageName) == 0;
        }

        public Drawable getIcon(UserHandle userHandle) {
            return new BitmapDrawable(this.mCtx.getResources(), getIconBitmap(userHandle));
        }

        public Bitmap getIconBitmap(UserHandle userHandle) {
            Drawable dr = null;
            if (this.mHasSubstitutePermission) {
                dr = getIconSubstituteInternal();
            }
            if (dr == null) {
                try {
                    if (this.mAi.icon != 0) {
                        dr = loadIconFromResource(this.mPm.getResourcesForApplication(this.mAi), this.mAi.icon);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                }
            }
            if (dr == null) {
                dr = this.mAi.loadIcon(this.mPm);
            }
            SimpleIconFactory sif = SimpleIconFactory.obtain(this.mCtx);
            Bitmap icon = sif.createUserBadgedIconBitmap(dr, userHandle);
            sif.recycle();
            return icon;
        }

        public String getLabel() {
            String label = null;
            if (this.mHasSubstitutePermission) {
                label = getAppSubLabelInternal();
            }
            if (label != null) {
                return label;
            }
            String label2 = (String) this.mAi.loadLabel(this.mPm);
            return label2;
        }

        public String getSubLabel() {
            if (this.mHasSubstitutePermission) {
                return null;
            }
            return getAppSubLabelInternal();
        }

        protected String loadLabelFromResource(Resources res, int resId) {
            return res.getString(resId);
        }

        protected Drawable loadIconFromResource(Resources res, int resId) {
            return res.getDrawableForDensity(resId, this.mIconDpi);
        }
    }
}
