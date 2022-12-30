#pragma once
#include <iostream>
#include <istream>
#include <string>
#include "Reader.h"
//#include <fstream>

namespace soundProcessor{
    extern const int ChunkIdSize;
    extern const int StandardHz ;



    class WaveHeadParser {
    public:
        void pars(std::istream &inputStream);

    private:
    };
}


