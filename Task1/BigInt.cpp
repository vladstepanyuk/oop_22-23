#include <iostream>
#include <string>
#include "BigInt.h"

const char DifferenceAsciiCodeIntSymbol = '0';
const char CharZero = '0';
const char CharOne = '1';
const char CharPlus = '+';
const char CharMinus = '-';

BigInt::BigInt() : number(1, CharZero), isPositive(true) {};

BigInt::BigInt(int val) : number(std::to_string(abs(val))), isPositive(val == abs(val)) {};

BigInt::BigInt(std::string string1) {
    try {
        isNumber(string1); //cause an exception to throw
    }
    catch (std::invalid_argument &e) {
        std::cerr << e.what() << std::endl;
        return;
    }
    this->isPositive = string1[0] != CharMinus;
    this->number = (string1[0] == CharPlus || string1[0] == CharMinus) ? string1.substr(1) : string1;
    int numOfZeros = 0;
    for (int i = 0; i < this->number.size() - 1; ++i) {
        if (this->number[i] == CharZero) numOfZeros++;
        else break;
    }
    this->number = this->number.substr(numOfZeros);
}; // бросать исключение std::invalid_argument при ошибке

BigInt::BigInt(const BigInt &bigInt) : number(bigInt.number), isPositive(bigInt.isPositive) {};

BigInt::~BigInt() = default;

BigInt &BigInt::operator=(const BigInt &bigInt) {
    if (bigInt == *this) {
        return *this;
    } else {
        this->number = bigInt.number;
        this->isPositive = bigInt.isPositive;
        return *this;
    }
}
//возможно присваивание самому себе!

BigInt BigInt::operator~() const {
    BigInt newNum = *this;
    std::string binStr = newNum.convertNumToBin();
    for (int i = 0; i < binStr.size(); ++i) {
        binStr[i] = binStr[i] == CharOne ? CharZero : CharOne;
    }
    newNum = newNum.convertBinToNum(binStr);
    return newNum;
}

BigInt &BigInt::operator++() {
    BigInt one = 1;
    *this += one;
    return *this;
}

BigInt BigInt::operator++(int val) {
    BigInt NewNum = *this;
    ++*this;
    return NewNum;
}

BigInt &BigInt::operator--() {
    BigInt one = 1;
    *this -= one;
    return *this;
}

BigInt BigInt::operator--(int) {
    BigInt NewNum = *this;
    --*this;
    return NewNum;
}

BigInt &BigInt::operator+=(const BigInt &val2) {
    BigInt val11 = *this > val2 ? *this : val2;
    BigInt val22 = *this > val2 ? val2 : *this;
    bool sign = val11.isPositive;
    if (val11.isPositive == val22.isPositive) {
        val11.isPositive = true;
        val22.isPositive = true;
    } else {
        val11.isPositive = true;
        val22.isPositive = false;
    }
    std::string result = val11.number;
    reverseStr(result);
    result.push_back(CharZero);

    int len = val22.length();
    for (int i = 0; i < len; ++i) {
        int rez = val11.sign() * (result[i] - DifferenceAsciiCodeIntSymbol) +
                  val22.sign() * (val22.number[val22.length() - i - 1] - DifferenceAsciiCodeIntSymbol);
        rez = rez >= 0 ? rez : (-20 - rez); // если результат сложения меньше нуля, то мы "занимаем" один у старшего разряда
        result[i] = abs(rez) % 10 + DifferenceAsciiCodeIntSymbol;
        int j = i + 1;
        while (abs(rez) > 9) {
            rez = result[j] - DifferenceAsciiCodeIntSymbol + (rez / 10);
            rez = rez >= 0 ? rez : (-20 - rez);
            result[j] = abs(rez) % 10 + DifferenceAsciiCodeIntSymbol;
            j++;
        }
    }

    int num_of_nuls = 0;
    for (int i = result.size() - 1; i >= 1; --i) {
        if (result[i] == CharZero) num_of_nuls++;
        else break;
    }
    result.resize(result.size() - num_of_nuls);
    result.push_back(sign ? CharPlus : CharMinus);
    reverseStr(result);
    *this = BigInt(result);
    if (this->number == std::string(1, CharZero)) this->isPositive = true;
    return *this;
}

BigInt &BigInt::operator*=(const BigInt &val) {
    BigInt newNum = 0;
    std::string string1 = this->number;
    std::string string2 = val.number;
    std::string ten1;
    for (int i = string2.size() - 1; i >= 0; --i) {
        int multiplier1 = string2[i] - DifferenceAsciiCodeIntSymbol;
        std::string ten2;
        for (int j = string1.size() - 1; j >= 0; --j) {
            int multiplier2 = string1[j] - DifferenceAsciiCodeIntSymbol;
            newNum += BigInt(std::to_string(multiplier1 * multiplier2) + ten1 + ten2);
            ten2 += CharZero;
        }
        ten1 += CharZero;
    }
    *this = this->isPositive == val.isPositive ? (newNum) : (-newNum);
    return *this;
}

BigInt &BigInt::operator-=(const BigInt &val) {
    BigInt helpNum = -val;
    *this += helpNum;
    return *this;
}

BigInt &BigInt::operator/=(const BigInt &val) {
    BigInt newNum = 0;
    std::string divisible = this->number;
    BigInt divider = val.isPositive ? val : -val;
    int helpNum1 = 0;
    std::string string1;
    for (int i = 0; i < divisible.size(); ++i) {
        newNum *= 10;
        BigInt helpNum2 = string1 + divisible.substr(helpNum1, i - helpNum1 + 1);
        if (divider > helpNum2) {
            continue;
        } else {
            BigInt oneMoreHelpNum = divider;
            int j = 0;
            while (oneMoreHelpNum <= helpNum2) {
                oneMoreHelpNum += divider;
                j++;
            }
            oneMoreHelpNum -= divider;
            oneMoreHelpNum -= helpNum2;
            string1 = oneMoreHelpNum.number;
            newNum += j;
            helpNum1 = i + 1;
        }
    }
    *this = this->isPositive == val.isPositive ? (+newNum) : (-newNum);
    return *this;
}

BigInt &BigInt::operator^=(const BigInt &val) {
    BigInt bigNum = *this >= val ? *this : val;
    BigInt smallNum = !(*this >= val) ? *this : val;
    std::string helpStr1 = bigNum.convertNumToBin();
    std::string helpStr2 = smallNum.convertNumToBin();
    reverseStr(helpStr1);
    reverseStr(helpStr2);

    for (int i = 0; i < helpStr2.size(); ++i) {
        helpStr1[i] = (helpStr1[i] != helpStr2[i]) + DifferenceAsciiCodeIntSymbol;
    }
    for (int i = helpStr2.size(); i < helpStr1.size(); ++i) {
        helpStr1[i] = helpStr1[i] == CharOne ? CharOne : CharZero;
    }
    reverseStr(helpStr1);
    *this = convertBinToNum(helpStr1);
    return *this;
}

BigInt &BigInt::operator%=(const BigInt &val) {
    BigInt newNum = *this;
    newNum /= val;
    newNum *= val;
    newNum -= *this;
    *this = -newNum;
    return *this;
}

BigInt &BigInt::operator&=(const BigInt &val) {
    BigInt bigNum = *this >= val ? *this : val;
    BigInt smallNum = !(*this >= val) ? *this : val;
    std::string helpStr1 = bigNum.convertNumToBin();
    std::string helpStr2 = smallNum.convertNumToBin();
    reverseStr(helpStr1);
    reverseStr(helpStr2);

    for (int i = 0; i < helpStr2.size(); ++i) {
        helpStr2[i] = (static_cast<bool>(helpStr1[i] - DifferenceAsciiCodeIntSymbol) &&
                       static_cast<bool>(helpStr2[i] - DifferenceAsciiCodeIntSymbol)) + DifferenceAsciiCodeIntSymbol;
    }
    reverseStr(helpStr2);
    *this = convertBinToNum(helpStr2);
    return *this;
}

BigInt &BigInt::operator|=(const BigInt &val) {
    BigInt bigNum = *this >= val ? *this : val;
    BigInt smallNum = !(*this >= val) ? *this : val;
    std::string helpStr1 = bigNum.convertNumToBin();
    std::string helpStr2 = smallNum.convertNumToBin();
    reverseStr(helpStr1);
    reverseStr(helpStr2);

    for (int i = 0; i < helpStr2.size(); ++i) {
        helpStr1[i] = (static_cast<bool>(helpStr1[i] - DifferenceAsciiCodeIntSymbol) ||
                       static_cast<bool>(helpStr2[i] - DifferenceAsciiCodeIntSymbol)) +
                      DifferenceAsciiCodeIntSymbol;
    }

    reverseStr(helpStr1);
    *this = convertBinToNum(helpStr1);
    return *this;
};

BigInt BigInt::operator+() const {
    BigInt newInt = BigInt(*this);
    newInt.isPositive = this->isPositive;
    return newInt;
} // unary +
BigInt BigInt::operator-() const {
    BigInt newInt = BigInt(*this);
    newInt.isPositive = !this->isPositive;
    return newInt;
}  // unary -

bool BigInt::operator==(const BigInt &bigInt) const {
    return (this->isPositive == bigInt.isPositive) && (this->number == bigInt.number);
}

bool BigInt::operator!=(const BigInt &bigInt) const {
    return (this->isPositive != bigInt.isPositive) || (this->number != bigInt.number);
}

bool BigInt::operator<(const BigInt &val) const {
    return !(*this > val) && *this != val;
}

bool BigInt::operator>(const BigInt &val) const {
    if (this->number.size() > val.number.size()) {
        return true;
    } else if (this->number.size() < val.number.size()) {
        return false;
    } else {
        return this->number > val.number;
    }
}

bool BigInt::operator<=(const BigInt &val) const {
    return !(*this > val);
}

bool BigInt::operator>=(const BigInt &val) const {
    return *this > val || val == *this;
}

BigInt::operator int() const {
    return this->sign() * atoi(this->number.c_str());
}

size_t BigInt::size() const {
    return number.size() + sizeof(bool);
}

BigInt::operator std::string() const {
    return this->isPositive ? this->number : CharMinus + this->number;
}


int BigInt::sign() const {
    return this->isPositive ? 1 : (-1);
}

size_t BigInt::length() const {
    return number.size();
}

void BigInt::isNumber(std::string string1) {
    if (!(string1[0] == CharPlus || string1[0] == CharMinus || (string1[0] >= CharZero && string1[0] <= '9')))
        throw std::invalid_argument("Invalid syntax.");
    unsigned int len = string1.size();
    for (int i = 1; i < len; ++i) {
        if (string1[i] < CharZero || string1[i] > '9') throw std::invalid_argument("Invalid syntax.");
    }
}

void BigInt::reverseStr(std::string &str) {
    int n = str.length();

    // Swap character starting from two
    // corners
    for (int i = 0; i < n / 2; i++)
        std::swap(str[i], str[n - i - 1]);
}

std::string BigInt::convertNumToBin() {
    BigInt two = 1;
    BigInt newNum = this->isPositive ? *this : -*this;

    std::string resStr;
    while (two <= newNum) {
        resStr.push_back(CharZero);
        two *= 2;
    }
    resStr.push_back(this->isPositive ? CharZero : CharOne);
    two /= 2;
    for (int i = resStr.size() - 2; i >= 0; --i) {
        if (two <= newNum) {
            resStr[i] = CharOne;
            newNum -= two;
        }
        two /= 2;
    }
    reverseStr(resStr);
    return resStr;
}

BigInt BigInt::convertBinToNum(std::string bin) {
//        std::string bin = this->number;
    reverseStr(bin);
    BigInt resNum = 0;
    BigInt two = 1;

    for (int i = 0; i < bin.size() - 1; ++i) {
        if (bin[i] == CharOne) resNum += two;
        two *= 2;
    }
    resNum = bin[bin.size() - 1] == CharZero ? resNum : -resNum;
    return resNum;
}


BigInt operator+(const BigInt &val1, const BigInt &val2) {
    BigInt newNum = val1;
    newNum += val2;
    return newNum;
}

BigInt operator-(const BigInt &val1, const BigInt &val2) {
    BigInt newNum = val1;
    newNum -= val2;
    return newNum;
}

BigInt operator*(const BigInt &val1, const BigInt &val2) {
    BigInt newNum = val1;
    newNum *= val2;
    return newNum;
}

BigInt operator/(const BigInt &val1, const BigInt &val2) {
    BigInt newNum = val1;
    newNum /= val2;
    return newNum;
}

BigInt operator^(const BigInt &val1, const BigInt &val2) {
    BigInt newNum = val1;
    newNum ^= val2;
    return newNum;
};

BigInt operator%(const BigInt &val1, const BigInt &val2) {
    BigInt newNum = val1;
    newNum %= val2;
    return newNum;
}

BigInt operator&(const BigInt &val1, const BigInt &val2) {
    BigInt newNum = val1;
    newNum &= val2;
    return newNum;
}

BigInt operator|(const BigInt &val1, const BigInt &val2) {
    BigInt newNum = val1;
    newNum |= val2;
    return newNum;
}


std::ostream &operator<<(std::ostream &o, const BigInt &i) {
    return o << i.operator std::string();
}
