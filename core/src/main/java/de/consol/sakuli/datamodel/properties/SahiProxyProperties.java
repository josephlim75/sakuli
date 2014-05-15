/*
 * Sakuli - Testing and Monitoring-Tool for Websites and common UIs.
 *
 * Copyright 2013 - 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.consol.sakuli.datamodel.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author tschneck Date: 14.05.14
 */
@Component
public class SahiProxyProperties extends AbstractProperties {

    public static final String PROXY_HOME_FOLDER = "sahi.proxy.homePath";
    public static final String PROXY_CONFIG_FOLDER = "sahi.proxy.configurationPath";
    public static final String PROXY_PORT = "sahi.proxy.port";
    public static final String MAX_CONNECT_TRIES = "sahi.proxy.maxConnectTries";
    public static final String RECONNECT_SECONDS = "sahi.proxy.reconnectSeconds";

    @Value("${" + PROXY_HOME_FOLDER + "}")
    private String sahiHomeFolderPropertyValue;
    private Path sahiHomeFolder;
    @Value("${" + PROXY_CONFIG_FOLDER + "}")
    private String sahiConfigFolderPropertyValue;
    private Path sahiConfigFolder;
    @Value("${" + PROXY_PORT + "}")
    private Integer proxyPort;
    @Value("${" + RECONNECT_SECONDS + "}")
    private Integer reconnectSeconds;
    @Value("${" + MAX_CONNECT_TRIES + "}")
    private Integer maxConnectTries;

    @PostConstruct
    public void initFolders() throws FileNotFoundException {
        sahiHomeFolder = Paths.get(sahiHomeFolderPropertyValue).normalize();
        sahiConfigFolder = Paths.get(sahiConfigFolderPropertyValue).normalize();
        checkFolders(sahiHomeFolder, sahiConfigFolder);
    }

    public String getSahiHomeFolderPropertyValue() {
        return sahiHomeFolderPropertyValue;
    }

    public void setSahiHomeFolderPropertyValue(String sahiHomeFolderPropertyValue) {
        this.sahiHomeFolderPropertyValue = sahiHomeFolderPropertyValue;
    }

    public String getSahiConfigFolderPropertyValue() {
        return sahiConfigFolderPropertyValue;
    }

    public void setSahiConfigFolderPropertyValue(String sahiConfigFolderPropertyValue) {
        this.sahiConfigFolderPropertyValue = sahiConfigFolderPropertyValue;
    }

    public Path getSahiHomeFolder() {
        return sahiHomeFolder;
    }

    public void setSahiHomeFolder(Path sahiHomeFolder) {
        this.sahiHomeFolder = sahiHomeFolder;
    }

    public Path getSahiConfigFolder() {
        return sahiConfigFolder;
    }

    public void setSahiConfigFolder(Path sahiConfigFolder) {
        this.sahiConfigFolder = sahiConfigFolder;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public Integer getReconnectSeconds() {
        return reconnectSeconds;
    }

    public void setReconnectSeconds(Integer reconnectSeconds) {
        this.reconnectSeconds = reconnectSeconds;
    }

    public Integer getMaxConnectTries() {
        return maxConnectTries;
    }

    public void setMaxConnectTries(Integer maxConnectTries) {
        this.maxConnectTries = maxConnectTries;
    }
}
