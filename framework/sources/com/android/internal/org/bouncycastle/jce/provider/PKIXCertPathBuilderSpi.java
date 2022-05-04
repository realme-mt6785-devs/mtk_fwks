package com.android.internal.org.bouncycastle.jce.provider;

import android.media.MediaMetrics;
import com.android.internal.org.bouncycastle.jcajce.PKIXCertStore;
import com.android.internal.org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import com.android.internal.org.bouncycastle.jcajce.PKIXExtendedParameters;
import com.android.internal.org.bouncycastle.x509.ExtendedPKIXBuilderParameters;
import com.android.internal.org.bouncycastle.x509.ExtendedPKIXParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes4.dex */
public class PKIXCertPathBuilderSpi extends CertPathBuilderSpi {
    private Exception certPathException;
    private final boolean isForCRLCheck;

    public PKIXCertPathBuilderSpi() {
        this(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PKIXCertPathBuilderSpi(boolean isForCRLCheck) {
        this.isForCRLCheck = isForCRLCheck;
    }

    @Override // java.security.cert.CertPathBuilderSpi
    public CertPathBuilderResult engineBuild(CertPathParameters params) throws CertPathBuilderException, InvalidAlgorithmParameterException {
        PKIXExtendedBuilderParameters paramsPKIX;
        Exception exc;
        PKIXExtendedBuilderParameters.Builder paramsBldrPKIXBldr;
        if (params instanceof PKIXBuilderParameters) {
            PKIXExtendedParameters.Builder paramsPKIXBldr = new PKIXExtendedParameters.Builder((PKIXBuilderParameters) params);
            if (params instanceof ExtendedPKIXParameters) {
                ExtendedPKIXBuilderParameters extPKIX = (ExtendedPKIXBuilderParameters) params;
                for (PKIXCertStore pKIXCertStore : extPKIX.getAdditionalStores()) {
                    paramsPKIXBldr.addCertificateStore(pKIXCertStore);
                }
                paramsBldrPKIXBldr = new PKIXExtendedBuilderParameters.Builder(paramsPKIXBldr.build());
                paramsBldrPKIXBldr.addExcludedCerts(extPKIX.getExcludedCerts());
                paramsBldrPKIXBldr.setMaxPathLength(extPKIX.getMaxPathLength());
            } else {
                paramsBldrPKIXBldr = new PKIXExtendedBuilderParameters.Builder((PKIXBuilderParameters) params);
            }
            paramsPKIX = paramsBldrPKIXBldr.build();
        } else if (params instanceof PKIXExtendedBuilderParameters) {
            paramsPKIX = (PKIXExtendedBuilderParameters) params;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + PKIXExtendedBuilderParameters.class.getName() + MediaMetrics.SEPARATOR);
        }
        List certPathList = new ArrayList();
        Collection targets = CertPathValidatorUtilities.findTargets(paramsPKIX);
        CertPathBuilderResult result = null;
        Iterator targetIter = targets.iterator();
        while (targetIter.hasNext() && result == null) {
            X509Certificate cert = (X509Certificate) targetIter.next();
            result = build(cert, paramsPKIX, certPathList);
        }
        if (result != null || (exc = this.certPathException) == null) {
            if (!(result == null && this.certPathException == null)) {
                return result;
            }
            throw new CertPathBuilderException("Unable to find certificate chain.");
        } else if (exc instanceof AnnotatedException) {
            throw new CertPathBuilderException(this.certPathException.getMessage(), this.certPathException.getCause());
        } else {
            throw new CertPathBuilderException("Possible certificate chain could not be validated.", this.certPathException);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.security.cert.CertPathBuilderResult build(java.security.cert.X509Certificate r10, com.android.internal.org.bouncycastle.jcajce.PKIXExtendedBuilderParameters r11, java.util.List r12) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi.build(java.security.cert.X509Certificate, com.android.internal.org.bouncycastle.jcajce.PKIXExtendedBuilderParameters, java.util.List):java.security.cert.CertPathBuilderResult");
    }
}
