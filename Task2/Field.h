#pragma once
#include <vector>
#include <string>
#include <iostream>

namespace lifeConway{
    extern const std::vector<char> StandardBRules;
    extern const std::vector<char> StandardSRules;

    extern const char DiffAsciiCodeIntSymbol;

    extern const char CharIsNotAlive;
    extern const char CharIsAlive;

    extern const std::string StandardName;

    class Field final {
    public:
        Field();

        Field(const std::vector<std::string> &field, const std::vector<char> &B = StandardBRules, const std::vector<char> &S = StandardSRules,
              const std::string &name = StandardName);

        Field(const Field &field);

        ~Field();

        const std::string & returnName();

        std::string returnRules();

        void tick(int n);

        std::vector<std::string> & returnField();
    private:

        static bool checkCondition(Field field, int i, int j);

        std::string name;
        std::vector<char> B;
        std::vector<char> S;
        std::vector<std::string> field;
    };
}

