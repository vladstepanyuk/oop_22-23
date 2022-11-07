#include "LifeConway.h"
//#include <windows.h>

LifeConway::LifeConway(int argc, char **argv) {
    if(argc == 1) {
        Field field1;
        field = field1;
        ticksNum = -1;
    } else if (argc == 2) {
        InputFileParser parser;
        try {
            field = parser.pars(argv[1]);
        } catch (std::exception &error) {
            std::cerr << error.what() << std::endl;
        }
    } else if (argc == 4) {
        try {
            InputFileParser parser;
            field = parser.pars(argv[1]);
        } catch (std::exception &error) {
            std::cerr << error.what() << std::endl;
        }
        char *err;
        ticksNum = strtol(argv[2], &err, 10);
        if (!*err) std::cerr << "ticks number - nan " << std:: endl;
        outputFileName = std::string(argv[3]);
    } else{
        std::cerr << "program args wrong format" << std:: endl;
        Field field1;
        field = field1;
    }
}

void LifeConway::startGame() {
    anInterface->game(this->returnField(), this->ticksNum, this->outputFileName);
}

Field *LifeConway::returnField() {
    return &field;
}

void LifeConway::setGameMode(Interface *pInterface) {
    this->anInterface = pInterface;
}

LifeConway::~LifeConway() = default;
