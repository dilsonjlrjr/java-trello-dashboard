package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.exception.SecurityResourceException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumSecurityResourceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumSecurityResourceMessage;
import org.springframework.stereotype.Service;

@Service
public class SecurityResourceService {

    public void doValidateIdTokenAccessResource(Long idResourceConsulting, Long idUserSession) {
        if (!idResourceConsulting.equals(idUserSession))
            throw new SecurityResourceException(EnumSecurityResourceMessage.UNAUTHORIZED_ACCESS_RESOURCE.getMessage(),
                    EnumSecurityResourceCode.UNAUTHORIZED_ACCESS_RESOURCE.getCode());
    }
}
