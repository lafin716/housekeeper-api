package com.lafin.housekeeper.entity.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class DateEntityListener {

    @PrePersist
    public void prePersist(Object o) {
        if (o instanceof Auditable) {
            ((Auditable) o).setCreated(LocalDateTime.now());
            ((Auditable) o).setUpdated(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o) {
        if (o instanceof Auditable) {
            ((Auditable) o).setUpdated(LocalDateTime.now());
        }
    }
}
