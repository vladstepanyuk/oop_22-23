#include "ProgramContext.h"

namespace {
    const int ArgcWithoutFile = 1;
    const int ArgcOnlineWithFile = 2;
    const int ArgcFileName = 1;
    const int ArgcNumTicks = 2;
    const int ArgcOutputFileName = 3;
    const int NoTicks = -1;
    const int ArgcOffline = 4;
}

const std::string lifeConway::standardField = "#Life 1.06\n#N NoName\n#R B3/S23\n#S 9 9\n4 3\n4 4\n4 5";

lifeConway::ProgramContext::ProgramContext(int argc, char **argv) {
    if (argc == ArgcWithoutFile){
        std::stringstream *standardInStream = new std::stringstream();
        *standardInStream << standardField;
        inputStream=standardInStream;
        ticksNum = NoTicks;
        isOnline = true;
    } else if(argc == ArgcOnlineWithFile){
        std::ifstream *inputFile = new std::ifstream();
        inputFile->open(argv[ArgcFileName]);
        inputStream = inputFile;
        ticksNum = NoTicks;
        isOnline = true;
    } else if(argc == ArgcOffline){
        std::ifstream *inputFile = new std::ifstream();
        inputFile->open(argv[ArgcFileName]);
        inputStream = inputFile;
        char *err;
        ticksNum = strtol(argv[ArgcNumTicks], &err, 10);
        if (!*err) throw std::exception("ticks number - nan ");
        std::ofstream *outputFile = new std::ofstream();
        outputFile->open(argv[ArgcOutputFileName]);
        outputStream = outputFile;
        isOnline = false;
    }
}

std::istream *lifeConway::ProgramContext::returnInStream() {
    return inputStream;
}

std::ostream *lifeConway::ProgramContext::returnOutStream() {
    return outputStream;
}

int lifeConway::ProgramContext::returnTicksNum() const {
    return ticksNum;
}

lifeConway::ProgramContext::~ProgramContext() {
    delete inputStream;
    if (!isOnline) delete outputStream;
}

bool lifeConway::ProgramContext::IsOnline() const {
    return isOnline;
}
