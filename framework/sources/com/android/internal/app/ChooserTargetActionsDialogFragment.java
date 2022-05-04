package com.android.internal.app;

import android.app.ActivityManager;
import android.app.DialogFragment;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.app.ChooserTargetActionsDialogFragment;
import com.android.internal.app.ResolverListAdapter;
import com.android.internal.app.chooser.DisplayResolveInfo;
import com.android.internal.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
/* loaded from: classes4.dex */
public class ChooserTargetActionsDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static final String TARGET_INFOS_KEY = "target_infos";
    public static final String USER_HANDLE_KEY = "user_handle";
    protected ArrayList<DisplayResolveInfo> mTargetInfos = new ArrayList<>();
    protected UserHandle mUserHandle;

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            setStateFromBundle(savedInstanceState);
        } else {
            setStateFromBundle(getArguments());
        }
    }

    void setStateFromBundle(Bundle b) {
        this.mTargetInfos = (ArrayList) b.get(TARGET_INFOS_KEY);
        this.mUserHandle = (UserHandle) b.get(USER_HANDLE_KEY);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(USER_HANDLE_KEY, this.mUserHandle);
        outState.putParcelableArrayList(TARGET_INFOS_KEY, this.mTargetInfos);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            setStateFromBundle(savedInstanceState);
        } else {
            setStateFromBundle(getArguments());
        }
        Optional.of(getDialog()).map(ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda2.INSTANCE).ifPresent(ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda0.INSTANCE);
        List<Pair<Drawable, CharSequence>> items = (List) this.mTargetInfos.stream().map(new Function() { // from class: com.android.internal.app.ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ChooserTargetActionsDialogFragment.this.lambda$onCreateView$1$ChooserTargetActionsDialogFragment((DisplayResolveInfo) obj);
            }
        }).collect(Collectors.toList());
        View v = inflater.inflate(R.layout.chooser_dialog, container, false);
        TextView title = (TextView) v.findViewById(16908310);
        ImageView icon = (ImageView) v.findViewById(16908294);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.listContainer);
        ResolverListAdapter.ResolveInfoPresentationGetter pg = getProvidingAppPresentationGetter();
        title.setText(pg.getLabel());
        icon.setImageDrawable(pg.getIcon(this.mUserHandle));
        rv.setAdapter(new VHAdapter(items));
        return v;
    }

    public /* synthetic */ Pair lambda$onCreateView$1$ChooserTargetActionsDialogFragment(DisplayResolveInfo dri) {
        return new Pair(getItemIcon(dri), getItemLabel(dri));
    }

    /* loaded from: classes4.dex */
    class VHAdapter extends RecyclerView.Adapter<VH> {
        List<Pair<Drawable, CharSequence>> mItems;

        VHAdapter(List<Pair<Drawable, CharSequence>> items) {
            this.mItems = items;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.android.internal.widget.RecyclerView.Adapter
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.chooser_dialog_item, parent, false));
        }

        public void onBindViewHolder(VH holder, int position) {
            holder.bind(this.mItems.get(position), position);
        }

        @Override // com.android.internal.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mItems.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class VH extends RecyclerView.ViewHolder {
        ImageView mIcon;
        TextView mLabel;

        VH(View itemView) {
            super(itemView);
            this.mLabel = (TextView) itemView.findViewById(R.id.text);
            this.mIcon = (ImageView) itemView.findViewById(16908294);
        }

        public void bind(Pair<Drawable, CharSequence> item, final int position) {
            this.mLabel.setText(item.second);
            if (item.first == null) {
                this.mIcon.setVisibility(8);
            } else {
                this.mIcon.setVisibility(0);
                this.mIcon.setImageDrawable(item.first);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.internal.app.ChooserTargetActionsDialogFragment$VH$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ChooserTargetActionsDialogFragment.VH.this.lambda$bind$0$ChooserTargetActionsDialogFragment$VH(position, view);
                }
            });
        }

        public /* synthetic */ void lambda$bind$0$ChooserTargetActionsDialogFragment$VH(int position, View v) {
            ChooserTargetActionsDialogFragment chooserTargetActionsDialogFragment = ChooserTargetActionsDialogFragment.this;
            chooserTargetActionsDialogFragment.onClick(chooserTargetActionsDialogFragment.getDialog(), position);
        }
    }

    public void onClick(DialogInterface dialog, int which) {
        pinComponent(this.mTargetInfos.get(which).getResolvedComponentName());
        ((ChooserActivity) getActivity()).handlePackagesChanged();
        dismiss();
    }

    private void pinComponent(ComponentName name) {
        SharedPreferences sp = ChooserActivity.getPinnedSharedPrefs(getContext());
        String key = name.flattenToString();
        boolean currentVal = sp.getBoolean(name.flattenToString(), false);
        if (currentVal) {
            sp.edit().remove(key).apply();
        } else {
            sp.edit().putBoolean(key, true).apply();
        }
    }

    private Drawable getPinIcon(boolean isPinned) {
        if (isPinned) {
            return getContext().getDrawable(R.drawable.ic_close);
        }
        return getContext().getDrawable(R.drawable.ic_chooser_pin_dialog);
    }

    private CharSequence getPinLabel(boolean isPinned, CharSequence targetLabel) {
        return isPinned ? getResources().getString(R.string.unpin_specific_target, targetLabel) : getResources().getString(R.string.pin_specific_target, targetLabel);
    }

    protected CharSequence getItemLabel(DisplayResolveInfo dri) {
        PackageManager pm = getContext().getPackageManager();
        return getPinLabel(dri.isPinned(), dri.getResolveInfo().loadLabel(pm));
    }

    protected Drawable getItemIcon(DisplayResolveInfo dri) {
        return getPinIcon(dri.isPinned());
    }

    private ResolverListAdapter.ResolveInfoPresentationGetter getProvidingAppPresentationGetter() {
        ActivityManager am = (ActivityManager) getContext().getSystemService("activity");
        int iconDpi = am.getLauncherLargeIconDensity();
        return new ResolverListAdapter.ResolveInfoPresentationGetter(getContext(), iconDpi, this.mTargetInfos.get(0).getResolveInfo());
    }
}
