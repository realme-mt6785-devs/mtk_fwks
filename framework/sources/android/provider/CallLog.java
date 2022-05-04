package android.provider;

import android.annotation.SystemApi;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.UserInfo;
import android.database.Cursor;
import android.location.Country;
import android.location.CountryDetector;
import android.net.Uri;
import android.os.OutcomeReceiver;
import android.os.ParcelFileDescriptor;
import android.os.ParcelableException;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.ContactsContract;
import android.telecom.CallerInfo;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import com.oplus.uifirst.IOplusUIFirstManager;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class CallLog {
    public static final String AUTHORITY = "call_log";
    public static final Uri CALL_COMPOSER_PICTURE_URI;
    public static final String CALL_COMPOSER_SEGMENT = "call_composer";
    public static final Uri CONTENT_URI;
    private static final String LOG_TAG = "CallLog";
    public static final String SHADOW_AUTHORITY = "call_log_shadow";
    public static final Uri SHADOW_CALL_COMPOSER_PICTURE_URI;
    private static final boolean VERBOSE_LOG = false;

    static {
        Uri parse = Uri.parse("content://call_log");
        CONTENT_URI = parse;
        Uri build = parse.buildUpon().appendPath(CALL_COMPOSER_SEGMENT).build();
        CALL_COMPOSER_PICTURE_URI = build;
        SHADOW_CALL_COMPOSER_PICTURE_URI = build.buildUpon().authority(SHADOW_AUTHORITY).build();
    }

    @SystemApi
    /* loaded from: classes2.dex */
    public static class CallComposerLoggingException extends Throwable {
        public static final int ERROR_INPUT_CLOSED = 3;
        public static final int ERROR_REMOTE_END_CLOSED = 1;
        public static final int ERROR_STORAGE_FULL = 2;
        public static final int ERROR_UNKNOWN = 0;
        private final int mErrorCode;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface CallComposerLoggingError {
        }

        public CallComposerLoggingException(int errorCode) {
            this.mErrorCode = errorCode;
        }

        public int getErrorCode() {
            return this.mErrorCode;
        }

        @Override // java.lang.Throwable
        public String toString() {
            String errorString;
            switch (this.mErrorCode) {
                case 0:
                    errorString = "UNKNOWN";
                    break;
                case 1:
                    errorString = "REMOTE_END_CLOSED";
                    break;
                case 2:
                    errorString = "STORAGE_FULL";
                    break;
                case 3:
                    errorString = "INPUT_CLOSED";
                    break;
                default:
                    errorString = "[[" + this.mErrorCode + "]]";
                    break;
            }
            return "CallComposerLoggingException: " + errorString;
        }
    }

    @SystemApi
    public static void storeCallComposerPicture(final Context context, final InputStream input, Executor executor, final OutcomeReceiver<Uri, CallComposerLoggingException> callback) {
        Objects.requireNonNull(context);
        Objects.requireNonNull(input);
        Objects.requireNonNull(executor);
        Objects.requireNonNull(callback);
        executor.execute(new Runnable() { // from class: android.provider.CallLog$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CallLog.lambda$storeCallComposerPicture$0(input, callback, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$storeCallComposerPicture$0(InputStream input, OutcomeReceiver callback, Context context) {
        UserManager userManager;
        Context context2 = context;
        ByteArrayOutputStream tmpOut = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (true) {
            try {
                int bytesRead = input.read(buffer);
                if (bytesRead < 0) {
                    break;
                }
                tmpOut.write(buffer, 0, bytesRead);
                context2 = context;
            } catch (IOException e) {
                Log.e(LOG_TAG, "IOException while reading call composer pic from input: " + e);
                callback.onError(new CallComposerLoggingException(3));
                return;
            }
        }
        byte[] picData = tmpOut.toByteArray();
        UserManager userManager2 = (UserManager) context2.getSystemService(UserManager.class);
        UserHandle user = context.getUser();
        UserHandle realUser = UserHandle.CURRENT.equals(user) ? Process.myUserHandle() : user;
        if (realUser != UserHandle.ALL) {
            Uri baseUri = userManager2.isUserUnlocked(realUser) ? CALL_COMPOSER_PICTURE_URI : SHADOW_CALL_COMPOSER_PICTURE_URI;
            Uri pictureInsertionUri = ContentProvider.maybeAddUserId(baseUri, realUser.getIdentifier());
            Log.i(LOG_TAG, "Inserting call composer for single user at " + pictureInsertionUri);
            try {
                Uri result = storeCallComposerPictureAtUri(context2, pictureInsertionUri, false, picData);
                callback.onResult(result);
            } catch (CallComposerLoggingException e2) {
                callback.onError(e2);
            }
        } else if (!userManager2.isUserUnlocked(UserHandle.SYSTEM)) {
            Uri pictureInsertionUri2 = ContentProvider.maybeAddUserId(SHADOW_CALL_COMPOSER_PICTURE_URI, UserHandle.SYSTEM.getIdentifier());
            Log.i(LOG_TAG, "Inserting call composer for all users, but system locked at " + pictureInsertionUri2);
            try {
                Uri result2 = storeCallComposerPictureAtUri(context2, pictureInsertionUri2, true, picData);
                callback.onResult(result2);
            } catch (CallComposerLoggingException e3) {
                callback.onError(e3);
            }
        } else {
            Uri systemPictureInsertionUri = ContentProvider.maybeAddUserId(CALL_COMPOSER_PICTURE_URI, UserHandle.SYSTEM.getIdentifier());
            try {
                Uri systemInsertedPicture = storeCallComposerPictureAtUri(context2, systemPictureInsertionUri, true, picData);
                Log.i(LOG_TAG, "Inserting call composer for all users, succeeded with system, result is " + systemInsertedPicture);
                Uri strippedInsertionUri = ContentProvider.getUriWithoutUserId(systemInsertedPicture);
                for (UserInfo u : userManager2.getAliveUsers()) {
                    UserHandle userHandle = u.getUserHandle();
                    if (!userHandle.isSystem() && Calls.shouldHaveSharedCallLogEntries(context2, userManager2, userHandle.getIdentifier())) {
                        if (!userManager2.isUserRunning(userHandle)) {
                            userManager = userManager2;
                        } else if (userManager2.isUserUnlocked(userHandle)) {
                            Uri insertionUri = ContentProvider.maybeAddUserId(strippedInsertionUri, userHandle.getIdentifier());
                            StringBuilder sb = new StringBuilder();
                            userManager = userManager2;
                            sb.append("Inserting call composer for all users, now on user ");
                            sb.append(userHandle);
                            sb.append(" inserting at ");
                            sb.append(insertionUri);
                            Log.i(LOG_TAG, sb.toString());
                            try {
                                storeCallComposerPictureAtUri(context2, insertionUri, false, picData);
                            } catch (CallComposerLoggingException e4) {
                                Log.e(LOG_TAG, "Error writing for user " + userHandle.getIdentifier() + ": " + e4);
                            }
                        } else {
                            userManager = userManager2;
                        }
                        context2 = context;
                        userManager2 = userManager;
                    }
                }
                callback.onResult(strippedInsertionUri);
            } catch (CallComposerLoggingException e5) {
                callback.onError(e5);
            }
        }
    }

    private static Uri storeCallComposerPictureAtUri(Context context, Uri insertionUri, boolean forAllUsers, byte[] picData) throws CallComposerLoggingException {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Calls.ADD_FOR_ALL_USERS, Integer.valueOf(forAllUsers ? 1 : 0));
            Uri pictureFileUri = context.getContentResolver().insert(insertionUri, cv);
            if (pictureFileUri != null) {
                try {
                    ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(pictureFileUri, "w");
                    FileOutputStream output = new FileOutputStream(pfd.getFileDescriptor());
                    try {
                        output.write(picData);
                        if (pfd != null) {
                            pfd.close();
                        }
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "Got IOException writing to remote end: " + e);
                        context.getContentResolver().delete(pictureFileUri, null);
                        throw new CallComposerLoggingException(1);
                    }
                } catch (FileNotFoundException e2) {
                    throw new CallComposerLoggingException(0);
                } catch (IOException e3) {
                    Log.e(LOG_TAG, "Got IOException closing remote descriptor: " + e3);
                }
                return pictureFileUri;
            }
            throw new CallComposerLoggingException(2);
        } catch (ParcelableException e4) {
            throw new CallComposerLoggingException(0);
        }
    }

    private static void sendCallComposerError(OutcomeReceiver<?, CallComposerLoggingException> cb, int error) {
        cb.onError(new CallComposerLoggingException(error));
    }

    /* loaded from: classes2.dex */
    public static class AddCallParams {
        private PhoneAccountHandle mAccountHandle;
        private boolean mAddForAllUsers;
        private int mCallBlockReason;
        private CharSequence mCallScreeningAppName;
        private String mCallScreeningComponentName;
        private int mCallType;
        private CallerInfo mCallerInfo;
        private long mDataUsage;
        private int mDuration;
        private int mFeatures;
        private boolean mIsRead;
        private double mLatitude;
        private double mLongitude;
        private long mMissedReason;
        private String mNumber;
        private Uri mPictureUri;
        private String mPostDialDigits;
        private int mPresentation;
        private int mPriority;
        private long mStart;
        private String mSubject;
        private UserHandle mUserToBeInsertedTo;
        private String mViaNumber;

        /* loaded from: classes2.dex */
        public static final class AddCallParametersBuilder {
            private PhoneAccountHandle mAccountHandle;
            private boolean mAddForAllUsers;
            private CharSequence mCallScreeningAppName;
            private String mCallScreeningComponentName;
            private CallerInfo mCallerInfo;
            private int mDuration;
            private int mFeatures;
            private boolean mIsRead;
            private String mNumber;
            private Uri mPictureUri;
            private String mPostDialDigits;
            private long mStart;
            private String mSubject;
            private UserHandle mUserToBeInsertedTo;
            private String mViaNumber;
            private int mPresentation = 3;
            private int mCallType = 1;
            private Long mDataUsage = Long.MIN_VALUE;
            private int mCallBlockReason = 0;
            private long mMissedReason = 0;
            private int mPriority = 0;
            private double mLatitude = Double.NaN;
            private double mLongitude = Double.NaN;

            public AddCallParametersBuilder setCallerInfo(CallerInfo callerInfo) {
                this.mCallerInfo = callerInfo;
                return this;
            }

            public AddCallParametersBuilder setNumber(String number) {
                this.mNumber = number;
                return this;
            }

            public AddCallParametersBuilder setPostDialDigits(String postDialDigits) {
                this.mPostDialDigits = postDialDigits;
                return this;
            }

            public AddCallParametersBuilder setViaNumber(String viaNumber) {
                this.mViaNumber = viaNumber;
                return this;
            }

            public AddCallParametersBuilder setPresentation(int presentation) {
                this.mPresentation = presentation;
                return this;
            }

            public AddCallParametersBuilder setCallType(int callType) {
                this.mCallType = callType;
                return this;
            }

            public AddCallParametersBuilder setFeatures(int features) {
                this.mFeatures = features;
                return this;
            }

            public AddCallParametersBuilder setAccountHandle(PhoneAccountHandle accountHandle) {
                this.mAccountHandle = accountHandle;
                return this;
            }

            public AddCallParametersBuilder setStart(long start) {
                this.mStart = start;
                return this;
            }

            public AddCallParametersBuilder setDuration(int duration) {
                this.mDuration = duration;
                return this;
            }

            public AddCallParametersBuilder setDataUsage(long dataUsage) {
                this.mDataUsage = Long.valueOf(dataUsage);
                return this;
            }

            public AddCallParametersBuilder setAddForAllUsers(boolean addForAllUsers) {
                this.mAddForAllUsers = addForAllUsers;
                return this;
            }

            public AddCallParametersBuilder setUserToBeInsertedTo(UserHandle userToBeInsertedTo) {
                this.mUserToBeInsertedTo = userToBeInsertedTo;
                return this;
            }

            public AddCallParametersBuilder setIsRead(boolean isRead) {
                this.mIsRead = isRead;
                return this;
            }

            public AddCallParametersBuilder setCallBlockReason(int callBlockReason) {
                this.mCallBlockReason = callBlockReason;
                return this;
            }

            public AddCallParametersBuilder setCallScreeningAppName(CharSequence callScreeningAppName) {
                this.mCallScreeningAppName = callScreeningAppName;
                return this;
            }

            public AddCallParametersBuilder setCallScreeningComponentName(String callScreeningComponentName) {
                this.mCallScreeningComponentName = callScreeningComponentName;
                return this;
            }

            public AddCallParametersBuilder setMissedReason(long missedReason) {
                this.mMissedReason = missedReason;
                return this;
            }

            public AddCallParametersBuilder setPriority(int priority) {
                this.mPriority = priority;
                return this;
            }

            public AddCallParametersBuilder setSubject(String subject) {
                this.mSubject = subject;
                return this;
            }

            public AddCallParametersBuilder setLatitude(double latitude) {
                this.mLatitude = latitude;
                return this;
            }

            public AddCallParametersBuilder setLongitude(double longitude) {
                this.mLongitude = longitude;
                return this;
            }

            public AddCallParametersBuilder setPictureUri(Uri pictureUri) {
                this.mPictureUri = pictureUri;
                return this;
            }

            public AddCallParams build() {
                return new AddCallParams(this.mCallerInfo, this.mNumber, this.mPostDialDigits, this.mViaNumber, this.mPresentation, this.mCallType, this.mFeatures, this.mAccountHandle, this.mStart, this.mDuration, this.mDataUsage.longValue(), this.mAddForAllUsers, this.mUserToBeInsertedTo, this.mIsRead, this.mCallBlockReason, this.mCallScreeningAppName, this.mCallScreeningComponentName, this.mMissedReason, this.mPriority, this.mSubject, this.mLatitude, this.mLongitude, this.mPictureUri);
            }
        }

        private AddCallParams(CallerInfo callerInfo, String number, String postDialDigits, String viaNumber, int presentation, int callType, int features, PhoneAccountHandle accountHandle, long start, int duration, long dataUsage, boolean addForAllUsers, UserHandle userToBeInsertedTo, boolean isRead, int callBlockReason, CharSequence callScreeningAppName, String callScreeningComponentName, long missedReason, int priority, String subject, double latitude, double longitude, Uri pictureUri) {
            this.mLatitude = Double.NaN;
            this.mLongitude = Double.NaN;
            this.mCallerInfo = callerInfo;
            this.mNumber = number;
            this.mPostDialDigits = postDialDigits;
            this.mViaNumber = viaNumber;
            this.mPresentation = presentation;
            this.mCallType = callType;
            this.mFeatures = features;
            this.mAccountHandle = accountHandle;
            this.mStart = start;
            this.mDuration = duration;
            this.mDataUsage = dataUsage;
            this.mAddForAllUsers = addForAllUsers;
            this.mUserToBeInsertedTo = userToBeInsertedTo;
            this.mIsRead = isRead;
            this.mCallBlockReason = callBlockReason;
            this.mCallScreeningAppName = callScreeningAppName;
            this.mCallScreeningComponentName = callScreeningComponentName;
            this.mMissedReason = missedReason;
            this.mPriority = priority;
            this.mSubject = subject;
            this.mLatitude = latitude;
            this.mLongitude = longitude;
            this.mPictureUri = pictureUri;
        }
    }

    /* loaded from: classes2.dex */
    public static class Calls implements BaseColumns {
        public static final String ADD_FOR_ALL_USERS = "add_for_all_users";
        public static final String ALLOW_VOICEMAILS_PARAM_KEY = "allow_voicemails";
        public static final int ANSWERED_EXTERNALLY_TYPE = 7;
        public static final long AUTO_MISSED_EMERGENCY_CALL = 1;
        public static final long AUTO_MISSED_MAXIMUM_DIALING = 4;
        public static final long AUTO_MISSED_MAXIMUM_RINGING = 2;
        public static final int BLOCKED_TYPE = 6;
        public static final String BLOCK_REASON = "block_reason";
        public static final int BLOCK_REASON_BLOCKED_NUMBER = 3;
        public static final int BLOCK_REASON_CALL_SCREENING_SERVICE = 1;
        public static final int BLOCK_REASON_DIRECT_TO_VOICEMAIL = 2;
        public static final int BLOCK_REASON_NOT_BLOCKED = 0;
        public static final int BLOCK_REASON_NOT_IN_CONTACTS = 7;
        public static final int BLOCK_REASON_PAY_PHONE = 6;
        public static final int BLOCK_REASON_RESTRICTED_NUMBER = 5;
        public static final int BLOCK_REASON_UNKNOWN_NUMBER = 4;
        public static final String CACHED_FORMATTED_NUMBER = "formatted_number";
        public static final String CACHED_LOOKUP_URI = "lookup_uri";
        public static final String CACHED_MATCHED_NUMBER = "matched_number";
        public static final String CACHED_NAME = "name";
        public static final String CACHED_NORMALIZED_NUMBER = "normalized_number";
        public static final String CACHED_NUMBER_LABEL = "numberlabel";
        public static final String CACHED_NUMBER_TYPE = "numbertype";
        public static final String CACHED_PHOTO_ID = "photo_id";
        public static final String CACHED_PHOTO_URI = "photo_uri";
        public static final String CALL_SCREENING_APP_NAME = "call_screening_app_name";
        public static final String CALL_SCREENING_COMPONENT_NAME = "call_screening_component_name";
        public static final String COMPOSER_PHOTO_URI = "composer_photo_uri";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/calls";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/calls";
        public static final Uri CONTENT_URI;
        private static final Uri CONTENT_URI_LIMIT_1;
        public static final Uri CONTENT_URI_WITH_VOICEMAIL;
        public static final String COUNTRY_ISO = "countryiso";
        public static final String DATA_USAGE = "data_usage";
        public static final String DATE = "date";
        public static final String DEFAULT_SORT_ORDER = "date DESC";
        public static final String DURATION = "duration";
        public static final String EXTRA_CALL_TYPE_FILTER = "android.provider.extra.CALL_TYPE_FILTER";
        public static final String FEATURES = "features";
        public static final int FEATURES_ASSISTED_DIALING_USED = 16;
        public static final int FEATURES_HD_CALL = 4;
        public static final int FEATURES_PULLED_EXTERNALLY = 2;
        public static final int FEATURES_RTT = 32;
        public static final int FEATURES_VIDEO = 1;
        public static final int FEATURES_VOLTE = 64;
        public static final int FEATURES_WIFI = 8;
        public static final String GEOCODED_LOCATION = "geocoded_location";
        public static final int INCOMING_TYPE = 1;
        public static final String IS_READ = "is_read";
        public static final String LAST_MODIFIED = "last_modified";
        public static final String LIMIT_PARAM_KEY = "limit";
        public static final String LOCATION = "location";
        public static final int LOW_RING_VOLUME = 0;
        private static final int MIN_DURATION_FOR_NORMALIZED_NUMBER_UPDATE_MS = 10000;
        public static final String MISSED_REASON = "missed_reason";
        public static final long MISSED_REASON_NOT_MISSED = 0;
        public static final int MISSED_TYPE = 3;
        public static final String NEW = "new";
        public static final String NUMBER = "number";
        public static final String NUMBER_PRESENTATION = "presentation";
        public static final String OFFSET_PARAM_KEY = "offset";
        public static final int OUTGOING_TYPE = 2;
        public static final String PHONE_ACCOUNT_ADDRESS = "phone_account_address";
        public static final String PHONE_ACCOUNT_COMPONENT_NAME = "subscription_component_name";
        public static final String PHONE_ACCOUNT_HIDDEN = "phone_account_hidden";
        public static final String PHONE_ACCOUNT_ID = "subscription_id";
        public static final String POST_DIAL_DIGITS = "post_dial_digits";
        public static final int PRESENTATION_ALLOWED = 1;
        public static final int PRESENTATION_PAYPHONE = 4;
        public static final int PRESENTATION_RESTRICTED = 2;
        public static final int PRESENTATION_UNKNOWN = 3;
        public static final String PRIORITY = "priority";
        public static final int PRIORITY_NORMAL = 0;
        public static final int PRIORITY_URGENT = 1;
        public static final int REJECTED_TYPE = 5;
        public static final long SHORT_RING_THRESHOLD = 5000;
        public static final String SUBJECT = "subject";
        public static final String SUB_ID = "sub_id";
        public static final String TRANSCRIPTION = "transcription";
        public static final String TRANSCRIPTION_STATE = "transcription_state";
        public static final String TYPE = "type";
        public static final long USER_MISSED_CALL_FILTERS_TIMEOUT = 4194304;
        public static final long USER_MISSED_CALL_SCREENING_SERVICE_SILENCED = 2097152;
        public static final long USER_MISSED_DND_MODE = 262144;
        public static final long USER_MISSED_LOW_RING_VOLUME = 524288;
        public static final long USER_MISSED_NO_ANSWER = 65536;
        public static final long USER_MISSED_NO_VIBRATE = 1048576;
        public static final long USER_MISSED_SHORT_RING = 131072;
        public static final String VIA_NUMBER = "via_number";
        public static final int VOICEMAIL_TYPE = 4;
        public static final String VOICEMAIL_URI = "voicemail_uri";
        public static final Uri SHADOW_CONTENT_URI = Uri.parse("content://call_log_shadow/calls");
        public static final Uri CONTENT_FILTER_URI = Uri.parse("content://call_log/calls/filter");

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface MissedReason {
        }

        static {
            Uri parse = Uri.parse("content://call_log/calls");
            CONTENT_URI = parse;
            CONTENT_URI_LIMIT_1 = parse.buildUpon().appendQueryParameter("limit", IOplusUIFirstManager.APP_START_ANIMATION).build();
            CONTENT_URI_WITH_VOICEMAIL = parse.buildUpon().appendQueryParameter(ALLOW_VOICEMAILS_PARAM_KEY, "true").build();
        }

        public static Uri addCall(CallerInfo ci, Context context, String number, int presentation, int callType, int features, PhoneAccountHandle accountHandle, long start, int duration, Long dataUsage, long missedReason) {
            return addCall(ci, context, number, "", "", presentation, callType, features, accountHandle, start, duration, dataUsage, false, null, false, 0, null, null, missedReason);
        }

        public static Uri addCall(CallerInfo ci, Context context, String number, String postDialDigits, String viaNumber, int presentation, int callType, int features, PhoneAccountHandle accountHandle, long start, int duration, Long dataUsage, boolean addForAllUsers, UserHandle userToBeInsertedTo, long missedReason) {
            return addCall(ci, context, number, postDialDigits, viaNumber, presentation, callType, features, accountHandle, start, duration, dataUsage, addForAllUsers, userToBeInsertedTo, false, 0, null, null, missedReason);
        }

        public static Uri addCall(CallerInfo ci, Context context, String number, String postDialDigits, String viaNumber, int presentation, int callType, int features, PhoneAccountHandle accountHandle, long start, int duration, Long dataUsage, boolean addForAllUsers, UserHandle userToBeInsertedTo, boolean isRead, int callBlockReason, CharSequence callScreeningAppName, String callScreeningComponentName, long missedReason) {
            AddCallParams.AddCallParametersBuilder builder = new AddCallParams.AddCallParametersBuilder();
            builder.setCallerInfo(ci);
            builder.setNumber(number);
            builder.setPostDialDigits(postDialDigits);
            builder.setViaNumber(viaNumber);
            builder.setPresentation(presentation);
            builder.setCallType(callType);
            builder.setFeatures(features);
            builder.setAccountHandle(accountHandle);
            builder.setStart(start);
            builder.setDuration(duration);
            builder.setDataUsage(dataUsage == null ? Long.MIN_VALUE : dataUsage.longValue());
            builder.setAddForAllUsers(addForAllUsers);
            builder.setUserToBeInsertedTo(userToBeInsertedTo);
            builder.setIsRead(isRead);
            builder.setCallBlockReason(callBlockReason);
            builder.setCallScreeningAppName(callScreeningAppName);
            builder.setCallScreeningComponentName(callScreeningComponentName);
            builder.setMissedReason(missedReason);
            return addCall(context, builder.build());
        }

        /* JADX WARN: Removed duplicated region for block: B:106:0x0217 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0047  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x00d0  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0113  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x016f  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x019c  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x01d1  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x026a  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x0330  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static android.net.Uri addCall(android.content.Context r25, android.provider.CallLog.AddCallParams r26) {
            /*
                Method dump skipped, instructions count: 871
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.provider.CallLog.Calls.addCall(android.content.Context, android.provider.CallLog$AddCallParams):android.net.Uri");
        }

        private static String charSequenceToString(CharSequence sequence) {
            if (sequence == null) {
                return null;
            }
            return sequence.toString();
        }

        public static boolean shouldHaveSharedCallLogEntries(Context context, UserManager userManager, int userId) {
            UserInfo userInfo;
            return !userManager.hasUserRestriction(UserManager.DISALLOW_OUTGOING_CALLS, UserHandle.of(userId)) && (userInfo = userManager.getUserInfo(userId)) != null && !userInfo.isManagedProfile();
        }

        public static String getLastOutgoingCall(Context context) {
            ContentResolver resolver = context.getContentResolver();
            Cursor c = null;
            try {
                c = resolver.query(CONTENT_URI_LIMIT_1, new String[]{"number"}, "type = 2", null, "date DESC");
                if (c != null && c.moveToFirst()) {
                    return c.getString(0);
                }
                if (c != null) {
                    c.close();
                }
                return "";
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }

        private static Uri addEntryAndRemoveExpiredEntries(Context context, UserManager userManager, UserHandle user, ContentValues values) {
            ContentResolver resolver = context.getContentResolver();
            Uri uri = ContentProvider.maybeAddUserId(userManager.isUserUnlocked(user) ? CONTENT_URI : SHADOW_CONTENT_URI, user.getIdentifier());
            try {
                Uri result = resolver.insert(uri, values);
                if (result != null) {
                    String lastPathSegment = result.getLastPathSegment();
                    if (lastPathSegment != null && lastPathSegment.equals("0")) {
                        Log.w(CallLog.LOG_TAG, "Failed to insert into call log due to appops denial; resultUri=" + result);
                    }
                } else {
                    Log.w(CallLog.LOG_TAG, "Failed to insert into call log; null result uri.");
                }
                if (!values.containsKey("subscription_id") || TextUtils.isEmpty(values.getAsString("subscription_id")) || !values.containsKey("subscription_component_name") || TextUtils.isEmpty(values.getAsString("subscription_component_name"))) {
                    resolver.delete(uri, "_id IN (SELECT _id FROM calls ORDER BY date DESC LIMIT -1 OFFSET 500)", null);
                } else {
                    resolver.delete(uri, "_id IN (SELECT _id FROM calls WHERE subscription_component_name = ? AND subscription_id = ? ORDER BY date DESC LIMIT -1 OFFSET 500)", new String[]{values.getAsString("subscription_component_name"), values.getAsString("subscription_id")});
                }
                return result;
            } catch (IllegalArgumentException e) {
                Log.w(CallLog.LOG_TAG, "Failed to insert calllog", e);
                return null;
            }
        }

        private static Uri maybeInsertLocation(AddCallParams params, ContentResolver resolver, UserHandle user) {
            if (Double.isNaN(params.mLatitude) || Double.isNaN(params.mLongitude)) {
                return null;
            }
            ContentValues locationValues = new ContentValues();
            locationValues.put("latitude", Double.valueOf(params.mLatitude));
            locationValues.put("longitude", Double.valueOf(params.mLongitude));
            Uri locationUri = ContentProvider.maybeAddUserId(Locations.CONTENT_URI, user.getIdentifier());
            try {
                return resolver.insert(locationUri, locationValues);
            } catch (SecurityException e) {
                Log.w(CallLog.LOG_TAG, "Skipping inserting location because caller lacks ACCESS_FINE_LOCATION.");
                return null;
            }
        }

        private static void updateDataUsageStatForData(ContentResolver resolver, String dataId) {
            Uri feedbackUri = ContactsContract.DataUsageFeedback.FEEDBACK_URI.buildUpon().appendPath(dataId).appendQueryParameter("type", "call").build();
            resolver.update(feedbackUri, new ContentValues(), null, null);
        }

        private static void updateNormalizedNumber(Context context, ContentResolver resolver, String dataId, String number) {
            if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(dataId)) {
                String countryIso = getCurrentCountryIso(context);
                if (!TextUtils.isEmpty(countryIso)) {
                    String normalizedNumber = PhoneNumberUtils.formatNumberToE164(number, countryIso);
                    if (!TextUtils.isEmpty(normalizedNumber)) {
                        ContentValues values = new ContentValues();
                        values.put("data4", normalizedNumber);
                        resolver.update(ContactsContract.Data.CONTENT_URI, values, "_id=?", new String[]{dataId});
                    }
                }
            }
        }

        private static int getLogNumberPresentation(String number, int presentation) {
            if (presentation == 2) {
                return presentation;
            }
            if (presentation == 4) {
                return presentation;
            }
            return (TextUtils.isEmpty(number) || presentation == 3) ? 3 : 1;
        }

        private static String getLogAccountAddress(Context context, PhoneAccountHandle accountHandle) {
            PhoneAccount account;
            Uri address;
            TelecomManager tm = null;
            try {
                tm = TelecomManager.from(context);
            } catch (UnsupportedOperationException e) {
            }
            if (tm == null || accountHandle == null || (account = tm.getPhoneAccount(accountHandle)) == null || (address = account.getSubscriptionAddress()) == null) {
                return null;
            }
            String accountAddress = address.getSchemeSpecificPart();
            return accountAddress;
        }

        private static String getCurrentCountryIso(Context context) {
            Country country;
            CountryDetector detector = (CountryDetector) context.getSystemService(Context.COUNTRY_DETECTOR);
            if (detector == null || (country = detector.detectCountry()) == null) {
                return null;
            }
            String countryIso = country.getCountryIso();
            return countryIso;
        }

        public static boolean isUserMissed(long missedReason) {
            return missedReason >= 65536;
        }
    }

    /* loaded from: classes2.dex */
    public static class Locations implements BaseColumns {
        public static final String AUTHORITY = "call_composer_locations";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/call_composer_location";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/call_composer_location";
        public static final Uri CONTENT_URI = Uri.parse("content://call_composer_locations");
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";

        private Locations() {
        }
    }
}
