package com.android.internal.os;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public class ProcessCpuTrackerExtPlugin {
    public static Class<?> TYPE = RefClass.load(ProcessCpuTrackerExtPlugin.class, "com.android.internal.os.ProcessCpuTrackerExtImpl");
    @MethodParams({ArrayList.class})
    public static RefConstructor<IProcessCpuTrackerExt> constructor;
}
