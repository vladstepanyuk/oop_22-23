//
// Created by io on 21.10.2022.
//

#pragma once
#include "Field.h"
#include <string>
#include <fstream>
#include <cctype>

class InputFileParser {
public:
    Field pars(char* inputFileName);
};