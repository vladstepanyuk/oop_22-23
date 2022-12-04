//
// Created by io on 24.10.2022.
//

#include "Printer.h"

namespace {
    const std::string String1_06Format = "#Life 1.06";
    const std::string StringName = "#N ";
    const std::string StringRule = "#R ";
    const std::string StringSize = "#S ";
    const char CharSpace = ' ';
}

void lifeConway::Printer::printTerminal(Field field) {
    std::vector<std::string> y = field.returnField();
    for (auto & i : y) {
        for (char j : i) {
            std::cout << j;
        }
        std::cout << std::endl;
    }
    std::cout << std::endl;
}

void lifeConway::Printer::printFile(Field field, std::ostream &outStream) {
    outStream << String1_06Format << std::endl;
    outStream << StringName << field.returnName() << std::endl;
    outStream << StringRule << field.returnRules() << std::endl;
    std::vector<std::string> field1 = field.returnField();
    outStream << StringSize << field1.size() << CharSpace << field1[0].size() << std::endl;
    for (int y = 0; y < field1.size(); ++y)
        for (int x = 0; x < field1[y].size(); ++x)
            if (field1[y][x] == CharIsAlive)
                outStream << x << " " << y << std::endl;
}
