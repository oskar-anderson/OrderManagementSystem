package com.example.ordermanagementsystem.util;

import java.time.OffsetDateTime;

public class Const {
    /**
        Max date for setting validity periods for soft-deleting data.
        For example Product cannot be deleted, but its end date can be changed.
        Think of this as OffsetDateTime.max() that database will not reject.
     */
    static final OffsetDateTime MAX_OFFSET_DATE_TIME = OffsetDateTime.parse("9999-12-31T23:59:59.999999999-18:00");
}
