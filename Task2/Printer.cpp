//
// Created by io on 24.10.2022.
//

#include "Printer.h"

const char CharIsAlive = -79;

using namespace lifeConway;

void Printer::printTerminal(Field field) {
    std::vector<std::string> y = field.returnField();
    for (auto & i : y) {
        for (char j : i) {
            std::cout << j;
        }
        std::cout << std::endl;
    }
    std::cout << std::endl;
}

void Printer::printFile(Field field, const std::string &fileName) {
    std::ofstream outputFile(fileName);
    outputFile << "#Life 1.06" << std::endl;
    outputFile << "#N " << field.returnName() << std::endl;
    outputFile << "#R " << field.returnRules() << std::endl;
    std::vector<std::string> field1 = field.returnField();
    outputFile << "#S " << field1.size() << " " << field1[0].size() << std::endl;
    for (int y = 0; y < field1.size(); ++y)
        for (int x = 0; x < field1[y].size(); ++x)
            if (field1[y][x] == CharIsAlive)
                outputFile << x << " " << y << std::endl;
    outputFile.close();
}
