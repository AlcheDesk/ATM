package com.meowlomo.atm.core.service.util;

import javassist.tools.rmi.ObjectNotFoundException;

public interface RunSetResultUtilService {

    void finishDanglingRunSetResult();

    Object terminateRunSetResult(Long runSetId) throws ObjectNotFoundException;

}
