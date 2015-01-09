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

package de.consol.sakuli.loader;

import de.consol.sakuli.actions.environment.CipherUtil;
import de.consol.sakuli.actions.screenbased.ScreenshotActions;
import de.consol.sakuli.actions.settings.ScreenBasedSettings;
import de.consol.sakuli.datamodel.TestCase;
import de.consol.sakuli.datamodel.TestSuite;
import de.consol.sakuli.datamodel.actions.ImageLib;
import de.consol.sakuli.datamodel.actions.Screen;
import de.consol.sakuli.datamodel.properties.ActionProperties;
import de.consol.sakuli.datamodel.properties.SahiProxyProperties;
import de.consol.sakuli.datamodel.properties.SakuliProperties;
import de.consol.sakuli.exceptions.SakuliExceptionHandler;
import net.sf.sahi.report.Report;
import net.sf.sahi.rhino.RhinoScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

/**
 * Loads all necessary objects for the {@link de.consol.sakuli.actions.screenbased.ScreenshotActions}.
 *
 * @author tschneck Date: 18.10.13
 */
@Component
public class ScreenActionLoaderImpl implements ScreenActionLoader {

    @Qualifier(BaseActionLoaderImpl.QUALIFIER)
    @Autowired
    private BaseActionLoader baseLoader;

    @Autowired
    private ScreenBasedSettings settings;
    @Autowired
    private ScreenshotActions screenshotActions;
    @Autowired
    private Screen screen;

    @Override
    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    @Override
    public ScreenshotActions getScreenshotActions() {
        return screenshotActions;
    }

    public void setScreenshotActions(ScreenshotActions screenshotActions) {
        this.screenshotActions = screenshotActions;
    }

    @Override
    public BaseActionLoader getBaseLoader() {
        return baseLoader;
    }

    public void setBaseLoader(BaseActionLoader baseLoader) {
        this.baseLoader = baseLoader;
    }

    @Override
    public void loadSettingDefaults() {
        getSettings().setDefaults();
    }

    @Override
    public ScreenBasedSettings getSettings() {
        return settings;
    }

    public void setSettings(ScreenBasedSettings settings) {
        this.settings = settings;
    }

    @Override
    public CipherUtil getCipherUtil() {
        return baseLoader.getCipherUtil();
    }

    @Override
    public void init(String testCaseID, String... imagePaths) {
        baseLoader.init(testCaseID, imagePaths);
    }

    @Override
    public void init(String testCaseID, Path... imagePaths) {
        baseLoader.init(testCaseID, imagePaths);
    }

    @Override
    public SakuliProperties getSakuliProperties() {
        return baseLoader.getSakuliProperties();
    }

    @Override
    public ActionProperties getActionProperties() {
        return baseLoader.getActionProperties();
    }

    @Override
    public SahiProxyProperties getSahiProxyProperties() {
        return baseLoader.getSahiProxyProperties();
    }

    @Override
    public SakuliExceptionHandler getExceptionHandler() {
        return baseLoader.getExceptionHandler();
    }

    @Override
    public TestSuite getTestSuite() {
        return baseLoader.getTestSuite();
    }

    @Override
    public TestCase getCurrentTestCase() {
        return baseLoader.getCurrentTestCase();
    }

    @Override
    public void setCurrentTestCase(TestCase testCase) {
        baseLoader.setCurrentTestCase(testCase);
    }

    @Override
    public ImageLib getImageLib() {
        return baseLoader.getImageLib();
    }

    @Override
    public RhinoScriptRunner getRhinoScriptRunner() {
        return baseLoader.getRhinoScriptRunner();
    }

    @Override
    public void setRhinoScriptRunner(RhinoScriptRunner scriptRunner) {
        baseLoader.setRhinoScriptRunner(scriptRunner);
    }

    @Override
    public Report getSahiReport() {
        return baseLoader.getSahiReport();
    }
}
