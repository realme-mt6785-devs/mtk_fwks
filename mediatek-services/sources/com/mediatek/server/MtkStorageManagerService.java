package com.mediatek.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.storage.VolumeInfo;
import android.util.Slog;
import com.android.server.StorageManagerService;
import com.google.android.collect.Lists;
import java.util.ArrayList;
/* loaded from: classes.dex */
class MtkStorageManagerService extends StorageManagerService {
    private static final Object FORMAT_LOCK = new Object();
    private static final String PRIVACY_PROTECTION_WIPE = "com.mediatek.ppl.NOTIFY_MOUNT_SERVICE_WIPE";
    private static final String PRIVACY_PROTECTION_WIPE_DONE = "com.mediatek.ppl.MOUNT_SERVICE_WIPE_RESPONSE";
    private static final String TAG = "MtkStorageManagerService";
    private final BroadcastReceiver mPrivacyProtectionReceiver = new BroadcastReceiver() { // from class: com.mediatek.server.MtkStorageManagerService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(MtkStorageManagerService.PRIVACY_PROTECTION_WIPE)) {
                Slog.i(MtkStorageManagerService.TAG, "Privacy Protection wipe!");
                MtkStorageManagerService.this.formatPhoneStorageAndExternalSDCard();
            }
        }
    };

    public MtkStorageManagerService(Context context) {
        super(context);
        registerPrivacyProtectionReceiver();
    }

    /* loaded from: classes.dex */
    public static class MtkStorageManagerServiceLifecycle extends StorageManagerService.Lifecycle {
        public MtkStorageManagerServiceLifecycle(Context context) {
            super(context);
        }

        public void onStart() {
            this.mStorageManagerService = new MtkStorageManagerService(getContext());
            publishBinderService("mount", this.mStorageManagerService);
            this.mStorageManagerService.start();
        }
    }

    private void registerPrivacyProtectionReceiver() {
        IntentFilter privacyProtectionFilter = new IntentFilter();
        privacyProtectionFilter.addAction(PRIVACY_PROTECTION_WIPE);
        this.mContext.registerReceiver(this.mPrivacyProtectionReceiver, privacyProtectionFilter, "com.mediatek.permission.MOUNT_SERVICE_WIPE", this.mHandler);
    }

    private ArrayList<VolumeInfo> findVolumeListNeedFormat() {
        Slog.i(TAG, "findVolumeListNeedFormat");
        ArrayList<VolumeInfo> tempVolumes = Lists.newArrayList();
        synchronized (this.mLock) {
            for (int i = 0; i < this.mVolumes.size(); i++) {
                VolumeInfo vol = (VolumeInfo) this.mVolumes.valueAt(i);
                if ((!isUSBOTG(vol) && vol.isVisible() && vol.getType() == 0) || (vol.getType() == 1 && vol.getDiskId() != null)) {
                    tempVolumes.add(vol);
                    Slog.i(TAG, "i will try to format volume= " + vol);
                }
            }
        }
        return tempVolumes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.mediatek.server.MtkStorageManagerService$1] */
    public void formatPhoneStorageAndExternalSDCard() {
        final ArrayList<VolumeInfo> tempVolumes = findVolumeListNeedFormat();
        new Thread() { // from class: com.mediatek.server.MtkStorageManagerService.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                synchronized (MtkStorageManagerService.FORMAT_LOCK) {
                    int unused = MtkStorageManagerService.this.mCurrentUserId;
                    for (int i = 0; i < tempVolumes.size(); i++) {
                        VolumeInfo vol = (VolumeInfo) tempVolumes.get(i);
                        if (vol.getType() != 1 || vol.getDiskId() == null) {
                            if (vol.getState() == 1) {
                                Slog.i(MtkStorageManagerService.TAG, "volume is checking, wait..");
                                int j = 0;
                                while (true) {
                                    if (j >= 30) {
                                        break;
                                    }
                                    try {
                                        sleep(1000L);
                                    } catch (InterruptedException ex) {
                                        Slog.e(MtkStorageManagerService.TAG, "Exception when wait!", ex);
                                    }
                                    if (vol.getState() != 1) {
                                        Slog.i(MtkStorageManagerService.TAG, "volume wait checking done!");
                                        break;
                                    }
                                    j++;
                                }
                            }
                            int j2 = vol.getState();
                            if (j2 == 2) {
                                Slog.i(MtkStorageManagerService.TAG, "volume is mounted, unmount firstly, volume=" + vol);
                                MtkStorageManagerService.this.unmount(vol.getId());
                                int j3 = 0;
                                while (true) {
                                    if (j3 >= 30) {
                                        break;
                                    }
                                    try {
                                        sleep(1000L);
                                    } catch (InterruptedException ex2) {
                                        Slog.e(MtkStorageManagerService.TAG, "Exception when wait!", ex2);
                                    }
                                    if (vol.getState() == 0) {
                                        Slog.i(MtkStorageManagerService.TAG, "wait unmount done!");
                                        break;
                                    }
                                    j3++;
                                }
                            }
                            MtkStorageManagerService.this.format(vol.getId());
                            Slog.d(MtkStorageManagerService.TAG, "format Succeed! volume=" + vol);
                        } else {
                            Slog.i(MtkStorageManagerService.TAG, "use partition public to format, volume= " + vol);
                            MtkStorageManagerService.this.partitionPublic(vol.getDiskId());
                            if (vol.getFsUuid() != null) {
                                MtkStorageManagerService.this.forgetVolume(vol.getFsUuid());
                            }
                        }
                    }
                    Intent intent = new Intent(MtkStorageManagerService.PRIVACY_PROTECTION_WIPE_DONE);
                    MtkStorageManagerService.this.mContext.sendBroadcast(intent, "com.mediatek.permission.MOUNT_SERVICE_WIPE");
                    Slog.d(MtkStorageManagerService.TAG, "Privacy Protection wipe: send " + intent);
                }
            }
        }.start();
    }

    public boolean isUSBOTG(VolumeInfo vol) {
        String[] idSplit;
        String diskID = vol.getDiskId();
        if (diskID == null || (idSplit = diskID.split(":")) == null || idSplit.length != 2 || !idSplit[1].startsWith("8,")) {
            return false;
        }
        Slog.d(TAG, "this is a usb otg");
        return true;
    }
}
