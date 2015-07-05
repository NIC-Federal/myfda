package com.nicusa.testing.tests.rules

import org.junit.rules.ExternalResource
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.TransactionCallbackWithoutResult
import org.springframework.transaction.support.TransactionTemplate
import javax.sql.DataSource

/**
 * Created by mchurch on 7/1/15.
 */
class ClearDatabaseBeforeAndAfterTest extends ExternalResource {

    private JdbcTemplate jdbcTemplate;
    private TransactionTemplate transactionTemplate;

    public ClearDatabaseBeforeAndAfterTest(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    protected void before() throws Throwable {
        clearDatabase();
    }

    @Override
    protected void after() {
        clearDatabase();
    }

    private void clearDatabase() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0")
                jdbcTemplate.execute("DELETE FROM ADVERSEEFFECTDESCRIPTION")
                jdbcTemplate.execute("DELETE FROM DRUG")
                jdbcTemplate.execute("DELETE FROM NOTIFICATIONSETTING")
                jdbcTemplate.execute("DELETE FROM PORTFOLIO")
                jdbcTemplate.execute("DELETE FROM PORTFOLIO_DRUG")
                jdbcTemplate.execute("DELETE FROM SEQUENCE ")
                jdbcTemplate.execute("DELETE FROM USERPROFILE")
                jdbcTemplate.execute("DELETE FROM USERPROFILE_NOTIFICATIONSETTING")
                jdbcTemplate.execute("DELETE FROM UserConnection")
                jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1")
            }
        })

    }
}
