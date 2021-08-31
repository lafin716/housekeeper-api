package com.lafin.housekeeper.entity.listener;

import java.time.LocalDateTime;

public interface Auditable {

    LocalDateTime getCreated();

    LocalDateTime getUpdated();

    void setCreated(LocalDateTime created);

    void setUpdated(LocalDateTime updated);
}
