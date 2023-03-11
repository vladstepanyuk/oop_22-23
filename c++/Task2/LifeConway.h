#pragma once
#include "Field.h"
#include "InputFileParser.h"
#include "Interface.h"
#include "ProgramContext.h"

namespace lifeConway {

    class LifeConway final {
    public:

        explicit LifeConway(ProgramContext &context);

        void setGameMode(Interface *pInterface);

        virtual void startGame();

        Field &returnField();

        ~LifeConway();

    private:
        Field field;
        Interface *anInterface;
        std::ostream *outputStream;
        int ticksNum;
    };

}
