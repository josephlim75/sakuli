/*
 * Sakuli - Testing and Monitoring-Tool for Websites and common UIs.
 *
 * Copyright 2013 - 2016 the original author or authors.
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

package org.sakuli.starter.helper;

import org.apache.commons.cli.Options;
import org.testng.annotations.Test;

/**
 * @author tschneck
 *         Date: 2/13/16
 */
public class CmdPrintHelperTest {

    /**
     * If no exception will thrown, all should be fine.
     *
     * @throws Exception
     */
    @Test
    public void testPrint() throws Exception {
        CmdPrintHelper.printVersion();
        CmdPrintHelper.printHelp(new Options());
    }

}