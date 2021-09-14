package com.lafin.housekeeper.component.resttemplate;

import org.springframework.util.MultiValueMap;

public interface Request {
    MultiValueMap toQueryParameters();
}
