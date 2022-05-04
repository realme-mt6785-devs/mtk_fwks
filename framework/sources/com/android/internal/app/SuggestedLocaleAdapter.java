package com.android.internal.app;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.app.LocaleHelper;
import com.android.internal.app.LocaleStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes4.dex */
public class SuggestedLocaleAdapter extends BaseAdapter implements Filterable {
    private static final int MIN_REGIONS_FOR_SUGGESTIONS = 6;
    private static final int TYPE_HEADER_ALL_OTHERS = 1;
    private static final int TYPE_HEADER_SUGGESTED = 0;
    private static final int TYPE_LOCALE = 2;
    private final boolean mCountryMode;
    private LayoutInflater mInflater;
    private ArrayList<LocaleStore.LocaleInfo> mLocaleOptions;
    private ArrayList<LocaleStore.LocaleInfo> mOriginalLocaleOptions;
    private int mSuggestionCount;
    private Locale mDisplayLocale = null;
    private Context mContextOverride = null;

    static /* synthetic */ int access$208(SuggestedLocaleAdapter x0) {
        int i = x0.mSuggestionCount;
        x0.mSuggestionCount = i + 1;
        return i;
    }

    public SuggestedLocaleAdapter(Set<LocaleStore.LocaleInfo> localeOptions, boolean countryMode) {
        this.mCountryMode = countryMode;
        this.mLocaleOptions = new ArrayList<>(localeOptions.size());
        for (LocaleStore.LocaleInfo li : localeOptions) {
            if (li.isSuggested()) {
                this.mSuggestionCount++;
            }
            this.mLocaleOptions.add(li);
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int position) {
        return getItemViewType(position) == 2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int position) {
        if (!showHeaders()) {
            return 2;
        }
        if (position == 0) {
            return 0;
        }
        return position == this.mSuggestionCount + 1 ? 1 : 2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        if (showHeaders()) {
            return 3;
        }
        return 1;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (showHeaders()) {
            return this.mLocaleOptions.size() + 2;
        }
        return this.mLocaleOptions.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        int offset = 0;
        if (showHeaders()) {
            offset = position > this.mSuggestionCount ? -2 : -1;
        }
        return this.mLocaleOptions.get(position + offset);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public void setDisplayLocale(Context context, Locale locale) {
        if (locale == null) {
            this.mDisplayLocale = null;
            this.mContextOverride = null;
        } else if (!locale.equals(this.mDisplayLocale)) {
            this.mDisplayLocale = locale;
            Configuration configOverride = new Configuration();
            configOverride.setLocale(locale);
            this.mContextOverride = context.createConfigurationContext(configOverride);
        }
    }

    private void setTextTo(TextView textView, int resId) {
        Context context = this.mContextOverride;
        if (context == null) {
            textView.setText(resId);
        } else {
            textView.setText(context.getText(resId));
        }
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        int i;
        if (convertView == null && this.mInflater == null) {
            this.mInflater = LayoutInflater.from(parent.getContext());
        }
        int itemType = getItemViewType(position);
        switch (itemType) {
            case 0:
            case 1:
                if (!(convertView instanceof TextView)) {
                    convertView = this.mInflater.inflate(R.layout.language_picker_section_header, parent, false);
                }
                TextView textView = (TextView) convertView;
                if (itemType == 0) {
                    setTextTo(textView, R.string.language_picker_section_suggested);
                } else if (this.mCountryMode) {
                    setTextTo(textView, R.string.region_picker_section_all);
                } else {
                    setTextTo(textView, R.string.language_picker_section_all);
                }
                Locale locale = this.mDisplayLocale;
                if (locale == null) {
                    locale = Locale.getDefault();
                }
                textView.setTextLocale(locale);
                break;
            default:
                if (!(convertView instanceof ViewGroup)) {
                    convertView = this.mInflater.inflate(R.layout.language_picker_item, parent, false);
                }
                TextView text = (TextView) convertView.findViewById(R.id.locale);
                LocaleStore.LocaleInfo item = (LocaleStore.LocaleInfo) getItem(position);
                text.setText(item.getLabel(this.mCountryMode));
                text.setTextLocale(item.getLocale());
                text.setContentDescription(item.getContentDescription(this.mCountryMode));
                if (this.mCountryMode) {
                    int layoutDir = TextUtils.getLayoutDirectionFromLocale(item.getParent());
                    convertView.setLayoutDirection(layoutDir);
                    if (layoutDir == 1) {
                        i = 4;
                    } else {
                        i = 3;
                    }
                    text.setTextDirection(i);
                    break;
                }
                break;
        }
        return convertView;
    }

    private boolean showHeaders() {
        int i;
        return ((this.mCountryMode && this.mLocaleOptions.size() < 6) || (i = this.mSuggestionCount) == 0 || i == this.mLocaleOptions.size()) ? false : true;
    }

    public void sort(LocaleHelper.LocaleInfoComparator comp) {
        Collections.sort(this.mLocaleOptions, comp);
    }

    /* loaded from: classes4.dex */
    class FilterByNativeAndUiNames extends Filter {
        FilterByNativeAndUiNames() {
        }

        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence prefix) {
            Filter.FilterResults results = new Filter.FilterResults();
            if (SuggestedLocaleAdapter.this.mOriginalLocaleOptions == null) {
                SuggestedLocaleAdapter.this.mOriginalLocaleOptions = new ArrayList(SuggestedLocaleAdapter.this.mLocaleOptions);
            }
            ArrayList<LocaleStore.LocaleInfo> values = new ArrayList<>(SuggestedLocaleAdapter.this.mOriginalLocaleOptions);
            if (prefix == null || prefix.length() == 0) {
                results.values = values;
                results.count = values.size();
            } else {
                Locale locale = Locale.getDefault();
                String prefixString = LocaleHelper.normalizeForSearch(prefix.toString(), locale);
                int count = values.size();
                ArrayList<LocaleStore.LocaleInfo> newValues = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    LocaleStore.LocaleInfo value = values.get(i);
                    String nameToCheck = LocaleHelper.normalizeForSearch(value.getFullNameInUiLanguage(), locale);
                    String nativeNameToCheck = LocaleHelper.normalizeForSearch(value.getFullNameNative(), locale);
                    if (wordMatches(nativeNameToCheck, prefixString) || wordMatches(nameToCheck, prefixString)) {
                        newValues.add(value);
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        boolean wordMatches(String valueText, String prefixString) {
            if (valueText.startsWith(prefixString)) {
                return true;
            }
            String[] words = valueText.split(" ");
            for (String word : words) {
                if (word.startsWith(prefixString)) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
            SuggestedLocaleAdapter.this.mLocaleOptions = (ArrayList) results.values;
            SuggestedLocaleAdapter.this.mSuggestionCount = 0;
            Iterator it = SuggestedLocaleAdapter.this.mLocaleOptions.iterator();
            while (it.hasNext()) {
                LocaleStore.LocaleInfo li = (LocaleStore.LocaleInfo) it.next();
                if (li.isSuggested()) {
                    SuggestedLocaleAdapter.access$208(SuggestedLocaleAdapter.this);
                }
            }
            if (results.count > 0) {
                SuggestedLocaleAdapter.this.notifyDataSetChanged();
            } else {
                SuggestedLocaleAdapter.this.notifyDataSetInvalidated();
            }
        }
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        return new FilterByNativeAndUiNames();
    }
}
