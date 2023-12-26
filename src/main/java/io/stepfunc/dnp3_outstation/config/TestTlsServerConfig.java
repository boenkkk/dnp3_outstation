package io.stepfunc.dnp3_outstation.config;

import io.stepfunc.dnp3.CertificateMode;
import io.stepfunc.dnp3.TlsServerConfig;

public class TestTlsServerConfig {

    public static TlsServerConfig getSelfSignedTlsConfig() {
        // ANCHOR: tls_self_signed_config
        TlsServerConfig config =
                new TlsServerConfig(
                        "test.com",
                        "./certs/self_signed/entity1_cert.pem",
                        "./certs/self_signed/entity2_cert.pem",
                        "./certs/self_signed/entity2_key.pem",
                        "" // no password
                ).withCertificateMode(CertificateMode.SELF_SIGNED);
        // ANCHOR_END: tls_self_signed_config

        return config;
    }

    public static TlsServerConfig getCaTlsConfig() {
        // ANCHOR: tls_ca_chain_config
        TlsServerConfig config =
                new TlsServerConfig(
                        "test.com",
                        "./certs/ca_chain/ca_cert.pem",
                        "./certs/ca_chain/entity2_cert.pem",
                        "./certs/ca_chain/entity2_key.pem",
                        "" // no password
                );
        // ANCHOR_END: tls_ca_chain_config

        return config;
    }
}
