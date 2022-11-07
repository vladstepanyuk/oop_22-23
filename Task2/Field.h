#pragma once
#include <vector>
#include <string>
#include <iostream>


class Field {
public:
    Field();

    Field(std::vector<std::vector<char>> field, std::vector<char> B = {3}, std::vector<char> S = {2, 3},
          std::string name = "NoName");

    Field(const Field &field);

    ~Field();

    std::string returnName();

    std::string returnRules();

    void tick(int n);

    std::vector<std::vector<char>> returnField();
private:

    static bool checkCondition(Field field, int i, int j);

    std::string name;
    std::vector<char> B;
    std::vector<char> S;
    std::vector<std::vector<char>> field;
};


