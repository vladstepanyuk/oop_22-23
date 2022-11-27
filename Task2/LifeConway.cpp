#include "LifeConway.h"
//#include <windows.h>

const int ArgcWithoutFile = 1;
const int ArgcOnlineWithFile = 2;
const int ArgcOffline = 4;
const int ArgcFileName = 1;
const int ArgcNumTicks = 2;
const int ArgcOutputFileName = 3;
const int NoTicks = -1;

using namespace lifeConway;

LifeConway::LifeConway(int argc, char **argv) {
    if(argc == ArgcWithoutFile) {
        Field field1;
        field = field1;
        ticksNum = NoTicks;
    } else if (argc == ArgcOnlineWithFile) {
        InputFileParser parser;
        try {
            field = parser.pars(argv[ArgcFileName]);
        } catch (std::exception &error) {
            std::cerr << error.what() << std::endl;
        }
    } else if (argc == ArgcOffline) {
        try {
            InputFileParser parser;
            field = parser.pars(argv[ArgcFileName]);
        } catch (std::exception &error) {
            std::cerr << error.what() << std::endl;
        }
        char *err;
        ticksNum = strtol(argv[ArgcNumTicks], &err, 10);
        if (!*err) std::cerr << "ticks number - nan " << std:: endl;
        outputFileName = std::string(argv[ArgcOutputFileName]);
    } else{
        std::cerr << "program args wrong format" << std:: endl;
        Field field1;
        field = field1;
    }
}

void LifeConway::startGame() {
    anInterface->game(this->returnField(), this->ticksNum, this->outputFileName);
}

Field & LifeConway::returnField() {
    return field;
}

void LifeConway::setGameMode(Interface *pInterface) {
    this->anInterface = pInterface;
}

LifeConway::~LifeConway() = default;
