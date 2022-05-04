package com.android.internal.widget;

import java.util.function.Consumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class ConversationLayout$$ExternalSyntheticLambda6 implements Consumer {
    public static final /* synthetic */ ConversationLayout$$ExternalSyntheticLambda6 INSTANCE = new ConversationLayout$$ExternalSyntheticLambda6();

    private /* synthetic */ ConversationLayout$$ExternalSyntheticLambda6() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((MessagingMessage) obj).removeMessage();
    }
}
