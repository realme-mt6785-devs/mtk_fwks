package com.mediatek.boostfwk;

import com.mediatek.boostfwk.scenario.BasicScenario;
/* loaded from: classes4.dex */
public class BoostFwkManager {
    public static final int BOOST_BEGIN = 0;
    public static final int BOOST_END = 1;
    public static final int UX_FRAME = 2;
    public static final int UX_LAUNCH = 3;
    public static final int UX_SCROLL = 1;

    /* loaded from: classes4.dex */
    public class Scroll {
        public static final int HORIZONTAL = 3;
        public static final int INPUT_EVENT = 0;
        public static final int PREFILING = 1;
        public static final int VERTICAL = 2;

        public Scroll() {
        }
    }

    /* loaded from: classes4.dex */
    public class Draw {
        public static final int FRAME_DRAW = 0;
        public static final int FRAME_DRAW_STEP = 1;

        public Draw() {
        }
    }

    /* loaded from: classes4.dex */
    public class Launch {
        public static final int LAUNCH_COLD = 1;
        public static final int LAUNCH_HOT = 2;

        public Launch() {
        }
    }

    public void perfHint(BasicScenario scenario) {
    }
}
