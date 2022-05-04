package android.nfc;

import android.content.Context;
/* loaded from: classes2.dex */
public final class NfcManager {
    private NfcAdapter mAdapter;
    private final Context mContext;

    public NfcManager(Context context) {
        this.mContext = context.getApplicationContext();
        init();
    }

    private void init() {
        NfcAdapter adapter;
        Context context = this.mContext;
        if (context != null) {
            try {
                adapter = NfcAdapter.getNfcAdapter(context);
            } catch (UnsupportedOperationException e) {
                adapter = null;
            }
            this.mAdapter = adapter;
            return;
        }
        throw new IllegalArgumentException("context not associated with any application (using a mock context?)");
    }

    public NfcAdapter getDefaultAdapter() {
        NfcAdapter nfcAdapter = this.mAdapter;
        if (nfcAdapter == null) {
            init();
        } else {
            nfcAdapter.getAdapterState();
        }
        return this.mAdapter;
    }
}
