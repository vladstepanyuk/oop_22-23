//
// Created by io on 24.10.2022.
//

#pragma once
#include "Field.h"
#include "Printer.h"


class Interface {
public:
    virtual void game(Field *field, int n , const std::string &outputFileNamed);
};

class OnlineInterface : public Interface {
public:
    void game(Field *field, int n, const std::string &outputFileName) override;
private:
    void returnCommandArg(const std::string &string, std::string *command, std::string *arg);
};

class OfflineInterface : public Interface {
public:
    void game(Field *field, int n, const std::string &outputFileName) override;
};



