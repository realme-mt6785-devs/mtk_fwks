package android.os;

import android.content.IntentSender;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.os.IUserRestrictionsListener;
import android.os.UserManager;
import java.util.List;
/* loaded from: classes2.dex */
public interface IUserManager extends IInterface {
    void addUserRestrictionsListener(IUserRestrictionsListener iUserRestrictionsListener) throws RemoteException;

    boolean canAddMoreManagedProfiles(int i, boolean z) throws RemoteException;

    boolean canAddMoreProfilesToUser(String str, int i, boolean z) throws RemoteException;

    boolean canHaveRestrictedProfile(int i) throws RemoteException;

    void clearSeedAccountData() throws RemoteException;

    UserInfo createProfileForUserEvenWhenDisallowedWithThrow(String str, String str2, int i, int i2, String[] strArr) throws RemoteException;

    UserInfo createProfileForUserWithThrow(String str, String str2, int i, int i2, String[] strArr) throws RemoteException;

    UserInfo createRestrictedProfileWithThrow(String str, int i) throws RemoteException;

    UserInfo createUserWithThrow(String str, String str2, int i) throws RemoteException;

    void evictCredentialEncryptionKey(int i) throws RemoteException;

    UserInfo findCurrentGuestUser() throws RemoteException;

    Bundle getApplicationRestrictions(String str) throws RemoteException;

    Bundle getApplicationRestrictionsForUser(String str, int i) throws RemoteException;

    int getCredentialOwnerProfile(int i) throws RemoteException;

    Bundle getDefaultGuestRestrictions() throws RemoteException;

    String[] getPreInstallableSystemPackages(String str) throws RemoteException;

    UserInfo getPrimaryUser() throws RemoteException;

    int[] getProfileIds(int i, boolean z) throws RemoteException;

    UserInfo getProfileParent(int i) throws RemoteException;

    int getProfileParentId(int i) throws RemoteException;

    List<UserInfo> getProfiles(int i, boolean z) throws RemoteException;

    String getSeedAccountName() throws RemoteException;

    PersistableBundle getSeedAccountOptions() throws RemoteException;

    String getSeedAccountType() throws RemoteException;

    String getUserAccount(int i) throws RemoteException;

    int getUserBadgeColorResId(int i) throws RemoteException;

    int getUserBadgeDarkColorResId(int i) throws RemoteException;

    int getUserBadgeLabelResId(int i) throws RemoteException;

    int getUserBadgeNoBackgroundResId(int i) throws RemoteException;

    int getUserBadgeResId(int i) throws RemoteException;

    long getUserCreationTime(int i) throws RemoteException;

    int getUserHandle(int i) throws RemoteException;

    ParcelFileDescriptor getUserIcon(int i) throws RemoteException;

    int getUserIconBadgeResId(int i) throws RemoteException;

    UserInfo getUserInfo(int i) throws RemoteException;

    String getUserName() throws RemoteException;

    int getUserRestrictionSource(String str, int i) throws RemoteException;

    List<UserManager.EnforcingUser> getUserRestrictionSources(String str, int i) throws RemoteException;

    Bundle getUserRestrictions(int i) throws RemoteException;

    int getUserSerialNumber(int i) throws RemoteException;

    long getUserStartRealtime() throws RemoteException;

    long getUserUnlockRealtime() throws RemoteException;

    List<UserInfo> getUsers(boolean z, boolean z2, boolean z3) throws RemoteException;

    boolean hasBadge(int i) throws RemoteException;

    boolean hasBaseUserRestriction(String str, int i) throws RemoteException;

    boolean hasRestrictedProfiles() throws RemoteException;

    boolean hasUserRestriction(String str, int i) throws RemoteException;

    boolean hasUserRestrictionOnAnyUser(String str) throws RemoteException;

    boolean isCloneProfile(int i) throws RemoteException;

    boolean isDemoUser(int i) throws RemoteException;

    boolean isManagedProfile(int i) throws RemoteException;

    boolean isMediaSharedWithParent(int i) throws RemoteException;

    boolean isPreCreated(int i) throws RemoteException;

    boolean isProfile(int i) throws RemoteException;

    boolean isQuietModeEnabled(int i) throws RemoteException;

    boolean isRestricted() throws RemoteException;

    boolean isSameProfileGroup(int i, int i2) throws RemoteException;

    boolean isSettingRestrictedForUser(String str, int i, String str2, int i2) throws RemoteException;

    boolean isUserForeground(int i) throws RemoteException;

    boolean isUserNameSet(int i) throws RemoteException;

    boolean isUserOfType(int i, String str) throws RemoteException;

    boolean isUserRunning(int i) throws RemoteException;

    boolean isUserUnlocked(int i) throws RemoteException;

    boolean isUserUnlockingOrUnlocked(int i) throws RemoteException;

    boolean markGuestForDeletion(int i) throws RemoteException;

    UserInfo preCreateUserWithThrow(String str) throws RemoteException;

    boolean removeUser(int i) throws RemoteException;

    boolean removeUserEvenWhenDisallowed(int i) throws RemoteException;

    int removeUserOrSetEphemeral(int i, boolean z) throws RemoteException;

    boolean requestQuietModeEnabled(String str, boolean z, int i, IntentSender intentSender, int i2) throws RemoteException;

    void setApplicationRestrictions(String str, Bundle bundle, int i) throws RemoteException;

    void setDefaultGuestRestrictions(Bundle bundle) throws RemoteException;

    void setSeedAccountData(int i, String str, String str2, PersistableBundle persistableBundle, boolean z) throws RemoteException;

    void setUserAccount(int i, String str) throws RemoteException;

    void setUserAdmin(int i) throws RemoteException;

    void setUserEnabled(int i) throws RemoteException;

    void setUserIcon(int i, Bitmap bitmap) throws RemoteException;

    void setUserName(int i, String str) throws RemoteException;

    void setUserRestriction(String str, boolean z, int i) throws RemoteException;

    boolean someUserHasSeedAccount(String str, String str2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IUserManager {
        @Override // android.os.IUserManager
        public int getCredentialOwnerProfile(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getProfileParentId(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public UserInfo createUserWithThrow(String name, String userType, int flags) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public UserInfo preCreateUserWithThrow(String userType) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public UserInfo createProfileForUserWithThrow(String name, String userType, int flags, int userId, String[] disallowedPackages) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public UserInfo createRestrictedProfileWithThrow(String name, int parentUserHandle) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public String[] getPreInstallableSystemPackages(String userType) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public void setUserEnabled(int userId) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setUserAdmin(int userId) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void evictCredentialEncryptionKey(int userId) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public boolean removeUser(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean removeUserEvenWhenDisallowed(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public void setUserName(int userId, String name) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setUserIcon(int userId, Bitmap icon) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public ParcelFileDescriptor getUserIcon(int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public UserInfo getPrimaryUser() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public List<UserInfo> getUsers(boolean excludePartial, boolean excludeDying, boolean excludePreCreated) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public List<UserInfo> getProfiles(int userId, boolean enabledOnly) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public int[] getProfileIds(int userId, boolean enabledOnly) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public boolean canAddMoreProfilesToUser(String userType, int userId, boolean allowedToRemoveOne) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean canAddMoreManagedProfiles(int userId, boolean allowedToRemoveOne) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public UserInfo getProfileParent(int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public boolean isSameProfileGroup(int userId, int otherUserHandle) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isUserOfType(int userId, String userType) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public UserInfo getUserInfo(int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public String getUserAccount(int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public void setUserAccount(int userId, String accountName) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public long getUserCreationTime(int userId) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IUserManager
        public boolean isRestricted() throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean canHaveRestrictedProfile(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public int getUserSerialNumber(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserHandle(int userSerialNumber) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserRestrictionSource(String restrictionKey, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public List<UserManager.EnforcingUser> getUserRestrictionSources(String restrictionKey, int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public Bundle getUserRestrictions(int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public boolean hasBaseUserRestriction(String restrictionKey, int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean hasUserRestriction(String restrictionKey, int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean hasUserRestrictionOnAnyUser(String restrictionKey) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isSettingRestrictedForUser(String setting, int userId, String value, int callingUid) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public void addUserRestrictionsListener(IUserRestrictionsListener listener) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setUserRestriction(String key, boolean value, int userId) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public void setApplicationRestrictions(String packageName, Bundle restrictions, int userId) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public Bundle getApplicationRestrictions(String packageName) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public Bundle getApplicationRestrictionsForUser(String packageName, int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public void setDefaultGuestRestrictions(Bundle restrictions) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public Bundle getDefaultGuestRestrictions() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public int removeUserOrSetEphemeral(int userId, boolean evenWhenDisallowed) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public boolean markGuestForDeletion(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public UserInfo findCurrentGuestUser() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public boolean isQuietModeEnabled(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public void setSeedAccountData(int userId, String accountName, String accountType, PersistableBundle accountOptions, boolean persist) throws RemoteException {
        }

        @Override // android.os.IUserManager
        public String getSeedAccountName() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public String getSeedAccountType() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public PersistableBundle getSeedAccountOptions() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public void clearSeedAccountData() throws RemoteException {
        }

        @Override // android.os.IUserManager
        public boolean someUserHasSeedAccount(String accountName, String accountType) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isProfile(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isManagedProfile(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isCloneProfile(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isMediaSharedWithParent(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isDemoUser(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isPreCreated(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public UserInfo createProfileForUserEvenWhenDisallowedWithThrow(String name, String userType, int flags, int userId, String[] disallowedPackages) throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public boolean isUserUnlockingOrUnlocked(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public int getUserIconBadgeResId(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserBadgeResId(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserBadgeNoBackgroundResId(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserBadgeLabelResId(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserBadgeColorResId(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public int getUserBadgeDarkColorResId(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IUserManager
        public boolean hasBadge(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isUserUnlocked(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isUserRunning(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isUserForeground(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean isUserNameSet(int userId) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean hasRestrictedProfiles() throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public boolean requestQuietModeEnabled(String callingPackage, boolean enableQuietMode, int userId, IntentSender target, int flags) throws RemoteException {
            return false;
        }

        @Override // android.os.IUserManager
        public String getUserName() throws RemoteException {
            return null;
        }

        @Override // android.os.IUserManager
        public long getUserStartRealtime() throws RemoteException {
            return 0L;
        }

        @Override // android.os.IUserManager
        public long getUserUnlockRealtime() throws RemoteException {
            return 0L;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IUserManager {
        public static final String DESCRIPTOR = "android.os.IUserManager";
        static final int TRANSACTION_addUserRestrictionsListener = 40;
        static final int TRANSACTION_canAddMoreManagedProfiles = 21;
        static final int TRANSACTION_canAddMoreProfilesToUser = 20;
        static final int TRANSACTION_canHaveRestrictedProfile = 30;
        static final int TRANSACTION_clearSeedAccountData = 55;
        static final int TRANSACTION_createProfileForUserEvenWhenDisallowedWithThrow = 63;
        static final int TRANSACTION_createProfileForUserWithThrow = 5;
        static final int TRANSACTION_createRestrictedProfileWithThrow = 6;
        static final int TRANSACTION_createUserWithThrow = 3;
        static final int TRANSACTION_evictCredentialEncryptionKey = 10;
        static final int TRANSACTION_findCurrentGuestUser = 49;
        static final int TRANSACTION_getApplicationRestrictions = 43;
        static final int TRANSACTION_getApplicationRestrictionsForUser = 44;
        static final int TRANSACTION_getCredentialOwnerProfile = 1;
        static final int TRANSACTION_getDefaultGuestRestrictions = 46;
        static final int TRANSACTION_getPreInstallableSystemPackages = 7;
        static final int TRANSACTION_getPrimaryUser = 16;
        static final int TRANSACTION_getProfileIds = 19;
        static final int TRANSACTION_getProfileParent = 22;
        static final int TRANSACTION_getProfileParentId = 2;
        static final int TRANSACTION_getProfiles = 18;
        static final int TRANSACTION_getSeedAccountName = 52;
        static final int TRANSACTION_getSeedAccountOptions = 54;
        static final int TRANSACTION_getSeedAccountType = 53;
        static final int TRANSACTION_getUserAccount = 26;
        static final int TRANSACTION_getUserBadgeColorResId = 69;
        static final int TRANSACTION_getUserBadgeDarkColorResId = 70;
        static final int TRANSACTION_getUserBadgeLabelResId = 68;
        static final int TRANSACTION_getUserBadgeNoBackgroundResId = 67;
        static final int TRANSACTION_getUserBadgeResId = 66;
        static final int TRANSACTION_getUserCreationTime = 28;
        static final int TRANSACTION_getUserHandle = 32;
        static final int TRANSACTION_getUserIcon = 15;
        static final int TRANSACTION_getUserIconBadgeResId = 65;
        static final int TRANSACTION_getUserInfo = 25;
        static final int TRANSACTION_getUserName = 78;
        static final int TRANSACTION_getUserRestrictionSource = 33;
        static final int TRANSACTION_getUserRestrictionSources = 34;
        static final int TRANSACTION_getUserRestrictions = 35;
        static final int TRANSACTION_getUserSerialNumber = 31;
        static final int TRANSACTION_getUserStartRealtime = 79;
        static final int TRANSACTION_getUserUnlockRealtime = 80;
        static final int TRANSACTION_getUsers = 17;
        static final int TRANSACTION_hasBadge = 71;
        static final int TRANSACTION_hasBaseUserRestriction = 36;
        static final int TRANSACTION_hasRestrictedProfiles = 76;
        static final int TRANSACTION_hasUserRestriction = 37;
        static final int TRANSACTION_hasUserRestrictionOnAnyUser = 38;
        static final int TRANSACTION_isCloneProfile = 59;
        static final int TRANSACTION_isDemoUser = 61;
        static final int TRANSACTION_isManagedProfile = 58;
        static final int TRANSACTION_isMediaSharedWithParent = 60;
        static final int TRANSACTION_isPreCreated = 62;
        static final int TRANSACTION_isProfile = 57;
        static final int TRANSACTION_isQuietModeEnabled = 50;
        static final int TRANSACTION_isRestricted = 29;
        static final int TRANSACTION_isSameProfileGroup = 23;
        static final int TRANSACTION_isSettingRestrictedForUser = 39;
        static final int TRANSACTION_isUserForeground = 74;
        static final int TRANSACTION_isUserNameSet = 75;
        static final int TRANSACTION_isUserOfType = 24;
        static final int TRANSACTION_isUserRunning = 73;
        static final int TRANSACTION_isUserUnlocked = 72;
        static final int TRANSACTION_isUserUnlockingOrUnlocked = 64;
        static final int TRANSACTION_markGuestForDeletion = 48;
        static final int TRANSACTION_preCreateUserWithThrow = 4;
        static final int TRANSACTION_removeUser = 11;
        static final int TRANSACTION_removeUserEvenWhenDisallowed = 12;
        static final int TRANSACTION_removeUserOrSetEphemeral = 47;
        static final int TRANSACTION_requestQuietModeEnabled = 77;
        static final int TRANSACTION_setApplicationRestrictions = 42;
        static final int TRANSACTION_setDefaultGuestRestrictions = 45;
        static final int TRANSACTION_setSeedAccountData = 51;
        static final int TRANSACTION_setUserAccount = 27;
        static final int TRANSACTION_setUserAdmin = 9;
        static final int TRANSACTION_setUserEnabled = 8;
        static final int TRANSACTION_setUserIcon = 14;
        static final int TRANSACTION_setUserName = 13;
        static final int TRANSACTION_setUserRestriction = 41;
        static final int TRANSACTION_someUserHasSeedAccount = 56;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUserManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IUserManager)) {
                return new Proxy(obj);
            }
            return (IUserManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getCredentialOwnerProfile";
                case 2:
                    return "getProfileParentId";
                case 3:
                    return "createUserWithThrow";
                case 4:
                    return "preCreateUserWithThrow";
                case 5:
                    return "createProfileForUserWithThrow";
                case 6:
                    return "createRestrictedProfileWithThrow";
                case 7:
                    return "getPreInstallableSystemPackages";
                case 8:
                    return "setUserEnabled";
                case 9:
                    return "setUserAdmin";
                case 10:
                    return "evictCredentialEncryptionKey";
                case 11:
                    return "removeUser";
                case 12:
                    return "removeUserEvenWhenDisallowed";
                case 13:
                    return "setUserName";
                case 14:
                    return "setUserIcon";
                case 15:
                    return "getUserIcon";
                case 16:
                    return "getPrimaryUser";
                case 17:
                    return "getUsers";
                case 18:
                    return "getProfiles";
                case 19:
                    return "getProfileIds";
                case 20:
                    return "canAddMoreProfilesToUser";
                case 21:
                    return "canAddMoreManagedProfiles";
                case 22:
                    return "getProfileParent";
                case 23:
                    return "isSameProfileGroup";
                case 24:
                    return "isUserOfType";
                case 25:
                    return "getUserInfo";
                case 26:
                    return "getUserAccount";
                case 27:
                    return "setUserAccount";
                case 28:
                    return "getUserCreationTime";
                case 29:
                    return "isRestricted";
                case 30:
                    return "canHaveRestrictedProfile";
                case 31:
                    return "getUserSerialNumber";
                case 32:
                    return "getUserHandle";
                case 33:
                    return "getUserRestrictionSource";
                case 34:
                    return "getUserRestrictionSources";
                case 35:
                    return "getUserRestrictions";
                case 36:
                    return "hasBaseUserRestriction";
                case 37:
                    return "hasUserRestriction";
                case 38:
                    return "hasUserRestrictionOnAnyUser";
                case 39:
                    return "isSettingRestrictedForUser";
                case 40:
                    return "addUserRestrictionsListener";
                case 41:
                    return "setUserRestriction";
                case 42:
                    return "setApplicationRestrictions";
                case 43:
                    return "getApplicationRestrictions";
                case 44:
                    return "getApplicationRestrictionsForUser";
                case 45:
                    return "setDefaultGuestRestrictions";
                case 46:
                    return "getDefaultGuestRestrictions";
                case 47:
                    return "removeUserOrSetEphemeral";
                case 48:
                    return "markGuestForDeletion";
                case 49:
                    return "findCurrentGuestUser";
                case 50:
                    return "isQuietModeEnabled";
                case 51:
                    return "setSeedAccountData";
                case 52:
                    return "getSeedAccountName";
                case 53:
                    return "getSeedAccountType";
                case 54:
                    return "getSeedAccountOptions";
                case 55:
                    return "clearSeedAccountData";
                case 56:
                    return "someUserHasSeedAccount";
                case 57:
                    return "isProfile";
                case 58:
                    return "isManagedProfile";
                case 59:
                    return "isCloneProfile";
                case 60:
                    return "isMediaSharedWithParent";
                case 61:
                    return "isDemoUser";
                case 62:
                    return "isPreCreated";
                case 63:
                    return "createProfileForUserEvenWhenDisallowedWithThrow";
                case 64:
                    return "isUserUnlockingOrUnlocked";
                case 65:
                    return "getUserIconBadgeResId";
                case 66:
                    return "getUserBadgeResId";
                case 67:
                    return "getUserBadgeNoBackgroundResId";
                case 68:
                    return "getUserBadgeLabelResId";
                case 69:
                    return "getUserBadgeColorResId";
                case 70:
                    return "getUserBadgeDarkColorResId";
                case 71:
                    return "hasBadge";
                case 72:
                    return "isUserUnlocked";
                case 73:
                    return "isUserRunning";
                case 74:
                    return "isUserForeground";
                case 75:
                    return "isUserNameSet";
                case 76:
                    return "hasRestrictedProfiles";
                case 77:
                    return "requestQuietModeEnabled";
                case 78:
                    return "getUserName";
                case 79:
                    return "getUserStartRealtime";
                case 80:
                    return "getUserUnlockRealtime";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bitmap _arg1;
            boolean _arg0;
            boolean _arg12;
            Bundle _arg13;
            Bundle _arg02;
            PersistableBundle _arg3;
            boolean _arg4;
            boolean _arg14;
            IntentSender _arg32;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg15 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg03 = data.readInt();
                            int _result = getCredentialOwnerProfile(_arg03);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg04 = data.readInt();
                            int _result2 = getProfileParentId(_arg04);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            String _arg16 = data.readString();
                            int _arg2 = data.readInt();
                            UserInfo _result3 = createUserWithThrow(_arg05, _arg16, _arg2);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            UserInfo _result4 = preCreateUserWithThrow(_arg06);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            String _arg17 = data.readString();
                            int _arg22 = data.readInt();
                            int _arg33 = data.readInt();
                            String[] _arg42 = data.createStringArray();
                            UserInfo _result5 = createProfileForUserWithThrow(_arg07, _arg17, _arg22, _arg33, _arg42);
                            reply.writeNoException();
                            if (_result5 != null) {
                                reply.writeInt(1);
                                _result5.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            UserInfo _result6 = createRestrictedProfileWithThrow(_arg08, data.readInt());
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            String[] _result7 = getPreInstallableSystemPackages(_arg09);
                            reply.writeNoException();
                            reply.writeStringArray(_result7);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            setUserEnabled(_arg010);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            setUserAdmin(_arg011);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            evictCredentialEncryptionKey(_arg012);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            boolean removeUser = removeUser(_arg013);
                            reply.writeNoException();
                            reply.writeInt(removeUser ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            boolean removeUserEvenWhenDisallowed = removeUserEvenWhenDisallowed(_arg014);
                            reply.writeNoException();
                            reply.writeInt(removeUserEvenWhenDisallowed ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            setUserName(_arg015, data.readString());
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg016 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = Bitmap.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            setUserIcon(_arg016, _arg1);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg017 = data.readInt();
                            ParcelFileDescriptor _result8 = getUserIcon(_arg017);
                            reply.writeNoException();
                            if (_result8 != null) {
                                reply.writeInt(1);
                                _result8.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            UserInfo _result9 = getPrimaryUser();
                            reply.writeNoException();
                            if (_result9 != null) {
                                reply.writeInt(1);
                                _result9.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            } else {
                                _arg0 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = true;
                            } else {
                                _arg12 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg15 = true;
                            }
                            List<UserInfo> _result10 = getUsers(_arg0, _arg12, _arg15);
                            reply.writeNoException();
                            reply.writeTypedList(_result10);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg018 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg15 = true;
                            }
                            List<UserInfo> _result11 = getProfiles(_arg018, _arg15);
                            reply.writeNoException();
                            reply.writeTypedList(_result11);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg019 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg15 = true;
                            }
                            int[] _result12 = getProfileIds(_arg019, _arg15);
                            reply.writeNoException();
                            reply.writeIntArray(_result12);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg020 = data.readString();
                            int _arg18 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg15 = true;
                            }
                            boolean canAddMoreProfilesToUser = canAddMoreProfilesToUser(_arg020, _arg18, _arg15);
                            reply.writeNoException();
                            reply.writeInt(canAddMoreProfilesToUser ? 1 : 0);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg15 = true;
                            }
                            boolean canAddMoreManagedProfiles = canAddMoreManagedProfiles(_arg021, _arg15);
                            reply.writeNoException();
                            reply.writeInt(canAddMoreManagedProfiles ? 1 : 0);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg022 = data.readInt();
                            UserInfo _result13 = getProfileParent(_arg022);
                            reply.writeNoException();
                            if (_result13 != null) {
                                reply.writeInt(1);
                                _result13.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg023 = data.readInt();
                            boolean isSameProfileGroup = isSameProfileGroup(_arg023, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isSameProfileGroup ? 1 : 0);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg024 = data.readInt();
                            boolean isUserOfType = isUserOfType(_arg024, data.readString());
                            reply.writeNoException();
                            reply.writeInt(isUserOfType ? 1 : 0);
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg025 = data.readInt();
                            UserInfo _result14 = getUserInfo(_arg025);
                            reply.writeNoException();
                            if (_result14 != null) {
                                reply.writeInt(1);
                                _result14.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg026 = data.readInt();
                            String _result15 = getUserAccount(_arg026);
                            reply.writeNoException();
                            reply.writeString(_result15);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg027 = data.readInt();
                            setUserAccount(_arg027, data.readString());
                            reply.writeNoException();
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg028 = data.readInt();
                            long _result16 = getUserCreationTime(_arg028);
                            reply.writeNoException();
                            reply.writeLong(_result16);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isRestricted = isRestricted();
                            reply.writeNoException();
                            reply.writeInt(isRestricted ? 1 : 0);
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg029 = data.readInt();
                            boolean canHaveRestrictedProfile = canHaveRestrictedProfile(_arg029);
                            reply.writeNoException();
                            reply.writeInt(canHaveRestrictedProfile ? 1 : 0);
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg030 = data.readInt();
                            int _result17 = getUserSerialNumber(_arg030);
                            reply.writeNoException();
                            reply.writeInt(_result17);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg031 = data.readInt();
                            int _result18 = getUserHandle(_arg031);
                            reply.writeNoException();
                            reply.writeInt(_result18);
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg032 = data.readString();
                            int _result19 = getUserRestrictionSource(_arg032, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result19);
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg033 = data.readString();
                            List<UserManager.EnforcingUser> _result20 = getUserRestrictionSources(_arg033, data.readInt());
                            reply.writeNoException();
                            reply.writeTypedList(_result20);
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg034 = data.readInt();
                            Bundle _result21 = getUserRestrictions(_arg034);
                            reply.writeNoException();
                            if (_result21 != null) {
                                reply.writeInt(1);
                                _result21.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg035 = data.readString();
                            boolean hasBaseUserRestriction = hasBaseUserRestriction(_arg035, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(hasBaseUserRestriction ? 1 : 0);
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg036 = data.readString();
                            boolean hasUserRestriction = hasUserRestriction(_arg036, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(hasUserRestriction ? 1 : 0);
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg037 = data.readString();
                            boolean hasUserRestrictionOnAnyUser = hasUserRestrictionOnAnyUser(_arg037);
                            reply.writeNoException();
                            reply.writeInt(hasUserRestrictionOnAnyUser ? 1 : 0);
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg038 = data.readString();
                            int _arg19 = data.readInt();
                            String _arg23 = data.readString();
                            int _arg34 = data.readInt();
                            boolean isSettingRestrictedForUser = isSettingRestrictedForUser(_arg038, _arg19, _arg23, _arg34);
                            reply.writeNoException();
                            reply.writeInt(isSettingRestrictedForUser ? 1 : 0);
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            IUserRestrictionsListener _arg039 = IUserRestrictionsListener.Stub.asInterface(data.readStrongBinder());
                            addUserRestrictionsListener(_arg039);
                            reply.writeNoException();
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg040 = data.readString();
                            if (data.readInt() != 0) {
                                _arg15 = true;
                            }
                            int _arg24 = data.readInt();
                            setUserRestriction(_arg040, _arg15, _arg24);
                            reply.writeNoException();
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg041 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            int _arg25 = data.readInt();
                            setApplicationRestrictions(_arg041, _arg13, _arg25);
                            reply.writeNoException();
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg042 = data.readString();
                            Bundle _result22 = getApplicationRestrictions(_arg042);
                            reply.writeNoException();
                            if (_result22 != null) {
                                reply.writeInt(1);
                                _result22.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg043 = data.readString();
                            Bundle _result23 = getApplicationRestrictionsForUser(_arg043, data.readInt());
                            reply.writeNoException();
                            if (_result23 != null) {
                                reply.writeInt(1);
                                _result23.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            setDefaultGuestRestrictions(_arg02);
                            reply.writeNoException();
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            Bundle _result24 = getDefaultGuestRestrictions();
                            reply.writeNoException();
                            if (_result24 != null) {
                                reply.writeInt(1);
                                _result24.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg044 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg15 = true;
                            }
                            int _result25 = removeUserOrSetEphemeral(_arg044, _arg15);
                            reply.writeNoException();
                            reply.writeInt(_result25);
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg045 = data.readInt();
                            boolean markGuestForDeletion = markGuestForDeletion(_arg045);
                            reply.writeNoException();
                            reply.writeInt(markGuestForDeletion ? 1 : 0);
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            UserInfo _result26 = findCurrentGuestUser();
                            reply.writeNoException();
                            if (_result26 != null) {
                                reply.writeInt(1);
                                _result26.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg046 = data.readInt();
                            boolean isQuietModeEnabled = isQuietModeEnabled(_arg046);
                            reply.writeNoException();
                            reply.writeInt(isQuietModeEnabled ? 1 : 0);
                            return true;
                        case 51:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg047 = data.readInt();
                            String _arg110 = data.readString();
                            String _arg26 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = true;
                            } else {
                                _arg4 = false;
                            }
                            setSeedAccountData(_arg047, _arg110, _arg26, _arg3, _arg4);
                            reply.writeNoException();
                            return true;
                        case 52:
                            data.enforceInterface(DESCRIPTOR);
                            String _result27 = getSeedAccountName();
                            reply.writeNoException();
                            reply.writeString(_result27);
                            return true;
                        case 53:
                            data.enforceInterface(DESCRIPTOR);
                            String _result28 = getSeedAccountType();
                            reply.writeNoException();
                            reply.writeString(_result28);
                            return true;
                        case 54:
                            data.enforceInterface(DESCRIPTOR);
                            PersistableBundle _result29 = getSeedAccountOptions();
                            reply.writeNoException();
                            if (_result29 != null) {
                                reply.writeInt(1);
                                _result29.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 55:
                            data.enforceInterface(DESCRIPTOR);
                            clearSeedAccountData();
                            reply.writeNoException();
                            return true;
                        case 56:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg048 = data.readString();
                            boolean someUserHasSeedAccount = someUserHasSeedAccount(_arg048, data.readString());
                            reply.writeNoException();
                            reply.writeInt(someUserHasSeedAccount ? 1 : 0);
                            return true;
                        case 57:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg049 = data.readInt();
                            boolean isProfile = isProfile(_arg049);
                            reply.writeNoException();
                            reply.writeInt(isProfile ? 1 : 0);
                            return true;
                        case 58:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg050 = data.readInt();
                            boolean isManagedProfile = isManagedProfile(_arg050);
                            reply.writeNoException();
                            reply.writeInt(isManagedProfile ? 1 : 0);
                            return true;
                        case 59:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg051 = data.readInt();
                            boolean isCloneProfile = isCloneProfile(_arg051);
                            reply.writeNoException();
                            reply.writeInt(isCloneProfile ? 1 : 0);
                            return true;
                        case 60:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg052 = data.readInt();
                            boolean isMediaSharedWithParent = isMediaSharedWithParent(_arg052);
                            reply.writeNoException();
                            reply.writeInt(isMediaSharedWithParent ? 1 : 0);
                            return true;
                        case 61:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg053 = data.readInt();
                            boolean isDemoUser = isDemoUser(_arg053);
                            reply.writeNoException();
                            reply.writeInt(isDemoUser ? 1 : 0);
                            return true;
                        case 62:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg054 = data.readInt();
                            boolean isPreCreated = isPreCreated(_arg054);
                            reply.writeNoException();
                            reply.writeInt(isPreCreated ? 1 : 0);
                            return true;
                        case 63:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg055 = data.readString();
                            String _arg111 = data.readString();
                            int _arg27 = data.readInt();
                            int _arg35 = data.readInt();
                            String[] _arg43 = data.createStringArray();
                            UserInfo _result30 = createProfileForUserEvenWhenDisallowedWithThrow(_arg055, _arg111, _arg27, _arg35, _arg43);
                            reply.writeNoException();
                            if (_result30 != null) {
                                reply.writeInt(1);
                                _result30.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 64:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg056 = data.readInt();
                            boolean isUserUnlockingOrUnlocked = isUserUnlockingOrUnlocked(_arg056);
                            reply.writeNoException();
                            reply.writeInt(isUserUnlockingOrUnlocked ? 1 : 0);
                            return true;
                        case 65:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg057 = data.readInt();
                            int _result31 = getUserIconBadgeResId(_arg057);
                            reply.writeNoException();
                            reply.writeInt(_result31);
                            return true;
                        case 66:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg058 = data.readInt();
                            int _result32 = getUserBadgeResId(_arg058);
                            reply.writeNoException();
                            reply.writeInt(_result32);
                            return true;
                        case 67:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg059 = data.readInt();
                            int _result33 = getUserBadgeNoBackgroundResId(_arg059);
                            reply.writeNoException();
                            reply.writeInt(_result33);
                            return true;
                        case 68:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg060 = data.readInt();
                            int _result34 = getUserBadgeLabelResId(_arg060);
                            reply.writeNoException();
                            reply.writeInt(_result34);
                            return true;
                        case 69:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg061 = data.readInt();
                            int _result35 = getUserBadgeColorResId(_arg061);
                            reply.writeNoException();
                            reply.writeInt(_result35);
                            return true;
                        case 70:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg062 = data.readInt();
                            int _result36 = getUserBadgeDarkColorResId(_arg062);
                            reply.writeNoException();
                            reply.writeInt(_result36);
                            return true;
                        case 71:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg063 = data.readInt();
                            boolean hasBadge = hasBadge(_arg063);
                            reply.writeNoException();
                            reply.writeInt(hasBadge ? 1 : 0);
                            return true;
                        case 72:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg064 = data.readInt();
                            boolean isUserUnlocked = isUserUnlocked(_arg064);
                            reply.writeNoException();
                            reply.writeInt(isUserUnlocked ? 1 : 0);
                            return true;
                        case 73:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg065 = data.readInt();
                            boolean isUserRunning = isUserRunning(_arg065);
                            reply.writeNoException();
                            reply.writeInt(isUserRunning ? 1 : 0);
                            return true;
                        case 74:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg066 = data.readInt();
                            boolean isUserForeground = isUserForeground(_arg066);
                            reply.writeNoException();
                            reply.writeInt(isUserForeground ? 1 : 0);
                            return true;
                        case 75:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg067 = data.readInt();
                            boolean isUserNameSet = isUserNameSet(_arg067);
                            reply.writeNoException();
                            reply.writeInt(isUserNameSet ? 1 : 0);
                            return true;
                        case 76:
                            data.enforceInterface(DESCRIPTOR);
                            boolean hasRestrictedProfiles = hasRestrictedProfiles();
                            reply.writeNoException();
                            reply.writeInt(hasRestrictedProfiles ? 1 : 0);
                            return true;
                        case 77:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg068 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            } else {
                                _arg14 = false;
                            }
                            int _arg28 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg32 = IntentSender.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            int _arg44 = data.readInt();
                            boolean requestQuietModeEnabled = requestQuietModeEnabled(_arg068, _arg14, _arg28, _arg32, _arg44);
                            reply.writeNoException();
                            reply.writeInt(requestQuietModeEnabled ? 1 : 0);
                            return true;
                        case 78:
                            data.enforceInterface(DESCRIPTOR);
                            String _result37 = getUserName();
                            reply.writeNoException();
                            reply.writeString(_result37);
                            return true;
                        case 79:
                            data.enforceInterface(DESCRIPTOR);
                            long _result38 = getUserStartRealtime();
                            reply.writeNoException();
                            reply.writeLong(_result38);
                            return true;
                        case 80:
                            data.enforceInterface(DESCRIPTOR);
                            long _result39 = getUserUnlockRealtime();
                            reply.writeNoException();
                            reply.writeLong(_result39);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IUserManager {
            public static IUserManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.os.IUserManager
            public int getCredentialOwnerProfile(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialOwnerProfile(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getProfileParentId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfileParentId(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo createUserWithThrow(String name, String userType, int flags) throws RemoteException {
                UserInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeString(userType);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createUserWithThrow(name, userType, flags);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = UserInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo preCreateUserWithThrow(String userType) throws RemoteException {
                UserInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(userType);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().preCreateUserWithThrow(userType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = UserInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo createProfileForUserWithThrow(String name, String userType, int flags, int userId, String[] disallowedPackages) throws RemoteException {
                UserInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeString(userType);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    _data.writeStringArray(disallowedPackages);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createProfileForUserWithThrow(name, userType, flags, userId, disallowedPackages);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = UserInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo createRestrictedProfileWithThrow(String name, int parentUserHandle) throws RemoteException {
                UserInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeInt(parentUserHandle);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createRestrictedProfileWithThrow(name, parentUserHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = UserInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public String[] getPreInstallableSystemPackages(String userType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(userType);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreInstallableSystemPackages(userType);
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserEnabled(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUserEnabled(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserAdmin(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUserAdmin(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void evictCredentialEncryptionKey(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().evictCredentialEncryptionKey(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean removeUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeUser(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean removeUserEvenWhenDisallowed(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeUserEvenWhenDisallowed(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserName(int userId, String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUserName(userId, name);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserIcon(int userId, Bitmap icon) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    if (icon != null) {
                        _data.writeInt(1);
                        icon.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUserIcon(userId, icon);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public ParcelFileDescriptor getUserIcon(int userId) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserIcon(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo getPrimaryUser() throws RemoteException {
                UserInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPrimaryUser();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = UserInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public List<UserInfo> getUsers(boolean excludePartial, boolean excludeDying, boolean excludePreCreated) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    _data.writeInt(excludePartial ? 1 : 0);
                    _data.writeInt(excludeDying ? 1 : 0);
                    if (!excludePreCreated) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUsers(excludePartial, excludeDying, excludePreCreated);
                    }
                    _reply.readException();
                    List<UserInfo> _result = _reply.createTypedArrayList(UserInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public List<UserInfo> getProfiles(int userId, boolean enabledOnly) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(enabledOnly ? 1 : 0);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfiles(userId, enabledOnly);
                    }
                    _reply.readException();
                    List<UserInfo> _result = _reply.createTypedArrayList(UserInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int[] getProfileIds(int userId, boolean enabledOnly) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(enabledOnly ? 1 : 0);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfileIds(userId, enabledOnly);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean canAddMoreProfilesToUser(String userType, int userId, boolean allowedToRemoveOne) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(userType);
                    _data.writeInt(userId);
                    boolean _result = true;
                    _data.writeInt(allowedToRemoveOne ? 1 : 0);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canAddMoreProfilesToUser(userType, userId, allowedToRemoveOne);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean canAddMoreManagedProfiles(int userId, boolean allowedToRemoveOne) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = true;
                    _data.writeInt(allowedToRemoveOne ? 1 : 0);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canAddMoreManagedProfiles(userId, allowedToRemoveOne);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo getProfileParent(int userId) throws RemoteException {
                UserInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfileParent(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = UserInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isSameProfileGroup(int userId, int otherUserHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(otherUserHandle);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSameProfileGroup(userId, otherUserHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserOfType(int userId, String userType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(userType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserOfType(userId, userType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo getUserInfo(int userId) throws RemoteException {
                UserInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserInfo(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = UserInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public String getUserAccount(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserAccount(userId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserAccount(int userId, String accountName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(accountName);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUserAccount(userId, accountName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public long getUserCreationTime(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserCreationTime(userId);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isRestricted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRestricted();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean canHaveRestrictedProfile(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canHaveRestrictedProfile(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserSerialNumber(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserSerialNumber(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserHandle(int userSerialNumber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userSerialNumber);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserHandle(userSerialNumber);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserRestrictionSource(String restrictionKey, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(restrictionKey);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserRestrictionSource(restrictionKey, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public List<UserManager.EnforcingUser> getUserRestrictionSources(String restrictionKey, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(restrictionKey);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserRestrictionSources(restrictionKey, userId);
                    }
                    _reply.readException();
                    List<UserManager.EnforcingUser> _result = _reply.createTypedArrayList(UserManager.EnforcingUser.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public Bundle getUserRestrictions(int userId) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserRestrictions(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean hasBaseUserRestriction(String restrictionKey, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(restrictionKey);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasBaseUserRestriction(restrictionKey, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean hasUserRestriction(String restrictionKey, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(restrictionKey);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasUserRestriction(restrictionKey, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean hasUserRestrictionOnAnyUser(String restrictionKey) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(restrictionKey);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasUserRestrictionOnAnyUser(restrictionKey);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isSettingRestrictedForUser(String setting, int userId, String value, int callingUid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(setting);
                    _data.writeInt(userId);
                    _data.writeString(value);
                    _data.writeInt(callingUid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSettingRestrictedForUser(setting, userId, value, callingUid);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void addUserRestrictionsListener(IUserRestrictionsListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addUserRestrictionsListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setUserRestriction(String key, boolean value, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeInt(value ? 1 : 0);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUserRestriction(key, value, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setApplicationRestrictions(String packageName, Bundle restrictions, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (restrictions != null) {
                        _data.writeInt(1);
                        restrictions.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setApplicationRestrictions(packageName, restrictions, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public Bundle getApplicationRestrictions(String packageName) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApplicationRestrictions(packageName);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public Bundle getApplicationRestrictionsForUser(String packageName, int userId) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApplicationRestrictionsForUser(packageName, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setDefaultGuestRestrictions(Bundle restrictions) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (restrictions != null) {
                        _data.writeInt(1);
                        restrictions.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDefaultGuestRestrictions(restrictions);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public Bundle getDefaultGuestRestrictions() throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultGuestRestrictions();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int removeUserOrSetEphemeral(int userId, boolean evenWhenDisallowed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(evenWhenDisallowed ? 1 : 0);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeUserOrSetEphemeral(userId, evenWhenDisallowed);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean markGuestForDeletion(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().markGuestForDeletion(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo findCurrentGuestUser() throws RemoteException {
                UserInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findCurrentGuestUser();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = UserInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isQuietModeEnabled(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isQuietModeEnabled(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void setSeedAccountData(int userId, String accountName, String accountType, PersistableBundle accountOptions, boolean persist) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(accountName);
                    _data.writeString(accountType);
                    int i = 1;
                    if (accountOptions != null) {
                        _data.writeInt(1);
                        accountOptions.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!persist) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSeedAccountData(userId, accountName, accountType, accountOptions, persist);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public String getSeedAccountName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeedAccountName();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public String getSeedAccountType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeedAccountType();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public PersistableBundle getSeedAccountOptions() throws RemoteException {
                PersistableBundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSeedAccountOptions();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PersistableBundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public void clearSeedAccountData() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearSeedAccountData();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean someUserHasSeedAccount(String accountName, String accountType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(accountName);
                    _data.writeString(accountType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().someUserHasSeedAccount(accountName, accountType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isProfile(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isProfile(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isManagedProfile(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(58, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isManagedProfile(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isCloneProfile(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCloneProfile(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isMediaSharedWithParent(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(60, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMediaSharedWithParent(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isDemoUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDemoUser(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isPreCreated(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(62, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPreCreated(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public UserInfo createProfileForUserEvenWhenDisallowedWithThrow(String name, String userType, int flags, int userId, String[] disallowedPackages) throws RemoteException {
                UserInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeString(userType);
                    _data.writeInt(flags);
                    _data.writeInt(userId);
                    _data.writeStringArray(disallowedPackages);
                    boolean _status = this.mRemote.transact(63, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createProfileForUserEvenWhenDisallowedWithThrow(name, userType, flags, userId, disallowedPackages);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = UserInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserUnlockingOrUnlocked(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserUnlockingOrUnlocked(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserIconBadgeResId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(65, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserIconBadgeResId(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserBadgeResId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserBadgeResId(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserBadgeNoBackgroundResId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserBadgeNoBackgroundResId(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserBadgeLabelResId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserBadgeLabelResId(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserBadgeColorResId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserBadgeColorResId(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public int getUserBadgeDarkColorResId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(70, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserBadgeDarkColorResId(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean hasBadge(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasBadge(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserUnlocked(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserUnlocked(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserRunning(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserRunning(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserForeground(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(74, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserForeground(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean isUserNameSet(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(75, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserNameSet(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean hasRestrictedProfiles() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(76, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasRestrictedProfiles();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public boolean requestQuietModeEnabled(String callingPackage, boolean enableQuietMode, int userId, IntentSender target, int flags) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(callingPackage);
                        boolean _result = true;
                        _data.writeInt(enableQuietMode ? 1 : 0);
                        try {
                            _data.writeInt(userId);
                            if (target != null) {
                                _data.writeInt(1);
                                target.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            try {
                                _data.writeInt(flags);
                                try {
                                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        if (_reply.readInt() == 0) {
                                            _result = false;
                                        }
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    boolean requestQuietModeEnabled = Stub.getDefaultImpl().requestQuietModeEnabled(callingPackage, enableQuietMode, userId, target, flags);
                                    _reply.recycle();
                                    _data.recycle();
                                    return requestQuietModeEnabled;
                                } catch (Throwable th2) {
                                    th = th2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // android.os.IUserManager
            public String getUserName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(78, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserName();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public long getUserStartRealtime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserStartRealtime();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IUserManager
            public long getUserUnlockRealtime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(80, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserUnlockRealtime();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IUserManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
