#pragma once

#include <iostream>
#include <string>

class BigInt {
public:
    BigInt();

    BigInt(int val);

    BigInt(std::string string1); // бросать исключение std::invalid_argument при ошибке
    BigInt(const BigInt &bigInt);

    ~BigInt();

    BigInt &operator=(const BigInt &bigInt); //возможно присваивание самому себе!

    BigInt operator~() const;

    BigInt &operator++();

    BigInt operator++(int val);

    BigInt &operator--();

    BigInt operator--(int);

    BigInt &operator+=(const BigInt &val2);

    BigInt &operator*=(const BigInt &val);

    BigInt &operator-=(const BigInt &val);

    BigInt &operator/=(const BigInt &val);

    BigInt &operator^=(const BigInt &val);

    BigInt &operator%=(const BigInt &val);

    BigInt &operator&=(const BigInt &val);

    BigInt &operator|=(const BigInt &val);

    BigInt operator+() const; // unary +
    BigInt operator-() const; // unary -

    bool operator==(const BigInt &bigInt) const;

    bool operator!=(const BigInt &bigInt) const;

    bool operator<(const BigInt &val) const;

    bool operator>(const BigInt &val) const;

    bool operator<=(const BigInt &val) const;

    bool operator>=(const BigInt &val) const;

    operator int() const;

    size_t size() const;

    operator std::string() const;

private:

    int sign() const;

    size_t length() const;

    static void isNumber(std::string string1);

    void reverseStr(std::string &str);

    std::string convertNumToBin();

    BigInt convertBinToNum(std::string bin);

    std::string number;
    bool isPositive;
};

BigInt operator+(const BigInt &val1, const BigInt &val2);

BigInt operator-(const BigInt &val1, const BigInt &val2);

BigInt operator*(const BigInt &val1, const BigInt &val2);

BigInt operator/(const BigInt &val1, const BigInt &val2);

BigInt operator^(const BigInt &val1, const BigInt &val2);

BigInt operator%(const BigInt &val1, const BigInt &val2);

BigInt operator&(const BigInt &val1, const BigInt &val2);

BigInt operator|(const BigInt &val1, const BigInt &val2);


std::ostream &operator<<(std::ostream &o, const BigInt &i);
