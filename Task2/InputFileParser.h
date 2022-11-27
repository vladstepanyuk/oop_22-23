#pragma once
#include "Field.h"
#include <string>
#include <fstream>
#include <cctype>

namespace lifeConway {
    class InputFileParser {
    public:
        Field pars(char* inputFileName);
    };
}
