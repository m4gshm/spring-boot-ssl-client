package javax.net.ssl;

import java.security.KeyStore;

public class NoValidationTrustManagerFactory extends TrustManagerFactory {

    public static final TrustManager[] NO_VALIDATION_TRUST_MANAGER = {new NoValidationX509TrustManager()};
    public static final NoValidationTrustManagerFactory INSTANCE = new NoValidationTrustManagerFactory();

    public NoValidationTrustManagerFactory() {
        super(new TrustManagerFactorySpi() {
            @Override
            protected void engineInit(KeyStore ks) {

            }

            @Override
            protected void engineInit(ManagerFactoryParameters spec) {

            }

            @Override
            protected TrustManager[] engineGetTrustManagers() {
                return NO_VALIDATION_TRUST_MANAGER;
            }
        }, null, getDefaultAlgorithm());
    }
}
