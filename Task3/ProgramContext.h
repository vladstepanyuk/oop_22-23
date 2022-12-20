#pragma once
#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <boost/program_options.hpp>

namespace po = boost::program_options;

namespace soundProcessor{
    class ProgramContext {
    public:
        ProgramContext(int argc, char **argv);

        std::istream *returnInputStream(int N);

        std::istream *returnConfigStream();

        std::ostream * returnOutputStream();

        ~ProgramContext();
    private:
        bool thereIsC;
        std::vector<std::istream *>inputStreams;
        std::istream *configStream;
        std::ostream *outputStream;
    };
}
