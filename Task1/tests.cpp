#include "gtest/gtest.h"
#include "BigInt.h"

TEST(BigIntTest, constructorTest){
    BigInt number;
    EXPECT_EQ(number.operator std::string(), "0");
    EXPECT_EQ(number.sign(), +1);

    number = BigInt(-1337);
    EXPECT_EQ(number.operator std::string(), "1337");
    EXPECT_EQ(number.sign(), -1);

    number = BigInt("-17177171718171071708");
    EXPECT_EQ(number.operator std::string(), "17177171718171071708");
    EXPECT_EQ(number.sign(), -1);
}


TEST(BigIntTest, additionTest){
    BigInt number1 = std::string("7265987299552068805");
    BigInt number2 = std::string("-3789476347");
    number1 += number2;
    EXPECT_EQ(number1.operator std::string(), "7265987295762592458");
    EXPECT_EQ(number1.sign(), +1);

    number1 = std::string("-7265987295762592458");
    number2 = std::string("-3789476347");
    EXPECT_EQ((number1 + number2).operator std::string(), "7265987299552068805");
    EXPECT_EQ((number1 + number2).sign(), -1);

    number1 = std::string("700000000000000");
    ++number1;
    EXPECT_EQ(number1.operator std::string(), "700000000000001");
    EXPECT_EQ(number1.sign(), +1);

    number2 = number1++;
    EXPECT_EQ(number1.operator std::string(), "700000000000002");
    EXPECT_EQ(number1.sign(), +1);
    EXPECT_EQ(number2.operator std::string(), "700000000000001");
    EXPECT_EQ(number2.sign(), +1);
}

TEST(BigIntTest, subtractionTest){
    BigInt number1 = std::string("10000007");
    BigInt number2 = std::string("7");
    number1 -= number2;
    EXPECT_EQ(number1.operator std::string(), "10000000");
    EXPECT_EQ(number1.sign(), +1);

    number1 = std::string("10000000");
    number2 = std::string("-7");
    EXPECT_EQ((number1 - number2).operator std::string(), "10000007");
    EXPECT_EQ((number1 - number2).sign(), +1);

    EXPECT_EQ((number1 - number1).operator std::string(), "0");

    number1 = std::string("700000000000002");
    --number1;
    EXPECT_EQ(number1.operator std::string(), "700000000000001");
    EXPECT_EQ(number1.sign(), +1);

    number2 = number1--;
    EXPECT_EQ(number1.operator std::string(), "700000000000000");
    EXPECT_EQ(number1.sign(), +1);
    EXPECT_EQ(number2.operator std::string(), "700000000000001");
    EXPECT_EQ(number2.sign(), +1);
}

TEST(BigIntTest, multTest){
    BigInt number1 = 20;
    BigInt number2 = std::string("3000000000000000");
    number1 *= number2;
    EXPECT_EQ(number1.operator std::string(), "60000000000000000");
    EXPECT_EQ(number1.sign(), +1);

    number1 = 20;
    number2 = std::string("-3000000000000000");

    EXPECT_EQ((number1 * number2).operator std::string(), "60000000000000000");
    EXPECT_EQ((number1 * number2).sign(), -1);
}

TEST(BigIntTest, divisionTest){
    BigInt number1 = std::string("3000000000000000");
    BigInt number2 = 20;
    number1 /= number2;
    EXPECT_EQ(number1.operator std::string(), "150000000000000");
    EXPECT_EQ(number1.sign(), +1);

    number1 = std::string("-3000000000000000");
    number2 = 20;

    EXPECT_EQ((number1 / number2).operator std::string(), "150000000000000");
    EXPECT_EQ((number1 / number2).sign(), -1);

    number1 = std::string("503275082375982750982605239085702985");
    number2 = 10;

    EXPECT_EQ((number1 % number2).operator std::string(), "5");
    EXPECT_EQ((number1 % number2).sign(), +1);
}

TEST(BigIntTest, bitOpsTest){
    BigInt number1 = 789;
    number1 = ~number1;
    EXPECT_EQ((number1).operator std::string(), "234");
    EXPECT_EQ((number1).sign(), -1);

    BigInt number2 = BigInt("4294967296");
    number2 |= number1;
    EXPECT_EQ((number2).operator std::string(), "4294967786");
    EXPECT_EQ((number2).sign(), 1);

    number2 &= BigInt("4294967296");
    EXPECT_EQ((number2).operator std::string(), "4294967296");
    EXPECT_EQ((number2).sign(), 1);
}

TEST(BigIntTest, compareTest){
    BigInt number1 = 100;
    BigInt number2 = -100;
    EXPECT_EQ((number1 != number2), true);
    EXPECT_EQ((number1 == -number2), true);
    EXPECT_EQ((number1 >= -number2), true);
    EXPECT_EQ((number1 <= -number2), true);

    number2 = 7;
    EXPECT_EQ((number1 > number2), true);
    EXPECT_EQ((number2 < number1), true);
}

TEST(BigIntTest, intTest){
    BigInt number = 100;
    EXPECT_EQ(int(number), 100);
}

TEST(BigIntTest, stringTest){
    BigInt number = BigInt("100");
    EXPECT_EQ(number.operator std::string(), "100");
}

TEST(BigIntTest, sizeTest){
    BigInt number = BigInt("100");
    EXPECT_EQ(number.size(), 3 + sizeof(bool));
}

TEST(BigIntTest, signTest){
    BigInt number = 100;
    EXPECT_EQ(number.sign(), 1);

    number = -100;
    EXPECT_EQ(number.sign(), -1);
}
