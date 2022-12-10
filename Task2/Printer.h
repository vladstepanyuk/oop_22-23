#pragma once
#include "Field.h"
#include <fstream>

namespace lifeConway{
    class Printer {
    public:
        void printTerminal(Field field);
        void printFile(Field field, std::ostream &outStream);
    };


}


