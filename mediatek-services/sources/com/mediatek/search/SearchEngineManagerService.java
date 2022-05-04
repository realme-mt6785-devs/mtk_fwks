package com.mediatek.search;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.hidl.base.V1_0.DebugInfo;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import com.mediatek.common.regionalphone.RegionalPhone;
import com.mediatek.common.search.SearchEngine;
import com.mediatek.search.ISearchEngineManagerService;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class SearchEngineManagerService extends ISearchEngineManagerService.Stub {
    private static final String TAG = "SearchEngineManagerService";
    private final Context mContext;
    private SearchEngine mDefaultSearchEngine;
    private ContentObserver mSearchEngineObserver = new ContentObserver(new Handler()) { // from class: com.mediatek.search.SearchEngineManagerService.1
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            SearchEngineManagerService.this.initSearchEngines();
            SearchEngineManagerService searchEngineManagerService = SearchEngineManagerService.this;
            searchEngineManagerService.broadcastSearchEngineChangedInternal(searchEngineManagerService.mContext);
        }
    };
    private List<SearchEngine> mSearchEngines;

    public SearchEngineManagerService(Context context) {
        this.mContext = context;
        context.registerReceiver(new BootCompletedReceiver(), new IntentFilter("android.intent.action.BOOT_COMPLETED"));
        context.getContentResolver().registerContentObserver(RegionalPhone.SEARCHENGINE_URI, true, this.mSearchEngineObserver);
    }

    /* loaded from: classes.dex */
    private final class BootCompletedReceiver extends BroadcastReceiver {
        private BootCompletedReceiver() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.mediatek.search.SearchEngineManagerService$BootCompletedReceiver$1] */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            new Thread() { // from class: com.mediatek.search.SearchEngineManagerService.BootCompletedReceiver.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Process.setThreadPriority(10);
                    SearchEngineManagerService.this.mContext.unregisterReceiver(BootCompletedReceiver.this);
                    SearchEngineManagerService.this.initSearchEngines();
                    SearchEngineManagerService.this.mContext.registerReceiver(new LocaleChangeReceiver(), new IntentFilter("android.intent.action.LOCALE_CHANGED"));
                }
            }.start();
        }
    }

    public synchronized List<SearchEngine> getAvailables() {
        Log.i(TAG, "get avilable search engines");
        if (this.mSearchEngines == null) {
            initSearchEngines();
        }
        return this.mSearchEngines;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSearchEngines() throws IllegalArgumentException {
        this.mSearchEngines = new ArrayList();
        Resources res = this.mContext.getResources();
        String[] searchEngines = res.getStringArray(134479882);
        if (searchEngines == null || 1 >= searchEngines.length) {
            throw new IllegalArgumentException("No data found for ");
        }
        String sp = searchEngines[0];
        for (int i = 1; i < searchEngines.length; i++) {
            String configInfo = searchEngines[i];
            SearchEngine info = SearchEngine.parseFrom(configInfo, sp);
            this.mSearchEngines.add(info);
        }
        SearchEngine searchEngine = this.mDefaultSearchEngine;
        if (searchEngine != null) {
            this.mDefaultSearchEngine = getBestMatch(searchEngine.getName(), this.mDefaultSearchEngine.getFaviconUri());
        }
        if (this.mDefaultSearchEngine == null) {
            this.mDefaultSearchEngine = this.mSearchEngines.get(0);
        }
    }

    /* loaded from: classes.dex */
    private final class LocaleChangeReceiver extends BroadcastReceiver {
        private LocaleChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SearchEngineManagerService.this.initSearchEngines();
            SearchEngineManagerService searchEngineManagerService = SearchEngineManagerService.this;
            searchEngineManagerService.broadcastSearchEngineChangedInternal(searchEngineManagerService.mContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastSearchEngineChangedInternal(Context context) {
        Intent intent = new Intent("com.mediatek.search.SEARCH_ENGINE_CHANGED");
        context.sendBroadcast(intent);
        Log.d(TAG, "broadcast serach engine changed");
    }

    public SearchEngine getBestMatch(String name, String favicon) {
        SearchEngine engine = getByName(name);
        return engine != null ? engine : getByFavicon(favicon);
    }

    private SearchEngine getByFavicon(String favicon) {
        List<SearchEngine> engines = getAvailables();
        for (SearchEngine engine : engines) {
            if (favicon.equals(engine.getFaviconUri())) {
                return engine;
            }
        }
        return null;
    }

    private SearchEngine getByName(String name) {
        List<SearchEngine> engines = getAvailables();
        for (SearchEngine engine : engines) {
            if (name.equals(engine.getName())) {
                return engine;
            }
        }
        return null;
    }

    public SearchEngine getSearchEngine(int field, String value) {
        switch (field) {
            case -1:
                return getByName(value);
            case DebugInfo.Architecture.IS_32BIT /* 2 */:
                return getByFavicon(value);
            default:
                return null;
        }
    }

    public SearchEngine getDefault() {
        return this.mDefaultSearchEngine;
    }

    public boolean setDefault(SearchEngine engine) {
        List<SearchEngine> engines = getAvailables();
        for (SearchEngine eng : engines) {
            if (eng.getName().equals(engine.getName())) {
                this.mDefaultSearchEngine = eng;
                return true;
            }
        }
        return false;
    }
}
