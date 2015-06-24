package com.nicusa.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by mchurch on 6/23/15.
 */
public class NotificationSubjectAttributeConverterTest {

    private NotificationSubjectAttributeConverter notificationSubjectAttributeConverter =
            new NotificationSubjectAttributeConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        assertThat(notificationSubjectAttributeConverter.convertToDatabaseColumn(NotificationSubject.ADVERSE_EFFECTS),
                is("ADVERSE_EFFECTS"));
    }

    @Test
    public void testConvertToEntityAttribute() throws Exception {
        assertThat(notificationSubjectAttributeConverter.convertToEntityAttribute("ADVERSE_EFFECTS"),
                is(NotificationSubject.ADVERSE_EFFECTS));
    }

}