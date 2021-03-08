package com.meowlomo.atm.core.service.filter;

import com.meowlomo.atm.core.model.Element;

public interface ElementContentFilterService {

    Element convertParameterJsonNamingConvention(Element record);

    Element generateAPIDetailsFromURl(Element record);
}
