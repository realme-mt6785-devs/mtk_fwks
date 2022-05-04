package com.android.internal.app;

import android.app.ActivityManager;
import android.app.prediction.AppPredictor;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.service.chooser.ChooserTarget;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.R;
import com.android.internal.app.ChooserActivity;
import com.android.internal.app.ResolverActivity;
import com.android.internal.app.ResolverListAdapter;
import com.android.internal.app.chooser.ChooserTargetInfo;
import com.android.internal.app.chooser.DisplayResolveInfo;
import com.android.internal.app.chooser.MultiDisplayResolveInfo;
import com.android.internal.app.chooser.SelectableTargetInfo;
import com.android.internal.app.chooser.TargetInfo;
import com.android.internal.config.sysui.SystemUiDeviceConfigFlags;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public class ChooserListAdapter extends ResolverListAdapter {
    public static final float CALLER_TARGET_SCORE_BOOST = 900.0f;
    private static final boolean DEBUG = false;
    private static final int MAX_CHOOSER_TARGETS_PER_APP = 2;
    private static final int MAX_SUGGESTED_APP_TARGETS = 4;
    public static final int NO_POSITION = -1;
    public static final float SHORTCUT_TARGET_SCORE_BOOST = 90.0f;
    private static final String TAG = "ChooserListAdapter";
    public static final int TARGET_BAD = -1;
    public static final int TARGET_CALLER = 0;
    public static final int TARGET_SERVICE = 1;
    public static final int TARGET_STANDARD = 2;
    public static final int TARGET_STANDARD_AZ = 3;
    private AppPredictor mAppPredictor;
    private AppPredictor.Callback mAppPredictorCallback;
    private boolean mApplySharingAppLimits;
    private final ChooserActivityLogger mChooserActivityLogger;
    private final ChooserListCommunicator mChooserListCommunicator;
    private final int mMaxShortcutTargetsPerApp;
    private final SelectableTargetInfo.SelectableTargetInfoCommunicator mSelectableTargetInfoCommunicator;
    private boolean mEnableStackedApps = !this.mResolverListAdapterExt.isOpShareUi();
    private int mNumShortcutResults = 0;
    private Map<DisplayResolveInfo, ResolverListAdapter.LoadIconTask> mIconLoaders = new HashMap();
    private ChooserTargetInfo mPlaceHolderTargetInfo = new ChooserActivity.PlaceHolderTargetInfo();
    private final List<ChooserTargetInfo> mServiceTargets = new ArrayList();
    private final List<DisplayResolveInfo> mCallerTargets = new ArrayList();
    private final ChooserActivity.BaseChooserTargetComparator mBaseTargetComparator = new ChooserActivity.BaseChooserTargetComparator();
    private boolean mListViewDataChanged = false;
    private List<DisplayResolveInfo> mSortedList = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public interface ChooserListCommunicator extends ResolverListAdapter.ResolverListCommunicator {
        int getMaxRankedTargets();

        boolean isSendAction(Intent intent);

        void sendListViewUpdateMessage(UserHandle userHandle);
    }

    public ChooserListAdapter(Context context, List<Intent> payloadIntents, Intent[] initialIntents, List<ResolveInfo> rList, boolean filterLastUsed, ResolverListController resolverListController, ChooserListCommunicator chooserListCommunicator, SelectableTargetInfo.SelectableTargetInfoCommunicator selectableTargetInfoCommunicator, PackageManager packageManager, ChooserActivityLogger chooserActivityLogger) {
        super(context, payloadIntents, null, rList, filterLastUsed, resolverListController, chooserListCommunicator, false);
        boolean z = true;
        int i = 0;
        this.mMaxShortcutTargetsPerApp = context.getResources().getInteger(R.integer.config_maxShortcutTargetsPerApp);
        this.mChooserListCommunicator = chooserListCommunicator;
        createPlaceHolders();
        this.mSelectableTargetInfoCommunicator = selectableTargetInfoCommunicator;
        this.mChooserActivityLogger = chooserActivityLogger;
        if (initialIntents != null) {
            int i2 = 0;
            while (i2 < initialIntents.length) {
                Intent ii = initialIntents[i2];
                if (ii != null) {
                    ResolveInfo ri = null;
                    ActivityInfo ai = null;
                    ComponentName cn = ii.getComponent();
                    if (cn != null) {
                        try {
                            ai = packageManager.getActivityInfo(ii.getComponent(), i);
                            ri = new ResolveInfo();
                            ri.activityInfo = ai;
                        } catch (PackageManager.NameNotFoundException e) {
                        }
                    }
                    if (ai == null) {
                        ri = packageManager.resolveActivity(ii, 65536);
                        ai = ri != null ? ri.activityInfo : null;
                    }
                    if (ai == null) {
                        Log.w(TAG, "No activity found for " + ii);
                    } else {
                        UserManager userManager = (UserManager) context.getSystemService("user");
                        if (ii instanceof LabeledIntent) {
                            LabeledIntent li = (LabeledIntent) ii;
                            ri.resolvePackageName = li.getSourcePackage();
                            ri.labelRes = li.getLabelResource();
                            ri.nonLocalizedLabel = li.getNonLocalizedLabel();
                            ri.icon = li.getIconResource();
                            ri.iconResourceId = ri.icon;
                        }
                        if (userManager.isManagedProfile()) {
                            ri.noResourceId = z;
                            ri.icon = 0;
                        }
                        this.mCallerTargets.add(new DisplayResolveInfo(ii, ri, ii, makePresentationGetter(ri)));
                        if (this.mCallerTargets.size() == 4) {
                            break;
                        }
                    }
                }
                i2++;
                z = true;
                i = 0;
            }
        }
        this.mApplySharingAppLimits = DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_SYSTEMUI, SystemUiDeviceConfigFlags.APPLY_SHARING_APP_LIMITS_IN_SYSUI, true);
    }

    AppPredictor getAppPredictor() {
        return this.mAppPredictor;
    }

    @Override // com.android.internal.app.ResolverListAdapter
    public void handlePackagesChanged() {
        createPlaceHolders();
        this.mChooserListCommunicator.onHandlePackagesChanged(this);
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        if (!this.mListViewDataChanged) {
            this.mChooserListCommunicator.sendListViewUpdateMessage(getUserHandle());
            this.mListViewDataChanged = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void refreshListView() {
        if (this.mListViewDataChanged) {
            super.notifyDataSetChanged();
        }
        this.mListViewDataChanged = false;
    }

    private void createPlaceHolders() {
        this.mNumShortcutResults = 0;
        this.mServiceTargets.clear();
        for (int i = 0; i < this.mChooserListCommunicator.getMaxRankedTargets(); i++) {
            this.mServiceTargets.add(this.mPlaceHolderTargetInfo);
        }
    }

    @Override // com.android.internal.app.ResolverListAdapter
    View onCreateView(ViewGroup parent) {
        return this.mInflater.inflate(R.layout.resolve_grid_item, parent, false);
    }

    @Override // com.android.internal.app.ResolverListAdapter
    protected void onBindView(View view, TargetInfo info, int position) {
        ResolverListAdapter.ViewHolder holder = (ResolverListAdapter.ViewHolder) view.getTag();
        if (info == null) {
            holder.icon.setImageDrawable(this.mContext.getDrawable(R.drawable.resolver_icon_placeholder));
            return;
        }
        if (!(info instanceof DisplayResolveInfo)) {
            holder.bindLabel(info.getDisplayLabel(), info.getExtendedInfo(), alwaysShowSubLabel());
            holder.bindIcon(info);
            if (info instanceof SelectableTargetInfo) {
                DisplayResolveInfo rInfo = ((SelectableTargetInfo) info).getDisplayResolveInfo();
                CharSequence charSequence = "";
                CharSequence appName = rInfo != null ? rInfo.getDisplayLabel() : charSequence;
                CharSequence extendedInfo = info.getExtendedInfo();
                CharSequence[] charSequenceArr = new CharSequence[3];
                charSequenceArr[0] = info.getDisplayLabel();
                if (extendedInfo != null) {
                    charSequence = extendedInfo;
                }
                charSequenceArr[1] = charSequence;
                charSequenceArr[2] = appName;
                String contentDescription = String.join(" ", charSequenceArr);
                holder.updateContentDescription(contentDescription);
            }
        } else {
            DisplayResolveInfo dri = (DisplayResolveInfo) info;
            holder.bindLabel(dri.getDisplayLabel(), dri.getExtendedInfo(), alwaysShowSubLabel());
            ResolverListAdapter.LoadIconTask task = this.mIconLoaders.get(dri);
            if (task == null) {
                ResolverListAdapter.LoadIconTask task2 = new ResolverListAdapter.LoadIconTask(dri, holder);
                this.mIconLoaders.put(dri, task2);
                task2.execute(new Void[0]);
            } else {
                task.setViewHolder(holder);
            }
        }
        if (info instanceof ChooserActivity.PlaceHolderTargetInfo) {
            int maxWidth = this.mContext.getResources().getDimensionPixelSize(R.dimen.chooser_direct_share_label_placeholder_max_width);
            holder.text.setMaxWidth(maxWidth);
            holder.text.setBackground(this.mContext.getResources().getDrawable(R.drawable.chooser_direct_share_label_placeholder, this.mContext.getTheme()));
            holder.itemView.setBackground(null);
        } else {
            holder.text.setMaxWidth(Integer.MAX_VALUE);
            holder.text.setBackground(null);
            holder.itemView.setBackground(holder.defaultItemViewBackground);
        }
        if (info instanceof MultiDisplayResolveInfo) {
            Drawable bkg = this.mContext.getDrawable(R.drawable.chooser_group_background);
            holder.text.setPaddingRelative(0, 0, bkg.getIntrinsicWidth(), 0);
            holder.text.setBackground(bkg);
        } else if (!info.isPinned() || getPositionTargetType(position) != 2) {
            holder.text.setBackground(null);
            holder.text.setPaddingRelative(0, 0, 0, 0);
        } else {
            Drawable bkg2 = this.mContext.getDrawable(R.drawable.chooser_pinned_background);
            holder.text.setPaddingRelative(bkg2.getIntrinsicWidth(), 0, 0, 0);
            holder.text.setBackground(bkg2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateAlphabeticalList() {
        new AsyncTask<Void, Void, List<DisplayResolveInfo>>() { // from class: com.android.internal.app.ChooserListAdapter.1
            /* JADX INFO: Access modifiers changed from: protected */
            public List<DisplayResolveInfo> doInBackground(Void... voids) {
                List<DisplayResolveInfo> allTargets = new ArrayList<>();
                allTargets.addAll(ChooserListAdapter.this.mDisplayList);
                allTargets.addAll(ChooserListAdapter.this.mCallerTargets);
                if (!ChooserListAdapter.this.mEnableStackedApps) {
                    return allTargets;
                }
                Map<String, DisplayResolveInfo> consolidated = new HashMap<>();
                for (DisplayResolveInfo info : allTargets) {
                    String packageName = info.getResolvedComponentName().getPackageName();
                    DisplayResolveInfo multiDri = consolidated.get(packageName);
                    if (multiDri == null) {
                        consolidated.put(packageName, info);
                    } else if (multiDri instanceof MultiDisplayResolveInfo) {
                        ((MultiDisplayResolveInfo) multiDri).addTarget(info);
                    } else {
                        MultiDisplayResolveInfo multiDisplayResolveInfo = new MultiDisplayResolveInfo(packageName, multiDri);
                        multiDisplayResolveInfo.addTarget(info);
                        consolidated.put(packageName, multiDisplayResolveInfo);
                    }
                }
                List<DisplayResolveInfo> groupedTargets = new ArrayList<>();
                groupedTargets.addAll(consolidated.values());
                Collections.sort(groupedTargets, new ChooserActivity.AzInfoComparator(ChooserListAdapter.this.mContext));
                return groupedTargets;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void onPostExecute(List<DisplayResolveInfo> newList) {
                ChooserListAdapter.this.mSortedList = newList;
                ChooserListAdapter.this.notifyDataSetChanged();
            }
        }.execute(new Void[0]);
    }

    @Override // com.android.internal.app.ResolverListAdapter, android.widget.Adapter
    public int getCount() {
        return getRankedTargetCount() + getAlphaTargetCount() + getSelectableServiceTargetCount() + getCallerTargetCount();
    }

    @Override // com.android.internal.app.ResolverListAdapter
    public int getUnfilteredCount() {
        int appTargets = super.getUnfilteredCount();
        if (appTargets > this.mChooserListCommunicator.getMaxRankedTargets()) {
            appTargets += this.mChooserListCommunicator.getMaxRankedTargets();
        }
        return getSelectableServiceTargetCount() + appTargets + getCallerTargetCount();
    }

    public int getCallerTargetCount() {
        return this.mCallerTargets.size();
    }

    public int getSelectableServiceTargetCount() {
        int count = 0;
        for (ChooserTargetInfo info : this.mServiceTargets) {
            if (info instanceof SelectableTargetInfo) {
                count++;
            }
        }
        return count;
    }

    public int getServiceTargetCount() {
        ChooserListCommunicator chooserListCommunicator = this.mChooserListCommunicator;
        if (!chooserListCommunicator.isSendAction(chooserListCommunicator.getTargetIntent()) || ActivityManager.isLowRamDeviceStatic()) {
            return 0;
        }
        return Math.min(this.mServiceTargets.size(), this.mChooserListCommunicator.getMaxRankedTargets());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getAlphaTargetCount() {
        int groupedCount = this.mSortedList.size();
        int ungroupedCount = this.mCallerTargets.size() + this.mDisplayList.size();
        if (ungroupedCount > this.mChooserListCommunicator.getMaxRankedTargets()) {
            return groupedCount;
        }
        return 0;
    }

    public int getRankedTargetCount() {
        int spacesAvailable = this.mChooserListCommunicator.getMaxRankedTargets() - getCallerTargetCount();
        return Math.min(spacesAvailable, super.getCount());
    }

    public int getPositionTargetType(int position) {
        int serviceTargetCount = getServiceTargetCount();
        if (position < serviceTargetCount) {
            return 1;
        }
        int offset = 0 + serviceTargetCount;
        int callerTargetCount = getCallerTargetCount();
        if (position - offset < callerTargetCount) {
            return 0;
        }
        int offset2 = offset + callerTargetCount;
        int rankedTargetCount = getRankedTargetCount();
        if (position - offset2 < rankedTargetCount) {
            return 2;
        }
        int standardTargetCount = getAlphaTargetCount();
        if (position - (offset2 + rankedTargetCount) < standardTargetCount) {
            return 3;
        }
        return -1;
    }

    @Override // com.android.internal.app.ResolverListAdapter, android.widget.Adapter
    public TargetInfo getItem(int position) {
        return targetInfoForPosition(position, true);
    }

    @Override // com.android.internal.app.ResolverListAdapter
    public TargetInfo targetInfoForPosition(int position, boolean filtered) {
        if (position == -1) {
            return null;
        }
        int serviceTargetCount = filtered ? getServiceTargetCount() : getSelectableServiceTargetCount();
        if (position < serviceTargetCount) {
            return this.mServiceTargets.get(position);
        }
        int offset = 0 + serviceTargetCount;
        int callerTargetCount = getCallerTargetCount();
        if (position - offset < callerTargetCount) {
            return this.mCallerTargets.get(position - offset);
        }
        int offset2 = offset + callerTargetCount;
        int rankedTargetCount = getRankedTargetCount();
        if (position - offset2 < rankedTargetCount) {
            return filtered ? super.getItem(position - offset2) : getDisplayResolveInfo(position - offset2);
        }
        int offset3 = offset2 + rankedTargetCount;
        if (position - offset3 >= getAlphaTargetCount() || this.mSortedList.isEmpty()) {
            return null;
        }
        return this.mSortedList.get(position - offset3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.internal.app.ResolverListAdapter
    public boolean shouldAddResolveInfo(DisplayResolveInfo dri) {
        for (TargetInfo existingInfo : this.mCallerTargets) {
            if (this.mResolverListCommunicator.resolveInfoMatch(dri.getResolveInfo(), existingInfo.getResolveInfo())) {
                return false;
            }
        }
        return super.shouldAddResolveInfo(dri);
    }

    public List<ChooserTargetInfo> getSurfacedTargetInfo() {
        int maxSurfacedTargets = this.mChooserListCommunicator.getMaxRankedTargets();
        return this.mServiceTargets.subList(0, Math.min(maxSurfacedTargets, getSelectableServiceTargetCount()));
    }

    public void addServiceResults(DisplayResolveInfo origTarget, List<ChooserTarget> targets, int targetType, Map<ChooserTarget, ShortcutInfo> directShareToShortcutInfos, List<ChooserActivity.ChooserTargetServiceConnection> pendingChooserTargetServiceConnections) {
        float targetScore;
        if (targets.size() != 0) {
            float baseScore = getBaseScore(origTarget, targetType);
            Collections.sort(targets, this.mBaseTargetComparator);
            int i = 0;
            int maxTargets = 2;
            boolean isShortcutResult = targetType == 2 || targetType == 3;
            if (isShortcutResult) {
                maxTargets = this.mMaxShortcutTargetsPerApp;
            }
            int targetsLimit = this.mApplySharingAppLimits ? Math.min(targets.size(), maxTargets) : targets.size();
            int count = targetsLimit;
            float lastScore = 0.0f;
            boolean shouldNotify = false;
            int i2 = 0;
            while (i2 < count) {
                ChooserTarget target = targets.get(i2);
                float targetScore2 = target.getScore();
                if (this.mApplySharingAppLimits) {
                    float targetScore3 = targetScore2 * baseScore;
                    if (i2 <= 0 || targetScore3 < lastScore) {
                        targetScore = targetScore3;
                    } else {
                        targetScore = lastScore * 0.95f;
                    }
                } else {
                    targetScore = targetScore2;
                }
                UserHandle userHandle = getUserHandle();
                Context contextAsUser = this.mContext.createContextAsUser(userHandle, i);
                boolean isInserted = insertServiceTarget(new SelectableTargetInfo(contextAsUser, origTarget, target, targetScore, this.mSelectableTargetInfoCommunicator, isShortcutResult ? directShareToShortcutInfos.get(target) : null));
                if (isInserted && isShortcutResult) {
                    this.mNumShortcutResults++;
                }
                shouldNotify |= isInserted;
                lastScore = targetScore;
                i2++;
                count = count;
                i = 0;
            }
            if (shouldNotify) {
                notifyDataSetChanged();
            }
        }
    }

    int getNumServiceTargetsForExpand() {
        return this.mNumShortcutResults;
    }

    public float getBaseScore(DisplayResolveInfo target, int targetType) {
        if (target == null) {
            return 900.0f;
        }
        float score = super.getScore(target);
        if (targetType == 2 || targetType == 3) {
            return 90.0f * score;
        }
        return score;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$completeServiceTargetLoading$0(ChooserTargetInfo o) {
        return o instanceof ChooserActivity.PlaceHolderTargetInfo;
    }

    public void completeServiceTargetLoading() {
        this.mServiceTargets.removeIf(ChooserListAdapter$$ExternalSyntheticLambda0.INSTANCE);
        if (this.mServiceTargets.isEmpty()) {
            this.mServiceTargets.add(new ChooserActivity.EmptyTargetInfo());
            this.mChooserActivityLogger.logSharesheetEmptyDirectShareRow();
        }
        notifyDataSetChanged();
    }

    private boolean insertServiceTarget(ChooserTargetInfo chooserTargetInfo) {
        if (this.mServiceTargets.size() == 1 && (this.mServiceTargets.get(0) instanceof ChooserActivity.EmptyTargetInfo)) {
            return false;
        }
        for (ChooserTargetInfo otherTargetInfo : this.mServiceTargets) {
            if (chooserTargetInfo.isSimilar(otherTargetInfo)) {
                return false;
            }
        }
        int currentSize = this.mServiceTargets.size();
        float newScore = chooserTargetInfo.getModifiedScore();
        for (int i = 0; i < Math.min(currentSize, this.mChooserListCommunicator.getMaxRankedTargets()); i++) {
            ChooserTargetInfo serviceTarget = this.mServiceTargets.get(i);
            if (serviceTarget == null) {
                this.mServiceTargets.set(i, chooserTargetInfo);
                return true;
            } else if (newScore > serviceTarget.getModifiedScore()) {
                this.mServiceTargets.add(i, chooserTargetInfo);
                return true;
            }
        }
        if (currentSize >= this.mChooserListCommunicator.getMaxRankedTargets()) {
            return false;
        }
        this.mServiceTargets.add(chooserTargetInfo);
        return true;
    }

    public ChooserTarget getChooserTargetForValue(int value) {
        return this.mServiceTargets.get(value).getChooserTarget();
    }

    @Override // com.android.internal.app.ResolverListAdapter
    protected boolean alwaysShowSubLabel() {
        return true;
    }

    @Override // com.android.internal.app.ResolverListAdapter
    AsyncTask<List<ResolverActivity.ResolvedComponentInfo>, Void, List<ResolverActivity.ResolvedComponentInfo>> createSortingTask(final boolean doPostProcessing) {
        return new AsyncTask<List<ResolverActivity.ResolvedComponentInfo>, Void, List<ResolverActivity.ResolvedComponentInfo>>() { // from class: com.android.internal.app.ChooserListAdapter.2
            /* JADX INFO: Access modifiers changed from: protected */
            public List<ResolverActivity.ResolvedComponentInfo> doInBackground(List<ResolverActivity.ResolvedComponentInfo>... params) {
                ChooserListAdapter.this.mResolverListController.topK(params[0], ChooserListAdapter.this.mChooserListCommunicator.getMaxRankedTargets());
                return params[0];
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public void onPostExecute(List<ResolverActivity.ResolvedComponentInfo> sortedComponents) {
                ChooserListAdapter.this.processSortedList(sortedComponents, doPostProcessing);
                if (doPostProcessing) {
                    ChooserListAdapter.this.mChooserListCommunicator.updateProfileViewButton();
                    ChooserListAdapter.this.notifyDataSetChanged();
                }
            }
        };
    }

    public void setAppPredictor(AppPredictor appPredictor) {
        this.mAppPredictor = appPredictor;
    }

    public void setAppPredictorCallback(AppPredictor.Callback appPredictorCallback) {
        this.mAppPredictorCallback = appPredictorCallback;
    }

    public void destroyAppPredictor() {
        if (getAppPredictor() != null) {
            getAppPredictor().unregisterPredictionUpdates(this.mAppPredictorCallback);
            getAppPredictor().destroy();
            setAppPredictor(null);
        }
    }
}
