package com.meowlomo.atm.core.service.util;

import java.util.Set;

public interface RunSetAliasNameMapUtilService {
    Set<Long> getRunSetIdsFromAliasNames(Set<String> aliasNames);
}
