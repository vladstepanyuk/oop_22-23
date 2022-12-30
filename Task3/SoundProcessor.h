#pragma once

#include "ProgramContext.h"
#include "Factory.h"
#include <cstdio>
#include <filesystem>
#include <strstream>



namespace soundProcessor {

    std::pair<std::string, std::vector<int>> parsConfigLine(const std::string &strBuff);

    class SoundProcessor {
    public:
        void makeTransformations(ProgramContext &context);
    };

}