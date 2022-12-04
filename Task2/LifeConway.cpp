#include "LifeConway.h"
//#include <windows.h>

namespace {
    const int ArgcWithoutFile = 1;
    const int ArgcOnlineWithFile = 2;
    const int ArgcFileName = 1;
    const int ArgcNumTicks = 2;
    const int ArgcOutputFileName = 3;
    const int NoTicks = -1;
}

lifeConway::LifeConway::LifeConway(ProgramContext &context) {
    InputFileParser parser;
    field = parser.pars(*context.returnInStream());
    ticksNum = context.returnTicksNum();
    outputStream = context.returnOutStream();
}

void lifeConway::LifeConway::startGame() {
    anInterface->game(this->returnField(), this->ticksNum, this->outputStream);
}

lifeConway::Field & lifeConway::LifeConway::returnField() {
    return field;
}

void lifeConway::LifeConway::setGameMode(Interface *pInterface) {
    this->anInterface = pInterface;
}

lifeConway::LifeConway::~LifeConway() = default;
