package com.android.internal.os;

import java.io.EOFException;
import java.util.ArrayList;
/* loaded from: classes4.dex */
class ZygoteArguments {
    boolean mAbiListQuery;
    String[] mAllowlistedDataInfoList;
    String[] mApiDenylistExemptions;
    String mAppDataDir;
    boolean mBindMountAppDataDirs;
    boolean mBindMountAppStorageDirs;
    boolean mBootCompleted;
    private boolean mCapabilitiesSpecified;
    long mEffectiveCapabilities;
    boolean mGidSpecified;
    int[] mGids;
    String mInstructionSet;
    String mInvokeWith;
    boolean mIsTopApp;
    String mNiceName;
    String mPackageName;
    long mPermittedCapabilities;
    boolean mPidQuery;
    String[] mPkgDataInfoList;
    String mPreloadApp;
    boolean mPreloadDefault;
    String mPreloadPackage;
    String mPreloadPackageCacheKey;
    String mPreloadPackageLibFileName;
    String mPreloadPackageLibs;
    ArrayList<int[]> mRLimits;
    String[] mRemainingArgs;
    int mRuntimeFlags;
    String mSeInfo;
    private boolean mSeInfoSpecified;
    boolean mStartChildZygote;
    int mTargetSdkVersion;
    private boolean mTargetSdkVersionSpecified;
    boolean mUidSpecified;
    boolean mUsapPoolEnabled;
    int mUid = 0;
    int mGid = 0;
    int mMountExternal = 0;
    boolean mUsapPoolStatusSpecified = false;
    int mHiddenApiAccessLogSampleRate = -1;
    int mHiddenApiAccessStatslogSampleRate = -1;
    long[] mDisabledCompatChanges = null;
    public final IZygoteArgumentsExt mZygoteArgumentsExt = ZygoteArgumentsExtPlugin.constructor.newInstance();

    private ZygoteArguments(ZygoteCommandBuffer args, int argCount) throws IllegalArgumentException, EOFException {
        parseArgs(args, argCount);
    }

    public static ZygoteArguments getInstance(ZygoteCommandBuffer args) throws IllegalArgumentException, EOFException {
        int argCount = args.getCount();
        if (argCount == 0) {
            return null;
        }
        return new ZygoteArguments(args, argCount);
    }

    private void parseArgs(ZygoteCommandBuffer args, int argCount) throws IllegalArgumentException, EOFException {
        int i;
        String unprocessedArg = null;
        int curArg = 0;
        boolean seenRuntimeArgs = false;
        boolean expectRuntimeArgs = true;
        while (true) {
            i = 0;
            if (curArg >= argCount) {
                break;
            }
            String arg = args.nextArg();
            if (arg.equals("--")) {
                curArg++;
                break;
            }
            if (!arg.startsWith("--setuid=")) {
                if (!arg.startsWith("--setgid=")) {
                    if (!arg.startsWith("--target-sdk-version=")) {
                        if (arg.equals("--runtime-args")) {
                            seenRuntimeArgs = true;
                        } else if (arg.startsWith("--runtime-flags=")) {
                            this.mRuntimeFlags = Integer.parseInt(getAssignmentValue(arg));
                        } else if (!arg.startsWith("--seinfo=")) {
                            if (!arg.startsWith("--capabilities=")) {
                                if (arg.startsWith("--rlimit=")) {
                                    String[] limitStrings = getAssignmentList(arg);
                                    if (limitStrings.length == 3) {
                                        int[] rlimitTuple = new int[limitStrings.length];
                                        for (int i2 = 0; i2 < limitStrings.length; i2++) {
                                            rlimitTuple[i2] = Integer.parseInt(limitStrings[i2]);
                                        }
                                        if (this.mRLimits == null) {
                                            this.mRLimits = new ArrayList<>();
                                        }
                                        this.mRLimits.add(rlimitTuple);
                                    } else {
                                        throw new IllegalArgumentException("--rlimit= should have 3 comma-delimited ints");
                                    }
                                } else if (!arg.startsWith("--setgroups=")) {
                                    if (!arg.equals("--invoke-with")) {
                                        if (!arg.startsWith("--nice-name=")) {
                                            if (arg.equals("--mount-external-default")) {
                                                this.mMountExternal = 1;
                                            } else if (arg.equals("--mount-external-installer")) {
                                                this.mMountExternal = 2;
                                            } else if (arg.equals("--mount-external-pass-through")) {
                                                this.mMountExternal = 3;
                                            } else if (arg.equals("--mount-external-android-writable")) {
                                                this.mMountExternal = 4;
                                            } else if (arg.equals("--mount-external-oplus-android-writable")) {
                                                this.mMountExternal = 5;
                                            } else if (arg.equals("--query-abi-list")) {
                                                this.mAbiListQuery = true;
                                            } else if (arg.equals("--get-pid")) {
                                                this.mPidQuery = true;
                                            } else if (arg.equals("--boot-completed")) {
                                                this.mBootCompleted = true;
                                            } else if (arg.startsWith("--instruction-set=")) {
                                                this.mInstructionSet = getAssignmentValue(arg);
                                            } else if (arg.startsWith("--app-data-dir=")) {
                                                this.mAppDataDir = getAssignmentValue(arg);
                                            } else if (arg.equals("--preload-app")) {
                                                curArg++;
                                                this.mPreloadApp = args.nextArg();
                                            } else if (arg.equals("--preload-package")) {
                                                curArg += 4;
                                                this.mPreloadPackage = args.nextArg();
                                                this.mPreloadPackageLibs = args.nextArg();
                                                this.mPreloadPackageLibFileName = args.nextArg();
                                                this.mPreloadPackageCacheKey = args.nextArg();
                                            } else if (arg.equals("--preload-default")) {
                                                this.mPreloadDefault = true;
                                                expectRuntimeArgs = false;
                                            } else if (arg.equals("--start-child-zygote")) {
                                                this.mStartChildZygote = true;
                                            } else if (arg.equals("--set-api-denylist-exemptions")) {
                                                this.mApiDenylistExemptions = new String[(argCount - curArg) - 1];
                                                curArg++;
                                                int i3 = 0;
                                                while (curArg < argCount) {
                                                    this.mApiDenylistExemptions[i3] = args.nextArg();
                                                    curArg++;
                                                    i3++;
                                                }
                                                expectRuntimeArgs = false;
                                            } else if (arg.startsWith("--hidden-api-log-sampling-rate=")) {
                                                String rateStr = getAssignmentValue(arg);
                                                try {
                                                    this.mHiddenApiAccessLogSampleRate = Integer.parseInt(rateStr);
                                                    expectRuntimeArgs = false;
                                                } catch (NumberFormatException nfe) {
                                                    throw new IllegalArgumentException("Invalid log sampling rate: " + rateStr, nfe);
                                                }
                                            } else if (arg.startsWith("--hidden-api-statslog-sampling-rate=")) {
                                                String rateStr2 = getAssignmentValue(arg);
                                                try {
                                                    this.mHiddenApiAccessStatslogSampleRate = Integer.parseInt(rateStr2);
                                                    expectRuntimeArgs = false;
                                                } catch (NumberFormatException nfe2) {
                                                    throw new IllegalArgumentException("Invalid statslog sampling rate: " + rateStr2, nfe2);
                                                }
                                            } else if (!arg.startsWith("--package-name=")) {
                                                if (arg.startsWith("--usap-pool-enabled=")) {
                                                    this.mUsapPoolStatusSpecified = true;
                                                    this.mUsapPoolEnabled = Boolean.parseBoolean(getAssignmentValue(arg));
                                                    expectRuntimeArgs = false;
                                                } else if (arg.startsWith(Zygote.START_AS_TOP_APP_ARG)) {
                                                    this.mIsTopApp = true;
                                                } else if (!arg.startsWith("--disabled-compat-changes=")) {
                                                    if (arg.startsWith(Zygote.PKG_DATA_INFO_MAP)) {
                                                        this.mPkgDataInfoList = getAssignmentList(arg);
                                                    } else if (arg.startsWith(Zygote.ALLOWLISTED_DATA_INFO_MAP)) {
                                                        this.mAllowlistedDataInfoList = getAssignmentList(arg);
                                                    } else if (arg.equals(Zygote.BIND_MOUNT_APP_STORAGE_DIRS)) {
                                                        this.mBindMountAppStorageDirs = true;
                                                    } else if (!arg.equals(Zygote.BIND_MOUNT_APP_DATA_DIRS)) {
                                                        if (!this.mZygoteArgumentsExt.canParseArg(arg)) {
                                                            unprocessedArg = arg;
                                                            break;
                                                        }
                                                        this.mZygoteArgumentsExt.doParseArg(arg);
                                                    } else {
                                                        this.mBindMountAppDataDirs = true;
                                                    }
                                                } else if (this.mDisabledCompatChanges == null) {
                                                    String[] params = getAssignmentList(arg);
                                                    int length = params.length;
                                                    this.mDisabledCompatChanges = new long[length];
                                                    for (int i4 = 0; i4 < length; i4++) {
                                                        this.mDisabledCompatChanges[i4] = Long.parseLong(params[i4]);
                                                    }
                                                } else {
                                                    throw new IllegalArgumentException("Duplicate arg specified");
                                                }
                                            } else if (this.mPackageName == null) {
                                                this.mPackageName = getAssignmentValue(arg);
                                            } else {
                                                throw new IllegalArgumentException("Duplicate arg specified");
                                            }
                                        } else if (this.mNiceName == null) {
                                            this.mNiceName = getAssignmentValue(arg);
                                        } else {
                                            throw new IllegalArgumentException("Duplicate arg specified");
                                        }
                                    } else if (this.mInvokeWith == null) {
                                        curArg++;
                                        try {
                                            this.mInvokeWith = args.nextArg();
                                        } catch (IndexOutOfBoundsException e) {
                                            throw new IllegalArgumentException("--invoke-with requires argument");
                                        }
                                    } else {
                                        throw new IllegalArgumentException("Duplicate arg specified");
                                    }
                                } else if (this.mGids == null) {
                                    String[] params2 = getAssignmentList(arg);
                                    this.mGids = new int[params2.length];
                                    for (int i5 = params2.length - 1; i5 >= 0; i5--) {
                                        this.mGids[i5] = Integer.parseInt(params2[i5]);
                                    }
                                } else {
                                    throw new IllegalArgumentException("Duplicate arg specified");
                                }
                            } else if (!this.mCapabilitiesSpecified) {
                                this.mCapabilitiesSpecified = true;
                                String capString = getAssignmentValue(arg);
                                String[] capStrings = capString.split(",", 2);
                                if (capStrings.length == 1) {
                                    long longValue = Long.decode(capStrings[0]).longValue();
                                    this.mEffectiveCapabilities = longValue;
                                    this.mPermittedCapabilities = longValue;
                                } else {
                                    this.mPermittedCapabilities = Long.decode(capStrings[0]).longValue();
                                    this.mEffectiveCapabilities = Long.decode(capStrings[1]).longValue();
                                }
                            } else {
                                throw new IllegalArgumentException("Duplicate arg specified");
                            }
                        } else if (!this.mSeInfoSpecified) {
                            this.mSeInfoSpecified = true;
                            this.mSeInfo = getAssignmentValue(arg);
                        } else {
                            throw new IllegalArgumentException("Duplicate arg specified");
                        }
                    } else if (!this.mTargetSdkVersionSpecified) {
                        this.mTargetSdkVersionSpecified = true;
                        this.mTargetSdkVersion = Integer.parseInt(getAssignmentValue(arg));
                    } else {
                        throw new IllegalArgumentException("Duplicate target-sdk-version specified");
                    }
                } else if (!this.mGidSpecified) {
                    this.mGidSpecified = true;
                    this.mGid = Integer.parseInt(getAssignmentValue(arg));
                } else {
                    throw new IllegalArgumentException("Duplicate arg specified");
                }
            } else if (!this.mUidSpecified) {
                this.mUidSpecified = true;
                this.mUid = Integer.parseInt(getAssignmentValue(arg));
            } else {
                throw new IllegalArgumentException("Duplicate arg specified");
            }
            curArg++;
        }
        if (this.mBootCompleted) {
            if (argCount > curArg) {
                throw new IllegalArgumentException("Unexpected arguments after --boot-completed");
            }
        } else if (this.mAbiListQuery || this.mPidQuery) {
            if (argCount > curArg) {
                throw new IllegalArgumentException("Unexpected arguments after --query-abi-list.");
            }
        } else if (this.mPreloadPackage != null) {
            if (argCount > curArg) {
                throw new IllegalArgumentException("Unexpected arguments after --preload-package.");
            }
        } else if (this.mPreloadApp != null) {
            if (argCount > curArg) {
                throw new IllegalArgumentException("Unexpected arguments after --preload-app.");
            }
        } else if (expectRuntimeArgs) {
            if (!seenRuntimeArgs) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected argument : ");
                sb.append(unprocessedArg == null ? args.nextArg() : unprocessedArg);
                throw new IllegalArgumentException(sb.toString());
            }
            String[] strArr = new String[argCount - curArg];
            this.mRemainingArgs = strArr;
            int i6 = 0;
            if (unprocessedArg != null) {
                strArr[0] = unprocessedArg;
                i6 = 0 + 1;
            }
            while (i6 < argCount - curArg) {
                this.mRemainingArgs[i6] = args.nextArg();
                i6++;
            }
        }
        if (this.mStartChildZygote) {
            boolean seenChildSocketArg = false;
            String[] strArr2 = this.mRemainingArgs;
            int length2 = strArr2.length;
            while (true) {
                if (i >= length2) {
                    break;
                } else if (strArr2[i].startsWith(Zygote.CHILD_ZYGOTE_SOCKET_NAME_ARG)) {
                    seenChildSocketArg = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!seenChildSocketArg) {
                throw new IllegalArgumentException("--start-child-zygote specified without --zygote-socket=");
            }
        }
    }

    private static String getAssignmentValue(String arg) {
        return arg.substring(arg.indexOf(61) + 1);
    }

    private static String[] getAssignmentList(String arg) {
        return getAssignmentValue(arg).split(",");
    }
}
