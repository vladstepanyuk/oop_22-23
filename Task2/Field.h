#pragma once
#include <vector>
#include <string>
#include <iostream>

namespace lifeConway{
    class Field final {
    public:
        Field();

        Field(const std::vector<std::string> &field, const std::vector<char> &B = {3}, const std::vector<char> &S = {2, 3},
              const std::string &name = "NoName");

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

