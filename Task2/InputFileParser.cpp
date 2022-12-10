//
// Created by io on 21.10.2022.
//
#include "InputFileParser.h"

namespace {
    const std::string String1_06Format = "#Life 1.06";
    const std::string StringName = "#N";
    const std::string RuleBirth = "#R B";
    const std::string StrSharpS = "/S";
    const std::string StingSize = "#S";
}

lifeConway::Field lifeConway::InputFileParser::pars(std::istream &inputStream) {
    std::string str;

    if (std::getline(inputStream, str) && str != String1_06Format) {
        throw std::exception("wrong file format");
    }

    std::string name;
    if (std::getline(inputStream, str) && str.substr(0, StringName.size()) != StringName) {
        throw std::exception("wrong file format");
    }
    name = str.substr(StringName.size() + 1);

    std::vector<char> B;
    std::vector<char> S;
    if (std::getline(inputStream, str) && str.substr(0, RuleBirth.size()) != RuleBirth) {
        throw std::exception("wrong file format");
    }
    int i;
    for (i = RuleBirth.size(); std::isdigit(str[i]); ++i) B.push_back(str[i] - DiffAsciiCodeIntSymbol);
    if (str.substr(i, StrSharpS.size()) != StrSharpS) {
        throw std::exception("wrong file format");
    }
    for (i += StrSharpS.size(); std::isdigit(str[i]); ++i) S.push_back(str[i] - DiffAsciiCodeIntSymbol);

    int n, m;
    inputStream >> str;
    if (str != StingSize) {
        throw std::exception("wrong file format");
    }
    inputStream >> str;
    char *err;
    n = strtol(str.c_str(), &err, 10);
    if (*err != 0) {
        throw std::exception("wrong file format");
    }
    inputStream >> str;
    m = strtol(str.c_str(), &err, 10);
    if (*err != 0) {
        throw std::exception("wrong file format");
    }

    std::vector<std::string> field1;
    for (i = 0; i < n; ++i) {
        field1.push_back(std::string (m, CharIsNotAlive));
    }

    for (int x, y; inputStream >> str;) {
        x = strtol(str.c_str(), &err, 10);
        if (*err != 0) {
            throw std::exception("wrong file format");
        }
        inputStream >> str;
        y = strtol(str.c_str(), &err, 10);
        if (*err != 0) {
            throw std::exception("wrong file format");
        }
        field1[y][x] = CharIsAlive;
    }

    Field field = Field(field1, B, S, name);

    return field;
}
