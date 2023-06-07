package com.company.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum EnumStatus {
    ACTIVE(1),IN_PROGRESS(2), SUCCESS(3);
    public long value;
}
