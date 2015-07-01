package com.nicusa.domain;

import com.nicusa.UiApplication;
import com.nicusa.resource.UserProfileResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@ActiveProfiles("hsqldb")
public class UserProfileIT {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    public void shouldPersistMergeFindRefreshAndDelete() {

        final UserProfile persistedUserProfile = transactionTemplate.execute(new TransactionCallback<UserProfile>() {
            @Override
            public UserProfile doInTransaction(TransactionStatus transactionStatus) {
                UserProfile userProfile = testUserProfile();
                assertThat(userProfile.getId(), is(nullValue()));
                entityManager.persist(userProfile);
                assertThat(userProfile.getId(), is(not(nullValue())));
                assertThat(userProfile.getId(), is(greaterThan(UserProfile.ANONYMOUS_USER_PROFILE_ID)));
                return userProfile;
            }
        });

        final Long persistedUserProfileId = persistedUserProfile.getId();
        persistedUserProfile.setName("Angry Unikitty");

        final UserProfile mergedUserProfile = transactionTemplate.execute(new TransactionCallback<UserProfile>() {
            @Override
            public UserProfile doInTransaction(TransactionStatus transactionStatus) {
                UserProfile userProfile = entityManager.merge(persistedUserProfile);
                assertThat(userProfile.getId(), is(persistedUserProfileId));
                assertThat(userProfile.getName(), is("Angry Unikitty"));
                return userProfile;
            }
        });

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                UserProfile userProfile = entityManager.find(UserProfile.class, persistedUserProfileId);
                assertThat(userProfile.getId(), is(persistedUserProfileId));
                assertThat(userProfile.getName(), is("Angry Unikitty"));
            }
        });

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                UserProfile userProfile = entityManager.find(UserProfile.class, persistedUserProfileId);
                assertThat(userProfile.getName(), is("Angry Unikitty"));
                userProfile.setName("Unikitty");
                assertThat(userProfile.getName(), is("Unikitty"));
                entityManager.refresh(userProfile);
                assertThat(userProfile.getName(), is("Angry Unikitty"));
            }
        });

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                UserProfile userProfile = entityManager.find(UserProfile.class, persistedUserProfileId);
                entityManager.remove(userProfile);
            }
        });


        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                assertThat(entityManager.find(UserProfile.class, persistedUserProfileId), is(nullValue()));
            }
        });

    }

    private UserProfile testUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setName("Unikitty");
        return userProfile;
    }
}
