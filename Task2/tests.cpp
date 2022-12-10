#include "gtest/gtest.h"
#include "LifeConway.h"
#include <vector>

//using namespace std;
namespace {
    const std::vector<std::string> fieldTest1 = {
            {lifeConway::CharIsNotAlive, lifeConway::CharIsAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive},
            {lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive},
            {lifeConway::CharIsAlive, lifeConway::CharIsAlive, lifeConway::CharIsAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive},
            {lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive},
            {lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive}
    }; //field from in.txt

    const std::vector<std::string> fieldTest2 = {
            {lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive},
            {lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive},
            {lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsAlive, lifeConway::CharIsNotAlive},
            {lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsAlive},
            {lifeConway::CharIsNotAlive, lifeConway::CharIsNotAlive, lifeConway::CharIsAlive, lifeConway::CharIsAlive, lifeConway::CharIsAlive}
    };

    const std::string TestStringStream = "#Life 1.06\n#N Universe\n#R B3/S23\n#S 5 5\n0 2\n1 0\n1 2\n2 1\n2 2";

    const int ArgcWithoutFiles = 1;

    const std::string StandardRules = "B3/S23";
}


TEST(test, StandartConstructorTest) {
    std::vector<std::string> field; // default field filling
    for (int i = 0; i < 9; ++i) {
        field.push_back(std::string(9, lifeConway::CharIsNotAlive));
    }
    field[3][4] = lifeConway::CharIsAlive;
    field[4][4] = lifeConway::CharIsAlive;
    field[5][4] = lifeConway::CharIsAlive;

    lifeConway::ProgramContext context(ArgcWithoutFiles, nullptr);
    lifeConway::InputFileParser parser;
    lifeConway::Field test = parser.pars(*context.returnInStream());
    EXPECT_EQ(test.returnField(), field);
    EXPECT_EQ(test.returnName(), lifeConway::StandardName);
    EXPECT_EQ(test.returnRules(), StandardRules);
}

TEST(test, ReadFieldFromFileTest) {
    std::stringstream testStream;
    testStream << TestStringStream;
    lifeConway::InputFileParser parser;
    lifeConway::Field field = parser.pars(testStream);
    std::vector<std::string> fieldChar = field.returnField();

    EXPECT_EQ(field.returnField(), fieldTest1);
    EXPECT_EQ(field.returnName(), "Universe");
    EXPECT_EQ(field.returnRules(), StandardRules);

}

TEST(test, TickTest) {
    lifeConway::Field field(fieldTest1);
    field.tick(8);

    EXPECT_EQ(field.returnField(), fieldTest2);
}

