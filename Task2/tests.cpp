#include "gtest/gtest.h"
#include "LifeConway.h"
#include <vector>

using namespace std;

const char CharIsNotAlive = -78;
const char CharIsAlive = -79;

const std::vector<vector<char>> fieldTest1 = {
        {CharIsNotAlive, CharIsAlive, CharIsNotAlive, CharIsNotAlive, CharIsNotAlive},
        {CharIsNotAlive, CharIsNotAlive, CharIsAlive, CharIsNotAlive, CharIsNotAlive},
        {CharIsAlive, CharIsAlive, CharIsAlive, CharIsNotAlive, CharIsNotAlive},
        {CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsNotAlive},
        {CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsNotAlive}
}; //field from in.txt

const std::vector<vector<char>> fieldTest2 = {
        {CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsNotAlive},
        {CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsNotAlive},
        {CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsAlive, CharIsNotAlive},
        {CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsNotAlive, CharIsAlive},
        {CharIsNotAlive, CharIsNotAlive, CharIsAlive, CharIsAlive, CharIsAlive}
};

TEST(test, StandartConstructorTest) {
    vector<vector<char>> field;
    for (int i = 0; i < 9; ++i) {
        field.push_back(std::vector<char>(9, CharIsNotAlive));
    }
    field[3][4] = CharIsAlive;
    field[4][4] = CharIsAlive;
    field[5][4] = CharIsAlive;
    Field test;
    EXPECT_EQ(test.returnField(), field);
    EXPECT_EQ(test.returnName(), "NoName");
    EXPECT_EQ(test.returnRules(), "B3/S23");
}

TEST(test, ReadFieldFromFileTest) {
    char fileName[] = "in.txt";
    InputFileParser parser;
    Field field = parser.pars(fileName);
    vector<vector<char>> fieldChar = field.returnField();

    EXPECT_EQ(field.returnField(), fieldTest1);
    EXPECT_EQ(field.returnName(), "Universe");
    EXPECT_EQ(field.returnRules(), "B3/S23");

}

TEST(test, TickTest) {
    Field field(fieldTest1);
    field.tick(8);

    EXPECT_EQ(field.returnField(), fieldTest2);
}

