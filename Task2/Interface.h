//
// Created by io on 24.10.2022.
//

#pragma once
#include "Field.h"
#include "Printer.h"

namespace lifeConway {
    class Interface {
    public:
        virtual void game(Field &field, int n , const std::string &outputFileNamed);
    };

    class OnlineInterface : public Interface {
    public:
        void game(Field &field, int n, const std::string &outputFileName) override;
    private:
        void returnCommandArg(const std::string &string, std::string &command, std::string &arg);
        void tickCommand(Field &field, const std::string &arg, int &iterationsNum, Printer &printer);
        void printHelp();
        void dumpCommand(Field &field, const std::string &arg, Printer &printer);
    };

    class OfflineInterface : public Interface {
    public:
        void game(Field &field, int n, const std::string &outputFileName) override;
    };
}
