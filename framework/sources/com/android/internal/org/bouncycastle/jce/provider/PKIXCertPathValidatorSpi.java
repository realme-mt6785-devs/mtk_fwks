package com.android.internal.org.bouncycastle.jce.provider;

import com.android.internal.org.bouncycastle.asn1.x500.X500Name;
import com.android.internal.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.internal.org.bouncycastle.asn1.x509.Extension;
import com.android.internal.org.bouncycastle.asn1.x509.TBSCertificate;
import com.android.internal.org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import com.android.internal.org.bouncycastle.jcajce.PKIXExtendedParameters;
import com.android.internal.org.bouncycastle.jcajce.util.BCJcaJceHelper;
import com.android.internal.org.bouncycastle.jcajce.util.JcaJceHelper;
import com.android.internal.org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import com.android.internal.org.bouncycastle.x509.ExtendedPKIXParameters;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes4.dex */
public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathValidatorSpi() {
        this(false);
    }

    public PKIXCertPathValidatorSpi(boolean isForCRLCheck) {
        this.helper = new BCJcaJceHelper();
        this.isForCRLCheck = isForCRLCheck;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class NoPreloadHolder {
        private static final CertBlocklist blocklist = new CertBlocklist();

        private NoPreloadHolder() {
        }
    }

    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters params) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters paramsPKIX;
        List certs;
        CertPath certPath2;
        AnnotatedException e;
        int explicitPolicy;
        int inhibitAnyPolicy;
        int policyMapping;
        CertPath certPath3;
        X500Name workingIssuerName;
        PublicKey workingPublicKey;
        ProvCrlRevocationChecker revocationChecker;
        Set criticalExtensions;
        PKIXNameConstraintValidator nameConstraintValidator;
        List[] policyNodes;
        List pathCheckers;
        PublicKey workingPublicKey2;
        int policyMapping2;
        int inhibitAnyPolicy2;
        int maxPathLength;
        PKIXPolicyNode validPolicyTree;
        Set criticalExtensions2;
        CertPathValidatorException e2;
        PKIXCertPathValidatorSpi pKIXCertPathValidatorSpi = this;
        CertPath certPath4 = certPath;
        if (params instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder paramsPKIXBldr = new PKIXExtendedParameters.Builder((PKIXParameters) params);
            if (params instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extPKIX = (ExtendedPKIXParameters) params;
                paramsPKIXBldr.setUseDeltasEnabled(extPKIX.isUseDeltasEnabled());
                paramsPKIXBldr.setValidityModel(extPKIX.getValidityModel());
            }
            paramsPKIX = paramsPKIXBldr.build();
        } else if (params instanceof PKIXExtendedBuilderParameters) {
            paramsPKIX = ((PKIXExtendedBuilderParameters) params).getBaseParameters();
        } else if (params instanceof PKIXExtendedParameters) {
            paramsPKIX = (PKIXExtendedParameters) params;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        }
        if (paramsPKIX.getTrustAnchors() != null) {
            List certs2 = certPath.getCertificates();
            int i = certs2.size();
            if (!certs2.isEmpty()) {
                X509Certificate cert = (X509Certificate) certs2.get(0);
                if (cert != null) {
                    BigInteger serial = cert.getSerialNumber();
                    if (NoPreloadHolder.blocklist.isSerialNumberBlockListed(serial)) {
                        String message = "Certificate revocation of serial 0x" + serial.toString(16);
                        System.out.println(message);
                        AnnotatedException e3 = new AnnotatedException(message);
                        throw new CertPathValidatorException(e3.getMessage(), e3, certPath4, 0);
                    }
                }
                Date currentDate = new Date();
                Date validityDate = CertPathValidatorUtilities.getValidityDate(paramsPKIX, currentDate);
                Set userInitialPolicySet = paramsPKIX.getInitialPolicies();
                try {
                    TrustAnchor trust = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certs2.get(certs2.size() - 1), paramsPKIX.getTrustAnchors(), paramsPKIX.getSigProvider());
                    if (trust != null) {
                        checkCertificate(trust.getTrustedCert());
                        PKIXExtendedParameters paramsPKIX2 = new PKIXExtendedParameters.Builder(paramsPKIX).setTrustAnchor(trust).build();
                        List[] policyNodes2 = new ArrayList[i + 1];
                        for (int j = 0; j < policyNodes2.length; j++) {
                            policyNodes2[j] = new ArrayList();
                        }
                        Set policySet = new HashSet();
                        policySet.add(RFC3280CertPathUtilities.ANY_POLICY);
                        PKIXPolicyNode validPolicyTree2 = new PKIXPolicyNode(new ArrayList(), 0, policySet, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
                        policyNodes2[0].add(validPolicyTree2);
                        PKIXNameConstraintValidator nameConstraintValidator2 = new PKIXNameConstraintValidator();
                        Set acceptablePolicies = new HashSet();
                        PKIXNameConstraintValidator nameConstraintValidator3 = nameConstraintValidator2;
                        if (paramsPKIX2.isExplicitPolicyRequired()) {
                            explicitPolicy = 0;
                        } else {
                            explicitPolicy = i + 1;
                        }
                        if (paramsPKIX2.isAnyPolicyInhibited()) {
                            inhibitAnyPolicy = 0;
                        } else {
                            inhibitAnyPolicy = i + 1;
                        }
                        if (paramsPKIX2.isPolicyMappingInhibited()) {
                            policyMapping = 0;
                        } else {
                            policyMapping = i + 1;
                        }
                        X509Certificate sign = trust.getTrustedCert();
                        if (sign != null) {
                            try {
                                workingIssuerName = PrincipalUtils.getSubjectPrincipal(sign);
                                workingPublicKey = sign.getPublicKey();
                            } catch (RuntimeException e4) {
                                ex = e4;
                                certPath3 = certPath4;
                                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", ex, certPath3, -1);
                            }
                        } else {
                            try {
                                workingIssuerName = PrincipalUtils.getCA(trust);
                                workingPublicKey = trust.getCAPublicKey();
                            } catch (RuntimeException e5) {
                                ex = e5;
                                certPath3 = certPath4;
                                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", ex, certPath3, -1);
                            }
                        }
                        try {
                            AlgorithmIdentifier workingAlgId = CertPathValidatorUtilities.getAlgorithmIdentifier(workingPublicKey);
                            workingAlgId.getAlgorithm();
                            workingAlgId.getParameters();
                            if (paramsPKIX2.getTargetConstraints() != null && !paramsPKIX2.getTargetConstraints().match((Certificate) ((X509Certificate) certs2.get(0)))) {
                                throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath4, 0);
                            }
                            List<PKIXCertPathChecker> pathCheckers2 = paramsPKIX2.getCertPathCheckers();
                            for (PKIXCertPathChecker pKIXCertPathChecker : pathCheckers2) {
                                pKIXCertPathChecker.init(false);
                            }
                            if (paramsPKIX2.isRevocationEnabled()) {
                                revocationChecker = new ProvCrlRevocationChecker(pKIXCertPathValidatorSpi.helper);
                            } else {
                                revocationChecker = null;
                            }
                            X509Certificate cert2 = null;
                            int index = certs2.size() - 1;
                            int policyMapping3 = policyMapping;
                            int maxPathLength2 = i;
                            Set acceptablePolicies2 = acceptablePolicies;
                            int explicitPolicy2 = explicitPolicy;
                            PublicKey workingPublicKey3 = workingPublicKey;
                            X500Name workingIssuerName2 = workingIssuerName;
                            X509Certificate sign2 = sign;
                            PKIXPolicyNode validPolicyTree3 = validPolicyTree2;
                            int inhibitAnyPolicy3 = inhibitAnyPolicy;
                            while (index >= 0) {
                                if (!NoPreloadHolder.blocklist.isPublicKeyBlockListed(workingPublicKey3)) {
                                    int i2 = i - index;
                                    X509Certificate cert3 = (X509Certificate) certs2.get(index);
                                    boolean verificationAlreadyPerformed = true;
                                    if (index != certs2.size() - 1) {
                                        verificationAlreadyPerformed = false;
                                    }
                                    try {
                                        checkCertificate(cert3);
                                        RFC3280CertPathUtilities.processCertA(certPath, paramsPKIX2, validityDate, revocationChecker, index, workingPublicKey3, verificationAlreadyPerformed, workingIssuerName2, sign2);
                                        RFC3280CertPathUtilities.processCertBC(certPath4, index, nameConstraintValidator3, pKIXCertPathValidatorSpi.isForCRLCheck);
                                        PKIXPolicyNode validPolicyTree4 = RFC3280CertPathUtilities.processCertE(certPath4, index, RFC3280CertPathUtilities.processCertD(certPath, index, acceptablePolicies2, validPolicyTree3, policyNodes2, inhibitAnyPolicy3, pKIXCertPathValidatorSpi.isForCRLCheck));
                                        RFC3280CertPathUtilities.processCertF(certPath4, index, validPolicyTree4, explicitPolicy2);
                                        if (i2 != i) {
                                            if (cert3 != null && cert3.getVersion() == 1) {
                                                if (i2 != 1 || !cert3.equals(trust.getTrustedCert())) {
                                                    throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath4, index);
                                                }
                                                nameConstraintValidator = nameConstraintValidator3;
                                                inhibitAnyPolicy2 = inhibitAnyPolicy3;
                                                policyMapping2 = policyMapping3;
                                                maxPathLength = maxPathLength2;
                                                policyNodes = policyNodes2;
                                                pathCheckers = pathCheckers2;
                                            }
                                            RFC3280CertPathUtilities.prepareNextCertA(certPath4, index);
                                            policyNodes = policyNodes2;
                                            PKIXPolicyNode validPolicyTree5 = RFC3280CertPathUtilities.prepareCertB(certPath4, index, policyNodes, validPolicyTree4, policyMapping3);
                                            RFC3280CertPathUtilities.prepareNextCertG(certPath4, index, nameConstraintValidator3);
                                            int explicitPolicy3 = RFC3280CertPathUtilities.prepareNextCertH1(certPath4, index, explicitPolicy2);
                                            int policyMapping4 = RFC3280CertPathUtilities.prepareNextCertH2(certPath4, index, policyMapping3);
                                            int inhibitAnyPolicy4 = RFC3280CertPathUtilities.prepareNextCertH3(certPath4, index, inhibitAnyPolicy3);
                                            int explicitPolicy4 = RFC3280CertPathUtilities.prepareNextCertI1(certPath4, index, explicitPolicy3);
                                            int policyMapping5 = RFC3280CertPathUtilities.prepareNextCertI2(certPath4, index, policyMapping4);
                                            int inhibitAnyPolicy5 = RFC3280CertPathUtilities.prepareNextCertJ(certPath4, index, inhibitAnyPolicy4);
                                            RFC3280CertPathUtilities.prepareNextCertK(certPath4, index);
                                            nameConstraintValidator = nameConstraintValidator3;
                                            int maxPathLength3 = RFC3280CertPathUtilities.prepareNextCertM(certPath4, index, RFC3280CertPathUtilities.prepareNextCertL(certPath4, index, maxPathLength2));
                                            RFC3280CertPathUtilities.prepareNextCertN(certPath4, index);
                                            Set criticalExtensions3 = cert3.getCriticalExtensionOIDs();
                                            if (criticalExtensions3 != null) {
                                                validPolicyTree = validPolicyTree5;
                                                Set criticalExtensions4 = new HashSet(criticalExtensions3);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                                criticalExtensions2 = criticalExtensions4;
                                            } else {
                                                validPolicyTree = validPolicyTree5;
                                                criticalExtensions2 = new HashSet();
                                            }
                                            pathCheckers = pathCheckers2;
                                            RFC3280CertPathUtilities.prepareNextCertO(certPath4, index, criticalExtensions2, pathCheckers);
                                            sign2 = cert3;
                                            workingIssuerName2 = PrincipalUtils.getSubjectPrincipal(sign2);
                                            try {
                                                try {
                                                    workingPublicKey2 = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), index, pKIXCertPathValidatorSpi.helper);
                                                    AlgorithmIdentifier workingAlgId2 = CertPathValidatorUtilities.getAlgorithmIdentifier(workingPublicKey2);
                                                    workingAlgId2.getAlgorithm();
                                                    workingAlgId2.getParameters();
                                                    inhibitAnyPolicy3 = inhibitAnyPolicy5;
                                                    policyMapping3 = policyMapping5;
                                                    maxPathLength2 = maxPathLength3;
                                                    explicitPolicy2 = explicitPolicy4;
                                                    validPolicyTree3 = validPolicyTree;
                                                    int maxPathLength4 = index - 1;
                                                    certPath4 = certPath4;
                                                    i = i;
                                                    pathCheckers2 = pathCheckers;
                                                    policyNodes2 = policyNodes;
                                                    certs2 = certs2;
                                                    policySet = policySet;
                                                    trust = trust;
                                                    currentDate = currentDate;
                                                    nameConstraintValidator3 = nameConstraintValidator;
                                                    acceptablePolicies2 = acceptablePolicies2;
                                                    workingPublicKey3 = workingPublicKey2;
                                                    cert2 = cert3;
                                                    index = maxPathLength4;
                                                    pKIXCertPathValidatorSpi = this;
                                                } catch (CertPathValidatorException e6) {
                                                    e2 = e6;
                                                    throw new CertPathValidatorException("Next working key could not be retrieved.", e2, certPath4, index);
                                                }
                                            } catch (CertPathValidatorException e7) {
                                                e2 = e7;
                                            }
                                        } else {
                                            nameConstraintValidator = nameConstraintValidator3;
                                            inhibitAnyPolicy2 = inhibitAnyPolicy3;
                                            policyMapping2 = policyMapping3;
                                            maxPathLength = maxPathLength2;
                                            policyNodes = policyNodes2;
                                            pathCheckers = pathCheckers2;
                                        }
                                        maxPathLength2 = maxPathLength;
                                        validPolicyTree3 = validPolicyTree4;
                                        explicitPolicy2 = explicitPolicy2;
                                        workingPublicKey2 = workingPublicKey3;
                                        inhibitAnyPolicy3 = inhibitAnyPolicy2;
                                        policyMapping3 = policyMapping2;
                                        int maxPathLength42 = index - 1;
                                        certPath4 = certPath4;
                                        i = i;
                                        pathCheckers2 = pathCheckers;
                                        policyNodes2 = policyNodes;
                                        certs2 = certs2;
                                        policySet = policySet;
                                        trust = trust;
                                        currentDate = currentDate;
                                        nameConstraintValidator3 = nameConstraintValidator;
                                        acceptablePolicies2 = acceptablePolicies2;
                                        workingPublicKey3 = workingPublicKey2;
                                        cert2 = cert3;
                                        index = maxPathLength42;
                                        pKIXCertPathValidatorSpi = this;
                                    } catch (AnnotatedException e8) {
                                        throw new CertPathValidatorException(e8.getMessage(), e8.getUnderlyingException(), certPath4, index);
                                    }
                                } else {
                                    String message2 = "Certificate revocation of public key " + workingPublicKey3;
                                    System.out.println(message2);
                                    AnnotatedException e9 = new AnnotatedException(message2);
                                    throw new CertPathValidatorException(e9.getMessage(), e9, certPath4, index);
                                }
                            }
                            int explicitPolicy5 = RFC3280CertPathUtilities.wrapupCertB(certPath4, index + 1, RFC3280CertPathUtilities.wrapupCertA(explicitPolicy2, cert2));
                            Set criticalExtensions5 = cert2.getCriticalExtensionOIDs();
                            if (criticalExtensions5 != null) {
                                Set criticalExtensions6 = new HashSet(criticalExtensions5);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                                criticalExtensions6.remove(Extension.extendedKeyUsage.getId());
                                criticalExtensions = criticalExtensions6;
                            } else {
                                criticalExtensions = new HashSet();
                            }
                            RFC3280CertPathUtilities.wrapupCertF(certPath4, index + 1, pathCheckers2, criticalExtensions);
                            PKIXPolicyNode intersection = RFC3280CertPathUtilities.wrapupCertG(certPath, paramsPKIX2, userInitialPolicySet, index + 1, policyNodes2, validPolicyTree3, acceptablePolicies2);
                            if (explicitPolicy5 > 0 || intersection != null) {
                                return new PKIXCertPathValidatorResult(trust, intersection, cert2.getPublicKey());
                            }
                            throw new CertPathValidatorException("Path processing failed on policy.", null, certPath4, index);
                        } catch (CertPathValidatorException e10) {
                            throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e10, certPath4, -1);
                        }
                    } else {
                        certs = certs2;
                        certPath2 = certPath4;
                        try {
                            throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath2, -1);
                        } catch (AnnotatedException e11) {
                            e = e11;
                            throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath2, certs.size() - 1);
                        }
                    }
                } catch (AnnotatedException e12) {
                    e = e12;
                    certs = certs2;
                    certPath2 = certPath4;
                }
            } else {
                throw new CertPathValidatorException("Certification path is empty.", null, certPath4, -1);
            }
        } else {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
    }

    static void checkCertificate(X509Certificate cert) throws AnnotatedException {
        try {
            TBSCertificate.getInstance(cert.getTBSCertificate());
        } catch (IllegalArgumentException e) {
            throw new AnnotatedException(e.getMessage());
        } catch (CertificateEncodingException e2) {
            throw new AnnotatedException("unable to process TBSCertificate", e2);
        }
    }
}
