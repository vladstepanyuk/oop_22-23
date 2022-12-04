#include "Field.h"


namespace {

    const char CharB = 'B';
    const std::string StrSharpS = "/S";
    const std::vector<int> xDiff = {-1, 0, 1};
    const std::vector<int> yDiff = {-1, 0, 1};
}

const char lifeConway::DiffAsciiCodeIntSymbol = '0';
const std::vector<char> lifeConway::StandardBRules = {3};
const std::vector<char> lifeConway::StandardSRules = {2, 3};
const char lifeConway::CharIsAlive = -79;
const char lifeConway::CharIsNotAlive = -78;
const std::string lifeConway::StandardName = "NoName";

lifeConway::Field::Field() = default;

lifeConway::Field::Field(const std::vector<std::string> &field, const std::vector<char> &B, const std::vector<char> &S, const std::string &name)
        : name(name), B(B), S(S), field(field) {}


lifeConway::Field::Field(const Field &field) = default;


std::vector<std::string> & lifeConway::Field::returnField() {
    return this->field;
}

const std::string & lifeConway::Field::returnName() {
    return this->name;
}

std::string lifeConway::Field::returnRules() {
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

void lifeConway::Field::tick(int n) {
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

bool lifeConway::Field::checkCondition(Field field, int i, int j) {

    int counter = 0;
    for (int k = 0; k < xDiff.size(); ++k)
        for (int l = 0; l < yDiff.size(); ++l) {
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

lifeConway::Field::~Field() = default;


