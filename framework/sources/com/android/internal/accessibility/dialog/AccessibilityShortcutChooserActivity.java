package com.android.internal.accessibility.dialog;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.android.internal.accessibility.util.AccessibilityUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class AccessibilityShortcutChooserActivity extends Activity {
    private AlertDialog mMenuDialog;
    private AlertDialog mPermissionDialog;
    private ShortcutTargetAdapter mTargetAdapter;
    private final int mShortcutType = 1;
    private final List<AccessibilityTarget> mTargets = new ArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypedArray theme = getTheme().obtainStyledAttributes(R.styleable.Theme);
        if (!theme.getBoolean(38, false)) {
            requestWindowFeature(1);
        }
        this.mTargets.addAll(AccessibilityTargetHelper.getTargets(this, 1));
        this.mTargetAdapter = new ShortcutTargetAdapter(this.mTargets);
        AlertDialog createMenuDialog = createMenuDialog();
        this.mMenuDialog = createMenuDialog;
        createMenuDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.internal.accessibility.dialog.AccessibilityShortcutChooserActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                AccessibilityShortcutChooserActivity.this.lambda$onCreate$0$AccessibilityShortcutChooserActivity(dialogInterface);
            }
        });
        this.mMenuDialog.show();
    }

    public /* synthetic */ void lambda$onCreate$0$AccessibilityShortcutChooserActivity(DialogInterface dialog) {
        updateDialogListeners();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        this.mMenuDialog.dismiss();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTargetSelected(AdapterView<?> parent, View view, int position, long id) {
        AccessibilityTarget target = this.mTargets.get(position);
        target.onSelected();
        this.mMenuDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTargetChecked(AdapterView<?> parent, View view, int position, long id) {
        AccessibilityTarget target = this.mTargets.get(position);
        if (!(target instanceof AccessibilityServiceTarget) || target.isShortcutEnabled()) {
            target.onCheckedChanged(!target.isShortcutEnabled());
            this.mTargetAdapter.notifyDataSetChanged();
            return;
        }
        AlertDialog create = new AlertDialog.Builder(this).setView(AccessibilityTargetHelper.createEnableDialogContentView(this, (AccessibilityServiceTarget) target, new View.OnClickListener() { // from class: com.android.internal.accessibility.dialog.AccessibilityShortcutChooserActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccessibilityShortcutChooserActivity.this.lambda$onTargetChecked$1$AccessibilityShortcutChooserActivity(view2);
            }
        }, new View.OnClickListener() { // from class: com.android.internal.accessibility.dialog.AccessibilityShortcutChooserActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccessibilityShortcutChooserActivity.this.lambda$onTargetChecked$2$AccessibilityShortcutChooserActivity(view2);
            }
        })).create();
        this.mPermissionDialog = create;
        create.show();
    }

    public /* synthetic */ void lambda$onTargetChecked$1$AccessibilityShortcutChooserActivity(View v) {
        this.mPermissionDialog.dismiss();
        this.mTargetAdapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$onTargetChecked$2$AccessibilityShortcutChooserActivity(View v) {
        this.mPermissionDialog.dismiss();
    }

    private void onDoneButtonClicked() {
        this.mTargets.clear();
        this.mTargets.addAll(AccessibilityTargetHelper.getTargets(this, 1));
        if (this.mTargets.isEmpty()) {
            this.mMenuDialog.dismiss();
            return;
        }
        this.mTargetAdapter.setShortcutMenuMode(0);
        this.mTargetAdapter.notifyDataSetChanged();
        this.mMenuDialog.getButton(-1).setText(getString(com.android.internal.R.string.edit_accessibility_shortcut_menu_button));
        updateDialogListeners();
    }

    private void onEditButtonClicked() {
        this.mTargets.clear();
        this.mTargets.addAll(AccessibilityTargetHelper.getInstalledTargets(this, 1));
        this.mTargetAdapter.setShortcutMenuMode(1);
        this.mTargetAdapter.notifyDataSetChanged();
        this.mMenuDialog.getButton(-1).setText(getString(com.android.internal.R.string.done_accessibility_shortcut_menu_button));
        updateDialogListeners();
    }

    private void updateDialogListeners() {
        boolean isEditMenuMode = true;
        if (this.mTargetAdapter.getShortcutMenuMode() != 1) {
            isEditMenuMode = false;
        }
        this.mMenuDialog.setTitle(getString(isEditMenuMode ? com.android.internal.R.string.accessibility_edit_shortcut_menu_volume_title : com.android.internal.R.string.accessibility_select_shortcut_menu_title));
        this.mMenuDialog.getButton(-1).setOnClickListener(isEditMenuMode ? new View.OnClickListener() { // from class: com.android.internal.accessibility.dialog.AccessibilityShortcutChooserActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccessibilityShortcutChooserActivity.this.lambda$updateDialogListeners$3$AccessibilityShortcutChooserActivity(view);
            }
        } : new View.OnClickListener() { // from class: com.android.internal.accessibility.dialog.AccessibilityShortcutChooserActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccessibilityShortcutChooserActivity.this.lambda$updateDialogListeners$4$AccessibilityShortcutChooserActivity(view);
            }
        });
        this.mMenuDialog.getListView().setOnItemClickListener(isEditMenuMode ? new AdapterView.OnItemClickListener() { // from class: com.android.internal.accessibility.dialog.AccessibilityShortcutChooserActivity$$ExternalSyntheticLambda7
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                AccessibilityShortcutChooserActivity.this.onTargetChecked(adapterView, view, i, j);
            }
        } : new AdapterView.OnItemClickListener() { // from class: com.android.internal.accessibility.dialog.AccessibilityShortcutChooserActivity$$ExternalSyntheticLambda6
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                AccessibilityShortcutChooserActivity.this.onTargetSelected(adapterView, view, i, j);
            }
        });
    }

    public /* synthetic */ void lambda$updateDialogListeners$3$AccessibilityShortcutChooserActivity(View view) {
        onDoneButtonClicked();
    }

    public /* synthetic */ void lambda$updateDialogListeners$4$AccessibilityShortcutChooserActivity(View view) {
        onEditButtonClicked();
    }

    private AlertDialog createMenuDialog() {
        String dialogTitle = getString(com.android.internal.R.string.accessibility_select_shortcut_menu_title);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle(dialogTitle).setAdapter(this.mTargetAdapter, null).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.internal.accessibility.dialog.AccessibilityShortcutChooserActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                AccessibilityShortcutChooserActivity.this.lambda$createMenuDialog$5$AccessibilityShortcutChooserActivity(dialogInterface);
            }
        });
        if (AccessibilityUtils.isUserSetupCompleted(this)) {
            String positiveButtonText = getString(com.android.internal.R.string.edit_accessibility_shortcut_menu_button);
            builder.setPositiveButton(positiveButtonText, (DialogInterface.OnClickListener) null);
        }
        return builder.create();
    }

    public /* synthetic */ void lambda$createMenuDialog$5$AccessibilityShortcutChooserActivity(DialogInterface dialog) {
        finish();
    }
}
