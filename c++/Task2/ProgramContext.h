#pragma once

#include <fstream>
#include <sstream>

namespace lifeConway{

    extern const std::string standardField;

    class ProgramContext final{
    public:
        ProgramContext(int argc, char **argv);
        ~ProgramContext();
        std::istream *returnInStream();
        std::ostream *returnOutStream();
        bool IsOnline() const;
        int returnTicksNum() const;
    private:
        std::istream *inputStream;
        int ticksNum;
        std::ostream *outputStream;
        bool isOnline;
    };
}



