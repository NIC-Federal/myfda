package com.nicusa;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

public class SslTomcatConnectionCustomizer implements TomcatConnectorCustomizer
{
    private String absoluteKeystoreFile;
    private String keystorePassword;
    private String keystoreType;
    private String keystoreAlias;

    public SslTomcatConnectionCustomizer(String absoluteKeystoreFile, String keystorePassword, String keystoreType, String keystoreAlias)
    {
        this.absoluteKeystoreFile = absoluteKeystoreFile;
        this.keystorePassword = keystorePassword;
        this.keystoreType = keystoreType;
        this.keystoreAlias = keystoreAlias;
    }

    @Override
    public void customize (Connector connector)
    {
        if (absoluteKeystoreFile != null)
        {
            connector.setPort(8443);
            connector.setSecure(true);
            connector.setScheme("https");

            connector.setAttribute("SSLEnabled", true);
            connector.setAttribute("sslProtocol", "TLS");
            connector.setAttribute("protocol", "org.apache.coyote.http11.Http11Protocol");
            connector.setAttribute("clientAuth", false);
            connector.setAttribute("keystoreFile", absoluteKeystoreFile);
            connector.setAttribute("keystoreType", keystoreType);
            connector.setAttribute("keystorePass", keystorePassword);
            connector.setAttribute("keystoreAlias", keystoreAlias);
            connector.setAttribute("keyPass", keystorePassword);
        }
    }

}
