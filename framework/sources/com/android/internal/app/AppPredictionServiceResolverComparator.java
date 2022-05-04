package com.android.internal.app;

import android.app.prediction.AppPredictor;
import android.app.prediction.AppTarget;
import android.app.prediction.AppTargetEvent;
import android.app.prediction.AppTargetId;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Message;
import android.os.UserHandle;
import android.util.Log;
import com.android.internal.app.AbstractResolverComparator;
import com.android.internal.app.ResolverActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class AppPredictionServiceResolverComparator extends AbstractResolverComparator {
    private static final String TAG = "APSResolverComparator";
    private final AppPredictor mAppPredictor;
    private final Context mContext;
    private final Intent mIntent;
    private final String mReferrerPackage;
    private ResolverRankerServiceResolverComparator mResolverRankerService;
    private final Map<ComponentName, Integer> mTargetRanks = new HashMap();
    private final Map<ComponentName, Integer> mTargetScores = new HashMap();
    private final UserHandle mUser;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppPredictionServiceResolverComparator(Context context, Intent intent, String referrerPackage, AppPredictor appPredictor, UserHandle user, ChooserActivityLogger chooserActivityLogger) {
        super(context, intent);
        this.mContext = context;
        this.mIntent = intent;
        this.mAppPredictor = appPredictor;
        this.mUser = user;
        this.mReferrerPackage = referrerPackage;
        setChooserActivityLogger(chooserActivityLogger);
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    int compare(ResolveInfo lhs, ResolveInfo rhs) {
        ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = this.mResolverRankerService;
        if (resolverRankerServiceResolverComparator != null) {
            return resolverRankerServiceResolverComparator.compare(lhs, rhs);
        }
        Integer lhsRank = this.mTargetRanks.get(new ComponentName(lhs.activityInfo.packageName, lhs.activityInfo.name));
        Integer rhsRank = this.mTargetRanks.get(new ComponentName(rhs.activityInfo.packageName, rhs.activityInfo.name));
        if (lhsRank == null && rhsRank == null) {
            return 0;
        }
        if (lhsRank == null) {
            return -1;
        }
        if (rhsRank == null) {
            return 1;
        }
        return lhsRank.intValue() - rhsRank.intValue();
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    void doCompute(final List<ResolverActivity.ResolvedComponentInfo> targets) {
        if (targets.isEmpty()) {
            this.mHandler.sendEmptyMessage(0);
            return;
        }
        List<AppTarget> appTargets = new ArrayList<>();
        for (ResolverActivity.ResolvedComponentInfo target : targets) {
            appTargets.add(new AppTarget.Builder(new AppTargetId(target.name.flattenToString()), target.name.getPackageName(), this.mUser).setClassName(target.name.getClassName()).build());
        }
        this.mAppPredictor.sortTargets(appTargets, Executors.newSingleThreadExecutor(), new Consumer() { // from class: com.android.internal.app.AppPredictionServiceResolverComparator$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AppPredictionServiceResolverComparator.this.lambda$doCompute$1$AppPredictionServiceResolverComparator(targets, (List) obj);
            }
        });
    }

    public /* synthetic */ void lambda$doCompute$1$AppPredictionServiceResolverComparator(List targets, List sortedAppTargets) {
        if (sortedAppTargets.isEmpty()) {
            Log.i(TAG, "AppPredictionService disabled. Using resolver.");
            ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = new ResolverRankerServiceResolverComparator(this.mContext, this.mIntent, this.mReferrerPackage, new AbstractResolverComparator.AfterCompute() { // from class: com.android.internal.app.AppPredictionServiceResolverComparator$$ExternalSyntheticLambda0
                @Override // com.android.internal.app.AbstractResolverComparator.AfterCompute
                public final void afterCompute() {
                    AppPredictionServiceResolverComparator.this.lambda$doCompute$0$AppPredictionServiceResolverComparator();
                }
            }, getChooserActivityLogger());
            this.mResolverRankerService = resolverRankerServiceResolverComparator;
            resolverRankerServiceResolverComparator.compute(targets);
            return;
        }
        Log.i(TAG, "AppPredictionService response received");
        handleResult(sortedAppTargets);
    }

    public /* synthetic */ void lambda$doCompute$0$AppPredictionServiceResolverComparator() {
        this.mHandler.sendEmptyMessage(0);
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    void handleResultMessage(Message msg) {
        if (msg.what == 0 && msg.obj != null) {
            List<AppTarget> sortedAppTargets = (List) msg.obj;
            handleSortedAppTargets(sortedAppTargets);
        } else if (msg.obj == null && this.mResolverRankerService == null) {
            Log.e(TAG, "Unexpected null result");
        }
    }

    private void handleResult(List<AppTarget> sortedAppTargets) {
        if (this.mHandler.hasMessages(1)) {
            handleSortedAppTargets(sortedAppTargets);
            this.mHandler.removeMessages(1);
            afterCompute();
        }
    }

    private void handleSortedAppTargets(List<AppTarget> sortedAppTargets) {
        if (checkAppTargetRankValid(sortedAppTargets)) {
            sortedAppTargets.forEach(new Consumer() { // from class: com.android.internal.app.AppPredictionServiceResolverComparator$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AppPredictionServiceResolverComparator.this.lambda$handleSortedAppTargets$2$AppPredictionServiceResolverComparator((AppTarget) obj);
                }
            });
        }
        for (int i = 0; i < sortedAppTargets.size(); i++) {
            ComponentName componentName = new ComponentName(sortedAppTargets.get(i).getPackageName(), sortedAppTargets.get(i).getClassName());
            this.mTargetRanks.put(componentName, Integer.valueOf(i));
            Log.i(TAG, "handleSortedAppTargets, sortedAppTargets #" + i + ": " + componentName);
        }
    }

    public /* synthetic */ void lambda$handleSortedAppTargets$2$AppPredictionServiceResolverComparator(AppTarget target) {
        this.mTargetScores.put(new ComponentName(target.getPackageName(), target.getClassName()), Integer.valueOf(target.getRank()));
    }

    private boolean checkAppTargetRankValid(List<AppTarget> sortedAppTargets) {
        for (AppTarget target : sortedAppTargets) {
            if (target.getRank() != 0) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractResolverComparator
    public float getScore(ComponentName name) {
        ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = this.mResolverRankerService;
        if (resolverRankerServiceResolverComparator != null) {
            return resolverRankerServiceResolverComparator.getScore(name);
        }
        Integer rank = this.mTargetRanks.get(name);
        if (rank == null) {
            Log.w(TAG, "Score requested for unknown component. Did you call compute yet?");
            return 0.0f;
        }
        int consecutiveSumOfRanks = ((this.mTargetRanks.size() - 1) * this.mTargetRanks.size()) / 2;
        return 1.0f - (rank.intValue() / consecutiveSumOfRanks);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractResolverComparator
    public List<ComponentName> getTopComponentNames(int topK) {
        ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = this.mResolverRankerService;
        if (resolverRankerServiceResolverComparator != null) {
            return resolverRankerServiceResolverComparator.getTopComponentNames(topK);
        }
        return (List) this.mTargetRanks.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(topK).map(AppPredictionServiceResolverComparator$$ExternalSyntheticLambda3.INSTANCE).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractResolverComparator
    public void updateModel(ComponentName componentName) {
        ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = this.mResolverRankerService;
        if (resolverRankerServiceResolverComparator != null) {
            resolverRankerServiceResolverComparator.updateModel(componentName);
        } else {
            this.mAppPredictor.notifyAppTargetEvent(new AppTargetEvent.Builder(new AppTarget.Builder(new AppTargetId(componentName.toString()), componentName.getPackageName(), this.mUser).setClassName(componentName.getClassName()).build(), 1).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractResolverComparator
    public void destroy() {
        ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = this.mResolverRankerService;
        if (resolverRankerServiceResolverComparator != null) {
            resolverRankerServiceResolverComparator.destroy();
            this.mResolverRankerService = null;
        }
    }
}
