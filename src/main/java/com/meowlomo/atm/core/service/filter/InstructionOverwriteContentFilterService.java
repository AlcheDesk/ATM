package com.meowlomo.atm.core.service.filter;

import java.util.List;

import com.meowlomo.atm.core.model.InstructionOverwrite;

public interface InstructionOverwriteContentFilterService {

    List<InstructionOverwrite> expandInstructionOverwrite(List<InstructionOverwrite> instructionOverwrites);

}
