#include "Field.h"

const char DiffAsciiCodeIntSymbol = '0';
const char CharIsNotAlive = -78;
const char CharIsAlive = -79;
const char CharB = 'B';
const std::string StrSharpS = "/S";
const int StandardFieldSize = 9;
const std::vector<char> StandardBRules = {3};
const std::vector<char> StandardSRules = {2, 3};
const std::string StandardName = "NoName";


using namespace lifeConway;
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
    name = StandardName;
    B = StandardBRules;
    S = StandardSRules;
    for (int i = 0; i < StandardFieldSize; ++i) {
        field.push_back(std::string(StandardFieldSize, CharIsNotAlive));
    }
    field[3][4] = CharIsAlive;
    field[4][4] = CharIsAlive;
    field[5][4] = CharIsAlive;
}

Field::Field(const std::vector<std::string> &field, const std::vector<char> &B, const std::vector<char> &S, const std::string &name)
        : name(name), B(B), S(S), field(field) {}


Field::Field(const Field &field) = default;


std::vector<std::string> & Field::returnField() {
    return this->field;
}

const std::string & Field::returnName() {
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


