//
// Created by io on 21.10.2022.
//
#include "InputFileParser.h"

const char CharZero = '0';
const char CharNine = '9';
const char CharIsNotAlive = -78;
const char CharIsAlive = -79;

Field InputFileParser::pars(char *inputFileName) {
    Field field;
    std::string str;
    std::ifstream inputFile(inputFileName);

    if (std::getline(inputFile, str) && str != "#Life 1.06") {
        throw std::exception("wrong file format");
        return field;
    }

    std::string name;
    if (std::getline(inputFile, str) && str.substr(0, 2) != "#N") {
        throw std::exception("wrong file format");
        return field;
    }
    name = str.substr(3);

    std::vector<char> B;
    std::vector<char> S;
    if (std::getline(inputFile, str) && str.substr(0, 4) != "#R B") {
        throw std::exception("wrong file format");
        return field;
    }
    int i;
    for (i = 4; std::isdigit(str[i]); ++i) B.push_back(str[i] - CharZero);
    if (str.substr(i, 2) != "/S") {
        throw std::exception("wrong file format");
        return field;
    }
    for (i += 2; std::isdigit(str[i]); ++i) S.push_back(str[i] - CharZero);

    int n, m;
    inputFile >> str;
    if (str != "#S") {
        throw std::exception("wrong file format");
        return field;
    }
    inputFile >> str;
    char *err;
    n = strtol(str.c_str(), &err, 10);
    if (*err != 0) {
        throw std::exception("wrong file format");
        return field;
    }
    inputFile >> str;
    m = strtol(str.c_str(), &err, 10);
    if (*err != 0) {
        throw std::exception("wrong file format");
        return field;
    }

    std::vector<std::vector<char>> field1;
    for (i = 0; i < n; ++i) {
        field1.push_back(std::vector<char>(m, CharIsNotAlive));
    }

    for (int x, y; inputFile >> str;) {
        x = strtol(str.c_str(), &err, 10);
        if (*err != 0) {
            throw std::exception("wrong file format");
            return field;
        }
        inputFile >> str;
        y = strtol(str.c_str(), &err, 10);
        if (*err != 0) {
            throw std::exception("wrong file format");
            return field;
        }
        field1[y][x] = CharIsAlive;
    }

    field = Field(field1, B, S, name);

    inputFile.close();
    return field;
}
