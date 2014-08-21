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

package de.consol.sakuli.dao.impl;

import de.consol.sakuli.dao.DaoTestCaseStep;
import de.consol.sakuli.datamodel.TestCase;
import de.consol.sakuli.datamodel.TestCaseStep;
import de.consol.sakuli.exceptions.SakuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author tschneck
 *         Date: 12.07.13
 */
@Component
public class DaoTestCaseStepImpl extends Dao implements DaoTestCaseStep {

    @Autowired
    public DaoTestCaseStepImpl(DataSource dataSource) throws SakuliException {
        super(dataSource);
    }

    @Override
    public void saveTestCaseSteps(List<TestCaseStep> steps, int primaryKeyOfTestCase) {
        for (TestCaseStep step : steps) {
            logger.info("============== save STEP \"" + step.getName() + "\" ==============");
            MapSqlParameterSource stepParameters = new MapSqlParameterSource();
            stepParameters.addValue("sahi_cases_id", primaryKeyOfTestCase);
            stepParameters.addValue("result", step.getState().getErrorCode());
            stepParameters.addValue("result_desc", step.getState());
            stepParameters.addValue("name", step.getName());
            stepParameters.addValue("start", step.getStartDateTimeString());
            stepParameters.addValue("stop", step.getStopDateTimeString());
            stepParameters.addValue("warning", step.getWarningTime());
            stepParameters.addValue("duration", step.getDuration());

            logger.info("write the following values to 'sahi_steps': "
                    + stepParameters.getValues()
                    + "\n now execute ....");

            //generate the sql-statement
            SimpleJdbcInsert insertStepResults = new SimpleJdbcInsert(getDataSource())
                    .withTableName("sahi_steps")
                    .usingGeneratedKeyColumns("id");

            //execute the sql-statement and save the primary key
            try {
                int dbPrimaryKey = insertStepResults.executeAndReturnKey(stepParameters).intValue();
                logger.info("test case step '" + step.getName()
                        + "' has been written to 'sahi_steps' with  primaryKey=" + dbPrimaryKey);
                step.setDbPrimaryKey(dbPrimaryKey);
            } catch (DataAccessException e) {
                TestCase testCase = testSuite.getTestCaseByDBKey(primaryKeyOfTestCase);
                if (testCase != null) {
                    exceptionHandler.handleException(e);
                }
                exceptionHandler.handleException(e);
            }
        }
    }

    @Override
    public int getCountOfSahiSteps() {
        try {
            return this.getJdbcTemplate().queryForObject("select count(*) from sahi_steps", Integer.class);
        } catch (DataAccessException e) {
            exceptionHandler.handleException(e);
        }
        return -1;
    }
}