#pragma once

#include "Field.h"
#include "InputFileParser.h"
#include "Interface.h"

class LifeConway {
public:

    LifeConway(int argc, char **argv);

    void setGameMode(Interface *pInterface);

    virtual void startGame();

    Field * returnField();

    ~LifeConway();

private:
    Field field;
    Interface *anInterface;
    std::string outputFileName;
    int ticksNum;
};
