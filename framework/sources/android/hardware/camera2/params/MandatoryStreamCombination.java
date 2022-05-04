package android.hardware.camera2.params;

import android.graphics.ImageFormat;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import com.android.internal.logging.nano.MetricsProto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class MandatoryStreamCombination {
    private static final String TAG = "MandatoryStreamCombination";
    private final String mDescription;
    private final boolean mIsReprocessable;
    private final ArrayList<MandatoryStreamInformation> mStreamsInformation;
    private static StreamCombinationTemplate[] sLegacyCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.MAXIMUM)}, "Simple preview, GPU video processing, or no-preview video recording"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "No-viewfinder still image capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "In-application video/image processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "Standard still imaging"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "In-app processing plus still capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "Standard recording"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW)}, "Preview plus in-app processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "Still capture plus in-app processing")};
    private static StreamCombinationTemplate[] sLimitedCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.RECORD)}, "High-resolution video recording with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "High-resolution in-app video processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "Two-input in-app video processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.RECORD), new StreamTemplate(256, SizeThreshold.RECORD)}, "High-resolution recording with video snapshot"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD), new StreamTemplate(256, SizeThreshold.RECORD)}, "High-resolution in-app processing with video snapshot"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "Two-input in-app processing with still capture")};
    private static StreamCombinationTemplate[] sBurstCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.MAXIMUM)}, "Maximum-resolution GPU processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution in-app processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution two-input in-app processsing")};
    private static StreamCombinationTemplate[] sFullCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.MAXIMUM), new StreamTemplate(34, SizeThreshold.MAXIMUM)}, "Maximum-resolution GPU processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.MAXIMUM), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution in-app processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.MAXIMUM), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution two-input in-app processsing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "Video recording with maximum-size video snapshot"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.VGA), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Standard video recording plus maximum-resolution in-app processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.VGA), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Preview plus two-input maximum-resolution in-app processing")};
    private static StreamCombinationTemplate[] sRawCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "No-preview DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Standard DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "In-app processing plus DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Video recording with DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Preview with in-app processing and DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Two-input in-app processing plus DNG capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Still capture with simultaneous JPEG and DNG"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "In-app processing with simultaneous JPEG and DNG")};
    private static StreamCombinationTemplate[] sLevel3Combinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(35, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "In-app viewfinder analysis with dynamic selection of output format"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "In-app viewfinder analysis with dynamic selection of output format")};
    private static StreamCombinationTemplate[] sLimitedPrivateReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "No-viewfinder still image reprocessing", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL(Zero-Shutter-Lag) still imaging", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL still and in-app processing imaging", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL in-app processing with still capture", ReprocessType.PRIVATE)};
    private static StreamCombinationTemplate[] sLimitedYUVReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "No-viewfinder still image reprocessing", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL(Zero-Shutter-Lag) still imaging", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL still and in-app processing imaging", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL in-app processing with still capture", ReprocessType.YUV)};
    private static StreamCombinationTemplate[] sFullPrivateReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "High-resolution ZSL in-app video processing with regular preview", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution ZSL in-app processing with regular preview", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Maximum-resolution two-input ZSL in-app processing", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL still capture and in-app processing", ReprocessType.PRIVATE)};
    private static StreamCombinationTemplate[] sFullYUVReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW)}, "Maximum-resolution multi-frame image fusion in-app processing with regular preview", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW)}, "Maximum-resolution multi-frame image fusion two-input in-app processing", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "High-resolution ZSL in-app video processing with regular preview", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "ZSL still capture and in-app processing", ReprocessType.YUV)};
    private static StreamCombinationTemplate[] sRAWPrivateReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing and DNG capture", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing and preview with DNG capture", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL two-input in-app processing and DNG capture", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL still capture and preview with DNG capture", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing with still capture and DNG capture", ReprocessType.PRIVATE)};
    private static StreamCombinationTemplate[] sRAWYUVReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing and DNG capture", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing and preview with DNG capture", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL two-input in-app processing and DNG capture", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL still capture and preview with DNG capture", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "Mutually exclusive ZSL in-app processing with still capture and DNG capture", ReprocessType.YUV)};
    private static StreamCombinationTemplate[] sLevel3PrivateReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(32, SizeThreshold.MAXIMUM), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "In-app viewfinder analysis with ZSL, RAW, and JPEG reprocessing output", ReprocessType.PRIVATE)};
    private static StreamCombinationTemplate[] sLevel3YUVReprocCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(32, SizeThreshold.MAXIMUM)}, "In-app viewfinder analysis with ZSL and RAW", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(32, SizeThreshold.MAXIMUM), new StreamTemplate(256, SizeThreshold.MAXIMUM)}, "In-app viewfinder analysis with ZSL, RAW, and JPEG reprocessing output", ReprocessType.YUV)};
    private static StreamCombinationTemplate[] sConcurrentStreamCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.s1440p)}, "In-app video / image processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.s1440p)}, "preview / preview to GPU"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.s1440p)}, "No view-finder still image capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.s720p), new StreamTemplate(35, SizeThreshold.s1440p)}, "Two-input in app video / image processing"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.s720p), new StreamTemplate(34, SizeThreshold.s1440p)}, "High resolution video recording with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.s720p), new StreamTemplate(35, SizeThreshold.s1440p)}, "In-app video / image processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.s720p), new StreamTemplate(34, SizeThreshold.s1440p)}, "In-app video / image processing with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.s720p), new StreamTemplate(256, SizeThreshold.s1440p)}, "Standard stil image capture"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.s720p), new StreamTemplate(256, SizeThreshold.s1440p)}, "Standard still image capture")};
    private static StreamCombinationTemplate[] sConcurrentDepthOnlyStreamCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(ImageFormat.DEPTH16, SizeThreshold.VGA)}, "Depth capture for mesh based object rendering")};
    private static StreamCombinationTemplate[] sUltraHighResolutionStreamCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "Ultra high resolution YUV image capture with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(32, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "Ultra high resolution RAW_SENSOR image capture with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "Ultra high resolution JPEG image capture with preview"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "No-viewfinder Ultra high resolution YUV image capture with image analysis"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(32, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "No-viewfinder Ultra high resolution RAW_SENSOR image capture with image analysis"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "No-viewfinder Ultra high resolution JPEG image capture with image analysis"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.RECORD)}, "Ultra high resolution YUV image capture with preview + app-based image analysis"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(32, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.RECORD)}, "Ultra high resolution RAW image capture with preview + app-based image analysis"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.RECORD)}, "Ultra high resolution JPEG image capture with preview + app-based image analysis"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "Ultra high resolution YUV image capture with preview + app-based image analysis"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(32, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "Ultra high resolution RAW image capture with preview + app-based image analysis"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD)}, "Ultra high resolution JPEG image capture with preview + app-based image analysis"), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Ultra high resolution YUV image capture with preview + default", true), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(32, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Ultra high resolution RAW image capture with preview + default", true), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM)}, "Ultra high resolution JPEG capture with preview + default", true)};
    private static StreamCombinationTemplate[] sUltraHighResolutionReprocStreamCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(34, SizeThreshold.PREVIEW)}, "In-app RAW remosaic reprocessing with separate preview", ReprocessType.REMOSAIC), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.PREVIEW)}, "In-app RAW remosaic reprocessing with in-app image analysis", ReprocessType.REMOSAIC), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "In-app RAW -> JPEG reprocessing with separate preview", ReprocessType.REMOSAIC), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "In-app RAW -> YUV reprocessing with separate preview", ReprocessType.REMOSAIC), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(35, SizeThreshold.PREVIEW)}, "In-app RAW -> JPEG reprocessing with in-app image analysis", ReprocessType.REMOSAIC), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.FULL_RES), new StreamTemplate(35, SizeThreshold.PREVIEW)}, "In-app RAW -> YUV reprocessing with in-app image analysis", ReprocessType.REMOSAIC)};
    private static StreamCombinationTemplate[] sUltraHighResolutionYUVReprocStreamCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "Ultra high resolution YUV -> JPEG reprocessing with separate preview", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(35, SizeThreshold.PREVIEW)}, "Ultra high resolution YUV -> JPEG reprocessing with in-app image analysis", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "Ultra high resolution YUV -> YUV reprocessing with separate preview", ReprocessType.YUV), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(35, SizeThreshold.FULL_RES), new StreamTemplate(35, SizeThreshold.PREVIEW)}, "Ultra high resolution YUV -> YUV reprocessing with in-app image analysis", ReprocessType.YUV)};
    private static StreamCombinationTemplate[] sUltraHighResolutionPRIVReprocStreamCombinations = {new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(34, SizeThreshold.PREVIEW)}, "Ultra high resolution PRIVATE -> JPEG reprocessing with separate preview", ReprocessType.PRIVATE), new StreamCombinationTemplate(new StreamTemplate[]{new StreamTemplate(256, SizeThreshold.FULL_RES), new StreamTemplate(35, SizeThreshold.PREVIEW)}, "Ultra high resolution PRIVATE -> JPEG reprocessing with in-app image analysis", ReprocessType.PRIVATE)};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum ReprocessType {
        NONE,
        PRIVATE,
        YUV,
        REMOSAIC
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum SizeThreshold {
        VGA,
        PREVIEW,
        RECORD,
        MAXIMUM,
        s720p,
        s1440p,
        FULL_RES
    }

    /* loaded from: classes.dex */
    public static final class MandatoryStreamInformation {
        private final ArrayList<Size> mAvailableSizes;
        private final int mFormat;
        private final boolean mIsInput;
        private final boolean mIsMaximumSize;
        private final boolean mIsUltraHighResolution;

        public MandatoryStreamInformation(List<Size> availableSizes, int format, boolean isMaximumSize) {
            this(availableSizes, format, isMaximumSize, false, false);
        }

        public MandatoryStreamInformation(List<Size> availableSizes, int format, boolean isMaximumSize, boolean isInput) {
            this(availableSizes, format, isMaximumSize, isInput, false);
        }

        public MandatoryStreamInformation(List<Size> availableSizes, int format, boolean isMaximumSize, boolean isInput, boolean isUltraHighResolution) {
            ArrayList<Size> arrayList = new ArrayList<>();
            this.mAvailableSizes = arrayList;
            if (!availableSizes.isEmpty()) {
                arrayList.addAll(availableSizes);
                this.mFormat = StreamConfigurationMap.checkArgumentFormat(format);
                this.mIsMaximumSize = isMaximumSize;
                this.mIsInput = isInput;
                this.mIsUltraHighResolution = isUltraHighResolution;
                return;
            }
            throw new IllegalArgumentException("No available sizes");
        }

        public boolean isInput() {
            return this.mIsInput;
        }

        public boolean isUltraHighResolution() {
            return this.mIsUltraHighResolution;
        }

        public boolean isMaximumSize() {
            return this.mIsMaximumSize;
        }

        public List<Size> getAvailableSizes() {
            return Collections.unmodifiableList(this.mAvailableSizes);
        }

        public int getFormat() {
            return this.mFormat;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MandatoryStreamInformation)) {
                return false;
            }
            MandatoryStreamInformation other = (MandatoryStreamInformation) obj;
            if (this.mFormat == other.mFormat && this.mIsInput == other.mIsInput && this.mIsUltraHighResolution == other.mIsUltraHighResolution && this.mAvailableSizes.size() == other.mAvailableSizes.size()) {
                return this.mAvailableSizes.equals(other.mAvailableSizes);
            }
            return false;
        }

        public int hashCode() {
            return HashCodeHelpers.hashCode(this.mFormat, Boolean.hashCode(this.mIsInput), Boolean.hashCode(this.mIsUltraHighResolution), this.mAvailableSizes.hashCode());
        }
    }

    public MandatoryStreamCombination(List<MandatoryStreamInformation> streamsInformation, String description, boolean isReprocessable) {
        ArrayList<MandatoryStreamInformation> arrayList = new ArrayList<>();
        this.mStreamsInformation = arrayList;
        if (!streamsInformation.isEmpty()) {
            arrayList.addAll(streamsInformation);
            this.mDescription = description;
            this.mIsReprocessable = isReprocessable;
            return;
        }
        throw new IllegalArgumentException("Empty stream information");
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }

    public boolean isReprocessable() {
        return this.mIsReprocessable;
    }

    public List<MandatoryStreamInformation> getStreamsInformation() {
        return Collections.unmodifiableList(this.mStreamsInformation);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MandatoryStreamCombination)) {
            return false;
        }
        MandatoryStreamCombination other = (MandatoryStreamCombination) obj;
        if (this.mDescription == other.mDescription && this.mIsReprocessable == other.mIsReprocessable && this.mStreamsInformation.size() == other.mStreamsInformation.size()) {
            return this.mStreamsInformation.equals(other.mStreamsInformation);
        }
        return false;
    }

    public int hashCode() {
        return HashCodeHelpers.hashCode(Boolean.hashCode(this.mIsReprocessable), this.mDescription.hashCode(), this.mStreamsInformation.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class StreamTemplate {
        public int mFormat;
        public boolean mIsInput;
        public SizeThreshold mSizeThreshold;

        public StreamTemplate(int format, SizeThreshold sizeThreshold) {
            this(format, sizeThreshold, false);
        }

        public StreamTemplate(int format, SizeThreshold sizeThreshold, boolean isInput) {
            this.mFormat = format;
            this.mSizeThreshold = sizeThreshold;
            this.mIsInput = isInput;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class StreamCombinationTemplate {
        public String mDescription;
        public ReprocessType mReprocessType;
        public StreamTemplate[] mStreamTemplates;
        public boolean mSubstituteYUV;

        public StreamCombinationTemplate(StreamTemplate[] streamTemplates, String description) {
            this(streamTemplates, description, ReprocessType.NONE);
        }

        public StreamCombinationTemplate(StreamTemplate[] streamTemplates, String description, ReprocessType reprocessType) {
            this(streamTemplates, description, reprocessType, false);
        }

        public StreamCombinationTemplate(StreamTemplate[] streamTemplates, String description, boolean substituteYUV) {
            this(streamTemplates, description, ReprocessType.NONE, substituteYUV);
        }

        public StreamCombinationTemplate(StreamTemplate[] streamTemplates, String description, ReprocessType reprocessType, boolean substituteYUV) {
            this.mSubstituteYUV = false;
            this.mStreamTemplates = streamTemplates;
            this.mReprocessType = reprocessType;
            this.mDescription = description;
            this.mSubstituteYUV = substituteYUV;
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private final Size kPreviewSizeBound = new Size(1920, 1088);
        private int mCameraId;
        private List<Integer> mCapabilities;
        private Size mDisplaySize;
        private int mHwLevel;
        private boolean mIsHiddenPhysicalCamera;
        private StreamConfigurationMap mStreamConfigMap;
        private StreamConfigurationMap mStreamConfigMapMaximumResolution;

        public Builder(int cameraId, int hwLevel, Size displaySize, List<Integer> capabilities, StreamConfigurationMap sm, StreamConfigurationMap smMaxResolution) {
            this.mCameraId = cameraId;
            this.mDisplaySize = displaySize;
            this.mCapabilities = capabilities;
            this.mStreamConfigMap = sm;
            this.mStreamConfigMapMaximumResolution = smMaxResolution;
            this.mHwLevel = hwLevel;
            this.mIsHiddenPhysicalCamera = CameraManager.isHiddenPhysicalCamera(Integer.toString(cameraId));
        }

        public List<MandatoryStreamCombination> getAvailableMandatoryConcurrentStreamCombinations() {
            StreamCombinationTemplate[] chosenStreamCombinations;
            Size formatSize;
            IllegalArgumentException e;
            StreamCombinationTemplate[] chosenStreamCombinations2 = MandatoryStreamCombination.sConcurrentStreamCombinations;
            if (!isColorOutputSupported()) {
                Log.v(MandatoryStreamCombination.TAG, "Device is not backward compatible, depth streams are mandatory!");
                StreamCombinationTemplate[] chosenStreamCombinations3 = MandatoryStreamCombination.sConcurrentDepthOnlyStreamCombinations;
                chosenStreamCombinations = chosenStreamCombinations3;
            } else {
                chosenStreamCombinations = chosenStreamCombinations2;
            }
            Size sizeVGAp = new Size(640, 480);
            Size size720p = new Size(1280, MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_RECEIVE_WAP_PUSH);
            Size size1440p = new Size(1920, MetricsProto.MetricsEvent.ACTION_HUSH_GESTURE);
            ArrayList<MandatoryStreamCombination> availableConcurrentStreamCombinations = new ArrayList<>();
            availableConcurrentStreamCombinations.ensureCapacity(chosenStreamCombinations.length);
            int length = chosenStreamCombinations.length;
            int i = 0;
            while (i < length) {
                StreamCombinationTemplate combTemplate = chosenStreamCombinations[i];
                ArrayList<MandatoryStreamInformation> streamsInfo = new ArrayList<>();
                streamsInfo.ensureCapacity(combTemplate.mStreamTemplates.length);
                StreamTemplate[] streamTemplateArr = combTemplate.mStreamTemplates;
                int length2 = streamTemplateArr.length;
                int i2 = 0;
                while (i2 < length2) {
                    StreamTemplate template = streamTemplateArr[i2];
                    List<Size> sizes = new ArrayList<>();
                    switch (AnonymousClass1.$SwitchMap$android$hardware$camera2$params$MandatoryStreamCombination$SizeThreshold[template.mSizeThreshold.ordinal()]) {
                        case 1:
                            formatSize = size1440p;
                            break;
                        case 2:
                            formatSize = sizeVGAp;
                            break;
                        default:
                            formatSize = size720p;
                            break;
                    }
                    Size sizeChosen = getMinSize(formatSize, getMaxSize(this.mStreamConfigMap.getOutputSizes(template.mFormat)));
                    sizes.add(sizeChosen);
                    try {
                        try {
                            try {
                                MandatoryStreamInformation streamInfo = new MandatoryStreamInformation(sizes, template.mFormat, false);
                                streamsInfo.add(streamInfo);
                                i2++;
                                length = length;
                                chosenStreamCombinations = chosenStreamCombinations;
                                sizeVGAp = sizeVGAp;
                            } catch (IllegalArgumentException e2) {
                                e = e2;
                                String cause = "No available sizes found for format: " + template.mFormat + " size threshold: " + template.mSizeThreshold + " combination: " + combTemplate.mDescription;
                                throw new RuntimeException(cause, e);
                            }
                        } catch (IllegalArgumentException e3) {
                            e = e3;
                        }
                    } catch (IllegalArgumentException e4) {
                        e = e4;
                    }
                }
                try {
                    MandatoryStreamCombination streamCombination = new MandatoryStreamCombination(streamsInfo, combTemplate.mDescription, false);
                    availableConcurrentStreamCombinations.add(streamCombination);
                    i++;
                    length = length;
                    chosenStreamCombinations = chosenStreamCombinations;
                    sizeVGAp = sizeVGAp;
                } catch (IllegalArgumentException e5) {
                    String cause2 = "No stream information for mandatory combination: " + combTemplate.mDescription;
                    throw new RuntimeException(cause2, e5);
                }
            }
            return Collections.unmodifiableList(availableConcurrentStreamCombinations);
        }

        public List<MandatoryStreamCombination> getAvailableMandatoryMaximumResolutionStreamCombinations() {
            if (!isColorOutputSupported()) {
                Log.v(MandatoryStreamCombination.TAG, "Device is not backward compatible!, no mandatory maximum res streams");
                return null;
            }
            ArrayList<StreamCombinationTemplate> chosenStreamCombinationTemplates = new ArrayList<>();
            chosenStreamCombinationTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sUltraHighResolutionStreamCombinations));
            ArrayList<MandatoryStreamCombination> availableStreamCombinations = new ArrayList<>();
            boolean addRemosaicReprocessing = isRemosaicReprocessingSupported();
            int remosaicSize = 0;
            Size[] maxResYUVInputSizes = this.mStreamConfigMapMaximumResolution.getInputSizes(35);
            Size[] maxResPRIVInputSizes = this.mStreamConfigMapMaximumResolution.getInputSizes(34);
            if (addRemosaicReprocessing) {
                remosaicSize = 0 + MandatoryStreamCombination.sUltraHighResolutionReprocStreamCombinations.length;
                chosenStreamCombinationTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sUltraHighResolutionReprocStreamCombinations));
            }
            if (!(maxResYUVInputSizes == null || maxResYUVInputSizes.length == 0)) {
                remosaicSize += MandatoryStreamCombination.sUltraHighResolutionYUVReprocStreamCombinations.length;
                chosenStreamCombinationTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sUltraHighResolutionYUVReprocStreamCombinations));
            }
            if (!(maxResPRIVInputSizes == null || maxResPRIVInputSizes.length == 0)) {
                remosaicSize += MandatoryStreamCombination.sUltraHighResolutionPRIVReprocStreamCombinations.length;
                chosenStreamCombinationTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sUltraHighResolutionPRIVReprocStreamCombinations));
            }
            availableStreamCombinations.ensureCapacity(chosenStreamCombinationTemplates.size() + remosaicSize);
            fillUHMandatoryStreamCombinations(availableStreamCombinations, chosenStreamCombinationTemplates);
            return Collections.unmodifiableList(availableStreamCombinations);
        }

        private MandatoryStreamCombination createUHSensorMandatoryStreamCombination(StreamCombinationTemplate combTemplate, int substitutedFormat) {
            String formatString;
            Size[] defaultRawSizes;
            int format;
            Builder builder = this;
            ArrayList<MandatoryStreamInformation> streamsInfo = new ArrayList<>();
            streamsInfo.ensureCapacity(combTemplate.mStreamTemplates.length);
            boolean isReprocess = combTemplate.mReprocessType != ReprocessType.NONE;
            if (isReprocess) {
                ArrayList<Size> inputSize = new ArrayList<>();
                if (combTemplate.mReprocessType == ReprocessType.PRIVATE) {
                    inputSize.add(getMaxSize(builder.mStreamConfigMapMaximumResolution.getInputSizes(34)));
                    format = 34;
                } else if (combTemplate.mReprocessType == ReprocessType.REMOSAIC) {
                    inputSize.add(getMaxSize(builder.mStreamConfigMapMaximumResolution.getInputSizes(32)));
                    format = 32;
                } else {
                    inputSize.add(getMaxSize(builder.mStreamConfigMapMaximumResolution.getInputSizes(35)));
                    format = 35;
                }
                streamsInfo.add(new MandatoryStreamInformation(inputSize, format, false, true, true));
                streamsInfo.add(new MandatoryStreamInformation(inputSize, format, false, false, true));
            }
            HashMap<Pair<SizeThreshold, Integer>, List<Size>> availableDefaultNonRawSizes = enumerateAvailableSizes();
            if (availableDefaultNonRawSizes == null) {
                Log.e(MandatoryStreamCombination.TAG, "Available size enumeration failed");
                return null;
            }
            Size[] defaultRawSizes2 = builder.mStreamConfigMap.getOutputSizes(32);
            ArrayList<Size> availableDefaultRawSizes = new ArrayList<>();
            if (defaultRawSizes2 != null) {
                availableDefaultRawSizes.ensureCapacity(defaultRawSizes2.length);
                availableDefaultRawSizes.addAll(Arrays.asList(defaultRawSizes2));
            }
            StreamTemplate[] streamTemplateArr = combTemplate.mStreamTemplates;
            int length = streamTemplateArr.length;
            int i = 0;
            while (i < length) {
                StreamTemplate template = streamTemplateArr[i];
                List<Size> sizes = new ArrayList<>();
                int formatChosen = template.mFormat;
                boolean isUltraHighResolution = template.mSizeThreshold == SizeThreshold.FULL_RES;
                StreamConfigurationMap sm = isUltraHighResolution ? builder.mStreamConfigMapMaximumResolution : builder.mStreamConfigMap;
                boolean isMaximumSize = template.mSizeThreshold == SizeThreshold.MAXIMUM;
                if (substitutedFormat != 0 && isMaximumSize) {
                    formatChosen = substitutedFormat;
                }
                if (isUltraHighResolution) {
                    sizes.add(getMaxSize(sm.getOutputSizes(formatChosen)));
                    defaultRawSizes = defaultRawSizes2;
                } else if (formatChosen == 32) {
                    sizes = availableDefaultRawSizes;
                    defaultRawSizes = defaultRawSizes2;
                } else {
                    defaultRawSizes = defaultRawSizes2;
                    Pair<SizeThreshold, Integer> pair = new Pair<>(template.mSizeThreshold, new Integer(formatChosen));
                    sizes = availableDefaultNonRawSizes.get(pair);
                }
                try {
                    MandatoryStreamInformation streamInfo = new MandatoryStreamInformation(sizes, formatChosen, isMaximumSize, false, isUltraHighResolution);
                    streamsInfo.add(streamInfo);
                    i++;
                    builder = this;
                    defaultRawSizes2 = defaultRawSizes;
                } catch (IllegalArgumentException e) {
                    String cause = "No available sizes found for format: " + template.mFormat + " size threshold: " + template.mSizeThreshold + " combination: " + combTemplate.mDescription;
                    throw new RuntimeException(cause, e);
                }
            }
            switch (substitutedFormat) {
                case 32:
                    formatString = "RAW_SENSOR";
                    break;
                case 256:
                    formatString = "JPEG";
                    break;
                default:
                    formatString = "YUV";
                    break;
            }
            try {
                MandatoryStreamCombination streamCombination = new MandatoryStreamCombination(streamsInfo, combTemplate.mDescription + " " + formatString + " still-capture", isReprocess);
                return streamCombination;
            } catch (IllegalArgumentException e2) {
                String cause2 = "No stream information for mandatory combination: " + combTemplate.mDescription;
                throw new RuntimeException(cause2, e2);
            }
        }

        private void fillUHMandatoryStreamCombinations(ArrayList<MandatoryStreamCombination> availableStreamCombinations, ArrayList<StreamCombinationTemplate> chosenTemplates) {
            Iterator<StreamCombinationTemplate> it = chosenTemplates.iterator();
            while (it.hasNext()) {
                StreamCombinationTemplate combTemplate = it.next();
                MandatoryStreamCombination streamCombination = createUHSensorMandatoryStreamCombination(combTemplate, 0);
                availableStreamCombinations.add(streamCombination);
                if (combTemplate.mSubstituteYUV) {
                    MandatoryStreamCombination streamCombination2 = createUHSensorMandatoryStreamCombination(combTemplate, 32);
                    availableStreamCombinations.add(streamCombination2);
                    MandatoryStreamCombination streamCombination3 = createUHSensorMandatoryStreamCombination(combTemplate, 256);
                    availableStreamCombinations.add(streamCombination3);
                }
            }
        }

        public List<MandatoryStreamCombination> getAvailableMandatoryStreamCombinations() {
            if (!isColorOutputSupported()) {
                Log.v(MandatoryStreamCombination.TAG, "Device is not backward compatible!");
                return null;
            } else if (this.mCameraId >= 0 || isExternalCamera()) {
                ArrayList<StreamCombinationTemplate> availableTemplates = new ArrayList<>();
                if (isHardwareLevelAtLeastLegacy()) {
                    availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sLegacyCombinations));
                }
                if (isHardwareLevelAtLeastLimited() || isExternalCamera()) {
                    availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sLimitedCombinations));
                    if (isPrivateReprocessingSupported()) {
                        availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sLimitedPrivateReprocCombinations));
                    }
                    if (isYUVReprocessingSupported()) {
                        availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sLimitedYUVReprocCombinations));
                    }
                }
                if (isCapabilitySupported(6)) {
                    availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sBurstCombinations));
                }
                if (isHardwareLevelAtLeastFull()) {
                    availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sFullCombinations));
                    if (isPrivateReprocessingSupported()) {
                        availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sFullPrivateReprocCombinations));
                    }
                    if (isYUVReprocessingSupported()) {
                        availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sFullYUVReprocCombinations));
                    }
                }
                if (isCapabilitySupported(3)) {
                    availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sRawCombinations));
                    if (isPrivateReprocessingSupported()) {
                        availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sRAWPrivateReprocCombinations));
                    }
                    if (isYUVReprocessingSupported()) {
                        availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sRAWYUVReprocCombinations));
                    }
                }
                if (isHardwareLevelAtLeastLevel3()) {
                    availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sLevel3Combinations));
                    if (isPrivateReprocessingSupported()) {
                        availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sLevel3PrivateReprocCombinations));
                    }
                    if (isYUVReprocessingSupported()) {
                        availableTemplates.addAll(Arrays.asList(MandatoryStreamCombination.sLevel3YUVReprocCombinations));
                    }
                }
                return generateAvailableCombinations(availableTemplates);
            } else {
                Log.i(MandatoryStreamCombination.TAG, "Invalid camera id");
                return null;
            }
        }

        private List<MandatoryStreamCombination> generateAvailableCombinations(ArrayList<StreamCombinationTemplate> availableTemplates) {
            Size maxPrivateInputSize;
            Size maxYUVInputSize;
            List<Size> sizes;
            int format;
            if (availableTemplates.isEmpty()) {
                Log.e(MandatoryStreamCombination.TAG, "No available stream templates!");
                return null;
            }
            HashMap<Pair<SizeThreshold, Integer>, List<Size>> availableSizes = enumerateAvailableSizes();
            if (availableSizes == null) {
                Log.e(MandatoryStreamCombination.TAG, "Available size enumeration failed!");
                return null;
            }
            Size[] rawSizes = this.mStreamConfigMap.getOutputSizes(32);
            ArrayList<Size> availableRawSizes = new ArrayList<>();
            if (rawSizes != null) {
                availableRawSizes.ensureCapacity(rawSizes.length);
                availableRawSizes.addAll(Arrays.asList(rawSizes));
            }
            Size maxPrivateInputSize2 = new Size(0, 0);
            if (isPrivateReprocessingSupported()) {
                Size maxPrivateInputSize3 = getMaxSize(this.mStreamConfigMap.getInputSizes(34));
                maxPrivateInputSize = maxPrivateInputSize3;
            } else {
                maxPrivateInputSize = maxPrivateInputSize2;
            }
            Size maxYUVInputSize2 = new Size(0, 0);
            if (isYUVReprocessingSupported()) {
                Size maxYUVInputSize3 = getMaxSize(this.mStreamConfigMap.getInputSizes(35));
                maxYUVInputSize = maxYUVInputSize3;
            } else {
                maxYUVInputSize = maxYUVInputSize2;
            }
            ArrayList<MandatoryStreamCombination> availableStreamCombinations = new ArrayList<>();
            availableStreamCombinations.ensureCapacity(availableTemplates.size());
            Iterator<StreamCombinationTemplate> it = availableTemplates.iterator();
            while (it.hasNext()) {
                StreamCombinationTemplate combTemplate = it.next();
                ArrayList<MandatoryStreamInformation> streamsInfo = new ArrayList<>();
                streamsInfo.ensureCapacity(combTemplate.mStreamTemplates.length);
                boolean isReprocessable = combTemplate.mReprocessType != ReprocessType.NONE;
                if (isReprocessable) {
                    ArrayList<Size> inputSize = new ArrayList<>();
                    if (combTemplate.mReprocessType == ReprocessType.PRIVATE) {
                        inputSize.add(maxPrivateInputSize);
                        format = 34;
                    } else {
                        inputSize.add(maxYUVInputSize);
                        format = 35;
                    }
                    streamsInfo.add(new MandatoryStreamInformation(inputSize, format, true, true));
                    streamsInfo.add(new MandatoryStreamInformation(inputSize, format, true));
                }
                StreamTemplate[] streamTemplateArr = combTemplate.mStreamTemplates;
                int length = streamTemplateArr.length;
                int i = 0;
                while (i < length) {
                    StreamTemplate template = streamTemplateArr[i];
                    if (template.mFormat == 32) {
                        sizes = availableRawSizes;
                        streamTemplateArr = streamTemplateArr;
                        length = length;
                    } else {
                        streamTemplateArr = streamTemplateArr;
                        length = length;
                        Pair<SizeThreshold, Integer> pair = new Pair<>(template.mSizeThreshold, new Integer(template.mFormat));
                        sizes = availableSizes.get(pair);
                    }
                    boolean isMaximumSize = template.mSizeThreshold == SizeThreshold.MAXIMUM;
                    try {
                        MandatoryStreamInformation streamInfo = new MandatoryStreamInformation(sizes, template.mFormat, isMaximumSize);
                        streamsInfo.add(streamInfo);
                        i++;
                        it = it;
                    } catch (IllegalArgumentException e) {
                        Log.e(MandatoryStreamCombination.TAG, "No available sizes found for format: " + template.mFormat + " size threshold: " + template.mSizeThreshold + " combination: " + combTemplate.mDescription);
                        return null;
                    }
                }
                try {
                    MandatoryStreamCombination streamCombination = new MandatoryStreamCombination(streamsInfo, combTemplate.mDescription, isReprocessable);
                    availableStreamCombinations.add(streamCombination);
                    it = it;
                } catch (IllegalArgumentException e2) {
                    Log.e(MandatoryStreamCombination.TAG, "No stream information for mandatory combination: " + combTemplate.mDescription);
                    return null;
                }
            }
            return Collections.unmodifiableList(availableStreamCombinations);
        }

        private HashMap<Pair<SizeThreshold, Integer>, List<Size>> enumerateAvailableSizes() {
            Size recordingMaxSize;
            int[] formats = {34, 35, 256};
            new Size(0, 0);
            new Size(0, 0);
            Size vgaSize = new Size(640, 480);
            if (isExternalCamera() || this.mIsHiddenPhysicalCamera) {
                recordingMaxSize = getMaxCameraRecordingSize();
            } else {
                recordingMaxSize = getMaxRecordingSize();
            }
            if (recordingMaxSize == null) {
                Log.e(MandatoryStreamCombination.TAG, "Failed to find maximum recording size!");
                return null;
            }
            HashMap<Integer, Size[]> allSizes = new HashMap<>();
            for (int format : formats) {
                allSizes.put(new Integer(format), this.mStreamConfigMap.getOutputSizes(format));
            }
            List<Size> previewSizes = getSizesWithinBound(allSizes.get(new Integer(34)), this.kPreviewSizeBound);
            if (previewSizes == null || previewSizes.isEmpty()) {
                Log.e(MandatoryStreamCombination.TAG, "No preview sizes within preview size bound!");
                return null;
            }
            List<Size> orderedPreviewSizes = getAscendingOrderSizes(previewSizes, false);
            Size previewMaxSize = getMaxPreviewSize(orderedPreviewSizes);
            HashMap<Pair<SizeThreshold, Integer>, List<Size>> availableSizes = new HashMap<>();
            for (int format2 : formats) {
                Integer intFormat = new Integer(format2);
                Size[] sizes = allSizes.get(intFormat);
                Pair<SizeThreshold, Integer> pair = new Pair<>(SizeThreshold.VGA, intFormat);
                availableSizes.put(pair, getSizesWithinBound(sizes, vgaSize));
                Pair<SizeThreshold, Integer> pair2 = new Pair<>(SizeThreshold.PREVIEW, intFormat);
                availableSizes.put(pair2, getSizesWithinBound(sizes, previewMaxSize));
                Pair<SizeThreshold, Integer> pair3 = new Pair<>(SizeThreshold.RECORD, intFormat);
                availableSizes.put(pair3, getSizesWithinBound(sizes, recordingMaxSize));
                Pair<SizeThreshold, Integer> pair4 = new Pair<>(SizeThreshold.MAXIMUM, intFormat);
                availableSizes.put(pair4, Arrays.asList(sizes));
            }
            return availableSizes;
        }

        private static List<Size> getSizesWithinBound(Size[] sizes, Size bound) {
            ArrayList<Size> ret = new ArrayList<>();
            for (Size size : sizes) {
                if (size.getWidth() <= bound.getWidth() && size.getHeight() <= bound.getHeight()) {
                    ret.add(size);
                }
            }
            return ret;
        }

        public static Size getMinSize(Size a, Size b) {
            if (a == null || b == null) {
                throw new IllegalArgumentException("sizes was empty");
            } else if (a.getWidth() * a.getHeight() < b.getHeight() * b.getWidth()) {
                return a;
            } else {
                return b;
            }
        }

        public static Size getMaxSize(Size... sizes) {
            if (sizes == null || sizes.length == 0) {
                throw new IllegalArgumentException("sizes was empty");
            }
            Size sz = sizes[0];
            for (Size size : sizes) {
                if (size.getWidth() * size.getHeight() > sz.getWidth() * sz.getHeight()) {
                    sz = size;
                }
            }
            return sz;
        }

        private boolean isHardwareLevelAtLeast(int level) {
            int[] sortedHwLevels = {2, 4, 0, 1, 3};
            if (level == this.mHwLevel) {
                return true;
            }
            for (int sortedlevel : sortedHwLevels) {
                if (sortedlevel == level) {
                    return true;
                }
                if (sortedlevel == this.mHwLevel) {
                    return false;
                }
            }
            return false;
        }

        private boolean isExternalCamera() {
            return this.mHwLevel == 4;
        }

        private boolean isHardwareLevelAtLeastLegacy() {
            return isHardwareLevelAtLeast(2);
        }

        private boolean isHardwareLevelAtLeastLimited() {
            return isHardwareLevelAtLeast(0);
        }

        private boolean isHardwareLevelAtLeastFull() {
            return isHardwareLevelAtLeast(1);
        }

        private boolean isHardwareLevelAtLeastLevel3() {
            return isHardwareLevelAtLeast(3);
        }

        private boolean isCapabilitySupported(int capability) {
            return this.mCapabilities.contains(Integer.valueOf(capability));
        }

        private boolean isColorOutputSupported() {
            return isCapabilitySupported(0);
        }

        private boolean isPrivateReprocessingSupported() {
            return isCapabilitySupported(4);
        }

        private boolean isYUVReprocessingSupported() {
            return isCapabilitySupported(7);
        }

        private boolean isRemosaicReprocessingSupported() {
            return isCapabilitySupported(17);
        }

        private Size getMaxRecordingSize() {
            int quality = 8;
            if (!CamcorderProfile.hasProfile(this.mCameraId, 8)) {
                if (CamcorderProfile.hasProfile(this.mCameraId, 6)) {
                    quality = 6;
                } else if (CamcorderProfile.hasProfile(this.mCameraId, 5)) {
                    quality = 5;
                } else if (CamcorderProfile.hasProfile(this.mCameraId, 4)) {
                    quality = 4;
                } else if (CamcorderProfile.hasProfile(this.mCameraId, 7)) {
                    quality = 7;
                } else if (CamcorderProfile.hasProfile(this.mCameraId, 3)) {
                    quality = 3;
                } else if (CamcorderProfile.hasProfile(this.mCameraId, 2)) {
                    quality = 2;
                } else {
                    quality = -1;
                }
            }
            if (quality < 0) {
                return null;
            }
            CamcorderProfile maxProfile = CamcorderProfile.get(this.mCameraId, quality);
            return new Size(maxProfile.videoFrameWidth, maxProfile.videoFrameHeight);
        }

        private Size getMaxCameraRecordingSize() {
            Size FULLHD = new Size(1920, 1080);
            Size[] videoSizeArr = this.mStreamConfigMap.getOutputSizes(MediaRecorder.class);
            List<Size> sizes = new ArrayList<>();
            for (Size sz : videoSizeArr) {
                if (sz.getWidth() <= FULLHD.getWidth() && sz.getHeight() <= FULLHD.getHeight()) {
                    sizes.add(sz);
                }
            }
            List<Size> videoSizes = getAscendingOrderSizes(sizes, false);
            for (Size sz2 : videoSizes) {
                long minFrameDuration = this.mStreamConfigMap.getOutputMinFrameDuration(MediaRecorder.class, sz2);
                if (minFrameDuration > 3.3222591362126246E7d) {
                    Log.i(MandatoryStreamCombination.TAG, "External camera " + this.mCameraId + " has max video size:" + sz2);
                    return sz2;
                }
            }
            Log.w(MandatoryStreamCombination.TAG, "Camera " + this.mCameraId + " does not support any 30fps video output");
            return FULLHD;
        }

        private Size getMaxPreviewSize(List<Size> orderedPreviewSizes) {
            if (orderedPreviewSizes != null) {
                for (Size size : orderedPreviewSizes) {
                    if (this.mDisplaySize.getWidth() >= size.getWidth() && this.mDisplaySize.getHeight() >= size.getHeight()) {
                        return size;
                    }
                }
            }
            Log.w(MandatoryStreamCombination.TAG, "Camera " + this.mCameraId + " maximum preview size search failed with display size " + this.mDisplaySize);
            return this.kPreviewSizeBound;
        }

        /* loaded from: classes.dex */
        public static class SizeComparator implements Comparator<Size> {
            public int compare(Size lhs, Size rhs) {
                return StreamConfigurationMap.compareSizes(lhs.getWidth(), lhs.getHeight(), rhs.getWidth(), rhs.getHeight());
            }
        }

        private static List<Size> getAscendingOrderSizes(List<Size> sizeList, boolean ascending) {
            Comparator<Size> comparator = new SizeComparator();
            List<Size> sortedSizes = new ArrayList<>();
            sortedSizes.addAll(sizeList);
            Collections.sort(sortedSizes, comparator);
            if (!ascending) {
                Collections.reverse(sortedSizes);
            }
            return sortedSizes;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.hardware.camera2.params.MandatoryStreamCombination$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$hardware$camera2$params$MandatoryStreamCombination$SizeThreshold;

        static {
            int[] iArr = new int[SizeThreshold.values().length];
            $SwitchMap$android$hardware$camera2$params$MandatoryStreamCombination$SizeThreshold = iArr;
            try {
                iArr[SizeThreshold.s1440p.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$hardware$camera2$params$MandatoryStreamCombination$SizeThreshold[SizeThreshold.VGA.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
}
