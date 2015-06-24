package com.nicusa.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by mchurch on 6/23/15.
 */
public class NotificationTypeAttributeConverterTest {

    private NotificationTypeAttributeConverter notificationTypeAttributeConverter =
            new NotificationTypeAttributeConverter();

    @Test
    public void testConvertToDatabaseColumn() throws Exception {
        assertThat(notificationTypeAttributeConverter.convertToDatabaseColumn(NotificationType.ALL),
                is("ALL"));
    }

    @Test
    public void testConvertToEntityAttribute() throws Exception {
        assertThat(notificationTypeAttributeConverter.convertToEntityAttribute("ALL"),
                is(NotificationType.ALL));
    }
}