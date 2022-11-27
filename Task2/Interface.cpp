//
// Created by io on 24.10.2022.
//

#include "Interface.h"

const char CharSpace = ' ';
const char CharOne = '1';
const std::string TickCommand1 = "tick";
const std::string TickCommand2 = "t";
const std::string DumpCommand = "dump";
const std::string ExitCommand = "exit";
const std::string  HelpCommand = "help";

using namespace lifeConway;

void Interface::game(Field &field, int n, const std::string &outputFileNamed) {

}

void OnlineInterface::game(Field &field, int n, const std::string &outputFileName) {
    std::string str;
    int iterationsNum = 0;
    std::cout << "num of iterations:" << iterationsNum << std::endl;
    Printer printer;
    printer.printTerminal(field);

    std::string command;
    std::string arg;

    while (true) {
        std::getline(std::cin, str, '\n');

        try { returnCommandArg(str, command, arg); }
        catch (std::exception &err) {
            std::cerr << err.what() << std::endl;
            continue;
        }


        if (command == TickCommand1 || command == TickCommand2) {
            tickCommand(field, arg, iterationsNum, printer);
        } else if (command == ExitCommand) {
            return;
        } else if (command == HelpCommand) {
            printHelp();
        } else if (command == DumpCommand) {
            dumpCommand(field, arg, printer);
        } else {
            std::cerr << "wrong command" << std::endl;
            continue;
        }
    }
}

void OnlineInterface::returnCommandArg(const std::string &string, std::string &command, std::string &arg) {
    size_t firstSpaceIndex = string.find(CharSpace);
    if (firstSpaceIndex != std::string::npos) {
        command = string.substr(0, firstSpaceIndex);
        arg = string.substr(firstSpaceIndex + 1);
    } else if (string == TickCommand1 || string == TickCommand2) {
        command = TickCommand1;
        arg = CharOne;
    } else if (string == DumpCommand) {
        throw std::exception("wrong command");
    } else {
        command = string;
    }
}

void OnlineInterface::tickCommand(Field &field, const std::string &arg, int &iterationsNum, Printer &printer) {
    char *err;
    int n = strtol(arg.c_str(), &err, 10);
    if (*err != 0) {
        std::cerr << "wrong command" << std::endl;
        return;
    }
    system("CLS");
    field.tick(n);
    iterationsNum += n;
    std::cout << "num of iterations:" << iterationsNum << std::endl;
    printer.printTerminal(field);
}

void OnlineInterface::printHelp() {
    system("CLS");
    std::cout << "dump filename - save universe to file" << std::endl;
    std::cout << "tick n (t n) - calculate n iterations and print field" << std::endl;
    std::cout << "exit - stop game" << std::endl;
}

void OnlineInterface::dumpCommand(Field &field, const std::string &arg, Printer &printer) {
    printer.printFile(field, arg);
}

void OfflineInterface::game(Field &field, int n, const std::string &outputFileName) {
    field.tick(n);
    Printer printer;
    printer.printFile(field, outputFileName);
}
