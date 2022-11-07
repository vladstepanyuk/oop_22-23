#include "Field.h"

const char DiffAsciiCodeIntSymbol = '0';
const char CharIsNotAlive = -78;
const char CharIsAlive = -79;
const char CharB = 'B';
const std::string StrSharpS = "/S";

//000000000 standard field
//000000000
//000000000
//000010000
//000010000
//000010000
//000000000
//000000000
//000000000

Field::Field() {
    name = "NoName";
    B = {3};
    S = {2, 3};
    for (int i = 0; i < 9; ++i) {
        field.push_back(std::vector<char>(9, CharIsNotAlive));
    }
    field[3][4] = CharIsAlive;
    field[4][4] = CharIsAlive;
    field[5][4] = CharIsAlive;
}

Field::Field(std::vector<std::vector<char>> field, std::vector<char> B, std::vector<char> S, std::string name)
        : name(name), B(B), S(S), field(field) {}


Field::Field(const Field &field) = default;


std::vector<std::vector<char>> Field::returnField() {
    return this->field;
}

std::string Field::returnName() {
    return this->name;
}

std::string Field::returnRules() {
    std::string rules = std::string(1, CharB);
    for (int i = 0; i < this->B.size(); ++i) {
        rules += (this->B[i] + DiffAsciiCodeIntSymbol);
    }
    rules += StrSharpS;
    for (int i = 0; i < this->S.size(); ++i) {
        rules += (this->S[i] + DiffAsciiCodeIntSymbol);
    }
    return rules;
}

void Field::tick(int n) {
    for (int h = 0; h < n; h++) {
        Field newField = *this;
        int n1 = this->field.size();
        int m;
        for (int i = 0; i < n1; ++i) {
            m = this->field[i].size();
            for (int j = 0; j < m; ++j) {
                if (checkCondition(*this, i, j)) newField.field[i][j] = CharIsAlive;
                else newField.field[i][j] = CharIsNotAlive;
            }
        }
        *this = newField;
    }
}

bool Field::checkCondition(Field field, int i, int j) {
    int xDiff[] = {-1, 0, 1};
    int yDiff[] = {-1, 0, 1};
    int counter = 0;
    for (int k = 0; k < 3; ++k)
        for (int l = 0; l < 3; ++l) {
            int x1 = (field.field.size() + i + xDiff[k]) % field.field.size();
            int y1 = (field.field[0].size() + j + yDiff[l]) % field.field[0].size();
            if (field.field[x1][y1] == CharIsAlive && (x1 != i || y1 != j)) counter++;
        }
    if (field.field[i][j] == CharIsNotAlive) {
        for (int k = 0; k < field.B.size(); ++k) if (field.B[k] == counter) return true;
        return false;
    } else {
        for (int k = 0; k < field.S.size(); ++k) if (field.S[k] == counter) return true;
        return false;
    }
}

Field::~Field() = default;


