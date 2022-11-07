//
// Created by io on 24.10.2022.
//

#include "Interface.h"

const char CharSpace = ' ';
const char CharOne = '1';


void Interface::game(Field *field, int n, const std::string &outputFileNamed) {

}

void OnlineInterface::game(Field *field, int n, const std::string &outputFileName) {
    std::string str;
    int k = 0;
    std::cout << "num of iterations:" << k << std::endl;
    Printer printer;
    printer.printTerminal(*field);

    std::string command;
    std::string arg;

    while (true) {
        std::getline(std::cin, str, '\n');

        try { returnCommandArg(str, &command, &arg); }
        catch (std::exception &err) {
            std::cerr << err.what() << std::endl;
            continue;
        }


        if (command == "tick" || command == "t") {
            char *err;
            n = strtol(arg.c_str(), &err, 10);
            if (*err != 0) {
                std::cerr << "wrong command" << std::endl;
                continue;
            }
            system("CLS");
            field->tick(n);
            k += n;
            std::cout << "num of iterations:" << k << std::endl;

            printer.printTerminal(*field);

        } else if (command == "exit") {
            return;
        } else if (command == "help") {
            system("CLS");
            std::cout << "dump filename - save universe to file" << std::endl;
            std::cout << "tick n (t n) - calculate n iterations and print field" << std::endl;
            std::cout << "exit - stop game" << std::endl;
        } else if (command == "dump") {
            printer.printFile(*field, arg);
        } else {
            std::cerr << "wrong command" << std::endl;
            continue;
        }
    }
}

void OnlineInterface::returnCommandArg(const std::string &string, std::string *command, std::string *arg) {
    char *firstSpacePointer = const_cast<char *>(strchr(string.c_str(), CharSpace));
    if (firstSpacePointer != nullptr) {
        *command = string.substr(0, firstSpacePointer - string.c_str());
        *arg = string.substr(firstSpacePointer - string.c_str() + 1);
    } else if (string == "tick" || string == "t") {
        *command = "tick";
        *arg = CharOne;
    } else if (string == "dump") {
        throw std::exception("wrong command");
    } else {
        *command = string;
    }
}

void OfflineInterface::game(Field *field, int n, const std::string &outputFileName) {
    field->tick(n);
    Printer printer;
    printer.printFile(*field, outputFileName);
}
