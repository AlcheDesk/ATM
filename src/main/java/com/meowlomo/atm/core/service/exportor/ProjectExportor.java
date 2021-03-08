package com.meowlomo.atm.core.service.exportor;

import java.io.ByteArrayOutputStream;

import com.meowlomo.atm.core.model.Project;

public interface ProjectExportor {

	ByteArrayOutputStream exportProjectToExcel(Project project);
	
}
