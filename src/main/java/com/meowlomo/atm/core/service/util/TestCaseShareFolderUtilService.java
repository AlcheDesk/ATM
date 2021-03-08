package com.meowlomo.atm.core.service.util;

import com.meowlomo.atm.core.model.TestCaseShareFolder;

public interface TestCaseShareFolderUtilService {

    TestCaseShareFolder copyByPrimaryId(Long id, boolean nameIncrement);

}
