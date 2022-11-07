#pragma once
#include "Field.h"
#include <fstream>

class Printer {
public:
    void printTerminal(Field field);
    void printFile(Field field, const std::string& fileName);
};



